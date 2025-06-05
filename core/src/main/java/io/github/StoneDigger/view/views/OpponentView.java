package io.github.StoneDigger.view.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import io.github.StoneDigger.model.GameObjects.Entities.IOpponent;
import io.github.StoneDigger.model.GameObjects.Entities.IPlayer;
import io.github.StoneDigger.view.configs.GameScreenProperties;
import io.github.StoneDigger.view.configs.GameScreenPropertiesLoader;

import static io.github.StoneDigger.view.Assets.OPPONENT_TEXTURE;
import static io.github.StoneDigger.view.Assets.PLAYER_TEXTURE;


public class OpponentView extends Actor {
    private IOpponent opponent;

    private final float BLOCK_SIZE;

    public OpponentView(IOpponent opponentModel) {
        opponent = opponentModel;

        GameScreenProperties config = GameScreenPropertiesLoader.getInstance();
        BLOCK_SIZE = config.blockSize;
    }

    public void setOpponent(IOpponent opponent) {
        this.opponent = opponent;
    }

    @Override
    public void act(float delta) {
        // something like player static animation
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color prev = batch.getColor();
        batch.setColor(1,1,1,1);

        float playerXPosition = opponent.getPosition().x*BLOCK_SIZE;
        float playerYPosition = opponent.getPosition().y*BLOCK_SIZE;
        batch.draw(OPPONENT_TEXTURE, playerXPosition, playerYPosition, BLOCK_SIZE, BLOCK_SIZE);

        batch.setColor(prev);

    }

    public GridPoint2 getOpponentPosition() {
        return opponent.getPosition();
    }
}
