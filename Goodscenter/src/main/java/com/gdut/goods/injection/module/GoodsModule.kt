package com.gdut.goods.injection.module

import com.gdut.goods.service.GoodsService
import com.gdut.goods.service.impl.GoodsServiceImpl
import dagger.Module
import dagger.Provides


/**
 * @author  Li Xuyang
 * date  2019/8/26 15:03
 */
@Module
class GoodsModule {

    @Provides
    fun provideGoodsService(goodsService: GoodsServiceImpl): GoodsService {
        return goodsService
    }


}