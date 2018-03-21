package com.example.android.sd

import android.app.Fragment
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.android.sd.CompressionFragments.CFragmentOne
import com.example.android.sd.CompressionFragments.CFragmentTwo
import com.example.android.sd.ViewModels.CompressionViewModel
import utils.Compute
import utils.Variables

class CompressionActivity : AppCompatActivity() , CFragmentOne.OnNextClickListener
                                                , CFragmentOne.OnPreviousClickListener
                                                , CFragmentTwo.OnNextClickListener
                                                , CFragmentTwo.OnPreviousClickListener{

    lateinit var mViewModel : CompressionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compression)
        mViewModel = ViewModelProviders.of(this).get(CompressionViewModel::class.java)

        val serviceLoad = intent.getStringExtra(Variables.serviceLoad)
        mViewModel.serviceLoad = serviceLoad
        mViewModel.factoredLoad = Compute.FactoredLoad(mViewModel.serviceLoad)
        if(savedInstanceState == null){
            val ft = fragmentManager.beginTransaction()
            val fragment= CFragmentOne.newInstance()
            ft.add(R.id.frame_layout,fragment as Fragment).commit()
        }
    }

    /**
     * Revisits the first fragment
     */
    private fun addSecondFragment() {
        fragmentManager.beginTransaction()
                .replace(R.id.frame_layout, CFragmentTwo() as Fragment)
                .commit()
    }

    /**
     * Replaces the first fragment with the second one
     */
    private fun revisitFirstFragment() {
        val bundle : Bundle = Bundle()
        bundle.putBoolean(Variables.pageOneRevisiting,true)
        val fragment : CFragmentOne = CFragmentOne()
        fragment.arguments = bundle
        fragmentManager.beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit()
    }

    override fun onPageOneNextClickListener() {
        addSecondFragment()
    }

    override fun onPageOnePreviousClickListener() {
        finish()
    }

    override fun onPageTwoNextClickListener() {
        Log.i("Hello","Hello")
    }

    override fun onPageTwoPreviousClickListener() {
        revisitFirstFragment()
    }
}
