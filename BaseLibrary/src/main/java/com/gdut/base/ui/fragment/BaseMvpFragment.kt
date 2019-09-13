package com.gdut.base.ui.fragment

import android.os.Bundle
import com.gdut.base.common.BaseApplication
import com.gdut.base.injection.component.ActivityComponent
import com.gdut.base.injection.component.DaggerActivityComponent
import com.gdut.base.injection.module.ActivityModule
import com.gdut.base.injection.module.LifecycleProviderModule
import com.gdut.base.presenter.BasePresenter
import com.gdut.base.presenter.view.BaseView
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject



/**
 * @author  Li Xuyang
 * date  2019/8/8 20:50
 */
open abstract class BaseMvpFragment<T : BasePresenter<*>> : BaseFragment(), BaseView {
    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun onError(text:String) {

        toast(text)
    }




    @Inject
    lateinit var mPresenter: T

    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActivityInjection()

        injectComponent()


    }

    abstract fun injectComponent()

    private fun initActivityInjection() {

        activityComponent = DaggerActivityComponent.builder().appComponent((activity?.application as BaseApplication).appComponent).activityModule(
            activity?.let { ActivityModule(it) })
            .lifecycleProviderModule(LifecycleProviderModule(this))
            .build()
    }
}