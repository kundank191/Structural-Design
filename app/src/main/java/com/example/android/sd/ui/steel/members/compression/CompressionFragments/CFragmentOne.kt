package com.example.android.sd.ui.steel.members.compression.CompressionFragments

import android.app.Activity
import android.app.Fragment
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import com.example.android.sd.R
import com.example.android.sd.ui.steel.members.compression.CompressionViewModel
import kotlinx.android.synthetic.main.c_fragment_one.*
import kotlinx.android.synthetic.main.c_fragment_one.view.*
import com.example.android.sd.ui.steel.utils.Compute
import com.example.android.sd.ui.steel.utils.FunctionKit
import com.example.android.sd.ui.steel.utils.Variables


/**
 * Created by Kundan on 19-03-2018.
 * This is the first fragment for the Design of a compression member
 */

public class CFragmentOne : Fragment() {

    private lateinit var mNextListener : OnNextClickListener
    private lateinit var mPreviousListener : OnPreviousClickListener
    private lateinit var mViewModel : CompressionViewModel
    private var mRevisiting : Boolean = false

    companion object {
        fun newInstance(): CFragmentOne {
            return CFragmentOne()
        }
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        mViewModel = ViewModelProviders.of(activity as FragmentActivity).get(CompressionViewModel::class.java)
        try {
            mNextListener = activity as OnNextClickListener
        } catch (e : Throwable){
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
        mViewModel = ViewModelProviders.of(context as FragmentActivity).get(CompressionViewModel::class.java)
        try {
            mNextListener = context as OnNextClickListener
        } catch (e : Throwable){
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

        val rootView = inflater!!.inflate(R.layout.c_fragment_one,container,false)

        if(mRevisiting){
            var fcdValue  = "Assumed fcd value : " + mViewModel.fcdValue + Variables.unitKN
            rootView.page_one_FCD_TV.text = fcdValue
            var area = getString(R.string.required_area) + " " + FunctionKit.getTwoDecimalValue(mViewModel.areaRequired) + Variables.unitMM2
            rootView.page_one_area_required.text = area
            var radioButton : RadioButton = rootView.radio_group_section_selector
                                        .getChildAt(Integer.parseInt(mViewModel.selectedSectionIndex) - 1) as RadioButton
            radioButton.isChecked = true
        }
        rootView.C_One_FABNext.setOnClickListener {
            if(mViewModel.fcdValue != mViewModel.defaultValue){
                mNextListener.onPageOneNextClickListener()
            } else {
                FunctionKit.getSnackBar(C_One_CoordinateLayout as CoordinatorLayout,R.string.select_section_message).show()
            }
        }
        rootView.C_One_FABPrevious.setOnClickListener {
            mPreviousListener.onPageOnePreviousClickListener()
        }

        rootView.radio_group_section_selector.setOnCheckedChangeListener { radioGroup, i ->
            rootView.page_one_FCD_TV.text = getTheSection(radioGroup.indexOfChild(radioGroup.findViewById(radioGroup.checkedRadioButtonId)) + 1)
            mViewModel.selectedSectionIndex = (radioGroup.indexOfChild(radioGroup.findViewById(radioGroup.checkedRadioButtonId)) + 1).toString()
            mViewModel.areaRequired = Compute.AreaRequired(mViewModel.factoredLoad, mViewModel.fcdValue)
            var area = getString(R.string.required_area) + " " + FunctionKit.getTwoDecimalValue(mViewModel.areaRequired) + Variables.unitMM2
            rootView.page_one_area_required.text = area
        }
        return rootView
    }

    /**
     * takes the no of the radio button selected and returns fcd value corresponding to the section
     * also updates fcdValue and selected section in the viewModel
     */
    private fun getTheSection(i: Int) : String{
        var fcdValue = "90 N/mm2"
        when (i){
            // I section
            1 -> {
                fcdValue = "135 N/mm2"
                mViewModel.fcdValue = "135"
                mViewModel.selectedSection = getString(R.string.i_section)
            }
            // Channel Section
            2 -> {
                fcdValue = "60 N/mm2"
                mViewModel.fcdValue = "60"
                mViewModel.selectedSection = getString(R.string.channel_section)
            }
            // Angle Section
            3 -> {
                fcdValue = "90 N/mm2"
                mViewModel.fcdValue = "90"
                mViewModel.selectedSection = getString(R.string.angle_section)
            }
            // Built up Section
            4 -> {
                fcdValue = "200 N/mm2"
                mViewModel.fcdValue = "200"
                mViewModel.selectedSection = getString(R.string.built_section)
            }
        }
        return "Assumed FCD value : $fcdValue"
    }
    override fun onDetach() {
        super.onDetach()
    }

    public interface OnNextClickListener{
        fun onPageOneNextClickListener()
    }

    public interface OnPreviousClickListener{
        fun onPageOnePreviousClickListener()
    }
}