package com.cfxc.router

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.cfxc.common.constants.RouteConstant
import com.cfxc.router.annotation.Constants
import com.cfxc.router.annotation.Route
import com.cfxc.router.annotation.utils.Utils
import com.cfxc.router.constant.TestConstant
import com.cfxc.router.core.template.Router
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @description
 * @author: created by peihao.feng
 * @date: 4/21/21
 */
@Route(destinationText = RouteConstant.LOGIN_FRAGMENT)
class LoginFragment : Fragment(R.layout.fragment_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btn_login).setOnClickListener {
            Toast.makeText(requireContext(),"login success",Toast.LENGTH_SHORT).show()
            TestConstant.isNeedLogin = false
            lifecycleScope.launch {
                delay(300)
                val destination =
                    if (Utils.isEmpty(arguments?.getString(
                            Constants.KEY_CONTINUE_DESTINATION))) {
                        RouteConstant.MAIN_MODULE_HOME_FRAGMENT
                    } else {
                        arguments?.getString(Constants.KEY_CONTINUE_DESTINATION)
                    }
                Router.getInstance().build(destination)
                    .navigation(findNavController(),RouteConstant.LOGIN_FRAGMENT,true)
            }
        }
    }
}