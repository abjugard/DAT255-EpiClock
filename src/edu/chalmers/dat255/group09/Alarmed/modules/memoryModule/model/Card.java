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
 * 
 * @author Joakim Persson
 * 
 */
public final class Card {

	public enum CardStatus {
		Hidden, Visable;
	}

	private CardStatus currentStatus;
	private int color;

	public Card(int color) {
		this.currentStatus = CardStatus.Hidden;
		this.color = color;
	}

	public Card(Card card) {
		this.color = card.color;
		this.currentStatus = card.currentStatus;

	}

	public void toggleStatus() {

		if (currentStatus == CardStatus.Hidden) {
			currentStatus = CardStatus.Visable;
		} else {
			currentStatus = CardStatus.Hidden;
		}

	}

	public CardStatus getStatus() {
		return currentStatus;
	}

	// TODO only for rapid development
	public int getColor() {
		return color;
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

		return this.color == other.color;
	}

	@Override
	public int hashCode() {
		int sum = 0;

		sum += color * 13;

		return sum;
	}
}
