package com.example.android.sd.ViewModels

import android.arch.lifecycle.ViewModel


/**
 * Created by Kundan on 20-03-2018.
 * View Model which will store all the data for the compression Activity
 */
public class CompressionViewModel : ViewModel() {

    val defaultValue: String = "Default Value"
    // Stores the service load passed by the main activity
    var serviceLoad : String = defaultValue
    var factoredLoad : String = defaultValue
    var fcdValue : String = defaultValue
    var selectedSection: String = defaultValue
    var selectedSectionIndex : String = defaultValue
    var areaRequired: String = defaultValue
    var section_l : String = defaultValue
    var section_h : String = defaultValue
    var section_t : String = defaultValue
    var section_a : String = defaultValue
    var section_b : String = defaultValue
    var section_c : String = defaultValue
    var section_area : String = defaultValue
    var section_rvv : String = defaultValue
    var section_cg_distance : String = defaultValue
}