package com.song_invaders.game.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.song_invaders.game.SongInvaders;

/**
 * Created by natha on 9/23/2017.
 */

public class Missile
{
    private World world;

    private int x, y;
    private Body body;
    private BodyDef bodyDef;
    private PolygonShape shape;
    private FixtureDef fixtureDef;
    private Fixture fixture;

    public Missile(int x, int y, World world)
    {
        this.x = x;
        this.y = y;

        this.world = world;

        // Now create a BodyDefinition.  This defines the physics objects type and position in the simulation
        this.bodyDef = new BodyDef();
        this.bodyDef.type = BodyDef.BodyType.DynamicBody;
        // We are going to use 1 to 1 dimensions.  Meaning 1 in physics engine is 1 pixel
        // Set our body to the same position as our sprite
        this.bodyDef.position.set(x, y);

        // Create a body in the world using our definition
        this.body = world.createBody(bodyDef);

        // Now define the dimensions of the physics shape
        this.shape = new PolygonShape();
        // We are a box, so this makes sense, no?
        // Basically set the physics polygon to a box with the same dimension as our sprite
        this.shape.setAsBox(2, 5);

        // FixtureDef is a confusing expression for physical properties
        // Basically this is where you, in addition to defining the shape of the body
        // you also define it's properties like density, restitution and others we will see shortly
        // If you are wondering, density and area are used to calculate over all mass
        this.fixtureDef = new FixtureDef();
        this.fixtureDef.shape = this.shape;
        this.fixtureDef.density = 1f;
        this.fixtureDef.filter.categoryBits = SongInvaders.MISSILE_BIT;
        this.fixtureDef.filter.maskBits = SongInvaders.TARGET_BIT;

        this.fixture = body.createFixture(fixtureDef);
        this.fixture.setUserData(this);

        // Shape is the only disposable of the lot, so get rid of it
        this.shape.dispose();

        this.body.setGravityScale(0);
    }

    public void playSound() {
        SongInvaders.manager.get("audio/sounds/SC/SC/shoot.wav", Sound.class).play();
    }

    public void update() {
        this.x = (int) this.body.getPosition().x;
        this.y = (int) this.body.getPosition().y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Body getBody() {
        return this.body;
    }
}
