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

import android.graphics.Color;
import android.test.AndroidTestCase;
import edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.model.Card;
import edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.model.Card.CardStatus;

/**
 * 
 * @author Joakim Persson
 * 
 */
public class CardTest extends AndroidTestCase {

	private Card card;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		card = new Card(Color.RED);
	}

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

	public void testGetColor() {
		assertEquals(Color.RED, card.getColor());
	}

	public void testCopyConstructor() {
		Card otherCard = new Card(card);
		assertEquals(card, otherCard);
	}

	public void testHashCodeAgainstItself() {
		assertEquals(card.hashCode(), card.hashCode());
	}

	public void testHashCodeAgainstOtherColor() {
		Card otherCard = new Card(Color.BLACK);
		assertFalse(card.hashCode() == otherCard.hashCode());
	}

	public void testEHashCodeAgainstSameColor() {
		Card otherCard = new Card(Color.RED);
		assertEquals(card.hashCode(), otherCard.hashCode());
	}

	public void testHashCodeAgainstSameColorOtherCardStatus() {
		Card otherCard = new Card(Color.RED);
		otherCard.toggleStatus();
		assertTrue(card.getStatus() != otherCard.getStatus());
		assertEquals(card.hashCode(), otherCard.hashCode());
	}

	public void testEqualsNull() {
		assertFalse(card.equals(null));
	}

	public void testEqualsItself() {
		assertTrue(card.equals(card));
	}

	public void testEqualsAgainstOtherColor() {
		Card otherCard = new Card(Color.BLACK);
		assertFalse(card.equals(otherCard));
	}

	public void testEqualsSameColor() {
		Card otherCard = new Card(Color.RED);
		assertTrue(card.equals(otherCard));
	}

	public void testEqualsSameColorOtherCardStatus() {
		Card otherCard = new Card(Color.RED);
		otherCard.toggleStatus();
		assertTrue(card.getStatus() != otherCard.getStatus());
		assertTrue(card.equals(otherCard));
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		card = null;
	}
}
