package com.song_invaders.game.sprite;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Keenal on 9/23/2017.
 */

public class SpaceShip {


    Rectangle shape;
    private int speed = 200;
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;

    public SpaceShip(int x, int y)
    {
        this.shape = new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public Rectangle getShape() {
        return shape;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setShape(Rectangle shape) {
        this.shape = shape;
    }

    public void update(){

    }


}
