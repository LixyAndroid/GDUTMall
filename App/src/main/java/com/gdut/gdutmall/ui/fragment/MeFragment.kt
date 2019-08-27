package com.gdut.gdutmall.ui.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gdut.base.ext.loadUrl
import com.gdut.base.ext.onClick
import com.gdut.base.ui.fragment.BaseFragment
import com.gdut.gdutmall.R
import com.gdut.gdutmall.ui.activity.SettingActivity
import com.gdut.provider.common.isLogined
import com.gdut.user.ui.activity.LoginActivity
import com.gdut.user.ui.activity.UserInfoActivity
import com.gdut.base.utils.AppPrefsUtils
import com.gdut.provider.common.ProviderConstant
import kotlinx.android.synthetic.main.fragment_me.*


/**
 * @author  Li Xuyang
 * date  2019/8/24 21:46
 */
class MeFragment : BaseFragment(), View.OnClickListener {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_me, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        mUserIconIv.onClick(this)
        mUserNameTv.onClick(this)
        mSettingTv.onClick(this)

    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun loadData() {

        if (isLogined()) {
            val userIcon = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON)
            if (userIcon.isNotEmpty()) {
                mUserIconIv.loadUrl(userIcon)
            }
            mUserNameTv.text = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)
        } else {
            mUserIconIv.setImageResource(R.drawable.icon_default_user)
            mUserNameTv.text = getString(R.string.un_login_text)
        }
    }


    override fun onClick(view: View) {

        when (view.id) {
            R.id.mUserIconIv, R.id.mUserNameTv -> {
                if (isLogined()) {

                    // startActivity<UserInfoActivity>()

                    val intent = Intent()
                    //获取intent对象
                    intent.setClass(context, UserInfoActivity::class.java)
                    // 获取class是使用::反射
                    startActivity(intent)

                } else {
                    //startActivity<LoginActivity>()
                    val intent = Intent()
                    //获取intent对象
                    intent.setClass(context, LoginActivity::class.java)
                    // 获取class是使用::反射
                    startActivity(intent)
                }
            }


            R.id.mSettingTv ->{
                //startActivity<SettingActivity>()

                val intent = Intent()
                //获取intent对象
                intent.setClass(context, SettingActivity::class.java)
                // 获取class是使用::反射
                startActivity(intent)
            }
        }

    }

}