package com.example.android.sd.BoltFragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.sd.R;

import static utils.FunctionKit.getSnackBar;

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

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bolt_page_three, container, false);
        mCoordinatorLayout = rootView.findViewById(R.id.BPageThree_CoordinateLayout);

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
                Snackbar snackbar = getSnackBar(mCoordinatorLayout,R.string.welds_string);
                snackbar.show();
                mNextListener.onPageThreeNextClicked();
            }
        });

        // Inflate the layout for this fragment
        if(dataBundle != null){
            setupViews(dataBundle);
        }
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
