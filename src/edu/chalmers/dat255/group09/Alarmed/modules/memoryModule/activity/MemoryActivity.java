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
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
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
	private final static int DELAY = 500;
	private Timer timer;
	private boolean isFirstCard = true;
	private CardImageButton firstCard = null;
	private boolean isTimerActive = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_memory);

		List<CardImageButton> images = addData();
		GridView gridView = (GridView) findViewById(R.id.myGrid);
		MemoryAdapter memoryAdapter = new MemoryAdapter(images);
		gridView.setAdapter(memoryAdapter);
		gridView.setNumColumns(COLUMNS);
		gridView.setOnItemClickListener(this);

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

		if (!isTimerActive && !btn.isDisabled() && !btn.equals(firstCard)) {

			if (isFirstCard) {
				btn.toggleStatus();
				firstCard = btn;
				isFirstCard = false;
			} else {

				CardImageButton secondCard = btn;
				secondCard.toggleStatus();

				timer.schedule(new MemoryTask(firstCard, secondCard), DELAY);

				if (firstCard.equals(secondCard)) {
					PAIRS_LEFT--;
				}

				isFirstCard = true;
				firstCard = null;
				secondCard = null;
			}

			if (PAIRS_LEFT == 0) {
				Toast.makeText(this, "Well Played sir", Toast.LENGTH_SHORT)
						.show();
			}

		}

	}

	private class MemoryTask extends TimerTask {

		private final CardImageButton firstCardImage;
		private final CardImageButton secondCardImage;
		private Handler handler = new Handler();

		public MemoryTask(CardImageButton firstCard, CardImageButton secondCard) {
			this.firstCardImage = firstCard;
			this.secondCardImage = secondCard;
		}

		@Override
		public void run() {

			handler.post(new Runnable() {

				@Override
				public void run() {
					if (firstCardImage.equals(secondCardImage)) {

						firstCardImage.setDisabled();
						secondCardImage.setDisabled();
					} else {
						firstCardImage.toggleStatus();
						secondCardImage.toggleStatus();
					}

				}
			});

		}

	}

}
