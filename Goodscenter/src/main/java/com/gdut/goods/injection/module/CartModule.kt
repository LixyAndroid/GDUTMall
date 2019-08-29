package com.gdut.goods.injection.module

import com.gdut.goods.service.CartService
import com.gdut.goods.service.impl.CartServiceImpl
import dagger.Module
import dagger.Provides

/**
 * @author  Li Xuyang
 * date  2019/8/27 17:04
 */
@Module
class CartModule {
    @Provides
    fun providesCartService(cartService: CartServiceImpl):CartService{
        return  cartService
    }
}