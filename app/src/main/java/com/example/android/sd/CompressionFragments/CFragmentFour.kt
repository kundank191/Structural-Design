package com.example.android.sd.CompressionFragments

import android.app.Activity
import android.app.Fragment
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.sd.R
import com.example.android.sd.ViewModels.CompressionViewModel
import kotlinx.android.synthetic.main.c_fragment_four.view.*
import utils.FunctionKit
import utils.Variables


/**
 * Created by Kundan on 22-03-2018.
 * the final Fragment for designing a compression member
 */
class CFragmentFour : Fragment() {

    private lateinit var mViewModel : CompressionViewModel
    private lateinit var mNextListener : OnNextClickListener
    private lateinit var mPreviousListener : OnPreviousClickListener
    private var mRevisiting : Boolean = false

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        mViewModel = ViewModelProviders.of(activity as FragmentActivity).get(CompressionViewModel::class.java)
        try {
            mNextListener = activity as OnNextClickListener
        } catch (e : ClassCastException){
            throw ClassCastException(activity.toString() + "Must Implement on Next Click Listener")
        }

        try {
            mPreviousListener = activity as OnPreviousClickListener
        } catch (e : ClassCastException){
            throw ClassCastException(activity.toString() + "Must Implement on Previous Click Listener")
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mViewModel = ViewModelProviders.of(context as AppCompatActivity).get(CompressionViewModel::class.java)
        try {
            mNextListener = context as OnNextClickListener
        } catch (e : Exception){
            throw ClassCastException(context.toString() + "Must Implement on Next Click Listener")
        }

        try {
            mPreviousListener = context as OnPreviousClickListener
        } catch (e : ClassCastException){
            throw ClassCastException(context.toString() + "Must Implement on Previous Click Listener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(arguments != null && arguments!!.containsKey(Variables.pageOneRevisiting)){
            mRevisiting = true
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val rootView : View = inflater!!.inflate(R.layout.c_fragment_four,container,false)
        rootView.c_four_back.setOnClickListener {
            mPreviousListener.onPageFourPreviousClickListener()
        }
        rootView.c_four_complete.setOnClickListener {
            mNextListener.onPageFourCompleteClickListener()
        }
        val fcd : String = FunctionKit.getTwoDecimalValue(mViewModel.fcdValue) + Variables.unitKN
        rootView.C_four_fcd.text = fcd
        val load : String = FunctionKit.getTwoDecimalValue(mViewModel.factoredLoad) + Variables.unitKN
        rootView.C_four_FactoredLoad.text = load
        val area =FunctionKit.getTwoDecimalValue(mViewModel.areaRequired) + Variables.unitMM2
        rootView.C_four_section_area.text = area
        val str : String = FunctionKit.getTwoDecimalValue(mViewModel.strengthSection) + Variables.unitKN
        rootView.C_section_strength.text = str
        rootView.C_four_the_section.text = mViewModel.selectedSection
        rootView.C_four_section_l.text = mViewModel.section_l
        rootView.C_four_section_h.text = mViewModel.section_h
        rootView.C_four_section_t.text = mViewModel.section_t
        return rootView
    }

    public interface OnNextClickListener{
        fun onPageFourCompleteClickListener()
    }

    public interface OnPreviousClickListener{
        fun onPageFourPreviousClickListener()
    }
}