package com.gdut.base.ext

import android.view.View
import android.widget.Button
import android.widget.EditText
import com.gdut.base.data.protocol.BaseResp
import com.gdut.base.rx.BaseFunc
import com.gdut.base.rx.BaseFuncBoolean
import com.gdut.base.rx.BaseSubscriber
import com.gdut.base.utils.DefaultTextWacher
import com.trello.rxlifecycle.LifecycleProvider
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 * @author  Li Xuyang
 * date  2019/8/8 17:25
 */
fun <T> Observable<T>.execute(subscriber: BaseSubscriber<T>,lifecycleProvider: LifecycleProvider<*>){
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