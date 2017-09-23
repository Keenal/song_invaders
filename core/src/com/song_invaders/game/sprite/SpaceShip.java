package com.song_invaders.game.sprite;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Keenal on 9/23/2017.
 */

public class SpaceShip {


    Rectangle shape;
    private int width, height;
    private int speed = 200;

    public SpaceShip(int x, int y, int width, int height)
    {
        this.shape = new Rectangle(x, y, width, height);
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
