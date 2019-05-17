package at.fhooe.mc.festly;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import at.fhooe.mc.android.R;

public class ActivityDrinking extends Activity implements View.OnClickListener {

    public static final String PLAYER_LIST = "PLAYER_LIST";
    public static final String BAR_ID = "BAR_ID";
    public static final String BAR_LIST = "BAR_LIST";
    public PlayerArray playerArray;
    public LocationList locationList;
    public LocationData locationData;
    public int barid;
    protected CheckInPlayerAdapter adapter = null;
    protected ListView lv = null;
    CountDownTimer timer = null;
   private int currentPlayer = Integer.MIN_VALUE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinking);

        Intent i = getIntent();
        playerArray = (PlayerArray) i.getSerializableExtra(PLAYER_LIST);
        locationList = (LocationList) i.getSerializableExtra(BAR_LIST);
        barid = (int)i.getSerializableExtra(BAR_ID);
        locationData = locationList.array[barid];

        TextView tv = null;
        tv = (TextView) findViewById(R.id.activity_drinking_current_bar);
        tv.setText(locationData.getmName());

        /**
         * Countdown down here
         */
        final TextView mTextField = (TextView) findViewById(R.id.activity_drinking_countdown);
        int randomtime = 0;
        while ((randomtime < 30) || (randomtime > 60)) {
            randomtime = (int) (Math.random() * 60); //Math.random gives a random number between 0 and 60
        }
        randomtime = randomtime * 60000; //minutes converted to milliseconds

        final int finalRandomtime = 70000; //<-- Orginal Countdown just change for test
        timer = new CountDownTimer(finalRandomtime, 1000) {


            long minutes = finalRandomtime / 60000;
            int seconds = (finalRandomtime % 60000)/1000;


            public void onTick(long millisUntilFinished) {
                if (seconds == 0) { //jump from 0s to 59s
                    seconds = 59;
                    minutes--; //manual decrementation of minutes
                } else {
                    seconds--;
                }
                mTextField.setText(minutes + " minutes   |   " + seconds + " seconds");
            }

            public void onFinish() {
                mTextField.setText(R.string.activitydrinking_timeover);

                locationList.array[barid].malready = true;

                /**
                 * checks if is some round over
                 * if there is an other unused location it will set the boolean to flase else it will let it at true and quit the game
                 */
                boolean isalready = true;
                for(int r = 0; r < locationList.counter; r++) {
                    if(locationList.array[r].malready == false){
                        isalready = false;
                    }
                }

                if(isalready == true){

                    Intent i = new Intent(ActivityDrinking.this,ActivityFinish.class);
                    i.putExtra(ActivityFinish.PLAYER_LIST,playerArray);
                    i.putExtra(ActivityFinish.IS_FINISH, true);
                    startActivity(i);

                }else{
                    Intent i = new Intent(ActivityDrinking.this, ActivityBar.class);
                    i.putExtra(ActivityBar.BAR_LIST, locationList);
                    i.putExtra(ActivityBar.PLAYER_LIST, playerArray);
                    i.putExtra(ActivityBar.MODE, false);
                    startActivity(i);
                }

            }
        }.start();


        /**
         * ListView down here
         */
//        adapter = new CheckInPlayerAdapter(this);
//        lv = (ListView)findViewById(R.id.activity_drinking_lv_player);
//
//        for(int r = 0; r < playerArray.array.length; r++){
//            adapter.add(playerArray.array[r]);
//        }
//
//        lv.setAdapter(adapter);

        /**
         * Work Arround till its fixed
         */
        int arrayLength = playerArray.array.length;
        int arrayCounter = 0;
        Button btn = null;

        btn = (Button) findViewById(R.id.playerbutton_1);
        if(arrayCounter == arrayLength){  btn.setVisibility(View.INVISIBLE);}
        else{btn.setText((CharSequence) playerArray.array[0].getName());
        btn.setOnClickListener(this);
        arrayCounter++; }

        btn = (Button) findViewById(R.id.playerbutton_2);
        if(arrayCounter == arrayLength){  btn.setVisibility(View.INVISIBLE);}
        else{ btn.setText((CharSequence) playerArray.array[1].getName());
        btn.setOnClickListener(this);
        arrayCounter++;}

        btn = (Button) findViewById(R.id.playerbutton_3);
        if(arrayCounter == arrayLength){  btn.setVisibility(View.INVISIBLE);}
        else {btn.setText((CharSequence) playerArray.array[2].getName());
        btn.setOnClickListener(this);
        arrayCounter++;}

        btn = (Button) findViewById(R.id.playerbutton_4);
        if(arrayCounter == arrayLength){  btn.setVisibility(View.INVISIBLE);}
        else { btn.setText((CharSequence) playerArray.array[3].getName());
        btn.setOnClickListener(this);
        arrayCounter++;}

        btn = (Button) findViewById(R.id.playerbutton_5);
        if(arrayCounter == arrayLength){  btn.setVisibility(View.INVISIBLE);}
        else {btn.setText((CharSequence) playerArray.array[4].getName());
        btn.setOnClickListener(this);
        arrayCounter++; }

        btn = (Button) findViewById(R.id.playerbutton_6);
        if(arrayCounter == arrayLength){  btn.setVisibility(View.INVISIBLE);}
        else {btn.setText((CharSequence) playerArray.array[5].getName());
        btn.setOnClickListener(this);
        arrayCounter++; }

        btn = (Button) findViewById(R.id.playerbutton_7);
        if(arrayCounter == arrayLength){  btn.setVisibility(View.INVISIBLE);}
        else {btn.setText((CharSequence) playerArray.array[6].getName());
        btn.setOnClickListener(this);
        arrayCounter++; }

        btn = (Button) findViewById(R.id.playerbutton_8);
        if(arrayCounter == arrayLength){  btn.setVisibility(View.INVISIBLE);}
        else {btn.setText((CharSequence) playerArray.array[7].getName());
        btn.setOnClickListener(this);
        arrayCounter++; }

        btn = findViewById(R.id.activity_drinking_button_showloaderboard);
        btn.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        int avoidIntent = 0;
        switch (v.getId()) {
            case R.id.playerbutton_1: {
                currentPlayer = 0;
            }break;
            case R.id.playerbutton_2:{
                currentPlayer = 1;
            }break;
            case R.id.playerbutton_3: {
                currentPlayer = 2;
            }break;
            case R.id.playerbutton_4: {
                currentPlayer = 3;
            }break;
            case R.id.playerbutton_5: {
                currentPlayer = 4;
            }break;
            case R.id.playerbutton_6: {
                currentPlayer = 5;
            }break;
            case R.id.playerbutton_7: {
                currentPlayer = 6;
            }break;
            case R.id.playerbutton_8: {
                currentPlayer = 7;
            }break;
            case R.id.activity_drinking_button_showloaderboard: {
                Intent i = new Intent(this, ActivityFinish.class);
                i.putExtra(ActivityFinish.PLAYER_LIST, playerArray);
                i.putExtra(ActivityFinish.IS_FINISH, false);
                startActivity(i);
                avoidIntent = 1;
            }
        }

        //int avoidintent: when pressing "button show leaderboard", don't call ActivitySelection
        if(avoidIntent != 1) {
            Intent i = new Intent(ActivityDrinking.this, ActivitySelection.class);
            i.putExtra(ActivitySelection.PLAYER_LIST, playerArray);
            i.putExtra(ActivitySelection.PLAYER_INDEX, currentPlayer);
            startActivityForResult(i, 1);
        }
    }



    /**
     * method receives the updated player array after drink was selected
     * @param requestCode irrelevant
     * @param resultCode irrelevant
     * @param data contains intent which contains playerArray
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == 0) { return; }
        playerArray = (PlayerArray) data.getSerializableExtra("result");
    }

    @Override
    public void onBackPressed() {}


//    /**
//     * this schould be the used onitemclickmethode
//     * todo at the momeant it isnt get called, dont know why
//     * @param parent
//     * @param view
//     * @param position
//     * @param id
//     */
//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        CheckInPlayerAdapter adapter = (CheckInPlayerAdapter) parent.getAdapter();
//        PlayerData data = (PlayerData) adapter.getItem(position);
//        Toast.makeText(this, "Fucking", Toast.LENGTH_SHORT).show();
//
//    }
}

