package com.mobinsight.slotmachine;

import android.graphics.drawable.AnimationDrawable;

public class Slot1Thread implements Runnable {
	
	/** Animation drawable for the first slot. */
	private AnimationDrawable slot1Animation;
	
	public Slot1Thread(AnimationDrawable slot1Animation) {
		this.slot1Animation = slot1Animation;
	}

	@Override
	public void run() {
		slot1Animation.start();
	}

}
