package io.github.StoneDigger.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import io.github.StoneDigger.BoardGenerators.Board;
import io.github.StoneDigger.BoardGenerators.TileType;
import io.github.StoneDigger.TryingToDraw.MyGameScreen;

import static io.github.StoneDigger.Assets.PLAYER_TEXTURE;
import static io.github.StoneDigger.BoardGenerators.TileType.BLOCK_SIZE;
import static io.github.StoneDigger.BoardGenerators.TileType.GAP_SIZE;
import static io.github.StoneDigger.TryingToDraw.MyGameScreen.BOARD_UNIT;

public class PlayerActor extends Actor {
    private int x=0,y=0;
    private final Sprite sprite;
    private float moveTimer = 0;
    private final Board board;

    public PlayerActor(Board board) {
        sprite = new Sprite(PLAYER_TEXTURE);
        sprite.setSize(BLOCK_SIZE,BLOCK_SIZE);
        ///  TODO : change starting position
        //x = 1;
        //y = 1;
        setPosition(BOARD_UNIT + GAP_SIZE/2, BOARD_UNIT + GAP_SIZE/2);  // for now in (1,1)

        this.board = board;
    }



    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color prev = batch.getColor();
        batch.setColor(1,1,1,1);

        super.draw(batch, parentAlpha); // default empty
        sprite.setPosition(getX(),getY());
        sprite.draw(batch);

        batch.setColor(prev);
    }

    @Override
    public void act(float delta) {
        moveTimer+=delta;
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            if(checkNextMove(1,0)) {
                moveBy(BOARD_UNIT, 0);
                moveTimer = 0f;
                x++;
            }
            return;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            if(checkNextMove(-1,0)) {
                moveBy(-BOARD_UNIT, 0);
                moveTimer = 0f;
                x--;
            }
            return;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            if(checkNextMove(0,1)) {
                moveBy(0, BOARD_UNIT);
                moveTimer = 0f;
                y++;
            }
            return;
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            if(checkNextMove(0,-1)) {
                moveBy(0, -BOARD_UNIT);
                moveTimer = 0f;
                y--;
            }
            return;
        }

        // Ruch ciagly przy przytrzymaniu klawisza
        if (moveTimer >= 0.4f) {
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                if(checkNextMove(1,0)) {
                    moveBy(BOARD_UNIT, 0);
                    moveTimer = 0f;
                    x++;
                }
            } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                if(checkNextMove(-1,0)) {
                    moveBy(-BOARD_UNIT, 0);
                    moveTimer = 0f;
                    x--;
                }
            } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                if(checkNextMove(0,1)) {
                    moveBy(0, BOARD_UNIT);
                    moveTimer = 0f;
                    y++;
                }
            } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                if(checkNextMove(0,-1)) {
                    moveBy(0, -BOARD_UNIT);
                    moveTimer = 0f;
                    y--;
                }
            }

        }

        super.act(delta);
        int tileX = (int) ((getX() + getWidth() / 2) / BOARD_UNIT);
        int tileY = (int) ((getY() + getHeight() / 2) / BOARD_UNIT);

        if (board.get(tileX, tileY) == TileType.DIRT) {
            board.set(tileX, tileY, TileType.EMPTY);
        }

        if(board.get(this.x+x+1,this.y+y+1).equals(TileType.DIAMOND)) MyGameScreen.diamondsCollected++;
    }

    public boolean checkNextMove(int x,int y) {
        float nextX = getX() + x* BOARD_UNIT;
        float nextY = getY() + y* BOARD_UNIT;

        //na sciane

        if(board.get(this.x+x+1,this.y+y+1).equals(TileType.WALL)) return false;
        return true;
    }

    public int getPositionX() {
        return x;
    }

    public int getPositionY() {
        return y;
    }
}
