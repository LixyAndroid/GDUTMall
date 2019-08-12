package com.gdut.user.ui.activity

import android.os.Bundle
import com.gdut.base.ext.enable
import com.gdut.base.ext.onClick
import com.gdut.base.ui.activity.BaseMvpActivity
import com.gdut.user.R
import com.gdut.user.injection.component.DaggerUserComponent
import com.gdut.user.injection.module.UserModule
import com.gdut.user.presenter.ResetPwdPresenter
import com.gdut.user.presenter.view.ResetPwdView
import kotlinx.android.synthetic.main.activity_reset_pwd.*
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop
import org.jetbrains.anko.toast

/**
 * 重置密码界面
 */

class ResetPwdActivity : BaseMvpActivity<ResetPwdPresenter>(), ResetPwdView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_pwd)


        initView()

    }

    /**
     * 初始化视图
     */
    private fun initView() {

        //监听输入框
        mConfirmBtn.enable(mPwdEt, { isBtnEnable() })
        mConfirmBtn.enable(mPwdConfirmEt, { isBtnEnable() })





        mConfirmBtn.onClick {
            if (mPwdEt.text.toString() != mPwdEt.text.toString()) {
                toast("密码不一致")
                return@onClick
            } else
                mPresenter.resetPwd(intent.getStringExtra("mobile"), mPwdEt.text.toString())
        }


    }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }


    private fun isBtnEnable(): Boolean {
        return mPwdEt.text.isNullOrEmpty().not() &&
                mPwdConfirmEt.text.isNullOrEmpty().not()


    }


    /**
     * 修改密码回调
     */

    override fun onResetPwdResult(result: String) {
        toast(result)
        startActivity(intentFor<LoginActivity>().singleTop().clearTop())
    }


}
