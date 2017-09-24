package com.song_invaders.game.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.song_invaders.game.SongInvaders;

/**
 * Created by Howtoon on 9/23/17.
 */

public class Target {
    boolean isGood;
    boolean isDone;
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
    private TargetZone zone;

    public Target(int x, int y, World world)
    {
        this.world = world;
        this.zone = new TargetZone(x);

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
        shape.setAsBox(10, 10);

        // FixtureDef is a confusing expression for physical properties
        // Basically this is where you, in addition to defining the shape of the body
        // you also define it's properties like density, restitution and others we will see shortly
        // If you are wondering, density and area are used to calculate over all mass
        fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        //fixtureDef.restitution = 0.7f;
        fixtureDef.filter.categoryBits = SongInvaders.TARGET_BIT;


        fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);

        // Shape is the only disposable of the lot, so get rid of it
        shape.dispose();
    }

    public void update()
    {
        this.x = (int) this.body.getPosition().x;
        this.y = (int) this.body.getPosition().y;
    }

    public Body getBody() {
        return this.body;
    }

    public TargetZone getZone() {
        return this.zone;
    }

    public void inZone() {
        if (this.x > this.zone.getX() - 300 && this.x < this.zone.getX() + 100) {
            if (this.y > this.zone.getY() - 300 && this.y < this.zone.getY() + 100) {
                Filter filter = new Filter();
                filter.maskBits = SongInvaders.NOTHING_BIT;
                for (Fixture fixture : body.getFixtureList())
                    fixture.setFilterData(filter);
                //this.world.destroyBody(this.body);
               // bodyDef.position.set(x, y);
                // Create a body in the world using our definition
                //body = world.createBody(bodyDef);
                //fixture = body.createFixture(fixtureDef);
                //fixture.setUserData(this);
                this.body.setLinearVelocity(0, 0);
                this.body.setGravityScale(0);
                //this.body.applyLinearImpulse(new Vector2(this.x - this.zone.getX(), this.y - this.zone.getY()), this.body.getWorldCenter(), true);
                //this.bodyDef.position.set(this.zone.getX(), this.zone.getY());
               // this.body = this.world.createBody(this.bodyDef);
                //
                System.out.println("X: " + this.zone.getX() + " Y: " + this.zone.getY());
               // this.x = this.zone.getX();
                //this.y = this.zone.getY();
                System.out.println("Gott em'");
            }
        }
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
