package io.github.StoneDigger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;

public class Player {
    float moveTimer;
    Main game;
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

    public void updateMotion() {
        moveTimer += Gdx.graphics.getDeltaTime();

        // Pojedynczy ruch - bez czekania
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            move(1, 0);
            moveTimer = 0f;
            return;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            move(-1, 0);
            moveTimer = 0f;
            return;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            move(0, 1);
            moveTimer = 0f;
            return;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            move(0, -1);
            moveTimer = 0f;
            return;
        }

        // Ruch ciągły przy przytrzymaniu klawisza
        if (moveTimer >= 0.4f) {
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                move(1, 0);
                moveTimer = 0f;
            } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                move(-1, 0);
                moveTimer = 0f;
            } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                move(0, 1);
                moveTimer = 0f;
            } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                move(0, -1);
                moveTimer = 0f;
            }
        }
    }

}
