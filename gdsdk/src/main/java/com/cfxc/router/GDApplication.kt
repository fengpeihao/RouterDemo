package com.cfxc.router

import android.app.Application
import com.cfxc.router.core.template.Router

/**
 * @description
 * @author: created by peihao.feng
 * @date: 4/7/21
 */
open class GDApplication : Application() {

    companion object {
        lateinit var application: GDApplication
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        Router.init(this)
    }
}