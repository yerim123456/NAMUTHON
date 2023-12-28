package com.example.namuthon.presentation.report

import android.os.Bundle
import android.util.Log
import com.example.namuthon.R
import com.example.namuthon.coreui.base.BindingFragment
import com.example.namuthon.databinding.FragmentExampleBinding
import com.example.namuthon.databinding.FragmentReport01Binding

class FragmentReport01 : BindingFragment<FragmentReport01Binding>(R.layout.fragment_report01) {

    override fun initView() {
        binding.rgReport01Charter.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.rb_report01_charter_all -> {
                    send_data(binding.rbReport01CharterAll.text.toString())

                }

                R.id.rb_report01_charter_half -> {
                    send_data(binding.rbReport01CharterHalf.text.toString())
                }

                R.id.rb_report01_charter_none -> {
                    send_data(binding.rbReport01CharterNone.text.toString())

                }
            }
        }

    }


    private fun send_data(charter: String) {
        binding.tvReport01NextButton.setOnClickListener {


            parentFragmentManager.beginTransaction()
                .replace(R.id.container_main, FragmentReport02().apply {
                    arguments = Bundle().apply {
                        putString("charter", charter)
                    }
                }).commit()

        }
    }


}