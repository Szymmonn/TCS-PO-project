package io.github.StoneDigger.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import io.github.StoneDigger.BoardGenerators.Board;
import io.github.StoneDigger.BoardGenerators.TileType;

import static io.github.StoneDigger.Assets.PLAYER_TEXTURE;
import static io.github.StoneDigger.Assets.SIZE_TEXTURE;

public class PlayerActor extends Actor {
    private final Sprite sprite;
//    private final Player player;
    private float moveTimer=0;
    private final float moveByDistance;
    private final Board board = null;
    private final float tileSize = 1;

    public PlayerActor() {
        sprite = new Sprite(PLAYER_TEXTURE);
//        player = new Player();
        sprite.setSize(SIZE_TEXTURE,SIZE_TEXTURE);
        setPosition(10f, 10f);
        moveByDistance = SIZE_TEXTURE + 20;

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha); // default empty
        sprite.setPosition(getX(),getY());
        sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            moveBy(moveByDistance, 0);
            moveTimer = 0f;
            return;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            moveBy(-moveByDistance, 0);
            moveTimer = 0f;
            return;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            moveBy(0, moveByDistance);
            moveTimer = 0f;
            return;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            moveBy(0, -moveByDistance);
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

        int tileX = (int) ((getX() + getWidth() / 2) / tileSize);
        int tileY = (int) ((getY() + getHeight() / 2) / tileSize);

        if (board.get(tileX, tileY) == TileType.DIRT) {
            board.set(tileX, tileY, TileType.EMPTY);
        }
    }

    public boolean checkNextMove(int x,int y) {
        float nextX = getX() + x*moveByDistance;
        float nextY = getY() + y*moveByDistance;

        //poza granice mapy
        if(nextX < 0 || nextX > getStage().getWidth()-SIZE_TEXTURE) return false;
        if(nextY < 0 || nextY > getStage().getHeight()-SIZE_TEXTURE) return false;

        //na sciane

        if(board.get(this.x+x,this.y+y).equals(TileType.WALL)) return false;
        return true;
    }

    public float getPositionX() {
        return x;
    }

    public float getPositionY() {
        return y;
    }
}
