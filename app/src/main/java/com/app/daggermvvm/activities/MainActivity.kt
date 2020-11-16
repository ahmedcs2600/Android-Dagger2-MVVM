package com.app.daggermvvm.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.app.daggermvvm.R
import com.app.daggermvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val resId: Int
        get() = R.id.nav_host_fragment

    private lateinit var binding: ActivityMainBinding

    private val navController
        get() = findNavController(resId)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
    }
}