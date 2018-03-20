package com.example.android.sd.CompressionFragments

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.sd.R
import com.example.android.sd.ViewModels.CompressionViewModel
import kotlinx.android.synthetic.main.c_fragment_one.view.*


/**
 * Created by Kundan on 19-03-2018.
 * This is the first fragment for the Design of a compression member
 */

public class CFragmentOne : Fragment() {

    private lateinit var mNextListener : OnNextClickListener
    private lateinit var mPreviousListener : OnPreviousClickListener
    private lateinit var mViewModel : CompressionViewModel

    companion object {
        fun newInstance(): CFragmentOne {
            return CFragmentOne()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val rootView = inflater!!.inflate(R.layout.c_fragment_one,container,false)
        rootView.C_One_FABNext.setOnClickListener {
            Log.i("FloatButton","Next Clicked")
        }
        rootView.C_One_FABPrevious.setOnClickListener {
            mPreviousListener.onPageOnePreviousClickListener()
        }

        rootView.radio_group_section_selector.setOnCheckedChangeListener { radioGroup, i ->
            rootView.page_one_FCD_TV.text = getTheSection(i)
        }
        mViewModel = CompressionViewModel()
        return rootView
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
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
                mViewModel.fcdValue = "135"
                mViewModel.selectedSection = getString(R.string.channel_section)
            }
            // Angle Section
            3 -> {
                fcdValue = "90 N/mm2"
                mViewModel.fcdValue = "135"
                mViewModel.selectedSection = getString(R.string.angle_section)
            }
            // Built up Section
            4 -> {
                fcdValue = "200 N/mm2"
                mViewModel.fcdValue = "135"
                mViewModel.selectedSection = getString(R.string.built_section)
            }
        }
        return "Assumed FCD value $fcdValue"
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