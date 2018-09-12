package com.example.android.sd.ui.steel.connection.bolt.BoltFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.sd.R;

import com.example.android.sd.ui.steel.utils.Variables;

import static com.example.android.sd.ui.steel.utils.FunctionKit.getTwoDecimalValue;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class BoltPageFour extends Fragment {

    private onPageFourConfirmClickListener mConfirmListener;
    private onPageFourPreviousClickListener mPreviousListener;
    private Bundle dataBundle = null;

    private CoordinatorLayout mCoordinatorLayout;

    public BoltPageFour() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dataBundle = getArguments();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.bolt_page_four, container, false);
        mCoordinatorLayout = rootView.findViewById(R.id.BPageFour_CoordinateLayout);
        if(dataBundle != null){
            ((TextView) rootView.findViewById(R.id.BPageFour_TV_FactoredLoad))
                    .setText(String.format("%s%s", getTwoDecimalValue(dataBundle.getString(Variables.factoredLoad)), Variables.unitKN));
            ((TextView) rootView.findViewById(R.id.BPageFour_TV_boltGrade))
                    .setText(dataBundle.getString(Variables.gradeOfBolt));
            ((TextView) rootView.findViewById(R.id.BPageFour_TV_NoOfBolt))
                    .setText(dataBundle.getString(Variables.numberBolt));
            ((TextView) rootView.findViewById(R.id.BPageFour_TV_NoOfRows))
                    .setText(dataBundle.getString(Variables.numOfRows));
            ((TextView) rootView.findViewById(R.id.BPageFour_TV_BoltStrength))
                    .setText(String.format("%s%s", getTwoDecimalValue(dataBundle.getString(Variables.strengthBolt)), Variables.unitKN));
            ((TextView) rootView.findViewById(R.id.BPAgeFour_TV_section_l))
                    .setText(dataBundle.getString(Variables.section_l));
            ((TextView) rootView.findViewById(R.id.BPAgeFour_TV_section_h))
                    .setText(dataBundle.getString(Variables.section_h));
            ((TextView) rootView.findViewById(R.id.BPAgeFour_TV_section_t))
                    .setText(dataBundle.getString(Variables.section_t));
            ((TextView) rootView.findViewById(R.id.BPageFour_TV_Td))
                    .setText(String.format("%s%s", getTwoDecimalValue(dataBundle.getString(Variables.Strength_Td)), Variables.unitKN));
        }
        (rootView.findViewById(R.id.BPageFour_FABPrevious)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPreviousListener.onPageFourPreviousClicked();
            }
        });
        (rootView.findViewById(R.id.BPageFour_FABConfirm)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mConfirmListener.onPageFourConfirmClicked();
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mPreviousListener = (onPageFourPreviousClickListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() +
            "Must implement onPageFourPreviousClickListener");
        }
        try {
            mConfirmListener =(onPageFourConfirmClickListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() +
                    "Must inplement onPageFourConfirmClickListener");

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mConfirmListener = null;
        mPreviousListener = null;
    }

    public interface onPageFourPreviousClickListener{
        void onPageFourPreviousClicked();
    }

    public interface onPageFourConfirmClickListener{
        void onPageFourConfirmClicked();
    }

}
