package com.song_invaders.game.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
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

    public MShip(int x, int y)
    {
        this.shape = new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public Rectangle getShape() {
        return shape;
    }

    public void dropTarget()
    {

    }

    public void update()
    {
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
