package com.gdut.user.service.impl

import com.gdut.base.ext.convert
import com.gdut.user.data.repository.UploadRepository
import com.gdut.user.service.UploadService
import rx.Observable
import javax.inject.Inject

/**
 * @author  Li Xuyang
 * date  2019/8/4 15:33
 */
class UploadServiceImpl @Inject  constructor():UploadService {

    override fun getUploadToken(): Observable<String> {
        return repository.getUploadToken().convert()
    }


    @Inject
    lateinit var repository: UploadRepository

}