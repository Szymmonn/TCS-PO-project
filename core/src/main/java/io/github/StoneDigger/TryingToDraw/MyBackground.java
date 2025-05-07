package io.github.StoneDigger.TryingToDraw;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MyBackground extends Actor {
    private final Texture background;

    public MyBackground() {
        // 1. Allocate and paint once
        Pixmap pix = new Pixmap(MyGame.WIDTH, MyGame.HEIGHT, Pixmap.Format.RGBA8888);
        pix.setColor(0.6f, 0.3f, 0.1f, 1);
        pix.fill();                           // <— actually paint
        background = new Texture(pix);
        pix.dispose();                        // free the Pixmap data
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // you can also modulate alpha:
        Color prev = batch.getColor();
        batch.setColor(1f,1,1, parentAlpha);    // works like "nasycenie kolorow"
        batch.draw(background, 0, 0);
        batch.setColor(prev);
    }


    @Override
    public void act(float delta) {
    }

}
