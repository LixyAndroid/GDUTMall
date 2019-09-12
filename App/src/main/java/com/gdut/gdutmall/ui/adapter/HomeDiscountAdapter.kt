package com.gdut.gdutmall.ui.adapter

import android.content.Context
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gdut.gdutmall.R
import com.gdut.base.ui.adapter.BaseRecyclerViewAdapter
import com.gdut.base.utils.GlideUtils
import kotlinx.android.synthetic.main.layout_home_discount_item.view.*

/**
 * @author  Li Xuyang
 * date  2019/8/20 22:07
 */
class HomeDiscountAdapter(context:Context) : BaseRecyclerViewAdapter<String,HomeDiscountAdapter.ViewHolder>(context) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(mContext)
            .inflate(R.layout.layout_home_discount_item,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        GlideUtils.loadUrlImage(mContext,dataList[position],holder.itemView.mGoodsIconIv)
        holder.itemView.mDiscountAfterTv.text = "￥999.00"
        holder.itemView.mDiscountBeforeTv.text ="￥2188.00"
    }

    class ViewHolder(view: View): androidx.recyclerview.widget.RecyclerView.ViewHolder(view){

        init {
            view.mDiscountBeforeTv.paint.flags = Paint.STRIKE_THRU_TEXT_FLAG
            view.mDiscountBeforeTv.paint.isAntiAlias = true
        }
    }

}