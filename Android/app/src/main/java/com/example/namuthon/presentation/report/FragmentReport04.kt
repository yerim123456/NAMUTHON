package com.example.namuthon.presentation.report

import android.os.Bundle
import com.example.namuthon.R
import com.example.namuthon.coreui.base.BindingFragment
import com.example.namuthon.databinding.FragmentReport04Binding
import com.example.namuthon.presentation.MainActivity

class FragmentReport04 :
    BindingFragment<FragmentReport04Binding>(R.layout.fragment_report04){

    override fun initView() {
        val charter = arguments?.getString("charter")
        val money_charter = arguments?.getString("money_charter")
        val money_none_charter = arguments?.getString("money_none_charter")
        val done = arguments?.getString("done")

        binding.tvReport04NextButton.setOnClickListener {


            parentFragmentManager.beginTransaction().replace(
                R.id.container_main,
                FragmentReportProcess().apply {
                    arguments = Bundle().apply {
                        putString("charter", charter)
                        putString("money_charter", money_charter)
                        putString("money_none_charter", money_none_charter)
                        putString("done", done)
                        putString("address", binding.etReport04AdressEdittext.text.toString())
                    }
                }
            ).commit()

        }

    }
}