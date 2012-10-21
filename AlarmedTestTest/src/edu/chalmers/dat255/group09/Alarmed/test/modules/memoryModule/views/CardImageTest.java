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
package edu.chalmers.dat255.group09.Alarmed.test.modules.memoryModule.views;

import android.test.AndroidTestCase;
import edu.chalmers.dat255.group09.Alarmed.R;
import edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.model.Card;
import edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.model.Card.CardStatus;
import edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.views.CardImage;

/**
 * 
 * @author Daniel Augurell
 * 
 */
public class CardImageTest extends AndroidTestCase {
	private Card card;
	private CardImage cardImage;
	private static final int IMAGE_RESOURCE = 55;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		card = new Card();
		cardImage = new CardImage(getContext(), card, IMAGE_RESOURCE);
	}

	public void testGetImageResourceIfHidden() {
		assertTrue(card.getStatus().equals(CardStatus.Hidden));
		assertEquals(R.drawable.ic_launcher, cardImage.getImageResource());
	}

	public void testCardPressed() {
		cardImage.cardPressed();
		assertTrue(card.getStatus().equals(CardStatus.Visable));
		assertEquals(IMAGE_RESOURCE, cardImage.getImageResource());
	}

	public void testHashCodeAgainstSameImage() {
		Card otherCard = new Card();
		CardImage otherImage = new CardImage(getContext(), otherCard,
				IMAGE_RESOURCE);
		assertEquals(cardImage.hashCode(), otherImage.hashCode());
	}

	public void testHashCodeAgainstOtherImage() {
		Card otherCard = new Card();
		CardImage otherImage = new CardImage(getContext(), otherCard, 0);
		assertFalse(cardImage.hashCode() == otherImage.hashCode());
	}

	public void testHashCodeAgainstItself() {
		assertEquals(cardImage.hashCode(), cardImage.hashCode());
	}

	public void testEqualsNull() {
		assertFalse(cardImage.equals(null));
	}

	public void testEqualsItself() {
		assertTrue(cardImage.equals(cardImage));
	}

	public void testEqualsAgainstOtherImage() {
		Card otherCard = new Card();
		CardImage otherImage = new CardImage(getContext(), otherCard, 0);
		assertFalse(cardImage.equals(otherImage));
	}

	public void testEqualsSameImageOtherCardStatus() {
		Card otherCard = new Card();
		CardImage otherImage = new CardImage(getContext(), otherCard,
				IMAGE_RESOURCE);
		otherImage.cardPressed();
		assertFalse(cardImage.equals(otherImage));
	}

	public void testDisabled() {
		cardImage.setDisabled();
		assertTrue(cardImage.isDisabled());
	}
}
