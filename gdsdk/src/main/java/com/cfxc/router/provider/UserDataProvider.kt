package com.cfxc.router.provider

import com.cfxc.common.constants.IUserDataProvider
import com.cfxc.common.constants.RouteConstant
import com.cfxc.router.annotation.Route

/**
 * @description
 * @author: created by peihao.feng
 * @date: 4/22/21
 */
@Route(destinationText = RouteConstant.USER_DATA_PROVIDER)
class UserDataProvider: IUserDataProvider {

    override fun getUserName():String{
        return "Tom"
    }
}