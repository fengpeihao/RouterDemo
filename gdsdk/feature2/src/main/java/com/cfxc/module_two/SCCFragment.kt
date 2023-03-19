package com.cfxc.module_two

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cfxc.common.constants.BundleKeyConstant
import com.cfxc.common.constants.RequestKeyConstant
import com.cfxc.common.constants.RouteConstant
import com.cfxc.router.annotation.Route
import com.cfxc.router.core.template.Router
import com.example.module_two.R

/**
 * @description
 * @author: created by peihao.feng
 * @date: 4/8/21
 */
@Route(destinationText = RouteConstant.SCC_FRAGMENT)
class SCCFragment : Fragment(R.layout.fragment_module_two_first) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.tv_received).text = arguments?.getString(BundleKeyConstant.KEY_CONTENT) ?: "null"

        view.findViewById<Button>(R.id.btn_return_back).setOnClickListener {
            parentFragmentManager.setFragmentResult(
                RequestKeyConstant.KEY_FIRST_CONTACT_SECOND,
                bundleOf(BundleKeyConstant.KEY_CONTENT to "feature2 scc says ${view.findViewById<EditText>(R.id.edt_return_content).text}")
            )
            findNavController().popBackStack()
        }

        view.findViewById<Button>(R.id.btn_goto_main_home).setOnClickListener {
            Router.getInstance().build(RouteConstant.MAIN_MODULE_HOME_FRAGMENT)
                .navigation(findNavController())
        }
    }
}