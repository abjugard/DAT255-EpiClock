package edu.chalmers.dat255.group09.Alarmed.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.widget.TimePicker;

/**
 * A custom {@link TimePicker} to keep the scrolling in the picker if the parent
 * view also wants to scroll.
 * 
 * @author Daniel Augurell
 * 
 */
public class CustomTimePicker extends TimePicker {

	/**
	 * Default constructor for the custom time picker.
	 * 
	 * @param context
	 *            The android context
	 * @param attrs
	 *            The attributes of the CustomTimePicker
	 */
	public CustomTimePicker(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// Disallows the parent to receive the scroll
		if (ev.getActionMasked() == MotionEvent.ACTION_DOWN) {
			ViewParent p = getParent();
			if (p != null) {
				p.requestDisallowInterceptTouchEvent(true);
			}
		}
		return false;
	}

}
