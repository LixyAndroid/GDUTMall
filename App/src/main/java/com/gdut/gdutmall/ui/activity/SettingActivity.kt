package com.gdut.gdutmall.ui.activity

import android.os.Bundle
import com.gdut.base.ext.onClick
import com.gdut.base.ui.activity.BaseActivity
import com.gdut.gdutmall.R
import com.gdut.provider.common.isLogined
import com.gdut.user.utils.UserPrefsUtils
import kotlinx.android.synthetic.main.activity_setting.*
import org.jetbrains.anko.toast

/**
 * @author  Li Xuyang
 * date  2019/8/24 23:11
 */
class SettingActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        mUserProtocolTv.onClick {
            toast("用户协议")
        }
        mFeedBackTv.onClick {
            toast("反馈意见")
        }
        mAboutTv.onClick {
            toast("关于")
        }

        //退出登录，清空本地用户数据
        mLogoutBtn.onClick {
            if (isLogined()) {
                UserPrefsUtils.putUserInfo(null)
                finish()
            } else {
                toast("您还没有登录呢")
            }

        }
    }
}