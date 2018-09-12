package com.example.android.sd.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.android.sd.R
import com.example.android.sd.ui.steel.SteelDesignActivity
import kotlinx.android.synthetic.main.activity_main.*


/**
 * Created by Kundan on 12-09-2018.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        //currently it will only open Steel Design Activity
        button_rcc_design.setOnClickListener {
            startActivity(Intent(this,SteelDesignActivity::class.java))
        }

        button_steel_design.setOnClickListener {
            startActivity(Intent(this,SteelDesignActivity::class.java))
        }
    }
}