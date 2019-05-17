package at.fhooe.mc.festly;

import android.widget.EditText;

import java.io.Serializable;

import at.fhooe.mc.android.R;

/**
 * has to be serializable because of overgiving to the other activities with intent
 * safes data for each player
 */
public class PlayerData implements Serializable  {

    private String mName;
    public int mSaufCount;


    public PlayerData(String _name){
        mName = _name;
        mSaufCount = 0;
    }

    public String getName(){
        return mName;
    }

    public void setName(String _name){
        mName = _name;
    }

    public int getmSaufCount() {
        return mSaufCount;
    }

    public void increasemSaufCount(int _val) {this.mSaufCount += _val;}



}
