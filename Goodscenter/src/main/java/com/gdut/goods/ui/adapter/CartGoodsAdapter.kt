package com.gdut.goods.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.gdut.base.ext.loadUrl
import com.gdut.base.ext.onClick
import com.gdut.base.ui.adapter.BaseRecyclerViewAdapter
import com.gdut.base.utils.DefaultTextWacher
import com.gdut.base.utils.YuanFenConverter
import com.gdut.goods.R
import com.gdut.goods.data.protocol.CartGoods
import com.gdut.goods.event.CartAllCheckedEvent
import com.gdut.goods.event.UpdateTotalPriceEvent
import com.gdut.goods.getEditText
import kotlinx.android.synthetic.main.layout_cart_goods_item.view.*

/**
 * @author  Li Xuyang
 * date  2019/8/27 20:54
 */
class CartGoodsAdapter(context: Context) : BaseRecyclerViewAdapter<CartGoods, CartGoodsAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext)
            .inflate(
                R.layout.layout_cart_goods_item,
                parent,
                false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]
        //是否选中
        holder.itemView.mCheckedCb.isChecked = model.isSelected
        //加载商品图片
        holder.itemView.mGoodsIconIv.loadUrl(model.goodsIcon)
        //商品描述
        holder.itemView.mGoodsDescTv.text = model.goodsDesc
        //商品SKU
        holder.itemView.mGoodsSkuTv.text = model.goodsSku
        //商品价格
        holder.itemView.mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(model.goodsPrice)
        //商品数量
        holder.itemView.mGoodsCountBtn.setCurrentNumber(model.goodsCount)
        //选中按钮事件
        holder.itemView.mCheckedCb.onClick {
            model.isSelected = holder.itemView.mCheckedCb.isChecked
            val isAllChecked = dataList.all {it.isSelected }
            Bus.send(CartAllCheckedEvent(isAllChecked))
            notifyDataSetChanged()
        }

        //商品数量变化监听
        holder.itemView.mGoodsCountBtn.getEditText().addTextChangedListener(object :DefaultTextWacher(){
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                model.goodsCount = s.toString().toInt()

                Bus.send(UpdateTotalPriceEvent())
            }
        }

        )

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}