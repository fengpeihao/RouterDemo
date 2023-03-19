package com.cfxc.router

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cfxc.common.constants.RouteConstant
import com.cfxc.router.annotation.Route
import com.cfxc.router.core.template.Router

/**
 * @description
 * @author: created by peihao.feng
 * @date: 4/6/21
 */
@Route(destinationText = RouteConstant.MAIN_MODULE_FIRST_FRAGMENT)
class MainModuleFirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_module_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            view.findViewById<TextView>(R.id.tv_received).text = it.toString()
        }

        view.findViewById<Button>(R.id.btn_goto_main_second).setOnClickListener {
            Router.getInstance().build(RouteConstant.MAIN_MODULE_SECOND_FRAGMENT)
                .navigation(findNavController())
        }
    }
}