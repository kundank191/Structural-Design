package com.example.android.sd.BoltFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.android.sd.R;

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

    private String mGradeOfBolt;
    private String mDiaOfBolt;

    private EditText mBoltGradeE;
    private EditText mBoltDia;
    private CoordinatorLayout mCoordinatorLayout;

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
            setupViews(getArguments());
        }
    }

    //Setting up the views if value is provided
    private void setupViews(Bundle bundle){

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bolt_page_two,container,false);
//
//        mBoltGradeE = (EditText) rootView.findViewById(R.id.BPageOne_BoltGrade);
//        mBoltDia = (EditText) rootView.findViewById(R.id.BPageOne_BoltDia);
//        if(savedInstanceState != null){
//            mGradeOfBolt = savedInstanceState.getString(ARG_PARAM_GRADE);
//            mDiaOfBolt = savedInstanceState.getString(ARG_PARAM_DIA);
//            setupViews(mGradeOfBolt,mDiaOfBolt);
//        }
//
//        mCoordinatorLayout = (CoordinatorLayout) rootView.findViewById(R.id.BPageOne_CoordinateLayout);
//        FloatingActionButton mNext = rootView.findViewById(R.id.BPageOne_FABNext);
//        FloatingActionButton mPrevious = rootView.findViewById(R.id.BPageOne_FABPrevious);
//        mNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(getBoltGrade() != null){
//                    if(Float.parseFloat(getBoltGrade()) <= 10) {
//                        if (getBoltDia() != null) {
//                            mListener.onPageOneNextClicked(getBoltGrade(), getBoltDia());
//                        } else {
//                            Snackbar snackbar = getSnackBar(mCoordinatorLayout, R.string.enter_bolt_dia);
//                            snackbar.show();
//                        }
//                    } else {
//                        Snackbar snackbar = getSnackBar(mCoordinatorLayout, R.string.wrong_bolt_grade);
//                        snackbar.show();
//                    }
//                } else {
//                    Snackbar snackbar = getSnackBar(mCoordinatorLayout, R.string.enter_bolt_grade);
//                    snackbar.show();
//                }
//            }
//        });
//        mPrevious.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mPreviousListener.onPageOnePreviousClicked();
//            }
//        });
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
        void onPageTwoNextClicked(String boltGrade, String boltDia);
    }

    public interface onFABPreviousClickListener{
        void onPageTwoPreviousClicked();
    }
}
