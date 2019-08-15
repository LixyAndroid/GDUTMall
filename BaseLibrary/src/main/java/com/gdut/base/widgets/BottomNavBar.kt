package com.gdut.base.widgets

import android.content.Context
import android.util.AttributeSet
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.gdut.base.R

/**
 * @author  Li Xuyang
 * date  2019/8/15 11:18
 */
class BottomNavBar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : BottomNavigationBar(context, attrs, defStyleAttr) {


    init {
        //首页
        val homeItem = BottomNavigationItem(R.drawable.btn_nav_home_press,resources.getString(R.string.nav_bar_home))
                //未选中的
            .setInactiveIconResource(R.drawable.btn_nav_home_normal)
            .setActiveColorResource(R.color.common_blue)
            .setInActiveColorResource(R.color.text_normal)

        //分类
        val categoryItem = BottomNavigationItem(R.drawable.btn_nav_category_press,resources.getString(R.string.nav_bar_category))
            //未选中的
            .setInactiveIconResource(R.drawable.btn_nav_category_normal)
            .setActiveColorResource(R.color.common_blue)
            .setInActiveColorResource(R.color.text_normal)


        //购物车
        val cartItem = BottomNavigationItem(R.drawable.btn_nav_cart_press,resources.getString(R.string.nav_bar_cart))
            //未选中的
            .setInactiveIconResource(R.drawable.btn_nav_cart_normal)
            .setActiveColorResource(R.color.common_blue)
            .setInActiveColorResource(R.color.text_normal)

        //消息
        val msgItem = BottomNavigationItem(R.drawable.btn_nav_msg_press,resources.getString(R.string.nav_bar_msg))
            //未选中的
            .setInactiveIconResource(R.drawable.btn_nav_msg_normal)
            .setActiveColorResource(R.color.common_blue)
            .setInActiveColorResource(R.color.text_normal)

        //我的
        val userItem = BottomNavigationItem(R.drawable.btn_nav_user_press,resources.getString(R.string.nav_bar_user))
            //未选中的
            .setInactiveIconResource(R.drawable.btn_nav_user_normal)
            .setActiveColorResource(R.color.common_blue)
            .setInActiveColorResource(R.color.text_normal)


        setMode(BottomNavigationBar.MODE_FIXED)

        setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)

        setBarBackgroundColor(R.color.common_white)

        addItem(homeItem)
            .addItem(categoryItem)
            .addItem(cartItem)
            .addItem(msgItem)
            .addItem(userItem)
            .setFirstSelectedPosition(0)
            .initialise()


    }
}