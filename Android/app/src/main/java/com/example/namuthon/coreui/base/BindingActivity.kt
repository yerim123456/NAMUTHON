package com.example.namuthon.coreui.base

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.internal.ViewUtils.hideKeyboard


abstract class BindingActivity<T : ViewDataBinding>(
    @LayoutRes private val layoutRes: Int,
) : AppCompatActivity() {
    protected lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.lifecycleOwner = this
        initView()
    }

    protected abstract fun initView()

}