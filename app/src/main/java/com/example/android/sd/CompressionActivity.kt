package com.example.android.sd

import android.app.Fragment
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.android.sd.CompressionFragments.CFragmentOne
import com.example.android.sd.ViewModels.CompressionViewModel
import utils.Variables

class CompressionActivity : AppCompatActivity() , CFragmentOne.OnNextClickListener
                                                , CFragmentOne.OnPreviousClickListener{

    lateinit var mViewModel : CompressionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compression)

        var serviceLoad = intent.getStringExtra(Variables.serviceLoad)

        if(savedInstanceState == null){
            val ft = fragmentManager.beginTransaction()
            val fragment= CFragmentOne.newInstance()
            ft.add(R.id.frame_layout,fragment as Fragment).commit()
        }

    }

    override fun onPageOneNextClickListener() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPageOnePreviousClickListener() {
        finish() //To change body of created functions use File | Settings | File Templates.
    }
}
