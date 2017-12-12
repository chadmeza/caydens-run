package com.chadmeza.caydensrun;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.WindowManager;

public class GameMainActivity extends Activity {

	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 450;
	public static GameView sGame;
	public static AssetManager assets;
	
	private static SharedPreferences prefs;
	private static final String highScoreKey = "highScoreKey";
	private static final String musicKey = "musicKey";
	private static boolean music;
	private static int highScore;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		prefs = getPreferences(Activity.MODE_PRIVATE);
		highScore = retrieveHighScore();
		music = retrieveMusic();
		assets = getAssets();
		sGame = new GameView(this, GAME_WIDTH, GAME_HEIGHT);
		setContentView(sGame);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if (music) {
			Assets.onResume();
		}
		
		sGame.onResume();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		Assets.onPause();
		sGame.onPause();
	}
	
	public static void setHighScore(int highScore) {
		GameMainActivity.highScore = highScore;
		Editor editor = prefs.edit();
		editor.putInt(highScoreKey, highScore);
		editor.commit();
	}
	
	private int retrieveHighScore() {
		return prefs.getInt(highScoreKey, 0);
	}
	
	public static int getHighScore() {
		return highScore;
	}
	
	public static void setMusic(boolean music) {
		GameMainActivity.music = music;
		Editor editor = prefs.edit();
		editor.putBoolean(musicKey, music);
		editor.commit();
	}
	
	private boolean retrieveMusic() {
		return prefs.getBoolean(musicKey, true);
	}
	
	public static boolean getMusic() {
		return music;
	}
}
