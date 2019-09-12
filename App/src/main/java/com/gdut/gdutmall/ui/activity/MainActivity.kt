package com.gdut.gdutmall.ui.activity


import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.gdut.base.ui.activity.BaseActivity
import com.gdut.base.utils.AppPrefsUtils
import com.gdut.gdutmall.R
import com.gdut.gdutmall.ui.fragment.HomeFragment
import com.gdut.gdutmall.ui.fragment.MeFragment
import com.gdut.goods.common.GoodsConstant
import com.gdut.goods.event.AddCartEvent
import com.gdut.goods.event.UpdateCartSizeEvent
import com.gdut.goods.ui.fragment.CartFragment
import com.gdut.goods.ui.fragment.CategoryFragment
import com.gdut.message.ui.fragment.MessageFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : BaseActivity() {

    private val mStack = Stack<androidx.fragment.app.Fragment>()
    private val mHomeFragment by lazy { HomeFragment() }
    private val mCategoryFragment by lazy { CategoryFragment() }
    private val mCartFragment by lazy { CartFragment() }
    private val mMsgFragment by lazy { MessageFragment() }
    private val mMeFragment by lazy { MeFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initFragment()
        initBottomNav()
        changeFragment(0)
        initObserve()
        loadCartSize()

    }

    private fun initFragment() {

        val manager = supportFragmentManager.beginTransaction()
        manager.add(R.id.mContainer, mHomeFragment)
        manager.add(R.id.mContainer, mCategoryFragment)
        manager.add(R.id.mContainer, mCartFragment)
        manager.add(R.id.mContainer, mMsgFragment)
        manager.add(R.id.mContainer, mMeFragment)
        manager.commit()

        mStack.add(mHomeFragment)
        mStack.add(mCategoryFragment)
        mStack.add(mCartFragment)
        mStack.add(mMsgFragment)
        mStack.add(mMeFragment)

    }


    private fun initBottomNav() {
        mBottomNavBar.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {
            override fun onTabReselected(position: Int) {

            }

            override fun onTabUnselected(position: Int) {

            }

            override fun onTabSelected(position: Int) {

                changeFragment(position)
            }

        })
    }

    private fun changeFragment(position: Int) {
        val manager = supportFragmentManager.beginTransaction()
        for (fragment in mStack) {
            manager.hide(fragment)
        }

        manager.show(mStack[position])
        manager.commit()

    }


    private fun initObserve() {
        Bus.observe<UpdateCartSizeEvent>()


        Bus.observe<AddCartEvent>()
            .subscribe {
                loadCartSize()
            }.registerInBus(this)
    }


    private fun loadCartSize() {
        mBottomNavBar.checkCartBadge(AppPrefsUtils.getInt(GoodsConstant.SP_CART_SIZE))
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}
