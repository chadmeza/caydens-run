package com.chadmeza.caydensrun.state;

import android.view.MotionEvent;

import com.chadmeza.caydensrun.Assets;
import com.chadmeza.caydensrun.util.Painter;

public class InitState extends State {

	@Override
	public void init() {
		Assets.load();
	}

	@Override
	public void update(float delta) {
		setCurrentState(new LoadState(this, new MenuState()));
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
		return "InitState";
	}

}
