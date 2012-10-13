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

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import edu.chalmers.dat255.group09.Alarmed.R;
import edu.chalmers.dat255.group09.Alarmed.activity.BaseActivationActivity;
import edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.adapter.MemoryAdapter;
import edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.controller.MemoryController;
import edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.util.GameboardGenerator;
import edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.views.CardImageButton;

/**
 * 
 * @author Joakim Persson
 * 
 */
public class MemoryActivity extends BaseActivationActivity implements
		OnItemClickListener {

	private final static int NBR_OF_PAIRS = 8;
	private final static int COLUMNS = 4;
	private final static int DELAY = 500;
	private boolean isFirstCard = true;
	private Timer timer;
	private CardImageButton firstCard = null;
	private MemoryController controller;
	private GameboardGenerator generator;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_memory);
		generator = new GameboardGenerator(this);
		controller = new MemoryController(NBR_OF_PAIRS);

		initGridView();

		timer = new Timer();
	}

	private void initGridView() {
		GridView gridView = (GridView) findViewById(R.id.activity_math_grid_view);
		List<CardImageButton> images = generator.getGameBoard(NBR_OF_PAIRS,
				false);
		MemoryAdapter memoryAdapter = new MemoryAdapter(images);
		gridView.setAdapter(memoryAdapter);
		gridView.setNumColumns(COLUMNS);
		gridView.setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_memory, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int id, long numb) {
		CardImageButton btn = (CardImageButton) view;
		if (isValidPress(btn)) {
			handleButtonPressed(btn);
		}
	}

	private boolean isValidPress(CardImageButton btn) {
		return !btn.isDisabled() && !btn.equals(firstCard);
	}

	private void handleButtonPressed(CardImageButton cardImage) {

		if (isFirstCard) {
			handleFirstCardPressed(cardImage);
		} else {
			handleSecondCardPressed(cardImage);
		}

	}

	private void handleFirstCardPressed(CardImageButton cardImage) {
		cardImage.toggleStatus();
		firstCard = cardImage;
		isFirstCard = false;
	}

	private void handleSecondCardPressed(CardImageButton cardImage) {
		CardImageButton secondCard = cardImage;
		secondCard.toggleStatus();

		timer.schedule(new MemoryTask(firstCard, secondCard), DELAY);

		isFirstCard = true;
		firstCard = null;
		secondCard = null;
	}

	private class MemoryTask extends TimerTask {

		private final CardImageButton cardOne;
		private final CardImageButton cardTwo;
		private Handler handler = new Handler();

		public MemoryTask(CardImageButton firstCard, CardImageButton secondCard) {
			this.cardOne = firstCard;
			this.cardTwo = secondCard;
		}

		@Override
		public void run() {
			// Must use an handler to make the changes to the ui thread
			handler.post(new Runnable() {

				@Override
				public void run() {
					if (controller.isCardsEqual(cardOne, cardTwo)) {
						controller.correctGuess();
						cardOne.setDisabled();
						cardTwo.setDisabled();

					} else {
						cardOne.toggleStatus();
						cardTwo.toggleStatus();
					}

					if (controller.isGameOver()) {
						MemoryActivity.super.stopAlarm();
					}
				}

			});

		}
	}

}
