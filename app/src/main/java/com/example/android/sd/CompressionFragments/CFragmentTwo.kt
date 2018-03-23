package com.example.android.sd.CompressionFragments

import android.app.Activity
import android.app.Fragment
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.sd.R
import com.example.android.sd.ViewModels.CompressionViewModel
import kotlinx.android.synthetic.main.c_fragment_two.*
import kotlinx.android.synthetic.main.c_fragment_two.view.*
import utils.FunctionKit
import utils.Variables


/**
 * Created by Kundan on 21-03-2018.
 * second fragment of the compression activity
 */

public class CFragmentTwo : Fragment() {

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
        if(arguments != null && arguments.containsKey(Variables.pageOneRevisiting)){
            mRevisiting = true
        }
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val rootView : View = inflater!!.inflate(R.layout.c_fragment_two,container,false)

        val area = getString(R.string.required_area) + " " + FunctionKit.getTwoDecimalValue(mViewModel.areaRequired) + Variables.unitMM2
        rootView.page_two_area_required.text = area

        if(mRevisiting){
            rootView.section_l.setText(mViewModel.section_l)
            rootView.section_h.setText(mViewModel.section_h)
            rootView.section_t.setText(mViewModel.section_t)
            rootView.section_area.setText(mViewModel.section_area)
            rootView.section_rvv.setText(mViewModel.section_rvv)
            rootView.section_cg_distance.setText(mViewModel.section_cg_distance)
            rootView.section_effective_length.setText(mViewModel.effectiveLength)
        }
        rootView.C_two_FABNext.setOnClickListener {
            if(!TextUtils.isEmpty(rootView.section_l.text)){
                if(!TextUtils.isEmpty(rootView.section_h.text)){
                    if(!TextUtils.isEmpty(rootView.section_t.text)){
                        if(!TextUtils.isEmpty(rootView.section_area.text)){
                            if(!TextUtils.isEmpty(rootView.section_rvv.text)){
                                if(!TextUtils.isEmpty(rootView.section_cg_distance.text)){
                                    if(!TextUtils.isEmpty(rootView.section_effective_length.text)) {
                                        mViewModel.section_l = rootView.section_l.text.toString()
                                        mViewModel.section_h = rootView.section_h.text.toString()
                                        mViewModel.section_t = rootView.section_t.text.toString()
                                        mViewModel.section_area = rootView.section_area.text.toString()
                                        mViewModel.section_rvv = rootView.section_rvv.text.toString()
                                        mViewModel.section_cg_distance = rootView.section_cg_distance.text.toString()
                                        mViewModel.effectiveLength = rootView.section_effective_length.text.toString()
                                        mNextListener.onPageTwoNextClickListener()
                                    } else {
                                        FunctionKit.getSnackBar(second_page_coordinator_layout as CoordinatorLayout
                                                ,R.string.enter_effective_length).show()
                                    }
                                } else {
                                    FunctionKit.getSnackBar(second_page_coordinator_layout as CoordinatorLayout
                                            ,R.string.enter_section_CG).show()
                                }
                            } else {
                                FunctionKit.getSnackBar(second_page_coordinator_layout as CoordinatorLayout
                                        ,R.string.enter_section_rvv).show()
                            }
                        } else {
                            FunctionKit.getSnackBar(second_page_coordinator_layout as CoordinatorLayout
                                    ,R.string.enter_section_csa).show()
                        }
                    } else {
                        FunctionKit.getSnackBar(second_page_coordinator_layout as CoordinatorLayout
                                ,R.string.enter_section_thickness).show()
                    }
                } else {
                    FunctionKit.getSnackBar(second_page_coordinator_layout as CoordinatorLayout
                            ,R.string.enter_section_breadth).show()
                }
            } else {
                FunctionKit.getSnackBar(second_page_coordinator_layout as CoordinatorLayout
                        ,R.string.enter_section_length).show()
            }
        }
        rootView.C_two_FABPrevious.setOnClickListener {
            mPreviousListener.onPageTwoPreviousClickListener()
        }
        return rootView
    }

    public interface OnNextClickListener{
        fun onPageTwoNextClickListener()
    }

    public interface OnPreviousClickListener{
        fun onPageTwoPreviousClickListener()
    }

}
