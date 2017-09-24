package com.song_invaders.game.tools;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.song_invaders.game.SongInvaders;
import com.song_invaders.game.screens.PlayScreen;
import com.song_invaders.game.sprite.Missile;
import com.song_invaders.game.sprite.Target;

/**
 * Created by natha on 9/23/2017.
 */

public class WorldContactListener implements ContactListener
{
    @Override
    public void beginContact(Contact contact)
    {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int cdef = fixA.getFilterData().categoryBits;
        if (cdef == SongInvaders.MISSILE_BIT)
        {
            PlayScreen.removeMissile((Missile) fixA.getUserData());
            ((Target) fixB.getUserData()).getBody().setLinearVelocity(0, 30f);
            ((Target) fixB.getUserData()).inZone();
        }
        else {
            PlayScreen.removeMissile((Missile) fixB.getUserData());
            ((Target) fixA.getUserData()).getBody().setLinearVelocity(0, 30f);
            ((Target) fixA.getUserData()).inZone();
        }

    }

    @Override
    public void endContact(Contact contact)
    {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold)
    {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse)
    {

    }
}
