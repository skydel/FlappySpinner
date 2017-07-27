package com.zephyr.ventum.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.zephyr.ventum.utils.AssetsManager;
import com.zephyr.ventum.utils.Constants;
import com.zephyr.ventum.utils.GamePreferences;

/**
 * Created by sashaklimenko on 7/6/17.
 */

public class Spinner extends BaseActor {

    private TextureRegion textureRegion;
    private Body body;

    public Spinner(Body body) {
        this.body = body;
        GamePreferences gamePreferences = new GamePreferences();
        textureRegion = AssetsManager.getTextureRegion(gamePreferences.getCurrentSkin());

        spinnerRotateAction();
    }

    public float getSpinnerBodyX(){
        return body.getPosition().x;
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(textureRegion,
                body.getPosition().x - Constants.SPINNER_SIZE*2.5f/2,
                body.getPosition().y - Constants.SPINNER_SIZE*2.5f /2,
                Constants.SPINNER_SIZE*2.5f/2,
                Constants.SPINNER_SIZE*2.5f/2,
                Constants.SPINNER_SIZE*2.5f,
                Constants.SPINNER_SIZE*2.5f,
                1, 1, getRotation());
    }

    private void spinnerRotateAction() {
        SequenceAction rotateAction = new SequenceAction();
        rotateAction.addAction(Actions.rotateBy(360, 0.5f, Interpolation.linear));
        RepeatAction infiniteLoop = new RepeatAction();
        infiniteLoop.setCount(RepeatAction.FOREVER);
        infiniteLoop.setAction(rotateAction);
        this.addAction(infiniteLoop);
    }

    public void stopMove(){
        //body.setTransform(body.getPosition().x, body.getPosition().y, 0);
        body.setLinearVelocity(0,0);
    }

    public void jump(float delta) {
        body.applyLinearImpulse(Constants.SPINNER_JUMP_IMPULSE, body.getWorldCenter(), true);
        //body.setTransform(body.getPosition().x, body.getPosition().y + delta*30f, 0);
       // body.setLinearDamping(0.5f);
    }
}
