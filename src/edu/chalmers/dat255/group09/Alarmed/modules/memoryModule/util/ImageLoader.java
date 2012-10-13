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
package edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.util;

import android.util.SparseIntArray;
import edu.chalmers.dat255.group09.Alarmed.R;

/**
 * An imageloader the maps every drawable image resource used in the memory game
 * to a key or index starting on zero.
 * 
 * @author Joakim Persson
 * 
 */
public final class ImageLoader {

	private SparseIntArray imageResources;

	/**
	 * Create a new instance of the imageloader.
	 */
	public ImageLoader() {
		initImages();
	}

	/**
	 * Init the imageResources locations and map them to an key.
	 */
	private void initImages() {
		imageResources = new SparseIntArray();
		imageResources.append(0, R.drawable.sample_image_0);
		imageResources.append(1, R.drawable.sample_image_1);
		imageResources.append(2, R.drawable.sample_image_2);
		imageResources.append(3, R.drawable.sample_image_3);
		imageResources.append(4, R.drawable.sample_image_4);
		imageResources.append(5, R.drawable.sample_image_5);
		imageResources.append(6, R.drawable.sample_image_6);
		imageResources.append(7, R.drawable.sample_image_7);
	}

	/**
	 * Get an imageresource by accesing it via its key.
	 * 
	 * @param key
	 *            The resource:s key
	 * @return An image resource
	 */
	public int getImageResource(int key) {
		return imageResources.get(key);
	}
}
