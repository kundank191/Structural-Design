package com.example.android.sd;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class BoltActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bolt);

        (findViewById(R.id.BPageOne_FABPrevious)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        (findViewById(R.id.BPageOne_FABNext)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar;
                EditText boltGradeText = (EditText) findViewById(R.id.BPageOne_BoltGrade);
                EditText boltDiaText = (EditText) findViewById(R.id.BPageOne_BoltDia);
                if(!TextUtils.isEmpty(boltGradeText.getText())){
                    if(!TextUtils.isEmpty(boltDiaText.getText())){
                        //For now create a snackBar
                        snackbar = getSnackBar((CoordinatorLayout) findViewById(R.id.BPageOne_CoordinateLayout));
                        snackbar.setText("Yatta");
                        snackbar.show();

                    } else {
                        // Message user to insert bolt dia
                        snackbar = getSnackBar((CoordinatorLayout) findViewById(R.id.BPageOne_CoordinateLayout));
                        snackbar.setText(R.string.empty_bolt_dia);
                        snackbar.show();
                    }
                } else {
                    // Message user to insert bolt grade
                    snackbar = getSnackBar((CoordinatorLayout) findViewById(R.id.BPageOne_CoordinateLayout));
                    snackbar.setText(R.string.empty_bolt_grade);
                    snackbar.show();
                }

            }
        });
    }

    private void setupPageTwo(){
        setContentView(R.layout.activity_bolt);
    }

    private Snackbar getSnackBar(CoordinatorLayout mCoordinateLayout){
        Snackbar snackbar = Snackbar.make(mCoordinateLayout, R.string.enter_service_load, Snackbar.LENGTH_SHORT);
        snackbar.getView().setBackgroundColor(Color.WHITE);
        TextView textView = (snackbar.getView()).findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.argb(255, 3, 169, 244));
        return snackbar;
    }

    @Override
    public void onBackPressed() {
        Log.i(getLocalClassName(),"Back Pressed");
    }
}
