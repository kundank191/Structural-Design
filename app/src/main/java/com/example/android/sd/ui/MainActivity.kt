package com.example.android.sd.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.android.sd.R
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
        initializeRecyclerView()
    }

    private fun initializeRecyclerView() {
        val designTypeList = mutableListOf<DesignType>()
        designTypeList.add(DesignType(R.mipmap.ic_launcher, getString(R.string.steel_design_text)))
        designTypeList.add(DesignType(R.mipmap.ic_launcher, getString(R.string.rcc_design_text)))

        design_type_rv.layoutManager = LinearLayoutManager(this)
        design_type_rv.adapter = DesignTypeAdapter(designTypeList, this)
    }

    class DesignType(var imageID: Int = R.mipmap.ic_launcher, var designType: String = "Design Type")
}