package com.gdut.base.ext

import android.graphics.drawable.AnimationDrawable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.gdut.base.R
import com.gdut.base.data.protocol.BaseResp
import com.gdut.base.rx.BaseFunc
import com.gdut.base.rx.BaseFuncBoolean
import com.gdut.base.rx.BaseSubscriber
import com.gdut.base.utils.DefaultTextWacher
import com.gdut.base.utils.GlideUtils
import com.kennyc.view.MultiStateView
import com.trello.rxlifecycle.LifecycleProvider
import org.jetbrains.anko.find
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 * @author  Li Xuyang
 * date  2019/8/8 17:25
 */
fun <T> Observable<T>.excute(subscriber: BaseSubscriber<T>,lifecycleProvider: LifecycleProvider<*>){
     this. observeOn(AndroidSchedulers.mainThread())
         .compose(lifecycleProvider.bindToLifecycle())
        .subscribeOn(Schedulers.io())
        .subscribe(subscriber)
}

fun <T> Observable<BaseResp<T>>.convert():Observable<T>{
    return this.flatMap (BaseFunc())

}

fun <T> Observable<BaseResp<T>>.convertBoolean():Observable<Boolean>{
    return this.flatMap (BaseFuncBoolean())

}



fun View.onClick(listener:View.OnClickListener){
    this.setOnClickListener(listener)
}

fun View.onClick(method:()->Unit){
    this.setOnClickListener{method()}
}

fun Button.enable(et: EditText,method: () -> Boolean){

    val  btn = this
    et.addTextChangedListener(object :DefaultTextWacher(){
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            btn.isEnabled = method()
        }
    })
}

/*
    ImageView加载网络图片
 */
fun ImageView.loadUrl(url: String) {
    GlideUtils.loadUrlImage(context, url, this)
}

/*
    多状态视图开始加载
 */
fun MultiStateView.startLoading(){
    //viewState = MultiStateView.VIEW_STATE_LOADING
    val loadingView = getView(MultiStateView.VIEW_STATE_LOADING)
//    val animBackground = loadingView!!.find<View>(R.id.loading_anim_view).background
//    (animBackground as AnimationDrawable).start()
}

/*
    扩展视图可见性
 */
fun View.setVisible(visible:Boolean){
    this.visibility = if (visible) View.VISIBLE else View.GONE
}