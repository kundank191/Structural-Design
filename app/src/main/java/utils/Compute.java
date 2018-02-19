package utils;

import static utils.FunctionKit.getFloatOf;

/**
 * Created by Kundan on 15-02-2018.
 * all computations of expressions is done here
 */

public class Compute {

    public static String BoltValue(String boltDia, String endDistance, String pitch,
                            String Ymb, String n, String fu, String fub, String T){
        String kb = String.valueOf(Math.min(Math.min(getFloatOf(endDistance)/(3*getFloatOf(boltDia))
                                                        ,getFloatOf(pitch)/(3*getFloatOf(boltDia)) - 0.25)
                                            ,Math.min(getFloatOf(fub)/getFloatOf(fu)
                                                        ,1)));
        String Vdpb = String.valueOf(2.5*getFloatOf(kb)*getFloatOf(boltDia)*getFloatOf(T)/getFloatOf(Ymb));
        String Vdsb = String.valueOf(getFloatOf(fu)*getFloatOf(n)*0.78*22*getFloatOf(boltDia)*getFloatOf(boltDia)/(1000*4*7*1.732*getFloatOf(Ymb)));
        return String.valueOf(Math.min(getFloatOf(Vdpb),getFloatOf(Vdsb)));

    }

    public static String AreaAn(String FactoredLoad_Pu_Tdn,String fu){
        String area = String.valueOf(getFloatOf(FactoredLoad_Pu_Tdn)*1.25*1000/(0.8*getFloatOf(fu)));
        return area;
    }

    /**
     *
     * @param factoredLoad the load that the bolted connection has to carry
     * @param boltValue strength of one bolt
     * @return returns the number of bolts required for the job
     */
    public static String NumberOfBolts(String factoredLoad,String boltValue){
        String generalNumber = String.valueOf(Math.ceil(getFloatOf(factoredLoad)/getFloatOf(boltValue)));
        String number = "";
        if(getFloatOf(generalNumber) <= 3){
            return "3";
        }
        if (getFloatOf(generalNumber)%2 == 0){
            number =  generalNumber;
        } else {
            number = String.valueOf(getFloatOf(generalNumber) + 1);
        }
        return number.substring(0,number.length()-2);

    }

    public static String getClearance(String boltDia){
        if(getFloatOf(boltDia) < 16)
            return "1";
        if(getFloatOf(boltDia) < 24)
            return "2";
        return "3";
    }

    public static String getLengthOfConnection(String numberOfBolts, String numberOfRows, String pitch){
        return String.valueOf(getFloatOf(pitch)*(getFloatOf(numberOfBolts)/getFloatOf(numberOfRows) - 1));
    }

    public static String getLengthBS(String section_l, String section_a, String section_t){
        return String.valueOf(getFloatOf(section_l) + getFloatOf(section_a) - getFloatOf(section_t));
    }

    public static String getValueB(String section_l, String section_t, String length_BS, String length_LC
                                            ,String Strength_fy, String Strength_fu){
        return String.valueOf(1.4 - .076
                                        *(getFloatOf(section_l)/getFloatOf(section_t))
                                        *(getFloatOf(Strength_fy)/getFloatOf(Strength_fu))
                                        *(getFloatOf(length_BS)/getFloatOf(length_LC)));
    }

    public static String getAreaAnc(String section_l, String section_t, String boltDia, String clearance){
        return String.valueOf((getFloatOf(section_l) - getFloatOf(section_t)/2)*getFloatOf(section_l)
                                        - (getFloatOf(boltDia) + getFloatOf(clearance))*getFloatOf(section_l)*2);
    }

    public static String getAreaAgo(String section_h, String section_t){
        return String.valueOf((getFloatOf(section_h) - getFloatOf(section_t))*getFloatOf(section_t));
    }

    public static String getStrengthTdg(String Strength_fy,String Area_Ag, String factor_of_safety_Ymo){
        return String.valueOf(getFloatOf(Strength_fy) * getFloatOf(Area_Ag) / getFloatOf(factor_of_safety_Ymo));
    }

    public static String getStrengthTdn(String Area_Anc, String Strength_fu, String factor_of_Safety_Ym1
                                            ,String value_B, String strength_fy, String Area_Ago
                                            , String factor_of_safety_Ymo){
        return String.valueOf(0.9*getFloatOf(Area_Anc)*getFloatOf(Strength_fu)/getFloatOf(factor_of_Safety_Ym1)
                                + getFloatOf(value_B)*getFloatOf(strength_fy)*getFloatOf(Area_Ago)/getFloatOf(factor_of_safety_Ymo));
    }

    public static String getNoOfRows(String NoOfBolts, String l){
        if(getFloatOf(NoOfBolts) <= 3){
            return "1";
        }
        if(getFloatOf(l) <= 100)
        {
            return "1";
        }
        return "2";
    }

    public static String getSlendernessRatio(String section_MI, String effectiveLength, String Ag){
        return String.valueOf(getFloatOf(effectiveLength)/Math.sqrt(getFloatOf(section_MI)/getFloatOf(Ag)));
    }

    //TODO : Implement Tdb
    //TODO : Implement Avg
    //TODO : Implement Atg
    //TODO : Implement Atn
    //TODO : Find minimum of Tdg , Tdn , Tdb
    /**
     *
     * @param pitch current pitch value
     * @param boltDia the diameter of the bolt selected
     * @return if value of pitch can be changed then it will return true
     */
    public static boolean pitchCanChange(String pitch, String T, String boltDia, boolean plusClicked){
        if(plusClicked){
            pitch = String.valueOf(getFloatOf(pitch) + 1);
            return getFloatOf(pitch) <= (Math.min(getFloatOf("200"),getFloatOf(T) * 16));
        } else {
            pitch = String.valueOf(getFloatOf(pitch) - 1);
            return getFloatOf(pitch) >= (getFloatOf(boltDia) * 2.5);
        }

    }

    /**
     *
     * @param endDistance get current endDistance Value
     * @param boltDia get the bolt diameter
     * @param plusClicked to check if plus or minus button was clicked
     * @return true if the change in the end distance is possible
     */
    public static boolean endDistanceCanChange(String endDistance,String T, String boltDia, boolean plusClicked){
        if(plusClicked){
            endDistance = String.valueOf(getFloatOf(endDistance) + 1);
            return getFloatOf(endDistance) <= (getFloatOf(T)*4 + 10);
        } else {
            endDistance = String.valueOf(getFloatOf(endDistance) - 1);
            return getFloatOf(endDistance) >= (getFloatOf(boltDia) * 1.5);
        }

    }
}
