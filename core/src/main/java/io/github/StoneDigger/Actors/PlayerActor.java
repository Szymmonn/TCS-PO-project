package io.github.StoneDigger.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import io.github.StoneDigger.BoardGenerators.Board;
import io.github.StoneDigger.BoardGenerators.TileType;
import io.github.StoneDigger.TryingToDraw.MyGameScreen;

import java.util.Map;

import static io.github.StoneDigger.Assets.PLAYER_TEXTURE;
import static io.github.StoneDigger.Assets.SIZE_TEXTURE;

public class PlayerActor extends Actor {
    private int x=1,y=1;
    private final Sprite sprite;
    private float moveTimer = 0;
    private final float moveByDistance;
    private final Board board;
    private float rockDropTimer=0;


    public PlayerActor(Board board) {
        sprite = new Sprite(PLAYER_TEXTURE);
        sprite.setSize(SIZE_TEXTURE,SIZE_TEXTURE);
        setPosition(10f, 10f);
        moveByDistance = SIZE_TEXTURE + 20;
        moveBy(moveByDistance,moveByDistance);
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
        updateFallingBlocks(delta);
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
        if (moveTimer >= 0.25f) {
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

        super.act(delta);
        int tileX = (int) ((getX() + getWidth() / 2) / moveByDistance);
        int tileY = (int) ((getY() + getHeight() / 2) / moveByDistance);

        if (board.get(tileX, tileY) == TileType.DIRT) {
            board.set(tileX, tileY, TileType.EMPTY);

        }

        if(board.get(this.x,this.y).equals(TileType.DIAMOND)) {
            board.set(this.x,this.y,TileType.EMPTY);
            MyGameScreen.diamondsCollected++;
        }
    }

    public boolean checkNextMove(int x,int y) {
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
                if(tiles[i][j] != TileType.ROCK) continue;
                if(tiles[i][j - 1] == TileType.EMPTY) {
                    board.set(i, j - 1, TileType.ROCK);
                    board.set(i, j, TileType.EMPTY);
                    fallingStone[i][j] = false;
                    fallingStone[i][j-1] = true;
                    continue;
                }
                if(fallingStone[i][j]) {
                    if(tiles[i-1][j] == TileType.EMPTY && tiles[i-1][j-1] == TileType.EMPTY) {
                        board.set(i-1, j, TileType.ROCK);
                        board.set(i, j, TileType.EMPTY);
                        fallingStone[i][j] = false;
                        fallingStone[i-1][j] = true;
                    }
                    if(tiles[i+1][j] == TileType.EMPTY && tiles[i+1][j-1] == TileType.EMPTY) {
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

    public float getPositionX() {
        return x;
    }

    public float getPositionY() {
        return y;
    }
}
