package utils;

import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * Created by Kundan on 14-02-2018.
 */

public class FunctionKit {

    /**
     *
     * @param mCoordinateLayout the id of coordinator layout
     * @param stringMessageID the message to be shown in the Snack Bar
     * @return a snack bar with desired message
     */
    public static Snackbar getSnackBar(CoordinatorLayout mCoordinateLayout , int stringMessageID){
        Snackbar snackbar = Snackbar.make(mCoordinateLayout, stringMessageID, Snackbar.LENGTH_SHORT);
        snackbar.getView().setBackgroundColor(Color.WHITE);
        TextView textView = (snackbar.getView()).findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.argb(255, 3, 169, 244));
        return snackbar;
    }

    /**
     *
     * @param string takes in a String value
     * @return the float casted value of string
     */
    public static Float getFloatOf(String string){
        return Float.parseFloat(string);
    }

    /**
     *
     * @param floatValue takes in a String value
     * @return the String casted value of float
     */
    public static String getStringOf(float floatValue){
        return String.valueOf(floatValue);
    }

    /**
     *
     * @param value a String value holding a Float value
     * @return the formatter form of float with only two digs after decimal
     */
    public static String getTwoDecimalValue(String value){
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return String.valueOf(decimalFormat.format(getFloatOf(value)));
    }

}
