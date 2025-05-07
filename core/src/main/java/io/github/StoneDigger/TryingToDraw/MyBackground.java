package io.github.StoneDigger.TryingToDraw;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MyBackground extends Actor {
    @Override
    public void draw(Batch batch, float parentAlpha) {
        Pixmap pixmap = new Pixmap(MyGame.WIDTH, MyGame.HEIGHT, Pixmap.Format.RGBA8888);
        pixmap.setColor(0.6f, 0.3f, 0.1f, 1);   // brown color

        Texture brownBackground = new Texture(pixmap);
        batch.draw(brownBackground, 0,0);
    }
}
