package at.fhooe.mc.festly;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import at.fhooe.mc.android.R;


public class CheckInPlayerAdapter extends ArrayAdapter<PlayerData> {

    public String currentPlayer = null;

    @Override
    public PlayerData getItem(int position) {
        return super.getItem(position);
    }

    public CheckInPlayerAdapter(Context context) {
        super(context,-1);

    }

    /**
    @Override
    public int getItemViewType(int position) {
        return IGNORE_ITEM_VIEW_TYPE;
    } */


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        /**
         * checks if there is allready an view
        */
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.checkin_player, null);
        }

        final PlayerData data = (PlayerData) getItem(position);

        Button btn = null;
        btn = (Button) convertView.findViewById(R.id.checkin_player_button);
        btn.setText(data.getName());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPlayer = data.getName();
            }
        });


        return convertView;
    }





}