package com.example.android.sd.CompressionFragments

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.sd.R
import com.example.android.sd.ViewModels.CompressionViewModel
import kotlinx.android.synthetic.main.c_fragment_three.view.*
import utils.Compute
import utils.FunctionKit
import utils.Variables

/**
 * A simple [Fragment] subclass.
 */
class CFragmentThree : android.app.Fragment() {

    private lateinit var mViewModel : CompressionViewModel
    private lateinit var mNextListener : OnNextClickListener
    private lateinit var mPreviousListener : OnPreviousClickListener
    private var mRevisiting : Boolean = false

    companion object {
        fun newInstance(): CFragmentThree {
            return CFragmentThree()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mViewModel = ViewModelProviders.of(context as FragmentActivity).get(CompressionViewModel::class.java)
        try {
            mNextListener = context as OnNextClickListener
        } catch (e : ClassCastException){
            throw ClassCastException(context.toString() + "Must Implement on Next Click Listener")
        }

        try {
            mPreviousListener = context as OnPreviousClickListener
        } catch (e : ClassCastException){
            throw ClassCastException(context.toString() + "Must Implement on Previous Click Listener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if(arguments != null && arguments!!.containsKey(Variables.pageOneRevisiting)){
            mRevisiting = true
        }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView : View = inflater!!.inflate(R.layout.c_fragment_three,container,false)
        if(mRevisiting){
            rootView.fcd_1.setText(mViewModel.upperFcdValue)
            rootView.fcd_2.setText(mViewModel.lowerFcdValue)
        } else {
            doCalculations()
        }
        val text: String = getString(R.string.SlendernessRatio) + " : " + mViewModel.section_SR
        rootView.page_three_slenderness_ratio.text = text
        rootView.s_r_1.text = FunctionKit.getTwoDecimalValue(mViewModel.lower_SR)
        rootView.s_r_2.text = FunctionKit.getTwoDecimalValue(mViewModel.upper_SR)

        rootView.C_three_FABNext.setOnClickListener {
            if(!TextUtils.isEmpty(rootView.fcd_1.text)){
                if(!TextUtils.isEmpty(rootView.fcd_2.text)){
                    mViewModel.lowerFcdValue = rootView.fcd_1.text.toString()
                    mViewModel.upperFcdValue = rootView.fcd_2.text.toString()
                    mNextListener.onPageThreeNextClickListener()
                } else {
                    FunctionKit.getSnackBar(rootView.compression_page_three_coordinator, R.string.enter_fcd_2).show()
                }
            } else {
                FunctionKit.getSnackBar(rootView.compression_page_three_coordinator,R.string.enter_fcd_1).show()
            }
        }

        rootView.C_three_FABPrevious.setOnClickListener {
            mPreviousListener.onPageThreePreviousClickListener()
        }

        return rootView
    }

    private fun doCalculations() {
        mViewModel.section_SR = Compute.SlendernessRatio(mViewModel.effectiveLength, mViewModel.section_rvv)
        mViewModel.upper_SR = Compute.UpperSlendernessRatio(mViewModel.section_SR)
        mViewModel.lower_SR = Compute.LowerSlendernessRatio(mViewModel.section_SR)
    }

    public interface OnNextClickListener{
        fun onPageThreeNextClickListener()
    }

    public interface OnPreviousClickListener{
        fun onPageThreePreviousClickListener()
    }
}// Required empty public constructor
