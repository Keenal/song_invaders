package com.song_invaders.game.sprite;

import com.song_invaders.game.SongInvaders;

import java.util.Random;

/**
 * Created by Keenal on 9/23/2017.
 */

public class TargetZone {

    private Random rand;
    int x;
    int y;

    public TargetZone(int x) {
        this.x = x;
        rand = new Random();
        this.y = rand.nextInt(SongInvaders.HEIGHT);
    }

    public void update(){

    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
