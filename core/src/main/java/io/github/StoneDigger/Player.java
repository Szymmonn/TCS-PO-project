package io.github.StoneDigger;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;

public class Player {
    Main game;
    Texture CaverTexture;
    Sprite CaverSprite;

    float worldWidth;
    float worldHeight;
    float CaverWidth;
    float CaverHeight;

    public Player(Main game) {
        this.game = game;
        this.CaverTexture = new Texture("Player.png");
        this.CaverSprite = new Sprite(CaverTexture);
        this.worldWidth = game.viewport.getWorldWidth();
        this.worldHeight = game.viewport.getWorldHeight();

        //Caver size set-up
        CaverSprite.setSize(1,1);
        CaverSprite.setX(MathUtils.clamp(CaverSprite.getX(), 0, worldWidth - CaverWidth));
        this.CaverWidth = CaverSprite.getWidth();
        this.CaverHeight = CaverSprite.getHeight();

    }

    public void draw() {
        CaverSprite.draw(game.spriteBatch);
    }

    public void move(float x,float y) {
        CaverSprite.translate(x*CaverWidth,y*CaverHeight);
    }
}
