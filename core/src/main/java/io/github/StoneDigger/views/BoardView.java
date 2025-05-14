package io.github.StoneDigger.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import io.github.StoneDigger.models.BoardModel;
import io.github.StoneDigger.models.TileType;

import static io.github.StoneDigger.models.Constants.*;

public class BoardView extends Actor {
    private final BoardModel board;
    private final Texture background;
    private int width, height;

    public BoardView(BoardModel board) {
        this.board = board;
        width = board.getWidth();
        height = board.getHeight();
        /// background initialization
        Pixmap pix = new Pixmap(width*(BLOCK_SIZE + GAP_SIZE), height*(BLOCK_SIZE + GAP_SIZE), Pixmap.Format.RGBA8888);
        pix.setColor(0.6f, 0.3f, 0.1f, 1);
        pix.fill();                           // <— actually paint
        background = new Texture(pix);
        pix.dispose();
        ///
    }

    @Override
    public void act(float draw) {
        // something like tile animation
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color prev = batch.getColor();
        batch.draw(background, 0, 0, width*(BLOCK_SIZE+GAP_SIZE), height*(BLOCK_SIZE+GAP_SIZE));
        batch.setColor(1,1,1,1);
        for(int i=0;i<width;i++) {
            for(int j=0;j<height;j++) {
                TileType tileType = board.getTile(i,j);
                int tileXPosition = GAP_SIZE/2 + i*(BLOCK_SIZE + GAP_SIZE);
                int tileYPosition = GAP_SIZE/2 + j*(BLOCK_SIZE + GAP_SIZE);
                batch.draw(TileType.getTexture(tileType), tileXPosition, tileYPosition, BLOCK_SIZE, BLOCK_SIZE);
            }
        }

        batch.setColor(prev);
    }
}
