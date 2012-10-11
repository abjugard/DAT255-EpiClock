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
package edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;
import edu.chalmers.dat255.group09.Alarmed.R;
import edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.adapter.MemoryAdapter;
import edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.model.Card;
import edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.views.CardImageButton;

/**
 * 
 * @author Joakim Persson
 * 
 */
public class MemoryActivity extends Activity implements OnItemClickListener {

	private int PAIRS_LEFT = 3;
	private final static int COLUMNS = 3;
	private final static int TIMER_DURATION = 1500;
	private Timer timer;
	private boolean isFirstCard = true;
	private CardImageButton firstCard = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_memory);

		List<CardImageButton> images = addData();
		GridView g = (GridView) findViewById(R.id.myGrid);
		MemoryAdapter memoryAdapter = new MemoryAdapter(images);
		g.setAdapter(memoryAdapter);
		g.setNumColumns(COLUMNS);
		g.setOnItemClickListener(this);

		timer = new Timer();
	}

	private List<CardImageButton> addData() {
		List<CardImageButton> images = new ArrayList<CardImageButton>();

		images.add(new CardImageButton(this,
				new Card(R.drawable.sample_image_1)));
		images.add(new CardImageButton(this,
				new Card(R.drawable.sample_image_1)));
		images.add(new CardImageButton(this,
				new Card(R.drawable.sample_image_2)));
		images.add(new CardImageButton(this,
				new Card(R.drawable.sample_image_2)));
		images.add(new CardImageButton(this,
				new Card(R.drawable.sample_image_3)));
		images.add(new CardImageButton(this,
				new Card(R.drawable.sample_image_3)));
		return images;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_memory, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int id, long numb) {
		CardImageButton btn = (CardImageButton) view;

		btn.toggleStatus();

	}
}
