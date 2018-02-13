package com.example.android.sd;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.android.sd.BoltFragments.BoltPageOne;

public class BoltActivity extends AppCompatActivity implements BoltPageOne.onFABNextClickListener,
                                                                BoltPageOne.onFABPreviousClickListener{


    private String Service_Load;
    private String Factored_Load;
    private String Bolt_value;
    private String No_Of_Bolts;
    private String Grade_Of_Bolts;
    private String Dia_Of_Bolts;
    private String End_Distance;
    private String Pitch_Distance;
    private String Bolt_Strength;
    private String Area_Anc;
    private String Area_Ago;
    private String Section_long_side;
    private String Section_short_side;
    private String Section_thickness;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bolt);

        if(savedInstanceState == null) {
            BoltPageOne fragment = new BoltPageOne();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.Activity_container, fragment)
                    .commit();
        }
    }

    private void setupPageOne(){

    }
    private void setupPageTwo(){

    }

    private Snackbar getSnackBar(CoordinatorLayout mCoordinateLayout){
        Snackbar snackbar = Snackbar.make(mCoordinateLayout, R.string.enter_service_load, Snackbar.LENGTH_SHORT);
        snackbar.getView().setBackgroundColor(Color.WHITE);
        TextView textView = (snackbar.getView()).findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.argb(255, 3, 169, 244));
        return snackbar;
    }

    @Override
    public void onBackPressed() {
        Log.i(getLocalClassName(),"Back Pressed");
    }

    @Override
    public void onPageOneNextClicked(String boltGrade, String boltDia) {
        Log.i(getLocalClassName(),"Param ! : " + boltGrade + "Param @ : " + boltDia);
    }

    @Override
    public void onPageOnePreviousClicked() {
        Log.i(getLocalClassName(),"Finishing ");
        finish();
    }
}
