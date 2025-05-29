package io.github.StoneDigger.view.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import io.github.StoneDigger.model.Classes.Tiles.ATile;
import io.github.StoneDigger.model.Interfaces.IBoard;
import io.github.StoneDigger.view.views.utility.TileToTexture;

import static io.github.StoneDigger.view.screen.GameScreen.*;

public class BoardView extends Actor {
    private final IBoard board;
    private final Texture background;

    public BoardView(IBoard board) {
        this.board = board;

        /// background initialization
        Pixmap pix = new Pixmap(BOARD_WIDTH*(BLOCK_SIZE + GAP_SIZE), BOARD_HEIGHT*(BLOCK_SIZE + GAP_SIZE), Pixmap.Format.RGBA8888);
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
        batch.draw(background, 0, 0, BOARD_WIDTH*(BLOCK_SIZE+GAP_SIZE), BOARD_HEIGHT*(BLOCK_SIZE+GAP_SIZE));
        batch.setColor(1,1,1,1);
        for(int i=0;i<BOARD_WIDTH;i++) {
            for(int j=0;j<BOARD_HEIGHT;j++) {
                ATile tileType = board.getTile(new GridPoint2(i, j));
                int tileXPosition = GAP_SIZE/2 + i*(BLOCK_SIZE + GAP_SIZE);
                int tileYPosition = GAP_SIZE/2 + j*(BLOCK_SIZE + GAP_SIZE);
                Texture tileTexture = TileToTexture.getTexture(tileType);
                if (tileTexture != null)
                    batch.draw(tileTexture, tileXPosition, tileYPosition, BLOCK_SIZE, BLOCK_SIZE);
            }
        }

        batch.setColor(prev);
    }
}
