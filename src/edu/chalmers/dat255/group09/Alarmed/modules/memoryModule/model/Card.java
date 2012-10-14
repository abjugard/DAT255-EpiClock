/*
 * Copyright (C) 2012 Joakim Persson, Daniel Augurell, Adrian Bjugård, Andreas Rolén
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
package edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.model;


/**
 * A class representing the logic code for a card in an memory game.
 * 
 * @author Joakim Persson
 * 
 */
public final class Card {

	/**
	 * An simple enum representing the two states of an card in a memory game.
	 * 
	 */
	public enum CardStatus {
		Hidden, Visable;
	}

	private CardStatus currentStatus;

	/**
	 * Create a new instance of the card class.
	 * 
	 */
	public Card() {
		this.currentStatus = CardStatus.Hidden;
	}

	/**
	 * Create a new card by copying a previous card.
	 * 
	 * @param card
	 *            The card to copy.
	 */
	public Card(Card card) {
		this.currentStatus = card.currentStatus;
	}

	/**
	 * Toggle the card:s current status.
	 */
	public void toggleStatus() {

		if (currentStatus == CardStatus.Hidden) {
			currentStatus = CardStatus.Visable;
		} else {
			currentStatus = CardStatus.Hidden;
		}

	}

	/**
	 * 
	 * @return The cards current status
	 */
	public CardStatus getStatus() {
		return currentStatus;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Card other = (Card) obj;

		return this.currentStatus.equals(other.currentStatus);

	}

	@Override
	public int hashCode() {
		int sum = 0;
		sum += currentStatus.hashCode();
		return sum;
	}
}
