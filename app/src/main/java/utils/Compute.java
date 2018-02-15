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
}
