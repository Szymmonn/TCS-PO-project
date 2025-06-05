package io.github.StoneDigger.view.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import io.github.StoneDigger.model.Boards.Board;
import io.github.StoneDigger.model.Boards.IBoard;
import io.github.StoneDigger.model.GameObjects.Tiles.ATile;
import io.github.StoneDigger.view.views.utility.TileToTexture;

import static io.github.StoneDigger.view.screen.GameScreen.*;

public class BoardView extends Actor {
    private IBoard board;

    public BoardView(IBoard board) {
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
        for(int i=0;i<board.getWidth();i++) {
            for(int j=0;j<board.getHeight();j++) {
                ATile tileType = board.getTile(new GridPoint2(i, j));
                int tileXPosition = i * (BLOCK_SIZE + GAP_SIZE);
                int tileYPosition = j * (BLOCK_SIZE + GAP_SIZE);
                Texture tileTexture = TileToTexture.getTexture(tileType);
                if (tileTexture != null)
                    batch.draw(tileTexture, tileXPosition, tileYPosition, BLOCK_SIZE, BLOCK_SIZE);
            }
        }

        batch.setColor(prev);
    }

    public int getBoardWidth() {
        return board.getWidth();
    }
    public int getBoardHeight() {
        return board.getHeight();
    }
}
