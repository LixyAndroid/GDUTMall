package com.gdut.user.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.gdut.user.R
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mRegisterBtn.setOnClickListener {
            toast("注册")
        }
    }
}
