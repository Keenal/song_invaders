package com.song_invaders.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.song_invaders.game.screens.PlayScreen;

public class SongInvaders extends Game
{
	public SpriteBatch batch;
    public static AssetManager manager;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 480;

    public static final short NOTHING_BIT = 0;
    public static final short MISSILE_BIT = 1;
    public static final short TARGET_BIT = 2;
    public static final short BAD_TARGET_BIT = 4;


    @Override
	public void create () {
		this.batch = new SpriteBatch();
        manager = new AssetManager();

        manager.load("audio/sounds/SC/SC/SC_1.wav", Sound.class);
        manager.load("audio/sounds/SC/SC/SC_2.wav", Sound.class);
        manager.load("audio/sounds/SC/SC/SC_3.wav", Sound.class);
        manager.load("audio/sounds/SC/SC/SC_4.wav", Sound.class);
        manager.load("audio/sounds/SC/SC/shoot.wav", Sound.class);
        manager.load("audio/sounds/SC/SC/SC_full.wav", Music.class);
        manager.finishLoading();



        this.setScreen(new PlayScreen(this));
	}

	@Override
	public void render () { super.render(); }

	@Override
	public void dispose () {
		batch.dispose();
	}



}
