package at.fhooe.mc.festly;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.LocaleList;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.LinkedList;

import at.fhooe.mc.android.R;

public class ActivityLocation extends Activity implements View.OnClickListener{

    /**
     * the String for the playerlist, we need it to find der array
     */
    public static final String PLAYER_LIST = "at.fhooe.mc.festly.PLAYER_LIST";
    /**
     * List for the locations which get greatet, store in array beacause of serializeable
     */
    public LocationList locationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        Button b = null;
        b = (Button) findViewById(R.id.activitylocation_button_linz);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.activitylocation_button_wien);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.activitylocation_button_graz);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.activitylocation_button_preparty);
        b.setOnClickListener(this);
    }

    /**
     * to get the lat and long -> https://support.google.com/maps/answer/18539?co=GENIE.Platform%3DDesktop&hl=de
     * todo -> i think there is a way to safe the data in an xml and read it from there
     * @param v
     */
    @Override
    public void onClick(View v) {
        /**
         * Catch the string and safe the PlayerList
         */
        Intent i = getIntent();
        PlayerArray playerArray = (PlayerArray) i.getSerializableExtra(PLAYER_LIST);
        i = new Intent(this, ActivityBar.class);

        switch(v.getId()){
            case R.id.activitylocation_button_graz : {
                locationList = new LocationList(3);

                locationList.add(new LocationData("The Office Pub", 1, 47.070966, 15.443013));

                i.putExtra(ActivityBar.BAR_LIST, locationList);
                i.putExtra(ActivityBar.PLAYER_LIST, playerArray);
                i.putExtra(ActivityBar.MODE, false);


                startActivity(i);
            } break;
            case R.id.activitylocation_button_linz : {
                locationList = new LocationList(1);

                locationList.add(new LocationData("Remembar", R.drawable.location_linz_remembar, 48.303138, 14.290250));
                locationList.add(new LocationData("Musikpark A1 Linz", R.drawable.location_linz_a1, 48.292146, 14.302604));
                locationList.add(new LocationData("Chaplin's Bar",R.drawable.location_linz_chaplins,48.305886, 14.284614));
                locationList.add(new LocationData("SEGABAR", R.drawable.location_linz_segabar, 48.305808, 14.284963));
                locationList.add(new LocationData("Frau Dietrich", R.drawable.location_linz_dietrich, 48.305478, 14.284231));
                locationList.add(new LocationData("SANDBURG", R.drawable.location_linz_sandburg, 48.310387, 14.291897));


                /**
                 * Classes for the next activity
                 */
                i.putExtra(ActivityBar.BAR_LIST, locationList);
                i.putExtra(ActivityBar.PLAYER_LIST, playerArray);
                i.putExtra(ActivityBar.MODE, false);


                startActivity(i);
            } break;
            case R.id.activitylocation_button_wien : {
                locationList = new LocationList(2);

                locationList.add(new LocationData("Praterstern", 1, 48.217290, 16.397549));

                i.putExtra(ActivityBar.BAR_LIST, locationList);
                i.putExtra(ActivityBar.PLAYER_LIST, playerArray);
                i.putExtra(ActivityBar.MODE, false);


                startActivity(i);
            } break;
            case R.id.activitylocation_button_preparty : {
                locationList = new LocationList(4);
                String preparty = getString(R.string.activitylocation_preparty);
                locationList.add(new LocationData(preparty, 1, 1, 1));

                i = new Intent(this, ActivityBar.class);
                i.putExtra(ActivityBar.PLAYER_LIST, playerArray);
                i.putExtra(ActivityBar.BAR_LIST, locationList);
                i.putExtra(ActivityBar.MODE, true);

                startActivity(i);
            } break;
            default: {
                Log.e(MainActivity.TAG, "No Button");
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        locationList = null;
    }
}
