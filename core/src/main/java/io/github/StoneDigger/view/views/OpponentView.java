package io.github.StoneDigger.view.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import io.github.StoneDigger.model.GameObjects.Entities.Opponent;
import io.github.StoneDigger.model.GameObjects.Entities.OpponentAI;
import io.github.StoneDigger.view.configs.GameScreenProperties;
import io.github.StoneDigger.view.configs.GameScreenPropertiesLoader;
import io.github.StoneDigger.model.Interfaces.IOpponent;

import java.util.List;

import static io.github.StoneDigger.view.Assets.OPPONENTAI_TEXTURE;
import static io.github.StoneDigger.view.Assets.OPPONENT_TEXTURE;


public class OpponentView extends Actor {
    private List<IOpponent> opponent;

    private final float BLOCK_SIZE;

    public OpponentView(List<IOpponent> opponentModel) {
        opponent = opponentModel;

        GameScreenProperties config = GameScreenPropertiesLoader.getInstance();
        BLOCK_SIZE = config.blockSize;
    }

    public void setOpponent(List<IOpponent> opponent) {
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

        for (IOpponent opp : opponent) {
            float oppPositionX = opp.getPosition().x * BLOCK_SIZE;
            float oppPositionY = opp.getPosition().y * BLOCK_SIZE;
            if (opp instanceof Opponent)
                batch.draw(OPPONENT_TEXTURE, oppPositionX, oppPositionY, BLOCK_SIZE, BLOCK_SIZE);
            else if (opp instanceof OpponentAI)
                batch.draw(OPPONENTAI_TEXTURE, oppPositionX, oppPositionY, BLOCK_SIZE, BLOCK_SIZE);

        }


        batch.setColor(prev);

    }
}
