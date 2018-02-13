package com.example.android.sd.BoltFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
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
 * Use the {@link BoltPageOne#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BoltPageOne extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM_GRADE = "param1";
    private static final String ARG_PARAM_DIA = "param2";
    onFABNextClickListener mListener;
    private String mGradeOfBolt;
    private String mDiaOfBolt;
    private EditText mBoltGradeE;
    private EditText mBoltDia;
    private FloatingActionButton mNext;

    //private OnFragmentInteractionListener mListener;

    public BoltPageOne() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BoltPageOne.
     */
    public static BoltPageOne newInstance(String param1, String param2) {
        BoltPageOne fragment = new BoltPageOne();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_GRADE, param1);
        args.putString(ARG_PARAM_DIA, param2);
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
        } else if(savedInstanceState != null){
            mGradeOfBolt = savedInstanceState.getString(ARG_PARAM_GRADE);
            mDiaOfBolt = savedInstanceState.getString(ARG_PARAM_DIA);
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
        mNext = (FloatingActionButton) rootView.findViewById(R.id.BPageOne_FABNext);
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onNextClicked("hi","hi");
            }
        });
        return rootView;
    }

//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mListener = (onFABNextClickListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() +
                    "Must Implement on Next Click Listener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if(!TextUtils.isEmpty(mBoltGradeE.getText())) {
            mGradeOfBolt = mBoltGradeE.getText().toString();
            outState.putString(ARG_PARAM_GRADE, mGradeOfBolt);
        }
        if(!TextUtils.isEmpty(mBoltDia.getText())) {
            mDiaOfBolt = mBoltDia.getText().toString();
            outState.putString(ARG_PARAM_DIA, mDiaOfBolt);
        }
        super.onSaveInstanceState(outState);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }

    public interface onFABNextClickListener{
        void onNextClicked(String boltGrade, String boltDia);
    }
}
