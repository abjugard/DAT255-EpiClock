package edu.chalmers.dat255.group09.Alarmed.activity;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import edu.chalmers.dat255.group09.Alarmed.R;

public class ActivationActivity extends Activity {

	private AudioManager audioManager;
	private MediaPlayer mediaPlayer;
	private Vibrator vibrator;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initServices();
		startAlarm();
		initGUI();
	}

	private void initServices() {
		audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		mediaPlayer = new MediaPlayer();
		vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
	}

	private void startAlarm() {
		startVibration();
		startAudio();
	}

	private void initGUI() {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_activation);
	}

	private void startVibration() {
		long[] vibPattern = { 0, 200, 500 };
		vibrator.vibrate(vibPattern, 0);
	}

	private void startAudio() {
		try {
			mediaPlayer.setDataSource(this, getAlarmTone());
			if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {
				mediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
				mediaPlayer.prepare();
				mediaPlayer.start();
			}
		} catch (IOException e) {
			Log.d("Sound", "Sound I/O error");
		}
	}

	private Uri getAlarmTone() {
		Uri alertTone = RingtoneManager
				.getDefaultUri(RingtoneManager.TYPE_ALARM);
		if (alertTone == null) {
			alertTone = RingtoneManager
					.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
			if (alertTone == null) {
				alertTone = RingtoneManager
						.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
			}
		}
		return alertTone;
	}

	public void onStopAlarmBtnPressed(View view) {
		stopAlarm();
		finish();
	}

	private void stopAlarm() {
		vibrator.cancel();
		mediaPlayer.stop();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_activation, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
