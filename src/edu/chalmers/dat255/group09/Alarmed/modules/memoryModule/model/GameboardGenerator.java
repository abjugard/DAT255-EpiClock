package edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import android.content.Context;

import edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.util.ImageLoader;
import edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.views.CardImageButton;

public class GameboardGenerator {

	private Context context;
	private ImageLoader imageLoader;
	
	public GameboardGenerator(Context context) {
		this.context = context;
		imageLoader = new ImageLoader();
	}
	
	public List<CardImageButton> getGameBoard(int nbrOfpairs) {
		List<CardImageButton> images = new ArrayList<CardImageButton>();

		for (int i = 0; i < nbrOfpairs; i++) {
			List<CardImageButton> cardPair = getUniqueImagePair(i);
			images.addAll(cardPair);
		}

		Collections.shuffle(images, new Random());

		return images;
	}

	private List<CardImageButton> getUniqueImagePair(int i) {
		List<CardImageButton> pair = new ArrayList<CardImageButton>();
		Card card = new Card(imageLoader.getImageResource(i));
		Card otherCard = new Card(card);

		pair.add(new CardImageButton(context, card));
		pair.add(new CardImageButton(context, otherCard));
		return pair;
	}
}
