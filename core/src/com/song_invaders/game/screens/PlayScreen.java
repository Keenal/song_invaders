package com.song_invaders.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.song_invaders.game.SongInvaders;
import com.song_invaders.game.sprite.*;

/**
 * Created by natha on 9/23/2017.
 */

public class PlayScreen implements Screen
{
    private SongInvaders game;
    private SpriteBatch batch;
    private ShapeRenderer renderer;
    private OrthographicCamera camera;
    private World world;

    private HudScreen hud;
    private SpaceShip spaceShip;
    private Array<Rectangle> missiles;
    private static final int MISSILE_SPEED = 400;
    private long lastFired = 0;
    private static final long FIRE_COOLDOWN = 1000000000;
    private MShip mShip;
    private Target tmp;

    public PlayScreen(SongInvaders game)
    {
        // Set up Play Screen
        this.game = game;
        this.batch = game.batch;
        this.renderer = new ShapeRenderer();
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, SongInvaders.WIDTH, SongInvaders.HEIGHT);
        this.world = new World(new Vector2(0, -10f), true);

        this.hud = new HudScreen(batch);

        // Init Sprites
        this.spaceShip = new SpaceShip(0, 20);
        this.missiles = new Array<Rectangle>();
        this.mShip = new MShip(SongInvaders.WIDTH - MShip.WIDTH, SongInvaders.HEIGHT - MShip.HEIGHT - 20, this.world);
        this.tmp = new Target((int) this.mShip.getShape().x, (int) this.mShip.getShape().y - 20, this.world);
    }

    public World getWorld() {
        return this.world;
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
                this.spaceShip.getShape().x -= this.spaceShip.getSpeed() * Gdx.graphics.getDeltaTime();
                if (this.spaceShip.getShape().x < 0) {
                    this.spaceShip.getShape().x = 0;
                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            {
                // Move player to the right
                this.spaceShip.getShape().x += this.spaceShip.getSpeed() * Gdx.graphics.getDeltaTime();
                if (this.spaceShip.getShape().x > SongInvaders.WIDTH - SpaceShip.WIDTH) {
                    this.spaceShip.getShape().x = SongInvaders.WIDTH - SpaceShip.WIDTH;
                }
            }
            if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.SPACE))
            {
                // Fire missile
                if ((TimeUtils.nanoTime() - this.lastFired) > FIRE_COOLDOWN) {
                    missiles.add(new Rectangle(this.spaceShip.getShape().x + SpaceShip.WIDTH / 2, this.spaceShip.getShape().y + SpaceShip.HEIGHT, 2, 5));
                    this.lastFired = TimeUtils.nanoTime();
                    SongInvaders.manager.get("audio/sounds/SC/SC/shoot.wav", Sound.class).play();
                }
            }
        }
    }

    private void update(float dtime)
    {
        this.world.step(Gdx.graphics.getDeltaTime(), 6, 2);
        this.handleInput(dtime);
        this.camera.update();

        // Update Sprites
        this.mShip.update();

        // Update missiles
        for (Rectangle missile : this.missiles) {
            missile.y += this.MISSILE_SPEED * Gdx.graphics.getDeltaTime();
            if (missile.y > SongInvaders.HEIGHT)
                this.missiles.removeValue(missile, true);
        }

        this.tmp.update();
    }

    @Override
    public void show()
    {

    }

    @Override
    public void render(float dtime)
    {
        if (this.gameOver())
        {
            this.game.setScreen(new GameOverScreen(game));
            this.dispose();
        }

        // Update game state
        this.update(dtime);

        // Set up view
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.hud.stage.draw();


        // Draw batch
        this.batch.setProjectionMatrix(camera.combined);
        this.batch.begin();
        this.batch.end();

        // Draw shapes
        this.renderer.setProjectionMatrix(camera.combined);
        this.renderer.begin(ShapeRenderer.ShapeType.Filled);
        this.renderer.setColor(1, 0.5f, 0, 1);
        this.renderer.rect(this.spaceShip.getShape().x, this.spaceShip.getShape().y, SpaceShip.WIDTH, SpaceShip.HEIGHT);
        this.renderer.setColor(0.3f, 0.4f, 0.6f, 1);
        this.renderer.rect(this.mShip.getShape().x, this.mShip.getShape().y, MShip.WIDTH, MShip.HEIGHT);
        this.renderer.setColor(1, 1, 1, 1);
        for (Rectangle missile : this.missiles) {
            this.renderer.rect(missile.x, missile.y, missile.width, missile.height);
        }
        this.renderer.rect(this.tmp.getX(), this.tmp.getY(), 20, 20);
        this.renderer.end();

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
        this.world.dispose();
    }
}
