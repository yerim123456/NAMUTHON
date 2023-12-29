package com.example.namuthon.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.namuthon.R
import com.example.namuthon.coreui.base.BindingActivity
import com.example.namuthon.databinding.ActivityMainBinding

class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun initView() {
        val navController =
            supportFragmentManager.findFragmentById(R.id.container_main)?.findNavController()

        with(binding) {
            navigationMain.itemIconTintList = null
            navController?.let {
                navigationMain.setupWithNavController(it)
            }
        }
    }
    interface onBackPressedListener {
        fun onBackPressed()
    }

    override fun onBackPressed(){
        val fragmentList = supportFragmentManager.fragments
        for (fragment in fragmentList) {
            if (fragment is onBackPressedListener) {
                (fragment as onBackPressedListener).onBackPressed()
                return
            }
        }
    }

}