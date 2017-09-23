package com.song_invaders.game.sprite;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Shape;

/**
 * Created by Howtoon on 9/23/17.
 */

public class Target {
    boolean isGood;
    boolean isDone;
    TargetZone targetZone;
    int x;
    int y;

    Rectangle shape;
    String sound;

    public Target()
    {

    }

    public void update()
    {

    }

    public void playSound(){

    }

    public boolean isGood() {
        return isGood;
    }

    public void setGood(boolean good) {
        isGood = good;
    }

    public Rectangle getShape() {
        return shape;
    }

    public void setShape(Rectangle shape) {
        this.shape = shape;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

        public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public TargetZone getTargetZone() {
        return targetZone;
    }

    public void setTargetZone(TargetZone targetZone) {
        this.targetZone = targetZone;
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
