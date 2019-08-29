package com.gdut.user.service

import rx.Observable


/**
 * @author  Li Xuyang
 * date  2019/8/4 15:31
 */
interface UploadService {
    fun getUploadToken(): Observable<String>


}