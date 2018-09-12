package com.example.android.sd.ui.steel.connection.bolt.BoltFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.sd.R;

import com.example.android.sd.ui.steel.utils.Variables;

import static com.example.android.sd.ui.steel.utils.FunctionKit.getFloatOf;
import static com.example.android.sd.ui.steel.utils.FunctionKit.getSnackBar;
import static com.example.android.sd.ui.steel.utils.FunctionKit.getTwoDecimalValue;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class BoltPageThree extends Fragment {

    private Bundle dataBundle = null;
    private onFABNextClickListener mNextListener;
    private onFABPreviousClickListener mPreviousListener;
    private CoordinatorLayout mCoordinatorLayout;

    private String SlendernessRatio
            , Tdg
            , Tdn
            , Beta
            , Tdb
            , Td
            , FactoredLoad
            , section_l
            , section_h
            , section_t;
    private TextView TV_FL
            ,TV_SR
            ,TV_S_l
            ,TV_S_h
            ,TV_S_t
            ,TV_Tdg
            ,TV_Tdn
            ,TV_Beta
            ,TV_Tdb
            ,TV_Td;

    public BoltPageThree() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dataBundle = getArguments();
        }
    }

    /**
     *
     * @param data the data passed from the activity will be used to populate views
     */
    private void setupViews(Bundle data){
        FactoredLoad = data.getString(Variables.factoredLoad);
        section_l = data.getString(Variables.section_l);
        section_h = data.getString(Variables.section_h);
        section_t = data.getString(Variables.section_t);
        SlendernessRatio = data.getString(Variables.SlendernessRatio);
        Tdg = data.getString(Variables.Strength_Tdg);
        Tdn = data.getString(Variables.Strength_Tdn);
        Tdb = data.getString(Variables.Strength_Tdb);
        Td = data.getString(Variables.Strength_Td);
        Beta = data.getString(Variables.value_B);

        TV_FL.setText(String.format("%s%s", getTwoDecimalValue(FactoredLoad), Variables.unitKN));
        TV_S_l.setText(section_l);
        TV_S_h.setText(section_h);
        TV_S_t.setText(section_t);
        TV_SR.setText(getTwoDecimalValue(SlendernessRatio));
        TV_Tdg.setText(String.format("%s%s", getTwoDecimalValue(Tdg), Variables.unitKN));
        TV_Tdn.setText(String.format("%s%s", getTwoDecimalValue(Tdn), Variables.unitKN));
        TV_Tdb.setText(String.format("%s%s", getTwoDecimalValue(Tdb), Variables.unitKN));
        TV_Td.setText(String.format("%s%s", getTwoDecimalValue(Td), Variables.unitKN));
        TV_Beta.setText(getTwoDecimalValue(Beta));

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bolt_page_three, container, false);
        mCoordinatorLayout = rootView.findViewById(R.id.BPageThree_CoordinateLayout);
        TV_FL = (TextView) rootView.findViewById(R.id.BPageThree_FactoredLoad);
        TV_S_l = (TextView) rootView.findViewById(R.id.BPAgeThree_TV_section_l);
        TV_S_h = (TextView) rootView.findViewById(R.id.BPAgeThree_TV_section_h);
        TV_S_t = (TextView) rootView.findViewById(R.id.BPAgeThree_TV_section_t);
        TV_SR = (TextView) rootView.findViewById(R.id.BPageThree_TV_SlendernessRatio);
        TV_Tdg = (TextView) rootView.findViewById(R.id.BPageThree_TV_Tdg);
        TV_Tdn = (TextView) rootView.findViewById(R.id.BPageThree_TV_Tdn);
        TV_Beta = (TextView) rootView.findViewById(R.id.BPageThree_TV_B);
        TV_Tdb = (TextView) rootView.findViewById(R.id.BPageThree_TV_Tdb);
        TV_Td = (TextView) rootView.findViewById(R.id.BPageThree_TV_Td);

        if(dataBundle != null){
            setupViews(dataBundle);
        }
        (rootView.findViewById(R.id.BPageThree_FABPrevious)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = getSnackBar(mCoordinatorLayout,R.string.bolts_string);
                snackbar.show();
                mPreviousListener.onPageThreePreviousClicked();
            }
        });

        (rootView.findViewById(R.id.BPageThree_FABNext)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getFloatOf(SlendernessRatio) <= 400){
                    if (getFloatOf(Beta) >= 0.7 && getFloatOf(Beta) <= 1.0){
                        if (getFloatOf(Td) >= getFloatOf(FactoredLoad)){
                            mNextListener.onPageThreeNextClicked();
                        } else {
                            Snackbar snackbar = getSnackBar(mCoordinatorLayout,R.string.FailureTd);
                            snackbar.setDuration(Snackbar.LENGTH_LONG);
                            snackbar.show();
                        }
                    } else {
                        Snackbar snackbar = getSnackBar(mCoordinatorLayout,R.string.FailureB);
                        snackbar.setDuration(Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                } else {
                    Snackbar snackbar = getSnackBar(mCoordinatorLayout,R.string.FailureSR);
                    snackbar.setDuration(Snackbar.LENGTH_LONG);
                    snackbar.show();
                }

            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mNextListener = (onFABNextClickListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() +
                    "Must Implement on Next Click of Page Three Listener");
        }
        try {
            mPreviousListener = (onFABPreviousClickListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() +
                    "Must Implement onPageThreePreviousClick listener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mNextListener = null;
        mPreviousListener = null;
    }

    public interface onFABPreviousClickListener{
         void onPageThreePreviousClicked();
    }

    public interface onFABNextClickListener{
        void onPageThreeNextClicked();
    }

}
