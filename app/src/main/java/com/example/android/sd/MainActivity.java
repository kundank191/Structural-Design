package com.example.android.sd;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import utils.Variables;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CoordinatorLayout mCoordinateLayout = (CoordinatorLayout) findViewById(R.id.firstPage_CoordinateLayout);
        final EditText serviceLoadEditText = (EditText) findViewById(R.id.firstPage_ServiceLoad);

        FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.firstPage_FAB);
        myFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(serviceLoadEditText.getText())){
                    Intent intent;
                    if(isBolted()) {
                        intent = new Intent(getBaseContext(), BoltActivity.class);
                    } else {
                        intent = new Intent(getBaseContext(), WeldActivity.class);
                    }
                    intent.putExtra(Variables.serviceLoad,serviceLoadEditText.getText().toString());
                    startActivity(intent);
                } else {
                    Snackbar snackbar = Snackbar.make(mCoordinateLayout, R.string.enter_service_load, Snackbar.LENGTH_SHORT);
                    snackbar.getView().setBackgroundColor(Color.WHITE);
                    TextView textView = (snackbar.getView()).findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.argb(255, 3, 169, 244));
                    snackbar.show();
                }
            }
        });
    }

    private boolean isBolted(){
        RadioButton bolt = (RadioButton) findViewById(R.id.firstPage_Bolted);
        return bolt.isChecked();
    }
}
