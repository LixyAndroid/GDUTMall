package com.gdut.goods.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.Gravity
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.gdut.base.ext.onClick
import com.gdut.base.ui.activity.BaseActivity
import com.gdut.base.utils.AppPrefsUtils
import com.gdut.goods.R
import com.gdut.goods.common.GoodsConstant
import com.gdut.goods.event.AddCartEvent
import com.gdut.goods.event.UpdateCartSizeEvent
import com.gdut.goods.ui.adapter.GoodsDetailVpAdapter
import com.gdut.provider.common.afterLogin
import kotlinx.android.synthetic.main.activity_goods_detail.*
import org.jetbrains.anko.startActivity
import q.rorbin.badgeview.QBadgeView

/**
 * @author  Li Xuyang
 * date  2019/8/26 22:28
 */
class GoodsDetailActivity : BaseActivity() {

    private lateinit var mCartBdage: QBadgeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods_detail)

        initView()
        initObserve()
        loadCartSize()
    }


    private fun initView() {
        mGoodsDetailTab.tabMode = TabLayout.MODE_FIXED

        mGoodsDetailVp.adapter = GoodsDetailVpAdapter(supportFragmentManager, this)
        mGoodsDetailTab.setupWithViewPager(mGoodsDetailVp)

        mAddCartBtn.onClick {

            afterLogin {
                Bus.send(AddCartEvent())
            }
        }

        mEnterCartTv.onClick {
            startActivity<CartActivity>()
        }

        mLeftIv.onClick {
            finish()
        }


        mCartBdage = QBadgeView(this)
    }

    private fun initObserve() {
        Bus.observe<UpdateCartSizeEvent>()


        Bus.observe<AddCartEvent>()
            .subscribe {
                setCartBadge()
            }.registerInBus(this)
    }


    private fun loadCartSize() {

        setCartBadge()
    }

    private fun setCartBadge() {
        mCartBdage.badgeGravity = Gravity.END or Gravity.TOP
        mCartBdage.setGravityOffset(22f, -2f, true)
        mCartBdage.setBadgeTextSize(10f, true)
        mCartBdage.bindTarget(mEnterCartTv).badgeNumber =
            AppPrefsUtils.getInt(GoodsConstant.SP_CART_SIZE)

    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}