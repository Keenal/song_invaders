package com.song_invaders.game.sprite;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Shape;

/**
 * Created by Howtoon on 9/23/17.
 */

public class Target {
    boolean isGood;

    //TargetZone targetZone;
    //position variable?
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
/*
    public TargetZone getTargetZone() {
        return targetZone;
    }

    public void setTargetZone(TargetZone targetZone) {
        this.targetZone = targetZone;
    }
    */
}
