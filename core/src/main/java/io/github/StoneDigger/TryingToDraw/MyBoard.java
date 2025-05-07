package io.github.StoneDigger.TryingToDraw;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static io.github.StoneDigger.Assets.*;

public class MyBoard extends Actor {
    int[][] obstacleArray;
    int size_x;
    int size_y;
    private final Texture[] textureIds = {
        null,
        DIRT_TEXTURE,
        ROCK_TEXTURE,
        DIAMOND_TEXTURE
    };

    public MyBoard() {
        size_x = 2;
        size_y = 2;
        obstacleArray = new int[][]{{1,2}, {2,2}};
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        for(int i=0;i<size_x;i++) {
            for(int j=0;j<size_y;j++) {
                batch.draw(textureIds[obstacleArray[i][j]], i*SIZE_TEXTURE, j*SIZE_TEXTURE, SIZE_TEXTURE, SIZE_TEXTURE);
            }
        }
    }

    @Override
    public void act(float delta) {}

}
