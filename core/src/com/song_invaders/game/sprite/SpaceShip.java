package com.song_invaders.game.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Keenal on 9/23/2017.
 */

public class SpaceShip extends Sprite
{
    private World world;
    private Body body;

    private int speed = 200;
    public static final int WIDTH = 20;
    public static final int HEIGHT = 30;
    public Texture SS;

    public SpaceShip(int x, int y, World world)
    {
        super(new Texture("img/SS.png"));
        this.setSize(WIDTH, HEIGHT);
        this.world = world;
        BodyDef bdef = new BodyDef();
        bdef.position.set(x, y);
        bdef.type = BodyDef.BodyType.DynamicBody;
        this.body = this.world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(10);

        fdef.shape = shape;
        body.createFixture(fdef).setUserData(this);
    }

    public Body getBody() {
        return this.body;
    }

    public void update(){
        this.setPosition(this.body.getPosition().x + this.getWidth() / 2, this.body.getPosition().y - this.getHeight());
    }

}
