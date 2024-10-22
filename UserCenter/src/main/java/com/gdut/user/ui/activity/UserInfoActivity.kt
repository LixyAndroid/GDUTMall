package com.gdut.user.ui.activity

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.gdut.base.common.BaseConstant
import com.gdut.base.ext.onClick
import com.gdut.base.ui.activity.BaseTakePhotoActivity
import com.gdut.base.utils.AppPrefsUtils
import com.gdut.base.utils.GlideUtils
import com.gdut.provider.common.ProviderConstant
import com.gdut.user.R
import com.gdut.user.data.protocol.UserInfo
import com.gdut.user.injection.component.DaggerUserComponent
import com.gdut.user.injection.module.UserModule
import com.gdut.user.presenter.UserInfoPresenter
import com.gdut.user.presenter.view.UserInfoView
import com.gdut.user.utils.UserPrefsUtils
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.model.TResult
import com.qiniu.android.http.ResponseInfo
import com.qiniu.android.storage.UpCompletionHandler
import com.qiniu.android.storage.UploadManager
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.toast
import org.json.JSONObject

/**
 * 用户信息
 */

class UserInfoActivity : BaseTakePhotoActivity<UserInfoPresenter>(), UserInfoView,
    TakePhoto.TakeResultListener {


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
        //requestDangerousPermissions()

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


    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule())
            .build().inject(this)
        mPresenter.mView = this
    }


    override fun takeSuccess(result: TResult) {
//        Log.d("TakePhoto", result.image.originalPath)
//        Log.d("TakePhoto", result.image.compressPath)

        mLocalFileUrl = result.image.compressPath

        mPresenter.getUploadToken()

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
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    fun requestDangerousPermissions() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//版本判断
//            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                val strings = arrayOf(
//                    Manifest.permission.CAMERA,
//                    Manifest.permission.RECORD_AUDIO,
//                    Manifest.permission.READ_EXTERNAL_STORAGE,
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE
//                )
//                ActivityCompat.requestPermissions(this, strings, 100)
//            }
//        }
//    }


}
