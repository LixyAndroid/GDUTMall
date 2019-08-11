package com.gdut.user.ui.activity

import android.os.Bundle
import com.gdut.base.ui.activity.BaseMvpActivity
import com.gdut.user.R
import com.gdut.user.injection.component.DaggerUserComponent

import com.gdut.user.injection.module.UserModule
import com.gdut.user.presenter.RegisterPresenter
import com.gdut.user.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast


class RegisterActivity : BaseMvpActivity<RegisterPresenter>(),RegisterView {

    override fun onRegisterResult(result: Boolean) {
        if(result){
            toast("注册成功")
        }else{
            toast("注册失败")
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)



       initInjection()

        mRegisterBtn.setOnClickListener {
           mPresenter.register(mMobileEt.text.toString(),mVerifyCodeEt.text.toString(),mPwdEt.text.toString())
        }


        mGetVerifyCodeBtn.setOnClickListener {
            mPresenter.register2(mMobileEt.text.toString(),mVerifyCodeEt.text.toString(),mPwdEt.text.toString())
        }
    }

    private fun initInjection() {

        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }
}
