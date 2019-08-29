package com.gdut.base.injection

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.RUNTIME
import javax.inject.Scope

/**
 * @author  Li Xuyang
 * date  2019/8/11 17:08
 * 组件级别 作用域
 */
@Scope
@Documented
@Retention(RUNTIME)
annotation class PerComponentScope