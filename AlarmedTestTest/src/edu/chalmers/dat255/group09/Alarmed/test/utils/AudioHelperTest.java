package edu.chalmers.dat255.group09.Alarmed.test.utils;

import android.content.Intent;
import android.test.AndroidTestCase;
import edu.chalmers.dat255.group09.Alarmed.utils.AudioHelper;

public class AudioHelperTest extends AndroidTestCase {
	private AudioHelper hAudio;
	private Intent intent;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		intent = new Intent();
		hAudio = new AudioHelper(getContext(), intent);
	}
	
	public void testGetVolumeDialog() {
		assertNotNull(hAudio.getVolumeDialog());
	}
}
