package com.mobinsight.slotmachine;

import android.graphics.drawable.AnimationDrawable;

public class Slot3Thread implements Runnable {
	
	/** Animation drawable for the first slot. */
	private AnimationDrawable slot3Animation;
	
	
	public Slot3Thread(AnimationDrawable slot3Animation) {
		this.slot3Animation = slot3Animation;
	}

	@Override
	public void run() {
		slot3Animation.start();
	}

}
