package io.github.StoneDigge.useless;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import io.github.StoneDigger.BoardGenerators.Board;
import io.github.StoneDigger.BoardGenerators.RandomBoardGenerator;
import io.github.StoneDigger.BoardGenerators.TileType;

import static io.github.StoneDigger.Assets.*;
import static io.github.StoneDigger.CaveDiggerGame.*;

public class BoardActor extends Actor {
    private final Board board;

    public BoardActor() {
        board = new RandomBoardGenerator(0.1f, 0.1f, 0.1f).
            generate(BOARD_WIDTH, BOARD_HEIGHT, START_X, START_Y);
    }
    public BoardActor(Board board) {
        this.board = board;
    }

    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        TileType[][] boardArr = board.getTiles();
        for(int i=0;i<BOARD_WIDTH;i++)
            for(int j=0;j<BOARD_HEIGHT;j++) {
                if(boardArr[i][j] == TileType.DIRT) {
                    batch.draw(DIRT_TEXTURE, 40*i, 28*j, 40, 28);
                }
            }
    }

}
