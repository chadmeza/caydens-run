package com.chadmeza.caydensrun.state;

import android.graphics.Bitmap;
import android.view.MotionEvent;

import com.chadmeza.caydensrun.Assets;
import com.chadmeza.caydensrun.GameMainActivity;
import com.chadmeza.caydensrun.util.Painter;
import com.chadmeza.caydensrun.util.UIButton;

public class MenuState extends State {
	private Bitmap welcome, title, play, playDown, score, scoreDown, volume, volumeDown;
	private UIButton playButton, scoreButton, volumeButton, noVolumeButton;

	@Override
	public void onLoad() {
		welcome = Assets.loadBitmap("bg.png", false);
		title = Assets.loadBitmap("menu_title.png", true);
		play = Assets.loadBitmap("play_button.png", true);
		playDown = Assets.loadBitmap("play_button_over.png", true);
		score = Assets.loadBitmap("high_score_button.png", true);
		scoreDown = Assets.loadBitmap("high_score_button_over.png", true);
		volume = Assets.loadBitmap("volume_button.png", true);
		volumeDown = Assets.loadBitmap("volume_button_over.png", true);
	}

	@Override
	public void onExit() {
		Assets.unloadBitmap(welcome);
		Assets.unloadBitmap(title);
		Assets.unloadBitmap(play);
		Assets.unloadBitmap(playDown);
		Assets.unloadBitmap(score);
		Assets.unloadBitmap(scoreDown);
		Assets.unloadBitmap(volume);
		Assets.unloadBitmap(volumeDown);
	}
	
	@Override
	public void init() {
		playButton = new UIButton(290, 245, 510, 315, play,
				playDown);
		scoreButton = new UIButton(290, 335, 510, 405, score,
				scoreDown);
		volumeButton = new UIButton(530, 335, 600, 405, volume,
				volume);
		noVolumeButton = new UIButton(530, 335, 600, 405, volumeDown,
				volumeDown);
	}

	@Override
	public void update(float delta) {
	}

	@Override
	public void render(Painter g) {
		g.drawImage(welcome, 0, 0);
		g.drawImage(title, 146, 45);
		playButton.render(g);
		scoreButton.render(g);
		
		if (GameMainActivity.getMusic()) {
			volumeButton.render(g);
		} else {
			noVolumeButton.render(g);
		}
	}

	@Override
	public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
		if (e.getAction() == MotionEvent.ACTION_DOWN) {
			playButton.onTouchDown(scaledX, scaledY);
			scoreButton.onTouchDown(scaledX, scaledY);
			
			if (GameMainActivity.getMusic()) {
				volumeButton.onTouchDown(scaledX, scaledY);
			} else {
				noVolumeButton.onTouchDown(scaledX, scaledY);
			}
		}
		if (e.getAction() == MotionEvent.ACTION_UP) {
			if (GameMainActivity.getMusic()) {
				if (volumeButton.isPressed(scaledX, scaledY)) {
					volumeButton.cancel();
					GameMainActivity.setMusic(false);
					Assets.onPause();
				}
			} else {
				if (noVolumeButton.isPressed(scaledX, scaledY)) {
					noVolumeButton.cancel();
					GameMainActivity.setMusic(true);
					Assets.onResume();
				}
			}
			
			if (playButton.isPressed(scaledX, scaledY)) {
				playButton.cancel();
				setCurrentState(new LoadState(this, new PlayState()));
			} else if (scoreButton.isPressed(scaledX, scaledY)) {
				scoreButton.cancel();
				setCurrentState(new LoadState(this, new ScoreState()));
			} else {
				playButton.cancel();
				scoreButton.cancel();
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return "MenuState";
	}
}