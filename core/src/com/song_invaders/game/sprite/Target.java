package com.song_invaders.game.sprite;

import com.badlogic.gdx.math.Rectangle;

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
        img = new Texture("badlogic.jpg");
        sprite = new Sprite(img);

        // Center the sprite in the top/middle of the screen
        sprite.setPosition(Gdx.graphics.getWidth() / 2 - sprite.getWidth() / 2,
                Gdx.graphics.getHeight() / 2);

        // Create a physics world, the heart of the simulation.  The Vector
        passed in is gravity
        world = new World(new Vector2(0, -98f), true);

        // Now create a BodyDefinition.  This defines the physics objects type
        and position in the simulation
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        // We are going to use 1 to 1 dimensions.  Meaning 1 in physics engine
        is 1 pixel
        // Set our body to the same position as our sprite
        bodyDef.position.set(sprite.getX(), sprite.getY());

        // Create a body in the world using our definition
        body = world.createBody(bodyDef);

        // Now define the dimensions of the physics shape
        PolygonShape shape = new PolygonShape();
        // We are a box, so this makes sense, no?
        // Basically set the physics polygon to a box with the same dimensions
        as our sprite
        shape.setAsBox(sprite.getWidth()/2, sprite.getHeight()/2);

        // FixtureDef is a confusing expression for physical properties
        // Basically this is where you, in addition to defining the shape of the
        body
        // you also define it's properties like density, restitution and others
        we will see shortly
        // If you are wondering, density and area are used to calculate over all
        mass
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;

        Fixture fixture = body.createFixture(fixtureDef);

        // Shape is the only disposable of the lot, so get rid of it
        shape.dispose();
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
