package com.mobinsight.slotmachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Slot Machine Activity for 
 * MobInsight sample code demonstration.
 * Icons: https://www.iconfinder.com/iconsets/free-social-icons#readme
 *
 * @author Mandar Mulherkar
 *
 */
public class SlotMachine extends FragmentActivity {

	private static final int TOTAL_ANIMATION_FRAMES = 11;
	/** Slot 1 ImageView that will host the animation. */
	private static ImageView slot1;
	/** Slot 1 ImageView that will host the animation. */
	private static ImageView slot2;
	/** Slot 1 ImageView that will host the animation. */
	private static ImageView slot3;
	/** Slot animation. */
	private AnimationDrawable slot1Animation;
	/** Slot animation. */
	private AnimationDrawable slot2Animation;
	/** Slot animation. */
	private AnimationDrawable slot3Animation;
	/** Random generator. */
	private Random generator = new Random();
	/** Is animation running. */
	private static boolean running;
	/** How many slots are running. */
	private static int counter;
	/** Spinner button. */
	private Button spinButton;
	/** Bet Max button. */
	private Button betMaxButton;
	/** Dialog Fragment. */
	private SlotMachineDialogFragment dialog;
	/** Slot machine icons. */
	private Drawable facebook;
	private Drawable flickr;
	private Drawable github;
	private Drawable googleplus; 
	private Drawable linkedin; 
	private Drawable rss; 
	private Drawable skype; 
	private Drawable tumblr; 
	private Drawable twitter; 
	private Drawable vimeo;
	private Drawable youtube;
	private List<Drawable> drawableList1 = new ArrayList<Drawable>();
	private List<Drawable> drawableList2 = new ArrayList<Drawable>();
	private List<Drawable> drawableList3 = new ArrayList<Drawable>();
	/** {@inheritDoc} */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_slot_machine);
		slot1 = (ImageView) findViewById(R.id.slot1);
		slot2 = (ImageView) findViewById(R.id.slot2);
		slot3 = (ImageView) findViewById(R.id.slot3);
		
		slot1.setBackgroundResource(R.drawable.slotanimation);
		slot1Animation = (AnimationDrawable) slot1.getBackground();
		slot2.setBackgroundResource(R.drawable.slotanimation);
		slot2Animation = (AnimationDrawable) slot2.getBackground();
		slot3.setBackgroundResource(R.drawable.slotanimation);
		slot3Animation = (AnimationDrawable) slot3.getBackground();
		
		setupAnimationList();

	}

	private void setupAnimationList() {
		
		for(int i=0; i<TOTAL_ANIMATION_FRAMES; i++){
			Drawable drawable = slot1Animation.getFrame(i); 
			drawableList1.add(drawable);
		}
		
		for(int i=0; i<TOTAL_ANIMATION_FRAMES; i++){
			Drawable drawable = slot2Animation.getFrame(i); 
			drawableList2.add(drawable);
		}
		
		for(int i=0; i<TOTAL_ANIMATION_FRAMES; i++){
			Drawable drawable = slot3Animation.getFrame(i); 
			drawableList3.add(drawable);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.slot_machine, menu);
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		spinButton = (Button) findViewById(R.id.spinbutton);
		spinButton.setOnClickListener(new OnClickListener(){
		
			@Override
			public void onClick(View arg0) {
				if(!running){
					spinButton.setEnabled(false);
					slot1.post(new Slot1Thread(slot1Animation));
					slot2.post(new Slot2Thread(slot2Animation));
					slot3.post(new Slot3Thread(slot3Animation));
					counter = 3;
					
					slot1RandomStop();
					slot2RandomStop();
					slot3RandomStop();
					running = true;
				}
			}});
		
		betMaxButton = (Button) findViewById(R.id.betmaxbutton);
		betMaxButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				dialog = new SlotMachineDialogFragment();
				dialog.show(getSupportFragmentManager(), "Dialog");
			}
		});
	}
	
	private void slot1RandomStop() {
		Thread t = new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					int randomTime = generator.nextInt(3000);
					Thread.sleep(2000+randomTime);
					slot1Animation.stop();
					Drawable currentFrame = slot1Animation.getCurrent();
					processCurrentFrame(1, currentFrame);
					updateCounter();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}});
		t.setName("slot1");
		t.start();
	}

	private void slot2RandomStop() {
		Thread t = new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					int randomTime = generator.nextInt(3000);
					Thread.sleep(2000+randomTime);
					slot2Animation.stop();
					Drawable currentFrame = slot2Animation.getCurrent();
					processCurrentFrame(2, currentFrame);
					updateCounter();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}});
		t.setName("slot2");
		t.start();
	}
	
	private void slot3RandomStop() {
		Thread t = new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					int randomTime = generator.nextInt(3000);
					Thread.sleep(2000+randomTime);
					slot3Animation.stop();
					Drawable currentFrame = slot3Animation.getCurrent();
					processCurrentFrame(3, currentFrame);
					updateCounter();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}});
		t.setName("slot3");
		t.start();
	}


	synchronized protected void processCurrentFrame(int slot, Drawable currentFrame) {
		
		switch(slot){
		case 1:
			for(int i=0; i<TOTAL_ANIMATION_FRAMES; i++){
				if(currentFrame.equals(drawableList1.get(i))) {
					printFrameName(slot, i);
				}
			}
			break;
		case 2:
			for(int i=0; i<TOTAL_ANIMATION_FRAMES; i++){
				if(currentFrame.equals(drawableList2.get(i))) {
					printFrameName(slot, i);
				}
			}
			break;
		case 3:
			for(int i=0; i<TOTAL_ANIMATION_FRAMES; i++){
				if(currentFrame.equals(drawableList3.get(i))) {
					printFrameName(slot, i);
				}
			}
			break;
		default:
			return;
		}
	}

	private void printFrameName(int slot, int i) {
		Log.e("SlotMachine", "Slot "+slot+" "+AnimationFrames.values()[i]);
	}
	
	private void updateCounter() {
		if(counter > 0) {
			counter--;
		}
		if(counter == 0) {
			running = false;
			runOnUiThread(new Runnable(){
				@Override
				public void run() {
					spinButton.setEnabled(true);
				}
			});
		}
	}
	
}
