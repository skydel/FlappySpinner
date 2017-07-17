package com.zephyr.ventum.actors;

import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.zephyr.ventum.utils.TextureHolder;

/**
 * Created by sashaklimenko on 7/17/17.
 */

public class GameButton extends Button {

    public GameButton(float WIDTH, float HEIGHT, String drawable, boolean isCheckable) {

        Skin skin = new Skin();
        skin.addRegions(TextureHolder.getTextureAtlas());

        Button.ButtonStyle buttonStyle = new Button.ButtonStyle();
        buttonStyle.up = skin.getDrawable(drawable);
        buttonStyle.down = skin.getDrawable(drawable + "_pressed");
        if (isCheckable) {
            buttonStyle.checked = skin.getDrawable(drawable + "_pressed");
        }
        setStyle(buttonStyle);
        setSize(WIDTH, HEIGHT);
    }
}
