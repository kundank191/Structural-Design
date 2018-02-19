package com.example.android.sd.BoltFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.sd.R;

import utils.Compute;
import utils.FunctionKit;
import utils.Variables;

import static utils.FunctionKit.getFloatOf;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class BoltPageTwo extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM_GRADE = "param1";
    private static final String ARG_PARAM_DIA = "param2";

    onFABNextClickListener mListener;
    onFABPreviousClickListener mPreviousListener;

    private Bundle startUpBundle = null;

    private EditText ETSectionL;
    private EditText ETSectionH;
    private EditText ETSectionT;
    private EditText ETSectionA;
    private EditText ETSectionB;
    private EditText ETSectionC;
    private EditText ETSectionMI;
    private CoordinatorLayout mCoordinatorLayout;
    private TextView TVGrade, TVDia, TVBoltValue, TVNo, TVBoltStrength, TVPitch, TVEnd, TVAnc, TVAgo;
    private String mDefaultPitch, mDefaultEndDistance;
    private String Thickness_thinner_plate_T, mBoltDia;
    private String Ultimate_Load_fu = "400";
    private String Number_Of_Shear_Planes_n = "1";
    private String Factor_Of_Safety_Ymb = "1.25", FactoredLoad;

    //private OnFragmentInteractionListener mListener;

    public BoltPageTwo() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            startUpBundle = getArguments();
        } else {
            startUpBundle = null;
        }
    }

    //Setting up the views if value is provided
    private void setupViews(Bundle bundle){
        mDefaultPitch = bundle.getString(Variables.pitch);
        mDefaultEndDistance = bundle.getString(Variables.endDistance);
        Thickness_thinner_plate_T = bundle.getString(Variables.minimumThickness);
        Ultimate_Load_fu = bundle.getString(Variables.ultimateLoad_fu);
        Number_Of_Shear_Planes_n = bundle.getString(Variables.no_of_shear_Planes);
        Factor_Of_Safety_Ymb = bundle.getString(Variables.factorOfSafety_Ymb);
        FactoredLoad = bundle.getString(Variables.factoredLoad);
        mBoltDia = bundle.getString(Variables.diaOfBolt);
        TVGrade.setText(bundle.getString(Variables.gradeOfBolt) );
        TVDia.setText(String.format("%s%s", bundle.getString(Variables.diaOfBolt), Variables.unitMM));
        TVBoltValue.setText(String.format("%s%s", FunctionKit.getTwoDecimalValue(bundle.getString(Variables.valueBolt)), Variables.unitKN));
        TVNo.setText(bundle.getString(Variables.numberBolt));
        TVBoltStrength.setText(String.format("%s%s", FunctionKit.getTwoDecimalValue(bundle.getString(Variables.strengthBolt)), Variables.unitKN));
        TVPitch.setText(String.format("%s%s", FunctionKit.getTwoDecimalValue(bundle.getString(Variables.pitch)), Variables.unitMM));
        TVEnd.setText(String.format("%s%s", FunctionKit.getTwoDecimalValue(bundle.getString(Variables.endDistance)), Variables.unitMM));
        TVAnc.setText(String.format("%s%s", FunctionKit.getTwoDecimalValue(bundle.getString(Variables.Anc)), Variables.unitMM2));
        TVAgo.setText(String.format("%s%s", FunctionKit.getTwoDecimalValue(bundle.getString(Variables.Ago)), Variables.unitMM2));
    }

    private void setupDefaultValues(Bundle bundle){
        ETSectionL.setText(bundle.getString(Variables.section_l));
        ETSectionH.setText(bundle.getString(Variables.section_h));
        ETSectionT.setText(bundle.getString(Variables.section_t));
        ETSectionA.setText(bundle.getString(Variables.section_a));
        ETSectionB.setText(bundle.getString(Variables.section_b));
        ETSectionC.setText(bundle.getString(Variables.section_c));
        ETSectionMI.setText(bundle.getString(Variables.section_MI));

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bolt_page_two,container,false);

        ETSectionL = (EditText) rootView.findViewById(R.id.BPageTwo_l);
        ETSectionH = (EditText) rootView.findViewById(R.id.BPageTwo_h);
        ETSectionT = (EditText) rootView.findViewById(R.id.BPageTwo_t);
        ETSectionA = (EditText) rootView.findViewById(R.id.BPageTwo_a);
        ETSectionB = (EditText) rootView.findViewById(R.id.BPageTwo_b);
        ETSectionC = (EditText) rootView.findViewById(R.id.BPageTwo_c);
        ETSectionMI = (EditText) rootView.findViewById(R.id.BPageTwo_MI);
        mCoordinatorLayout = (CoordinatorLayout) rootView.findViewById(R.id.BPageTwo_CoordinateLayout);
        TVGrade = (TextView) rootView.findViewById(R.id.BPageTwo_TV_BoltGrade);
        TVDia = (TextView) rootView.findViewById(R.id.BPageTwo_TV_BoltDia);
        TVBoltValue = (TextView) rootView.findViewById(R.id.BPageTwo_TV_BoltValue);
        TVNo = (TextView) rootView.findViewById(R.id.BPageTwo_TV_NoOfBolts);
        TVBoltStrength = (TextView) rootView.findViewById(R.id.BPageTwo_TV_BoltStrength);
        TVPitch = (TextView) rootView.findViewById(R.id.BPageTwo_TV_Pitch);
        TVEnd = (TextView) rootView.findViewById(R.id.BPageTwo_TV_EndDistance);
        TVAnc = (TextView) rootView.findViewById(R.id.BPageTwo_TV_AreaAnc);
        TVAgo = (TextView) rootView.findViewById(R.id.BPageTwo_TV_AreaAgo);
        if(savedInstanceState != null){
 //           setupViews(savedInstanceState);
        }
        if(startUpBundle != null){
            setupViews(startUpBundle.getBundle(Variables.forPageTwoTVValues));
            if(startUpBundle.containsKey(Variables.forPageTwoETValues)){
                setupDefaultValues(startUpBundle.getBundle(Variables.forPageTwoETValues));
            }
        }
        (rootView.findViewById(R.id.BPageOne_addToPitch)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Compute.pitchCanChange(mDefaultPitch,Thickness_thinner_plate_T,mBoltDia,true)){
                    mDefaultPitch = String.valueOf(getFloatOf(mDefaultPitch) + 1);
                    TVPitch.setText(String.format("%s%s", FunctionKit.getTwoDecimalValue(mDefaultPitch), Variables.unitMM));
                    String Bolt_value = Compute.BoltValue(mBoltDia,mDefaultEndDistance,mDefaultPitch,Factor_Of_Safety_Ymb,
                            Number_Of_Shear_Planes_n,Ultimate_Load_fu,"400","10");
                    TVBoltValue.setText(String.format("%s%s", FunctionKit.getTwoDecimalValue(Bolt_value), Variables.unitKN));
                    String No_Of_Bolts = Compute.NumberOfBolts(FactoredLoad,Bolt_value);
                    TVNo.setText(No_Of_Bolts);
                    String Bolt_Strength = String.valueOf(getFloatOf(No_Of_Bolts)*getFloatOf(Bolt_value));
                    TVBoltStrength.setText(String.format("%s%s", FunctionKit.getTwoDecimalValue(Bolt_Strength), Variables.unitKN));
                } else {
                    Snackbar snackbar = FunctionKit.getSnackBar(mCoordinatorLayout, R.string.PitchMessageMore);
                    snackbar.show();
                }
            }
        });
        (rootView.findViewById(R.id.BPageOne_minusFromPitch)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Compute.pitchCanChange(mDefaultPitch,Thickness_thinner_plate_T,mBoltDia,false)){
                    mDefaultPitch = String.valueOf(getFloatOf(mDefaultPitch) - 1);
                    TVPitch.setText(String.format("%s%s", FunctionKit.getTwoDecimalValue(mDefaultPitch), Variables.unitMM));
                    String Bolt_value = Compute.BoltValue(mBoltDia,mDefaultEndDistance,mDefaultPitch,Factor_Of_Safety_Ymb,
                            Number_Of_Shear_Planes_n,Ultimate_Load_fu,"400","10");
                    TVBoltValue.setText(String.format("%s%s", FunctionKit.getTwoDecimalValue(Bolt_value), Variables.unitKN));
                    String No_Of_Bolts = Compute.NumberOfBolts(FactoredLoad,Bolt_value);
                    TVNo.setText(No_Of_Bolts);
                    String Bolt_Strength = String.valueOf(getFloatOf(No_Of_Bolts)*getFloatOf(Bolt_value));
                    TVBoltStrength.setText(String.format("%s%s", FunctionKit.getTwoDecimalValue(Bolt_Strength), Variables.unitKN));

                } else {
                    Snackbar snackbar = FunctionKit.getSnackBar(mCoordinatorLayout, R.string.PitchMessageLess);
                    snackbar.show();
                }
            }
        });
        (rootView.findViewById(R.id.BPageOne_addToEndDistance)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Compute.endDistanceCanChange(mDefaultEndDistance,Thickness_thinner_plate_T,mBoltDia,true)){
                    mDefaultEndDistance = String.valueOf(getFloatOf(mDefaultEndDistance) + 1);
                    TVEnd.setText(String.format("%s%s", FunctionKit.getTwoDecimalValue(mDefaultEndDistance), Variables.unitMM));
                    String Bolt_value = Compute.BoltValue(mBoltDia,mDefaultEndDistance,mDefaultPitch,Factor_Of_Safety_Ymb,
                            Number_Of_Shear_Planes_n,Ultimate_Load_fu,"400","10");
                    TVBoltValue.setText(String.format("%s%s", FunctionKit.getTwoDecimalValue(Bolt_value), Variables.unitKN));
                    String No_Of_Bolts = Compute.NumberOfBolts(FactoredLoad,Bolt_value);
                    TVNo.setText(No_Of_Bolts);
                    String Bolt_Strength = String.valueOf(getFloatOf(No_Of_Bolts)*getFloatOf(Bolt_value));
                    TVBoltStrength.setText(String.format("%s%s", FunctionKit.getTwoDecimalValue(Bolt_Strength), Variables.unitKN));

                } else {
                    Snackbar snackbar = FunctionKit.getSnackBar(mCoordinatorLayout, R.string.EndMessageMore);
                    snackbar.show();
                }
            }
        });
        (rootView.findViewById(R.id.BPageOne_minusFromEndDistance)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Compute.endDistanceCanChange(mDefaultEndDistance,Thickness_thinner_plate_T,mBoltDia,false)){
                    mDefaultEndDistance = String.valueOf(getFloatOf(mDefaultEndDistance) - 1);
                    TVEnd.setText(String.format("%s%s", FunctionKit.getTwoDecimalValue(mDefaultEndDistance), Variables.unitMM));
                    String Bolt_value = Compute.BoltValue(mBoltDia,mDefaultEndDistance,mDefaultPitch,Factor_Of_Safety_Ymb,
                            Number_Of_Shear_Planes_n,Ultimate_Load_fu,"400","10");
                    TVBoltValue.setText(String.format("%s%s", FunctionKit.getTwoDecimalValue(Bolt_value), Variables.unitKN));
                    String No_Of_Bolts = Compute.NumberOfBolts(FactoredLoad,Bolt_value);
                    TVNo.setText(No_Of_Bolts);
                    String Bolt_Strength = String.valueOf(getFloatOf(No_Of_Bolts)*getFloatOf(Bolt_value));
                    TVBoltStrength.setText(String.format("%s%s", FunctionKit.getTwoDecimalValue(Bolt_Strength), Variables.unitKN));

                } else {
                    Snackbar snackbar = FunctionKit.getSnackBar(mCoordinatorLayout, R.string.EndMessageLess);
                    snackbar.show();
                }
            }
        });
        (rootView.findViewById(R.id.BPageTwo_FABNext)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dataFrom(ETSectionL) != null){
                    if(dataFrom(ETSectionH) != null){
                        if(dataFrom(ETSectionT) != null){
                            if(dataFrom(ETSectionA) != null){
                                if(dataFrom(ETSectionB) != null){
                                    if(dataFrom(ETSectionC) != null){
                                        if(dataFrom(ETSectionMI) != null){
                                            Bundle bundle = new Bundle();
                                            bundle.putString(Variables.section_l,dataFrom(ETSectionL));
                                            bundle.putString(Variables.section_h,dataFrom(ETSectionH));
                                            bundle.putString(Variables.section_t,dataFrom(ETSectionT));
                                            bundle.putString(Variables.section_a,dataFrom(ETSectionA));
                                            bundle.putString(Variables.section_b,dataFrom(ETSectionB));
                                            bundle.putString(Variables.section_c,dataFrom(ETSectionC));
                                            bundle.putString(Variables.section_MI,dataFrom(ETSectionMI));
                                            bundle.putString(Variables.pitch,mDefaultPitch);
                                            bundle.putString(Variables.endDistance,mDefaultEndDistance);
                                            mListener.onPageTwoNextClicked(bundle);
                                        } else {
                                            Snackbar snackbar = FunctionKit.getSnackBar(mCoordinatorLayout,R.string.enter_section_MI);
                                            snackbar.show();
                                        }
                                    } else {
                                        Snackbar snackbar = FunctionKit.getSnackBar(mCoordinatorLayout,R.string.enter_section_c);
                                        snackbar.show();
                                    }
                                } else {
                                    Snackbar snackbar = FunctionKit.getSnackBar(mCoordinatorLayout,R.string.enter_section_b);
                                    snackbar.show();
                                }
                            } else {
                                Snackbar snackbar = FunctionKit.getSnackBar(mCoordinatorLayout,R.string.enter_section_a);
                                snackbar.show();
                            }
                        } else {
                            Snackbar snackbar = FunctionKit.getSnackBar(mCoordinatorLayout,R.string.enter_section_thickness);
                            snackbar.show();
                        }

                    } else {
                        Snackbar snackbar = FunctionKit.getSnackBar(mCoordinatorLayout,R.string.enter_section_breadth);
                        snackbar.show();
                    }

                } else {
                    Snackbar snackbar = FunctionKit.getSnackBar(mCoordinatorLayout,R.string.enter_section_length);
                    snackbar.show();
                }
            }
        });

        (rootView.findViewById(R.id.BPageTwo_FABPrevious)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPreviousListener.onPageTwoPreviousClicked();
            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mListener = (onFABNextClickListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() +
                    "Must Implement on Next Click Listener");
        }
        try {
            mPreviousListener = (onFABPreviousClickListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() +
                    "Must Implement onPreviousClick listener");
        }
    }

    /**
     *
     * @param editText Takes in edit text
     * @return null if edit text is empty else the value
     */
    private String dataFrom(EditText editText){
        if(!TextUtils.isEmpty(editText.getText())){
            return editText.getText().toString();
        } else {
            return null;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
        mListener = null;
        mPreviousListener = null;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public interface onFABNextClickListener{
        void onPageTwoNextClicked(Bundle dataBundle);
    }

    public interface onFABPreviousClickListener{
        void onPageTwoPreviousClicked();
    }
}
