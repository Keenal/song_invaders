package com.song_invaders.game.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Howtoon on 9/23/17.
 */

public class Target {
    boolean isGood;
    boolean isDone;
    TargetZone targetZone;
    int x;
    int y;
    BodyDef bodyDef;
    Rectangle rectangle;
    String sound;
    World world;
    Body body;
    PolygonShape shape;
    FixtureDef fixtureDef;
    Fixture fixture;

    public Target(int x, int y, World world)
    {
        this.world = world;

        // Now create a BodyDefinition.  This defines the physics objects type and position in the simulation
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        // We are going to use 1 to 1 dimensions.  Meaning 1 in physics engine is 1 pixel
        // Set our body to the same position as our sprite
        bodyDef.position.set(x, y);

        // Create a body in the world using our definition
        body = world.createBody(bodyDef);

        // Now define the dimensions of the physics shape
        shape = new PolygonShape();
        // We are a box, so this makes sense, no?
        // Basically set the physics polygon to a box with the same dimension as our sprite
        shape.setAsBox(20, 20);

        // FixtureDef is a confusing expression for physical properties
        // Basically this is where you, in addition to defining the shape of the body
        // you also define it's properties like density, restitution and others we will see shortly
        // If you are wondering, density and area are used to calculate over all mass
        fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;

        fixture = body.createFixture(fixtureDef);

        // Shape is the only disposable of the lot, so get rid of it
        shape.dispose();
    }

    public void update()
    {
        this.x = (int) this.body.getPosition().x;
        this.y = (int) this.body.getPosition().y;
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
        return rectangle;
    }

    public void setShape(Rectangle shape) {
        this.rectangle = rectangle;
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
