package com.chadmeza.caydensrun.state;

import android.view.MotionEvent;

import com.chadmeza.caydensrun.util.Painter;

public class LoadState extends State {

	private State currentState;
	private State target;
	
	public LoadState(State currentState, State target) {
		this.target = target;
		this.currentState = currentState;
	}
	
	@Override
	public void init() {

	}

	@Override
	public void update(float delta) {
		currentState.onExit();
		System.gc();
		target.onLoad();
		setCurrentState(target);
	}

	@Override
	public void render(Painter g) {
		
	}

	@Override
	public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
		return false;
	}

	@Override
	public String toString() {
		return "LoadState";
	}

}
