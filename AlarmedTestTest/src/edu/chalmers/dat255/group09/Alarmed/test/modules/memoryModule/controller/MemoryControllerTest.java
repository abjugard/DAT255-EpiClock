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
package edu.chalmers.dat255.group09.Alarmed.test.modules.memoryModule.controller;

import android.test.AndroidTestCase;
import edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.controller.MemoryController;
import edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.model.Card;
import edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.views.CardImageButton;

public class MemoryControllerTest extends AndroidTestCase {

	private MemoryController controller;
	private int nbrOfPairs;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		nbrOfPairs = 8;
		controller = new MemoryController(nbrOfPairs);
	}

	public void testGameOver() {
		assertFalse(controller.isGameOver());
		simulateGameOver();
		assertTrue(controller.isGameOver());
	}

	private void simulateGameOver() {
		for (int i = 0; i < nbrOfPairs; i++) {
			controller.correctGuess();
		}
	}

	public void testCardsEqual() {
		CardImageButton cardOne = new CardImageButton(getContext(), new Card(0));
		CardImageButton cardTwo = new CardImageButton(getContext(), new Card(0));
		assertTrue(controller.isCardsEqual(cardOne, cardTwo));
	}

	public void testCardsNotEqual() {
		CardImageButton cardOne = new CardImageButton(getContext(), new Card(0));
		CardImageButton cardTwo = new CardImageButton(getContext(), new Card(1));
		assertFalse(controller.isCardsEqual(cardOne, cardTwo));
	}

	public void testCorrectGuess() {
		assertEquals(nbrOfPairs, controller.getPairsLeft());
		controller.correctGuess();
		assertEquals(nbrOfPairs - 1, controller.getPairsLeft());
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		controller = null;
	}
}
