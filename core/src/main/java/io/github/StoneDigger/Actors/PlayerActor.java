package io.github.StoneDigger.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import io.github.StoneDigger.BoardGenerators.TileType;
import io.github.StoneDigger.BoardGenerators.Board;

import static io.github.StoneDigger.Assets.PLAYER_TEXTURE;
import static io.github.StoneDigger.Assets.SIZE_TEXTURE;

public class PlayerActor extends Actor {
    private int x=0,y=0;
    private final Sprite sprite;
    private float moveTimer=0;
    private final float moveByDistance = SIZE_TEXTURE+20;
    private Board board;

    public PlayerActor(Board board) {
        sprite = new Sprite(PLAYER_TEXTURE);
        sprite.setSize(SIZE_TEXTURE,SIZE_TEXTURE);
        setPosition(10f, 10f);
        moveBy(moveByDistance, moveByDistance);
        this.board = board;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        super.draw(batch, parentAlpha); // default empty


        sprite.setPosition(getX(),getY());
        sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        moveTimer+=delta;
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            if(checkNextMove(1,0)) {
                moveBy(moveByDistance, 0);
                moveTimer = 0f;
                x++;
            }
            return;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            if(checkNextMove(-1,0)) {
                moveBy(-moveByDistance, 0);
                moveTimer = 0f;
                x--;
            }
            return;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            if(checkNextMove(0,1)) {
                moveBy(0, moveByDistance);
                moveTimer = 0f;
                y++;
            }
            return;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            if(checkNextMove(0,-1)) {
                moveBy(0, -moveByDistance);
                moveTimer = 0f;
                y--;
            }
            return;
        }

        // Ruch ciagly przy przytrzymaniu klawisza
        if (moveTimer >= 0.4f) {
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                if(checkNextMove(1,0)) {
                    moveBy(moveByDistance, 0);
                    moveTimer = 0f;
                    x++;
                }
            } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                if(checkNextMove(-1,0)) {
                    moveBy(-moveByDistance, 0);
                    moveTimer = 0f;
                    x--;
                }
            } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                if(checkNextMove(0,1)) {
                    moveBy(0, moveByDistance);
                    moveTimer = 0f;
                    y++;
                }
            } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                if(checkNextMove(0,-1)) {
                    moveBy(0, -moveByDistance);
                    moveTimer = 0f;
                    y--;
                }
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
