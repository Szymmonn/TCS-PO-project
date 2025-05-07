package io.github.StoneDigger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;

public class Player {
    float breakTimer=0;
    boolean arrowPressed;
    //CaveDigger game;
    Texture caverTexture;
    Sprite caverSprite;

    float worldWidth;
    float worldHeight;
    float caverWidth;
    float caverHeight;

    public Player(Main game) {
        this.game = game;
        this.caverTexture = new Texture("Player.png");
        this.caverSprite = new Sprite(caverTexture);
        this.worldWidth = game.viewport.getWorldWidth();
        this.worldHeight = game.viewport.getWorldHeight();

        //caver size set-up
        caverSprite.setSize(1,1);
        caverSprite.setX(MathUtils.clamp(caverSprite.getX(), 0, worldWidth - caverWidth));
        this.caverWidth = caverSprite.getWidth();
        this.caverHeight = caverSprite.getHeight();

    }

    public void draw() {
        caverSprite.draw(game.spriteBatch);
    }

    public void move(float x,float y) {
        caverSprite.translate(x*caverWidth,y*caverHeight);
    }

    public void handleInput() {
        float delta = Gdx.graphics.getDeltaTime(); //Player nie moze ruszyc sie wiecej niz 1 raz na pol sekundy
        breakTimer+=delta;
        if(breakTimer>=0.2f) {
            breakTimer = 0f;
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                move(1, 0);
            } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                move(-1, 0);
            } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                move(0, 1);
            } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                move(0, -1);
            }
        }
    }

    public int getX_position() {
        return 2;
    }
    public int getY_position() {
        return 2;
    }
}
