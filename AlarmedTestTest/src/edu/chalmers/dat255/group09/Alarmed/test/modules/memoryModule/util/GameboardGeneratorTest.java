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
package edu.chalmers.dat255.group09.Alarmed.test.modules.memoryModule.util;

import java.util.List;

import android.test.AndroidTestCase;
import edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.util.GameboardGenerator;
import edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.views.CardImage;

/**
 * A test class for the GameboaardGenerator class.
 * 
 * @author Joakim Persson
 * 
 */
public class GameboardGeneratorTest extends AndroidTestCase {

	private GameboardGenerator generator;
	private static final int NBR_OF_PAIRS = 8;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		generator = new GameboardGenerator(getContext());
	}

	/**
	 * Tests that the generator is generating the specified number of pairs for
	 * the gameboard.
	 */
	public void testCorrectNbrOfPairs() {
		List<CardImage> cards = generator.getGameBoard(NBR_OF_PAIRS, true);

		assertEquals(NBR_OF_PAIRS, getNbrOfPairs(cards));
	}

	/**
	 * Checks a list of cards for duplicates/pairs.
	 * 
	 * @param cards
	 *            A list of cards.
	 * @return The number of pairs of cards in the list.
	 */
	private int getNbrOfPairs(List<CardImage> cards) {
		int nbrOfPairs = 0;

		for (int i = 0; i < cards.size(); i += 2) {
			if (cards.get(i).equals(cards.get(i + 1))) {
				nbrOfPairs++;
			}
		}

		return nbrOfPairs;
	}

	/**
	 * Tests that the generator is actually always returning a new gameboard, by
	 * generating two gameboards directly after each other and then checks that
	 * they are not equal.
	 */
	public void testIsGeneratingRandomGameBoards() {
		List<CardImage> cards = generator.getGameBoard(NBR_OF_PAIRS, false);
		List<CardImage> otherCards = generator
				.getGameBoard(NBR_OF_PAIRS, false);

		assertFalse(cards.equals(otherCards));
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		generator = null;
	}
}
