package edu.chalmers.dat255.group09.Alarmed.adapter;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import edu.chalmers.dat255.group09.Alarmed.R;
import edu.chalmers.dat255.group09.Alarmed.model.AlarmTime;

public class BrowseAlarmAdapter extends BaseAdapter {

	private ArrayList<AlarmTime> alams = new ArrayList<AlarmTime>();

	public BrowseAlarmAdapter() {
		alams.add(new AlarmTime(20, 20));
		alams.add(new AlarmTime(20, 20));
		alams.add(new AlarmTime(20, 20));
		alams.add(new AlarmTime(20, 20));
		alams.add(new AlarmTime(20, 20));
		alams.add(new AlarmTime(20, 20));
		alams.add(new AlarmTime(20, 20));
		alams.add(new AlarmTime(20, 20));
		alams.add(new AlarmTime(20, 20));
	}

	@Override
	public int getCount() {
		return alams.size();
	}

	@Override
	public Object getItem(int index) {
		return getItem(index);
	}

	@Override
	public long getItemId(int index) {
		return index;
	}

	@Override
	public View getView(int index, View view, ViewGroup parent) {
		if (view == null) {
			LayoutInflater inflater = LayoutInflater.from(parent.getContext());
			view = inflater.inflate(R.layout.alarms_list_item, parent, false);
		}

		AlarmTime alarmTime = alams.get(index);
		TextView textView = (TextView) view.findViewById(R.id.alarm_time_text);
		String text = alarmTime.getAlarmHours() + ":"
				+ alarmTime.getAlarmMinutes();
		textView.setText(text);
		return view;
	}

}
