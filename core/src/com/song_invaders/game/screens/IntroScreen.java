package com.song_invaders.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.song_invaders.game.SongInvaders;

/**
 * Created by natha on 9/24/2017.
 */

public class IntroScreen implements Screen
{
    private SongInvaders game;
    private Viewport viewport;
    private Stage stage;

    public IntroScreen(SongInvaders game)
    {
        this.game = game;
        this.viewport = new FitViewport(SongInvaders.WIDTH, SongInvaders.HEIGHT, new OrthographicCamera());
        this.stage = new Stage(viewport, game.batch);
        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Label titleLabel = new Label("{Title}", font);
        table.add(titleLabel).expandX();
        table.row();

        Label objectiveLabel = new Label("Objective: Shoot as many white balls while trying to avoid shooting the red balls", font);
        Label point1Label = new Label("Points: White Balls 20pts", font);
        Label point2Label = new Label("Red Balls -20pts & -1 life", font);
        Label playLabel = new Label("Click to Play", font);

        table.add(objectiveLabel);
        table.row();
        table.add(point1Label);
        table.row();
        table.add(point2Label);
        table.row();
        table.add(playLabel);

        this.stage.addActor(table);

    }

    @Override
    public void show()
    {

    }

    @Override
    public void render(float delta)
    {
        if (Gdx.input.justTouched()) {
            game.setScreen(new PlayScreen(this.game));
            dispose();
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.stage.draw();
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

    }
}
