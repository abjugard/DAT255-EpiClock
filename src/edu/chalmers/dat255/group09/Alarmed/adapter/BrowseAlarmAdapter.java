package edu.chalmers.dat255.group09.Alarmed.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.TextView;
import edu.chalmers.dat255.group09.Alarmed.R;

public class BrowseAlarmAdapter extends CursorAdapter {

	public BrowseAlarmAdapter(Context context, Cursor cursor) {
		super(context, cursor, false);
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {

		TextView textView = (TextView) view.findViewById(R.id.alarm_time_text);
		CheckBox checkBox = (CheckBox) view.findViewById(R.id.alarm_enabled);
		
		textView.setText(cursor.getString(cursor.getColumnIndex("time")));
		checkBox.setChecked(cursor.getInt(cursor.getColumnIndex("enabled")) > 0);
		checkBox.setTag(cursor.getInt(cursor.getColumnIndex("_id")));

	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {

		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View view = inflater.inflate(R.layout.alarms_list_item, parent, false);

		return view;
	}

}
