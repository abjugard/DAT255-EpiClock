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
 * 
 * @author Joakim Persson
 * 
 */
@SuppressLint("ViewConstructor")
public class CardImage extends ImageView {

	private Card card;
	private boolean disabled = false;

	public CardImage(Context context, Card card) {
		super(context);

		this.setImageResource(card.getImageResource());
		this.card = card;
	}

	public void toggleStatus() {
		card.toggleStatus();
		setImageResource(card.getImageResource());
	}

	public void setDisabled() {
		disabled = true;
		setImageResource(edu.chalmers.dat255.group09.Alarmed.R.drawable.sample_image_1);
	}

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
