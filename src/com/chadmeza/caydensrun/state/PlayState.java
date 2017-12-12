package com.chadmeza.caydensrun.state;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.chadmeza.caydensrun.Assets;
import com.chadmeza.caydensrun.GameMainActivity;
import com.chadmeza.caydensrun.animation.Animation;
import com.chadmeza.caydensrun.animation.Frame;
import com.chadmeza.caydensrun.model.Block;
import com.chadmeza.caydensrun.model.Cloud;
import com.chadmeza.caydensrun.model.Player;
import com.chadmeza.caydensrun.util.Painter;
import com.chadmeza.caydensrun.util.RandomNumberGenerator;

public class PlayState extends State {
	private Player player;
	private ArrayList<Block> blocks;
	private Cloud cloud, cloud2;

	private int playerScore = 0;
	private String playerScoreStr;
	private int numberPlaceholder;

	private static final int BLOCK_HEIGHT = 50;
	private static final int BLOCK_WIDTH = 20;
	private int blockSpeed = -200;

	private static final int PLAYER_WIDTH = 70;
	private static final int PLAYER_HEIGHT = 80;

	private float recentTouchY;

	private boolean gamePaused = false;
	
	private int sceneNumber = 0;
	
	private Bitmap block, cloud1_image, cloud2_image, duck, grass, jump, run1, run2, run3, 
					run4, run5, run6, run7, run8, num0, num1, num2, num3, num4, num5, num6,
					num7, num8, num9;
	private Animation runAnim;
	private int sunColor;
	private int sunStrokeColor;
	private int skyColor;

	@Override
	public void onLoad() {
		block = Assets.loadBitmap("block.png", false);
		duck = Assets.loadBitmap("duck.png", true);
		jump = Assets.loadBitmap("jump.png", true);
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
		run1 = Assets.loadBitmap("run_anim_1.png", true);
		run2 = Assets.loadBitmap("run_anim_2.png", true);
		run3 = Assets.loadBitmap("run_anim_3.png", true);
		run4 = Assets.loadBitmap("run_anim_4.png", true);
		run5 = Assets.loadBitmap("run_anim_5.png", true);
		run6 = Assets.loadBitmap("run_anim_6.png", true);
		run7 = Assets.loadBitmap("run_anim_7.png", true);
		run8 = Assets.loadBitmap("run_anim_8.png", true);

		Frame f1 = new Frame(run1, .1f);
		Frame f2 = new Frame(run2, .1f);
		Frame f3 = new Frame(run3, .1f);
		Frame f4 = new Frame(run4, .1f);
		Frame f5 = new Frame(run5, .1f);
		Frame f6 = new Frame(run6, .1f);
		Frame f7 = new Frame(run7, .1f);
		Frame f8 = new Frame(run8, .1f);

		runAnim = new Animation(f1, f2, f3, f4, f5, f6, f7, f8);
		
		sceneNumber = RandomNumberGenerator.getRandInt(5);
		
		switch (sceneNumber) {
		case 0:
			// Grass - Day
			
			cloud1_image = Assets.loadBitmap("cloud1.png", true);
			cloud2_image = Assets.loadBitmap("cloud2.png", true);
			grass = Assets.loadBitmap("grass.png", false);
			sunColor = Color.rgb(255, 248, 55);
			sunStrokeColor = Color.rgb(243, 168, 38);
			skyColor = Color.rgb(204, 243, 247);
			break;
		case 1:
			// Grass - Night
			
			cloud1_image = Assets.loadBitmap("cloud1_night.png", true);
			cloud2_image = Assets.loadBitmap("cloud2_night.png", true);
			grass = Assets.loadBitmap("grass.png", false);
			sunColor = Color.rgb(255, 255, 255);
			sunStrokeColor = Color.rgb(255, 255, 255);
			skyColor = Color.rgb(20, 21, 31);
			break;
		case 2:
			// Desert - Day
			
			cloud1_image = Assets.loadBitmap("cloud1.png", true);
			cloud2_image = Assets.loadBitmap("cloud2.png", true);
			grass = Assets.loadBitmap("desert.png", false);
			sunColor = Color.rgb(255, 248, 55);
			sunStrokeColor = Color.rgb(243, 168, 38);
			skyColor = Color.rgb(204, 243, 247);
			break;
		case 3:
			// Desert - Night
			
			cloud1_image = Assets.loadBitmap("cloud1_night.png", true);
			cloud2_image = Assets.loadBitmap("cloud2_night.png", true);
			grass = Assets.loadBitmap("desert.png", false);
			sunColor = Color.rgb(255, 255, 255);
			sunStrokeColor = Color.rgb(255, 255, 255);
			skyColor = Color.rgb(20, 21, 31);
			break;
		case 4:
			// Snow - Day
			
			cloud1_image = Assets.loadBitmap("cloud1.png", true);
			cloud2_image = Assets.loadBitmap("cloud2.png", true);
			grass = Assets.loadBitmap("snow.png", false);
			sunColor = Color.rgb(255, 248, 55);
			sunStrokeColor = Color.rgb(243, 168, 38);
			skyColor = Color.rgb(204, 243, 247);
			break;
		case 5:
			// Snow - Night
			
			cloud1_image = Assets.loadBitmap("cloud1_night.png", true);
			cloud2_image = Assets.loadBitmap("cloud2_night.png", true);
			grass = Assets.loadBitmap("snow.png", false);
			sunColor = Color.rgb(255, 255, 255);
			sunStrokeColor = Color.rgb(255, 255, 255);
			skyColor = Color.rgb(20, 21, 31);
			break;
		default:
			// Grass - Day
			
			cloud1_image = Assets.loadBitmap("cloud1.png", true);
			cloud2_image = Assets.loadBitmap("cloud2.png", true);
			grass = Assets.loadBitmap("grass.png", false);
			sunColor = Color.rgb(255, 248, 55);
			sunStrokeColor = Color.rgb(243, 168, 38);
			skyColor = Color.rgb(204, 243, 247);
			break;
		}
	}

	@Override
	public void onExit() {
		Assets.unloadBitmap(cloud1_image);
		Assets.unloadBitmap(cloud2_image);
		Assets.unloadBitmap(grass);
		Assets.unloadBitmap(block);
		Assets.unloadBitmap(duck);
		Assets.unloadBitmap(jump);
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
		Assets.unloadBitmap(run1);
		Assets.unloadBitmap(run2);
		Assets.unloadBitmap(run3);
		Assets.unloadBitmap(run4);
		Assets.unloadBitmap(run5);
		Assets.unloadBitmap(run6);
		Assets.unloadBitmap(run7);
		Assets.unloadBitmap(run8);
	}
	
	@Override
	public void init() {
		player = new Player(160, GameMainActivity.GAME_HEIGHT - 45
				- PLAYER_HEIGHT, PLAYER_WIDTH, PLAYER_HEIGHT);
		blocks = new ArrayList<Block>();
		cloud = new Cloud(100, 100);
		cloud2 = new Cloud(500, 50);
		for (int i = 0; i < 5; i++) {
			Block b = new Block(i * 200, GameMainActivity.GAME_HEIGHT - 95,
					BLOCK_WIDTH, BLOCK_HEIGHT);
			blocks.add(b);
		}
	}

	// Overrides onPause() from State.
	// Called when Activity is pausing.
	@Override
	public void onPause() {
		gamePaused = true;
	}

	@Override
	public void update(float delta) {
		if (gamePaused) {
			return;
		}

		if (!player.isAlive()) {
			setCurrentState(new LoadState(this, new GameOverState(playerScore / 100)));
		}
		playerScore += 1;
		if (playerScore % 500 == 0 && blockSpeed > -280) {
			blockSpeed -= 10;
		}
		cloud.update(delta);
		cloud2.update(delta);
		runAnim.update(delta);
		player.update(delta);
		updateBlocks(delta);
	}

	private void updateBlocks(float delta) {
		for (int i = 0; i < blocks.size(); i++) {
			Block b = blocks.get(i);
			b.update(delta, blockSpeed);
			if (b.isVisible()) {
				if (player.isDucked()
						&& Rect.intersects(b.getRect(), player.getDuckRect())) {
					b.onCollide(player);
				} else if (!player.isDucked()
						&& Rect.intersects(b.getRect(), player.getRect())) {
					b.onCollide(player);
				}
			}
		}
	}

	@Override
	public void render(Painter g) {
		g.setColor(skyColor);
		g.fillRect(0, 0, GameMainActivity.GAME_WIDTH,
				GameMainActivity.GAME_HEIGHT);
		renderPlayer(g);
		renderBlocks(g);
		renderSun(g);
		renderClouds(g);
		g.drawImage(grass, 0, 405);
		renderScore(g);

		if (gamePaused) {
			g.setColor(Color.argb(153, 0, 0, 0));
			g.fillRect(0, 0, GameMainActivity.GAME_WIDTH,
					GameMainActivity.GAME_HEIGHT);
		}
	}

	private void renderScore(Painter g) {
		playerScoreStr = "" + (playerScore / 100);
		for (int i = 0; i < playerScoreStr.length(); i++) {
			numberPlaceholder = Integer.parseInt(String.valueOf(playerScoreStr.charAt(i)));
			
			switch (numberPlaceholder) {
			case 0:
				g.drawImage(num0, 20 + (38 * i), 20);
				break;
			case 1:
				g.drawImage(num1, 20 + (38 * i), 20);
				break;
			case 2:
				g.drawImage(num2, 20 + (38 * i), 20);
				break;
			case 3:
				g.drawImage(num3, 20 + (38 * i), 20);
				break;
			case 4:
				g.drawImage(num4, 20 + (38 * i), 20);
				break;
			case 5:
				g.drawImage(num5, 20 + (38 * i), 20);
				break;
			case 6:
				g.drawImage(num6, 20 + (38 * i), 20);
				break;
			case 7:
				g.drawImage(num7, 20 + (38 * i), 20);
				break;
			case 8:
				g.drawImage(num8, 20 + (38 * i), 20);
				break;
			case 9:
				g.drawImage(num9, 20 + (38 * i), 20);
				break;
			default:
				break;
			}
		}
	}

	private void renderPlayer(Painter g) {
		if (player.isGrounded()) {
			if (player.isDucked()) {
				g.drawImage(duck, (int) player.getX(),
						(int) player.getY());
			} else {
				runAnim.render(g, (int) player.getX(),
						(int) player.getY(), player.getWidth(),
						player.getHeight());
			}
		} else {
			g.drawImage(jump, (int) player.getX(), (int) player.getY(),
					player.getWidth(), player.getHeight());
		}
	}

	private void renderBlocks(Painter g) {
		for (int i = 0; i < blocks.size(); i++) {
			Block b = blocks.get(i);
			if (b.isVisible()) {
				g.drawImage(block, (int) b.getX(), (int) b.getY(),
						BLOCK_WIDTH, BLOCK_HEIGHT);
			}
		}
	}

	private void renderSun(Painter g) {
		g.setColor(sunStrokeColor);
		g.fillOval(715, -85, 170, 170);
		g.setColor(sunColor);
		g.fillOval(725, -75, 150, 150);
	}

	private void renderClouds(Painter g) {
		g.drawImage(cloud1_image, (int) cloud.getX(), (int) cloud.getY(), 100,
				60);
		g.drawImage(cloud2_image, (int) cloud2.getX(), (int) cloud2.getY(),
				100, 60);
	}

	@Override
	public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {

		if (e.getAction() == MotionEvent.ACTION_DOWN) {
			recentTouchY = scaledY;
		} else if (e.getAction() == MotionEvent.ACTION_UP) {
			if (gamePaused) {
				gamePaused = false;
				return true;
			}
			if (scaledY - recentTouchY < -50) {
				player.jump();
			} else if (scaledY - recentTouchY > 50) {
				player.duck();
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return "PlayState";
	}
}