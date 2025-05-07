package io.github.StoneDigger.TryingToDraw;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import io.github.StoneDigger.BoardGenerators.Board;
import io.github.StoneDigger.BoardGenerators.RandomBoardGenerator;
import io.github.StoneDigger.BoardGenerators.TileType;

import static io.github.StoneDigger.Assets.*;

public class MyBoard extends Actor {
    TileType[][] obstacleArray;
    int size_x;
    int size_y;

    float sizeOfObstacleOnScreen = SIZE_TEXTURE + 20;

    public MyBoard() {
        size_x = 2;
        size_y = 2;
        obstacleArray = new RandomBoardGenerator(0.1f,0.1f,0.1f).generate(30,20,1,1).getTiles();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color prev = batch.getColor();
        batch.setColor(1f,1f,1f,1f);

        float temp = sizeOfObstacleOnScreen;
        float translate = (temp-SIZE_TEXTURE)/2;
        for(int i=0;i<size_x;i++) {
            for(int j=0;j<size_y;j++) {
                batch.draw(TileType.getTexture(obstacleArray[i][j]), i*temp +translate, j*temp + translate, SIZE_TEXTURE, SIZE_TEXTURE);
            }
        }
        batch.setColor(prev);
    }

    @Override
    public void act(float delta) {}

}
