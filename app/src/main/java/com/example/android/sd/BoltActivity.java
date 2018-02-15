package com.example.android.sd;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.sd.BoltFragments.BoltPageOne;
import com.example.android.sd.BoltFragments.BoltPageTwo;

import utils.Compute;
import utils.Variables;

import static utils.FunctionKit.getFloatOf;

public class BoltActivity extends AppCompatActivity implements BoltPageOne.onFABNextClickListener,
                                                                BoltPageOne.onFABPreviousClickListener,
                                                                BoltPageTwo.onFABNextClickListener,
                                                                BoltPageTwo.onFABPreviousClickListener{


    private String Service_Load = Variables.defaultValue;
    private String Factored_Load = Variables.defaultValue;
    private String Bolt_value = Variables.defaultValue;
    private String No_Of_Bolts = Variables.defaultValue;
    private String Grade_Of_Bolts = Variables.defaultValue;
    private String Dia_Of_Bolts = Variables.defaultValue;
    private String End_Distance = Variables.defaultValue;
    private String Pitch_Distance = Variables.defaultValue;
    private String ConnectionLength_Lc = Variables.defaultValue;
    private String Bolt_Strength = Variables.defaultValue;
    private String Area_An = Variables.defaultValue;
    private String Area_Ag = Variables.defaultValue;
    private String Area_Anc = Variables.defaultValue;
    private String Area_Ago = Variables.defaultValue;
    private String Section_l = Variables.defaultValue;
    private String Section_h = Variables.defaultValue;
    private String Section_t = Variables.defaultValue;
    private String Section_a = Variables.defaultValue;
    private String Section_b = Variables.defaultValue;
    private String Section_c = Variables.defaultValue;
    private String Section_MI = Variables.defaultValue;
    private String SR = Variables.defaultValue;
    private String Ultimate_Load_fu = "400";
    private String Number_Of_Shear_Planes_n = "1";
    private String Factor_Of_Safety_Ymb = "1.25";

    FragmentManager mFragmentManager;
    private BoltPageOne boltPageOneFragment = null;
    private BoltPageTwo boltPageTwoFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bolt);
        updateValuesOnLaunch();
        mFragmentManager = getSupportFragmentManager();
        if(savedInstanceState == null) {
            if(boltPageOneFragment == null){
                boltPageOneFragment = new BoltPageOne();
            }
            mFragmentManager.beginTransaction()
                    .add(R.id.Activity_container, boltPageOneFragment)
                    .commit();
        }
    }

    /**
     * Stores the values that has been passed through the intent
     */
    private void updateValuesOnLaunch(){
        Service_Load = getIntent().getStringExtra(Variables.serviceLoad);
        Factored_Load = String.valueOf(getFloatOf(Service_Load)*1.5);
    }
    private void updateValuesAfterPageOne(String boltGrade, String boltDia){

        Grade_Of_Bolts = boltGrade;
        Dia_Of_Bolts = boltDia;
        Pitch_Distance = String.valueOf(getFloatOf(boltDia)*1.5);
        End_Distance = String.valueOf(getFloatOf(boltDia)*2.5);
        Ultimate_Load_fu = String.valueOf(getFloatOf(Grade_Of_Bolts.substring(0,1))*100);
        Bolt_value = Compute.BoltValue(Dia_Of_Bolts,End_Distance,Pitch_Distance,Factor_Of_Safety_Ymb,
                                        Number_Of_Shear_Planes_n,Ultimate_Load_fu,"400","10");
        Area_An = Compute.AreaAn(Factored_Load,Ultimate_Load_fu);
        Area_Ag = String.valueOf(getFloatOf(Area_An)*1.2);
        No_Of_Bolts = Compute.NumberOfBolts(Factored_Load,Bolt_value);
        Bolt_Strength = String.valueOf(getFloatOf(No_Of_Bolts)*getFloatOf(Bolt_value));

    }
    private void updateValuesAfterPageTwo(Bundle dataBundle){

    }

    /**
     *
     * @return a bundle for all the values required for page two
     */
    private Bundle getBundleForPageTwo(){
        Bundle bundle = new Bundle();
        bundle.putString(Variables.gradeOfBolt,Grade_Of_Bolts);
        bundle.putString(Variables.diaOfBolt,Dia_Of_Bolts);
        bundle.putString(Variables.valueBolt,Bolt_value);
        bundle.putString(Variables.numberBolt,No_Of_Bolts);
        bundle.putString(Variables.strengthBolt,Bolt_Strength);
        bundle.putString(Variables.pitch,Pitch_Distance);
        bundle.putString(Variables.endDistance,End_Distance);
        bundle.putString(Variables.Anc, Area_An);
        bundle.putString(Variables.Ago, Area_Ag);
        return bundle;
    }


    @Override
    public void onBackPressed() {
        Log.i(getLocalClassName(),"Back Pressed");
    }

    @Override
    public void onPageOneNextClicked(String boltGrade, String boltDia) {
        updateValuesAfterPageOne(boltGrade,boltDia);
        if(boltPageTwoFragment == null) {
            boltPageTwoFragment = new BoltPageTwo();
            boltPageTwoFragment.setArguments(getBundleForPageTwo());
        }
        mFragmentManager.beginTransaction()
                .replace(R.id.Activity_container,boltPageTwoFragment)
                .commit();
    }
    @Override
    public void onPageOnePreviousClicked() {
        Log.i(getLocalClassName(),"Finishing ");
        finish();
    }

    @Override
    public void onPageTwoNextClicked(Bundle dataBundle) {

    }

    @Override
    public void onPageTwoPreviousClicked() {
        Bundle bundle = new Bundle();
        bundle.putString(Variables.gradeOfBolt,Grade_Of_Bolts);
        bundle.putString(Variables.diaOfBolt,Dia_Of_Bolts);

//        if (boltPageOneFragment == null){
            boltPageOneFragment = new BoltPageOne();
//        }

        boltPageOneFragment.setArguments(bundle);
        mFragmentManager.beginTransaction()
                .replace(R.id.Activity_container,boltPageOneFragment)
                .commit();
    }
}
