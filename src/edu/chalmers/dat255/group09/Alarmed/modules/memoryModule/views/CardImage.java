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
package edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageView;
import edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.model.Card;

/**
 * A wrapper gui class for the models card class. This class is responsible for
 * handling the setting and getting of the image and if the instance if disabled
 * or not in the game plan.
 * 
 * @author Joakim Persson
 * 
 */
@SuppressLint("ViewConstructor")
public class CardImage extends ImageView {

	private Card card;
	private boolean disabled = false;

	/**
	 * Create a new Card Image instance.
	 * 
	 * @param context
	 *            The android context.
	 * @param card
	 *            A card to create a wrapper around.
	 */
	public CardImage(Context context, Card card) {
		super(context);

		this.setImageResource(card.getImageResource());
		this.card = card;
	}

	/**
	 * Handle the logic if the card was pressed.
	 */
	public void cardPressed() {
		card.toggleStatus();
		setImageResource(card.getImageResource());
	}

	/**
	 * Disable the card image.
	 */
	public void setDisabled() {
		disabled = true;
		setImageResource(edu.chalmers.dat255.group09.Alarmed.R.drawable.sample_image_1);
	}

	/**
	 * Get the status of the card image, if is disabled or not.
	 * 
	 * @return If it is disabled or not.
	 */
	public boolean isDisabled() {
		return disabled;
	}

	@Override
	public boolean equals(Object o) {

		if (o instanceof CardImage) {
			CardImage other = (CardImage) o;
			return card.equals(other.card);
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return card.hashCode();
	}
}
