package com.chadmeza.caydensrun.state;

import android.view.MotionEvent;

import com.chadmeza.caydensrun.GameMainActivity;
import com.chadmeza.caydensrun.util.Painter;

public abstract class State {
	
	public void setCurrentState(State newState) {
		GameMainActivity.sGame.setCurrentState(newState);
	}
	
	public abstract void init();
	
	public abstract void update(float delta);
	
	public abstract void render(Painter g);
	
	public abstract boolean onTouch(MotionEvent e, int scaledX, int scaledY);
	
	public abstract String toString();
	
	public void onResume() {}
	
	public void onPause() {}
	
	public void onLoad() {}
	
	public void onExit() {}
}
