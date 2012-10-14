package edu.chalmers.dat255.group09.Alarmed.test.modules.memoryModule.model;

import edu.chalmers.dat255.group09.Alarmed.R;
import edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.model.Card;
import edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.model.Card.CardStatus;
import edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.views.CardImage;
import android.test.AndroidTestCase;

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
		CardImage otherImage= new CardImage(getContext(), otherCard, IMAGE_RESOURCE);
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
		CardImage otherImage = new CardImage(getContext(), otherCard, IMAGE_RESOURCE);
		otherImage.cardPressed();
		assertFalse(cardImage.equals(otherImage));
	}
	public void testDisabled(){
		cardImage.setDisabled();
		assertTrue(cardImage.isDisabled());
	}
}
