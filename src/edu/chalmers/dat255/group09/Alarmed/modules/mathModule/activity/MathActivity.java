package edu.chalmers.dat255.group09.Alarmed.modules.mathModule.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import edu.chalmers.dat255.group09.Alarmed.R;

public class MathActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_math, menu);
        return true;
    }
}
