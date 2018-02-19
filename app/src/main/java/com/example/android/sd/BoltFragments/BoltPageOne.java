package com.example.android.sd.BoltFragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.sd.R;

import utils.Variables;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class BoltPageOne extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM_GRADE = "param1";
    private static final String ARG_PARAM_DIA = "param2";

    onFABNextClickListener mListener;
    onFABPreviousClickListener mPreviousListener;

    private String mGradeOfBolt;
    private String mDiaOfBolt;
    private String mMinimumThickness;
    private Bundle dataBundle = null;

    private EditText mBoltGradeE;
    private EditText mBoltDia;
    private EditText mMinimumThicknessET;
    private CoordinatorLayout mCoordinatorLayout;

    //private OnFragmentInteractionListener mListener;

    public BoltPageOne() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dataBundle = getArguments();
        }
    }

    //Setting up the views if value is provided
    private void setupViews(String boltGrade, String boltDia, String minimumThickness){
        mBoltGradeE.setText(boltGrade);
        mBoltDia.setText(boltDia);
        mMinimumThicknessET.setText(minimumThickness);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bolt_page_one,container,false);

        mBoltGradeE = (EditText) rootView.findViewById(R.id.BPageOne_BoltGrade);
        mBoltDia = (EditText) rootView.findViewById(R.id.BPageOne_BoltDia);
        mMinimumThicknessET = (EditText) rootView.findViewById(R.id.BPageOne_MinimumThickness);
//        if(savedInstanceState != null){
//            mGradeOfBolt = savedInstanceState.getString(ARG_PARAM_GRADE);
//            mDiaOfBolt = savedInstanceState.getString(ARG_PARAM_DIA);
//            setupViews(mGradeOfBolt,mDiaOfBolt);
//        }
        if(dataBundle != null){
            mGradeOfBolt = dataBundle.getString(Variables.gradeOfBolt);
            mDiaOfBolt = dataBundle.getString(Variables.diaOfBolt);
            mMinimumThickness = dataBundle.getString(Variables.minimumThickness);
            setupViews(mGradeOfBolt, mDiaOfBolt, mMinimumThickness);
        }

        mCoordinatorLayout = (CoordinatorLayout) rootView.findViewById(R.id.BPageOne_CoordinateLayout);
        FloatingActionButton mNext = rootView.findViewById(R.id.BPageOne_FABNext);
        FloatingActionButton mPrevious = rootView.findViewById(R.id.BPageOne_FABPrevious);
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dataFrom(mBoltGradeE) != null){
                    if(Float.parseFloat(dataFrom(mBoltGradeE)) <= 10) {
                        if (dataFrom(mBoltDia) != null) {
                            if(dataFrom(mMinimumThicknessET) != null) {
                                mListener.onPageOneNextClicked(dataFrom(mBoltGradeE), dataFrom(mBoltDia), dataFrom(mMinimumThicknessET));
                            } else {
                                Snackbar snackbar = getSnackBar(mCoordinatorLayout, R.string.enter_minimum_plate_thickness);
                                snackbar.show();
                            }
                        } else {
                            Snackbar snackbar = getSnackBar(mCoordinatorLayout, R.string.enter_bolt_dia);
                            snackbar.show();
                        }
                    } else {
                        Snackbar snackbar = getSnackBar(mCoordinatorLayout, R.string.wrong_bolt_grade);
                        snackbar.show();
                    }
                } else {
                    Snackbar snackbar = getSnackBar(mCoordinatorLayout, R.string.enter_bolt_grade);
                    snackbar.show();
                }
            }
        });
        mPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPreviousListener.onPageOnePreviousClicked();
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

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
        mListener = null;
        mPreviousListener = null;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if(dataFrom(mBoltGradeE) != null && dataFrom(mBoltDia) != null) {
            outState.putString(ARG_PARAM_GRADE, dataFrom(mBoltGradeE));
            outState.putString(ARG_PARAM_DIA, dataFrom(mBoltDia));
        }
        super.onSaveInstanceState(outState);
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

    private Snackbar getSnackBar(CoordinatorLayout mCoordinateLayout , int stringMessageID){
        Snackbar snackbar = Snackbar.make(mCoordinateLayout, stringMessageID, Snackbar.LENGTH_SHORT);
        snackbar.getView().setBackgroundColor(Color.WHITE);
        TextView textView = (snackbar.getView()).findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.argb(255, 3, 169, 244));
        return snackbar;
    }

    public interface onFABNextClickListener{
        void onPageOneNextClicked(String boltGrade, String boltDia, String minimumThickness_T);
    }

    public interface onFABPreviousClickListener{
        void onPageOnePreviousClicked();
    }
}
