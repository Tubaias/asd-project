package com.mygdx.game.io;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class FontDisplayer {
    private BitmapFont font;
    private GlyphLayout layout;

    public FontDisplayer(String path, int size) {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(path));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = size; // font size
        font = generator.generateFont(parameter);
        generator.dispose(); // avoid memory leaks, important
        layout = new GlyphLayout();
    }

    public void drawFont(String str, float x, float y, SpriteBatch batch) {
        layout.setText(font, str);
        font.draw(batch, layout, x - layout.width / 2, y + layout.height / 2);
    }

    public void drawMultiline(float x, float y, SpriteBatch batch, String... lines) {
        String s = "";
        for (String l : lines) {
            s += l + "\n";
        }
        layout.setText(font, s);
        font.draw(batch, layout, x - layout.width / 2, y + layout.height / 2);
    }

    
}