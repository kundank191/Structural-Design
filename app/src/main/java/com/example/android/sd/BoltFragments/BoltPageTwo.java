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

import utils.FunctionKit;
import utils.Variables;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link BoltPageTwo#newInstance} factory method to
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

    //private OnFragmentInteractionListener mListener;

    public BoltPageTwo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param boltGrade Parameter 1.
     * @param boltDia Parameter 2.
     * @return A new instance of fragment BoltPageOne.
     */
    public static BoltPageTwo newInstance(String boltGrade, String boltDia) {
        BoltPageTwo fragment = new BoltPageTwo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_GRADE, boltGrade);
        args.putString(ARG_PARAM_DIA, boltDia);
        fragment.setArguments(args);
        return fragment;
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
        TVGrade.setText(bundle.getString(Variables.gradeOfBolt));
        TVDia.setText(bundle.getString(Variables.diaOfBolt));
        TVBoltValue.setText(bundle.getString(Variables.valueBolt));
        TVNo.setText(bundle.getString(Variables.numberBolt));
        TVBoltStrength.setText(bundle.getString(Variables.strengthBolt));
        TVPitch.setText(bundle.getString(Variables.pitch));
        TVEnd.setText(bundle.getString(Variables.endDistance));
        TVAnc.setText(bundle.getString(Variables.Anc));
        TVAgo.setText(bundle.getString(Variables.Ago));
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
            setupViews(startUpBundle);
        }
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
