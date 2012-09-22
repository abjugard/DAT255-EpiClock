package edu.chalmers.dat255.group09.Alarmed;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CreateAlarm extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_alarm);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_create_alarm, menu);
        return true;
    }
}
