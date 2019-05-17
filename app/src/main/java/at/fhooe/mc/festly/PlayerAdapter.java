package at.fhooe.mc.festly;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import at.fhooe.mc.android.R;

public class PlayerAdapter extends ArrayAdapter<PlayerData> {

    /**
     * Constructor for the adapter
     * @param context
     */
    public PlayerAdapter(Context context) {

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
            convertView = inflater.inflate(R.layout.insert_name, null);
        }
        final PlayerData data = getItem(position);

        if (data != null && data.getName() != "") {
            EditText editText = null;
            editText = (EditText) convertView.findViewById(R.id.insert_plain_text);
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    String name = s.toString();
                    if (name != "")
                        data.setName(name);
                }
            });
            editText.setText(data.getName());
        }
        return convertView;
    }
}
