package com.song_invaders.game.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.song_invaders.game.SongInvaders;

import java.sql.Time;

/**
 * Created by Howtoon on 9/23/17.
 */

public class MShip {
    private

    Target[] targets;
    Column[] columns;
    Rectangle shape;
    private int speed = 50;
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    private boolean moving_left = true;

    public Array<Circle> targetShapes;
    public Array<Circle> targetBadShapes;
    private static final int TARGETSHAPE_SPEED = 50;
    public boolean bounce = false;
    private long lastBounce = 0;
    public static final long BOUNCE_COOLDOWN = 10000000000L;


    private long shapeFired = 0;
    private long badShapeFired = 1000000000L;

    private static final long FIRE_COOLDOWN = 2000000000L;
    private static final long FIRE_COOLDOWN_BAD = 11000000000L;

    public Texture MJ;


    public MShip(int x, int y, World world)
    {
        this.shape = new Rectangle(x, y, WIDTH, HEIGHT);
        this.targetShapes = new Array<Circle>();
        this.targetBadShapes = new Array<Circle>();
        this.MJ = new Texture("img/MJ.png");
    }

    public Rectangle getShape() {
        return shape;
    }

    public void dropTarget() {
        if ((TimeUtils.nanoTime() - this.shapeFired) > FIRE_COOLDOWN) {
            targetShapes.add(new Circle(getShape().x + WIDTH / 2, getShape().y, 10));
            this.shapeFired = TimeUtils.nanoTime();
        }
        for (Circle targetShape : this.targetShapes) {
            if (!this.bounce) {
                targetShape.y -= this.TARGETSHAPE_SPEED * Gdx.graphics.getDeltaTime();
            }
            else {
                targetShape.y += (this.TARGETSHAPE_SPEED * 10) * Gdx.graphics.getDeltaTime();
                if ((TimeUtils.nanoTime() - lastBounce) > BOUNCE_COOLDOWN) {
                    this.bounce = false;
                }
                lastBounce = TimeUtils.nanoTime();
            }
            if (targetShape.y < 0) {
                this.targetShapes.removeValue(targetShape, true);
            }
        }
    }

    public void dropBadTarget() {
        if ((TimeUtils.nanoTime() - this.badShapeFired) > FIRE_COOLDOWN_BAD) {
            targetBadShapes.add(new Circle(getShape().x + WIDTH / 2, getShape().y, 10));
            this.badShapeFired = TimeUtils.nanoTime();
        }

        for (Circle targetBadShape : this.targetBadShapes) {
            targetBadShape.y -= this.TARGETSHAPE_SPEED * Gdx.graphics.getDeltaTime();
            if (targetBadShape.y < 0) {
                this.targetBadShapes.removeValue(targetBadShape, true);
            }
        }
    }

    public void update()
    {
        dropTarget();

        dropBadTarget();

        if (this.moving_left) {
            this.shape.x -= this.speed * Gdx.graphics.getDeltaTime();
        }
        else {
            this.shape.x += this.speed * Gdx.graphics.getDeltaTime();
        }

        if (this.shape.x < 0) {
            this.moving_left = false;
        }
        else if (this.shape.x > SongInvaders.WIDTH - WIDTH){
            this.moving_left = true;
        }

    }
}
