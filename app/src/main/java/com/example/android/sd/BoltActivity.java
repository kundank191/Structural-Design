package com.example.android.sd;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.sd.BoltFragments.BoltPageOne;
import com.example.android.sd.BoltFragments.BoltPageTwo;

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

    FragmentManager mFragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bolt);
        updateValuesOnLaunch();
        mFragmentManager = getSupportFragmentManager();
        if(savedInstanceState == null) {
            BoltPageOne fragment = new BoltPageOne();
            mFragmentManager.beginTransaction()
                    .add(R.id.Activity_container, fragment)
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

    }
    private void setupPageTwo(){

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
        bundle.putString(Variables.Anc,Area_Anc);
        bundle.putString(Variables.Ago,Area_Ago);
        return bundle;
    }


    @Override
    public void onBackPressed() {
        Log.i(getLocalClassName(),"Back Pressed");
    }

    @Override
    public void onPageOneNextClicked(String boltGrade, String boltDia) {
        BoltPageTwo fragment = new BoltPageTwo();
        fragment.setArguments(getBundleForPageTwo());
        mFragmentManager.beginTransaction()
                .replace(R.id.Activity_container,fragment)
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

    }
}
