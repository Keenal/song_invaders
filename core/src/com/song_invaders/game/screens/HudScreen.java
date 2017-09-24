package com.song_invaders.game.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.song_invaders.game.SongInvaders;

/**
 * Created by natha on 9/23/2017.
 */

public class HudScreen implements Disposable
{
    public Stage stage;
    private Viewport viewport;

    public static int score;
    public static int lives;

    private static Label scoreLabel;
    private static Label livesLabel;


    public HudScreen(SpriteBatch batch)
    {
        score = 0;
        lives = 3;

        this.viewport = new FitViewport(SongInvaders.WIDTH, SongInvaders.HEIGHT, new OrthographicCamera());
        this.stage = new Stage(this.viewport, batch);
        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        this.scoreLabel = new Label(String.format("Score: %06d", score), font);
        this.livesLabel = new Label(String.format("Lives: %d", lives), font);

        table.add(this.scoreLabel).expandX();
        table.add(this.livesLabel).expandX();

        this.stage.addActor(table);
    }

    public static void addScore(int value)
    {

        score += value;
        scoreLabel.setText(String.format("Score: %06d", score));

    }

    public static void addLife()
    {

        lives++;
        livesLabel.setText(String.format("Lives: %d", lives));

    }

    public static void removeLife()
    {

        lives--;
        livesLabel.setText(String.format("Lives: %d", lives));

    }

    @Override
    public void dispose()
    {
        this.stage.dispose();
    }
}
