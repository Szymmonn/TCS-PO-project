package io.github.StoneDigger.view.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import io.github.StoneDigger.model.Classes.Board;
import io.github.StoneDigger.model1.models.BoardModel;
import io.github.StoneDigger.model1.models.TileType;

import static io.github.StoneDigger.model1.models.Constants.BLOCK_SIZE;
import static io.github.StoneDigger.model1.models.Constants.GAP_SIZE;
import static io.github.StoneDigger.view.screen.GameScreen.BOARD_HEIGHT;
import static io.github.StoneDigger.view.screen.GameScreen.BOARD_WIDTH;

public class BoardView extends Actor {
    private final Board board;
    private final Texture background;

    public BoardView(Board board) {
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
                TileType tileType = board.getTile(i,j);
                int tileXPosition = GAP_SIZE/2 + i*(BLOCK_SIZE + GAP_SIZE);
                int tileYPosition = GAP_SIZE/2 + j*(BLOCK_SIZE + GAP_SIZE);
                batch.draw(TileType.getTexture(tileType), tileXPosition, tileYPosition, BLOCK_SIZE, BLOCK_SIZE);
            }
        }

        batch.setColor(prev);
    }
}
