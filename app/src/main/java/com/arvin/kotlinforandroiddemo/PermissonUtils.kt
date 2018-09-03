package com.arvin.kotlinforandroiddemo

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat

/**
 * 是否有指定权限
 */
fun Context.checkHasPermission(permission: String) =
        ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

/**
 * 获取权限
 */
fun Activity.getPermissions(permissions: Array<String>, requestCode: Int) {
    ActivityCompat.requestPermissions(this, permissions, requestCode)
}

/**
 * 用户是否拒绝了某权限
 */
fun Activity.isDenyPermission(permission: String) =
    ActivityCompat.shouldShowRequestPermissionRationale(this, permission)