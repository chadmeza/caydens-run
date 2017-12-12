package com.chadmeza.caydensrun.state;

import android.graphics.Bitmap;
import android.view.MotionEvent;

import com.chadmeza.caydensrun.Assets;
import com.chadmeza.caydensrun.GameMainActivity;
import com.chadmeza.caydensrun.util.Painter;

public class GameOverState extends State {
	private String playerScore;
	private int numberPlaceholder;
	private int startX;
	
	private Bitmap bg, title, touch, newHigh, num0, num1, num2, num3, num4, num5, num6, num7, num8, num9;
	private boolean newHighScore = false;
	
	public GameOverState(int playerScore) {
		this.playerScore = playerScore + "";
		if (playerScore > GameMainActivity.getHighScore()) {
			GameMainActivity.setHighScore(playerScore);
			newHighScore = true;
		}
	}
	
	@Override
	public void onLoad() {
		bg = Assets.loadBitmap("bg_gameover.png", false);
		title = Assets.loadBitmap("title_gameover.png", true);
		touch = Assets.loadBitmap("title_touch.png", true);
		newHigh = Assets.loadBitmap("title_newhighscore.png", true);
		num0 = Assets.loadBitmap("number_0.png", true);
		num1 = Assets.loadBitmap("number_1.png", true);
		num2 = Assets.loadBitmap("number_2.png", true);
		num3 = Assets.loadBitmap("number_3.png", true);
		num4 = Assets.loadBitmap("number_4.png", true);
		num5 = Assets.loadBitmap("number_5.png", true);
		num6 = Assets.loadBitmap("number_6.png", true);
		num7 = Assets.loadBitmap("number_7.png", true);
		num8 = Assets.loadBitmap("number_8.png", true);
		num9 = Assets.loadBitmap("number_9.png", true);
	}

	@Override
	public void onExit() {
		Assets.unloadBitmap(bg);
		Assets.unloadBitmap(title);
		Assets.unloadBitmap(touch);
		Assets.unloadBitmap(newHigh);
		Assets.unloadBitmap(num0);
		Assets.unloadBitmap(num1);
		Assets.unloadBitmap(num2);
		Assets.unloadBitmap(num3);
		Assets.unloadBitmap(num4);
		Assets.unloadBitmap(num5);
		Assets.unloadBitmap(num6);
		Assets.unloadBitmap(num7);
		Assets.unloadBitmap(num8);
		Assets.unloadBitmap(num9);
	}
	
	@Override
	public void init() {
		
	}

	@Override
	public void update(float delta) {
		
	}

	@Override
	public void render(Painter g) {
		g.drawImage(bg, 0, 0);
		g.drawImage(title, 183, 45);
		g.drawImage(touch, 255, 377);
		renderScore(g);
	}
	
	private void renderScore(Painter g) {
		if (newHighScore) {
			g.drawImage(newHigh, 268, 175);
		}
		
		startX = 400 - ((playerScore.length() * 38) / 2);
		
		for (int i = 0; i < playerScore.length(); i++) {
			numberPlaceholder = Integer.parseInt(String.valueOf(playerScore.charAt(i)));
			
			switch (numberPlaceholder) {
			case 0:
				g.drawImage(num0, startX + (38 * i), 223);
				break;
			case 1:
				g.drawImage(num1, startX + (38 * i), 223);
				break;
			case 2:
				g.drawImage(num2, startX + (38 * i), 223);
				break;
			case 3:
				g.drawImage(num3, startX + (38 * i), 223);
				break;
			case 4:
				g.drawImage(num4, startX + (38 * i), 223);
				break;
			case 5:
				g.drawImage(num5, startX + (38 * i), 223);
				break;
			case 6:
				g.drawImage(num6, startX + (38 * i), 223);
				break;
			case 7:
				g.drawImage(num7, startX + (38 * i), 223);
				break;
			case 8:
				g.drawImage(num8, startX + (38 * i), 223);
				break;
			case 9:
				g.drawImage(num9, startX + (38 * i), 223);
				break;
			default:
				break;
			}
		}
	}

	@Override
	public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
		if (e.getAction() == MotionEvent.ACTION_UP) {
			setCurrentState(new LoadState(this, new MenuState()));
		}

		return true;
	}

	@Override
	public String toString() {
		return "GameOverState";
	}

}
