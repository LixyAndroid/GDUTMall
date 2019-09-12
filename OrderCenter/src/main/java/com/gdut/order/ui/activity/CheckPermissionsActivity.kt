package com.gdut.order.ui.activity

import android.Manifest
import android.annotation.TargetApi
import android.content.pm.PackageManager

import android.os.Build

import androidx.appcompat.app.AppCompatActivity


/**
 * Created by Li Xuyang
 * on 2019/5/27 13:40
 */

open class CheckPermissionsActivity : AppCompatActivity() {
    /**
     * 需要进行检测的权限数组
     */
    protected var needPermissions = arrayOf<String>(

        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.READ_PHONE_STATE
    )

    /**
     * 判断是否需要检测，防止不停的弹框
     */
    private var isNeedCheck = true

    override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT >= 23 && applicationInfo.targetSdkVersion >= 23) {
            if (isNeedCheck) {
                checkPermissions(*needPermissions)
            }
        }
    }

    /**
     *
     * @param permissions
     * @since 2.5.0
     */
    private fun checkPermissions(vararg permissions: String) {
        try {
            if (Build.VERSION.SDK_INT >= 23 && applicationInfo.targetSdkVersion >= 23) {
                val needRequestPermissonList = findDeniedPermissions(permissions as Array<String>)
                if (null != needRequestPermissonList && needRequestPermissonList.size > 0) {
                    val array = needRequestPermissonList.toTypedArray()
                    val method = javaClass.getMethod(
                        "requestPermissions",
                        *arrayOf<Class<*>>(Array<String>::class.java, Int::class.javaPrimitiveType!!)
                    )

                    method.invoke(this, array, PERMISSON_REQUESTCODE)
                }
            }
        } catch (e: Throwable) {
        }

    }

    /**
     * 获取权限集中需要申请权限的列表
     *
     * @param permissions
     * @return
     * @since 2.5.0
     */
    private fun findDeniedPermissions(permissions: Array<String>): List<String> {
        val needRequestPermissonList = ArrayList<String>()
        if (Build.VERSION.SDK_INT >= 23 && applicationInfo.targetSdkVersion >= 23) {
            try {
                for (perm in permissions) {
                    val checkSelfMethod = javaClass.getMethod("checkSelfPermission", String::class.java)
                    val shouldShowRequestPermissionRationaleMethod = javaClass.getMethod(
                        "shouldShowRequestPermissionRationale",
                        String::class.java
                    )
                    if (checkSelfMethod.invoke(
                            this,
                            perm
                        ) as Int != PackageManager.PERMISSION_GRANTED || shouldShowRequestPermissionRationaleMethod.invoke(
                            this,
                            perm
                        ) as Boolean
                    ) {
                        needRequestPermissonList.add(perm)
                    }
                }
            } catch (e: Throwable) {

            }

        }
        return needRequestPermissonList
    }

    /**
     * 检测是否所有的权限都已经授权
     * @param grantResults
     * @return
     * @since 2.5.0
     */
    private fun verifyPermissions(grantResults: IntArray): Boolean {
        for (result in grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

    @TargetApi(23)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, paramArrayOfInt: IntArray
    ) {
        if (requestCode == PERMISSON_REQUESTCODE) {
            if (!verifyPermissions(paramArrayOfInt)) {
                isNeedCheck = false
            }
        }
    }

    companion object {

        private val PERMISSON_REQUESTCODE = 0
    }


}