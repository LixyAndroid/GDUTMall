package com.gdut.user.ui.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.support.annotation.RequiresApi
import android.support.v4.app.ActivityCompat
import android.util.Log
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.gdut.base.common.BaseConstant
import com.gdut.base.ext.onClick
import com.gdut.base.ui.activity.BaseMvpActivity
import com.gdut.user.R
import com.gdut.user.data.protocol.UserInfo
import com.gdut.user.injection.component.DaggerUserComponent
import com.gdut.user.injection.module.UserModule
import com.gdut.user.presenter.UserInfoPresenter
import com.gdut.user.presenter.view.UserInfoView
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.compress.CompressConfig
import com.jph.takephoto.model.TResult
import com.kotlin.base.utils.AppPrefsUtils
import com.kotlin.base.utils.DateUtils
import com.kotlin.base.utils.GlideUtils
import com.kotlin.provider.common.ProviderConstant
import com.kotlin.user.utils.UserPrefsUtils
import com.qiniu.android.http.ResponseInfo
import com.qiniu.android.storage.UpCompletionHandler
import com.qiniu.android.storage.UploadManager
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.toast
import org.json.JSONObject
import java.io.File

/**
 * 用户信息
 */

class UserInfoActivity : BaseMvpActivity<UserInfoPresenter>(), UserInfoView, TakePhoto.TakeResultListener {


    private lateinit var mTakePhoto: TakePhoto

    private lateinit var mTempFile: File

    private val mUploadManager: UploadManager by lazy {
        UploadManager()
    }

    private var mLocalFileUrl: String? = null
    private var mRemoteFileUrl: String? = null

    private var mUserIcon: String? = null
    private var mUserName: String? = null
    private var mUserMobile: String? = null
    private var mUserGender: String? = null
    private var mUserSign: String? = null


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        //拍照权限
        requestDangerousPermissions()

        mTakePhoto = TakePhotoImpl(this, this)
        mTakePhoto.onCreate(savedInstanceState)
        initView()
        initData()

    }


    /**
     * 初始化视图
     */
    private fun initView() {

        mUserIconView.onClick {
            showAlertView()
        }

        mHeaderBar.getRightView().onClick {
            mPresenter.editUser(
                mRemoteFileUrl!!,
                mUserNameEt.text.toString(),
                if (mGenderMaleRb.isChecked) "0" else "1",
                mUserSignEt.text.toString()
            )
        }

    }

    /**
     * 初始化数据
     */
    private fun initData() {
        mUserIcon = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON)
        mUserName = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)
        mUserMobile = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_MOBILE)
        mUserGender = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_GENDER)
        mUserSign = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_SIGN)


        mRemoteFileUrl = mUserIcon
        if (mUserIcon != "") {

            GlideUtils.loadUrlImage(this, mUserIcon!!, mUserIconIv)
        }
        mUserNameEt.setText(mUserName)
        mUserMobileTv.text = mUserMobile

        if (mUserGender == "0") {
            mGenderMaleRb.isChecked = true
        } else {
            mGenderFemaleRb.isChecked = true
        }

        mUserSignEt.setText(mUserSign)
    }


    private fun showAlertView() {
        AlertView("选择图片", "", "取消", null, arrayOf("拍照", "从相册中选择"), this,
            AlertView.Style.ActionSheet, object : OnItemClickListener {
                override fun onItemClick(o: Any?, position: Int) {
                    mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(), false)

                    when (position) {
                        0 -> {
                            createTempFile()
                            mTakePhoto.onPickFromCapture(Uri.fromFile(mTempFile))
                        }

                        1 -> mTakePhoto.onPickFromGallery()
                    }
                }

            }
        ).show()

    }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }


    override fun takeSuccess(result: TResult) {
//        Log.d("TakePhoto", result.image.originalPath)
//        Log.d("TakePhoto", result.image.compressPath)

        mLocalFileUrl = result.image.compressPath

        mPresenter.getUploadToken()

    }

    override fun takeCancel() {

    }

    override fun takeFail(result: TResult?, msg: String?) {
        Log.e("TakePhoto", msg)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        mTakePhoto.onActivityResult(requestCode, resultCode, data)
    }

    fun createTempFile() {
        val tempFileName = "${DateUtils.curTime}.png"

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            this.mTempFile = File(Environment.getExternalStorageDirectory(), tempFileName)
            return
        }

        this.mTempFile = File(filesDir, tempFileName)

    }

    override fun onGetUploadTokenResult(result: String) {
        mUploadManager.put(mLocalFileUrl, null, result, object : UpCompletionHandler {
            override fun complete(key: String?, info: ResponseInfo?, response: JSONObject?) {
                mRemoteFileUrl = BaseConstant.IMAGE_SERVER_ADDRESS + response?.get("hash")

                //Log.d("test", mRemoteFileUrl)

                GlideUtils.loadUrlImage(this@UserInfoActivity, mRemoteFileUrl!!, mUserIconIv)


            }

        }, null)
    }

    override fun onEditUserResult(result: UserInfo) {
        toast("修改成功")
        UserPrefsUtils.putUserInfo(result)
    }


    /**
     * 请求拍照权限
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    fun requestDangerousPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//版本判断
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                val strings = arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                ActivityCompat.requestPermissions(this, strings, 100)
            }
        }
    }


}
