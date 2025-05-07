package io.github.StoneDigger.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static io.github.StoneDigger.Assets.PLAYER_TEXTURE;
import static io.github.StoneDigger.Assets.SIZE_TEXTURE;

public class PlayerActor extends Actor {
    private int x=0,y=0;
    private final Sprite sprite;
//    private final Player player;
    private float moveTimer=0;
    private final float playerHeight;
    private float clampedX;
    private float clampedY;

    public PlayerActor() {
        sprite = new Sprite(PLAYER_TEXTURE);
//        player = new Player();
        sprite.setSize(SIZE_TEXTURE,SIZE_TEXTURE);
        setPosition(10f,10f);
        playerHeight = SIZE_TEXTURE+20;

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        clampedX = getStage().getWidth()-SIZE_TEXTURE;
        clampedY = getStage().getHeight()-SIZE_TEXTURE;
//        super.draw(batch, parentAlpha);
//        float x_pos = 40 * player.getX_position();
//        float y_pos = 24 * player.getY_position();

        sprite.setPosition(getX(),getY());
        sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        moveTimer+=delta;
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            moveBy(playerHeight, 0);
            moveTimer = 0f;
            x++;
            clamp(1);
            return;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            moveBy(-playerHeight, 0);
            moveTimer = 0f;
            x--;
            clamp(-1);
            return;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            moveBy(0, playerHeight);
            moveTimer = 0f;
            y++;
            clamp(1);
            return;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            moveBy(0, -playerHeight);
            moveTimer = 0f;
            y--;
            clamp(-1);
            return;
        }

        // Ruch ciagly przy przytrzymaniu klawisza
        if (moveTimer >= 0.4f) {
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                moveBy(playerHeight, 0);
                moveTimer = 0f;
                x++;
                clamp(1);
            } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                moveBy(-playerHeight, 0);
                moveTimer = 0f;
                x--;
                clamp(-1);
            } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                moveBy(0, playerHeight);
                moveTimer = 0f;
                y++;
                clamp(1);
            } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                moveBy(0, -playerHeight);
                y--;
                moveTimer = 0f;
                clamp(-1);
            }

        }
    }
    public void clamp(int value) {
        if(getX()<0 || getX()>clampedX) {
            moveBy(value*playerHeight,0); x+=value;
        } else if(getY()<0 || getY()>clampedY) {
            moveBy(0,value*playerHeight); y+=value;
        }
    }

    public float getPositionX() {
        return x;
    }

    public float getPositionY() {
        return y;
    }
}
