package com.cfxc.module_one

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cfxc.common.constants.BundleKeyConstant
import com.cfxc.common.constants.IUserDataProvider
import com.cfxc.common.constants.RequestKeyConstant
import com.cfxc.common.constants.RouteConstant
import com.example.module_one.R
import com.cfxc.router.annotation.Route
import com.cfxc.router.core.template.Router

/**
 * @description
 * @author: created by peihao.feng
 * @date: 4/8/21
 */
@Route(destinationText = RouteConstant.DDA_FRAGMENT)
class DDAFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_module_one_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btn_goto_next).setOnClickListener {
            Router.getInstance().build(RouteConstant.SCC_FRAGMENT)
                .with(bundleOf(BundleKeyConstant.KEY_CONTENT to "feature1 dda says 'Hello'"))
                .navigation(findNavController())
        }

        parentFragmentManager.setFragmentResultListener(
            RequestKeyConstant.KEY_FIRST_CONTACT_SECOND,
            viewLifecycleOwner
        ) { requestKey, result ->
            if (requestKey == RequestKeyConstant.KEY_FIRST_CONTACT_SECOND) {
                view.findViewById<TextView>(R.id.tv_received).text =
                    result.getString(BundleKeyConstant.KEY_CONTENT)
            }
        }

        view.findViewById<Button>(R.id.btn_get_app_module_data).setOnClickListener {
            val userDataProvider = Router.getInstance().build(RouteConstant.USER_DATA_PROVIDER)
                .navigation() as IUserDataProvider
            Toast.makeText(requireContext(), userDataProvider.getUserName(), Toast.LENGTH_SHORT)
                .show()
        }

        view.findViewById<Button>(R.id.btn_goto_second).setOnClickListener {
            Router.getInstance().build(RouteConstant.TRANSACTION_FRAGMENT)
                .navigation(findNavController())
        }
    }
}