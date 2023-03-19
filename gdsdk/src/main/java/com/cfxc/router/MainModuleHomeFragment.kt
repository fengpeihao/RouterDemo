package com.cfxc.router

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cfxc.common.constants.RouteConstant
import com.cfxc.router.annotation.Route
import com.cfxc.router.constant.TestConstant
import com.cfxc.router.core.template.Router

/**
 * @description
 * @author: created by peihao.feng
 * @date: 4/6/21
 */
//Resource IDs will be non-final in Android Gradle Plugin version 5.0, avoid using them as annotation attributes
@Route(destinationText = RouteConstant.MAIN_MODULE_HOME_FRAGMENT)
class MainModuleHomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btn_go_to_need_login).setOnClickListener {
            Router.getInstance().build(RouteConstant.MAIN_MODULE_FIRST_FRAGMENT)
                .navigation(findNavController())
        }
        view.findViewById<Button>(R.id.btn_go_to_need_prerequisite).setOnClickListener {
            Router.getInstance().build(RouteConstant.MAIN_MODULE_SECOND_FRAGMENT)
                .navigation(findNavController())
        }
        view.findViewById<Button>(R.id.btn_go_to_need_both).setOnClickListener {
            Router.getInstance().build(RouteConstant.MAIN_MODULE_THIRD_FRAGMENT)
                .navigation(findNavController())
        }
        view.findViewById<Button>(R.id.btn_go_to_module_first).setOnClickListener {
            Router.getInstance().build(RouteConstant.DDA_FRAGMENT)
                .navigation(findNavController())
        }
        view.findViewById<Button>(R.id.btn_reset_state).setOnClickListener {
            TestConstant.isNeedPrerequisite = true
            TestConstant.isNeedLogin = true
        }
    }
}