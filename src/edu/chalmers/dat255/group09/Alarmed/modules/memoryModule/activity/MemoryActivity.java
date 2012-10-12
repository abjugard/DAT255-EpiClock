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
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.SparseIntArray;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;
import edu.chalmers.dat255.group09.Alarmed.R;
import edu.chalmers.dat255.group09.Alarmed.activity.BaseActivationActivity;
import edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.adapter.MemoryAdapter;
import edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.model.Card;
import edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.views.CardImageButton;

/**
 * 
 * @author Joakim Persson
 * 
 */
public class MemoryActivity extends BaseActivationActivity implements OnItemClickListener {

	private int PAIRS = 8;
	private int PAIRS_LEFT = PAIRS;
	private final static int COLUMNS = 4;
	private final static int DELAY = 500;
	private Timer timer;
	private boolean isFirstCard = true;
	private CardImageButton firstCard = null;
	private boolean isTimerActive = false;
	private static SparseIntArray imagesIntegers;

	static {
		imagesIntegers = new SparseIntArray();
		imagesIntegers.append(0, R.drawable.sample_image_0);
		imagesIntegers.append(1, R.drawable.sample_image_1);
		imagesIntegers.append(2, R.drawable.sample_image_2);
		imagesIntegers.append(3, R.drawable.sample_image_3);
		imagesIntegers.append(4, R.drawable.sample_image_4);
		imagesIntegers.append(5, R.drawable.sample_image_5);
		imagesIntegers.append(6, R.drawable.sample_image_6);
		imagesIntegers.append(7, R.drawable.sample_image_7);
	}

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

		for (int i = 0; i < PAIRS; i++) {
			List<CardImageButton> cardPair = getUniqueImagePair(i);
			images.addAll(cardPair);
		}

		Collections.shuffle(images, new Random());

		return images;
	}

	private List<CardImageButton> getUniqueImagePair(int i) {
		List<CardImageButton> pair = new ArrayList<CardImageButton>();
		Card card = new Card(imagesIntegers.get(i));
		Card otherCard = new Card(card);

		pair.add(new CardImageButton(this, card));
		pair.add(new CardImageButton(this, otherCard));
		return pair;
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
				super.stopAlarm();
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
