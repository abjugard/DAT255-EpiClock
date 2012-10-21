/*
 * Copyright (C) 2012 Joakim Persson, Daniel Augurell, Adrian Bjugard, Andreas Rolen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.chalmers.dat255.group09.Alarmed.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.SeekBar;
import edu.chalmers.dat255.group09.Alarmed.R;

/**
 * Helper class which takes care of everything to do with vibration and audio
 * while creating an alarm.
 * 
 * @author Adrian Bjugard
 * @author Daniel Augurell
 */
public class AudioHelper {
	private Context context;
	private Intent intent;
	private AudioManager audioMan;

	private View volumeView;
	private AlertDialog volumeDialog;

	/**
	 * Constructor for the AudioHelper object.
	 * 
	 * @param c
	 *            The android context
	 * @param i
	 *            Intent containing information about the alarm
	 */
	public AudioHelper(Context c, Intent i) {
		context = c;
		intent = i;
		audioMan = (AudioManager) context
				.getSystemService(Context.AUDIO_SERVICE);

		initSettings();
		createVolumeDialog();
	}

	/**
	 * Sets default settings.
	 */
	private void initSettings() {
		boolean vibrationSetting = intent.getBooleanExtra("vibration", true);
		intent.putExtra("vibration", vibrationSetting);

		int maxVolume = audioMan.getStreamMaxVolume(AudioManager.STREAM_ALARM);
		int volumeSetting = intent.getIntExtra("volume", maxVolume - 1);
		intent.putExtra("volume", volumeSetting);

		setInitialAlarmTone();
	}

	/**
	 * Since the alarm tone dialog isn't guaranteed to spawn during the lifetime
	 * of the CreateAlarm activity, this method sets the default value unless
	 * one already exists in the intent (in edit mode).
	 */
	public void setInitialAlarmTone() {
		String previousTone = intent.getStringExtra("toneuri");
		if (previousTone == null) {
			Uri tone = RingtoneManager.getActualDefaultRingtoneUri(context,
					RingtoneManager.TYPE_ALARM);
			if (tone == null) {
				tone = RingtoneManager.getActualDefaultRingtoneUri(context,
						RingtoneManager.TYPE_RINGTONE);
			}

			if (tone != null) {
				intent.putExtra("toneuri", tone.toString());
			}
		}
	}

	/**
	 * Creates the volume dialog.
	 */
	private void createVolumeDialog() {
		int maxVolume = audioMan.getStreamMaxVolume(AudioManager.STREAM_ALARM);
		LayoutInflater inflater = LayoutInflater.from(context);
		volumeView = inflater.inflate(R.layout.volume_dialog, null);

		SeekBar seekBar = ((SeekBar) volumeView
				.findViewById(R.id.selector_volume));
		CheckBox checkBox = ((CheckBox) volumeView
				.findViewById(R.id.selector_vibration));

		seekBar.setMax(maxVolume);
		seekBar.setProgress(intent.getIntExtra("volume", maxVolume - 1));
		checkBox.setChecked(intent.getBooleanExtra("vibration", true));

		volumeDialog = new AlertDialog.Builder(context)
				.setTitle("Set volume options")
				.setView(volumeView)
				.setPositiveButton(android.R.string.ok,
						new VolumeDialogListener()).create();
	}

	/**
	 * Getter for the alarm volume and vibration selection dialog.
	 * 
	 * @return Alarm volume and vibration selection dialog
	 */
	public Dialog getVolumeDialog() {
		return volumeDialog;
	}

	/**
	 * A listener that activates when the OK button is clicked in the volume
	 * dialog.
	 * 
	 * @author Adrian Bjugard
	 */
	private class VolumeDialogListener implements
			DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int i) {
			intent.putExtra("vibration", ((CheckBox) volumeView
					.findViewById(R.id.selector_vibration)).isChecked());
			intent.putExtra("volume", ((SeekBar) volumeView
					.findViewById(R.id.selector_volume)).getProgress());
		}
	}
}
