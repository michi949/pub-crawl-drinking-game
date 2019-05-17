package at.fhooe.mc.festly;

import java.io.Serializable;

public class LocationList implements Serializable {

    protected  int cityId = 0;
    protected int counter = 0;
    public LocationData[] array = new LocationData[8];


    /**
     * Constructor with the city id
     * @param _cityId
     */
    public LocationList(int _cityId){
     this.cityId = _cityId;
    }

    /**
     * counter++ when a location has added
     * @param _data
     */
    public void add(LocationData _data) {
        array[counter] = _data; counter++;
    }

    public LocationData returnLocation(){
       int rnd = (int)Math.random()*3;
       return array[rnd];
    }

    public int getID(LocationData locationData) {
        int id = Integer.MIN_VALUE;
        for (int r = 0; r < this.counter; r++) {
            if(this.array[r].getmName().compareTo(locationData.getmName()) == 0)
                id = r;
        }
        return id;
    }
}
