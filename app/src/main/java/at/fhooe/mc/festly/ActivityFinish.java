package at.fhooe.mc.festly;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import at.fhooe.mc.android.R;

public class ActivityFinish extends Activity {
    public static final String PLAYER_LIST = "PLAYER_LIST";
    public static final String IS_FINISH = "IS_FINISH";
    private Boolean isFinish;
    public PlayerArray playerArray;
    protected ListView lv;
    public PlayerFinishAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        Intent i = getIntent();
        playerArray = (PlayerArray) i.getSerializableExtra(PLAYER_LIST);
        isFinish = (boolean) i.getSerializableExtra(IS_FINISH);
        playerArray.array = playerArray.sort();

        if(isFinish != true){
            TextView tv = null;
            tv = (TextView) findViewById(R.id.activity_finisher_header);
            tv.setText(R.string.activityfinish_currentleader);
        }

        lv = (ListView) findViewById(R.id.activity_finish_player_list);
        adapter = new PlayerFinishAdapter(this);

        lv.setAdapter(adapter);

        for(int r = 0; r < playerArray.array.length; r++){
            adapter.add(playerArray.array[r]);
        }

    }


    /**
     * Finish the intent if the game is still running but when its over then it shouldnt get kill
     */
    @Override
    public void onBackPressed() {

        if(isFinish != true){
            finish();
        }

    }
}

