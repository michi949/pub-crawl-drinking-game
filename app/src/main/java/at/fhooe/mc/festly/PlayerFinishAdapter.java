package at.fhooe.mc.festly;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import at.fhooe.mc.android.R;

public class PlayerFinishAdapter extends ArrayAdapter<PlayerData> {

    /**
     * Constructor for the adapter
     * @param context
     */
    public PlayerFinishAdapter(Context context) {

        super(context,-1);
    }

    /**
     * make sure thaht everthing is there where it should be
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return IGNORE_ITEM_VIEW_TYPE;
    }

    /**
     * i fucking don´t know why this method is get called but it get called
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        /**
         * checks if there is allready an view
         */
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.finish_playerlist, null);
        }
        final PlayerData data = getItem(position);

        TextView tv = null;
        tv = (TextView) convertView.findViewById(R.id.finishplayerlist_name);
        tv.setText(data.getName());
        tv = (TextView) convertView.findViewById(R.id.finishplayerlist_points);
        tv.setText("" + data.getmSaufCount());
        tv = (TextView) convertView.findViewById(R.id.finishplayerlist_number);
        int pos = this.getPosition(data) + 1;
        tv.setText("" + pos);

        return convertView;
    }
}