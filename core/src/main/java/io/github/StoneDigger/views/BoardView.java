package io.github.StoneDigger.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import io.github.StoneDigger.models.BoardModel;
import io.github.StoneDigger.models.TileType;

import static io.github.StoneDigger.models.Constants.*;

public class BoardView extends Actor {
    private final BoardModel board;

    public BoardView(BoardModel board) {
        this.board = board;
    }

    @Override
    public void act(float draw) {
        // something like tile animation
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color prev = batch.getColor();
        batch.setColor(1,1,1,1);

        for(int i=0;i<BOARD_HEIGHT;i++) {
            for(int j=0;j<BOARD_WIDTH;j++) {
                TileType tileType = board.getTile(i,j);
                int tileXPosition = GAP_SIZE/2 + i*(BLOCK_SIZE + GAP_SIZE);
                int tileYPosition = GAP_SIZE/2 + j*(BLOCK_SIZE + GAP_SIZE);
                batch.draw(TileType.getTexture(tileType), tileXPosition, tileYPosition, BLOCK_SIZE, BLOCK_SIZE);
            }
        }

        batch.setColor(prev);
    }
}
