package io.github.StoneDigger.view.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import io.github.StoneDigger.model.models.PlayerModel;

import static io.github.StoneDigger.view.Assets.PLAYER_TEXTURE;
import static io.github.StoneDigger.model.models.Constants.BLOCK_SIZE;
import static io.github.StoneDigger.model.models.Constants.GAP_SIZE;

public class PlayerView extends Actor {
    private final PlayerModel player;

    public PlayerView(PlayerModel playerModel) {
        player = playerModel;
    }

    @Override
    public void act(float delta) {
        // something like player static animation
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color prev = batch.getColor();
        batch.setColor(1,1,1,1);

        int playerXPosition = GAP_SIZE/2 + player.getPositionX()*(BLOCK_SIZE+GAP_SIZE);
        int playerYPosition = GAP_SIZE/2 + player.getPositionY()*(BLOCK_SIZE+GAP_SIZE);
        batch.draw(PLAYER_TEXTURE, playerXPosition, playerYPosition, BLOCK_SIZE, BLOCK_SIZE);

        batch.setColor(prev);

    }
}
