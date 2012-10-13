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
package edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import android.content.Context;

import edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.model.Card;
import edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.views.CardImage;

public class GameboardGenerator {

	private Context context;
	private ImageLoader imageLoader;

	public GameboardGenerator(Context context) {
		this.context = context;
		imageLoader = new ImageLoader();
	}

	// TODO add documentation for test case
	public List<CardImage> getGameBoard(int nbrOfpairs, boolean isTest) {
		List<CardImage> images = new ArrayList<CardImage>();

		for (int i = 0; i < nbrOfpairs; i++) {
			List<CardImage> cardPair = getUniqueImagePair(i);
			images.addAll(cardPair);
		}

		if (!isTest) {
			Collections.shuffle(images, new Random());
		}
		return images;
	}

	private List<CardImage> getUniqueImagePair(int i) {
		List<CardImage> pair = new ArrayList<CardImage>();
		Card card = new Card(imageLoader.getImageResource(i));
		Card otherCard = new Card(card);

		pair.add(new CardImage(context, card));
		pair.add(new CardImage(context, otherCard));
		return pair;
	}
}
