package com.song_invaders.game.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Keenal on 9/23/2017.
 */

public class SpaceShip {


    Rectangle shape;
    private int speed = 350;
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    public Texture SS;

    public SpaceShip(int x, int y)
    {
        this.shape = new Rectangle(x, y, WIDTH, HEIGHT);
        this.SS = new Texture("img/SS.png");
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
