package utils;

import android.arch.lifecycle.ViewModel;

/**
 * Created by Kundan on 16-02-2018.
 */

public class ViewModelBoltActivity extends ViewModel {

    private String Service_Load = Variables.defaultValue;
    private String Factored_Load = Variables.defaultValue;
    private String Bolt_value = Variables.defaultValue;
    private String No_Of_Bolts = Variables.defaultValue;
    private String Grade_Of_Bolts = Variables.defaultValue;
    private String Dia_Of_Bolts = Variables.defaultValue;
    private String End_Distance = Variables.defaultValue;
    private String Pitch_Distance = Variables.defaultValue;
    private String ConnectionLength_Lc = Variables.defaultValue;
    private String Bolt_Strength = Variables.defaultValue;
    private String Area_An = Variables.defaultValue;
    private String Area_Ag = Variables.defaultValue;
    private String Area_Anc = Variables.defaultValue;
    private String Area_Ago = Variables.defaultValue;
    private String Section_l = Variables.defaultValue;
    private String Section_h = Variables.defaultValue;
    private String Section_t = Variables.defaultValue;
    private String Section_a = Variables.defaultValue;
    private String Section_b = Variables.defaultValue;
    private String Section_c = Variables.defaultValue;
    private String Section_MI = Variables.defaultValue;
    private String SR = Variables.defaultValue;
    private String Ultimate_Load_fu = "400";
    private String Number_Of_Shear_Planes_n = "1";
    private String Factor_Of_Safety_Ymb = "1.25";

    public void setService_Load(String service_load){
        Service_Load = service_load;
    }
    public String getService_Load(){
        return Service_Load;
    }

    public void setFactored_Load(String service_load){
        Factored_Load = service_load;
    }
    public String getFactored_Load(){
        return Factored_Load;
    }

}
