package com.song_invaders.game.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.song_invaders.game.SongInvaders;

/**
 * Created by Howtoon on 9/23/17.
 */

public class MShip {
    com.song_invaders.game.sprite.Target[] targets;
    com.song_invaders.game.sprite.Column[] columns;
    Rectangle shape;
    private int speed = 50;
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    private boolean moving_left = true;

    public Array<Circle> targetShapes;
    private static final int TARGETSHAPE_SPEED = 300;

    private long shapeFired = 0;

    private static final long FIRE_COOLDOWN = 2000000000L;


    public MShip(int x, int y)
    {
        this.shape = new Rectangle(x, y, WIDTH, HEIGHT);
        this.targetShapes = new Array<Circle>();
    }

    public Rectangle getShape() {
        return shape;
    }

    public void dropTarget() {
        if ((TimeUtils.nanoTime() - this.shapeFired) > FIRE_COOLDOWN) {
            targetShapes.add(new Circle(getShape().x + WIDTH / 2, getShape().y, 20));
            this.shapeFired = TimeUtils.nanoTime();
        }

        for (Circle targetShape : this.targetShapes) {
            targetShape.y -= this.TARGETSHAPE_SPEED * Gdx.graphics.getDeltaTime();
            if (targetShape.y < 0) {
                this.targetShapes.removeValue(targetShape, true);
            }
        }
    }

    public void update()
    {
        dropTarget();

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
