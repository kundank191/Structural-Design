package com.example.android.sd

import android.app.Fragment
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.android.sd.CompressionFragments.CFragmentFour
import com.example.android.sd.CompressionFragments.CFragmentOne
import com.example.android.sd.CompressionFragments.CFragmentThree
import com.example.android.sd.CompressionFragments.CFragmentTwo
import com.example.android.sd.ViewModels.CompressionViewModel
import utils.Compute
import utils.Variables

class CompressionActivity : AppCompatActivity() , CFragmentOne.OnNextClickListener
                                                , CFragmentOne.OnPreviousClickListener
                                                , CFragmentTwo.OnNextClickListener
                                                , CFragmentTwo.OnPreviousClickListener
                                                , CFragmentThree.OnNextClickListener
                                                , CFragmentThree.OnPreviousClickListener
                                                , CFragmentFour.OnNextClickListener
                                                , CFragmentFour.OnPreviousClickListener{

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

    /**
     * Revisits the first fragment
     */
    private fun addSecondFragment() {
        fragmentManager.beginTransaction()
                .replace(R.id.frame_layout, CFragmentTwo() as Fragment)
                .commit()
    }

    /**
     * Preparing second fragment with default values
     */
    private fun revisitSecondFragment(){
        val bundle : Bundle = Bundle()
        bundle.putBoolean(Variables.pageOneRevisiting,true)
        val fragment : CFragmentTwo = CFragmentTwo()
        fragment.arguments = bundle
        fragmentManager.beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit()
    }

    /**
     * Goto third fragment
     */
    private fun addThirdFragment() {
        fragmentManager.beginTransaction()
                .replace(R.id.frame_layout, CFragmentThree.newInstance())
                .commit()
    }

    /**
     * Preparing Third fragment with default values
     */
    private fun revisitThirdFragment(){
        val bundle : Bundle = Bundle()
        bundle.putBoolean(Variables.pageOneRevisiting,true)
        val fragment : CFragmentThree = CFragmentThree()
        fragment.arguments = bundle
        fragmentManager.beginTransaction()
                .replace(R.id.frame_layout, fragment as Fragment)
                .commit()
    }

    /**
     * Goto fourth fragment
     */
    private fun addFourthFragment() {
        fragmentManager.beginTransaction()
                .replace(R.id.frame_layout, CFragmentFour() as Fragment)
                .commit()
    }

    override fun onPageOneNextClickListener() {
        addSecondFragment()
    }

    override fun onPageOnePreviousClickListener() {
        finish()
    }

    override fun onPageTwoNextClickListener() {
        addThirdFragment()
    }

    override fun onPageTwoPreviousClickListener() {
        revisitFirstFragment()
    }

    override fun onPageThreeNextClickListener() {
        mViewModel.fcdValue = Compute.FcdValue(mViewModel.lower_SR,mViewModel.upper_SR,mViewModel.section_SR
                                                , mViewModel.lowerFcdValue , mViewModel.upperFcdValue)
        mViewModel.strengthSection = Compute.SectionStrength(mViewModel.fcdValue,mViewModel.section_area)
        addFourthFragment()
    }

    override fun onPageThreePreviousClickListener() {
        revisitSecondFragment()
    }

    override fun onPageFourCompleteClickListener() {
        finish()
    }

    override fun onPageFourPreviousClickListener() {
        revisitThirdFragment()
    }
}
