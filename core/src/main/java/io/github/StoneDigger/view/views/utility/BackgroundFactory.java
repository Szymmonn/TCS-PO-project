package io.github.StoneDigger.view.views.utility;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class BackgroundFactory {

    public static Image createSolidBackground(float x, float y, float w, float h) {
        return createSolidBackground(x,y,w,h,Color.BLACK);
    }

    public static Image createSolidBackground(float x, float y, float w, float h, Color color) {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fill();

        Texture texture = new Texture(pixmap);
        pixmap.dispose();

        Image image = new Image(texture);
        image.setPosition(x,y);
        image.setSize(w,h);

        return image;
    }
}
