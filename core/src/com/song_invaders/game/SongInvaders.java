package com.song_invaders.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.song_invaders.game.screens.PlayScreen;

public class SongInvaders extends Game
{
	public SpriteBatch batch;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 480;

	@Override
	public void create () {
		this.batch = new SpriteBatch();

        this.setScreen(new PlayScreen(this));
	}

	@Override
	public void render () { super.render(); }

	@Override
	public void dispose () {
		batch.dispose();
	}
}
