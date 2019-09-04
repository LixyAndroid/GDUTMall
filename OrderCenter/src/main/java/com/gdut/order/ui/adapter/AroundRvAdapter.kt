package com.gdut.order.ui.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.amap.api.services.core.PoiItem
import com.gdut.order.R


/**
 * Created by Li Xuyang
 * on 2019/5/27 18:53
 */
class AroundRvAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var poiList = arrayListOf<PoiItem>()

    fun setPoiItemList(list: ArrayList<PoiItem>) {
        this.poiList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return poiList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewGroup: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_around_address, parent, false)
        return AroundItemHolder(itemView)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as AroundItemHolder).bindData(poiList.get(position))
    }

    inner class AroundItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvAddress: TextView = itemView.findViewById(R.id.address_title) as TextView

        init {

            itemView.setOnClickListener {
                val intent = Intent()
                intent.putExtra("address", tvAddress.text)
                (context as Activity).setResult(200, intent)
                (context as Activity).finish()
            }
        }

        fun bindData(poiItem: PoiItem) {
            tvAddress.text = poiItem.title
        }

    }

}