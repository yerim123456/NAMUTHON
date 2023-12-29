package com.example.namuthon.presentation.ocr

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.namuthon.R

class KeywordAdapter :
    ListAdapter<SearchDto, KeywordAdapter.IntroductionViewHolder>(IntroductionDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroductionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_keyword, parent, false)
        return IntroductionViewHolder(view)
    }

    override fun onBindViewHolder(holder: IntroductionViewHolder, position: Int) {
        holder.bind(getItem(position)) //position에 해당하는 data type을 전달
    }

    class IntroductionViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val Keyword = view.findViewById<TextView>(R.id.tv_keyword)
        fun bind(word: SearchDto) {
            Keyword.text = word.keyword
        }
    }
}

class IntroductionDiffCallback : DiffUtil.ItemCallback<SearchDto>() {
    override fun areItemsTheSame(oldItem: SearchDto, newItem: SearchDto): Boolean {
        return oldItem.keyword == newItem.keyword
    }

    override fun areContentsTheSame(oldItem: SearchDto, newItem: SearchDto): Boolean {
        return oldItem == newItem
    }
}