package com.example.namuthon.presentation.report

import android.os.Bundle
import com.example.namuthon.R
import com.example.namuthon.coreui.base.BindingFragment
import com.example.namuthon.databinding.FragmentReport0301Binding

class FragmentReport03_01 :
    BindingFragment<FragmentReport0301Binding>(R.layout.fragment_report03_01) {

    override fun initView() {
        val charter = arguments?.getString("charter")
        val money_charter = arguments?.getString("money_charter")
        val money_none_charter = arguments?.getString("money_none_charter")

        binding.rgReport0301Group.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.rb_report03_01_yet -> {
                    if (charter != null && money_none_charter != null && money_charter != null) {
                        send_data(charter, money_charter, money_none_charter, "계약 완료")
                    }
                }

                R.id.rb_report03_01_almost_done -> {
                    if (charter != null && money_none_charter != null && money_charter != null) {
                        send_data(charter, money_charter, money_none_charter, "계약 미완료")
                    }
                }
            }
        }



    }


    private fun send_data(charter: String, money_charter: String, money_none_charter: String, done: String) {
        binding.tvReport0301NextButton.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(
                R.id.container_main,
                FragmentReport04().apply {
                    arguments = Bundle().apply {
                        putString("charter", charter)
                        putString("money_charter", money_charter)
                        putString("money_none_charter", money_none_charter)
                        putString("done", done)
                    }
                }
            ).commit()
        }
    }
}