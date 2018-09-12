package com.example.android.sd.ui.steel;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.android.sd.R;
import com.example.android.sd.ui.steel.connection.bolt.BoltActivity;
import com.example.android.sd.ui.steel.connection.weld.WeldActivity;
import com.example.android.sd.ui.steel.members.compression.CompressionActivity;
import com.example.android.sd.ui.steel.utils.Variables;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.android.sd.ui.steel.utils.FunctionKit.getSnackBar;

public class SteelDesignActivity extends AppCompatActivity {

    @BindView(R.id.firstPage_CoordinateLayout)
    CoordinatorLayout mCoordinateLayout;
    @BindView(R.id.firstPage_ServiceLoad)
    EditText serviceLoadEditText;
    @BindView(R.id.firstPage_TC_radio)
    RadioGroup RG_TensionCompression;
    @BindView(R.id.firstPage_Tension_Radio)
    RadioGroup RG_TensionOptions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steel_design);

        ButterKnife.bind(this);

        final boolean[] isTension = {true};
        RG_TensionCompression.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id){
                    case R.id.firstPage_Tension:
                        isTension[0] = true;
                        RG_TensionOptions.setVisibility(View.VISIBLE);
                        break;
                    case R.id.firstPage_Compression:
                        isTension[0] = false;
                        RG_TensionOptions.setVisibility(View.GONE);
                        break;
                }
            }
        });
        FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.firstPage_FAB);
        myFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(serviceLoadEditText.getText())){
                    Intent intent;
                    if (isTension[0]) {
                        if (isBolted()) {
                            intent = new Intent(getBaseContext(), BoltActivity.class);
                        } else {
                            intent = new Intent(getBaseContext(), WeldActivity.class);
                        }
                    } else {
                        intent = new Intent(getBaseContext(), CompressionActivity.class);
                    }
                    intent.putExtra(Variables.serviceLoad, serviceLoadEditText.getText().toString());
                    startActivity(intent);
                } else {
                    Snackbar snackBar = getSnackBar(mCoordinateLayout,R.string.enter_service_load);
                    snackBar.show();
                }
            }
        });
    }

    private boolean isBolted(){
        RadioButton bolt = (RadioButton) findViewById(R.id.firstPage_Bolted);
        return bolt.isChecked();
    }
}
