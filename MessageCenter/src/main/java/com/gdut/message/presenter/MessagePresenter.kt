package com.gdut.message.presenter

import com.gdut.base.ext.excute
import com.gdut.base.presenter.BasePresenter
import com.gdut.base.rx.BaseSubscriber
import com.gdut.message.data.protocol.Message
import com.gdut.message.presenter.view.MessageView
import com.gdut.message.service.MessageService
import javax.inject.Inject

/*
    消息列表 Presenter
 */
class MessagePresenter @Inject constructor() : BasePresenter<MessageView>() {

    @Inject
    lateinit var messageService: MessageService

    /*
        获取消息列表
     */
    fun getMessageList() {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        messageService.getMessageList().excute(object : BaseSubscriber<MutableList<Message>?>(mView) {
            override fun onNext(t: MutableList<Message>?) {
                mView.onGetMessageResult(t)
            }
        }, lifecycleProvider)

    }


}
