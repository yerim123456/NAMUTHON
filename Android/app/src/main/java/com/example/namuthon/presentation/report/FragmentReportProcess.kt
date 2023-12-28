package com.example.namuthon.presentation.report

import android.os.Bundle
import android.os.Handler
import com.example.namuthon.R
import com.example.namuthon.coreui.base.BindingFragment
import com.example.namuthon.databinding.FragmentReport04Binding
import com.example.namuthon.databinding.FragmentReportProcessBinding

class FragmentReportProcess :
    BindingFragment<FragmentReportProcessBinding>(R.layout.fragment_report_process) {
    override fun initView() {
        val charter = arguments?.getString("charter")
        val money_charter = arguments?.getString("money_charter")
        val money_none_charter = arguments?.getString("money_none_charter")
        val done = arguments?.getString("done")
        val address = arguments?.getString("address")


        // 3초 후 화면 이동
        val handler:Handler = Handler()
        handler.postDelayed({
            parentFragmentManager.beginTransaction().replace(
                R.id.container_main,
                FragmentReportDone().apply {
                    arguments = Bundle().apply {
                        putString("charter", charter)
                        putString("money_charter", money_charter)
                        putString("money_none_charter", money_none_charter)
                        putString("done", done)
                        putString("address", address)
                    }
                }
            ).commit()
        },1500)
    }

    // api 작업 진행
}