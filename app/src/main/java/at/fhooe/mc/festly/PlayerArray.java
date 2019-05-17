package at.fhooe.mc.festly;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;

public class PlayerArray implements Serializable{
    public PlayerData[] array = null;

    PlayerArray(Object[] _data){
        this.array = new PlayerData[_data.length];

        for (int i = 0; i < array.length; i++){
            array[i] = (PlayerData) _data[i];
        }
    }

    /**
     * this method clones the current playerArray and return a sorted one
     * ONLY CALLED BY METHOD playerRank() !!!
     * @return sorted playerArray (descending)
     */
    public PlayerData[] sort(){
        PlayerData[] sortedArray = array.clone();
        Arrays.sort(sortedArray, new Comparator<PlayerData>() {
            @Override
            public int compare(PlayerData playerData, PlayerData t1) {
                return  t1.getmSaufCount() - playerData.getmSaufCount();
            }
        });
        return sortedArray;
    }

    /**
     * this method calls sort() and return the current leaderboard ranking of a specific Player
     * @param _data has to be PlayerData, not Array-Index
     * @return return the rank on the leaderboard
     */
    public int playerRank (PlayerData _data){
        PlayerData[] sortedArray = this.sort();
        for(int i = 0; i < this.array.length;i++) {
            if (sortedArray[i].getName().compareTo(_data.getName()) == 0) {
                return i+1; // +1, because array index starts at 0 and the leader should be 1 not 0
            }
        }
        return Integer.MIN_VALUE;
    }

    public int getId(PlayerData _data){
        int id = Integer.MIN_VALUE;
        for (int r = 0; r < this.array.length; r++){
            if(this.array[r].getName().compareTo(_data.getName()) == 0){
                id = r;
            }
        }
        return id;
    }

}
