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
    var fcdValue : String = defaultValue
    var selectedSection: String = defaultValue
}