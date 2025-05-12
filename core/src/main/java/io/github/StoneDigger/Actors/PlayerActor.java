package io.github.StoneDigger.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import io.github.StoneDigger.BoardGenerators.Board;
import io.github.StoneDigger.BoardGenerators.TileType;
import io.github.StoneDigger.TryingToDraw.MyGameScreen;
import java.util.Map;

import static io.github.StoneDigger.Assets.PLAYER_TEXTURE;
import static io.github.StoneDigger.BoardGenerators.TileType.BLOCK_SIZE;
import static io.github.StoneDigger.BoardGenerators.TileType.GAP_SIZE;
import static io.github.StoneDigger.TryingToDraw.MyGameScreen.BOARD_UNIT;

public class PlayerActor extends Actor {
    private int x=1,y=1;
    private final Sprite sprite;
    private float moveTimer = 0;
    private final Board board;
    private RockObserver rockObserver;

    public PlayerActor(Board board) {
        sprite = new Sprite(PLAYER_TEXTURE);
        sprite.setSize(BLOCK_SIZE,BLOCK_SIZE);
        setPosition(BOARD_UNIT + GAP_SIZE/2, BOARD_UNIT + GAP_SIZE/2);  // for now in (1,1)

        this.board = board;
        this.rockObserver = new RockObserver(board);
        board.addObserver(rockObserver);
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
        rockObserver.update(delta);
        if(board.get(x,y) == TileType.ROCK) restart();
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
        if (moveTimer >= 0.18f) {
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

        if(board.get(this.x,this.y).equals(TileType.DIAMOND)) {
            board.set(this.x,this.y,TileType.EMPTY);
            MyGameScreen.diamondsCollected++;
        }
    }

    public boolean checkNextMove(int x,int y) {
        float nextX = getX() + x* BOARD_UNIT;
        float nextY = getY() + y* BOARD_UNIT;

        //na sciane
        if(board.get(this.x+x,this.y+y).equals(TileType.WALL)) return false;

        //na kamien jesli dalej nie ma pustego
        if(board.get(this.x+x,this.y+y) == TileType.ROCK) {
            if (board.get(this.x + 2 * x, this.y + 2 * y) != TileType.EMPTY) return false;
            else {
                board.set(this.x + 2 * x, this.y + 2 * y,TileType.ROCK);
                board.set(this.x+x,this.y+y,TileType.EMPTY);
                return true;
            }
        }
        return true;
    }

    public void restart() {
        /// TO DO: Exploding blocks
        x = 1;
        y = 1;
        setPosition(x * BOARD_UNIT + GAP_SIZE / 2, y * BOARD_UNIT + GAP_SIZE / 2);

    }

    public int getPositionX() {
        return x;
    }

    public int getPositionY() {
        return y;
    }
}
