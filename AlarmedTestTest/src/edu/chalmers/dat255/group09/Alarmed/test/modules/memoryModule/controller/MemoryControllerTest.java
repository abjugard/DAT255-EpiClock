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
import edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.views.CardImage;

/**
 * 
 * @author Joakim Persson
 *
 */
public class MemoryControllerTest extends AndroidTestCase {

	private MemoryController controller;
	private static final int NBR_OF_PAIRS = 8;
	private static final int IMAGE_RESOURCE = 55;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		controller = new MemoryController(NBR_OF_PAIRS);
	}

	public void testGameOver() {
		assertFalse(controller.isGameOver());
		simulateGameOver();
		assertTrue(controller.isGameOver());
	}

	private void simulateGameOver() {
		for (int i = 0; i < NBR_OF_PAIRS; i++) {
			controller.correctGuess();
		}
	}

	public void testCardsEqual() {
		CardImage cardOne = new CardImage(getContext(), new Card(), IMAGE_RESOURCE);
		CardImage cardTwo = new CardImage(getContext(), new Card(), IMAGE_RESOURCE);
		assertTrue(controller.isCardsEqual(cardOne, cardTwo));
	}

	public void testCardsNotEqual() {
		CardImage cardOne = new CardImage(getContext(), new Card(), IMAGE_RESOURCE);
		CardImage cardTwo = new CardImage(getContext(), new Card(), 0);
		assertFalse(controller.isCardsEqual(cardOne, cardTwo));
	}

	public void testCorrectGuess() {
		assertEquals(NBR_OF_PAIRS, controller.getPairsLeft());
		controller.correctGuess();
		assertEquals(NBR_OF_PAIRS - 1, controller.getPairsLeft());
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		controller = null;
	}
}
