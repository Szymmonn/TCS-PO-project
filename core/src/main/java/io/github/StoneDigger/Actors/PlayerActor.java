package io.github.StoneDigger.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static io.github.StoneDigger.Assets.PLAYER_TEXTURE;

public class PlayerActor extends Actor {
    private final Sprite sprite;
//    private final Player player;
    private float moveTimer=0;
    private final float playerHeight;

    public PlayerActor() {
        sprite = new Sprite(PLAYER_TEXTURE);
//        player = new Player();
        sprite.setSize(300,300);
        playerHeight = 300;

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
//        super.draw(batch, parentAlpha);
//        float x_pos = 40 * player.getX_position();
//        float y_pos = 24 * player.getY_position();
//
        sprite.setPosition(getX(),getY());
        sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            moveBy(playerHeight, 0);
            moveTimer = 0f;
            return;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            moveBy(-playerHeight, 0);
            moveTimer = 0f;
            return;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            moveBy(0, playerHeight);
            moveTimer = 0f;
            return;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            moveBy(0, -playerHeight);
            moveTimer = 0f;
            return;
        }

        // Ruch ciągły przy przytrzymaniu klawisza
        if (moveTimer >= 0.4f) {
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                moveBy(1, 0);
                moveTimer = 0f;
            } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                moveBy(-1, 0);
                moveTimer = 0f;
            } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                moveBy(0, 1);
                moveTimer = 0f;
            } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                moveBy(0, -1);
                moveTimer = 0f;
            }
        }
    }
}
