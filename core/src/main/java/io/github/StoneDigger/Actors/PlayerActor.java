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
    private float rockDropTimer=0;


    public PlayerActor(Board board) {
        sprite = new Sprite(PLAYER_TEXTURE);
        sprite.setSize(BLOCK_SIZE,BLOCK_SIZE);
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
        updateFallingBlocks(delta);
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
        if (moveTimer >= 0.25f) {
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
        return true;
    }

    public void updateFallingBlocks(float delta) {
//        rockDropTimer+=delta; //timer czeka az minie 0.2 sekundy, i leci z kolejna petla aby obnizyc
        rockDropTimer+=delta;
        TileType[][] tiles = board.getOriginalTilesArray();
        boolean[][] fallingStone = new boolean[board.getWidth()][board.getHeight()];

        //czeka 0.2 sekundy
        if(rockDropTimer<0.5f) return;

        for (int j = 1; j < tiles[0].length; j++) {
            for (int i = 1; i < tiles.length; i++) {
                //jak nie kamień to dupa
                if(board.get(i,j) != TileType.ROCK) continue;

                //jak pod to idziemy
                if(board.get(i,j) == TileType.EMPTY) {
                    board.set(i, j - 1, TileType.ROCK);
                    board.set(i, j, TileType.EMPTY);
                    fallingStone[i][j] = false;
                    fallingStone[i][j-1] = true;
                    continue;
                }

                //jesli na lewo lub prawo to tom idziemy
                if(fallingStone[i][j]) {
                    if(board.get(i-1,j) == TileType.EMPTY && board.get(i-1,j-1) == TileType.EMPTY) {
                        board.set(i-1, j, TileType.ROCK);
                        board.set(i, j, TileType.EMPTY);
                        fallingStone[i][j] = false;
                        fallingStone[i-1][j] = true;
                    }
                    if(board.get(i+1,j) == TileType.EMPTY && board.get(i+1,j-1) == TileType.EMPTY) {
                        board.set(i+1, j, TileType.ROCK);
                        board.set(i, j, TileType.EMPTY);
                        fallingStone[i][j] = false;
                        fallingStone[i+1][j] = true;
                    }
                }
            }
        }

        rockDropTimer=0f;
    }

    public int getPositionX() {
        return x;
    }

    public int getPositionY() {
        return y;
    }
}
