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
package edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.adapter;

import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.views.CardImage;

/**
 * An adapter for display card images used in a memory game, with a gridview.
 * 
 * @author Joakim Persson
 * 
 */
public class MemoryAdapter extends BaseAdapter {

	private List<CardImage> images;

	/**
	 * Create a new MempryAdapter instance.
	 * 
	 * @param cardImages
	 *            A list of cardimages to be used in the adapter.
	 */
	public MemoryAdapter(List<CardImage> cardImages) {
		images = new ArrayList<CardImage>(cardImages);
	}

	@Override
	public int getCount() {
		return images.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		CardImage view;
		if (convertView instanceof CardImage) {
			view = (CardImage) convertView;
		} else {
			view = images.get(position);
			view.setLayoutParams(new GridView.LayoutParams(160, 160));
			view.setAdjustViewBounds(false);
			view.setScaleType(ImageView.ScaleType.CENTER_CROP);
			view.setPadding(8, 8, 8, 8);
		}

		return view;
	}
}
