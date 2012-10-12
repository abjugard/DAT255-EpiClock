package edu.chalmers.dat255.group09.Alarmed.modules.memoryModule.util;

import android.util.SparseIntArray;
import edu.chalmers.dat255.group09.Alarmed.R;

public final class ImageLoader {

	private SparseIntArray imageResources;

	public ImageLoader() {
		initImages();
	}

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
	
	public int getImageResource(int key) {
		return imageResources.get(key);
	}
}
