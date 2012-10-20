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
package edu.chalmers.dat255.group09.Alarmed.test.modules.memoryModule.model;

import android.test.AndroidTestCase;
import edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.model.Card;
import edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.model.Card.CardStatus;

/**
 * A test class for the Card class.
 * 
 * @author Joakim Persson
 * @author Daniel Augurell
 * 
 */
public class CardTest extends AndroidTestCase {

	private Card card;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		card = new Card();
	}

	/**
	 * Tests that the cards toggleStatus method is switching between the two different states.
	 */
	public void testChangeStatus() {
		CardStatus actual = card.getStatus();
		assertEquals(CardStatus.Hidden, actual);

		card.toggleStatus();

		actual = card.getStatus();
		assertEquals(CardStatus.Visable, actual);

		card.toggleStatus();

		actual = card.getStatus();
		assertEquals(CardStatus.Hidden, actual);

	}

	/**
	 * Tests that the copy constructor is working correctly and is copying the
	 * card:s status when the status is hidden, which it is when the object is
	 * created.
	 */
	public void testCopyConstructorWhenStatusIsHidden() {
		Card otherCard = new Card(card);
		assertEquals(card, otherCard);
	}

	/**
	 * Tests that the copy constructor is working correctly and is copying the
	 * card:s status when the status is visible.
	 */
	public void testCopyConstructorWhenStatusIsVisible() {
		card.toggleStatus();
		Card otherCard = new Card(card);
		assertEquals(card, otherCard);
	}

	/**
	 * Testing the the hashCode method is returning true when a card is compared
	 * against itself.
	 */
	public void testHashCodeAgainstItself() {
		assertEquals(card.hashCode(), card.hashCode());
	}

	/**
	 * Tests that two cards with different statues does not have the same
	 * hashcode.
	 */
	public void testHashCodeAgainstOtherCardDifferentStatus() {
		Card otherCard = new Card();
		otherCard.toggleStatus();
		assertTrue(card.getStatus() != otherCard.getStatus());
		assertFalse(card.hashCode() == otherCard.hashCode());
	}

	/**
	 * Tests that the cards equal method returns false when compared against
	 * null.
	 */
	public void testEqualsNull() {
		assertFalse(card.equals(null));
	}

	/**
	 * Testing the the equal method is returning true when a card is compared
	 * against itself.
	 */
	public void testEqualsItself() {
		assertTrue(card.equals(card));
	}

	/**
	 * Tests that two cards with the same status is equal each other.
	 */
	public void testEqualsAgainstOtherCard() {
		Card otherCard = new Card();
		assertTrue(card.equals(otherCard));
	}

	/**
	 * Tests that two cards with different statues is not equal.
	 */
	public void testEqualsOtherCardDifferentStatus() {
		Card otherCard = new Card();
		otherCard.toggleStatus();
		assertTrue(card.getStatus() != otherCard.getStatus());
		assertFalse(card.equals(otherCard));
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		card = null;
	}
}
