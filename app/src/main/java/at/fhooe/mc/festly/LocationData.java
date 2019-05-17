package at.fhooe.mc.festly;

import java.io.Serializable;

import at.fhooe.mc.android.R;


/**
 * class for loaction, here we safe the name, the int bzw name of the picture (its an int because the system safes the drawable as a int
 * and in german breitengrad/längengrad <- for later use
 */
public class LocationData implements Serializable {
    private String mName;
    private int mpic;
    private double mlongitude, mlatitude;
    public boolean malready;


    public LocationData(){
        mName = null;
        mpic = 0;
        mlongitude = 0.0;
        mlatitude = 0.0;
        malready = false;
    }

    /**
     * fist lat, than long thats the standard for writing the location
     * @param _name
     * @param _pic
     * @param _lat
     * @param _long
     */
    public LocationData(String _name, int _pic, double _lat, double _long){
        mName = _name;
        mpic = _pic;
        mlongitude = _long;
        mlatitude = _lat;
        malready = false;
    }


    /**
     * geter Methodes here
     * @return
     */
    public String getmName() {
        return mName;
    }

    public int getMpic() {
        return mpic;
    }

    public double getMlatitude() {
        return mlatitude;
    }

    public double getMlongitude() {
        return mlongitude;
    }

    public boolean isMalready() {
        return malready;
    }

    //End of getter and Setter
}
