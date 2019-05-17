package at.fhooe.mc.festly;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.Locale;

import at.fhooe.mc.android.R;

public class ActivityBar extends Activity implements View.OnClickListener{

    public static final String BAR_LIST = "BAR_LIST";
    public static final String PLAYER_LIST = "PLAYER_LIST";
    public static final String MODE = "MODE";
    boolean mode = false;
    LocationData locationData;
    PlayerArray playerArray;
    LocationList locationList;

    // TODO: 24.05.2018 Math.random currentyl set to 1 for testing purpose 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);

        /**
         * get the array of the locations back
         * and looks for an random bar to choose
         *
         */
        Intent i = getIntent();
        locationList = (LocationList)i.getSerializableExtra(BAR_LIST);
        playerArray = (PlayerArray) i.getSerializableExtra(PLAYER_LIST);
        mode = (boolean) i.getSerializableExtra(MODE);
        int rnd = (int)(Math.random()*locationList.counter);

        /**
         * checking if the bar was used already
         * todo: we can set a limit of times it should go through the loop, but it shouldnt come to this part because we catch it later
         */
        boolean isused = true;
        while(isused == true) {
            locationData = locationList.array[rnd];
            if (locationData.isMalready() == false){
                isused = false;
            } else {
                rnd = (int)(Math.random()*locationList.counter);
            }
        }

        //Toast.makeText(this, "Location A --> " + locationData.getmName(), Toast.LENGTH_SHORT).show();

        TextView tv = (TextView) findViewById(R.id.activity_bar_textview_barname);
        tv.setText(locationData.getmName());
        ImageView iv = (ImageView) findViewById(R.id.bar_activity_image_bar);
        iv.setImageResource(locationData.getMpic());


        Button b = null;
        b = (Button) findViewById(R.id.start_activity_drinking);
        b.setOnClickListener(this);


        b = (Button) findViewById(R.id.bar_activity_showway);
        if (mode == true) {
            b.setVisibility(View.GONE);
        } else if(mode == false) {
            b.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View _view) {
        Intent i = new Intent(this, ActivityDrinking.class);

        switch (_view.getId()) {
            case R.id.start_activity_drinking : {


                i.putExtra(ActivityDrinking.PLAYER_LIST, playerArray);
                i.putExtra(ActivityDrinking.BAR_ID, locationList.getID(locationData));
                i.putExtra(ActivityDrinking.BAR_LIST, locationList);
                startActivity(i);
            }break;
            case R.id.bar_activity_showway : {


                /**
                 * with the uri we open the google map
                 */
                String uri = "http://maps.google.com/maps?saddr=" + "&daddr=" + locationData.getMlatitude() + "," + locationData.getMlongitude() + " (" + "Where the party is at" + ")";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                intent.setPackage("com.google.android.apps.maps");
                startActivity(intent);

            } break;
            default :
                Log.i("...", "unknown id encountered");
        }

    }

    //kills ActivityBar in order not to crash when pressing back
    @Override
    public void onBackPressed() {
        finish();
    }
}
