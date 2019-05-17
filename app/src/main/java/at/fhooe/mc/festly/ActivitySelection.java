package at.fhooe.mc.festly;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import at.fhooe.mc.android.R;

public class ActivitySelection extends Activity implements View.OnClickListener {

    //variable just for testing purpose
    public static final String PLAYER_LIST = "PLAYER_LIST";
    public static final String PLAYER_INDEX = "PLAYER_INDEX";
    int testSaufcount = 0;
    int rank;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        Button b = null;
        b = (Button) findViewById(R.id.button_beer1);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.button_beer2);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.button_colarum);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.button_colawhisky);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.button_jagermeister);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.button_tequilashot);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.button_vodkaorange);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.button_vodkabull);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.button_wein);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.button_vodkashot);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.button_captaincola);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.button_cocktail);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.button_gintonic);
        b.setOnClickListener(this);
        b = (Button) findViewById(R.id.button_longisland);
        b.setOnClickListener(this);

    }

    // TODO: 24.05.2018 Intent to return back to ActivityDrinking
    @Override
    public void onClick(View _view) {

        //get playerArray
        Intent i = getIntent();
        PlayerArray playerArray = (PlayerArray) i.getSerializableExtra(PLAYER_LIST);
        int playerId = (int) i.getSerializableExtra(PLAYER_INDEX);

        //get PlayerData to update
        PlayerData chosendata = playerArray.array[playerId];
        String testPlayer = chosendata.getName();
        int drinkval = 0;

        switch (_view.getId()) {
            case R.id.button_beer1: {
                drinkval = 3;
                chosendata.increasemSaufCount(drinkval);
            }
            break;
            case R.id.button_beer2: {
                drinkval = 1;
                chosendata.increasemSaufCount(drinkval);
            }
            break;
            case R.id.button_vodkabull: {
                drinkval = 2;
                chosendata.increasemSaufCount(drinkval);
            }
            break;
            case R.id.button_vodkaorange: {
                drinkval = 2;
                chosendata.increasemSaufCount(drinkval);
            }
            break;
            case R.id.button_jagermeister : {
                drinkval = 3;
                chosendata.increasemSaufCount(drinkval);
            } break;
            case R.id.button_colawhisky : {
                drinkval = 2;
                chosendata.increasemSaufCount(drinkval);
            } break;
            case R.id.button_tequilashot : {
                drinkval = 3;
                chosendata.increasemSaufCount(drinkval);
            } break;
            case R.id.button_wein : {
                drinkval = 1;
                chosendata.increasemSaufCount(drinkval);
            } break;
            case R.id.button_vodkashot : {
                drinkval = 2;
                chosendata.increasemSaufCount(drinkval);
            } break;
            case R.id.button_captaincola : {
                drinkval = 2;
                chosendata.increasemSaufCount(drinkval);
            } break;
            case R.id.button_colarum : {
                drinkval = 2;
                chosendata.increasemSaufCount(drinkval);
            } break;
            case R.id.button_cocktail : {
                drinkval = 2;
                chosendata.increasemSaufCount(drinkval);
            } break;
            case R.id.button_gintonic : {
                drinkval = 2;
                chosendata.increasemSaufCount(drinkval);
            } break;
            case R.id.button_longisland : {
                drinkval = 6;
                chosendata.increasemSaufCount(drinkval);
            } break;
            default:

                Log.i("...", "unknown id encountered");
        }


        Toast.makeText(this,testPlayer + " scored " + drinkval + " points" + "\n" + "Saufcount: " + playerArray.array[playerId].getmSaufCount()
                + "\n" + "Current Rank: " + (playerArray.playerRank(chosendata)), Toast.LENGTH_SHORT).show();

        /**
         * returns updated version of playerArray and kills the activity
         */
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",playerArray);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
}
