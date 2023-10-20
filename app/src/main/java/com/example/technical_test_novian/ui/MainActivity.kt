package com.example.technical_test_novian.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.technical_test_novian.R
import com.example.technical_test_novian.ui.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, HomeFragment())
            .commit()
    }
}