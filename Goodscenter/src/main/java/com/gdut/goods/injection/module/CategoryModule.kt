package com.gdut.goods.injection.module

import com.gdut.goods.service.CategoryService
import com.gdut.goods.service.impl.CategoryServiceImpl

import dagger.Module
import dagger.Provides


/**
 * @author  Li Xuyang
 * date  2019/8/26 15:03
 */
@Module
class CategoryModule {

    @Provides
    fun providesUserService(categoryService: CategoryServiceImpl):CategoryService{
        return categoryService
    }




}