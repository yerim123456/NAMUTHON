package com.example.namuthon.presentation.home

import android.content.Intent
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.namuthon.R
import com.example.namuthon.coreui.base.BindingFragment
import com.example.namuthon.databinding.FragmentHomeBinding
import com.example.namuthon.presentation.ocr.OcrActivity
import com.example.namuthon.presentation.report.FragmentReport01

class FragmentHome : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override fun initView() {

        binding.ivHomeReportAll.setOnClickListener {
            val intent = Intent(context, OcrActivity::class.java)
            startActivity(intent)
        }
        binding.ivHomeReport.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.container_main, FragmentReport01())
                .commit()

        }
        binding.tvHomeSave.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container_main, FragmentSaveList())
                .commit()
        }
    }
}