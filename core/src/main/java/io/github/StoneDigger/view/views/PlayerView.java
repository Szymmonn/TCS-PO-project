package io.github.StoneDigger.view.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import io.github.StoneDigger.model.GameObjects.Entities.IPlayer;
import io.github.StoneDigger.model.GameObjects.Entities.Player;

import static io.github.StoneDigger.view.Assets.PLAYER_TEXTURE;
import static io.github.StoneDigger.view.screen.GameScreen.BLOCK_SIZE;
import static io.github.StoneDigger.view.screen.GameScreen.GAP_SIZE;

public class PlayerView extends Actor {
    private IPlayer player;

    public PlayerView(IPlayer playerModel) {
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

        int playerXPosition = player.getPosition().x * (BLOCK_SIZE + GAP_SIZE);
        int playerYPosition = player.getPosition().y * (BLOCK_SIZE + GAP_SIZE);
        batch.draw(PLAYER_TEXTURE, playerXPosition, playerYPosition, BLOCK_SIZE, BLOCK_SIZE);

        batch.setColor(prev);

    }

    public GridPoint2 getPlayerPosition() {
        return player.getPosition();
    }
}
