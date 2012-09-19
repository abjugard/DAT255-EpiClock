package edu.chalmers.dat255.group09.Alarmed;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class AlarmedMain extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmed_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_alarmed_main, menu);
        return true;
    }
}
