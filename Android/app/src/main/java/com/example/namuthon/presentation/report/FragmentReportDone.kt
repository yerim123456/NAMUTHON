package com.example.namuthon.presentation.report

import android.content.Intent
import android.net.Uri
import com.example.namuthon.R
import com.example.namuthon.coreui.base.BindingFragment
import com.example.namuthon.databinding.FragmentReportDoneBinding
import com.example.namuthon.databinding.FragmentReportProcessBinding

class FragmentReportDone :
    BindingFragment<FragmentReportDoneBinding>(R.layout.fragment_report_done) {
    override fun initView() {
        val charter = arguments?.getString("charter")
        val money_charter = arguments?.getString("money_charter")
        val money_none_charter = arguments?.getString("money_none_charter")
        val done = arguments?.getString("done")
        val address = arguments?.getString("address")

        binding.tvReportDoneDone.setText(done)
        binding.tvReportDoneCase.setText(charter)
        binding.tvReportDoneAddress.setText(address)

        if(money_none_charter.isNullOrEmpty()){
            binding.tvReportDoneRateMine.setText(money_charter.toString())
        }
        else{
            binding.tvReportDoneRateMine.setText(money_charter+ "/" + money_none_charter)
        }

        binding.tvReportDoneCheckNow.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://rt.molit.go.kr/"))
            startActivity(intent)
        }
        binding.tvReportDoneBillCheck.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(" https://hometax.go.kr/websquare/websquare.wq?w2xPath=/ui/pp/index_pp.xml"))
            startActivity(intent)

        }
        binding.tvReportDonePlaceCheck.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.nsdi.go.kr/lxportal/?menuno=4085"))
            startActivity(intent)

        }
        binding.tvReportDoneInsuranceCheck.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.khug.or.kr/hug/web/ig/dr/igdr000001.jsp?tabMenu=Y"))
            startActivity(intent)

        }

    }
}