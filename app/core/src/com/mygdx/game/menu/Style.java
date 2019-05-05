package com.mygdx.game.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class Style {

    private static TextButtonStyle style;

    public static TextButtonStyle getStyle() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/vcr_mono.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();

        parameter.size = 42; // font size
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();

        style = new TextButtonStyle();
        style.font = font;

        return style;
    }

}