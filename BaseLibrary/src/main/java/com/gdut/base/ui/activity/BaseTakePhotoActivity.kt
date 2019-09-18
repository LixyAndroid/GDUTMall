package com.gdut.base.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.gdut.base.common.BaseApplication
import com.gdut.base.injection.component.ActivityComponent
import com.gdut.base.injection.component.DaggerActivityComponent
import com.gdut.base.injection.module.ActivityModule
import com.gdut.base.injection.module.LifecycleProviderModule
import com.gdut.base.presenter.BasePresenter
import com.gdut.base.presenter.view.BaseView
import com.gdut.base.utils.DateUtils
import com.gdut.base.widgets.ProgressLoading
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.compress.CompressConfig
import com.jph.takephoto.model.TResult
import org.jetbrains.anko.toast
import java.io.File
import javax.inject.Inject

/**
 * @author  Li Xuyang
 * date  2019/8/8 15:58
 */
open abstract class BaseTakePhotoActivity<T : BasePresenter<*>> : BaseActivity(), BaseView,
    TakePhoto.TakeResultListener {


    private lateinit var mTakePhoto: TakePhoto

    private lateinit var mTempFile: File

    override fun showLoading() {
        mLoadingDialog.showLoading()
    }

    override fun hideLoading() {
        mLoadingDialog.hideLoading()
    }

    override fun onError(text:String) {
        toast(text)
    }


    @Inject
    lateinit var mPresenter: T

    lateinit var activityComponent: ActivityComponent

    private lateinit var mLoadingDialog: ProgressLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActivityInjection()

        injectComponent()


        mTakePhoto = TakePhotoImpl(this, this)
        mTakePhoto.onCreate(savedInstanceState)


        mLoadingDialog = ProgressLoading.create(this)

        ARouter.getInstance().inject(this)

    }

    abstract fun injectComponent()

    private fun initActivityInjection() {

        activityComponent = DaggerActivityComponent.builder().appComponent((application as BaseApplication).appComponent).activityModule(
            ActivityModule(this))
            .lifecycleProviderModule(LifecycleProviderModule(this))
            .build()
    }


    override fun takeSuccess(result: TResult) {
//        Log.d("TakePhoto", result.image.originalPath)
//        Log.d("TakePhoto", result.image.compressPath)


    }

    override fun takeCancel() {

    }

    override fun takeFail(result: TResult?, msg: String?) {
        Log.e("TakePhoto", msg)
    }


    protected fun showAlertView() {
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
}