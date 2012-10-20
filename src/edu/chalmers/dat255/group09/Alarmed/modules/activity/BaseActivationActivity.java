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
package edu.chalmers.dat255.group09.Alarmed.modules.activity;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;

/**
 * Base activity for alarm activation activitys.
 * 
 * @author Adrian Bjugard
 * @author Joakim Persson
 * 
 */
public abstract class BaseActivationActivity extends Activity {

	/**
	 * The maximum value for the volume of the alarm tone.
	 */
	public static final int VOLUME_MAX_LIMIT = 7;

	/**
	 * The minimum value for the volume of the alarm tone.
	 */
	public static final int VOLUME_MIN_LIMIT = 1;

	private static final int SCHEDULE_DELAY = 5000;
	private static final String WAKE_LOCK_TAG = "edu.chalmers.dat255.group09.Alarmed.activity.BaseActivationActivity";
	private WakeLock wakeLock;
	private AudioManager audioManager;
	private MediaPlayer mediaPlayer;
	private Vibrator vibrator;
	private Timer inputTimer;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		enterFullScreen();
		initWakeLock();
		initTouchListener();
		initServices();
		startAlarm();
	}

	/**
	 * A util method for the view to enter fullscreen mode and override
	 * lockscreen if the phone is locked. This method also disables the window
	 * title.
	 */
	private void enterFullScreen() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		Window window = getWindow();
		window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
	}

	/**
	 * Creates an wakelock for the activity which allows it to wake up the phone
	 * and then keep the screen on until the user has completed the task.
	 */
	private void initWakeLock() {
		PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);

		wakeLock = powerManager.newWakeLock(
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
						| PowerManager.FULL_WAKE_LOCK
						| PowerManager.ACQUIRE_CAUSES_WAKEUP, WAKE_LOCK_TAG);

		wakeLock.acquire();
	}

	/**
	 * Initiate an touch listener and attach it to the view.
	 */
	private void initTouchListener() {
		View v = (View) this.findViewById(android.R.id.content);
		v.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				inputDetected();
				return false;
			}
		});
	}

	/**
	 * Init services required for the alarm activity. It fetches androids
	 * audiomanager and vibrator. It is also responsible for creating the
	 * mediaplayer instance.
	 */
	private void initServices() {
		audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		mediaPlayer = new MediaPlayer();
		vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		inputTimer = new Timer();
	}

	/**
	 * A method for starting the alarm and trigger the audio.
	 */
	private void startAlarm() {
		if (Boolean.parseBoolean(getIntent().getStringExtra("vibration"))) {
			startVibration();
		}
		startAudio();
	}

	/**
	 * Makes the phone start vibrating after an specified pattern.
	 */
	private void startVibration() {
		long[] vibPattern = { 0, 200, 500 };
		vibrator.vibrate(vibPattern, 0);
	}

	/**
	 * Starts playing the specified alarm tone and sets it on repeat until the
	 * alarm is terminated.
	 */
	private void startAudio() {
		try {
			mediaPlayer.setDataSource(this, getAlarmTone());
			int volume = Integer.parseInt(getIntent().getStringExtra("volume"));
			audioManager.setStreamVolume(AudioManager.STREAM_ALARM, volume, 0);
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
			mediaPlayer.setLooping(true);
			mediaPlayer.prepare();
			mediaPlayer.start();
		} catch (IOException e) {
			Log.d("Sound", "Sound I/O error");
		}

	}

	/**
	 * Get the ringtone URI.
	 * 
	 * @return Returns alarm tone
	 */
	private Uri getAlarmTone() {
		Uri alertTone = Uri.parse(getIntent().getStringExtra("toneuri"));
		if (alertTone == null) {
			alertTone = RingtoneManager
					.getDefaultUri(RingtoneManager.TYPE_ALARM);
			if (alertTone == null) {
				alertTone = RingtoneManager
						.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
			}
		}
		return alertTone;
	}

	/**
	 * Stops the ongoning alarm services and when its done it also kills the
	 * current activity.
	 */
	public void stopAlarm() {
		vibrator.cancel();
		mediaPlayer.stop();
		wakeLock.release();
		finish();
		overrideTransition();
	}

	/**
	 * Makes the transition between views smoother by animating them.
	 */
	private void overrideTransition() {
		int fadeIn = android.R.anim.fade_in;
		int fadeOut = android.R.anim.fade_out;
		overridePendingTransition(fadeIn, fadeOut);
	}

	@Override
	public void onBackPressed() {
		// The user is not allowed to go back
		// Therefore does not call super implementation
		inputDetected();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)) {
			// The user is not allowed to lower the volume
			inputDetected();
			return true;
		}
		inputDetected();
		return true;
	}

	/**
	 * Increase the alarm audio streams volume one step.
	 */
	public void increaseVolume() {
		audioManager.adjustStreamVolume(AudioManager.STREAM_ALARM,
				AudioManager.ADJUST_RAISE, 0);
	}

	/**
	 * Decrease the alarm audio streams volume one step (if volume greater than
	 * the VOLUME_MIN_LIMIT).
	 */
	public void decreaseVolume() {
		if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) > VOLUME_MIN_LIMIT) {
			audioManager.adjustStreamVolume(AudioManager.STREAM_ALARM,
					AudioManager.ADJUST_LOWER, 0);
		}
	}

	/**
	 * Sets the volume for the alarm audio stream to the desired level.
	 * 
	 * @param desiredVol
	 *            An integer between VOLUME_MIN_LIMIT and VOLUME_MAX_LIMIT
	 */
	public void setVolume(int desiredVol) {
		if (desiredVol > VOLUME_MAX_LIMIT) {
			desiredVol = VOLUME_MAX_LIMIT;
		} else if (desiredVol < VOLUME_MIN_LIMIT) {
			desiredVol = VOLUME_MIN_LIMIT;
		}
		audioManager.setStreamVolume(AudioManager.STREAM_ALARM, desiredVol, 0);
	}

	/**
	 * Method which is run when input is detected. If input is detected the
	 * alarm tone volume is set to the minimum limit.
	 */
	public void inputDetected() {
		setVolume(VOLUME_MIN_LIMIT);
		refreshTimer(SCHEDULE_DELAY);
	}

	/**
	 * Refreshes the input timer.
	 * 
	 * @param millis
	 *            Amount of seconds until timer runs task
	 */
	private void refreshTimer(int millis) {
		inputTimer.cancel();
		inputTimer = new Timer();
		inputTimer.schedule(new InputTimerTask(), millis);
	}

	/**
	 * Timer-task that is used for scheduling what happens no input is detected
	 * for a while.
	 */
	private class InputTimerTask extends TimerTask {

		@Override
		public void run() {
			increaseVolume();

			refreshTimer(SCHEDULE_DELAY);
		}
	}
}
