package io.github.StoneDigger.Actors;

import com.badlogic.gdx.Gdx;
import io.github.StoneDigger.BoardGenerators.Board;
import io.github.StoneDigger.BoardGenerators.TileType;

public class RockObserver implements BoardObserver {
    int[][] fallingStone;
    private Board board;
    float rockDropTimer=0;



    public RockObserver(Board board) {
        this.fallingStone = new int[board.getWidth()][board.getHeight()];
        this.board = board;
    }

    public void update(float delta) {
        rockDropTimer += delta;

    }

    @Override
    public void onTileChanged(int x, int y, TileType previousType, Board board) {
        if (previousType != TileType.DIRT || board.get(x, y) != TileType.EMPTY) {
            return;
        }
        if (y < board.getHeight() - 1 && board.get(x, y + 1) == TileType.ROCK) {
            startFalling(x, y + 1);
        }
    }

    private void startFalling(int x, int y) {
        fallingStone[x][y] = 1;
        updateFallingBlocks();
    }

    public void updateFallingBlocks() {
        if(rockDropTimer<0.3f) return;
        TileType[][] tiles = board.getOriginalTilesArray();
//        rockDropTimer+=delta; //timer czeka az minie 0.2 sekundy, i leci z kolejna petla aby obnizyc
        boolean keepFalling=true;

        while(keepFalling) {
            if(rockDropTimer<0.3f) {
                continue;
            }
            //czeka 0.2 sekundy
            keepFalling = false;

            for (int j = 1; j < tiles[0].length; j++) {
                for (int i = 1; i < tiles.length; i++) {
                    //jak nie kamień to dupa
                    if (tiles[i][j] != TileType.ROCK) continue;

                    //jak pod to idziemy
                    if (tiles[i][j - 1] == TileType.EMPTY) {
                        board.set(i, j - 1, TileType.ROCK);
                        board.set(i, j, TileType.EMPTY);
                        fallingStone[i][j - 1] = fallingStone[i][j] + 1;
                        fallingStone[i][j] = 0;
                        keepFalling = true;
                        continue;
                    }

                    //jesli na lewo lub prawo to tom idziemy
                    if (fallingStone[i][j] != 0) {
                        if (tiles[i + 1][j] == TileType.EMPTY && tiles[i + 1][j - 1] == TileType.EMPTY) {
                            board.set(i + 1, j, TileType.ROCK);
                            board.set(i, j, TileType.EMPTY);
                            fallingStone[i + 1][j] = fallingStone[i][j] + 1;
                            fallingStone[i][j] = 0;
                            keepFalling = true;
                            i++;
                        } else if (tiles[i - 1][j] == TileType.EMPTY && tiles[i - 1][j - 1] == TileType.EMPTY) {
                            board.set(i - 1, j, TileType.ROCK);
                            board.set(i, j, TileType.EMPTY);
                            fallingStone[i - 1][j] = fallingStone[i][j] + 1;
                            fallingStone[i][j] = 0;
                            keepFalling = true;
                        }
                    }
                }
            }
            rockDropTimer = 0f;
        }
    }
}
