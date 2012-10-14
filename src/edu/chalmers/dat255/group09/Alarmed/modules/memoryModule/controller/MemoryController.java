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
package edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.controller;

import edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.views.CardImage;

/**
 * A simple controller for an memory game. This controller keeps track of how
 * many pairs their are left in the game. It is also responsible for telling
 * when a game of memory is over and if two cards are equal.
 * 
 * @author Joakim Persson
 * 
 */
public class MemoryController {
	private int pairsLeft;

	/**
	 * Create a new instance of the MemoryController.
	 * 
	 * @param nbrOfPairs
	 *            Number of card pairs used in the game.
	 */
	public MemoryController(int nbrOfPairs) {
		this.pairsLeft = nbrOfPairs;
	}

	/**
	 * Is responsible for telling if the game is over or not. The game is over
	 * when all the pairs has been found.
	 * 
	 * @return If the game is over.
	 */
	public boolean isGameOver() {
		return pairsLeft == 0;
	}

	/**
	 * Determines if two card image cards are equal.
	 * 
	 * @param firstCard
	 *            The first of the cards to be compared.
	 * @param secondCard
	 *            The seconds of the cards to be compared.
	 * @return If the two cards are equal.
	 */
	public boolean isCardsEqual(CardImage firstCard, CardImage secondCard) {
		return firstCard.equals(secondCard);
	}

	/**
	 * The user has found two cards that are identical.
	 */
	public void correctGuess() {
		pairsLeft--;
	}

	/**
	 * Get how many pairs that are left in the game.
	 * 
	 * @return How many card pairs that remains
	 */
	public int getPairsLeft() {
		return pairsLeft;
	}
}
