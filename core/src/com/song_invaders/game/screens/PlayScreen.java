package com.song_invaders.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.song_invaders.game.SongInvaders;
import com.song_invaders.game.Sprite.SpaceShip;

/**
 * Created by natha on 9/23/2017.
 */

public class PlayScreen implements Screen
{
    private SongInvaders game;
    private SpriteBatch batch;
    private HudScreen hud;
    private OrthographicCamera camera;
    private SpaceShip spaceShip;

    public PlayScreen(SongInvaders game)
    {
        // Set up Play Screen
        this.game = game;
        this.batch = game.batch;
        this.hud = new HudScreen(batch);
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, SongInvaders.WIDTH, SongInvaders.HEIGHT);

        // Init Sprites
        this.spaceShip = new SpaceShip();
    }

    public boolean gameOver()
    {
        return false;
    }

    private void handleInput(float dtime)
    {
        if (!gameOver())
        {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            {
                // Move player to the left
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            {
                // Move player to the left
            }
            if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.SPACE))
            {
                // Move player to the left
            }
        }
    }

    private void update(float dtime)
    {
        this.camera.update();
    }

    @Override
    public void show()
    {

    }

    @Override
    public void render(float dtime)
    {
        // Update game state
        this.update(dtime);

        // Set up view
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.hud.stage.draw();


        // Draw batch
        this.batch.begin();
        this.batch.end();
    }

    @Override
    public void resize(int width, int height)
    {

    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void hide()
    {

    }

    @Override
    public void dispose()
    {
        this.hud.dispose();
    }
}
