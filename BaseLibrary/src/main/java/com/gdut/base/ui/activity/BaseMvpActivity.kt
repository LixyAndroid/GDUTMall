package com.gdut.base.ui.activity

import android.os.Bundle import android.os.PersistableBundle
import com.alibaba.android.arouter.launcher.ARouter
import com.gdut.base.common.BaseApplication
import com.gdut.base.injection.component.ActivityComponent
import com.gdut.base.injection.component.DaggerActivityComponent
import com.gdut.base.injection.module.ActivityModule
import com.gdut.base.injection.module.LifecycleProviderModule

import com.gdut.base.presenter.BasePresenter
import com.gdut.base.presenter.view.BaseView
import com.gdut.base.widgets.ProgressLoading
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 * @author  Li Xuyang
 * date  2019/8/8 15:58
 */
open abstract class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {
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
}