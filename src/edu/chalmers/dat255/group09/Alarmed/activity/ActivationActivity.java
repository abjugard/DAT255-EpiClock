package edu.chalmers.dat255.group09.Alarmed.activity;

import java.io.IOException;

import android.annotation.SuppressLint;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import edu.chalmers.dat255.group09.Alarmed.R;

public class ActivationActivity extends Activity {

	private AudioManager audioManager;
	private MediaPlayer mediaPlayer;
	private Vibrator vibrator;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		initServices(this);
		startAlarm(); // do this first to make sure alarm goes off ASAP
		initGUI(this);
	}

	private void initServices(Context context) {
		audioManager = (AudioManager) context
				.getSystemService(Context.AUDIO_SERVICE);
		mediaPlayer = new MediaPlayer();
		vibrator = (Vibrator) context
				.getSystemService(Context.VIBRATOR_SERVICE);
	}

	private void startAlarm() {
		startVibration();
		startAudio(this, getAlarmUri());
	}

	private void startVibration() {
		long[] vibPattern = { 0, 200, 500 };
		vibrator.vibrate(vibPattern, 0);
	}

	private void startAudio(Context context, Uri alert) {
		try {
			mediaPlayer.setDataSource(context, alert);
			if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) != 0) {
				mediaPlayer.setAudioStreamType(AudioManager.STREAM_ALARM);
				mediaPlayer.prepare();
				mediaPlayer.start();
			}
		} catch (IOException e) {
			Log.d("Sound", "Sound I/O error");
		}
	}

	private void initGUI(ActivationActivity activity) {
		activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
		activity.getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_activation);

		Button stopAlarm = (Button) findViewById(R.id.stopAlarm);
		stopAlarm.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				stopAlarm();
				finish();
				return false;
			}
		});
	}

	protected void stopAlarm() {
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

	private Uri getAlarmUri() {
		Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
		if (alert == null) {
			// shouldn't happen
			alert = RingtoneManager
					.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
			if (alert == null) {
				// covering all bases, but also shouldn't happen
				alert = RingtoneManager
						.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
			}
		}
		return alert;
	}
}
