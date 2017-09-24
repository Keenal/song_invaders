package com.song_invaders.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
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
    private static final int TARGETSHAPE_SPEED = 300;
    private long lastFired = 0;
    private long shapeFired = 0;
    private static final long FIRE_COOLDOWN = 1000000000;
    private MShip mShip;
    //private Target tmp;

    public PlayScreen(SongInvaders game)
    {
        // Set up Play Screen
        this.game = game;
        this.batch = game.batch;
        this.renderer = new ShapeRenderer();
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, SongInvaders.WIDTH, SongInvaders.HEIGHT);
        this.world = new World(new Vector2(0, 0), true);

        this.hud = new HudScreen(batch);

        // Init Sprites
        this.spaceShip = new SpaceShip(0, 40, this.world);
        this.missiles = new Array<Rectangle>();
        this.mShip = new MShip(SongInvaders.WIDTH - MShip.WIDTH, SongInvaders.HEIGHT - MShip.HEIGHT - 20, this.world);
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
                this.spaceShip.getBody().applyLinearImpulse(new Vector2(-4f, 0), this.spaceShip.getBody().getWorldCenter(), true);
            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            {
                // Move player to the right
                this.spaceShip.getBody().applyLinearImpulse(new Vector2(4f, 0), this.spaceShip.getBody().getWorldCenter(), true);

            }
            if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.SPACE))
            {
                // Fire missile
                if ((TimeUtils.nanoTime() - this.lastFired) > FIRE_COOLDOWN) {
                    missiles.add(new Rectangle(this.spaceShip.getX() + SpaceShip.WIDTH / 2, this.spaceShip.getY() + SpaceShip.HEIGHT, 2, 5));
                    this.lastFired = TimeUtils.nanoTime();
                    SongInvaders.manager.get("audio/sounds/SC/SC/shoot.wav", Sound.class).play();
                }
            }

           /*
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN))

            {
                // Move player to the left

                if ((TimeUtils.nanoTime() - this.shapeFired) > FIRE_COOLDOWN) {
                   targetShapes.add(new Circle(this.mShip.getShape().x + mShip.WIDTH / 2, this.mShip.getShape().y, 20));
                    this.shapeFired = TimeUtils.nanoTime();
                }
            }
            */
        }
    }

    public void checkCollisions() {
        for (Rectangle missile : this.missiles) {
            for (Circle targetShape : this.mShip.targetShapes) {
                double dist = this.euclidDist((int) (targetShape.x), (int) (targetShape.y), (int) (missile.x), (int) (missile.y));

                if (dist < targetShape.radius / 2 + missile.width || dist < targetShape.radius / 2 + missile.height) {
                    this.mShip.targetShapes.removeValue(targetShape, true);
                    this.hud.addScore(20);
                    this.missiles.removeValue(missile, true);
                    break;
                }
            }
        }
    }

    public double euclidDist(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    private void update(float dtime)
    {
        this.world.step(Gdx.graphics.getDeltaTime(), 6, 2);
        this.handleInput(dtime);
        this.camera.update();

        // Update Sprites
        this.checkCollisions();
        this.mShip.update();
        this.spaceShip.update();
        //System.out.println("X: " + this.spaceShip.getX() + " Y: " + this.spaceShip.getY());

        // Update missiles
        for (Rectangle missile : this.missiles) {
            missile.y += this.MISSILE_SPEED * Gdx.graphics.getDeltaTime();
            if (missile.y > SongInvaders.HEIGHT)
                this.missiles.removeValue(missile, true);

        }

        //this.tmp.update();
        /*
        for (Circle targetShape : this.targetShapes) {
            targetShape.y -= this.TARGETSHAPE_SPEED* Gdx.graphics.getDeltaTime();
            if (targetShape.y < 0) {
                this.targetShapes.removeValue(targetShape, true);
            }
        }
        */
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
        this.spaceShip.draw(batch);
        //this.batch.draw(this.spaceShip.SS, this.spaceShip.getX(), this.spaceShip.getY(), SpaceShip.WIDTH, SpaceShip.HEIGHT);
        this.batch.draw(this.mShip.MJ, this.mShip.getShape().x, this.mShip.getShape().y, MShip.WIDTH, MShip.HEIGHT);
        this.batch.end();

        // Draw shapes
        this.renderer.setProjectionMatrix(camera.combined);
        this.renderer.begin(ShapeRenderer.ShapeType.Filled);
        this.renderer.setColor(1, 0.5f, 0, 1);
        //this.renderer.rect(this.spaceShip.getShape().x, this.spaceShip.getShape().y, SpaceShip.WIDTH, SpaceShip.HEIGHT);
        this.renderer.setColor(0.3f, 0.4f, 0.6f, 1);
        //this.renderer.rect(this.mShip.getShape().x, this.mShip.getShape().y, MShip.WIDTH, MShip.HEIGHT);
        this.renderer.setColor(1, 1, 1, 1);
        for (Rectangle missile : this.missiles) {
            this.renderer.rect(missile.x, missile.y, missile.width, missile.height);
        }
        //this.renderer.rect(this.tmp.getX(), this.tmp.getY(), 20, 20);
        for (Circle targetShape : mShip.targetShapes) {
            this.renderer.circle(targetShape.x, targetShape.y, targetShape.radius);
        }

        this.renderer.setColor(1, 0, 0, 1);
        for (Circle targetBadShape : mShip.targetBadShapes) {
            this.renderer.circle(targetBadShape.x, targetBadShape.y, targetBadShape.radius);
        }
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
