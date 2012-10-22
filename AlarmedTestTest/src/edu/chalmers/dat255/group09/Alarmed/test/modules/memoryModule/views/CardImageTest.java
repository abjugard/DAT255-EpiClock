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

	/**
	 * Test if the image is hidden when the card created.
	 */
	public void testGetImageResourceIfHidden() {
		assertTrue(card.getStatus().equals(CardStatus.Hidden));
		assertEquals(R.drawable.ic_launcher, cardImage.getImageResource());
	}

	/**
	 * Test if cardPressed is changing the status of the card.
	 */
	public void testCardPressed() {
		cardImage.cardPressed();
		assertTrue(card.getStatus().equals(CardStatus.Visible));
		assertEquals(IMAGE_RESOURCE, cardImage.getImageResource());
	}

	/**
	 * Test if the hash code is equal if it's the same image and the same status
	 * on the card.
	 */
	public void testHashCodeAgainstSameImage() {
		Card otherCard = new Card();
		CardImage otherImage = new CardImage(getContext(), otherCard,
				IMAGE_RESOURCE);
		assertEquals(cardImage.hashCode(), otherImage.hashCode());
	}

	/**
	 * Test if the hash code is not equal if it's not the same image but the same status
	 * on the card.
	 */
	public void testHashCodeAgainstOtherImage() {
		Card otherCard = new Card();
		CardImage otherImage = new CardImage(getContext(), otherCard, 0);
		assertFalse(cardImage.hashCode() == otherImage.hashCode());
	}
	/**
	 * Test if the hash code is equal against itself.
	 */
	public void testHashCodeAgainstItself() {
		assertEquals(cardImage.hashCode(), cardImage.hashCode());
	}
	/**
	 * Test if equals works on null.
	 */
	public void testEqualsNull() {
		assertFalse(cardImage.equals(null));
	}
	/**
	 * Test if the cardImage is equal to it self.
	 */
	public void testEqualsItself() {
		assertTrue(cardImage.equals(cardImage));
	}

	/**
	 * Test if its not equal to a different image.
	 */
	public void testEqualsAgainstOtherImage() {
		Card otherCard = new Card();
		CardImage otherImage = new CardImage(getContext(), otherCard, 0);
		assertFalse(cardImage.equals(otherImage));
	}
	/**
	 * Test of its not equal on different statuses.
	 */
	public void testEqualsSameImageOtherCardStatus() {
		Card otherCard = new Card();
		CardImage otherImage = new CardImage(getContext(), otherCard,
				IMAGE_RESOURCE);
		otherImage.cardPressed();
		assertFalse(cardImage.equals(otherImage));
	}
	/**
	 * Set if setDisable disables a card.
	 */
	public void testDisabled() {
		cardImage.setDisabled();
		assertTrue(cardImage.isDisabled());
	}
}
