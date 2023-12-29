package com.example.namuthon.presentation.report

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.namuthon.R
import com.example.namuthon.coreui.base.BindingFragment
import com.example.namuthon.databinding.FragmentReport02Binding
import com.example.namuthon.presentation.MainActivity

class FragmentReport02 : BindingFragment<FragmentReport02Binding>(R.layout.fragment_report02) , MainActivity.onBackPressedListener{

    override fun onBackPressed() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }

    override fun initView() {
        val charter = arguments?.getString("charter")
        when(charter.toString()){
            "전세" -> {
                binding.tvReport02CharterTextview.setText("전세금")
                binding.tvReport02Title.setText("전세금을 입력해주세요")
                binding.clCharterNoneLayout.visibility = View.GONE
            }
        }

        // 보증금 , 전세금 증가
        binding.tvReport02100millon.setOnClickListener {
            if(binding.etReport02CharterEdittext.text.toString().equals("")){
                binding.etReport02CharterEdittext.setText("100000000")
            }
            else{
                binding.etReport02CharterEdittext.setText((100000000 + binding.etReport02CharterEdittext.text.toString().toInt()).toString())
            }
        }
        binding.tvReport0210millon.setOnClickListener {
            if(binding.etReport02CharterEdittext.text.toString().equals("")){
                binding.etReport02CharterEdittext.setText("10000000")
            }
            else{
                binding.etReport02CharterEdittext.setText((10000000 + binding.etReport02CharterEdittext.text.toString().toInt()).toString())
            }
        }
        binding.tvReport021millon.setOnClickListener {
            if(binding.etReport02CharterEdittext.text.toString().equals("")){
                binding.etReport02CharterEdittext.setText("1000000")
            }
            else{
                binding.etReport02CharterEdittext.setText((1000000 + binding.etReport02CharterEdittext.text.toString().toInt()).toString())
            }
        }

        // 월세 증가
        binding.tvReport0210won.setOnClickListener {
            if(binding.etReport02CharterNoneEdittext.text.toString().equals("")){
                binding.etReport02CharterNoneEdittext.setText("100000")
            }
            else{
                binding.etReport02CharterNoneEdittext.setText((100000 + binding.etReport02CharterNoneEdittext.text.toString().toInt()).toString())
            }
        }
        binding.tvReport0250won.setOnClickListener {
            if(binding.etReport02CharterNoneEdittext.text.toString().equals("")){
                binding.etReport02CharterNoneEdittext.setText("500000")
            }
            else{
                binding.etReport02CharterNoneEdittext.setText((500000 + binding.etReport02CharterNoneEdittext.text.toString().toInt()).toString())
            }
        }

        if (charter != null) {
            binding.tvReport02NextButton.setOnClickListener {
                send_data(charter, binding.etReport02CharterEdittext.text.toString(),  binding.etReport02CharterNoneEdittext.text.toString())
            }
        }


    }


    private fun send_data(charter: String, money_charter:String, money_none_charter:String){

        parentFragmentManager.beginTransaction().replace(
            R.id.container_main,
            FragmentReport03_01().apply {
                arguments = Bundle().apply {
                    putString("charter", charter)
                    putString("money_charter", money_charter)
                    putString("money_none_charter", money_none_charter)
                }
            }
        ).commit()

    }

}