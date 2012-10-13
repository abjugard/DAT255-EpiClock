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
import edu.chalmers.dat255.group09.Alarmed.R;
import edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.model.Card;
import edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.model.Card.CardStatus;

/**
 * 
 * @author Joakim Persson
 * 
 */
public class CardTest extends AndroidTestCase {

	private Card card;
	private static final int IMAGE_RESOURCE = 55;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		card = new Card(IMAGE_RESOURCE);
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

	public void testGetImageResourceIfHidden() {
		assertTrue(card.getStatus().equals(CardStatus.Hidden));
		assertEquals(R.drawable.ic_launcher, card.getImageResource());
	}

	public void testGetImageResourceIfVisable() {
		card.toggleStatus();
		assertTrue(card.getStatus().equals(CardStatus.Visable));
		assertEquals(IMAGE_RESOURCE, card.getImageResource());
	}

	public void testCopyConstructor() {
		Card otherCard = new Card(card);
		assertEquals(card, otherCard);
	}

	public void testHashCodeAgainstItself() {
		assertEquals(card.hashCode(), card.hashCode());
	}

	public void testHashCodeAgainstOtherImage() {
		Card otherCard = new Card(0);
		assertFalse(card.hashCode() == otherCard.hashCode());
	}

	public void testHashCodeAgainstSameImage() {
		Card otherCard = new Card(IMAGE_RESOURCE);
		assertEquals(card.hashCode(), otherCard.hashCode());
	}

	public void testHashCodeAgainstSameImageOtherCardStatus() {
		Card otherCard = new Card(IMAGE_RESOURCE);
		otherCard.toggleStatus();
		assertTrue(card.getStatus() != otherCard.getStatus());
		assertFalse(card.hashCode() == otherCard.hashCode());
	}

	public void testEqualsNull() {
		assertFalse(card.equals(null));
	}

	public void testEqualsItself() {
		assertTrue(card.equals(card));
	}

	public void testEqualsAgainstOtherImage() {
		Card otherCard = new Card(0);
		assertFalse(card.equals(otherCard));
	}

	public void testEqualsSameImage() {
		Card otherCard = new Card(IMAGE_RESOURCE);
		assertTrue(card.equals(otherCard));
	}

	public void testEqualsSameImagerOtherCardStatus() {
		Card otherCard = new Card(IMAGE_RESOURCE);
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
