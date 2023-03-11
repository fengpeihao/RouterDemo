package com.cfxc.router.dispatcher

import com.cfxc.router.GDApplication
import com.cfxc.router.R

/**
 * @description
 * @author: created by peihao.feng
 * @date: 4/6/21
 */
object SchemeConstant {
    val SCHEME = GDApplication.application.getString(R.string.scheme)
    val HOST = GDApplication.application.getString(R.string.host)

    val KEY_SCHEME_CONTENT = "scheme_content"
}