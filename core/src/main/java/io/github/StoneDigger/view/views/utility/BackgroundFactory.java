package io.github.StoneDigger.view.views.utility;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class BackgroundFactory {

    public static Texture createSolidBackground() {
        return createSolidBackground(Color.BLACK);
    }

    public static Texture createSolidBackground(Color color) {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fill();

        Texture texture = new Texture(pixmap);
        pixmap.dispose();

        return texture;
    }
}
