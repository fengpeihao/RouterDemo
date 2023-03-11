package com.cfxc.router

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cfxc.common.constants.RouteConstant
import com.cfxc.router.annotation.Constants
import com.cfxc.router.annotation.Route
import com.cfxc.router.constant.TestConstant
import com.cfxc.router.core.template.Router
import kotlinx.android.synthetic.main.fragment_prerequisite.*

/**
 * @description
 * @author: created by peihao.feng
 * @date: 4/21/21
 */
@Route(destinationText = RouteConstant.PREREQUISITE_FRAGMENT)
class PrerequisiteFragment : Fragment(R.layout.fragment_prerequisite) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_continue.setOnClickListener {
            Toast.makeText(requireContext(),"completed", Toast.LENGTH_SHORT).show()
            TestConstant.isNeedPrerequisite = false
            Handler().postDelayed({
                Router.getInstance().build(arguments?.getString(
                    Constants.KEY_CONTINUE_DESTINATION))
                    .navigation(findNavController(), RouteConstant.PREREQUISITE_FRAGMENT, true)
            },300)
        }
    }
}