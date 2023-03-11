package com.cfxc.module_one

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.cfxc.common.constants.RouteConstant
import com.cfxc.router.annotation.Route
import com.example.module_one.R
import kotlinx.android.synthetic.main.fragment_module_one_second.*

/**
 * @description
 * @author: created by peihao.feng
 * @date: 4/27/21
 */
@Route(destinationText = RouteConstant.TRANSACTION_FRAGMENT)
class TransactionFragment : Fragment(R.layout.fragment_module_one_second) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            tv_received.text = it.toString()
        }
    }
}