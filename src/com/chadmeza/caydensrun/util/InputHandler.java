package com.chadmeza.caydensrun.util;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.chadmeza.caydensrun.GameMainActivity;
import com.chadmeza.caydensrun.state.State;

public class InputHandler implements OnTouchListener {

	private State currentState;
	
	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}
	
	@Override
	public boolean onTouch(View view, MotionEvent event) {
		int scaledX = (int) ((event.getX() / view.getWidth()) * GameMainActivity.GAME_WIDTH);
		int scaledY = (int) ((event.getY() / view.getHeight()) * GameMainActivity.GAME_HEIGHT);
		
		return currentState.onTouch(event, scaledX, scaledY);
	}

}
