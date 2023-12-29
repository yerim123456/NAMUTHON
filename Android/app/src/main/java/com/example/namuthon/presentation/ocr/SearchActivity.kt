package com.example.namuthon.presentation.ocr

import android.os.Bundle
import com.example.namuthon.R
import com.example.namuthon.coreui.base.BindingActivity
import com.example.namuthon.databinding.ActivitySearchBinding
import java.util.ArrayList


class SearchActivity : BindingActivity<ActivitySearchBinding>(R.layout.activity_search) {

    var ocrResult : String? = null

    // ArrayList 생성
    var stringList : ArrayList<String> = arrayListOf(
        "임차인부담","임대인부담","인도기한", "임대인특약", "임차인권리"
    )

    var keywordList : ArrayList<SearchDto> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        // ocr 정보 받아오기
        ocrResult = intent.getStringExtra("ocrResult") as String

        for (i in 0..keywordList.size){
            search(stringList[i])
        }

    }

    // 파이어스토어에서 데이터를 불러와서 검색어가 있는지 판단
    fun search(searchWord: String) {
        val adapter = KeywordAdapter()

        if(!ocrResult!!.contains(searchWord))
        {
            binding.rvKeyword.adapter = adapter
            keywordList.add(SearchDto(searchWord))
            adapter.submitList(keywordList)
        }
    }
}