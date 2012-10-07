package edu.chalmers.dat255.group09.Alarmed.adapter;

import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import edu.chalmers.dat255.group09.Alarmed.R;
import edu.chalmers.dat255.group09.Alarmed.model.Alarm;

public class BrowseAlarmAdapter extends ArrayAdapter<Alarm> {

	public BrowseAlarmAdapter(Context context, int textViewResourceId,
			List<Alarm> objects) {
		super(context, textViewResourceId, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(parent.getContext());
			view = inflater.inflate(R.layout.alarms_list_item, parent, false);
		}
		TextView textView = (TextView) view.findViewById(R.id.alarm_time_text);
		CheckBox checkBox = (CheckBox) view.findViewById(R.id.alarm_enabled);

		textView.setText(getItem(position).getAlarmHours() + ":"
				+ getItem(position).getAlarmMinutes());
		checkBox.setChecked(getItem(position).isEnabled());
		checkBox.setTag(getItem(position).getId());

		return view;
	}

	public void updateList(List<Alarm> list) {
		clear();
		for (Alarm a : list) {
			add(a);
		}
		notifyDataSetChanged();
	}

}
