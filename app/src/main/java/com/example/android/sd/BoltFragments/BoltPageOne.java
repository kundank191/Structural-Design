package com.example.android.sd.BoltFragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link BoltPageOne#newInstance} factory method to
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

    private EditText mBoltGradeE;
    private EditText mBoltDia;
    private CoordinatorLayout mCoordinatorLayout;

    //private OnFragmentInteractionListener mListener;

    public BoltPageOne() {
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
    public static BoltPageOne newInstance(String boltGrade, String boltDia) {
        BoltPageOne fragment = new BoltPageOne();
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
            mGradeOfBolt = getArguments().getString(ARG_PARAM_GRADE);
            mDiaOfBolt = getArguments().getString(ARG_PARAM_DIA);
            setupViews(mGradeOfBolt,mDiaOfBolt);
        }
    }

    //Setting up the views if value is provided
    private void setupViews(String boltGrade, String boltDia){
        mBoltGradeE.setText(boltGrade);
        mBoltDia.setText(boltDia);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bolt_page_one,container,false);

        mBoltGradeE = (EditText) rootView.findViewById(R.id.BPageOne_BoltGrade);
        mBoltDia = (EditText) rootView.findViewById(R.id.BPageOne_BoltDia);
        if(savedInstanceState != null){
            mGradeOfBolt = savedInstanceState.getString(ARG_PARAM_GRADE);
            mDiaOfBolt = savedInstanceState.getString(ARG_PARAM_DIA);
            setupViews(mGradeOfBolt,mDiaOfBolt);
        }

        mCoordinatorLayout = (CoordinatorLayout) rootView.findViewById(R.id.BPageOne_CoordinateLayout);
        FloatingActionButton mNext = rootView.findViewById(R.id.BPageOne_FABNext);
        FloatingActionButton mPrevious = rootView.findViewById(R.id.BPageOne_FABPrevious);
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getBoltGrade() != null){
                    if(Float.parseFloat(getBoltGrade()) <= 10) {
                        if (getBoltDia() != null) {
                            mListener.onPageOneNextClicked(getBoltGrade(), getBoltDia());
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
        if(getBoltGrade() != null && getBoltDia() != null) {
            outState.putString(ARG_PARAM_GRADE, getBoltGrade());
            outState.putString(ARG_PARAM_DIA, getBoltDia());
        }
        super.onSaveInstanceState(outState);
    }

    @Nullable
    private String getBoltGrade(){
        if(!TextUtils.isEmpty(mBoltGradeE.getText())){
            return mBoltGradeE.getText().toString();
        } else {
            return null;
        }
    }

    @Nullable
    private String getBoltDia(){
        if(!TextUtils.isEmpty(mBoltDia.getText())){
            return mBoltDia.getText().toString();
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
        void onPageOneNextClicked(String boltGrade, String boltDia);
    }

    public interface onFABPreviousClickListener{
        void onPageOnePreviousClicked();
    }
}
