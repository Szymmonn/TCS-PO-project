package io.github.StoneDigger.TryingToDraw;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import io.github.StoneDigger.BoardGenerators.RandomBoardGenerator;
import io.github.StoneDigger.BoardGenerators.TileType;

import static io.github.StoneDigger.Assets.*;

public class MyBoard extends Actor {
    TileType[][] obstacleArray;
    int BoardSizeY;
    int BoardSizeX;

    float sizeOfObstacleOnScreen = SIZE_TEXTURE + 20;

    public MyBoard() {
        BoardSizeX = 30;
        BoardSizeY = 20;
        obstacleArray = new RandomBoardGenerator(0.1f,0.1f,0.1f).generate(BoardSizeX,BoardSizeY,1,1).getTiles();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color prev = batch.getColor();
        batch.setColor(1f,1f,1f,1f);

        float temp = sizeOfObstacleOnScreen;
        float translate = (temp-SIZE_TEXTURE)/2;
        for(int i=0;i<BoardSizeX;i++) {
            for(int j = 0; j< BoardSizeX; j++) {
                batch.draw(TileType.getTexture(obstacleArray[i][j]), i*temp +translate, j*temp + translate, SIZE_TEXTURE, SIZE_TEXTURE);
            }
        }
        batch.setColor(prev);
    }

    @Override
    public void act(float delta) {}

}
