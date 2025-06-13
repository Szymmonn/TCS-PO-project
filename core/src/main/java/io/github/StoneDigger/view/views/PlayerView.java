package io.github.StoneDigger.view.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import io.github.StoneDigger.model.GameObjects.Entities.Player;
import io.github.StoneDigger.view.configs.GameScreenProperties;
import io.github.StoneDigger.view.configs.GameScreenPropertiesLoader;

import static io.github.StoneDigger.view.Assets.PLAYER_TEXTURE;


public class PlayerView extends Actor {
    private Player player;

    private final float BLOCK_SIZE;

    public PlayerView(Player playerModel) {
        player = playerModel;

        GameScreenProperties config = GameScreenPropertiesLoader.getInstance();
        BLOCK_SIZE = config.blockSize;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void act(float delta) {
        // something like player static animation
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color prev = batch.getColor();
        batch.setColor(1,1,1,1);

        float playerXPosition = player.getPosition().x*BLOCK_SIZE;
        float playerYPosition = player.getPosition().y*BLOCK_SIZE;
        batch.draw(PLAYER_TEXTURE, playerXPosition, playerYPosition, BLOCK_SIZE, BLOCK_SIZE);

        batch.setColor(prev);

    }

    // for camera update
    public GridPoint2 getPlayerPosition() {
        return player.getPosition();
    }
}
