package com.example.android.sd.ui.steel.members.compression

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
    var effectiveLength : String = defaultValue
    var fcdValue : String = defaultValue
    var lowerFcdValue : String = defaultValue
    var upperFcdValue : String = defaultValue
    var calculatedFcdValue : String = defaultValue
    var strengthSection : String = defaultValue
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
    var lower_SR : String = defaultValue
    var upper_SR : String = defaultValue
    var section_SR : String = defaultValue

}