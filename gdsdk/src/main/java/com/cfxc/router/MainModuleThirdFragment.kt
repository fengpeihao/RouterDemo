package com.cfxc.router

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.cfxc.common.constants.RouteConstant
import com.cfxc.router.annotation.Route


/**
 * @description
 * @author: created by peihao.feng
 * @date: 4/22/21
 */
@Route(destinationText = RouteConstant.MAIN_MODULE_THIRD_FRAGMENT)
class MainModuleThirdFragment : Fragment(R.layout.fragment_main_module_third) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            view.findViewById<TextView>(R.id.tv_received).text = it.toString()
        }
    }
}