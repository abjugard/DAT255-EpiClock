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

import edu.chalmers.dat255.group09.Alarmed.R;

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
	private int visableImageResource;
	private final static int hiddenImageResorce = R.drawable.ic_launcher;

	public Card(int imageResource) {
		this.currentStatus = CardStatus.Hidden;
		this.visableImageResource = imageResource;
	}

	public Card(Card card) {
		this(card.visableImageResource);
	}

	public void toggleStatus() {

		if (currentStatus == CardStatus.Hidden) {
			currentStatus = CardStatus.Visable;
		} else {
			currentStatus = CardStatus.Hidden;
		}

	}

	public int getImageResource() {
		if (currentStatus == CardStatus.Hidden) {
			return hiddenImageResorce;
		} else {
			return visableImageResource;
		}
	}

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

		return this.visableImageResource == other.visableImageResource
				&& this.currentStatus.equals(other.currentStatus);

	}

	@Override
	public int hashCode() {
		int sum = 0;

		sum += visableImageResource * 13;
		sum += currentStatus.hashCode();
		return sum;
	}
}
