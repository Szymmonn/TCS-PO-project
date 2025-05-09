package io.github.StoneDigger.TryingToDraw;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import io.github.StoneDigger.BoardGenerators.Board;
import io.github.StoneDigger.BoardGenerators.RandomBoardGenerator;
import io.github.StoneDigger.models.TileType;

import static io.github.StoneDigger.models.Constants.*;

public class MyBoard extends Actor {
    int BoardSizeY;
    int BoardSizeX;
    Board board;

    float sizeOfObstacleOnScreen = BLOCK_SIZE + GAP_SIZE;

    public MyBoard() {
        BoardSizeX = 30;
        BoardSizeY = 20;
        board = new RandomBoardGenerator(0.1f,0.1f,0.1f).generate(BoardSizeX,BoardSizeY,1,1);
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color prev = batch.getColor();
        batch.setColor(1f,1f,1f,1f);

        float temp = sizeOfObstacleOnScreen;
        float translate = (temp-BLOCK_SIZE)/2;
        int cols = board.getObstacleArray().length;
        int rows = board.getObstacleArray()[0].length;

        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                TileType type = board.get(x, y);
                batch.draw(TileType.getTexture(type), x * temp + translate, y * temp + translate,
                        BLOCK_SIZE, BLOCK_SIZE);
            }
        }

        batch.setColor(prev);
    }

    @Override
    public void act(float delta) {}

}
