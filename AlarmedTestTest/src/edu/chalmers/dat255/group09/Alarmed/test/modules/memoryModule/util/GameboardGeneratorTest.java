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
import edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.views.CardImageButton;

/**
 * 
 * @author Joakim Persson
 * 
 */
public class GameboardGeneratorTest extends AndroidTestCase {

	private GameboardGenerator generator;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		generator = new GameboardGenerator(getContext());
	}

	public void testCorrectNbrOfPairs() {
		int nbrOfPairs = 8;
		List<CardImageButton> cards = generator.getGameBoard(nbrOfPairs, true);

		assertEquals(nbrOfPairs, getNbrOfPairs(cards));
	}

	private int getNbrOfPairs(List<CardImageButton> cards) {
		int nbrOfPairs = 0;

		for (int i = 0; i < cards.size(); i += 2) {
			if (cards.get(i).equals(cards.get(i + 1))) {
				nbrOfPairs++;
			}
		}

		return nbrOfPairs;
	}

	public void testIsGeneratingRandomGameBoards() {
		int nbrOfpairs = 8;
		List<CardImageButton> cards = generator.getGameBoard(nbrOfpairs, false);
		List<CardImageButton> otherCards = generator.getGameBoard(nbrOfpairs,
				false);

		assertFalse(cards.equals(otherCards));
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
}
