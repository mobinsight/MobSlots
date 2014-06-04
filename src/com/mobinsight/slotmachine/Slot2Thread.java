package com.mobinsight.slotmachine;

import android.graphics.drawable.AnimationDrawable;

public class Slot2Thread implements Runnable {
	
	/** Animation drawable for the first slot. */
	private AnimationDrawable slot2Animation;
	
	
	public Slot2Thread(AnimationDrawable slot2Animation) {
		this.slot2Animation = slot2Animation;
	}

	@Override
	public void run() {
		slot2Animation.start();
	}

}
