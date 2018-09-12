package com.example.android.sd.ui.steel.connection.bolt;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.sd.ui.steel.connection.bolt.BoltFragments.BoltPageFour;
import com.example.android.sd.ui.steel.connection.bolt.BoltFragments.BoltPageOne;
import com.example.android.sd.ui.steel.connection.bolt.BoltFragments.BoltPageThree;
import com.example.android.sd.ui.steel.connection.bolt.BoltFragments.BoltPageTwo;
import com.example.android.sd.R;

import com.example.android.sd.ui.steel.utils.Compute;
import com.example.android.sd.ui.steel.utils.Variables;

import static com.example.android.sd.ui.steel.utils.FunctionKit.getFloatOf;

public class BoltActivity extends AppCompatActivity implements BoltPageOne.onFABNextClickListener,
                                                                BoltPageOne.onFABPreviousClickListener,
                                                                BoltPageTwo.onFABNextClickListener,
                                                                BoltPageTwo.onFABPreviousClickListener,
                                                                BoltPageThree.onFABNextClickListener,
                                                                BoltPageThree.onFABPreviousClickListener,
                                                                BoltPageFour.onPageFourConfirmClickListener,
                                                                BoltPageFour.onPageFourPreviousClickListener{


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
    private String Section_MI = Variables.defaultValue, Effective_Length = "200", SlendernessRatio = "20";
    private String Ultimate_Load_fu = "410", Yeild_Strength_fy = "250";
    private String Number_Of_Shear_Planes_n = "1";
    private String Factor_Of_Safety_Ymb = "1.25", Factor_Of_Safety_Ymo = "1.1";
    private String Thickness_thinner_plate_T = "10";

    private String Clearance = "2"
            , Length_Connection_LC
            , numberOfRows = "2"
            , length_BS
            , value_B
            , Strength_Tdg
            , Strength_Tdn
            , Strength_Tdb = "124.56"
            , Strength_Td = "144.12"
            , Area_Avg
            , Area_Atg
            , Area_Atn;

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

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    /**
     * Stores the values that has been passed through the intent
     */
    private void updateValuesOnLaunch(){
        Service_Load = getIntent().getStringExtra(Variables.serviceLoad);
        Factored_Load = String.valueOf(getFloatOf(Service_Load)*1.5);
    }

    /**
     *
     * @param boltGrade the value of boltGrade passed from page two
     * @param boltDia the value of boltDia passed from page two
     */
    private void updateValuesAfterPageOne(String boltGrade, String boltDia, String minimum_T, String effective_Length){

        Grade_Of_Bolts = boltGrade;
        Dia_Of_Bolts = boltDia;
        Thickness_thinner_plate_T = minimum_T;
        Pitch_Distance = String.valueOf(getFloatOf(boltDia)*2.5);
        End_Distance = String.valueOf(getFloatOf(boltDia)*1.5);
        Ultimate_Load_fu = String.valueOf(getFloatOf(Grade_Of_Bolts.substring(0,1))*100);
        Bolt_value = Compute.BoltValue(Dia_Of_Bolts,End_Distance,Pitch_Distance,Factor_Of_Safety_Ymb,
                                        Number_Of_Shear_Planes_n,Ultimate_Load_fu,Ultimate_Load_fu,Thickness_thinner_plate_T);
        Area_An = Compute.AreaAn(Factored_Load,Ultimate_Load_fu);
        Area_Ag = String.valueOf(getFloatOf(Area_An)*1.2);
        No_Of_Bolts = Compute.NumberOfBolts(Factored_Load,Bolt_value);
        Bolt_Strength = String.valueOf(getFloatOf(No_Of_Bolts)*getFloatOf(Bolt_value));
        Effective_Length = effective_Length;

    }

    /**
     *
     * @param dataBundle The data bundle passed from the page two after successful submission
     *                   the data is taken out and stored in the activity
     */
    private void updateValuesAfterPageTwo(Bundle dataBundle){
        Section_l = dataBundle.getString(Variables.section_l);
        Section_h = dataBundle.getString(Variables.section_h);
        Section_t = dataBundle.getString(Variables.section_t);
        Section_a = dataBundle.getString(Variables.section_a);
        Section_b = dataBundle.getString(Variables.section_b);
        Section_c = dataBundle.getString(Variables.section_c);
        Section_MI = dataBundle.getString(Variables.section_MI);

        Pitch_Distance = dataBundle.getString(Variables.pitch);
        End_Distance = dataBundle.getString(Variables.endDistance);
        Bolt_value = Compute.BoltValue(Dia_Of_Bolts,End_Distance,Pitch_Distance,Factor_Of_Safety_Ymb,
                Number_Of_Shear_Planes_n,Ultimate_Load_fu,"400","10");
        No_Of_Bolts = Compute.NumberOfBolts(Factored_Load,Bolt_value);
        Bolt_Strength = String.valueOf(getFloatOf(No_Of_Bolts)*getFloatOf(Bolt_value));

        Clearance = Compute.getClearance(Dia_Of_Bolts);
        numberOfRows = Compute.getNoOfRows(No_Of_Bolts,Section_l);
        Length_Connection_LC = Compute.getLengthOfConnection(No_Of_Bolts,numberOfRows,Pitch_Distance);
        length_BS = Compute.getLengthBS(Section_l,Section_a,Section_t);
        value_B = Compute.getValueB(Section_l,Section_t,length_BS,Length_Connection_LC,Yeild_Strength_fy,Ultimate_Load_fu);
        Area_Anc = Compute.getAreaAnc(Section_l,Section_t,Dia_Of_Bolts,Clearance);
        Area_Ago = Compute.getAreaAgo(Section_h,Section_t);
        Strength_Tdg = Compute.getStrengthTdg(Yeild_Strength_fy,Area_Ag,Factor_Of_Safety_Ymo);
        Strength_Tdn = Compute.getStrengthTdn(Area_Anc,Ultimate_Load_fu,Factor_Of_Safety_Ymb,value_B
                                                ,Yeild_Strength_fy,Area_Ago,Factor_Of_Safety_Ymo);
        SlendernessRatio = Compute.getSlendernessRatio(Section_MI,Effective_Length,Area_Ag);

    }

    /**
     *
     * @return a bundle of data required to revisit page one
     */
    private Bundle getBundleForPageOne(){
        Bundle bundle = new Bundle();
        bundle.putString(Variables.gradeOfBolt,Grade_Of_Bolts);
        bundle.putString(Variables.diaOfBolt,Dia_Of_Bolts);
        bundle.putString(Variables.minimumThickness, Thickness_thinner_plate_T);
        bundle.putString(Variables.effectiveLength, Effective_Length);
        return bundle;
    }

    /**
     *
     * @return a bundle for all the values required for page two
     */
    private Bundle getBundleForPageTwo(){
        Bundle completeBundle = new Bundle();
        Bundle bundle = new Bundle();
        bundle.putString(Variables.gradeOfBolt,Grade_Of_Bolts);
        bundle.putString(Variables.diaOfBolt,Dia_Of_Bolts);
        bundle.putString(Variables.valueBolt,Bolt_value);
        bundle.putString(Variables.numberBolt,No_Of_Bolts);
        bundle.putString(Variables.numOfRows,"2");
        bundle.putString(Variables.strengthBolt,Bolt_Strength);
        bundle.putString(Variables.pitch,Pitch_Distance);
        bundle.putString(Variables.endDistance,End_Distance);
        bundle.putString(Variables.Anc, Area_An);
        bundle.putString(Variables.Ago, Area_Ag);
        bundle.putString(Variables.minimumThickness, Thickness_thinner_plate_T);
        bundle.putString(Variables.factoredLoad,Factored_Load);
        bundle.putString(Variables.no_of_shear_Planes,Number_Of_Shear_Planes_n);
        bundle.putString(Variables.factorOfSafety_Ymb,Factor_Of_Safety_Ymb);
        bundle.putString(Variables.ultimateLoad_fu,Ultimate_Load_fu);

        completeBundle.putBundle(Variables.forPageTwoTVValues,bundle);

        if(!Section_a.equals(Variables.defaultValue)){
            Bundle defaultValuesBundle = new Bundle();

            defaultValuesBundle.putString(Variables.section_l,Section_l);
            defaultValuesBundle.putString(Variables.section_h,Section_h);
            defaultValuesBundle.putString(Variables.section_t,Section_t);
            defaultValuesBundle.putString(Variables.section_a,Section_a);
            defaultValuesBundle.putString(Variables.section_b,Section_b);
            defaultValuesBundle.putString(Variables.section_c,Section_c);
            defaultValuesBundle.putString(Variables.section_MI,Section_MI);

            completeBundle.putBundle(Variables.forPageTwoETValues,defaultValuesBundle);
        }
        return completeBundle;
    }

    /**
     *
     * @return bundle of all the data required for page three
     */
    private Bundle getBundleForPageThree(){
        Bundle bundle = new Bundle();
        bundle.putString(Variables.factoredLoad,Factored_Load);
        bundle.putString(Variables.section_l,Section_l);
        bundle.putString(Variables.section_h,Section_h);
        bundle.putString(Variables.section_t,Section_t);
        bundle.putString(Variables.SlendernessRatio,SlendernessRatio);
        bundle.putString(Variables.Strength_Tdg,Strength_Tdg);
        bundle.putString(Variables.Strength_Tdn,Strength_Tdn);
        bundle.putString(Variables.Strength_Tdb,Strength_Tdb);
        bundle.putString(Variables.Strength_Td,Strength_Td);
        bundle.putString(Variables.value_B,value_B);
        return bundle;
    }

    /**
     *
     * @return bundle of all data required to populate page four
     */
    private Bundle getBundleForPageFour(){
        Bundle bundle = new Bundle();
        bundle.putString(Variables.factoredLoad,Factored_Load);
        bundle.putString(Variables.gradeOfBolt,Grade_Of_Bolts);
        bundle.putString(Variables.numberBolt,No_Of_Bolts);
        bundle.putString(Variables.numOfRows,numberOfRows);
        bundle.putString(Variables.strengthBolt,Bolt_Strength);
        bundle.putString(Variables.section_l,Section_l);
        bundle.putString(Variables.section_h,Section_h);
        bundle.putString(Variables.section_t,Section_t);
        bundle.putString(Variables.section_a,Section_a);
        bundle.putString(Variables.section_b,Section_b);
        bundle.putString(Variables.Strength_Td,Strength_Td);
        return bundle;
    }


    @Override
    public void onBackPressed() {
        Log.i(getLocalClassName(),"Back Pressed");
    }

    @Override
    public void onPageOneNextClicked(String boltGrade, String boltDia, String minimumThickness_T, String effective_Length) {
        updateValuesAfterPageOne(boltGrade,boltDia,minimumThickness_T, effective_Length);
        if(boltPageTwoFragment == null) {
            boltPageTwoFragment = new BoltPageTwo();
        }
        boltPageTwoFragment.setArguments(getBundleForPageTwo());
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
        updateValuesAfterPageTwo(dataBundle);
        BoltPageThree fragment = new BoltPageThree();
        fragment.setArguments(getBundleForPageThree());
        mFragmentManager.beginTransaction()
                .replace(R.id.Activity_container,fragment)
                .commit();
    }

    @Override
    public void onPageTwoPreviousClicked() {
        if (boltPageOneFragment == null){
            boltPageOneFragment = new BoltPageOne();
        }
        boltPageOneFragment.setArguments(getBundleForPageOne());
        mFragmentManager.beginTransaction()
                .replace(R.id.Activity_container,boltPageOneFragment)
                .commit();
    }

    @Override
    public void onPageThreeNextClicked() {
        BoltPageFour fragment = new BoltPageFour();
        fragment.setArguments(getBundleForPageFour());
        mFragmentManager.beginTransaction()
                .replace(R.id.Activity_container,fragment)
                .commit();

    }

    @Override
    public void onPageThreePreviousClicked() {
        boltPageTwoFragment = new BoltPageTwo();
        boltPageTwoFragment.setArguments(getBundleForPageTwo());
        mFragmentManager.beginTransaction()
                .replace(R.id.Activity_container, boltPageTwoFragment)
                .commit();
    }

    @Override
    public void onPageFourPreviousClicked() {
        BoltPageThree fragment = new BoltPageThree();
        fragment.setArguments(getBundleForPageThree());
        mFragmentManager.beginTransaction()
                .replace(R.id.Activity_container,fragment)
                .commit();

    }

    @Override
    public void onPageFourConfirmClicked() {
        finish();

    }
}
