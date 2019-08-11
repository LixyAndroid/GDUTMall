package com.gdut.base.injection


import java.lang.annotation.Documented
import java.lang.annotation.Retention
import javax.inject.Scope
import java.lang.annotation.RetentionPolicy.RUNTIME

/**
 * @author  Li Xuyang
 * date  2019/8/11 17:08
 * Activity级别 作用域
 */
@Scope
@Documented
@Retention(RUNTIME)
annotation class ActivityScope