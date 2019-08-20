package com.gdut.gdutmall.ui.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gdut.base.ext.loadUrl
import com.gdut.gdutmall.R
import kotlinx.android.synthetic.main.layout_topic_item.view.*

/**
 * @author  Li Xuyang
 * date  2019/8/20 22:41
 */
class TopicAdapter(private val context: Context, private val list: List<String>) : PagerAdapter() {
    override fun destroyItem(parent: ViewGroup, paramInt: Int, paramObject: Any) {
        parent.removeView(paramObject as View)
    }

    override fun getCount(): Int {
        return this.list.size
    }

    override fun instantiateItem(parent: ViewGroup, position: Int): Any {
        val rooView = LayoutInflater.from(this.context).inflate(R.layout.layout_topic_item, null)
        //loadUrl为扩展方法
        rooView.mTopicIv.loadUrl(list[position])
        parent.addView(rooView)
        return rooView
    }

    override fun isViewFromObject(paramView: View, paramObject: Any): Boolean {
        return paramView === paramObject
    }
}