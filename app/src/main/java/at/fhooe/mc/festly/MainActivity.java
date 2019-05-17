package at.fhooe.mc.festly;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.LinkedList;

import at.fhooe.mc.android.R;

public class MainActivity extends Activity implements View.OnClickListener {

    public static final String TAG = "";
    protected PlayerAdapter adapter = null;
    protected ListView lv = null;
    protected int maxPlayers = 0; //must be < 4 (8 player total maximum)
    public LinkedList<PlayerData> PlayerList = new LinkedList<PlayerData>();
    public PlayerArray mPlayerArray;


    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Startup Dialog
         */
        Log.i("is:", "OnCreate is done");
        DialogFragment df = new DialogStartUpResponsibilityTest();
        df.show(getFragmentManager(), "done");

        /**
         * ListView Set
         */
        adapter = new PlayerAdapter(this);
        lv = (ListView)findViewById(R.id.activity_main_listview);
        lv.setDividerHeight(0);
        lv.setDivider(null);
        lv.setAdapter(adapter);

        /**
         *  adds 4 basic player - todo check list after add list is corrupt
         */
        adapter.add(new PlayerData(null));
        adapter.add(new PlayerData(null));
        adapter.add(new PlayerData(null));
        adapter.add(new PlayerData(null));

        /**
         * OnClick for add and start button
         */
        ImageButton ib = null;
        Button b = null;
        ib = (ImageButton) findViewById(R.id.activity_main_button_add);
        ib.setOnClickListener(this);
        b = (Button) findViewById(R.id.start_game);
        b.setOnClickListener(this);


    }



    @Override
    public void onClick(View _view) {
        /**
         * cases for pressed button
         */
        switch (_view.getId()) {
            case R.id.activity_main_button_add: {

                /**
                 * the list is limted to 7 player, 3 are created in onreat --> @maxPlayers limit to 3 -> max 7 player
                 */
                if (maxPlayers < 4) {
                    adapter.add(new PlayerData(null));
                    lv.invalidateViews();
                    maxPlayers++;
                } else {
                    /**
                     * Message for Player limit
                     */
                    Toast.makeText(this, R.string.toast_player_overflow, Toast.LENGTH_SHORT).show();
                }
            }
            break;
            case R.id.start_game: {

                PlayerList = new LinkedList<>();

                /**
                 * Button for start, goes throw the list and save the data in a class
                 * for loop, adapter.getCount() limit it
                 * @param editText - in player adapter we save the data between and here we catch it out of the array
                 */
                for(int i = 0; i < adapter.getCount(); i++){

                    String s =
                    adapter.getItem(i).getName();

                    if(s != null) {
                    if(s.compareTo("") != 0) {
                        PlayerData tmp = new PlayerData(s);
                        PlayerList.add(tmp);
                    }
                    }
                }

                /**
                 * starts the next window,
                 */
                if(PlayerList.size() != 0) {

                    /**
                     * convert to an array vrom dll because of the putExtra has to be serial
                     */
                    Object[] tmp = null;
                    tmp = PlayerList.toArray(new Object[PlayerList.size()]);

                    /**
                     * the class where the data will be safed
                     */
                    mPlayerArray = new PlayerArray(tmp);


                    /**
                     * putextra gives classes to the next activity has to be serializeable
                     */
                    Intent i = new Intent(this, ActivityLocation.class);
                    i.putExtra(ActivityLocation.PLAYER_LIST, mPlayerArray);
                    startActivity(i);
                } else {
                    Toast.makeText(this,R.string.activitymain_emptylist, Toast.LENGTH_SHORT).show();
                }
            }
            break;
            default: {
                Log.i(TAG, "unknown id encountered");


            }
        }
    }












}
