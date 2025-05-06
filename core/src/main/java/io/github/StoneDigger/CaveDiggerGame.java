package io.github.StoneDigger;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import io.github.StoneDigger.BoardGenerators.BoardActor;
import io.github.StoneDigger.BoardGenerators.PlayerActor;

public class CaveDiggerGame extends Group {
    private final PlayerActor playerActor;
    private final BoardActor boardActor;

    public static int BOARD_WIDTH = 40;
    public static int BOARD_HEIGHT = 20;
    public static int START_X = 10;
    public static int START_Y = 10;

    public CaveDiggerGame() {
        playerActor = new PlayerActor();
        addActor(playerActor);
        boardActor = new BoardActor();
        addActor(boardActor);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        playerActor.getPlayer().handleInput();
        // more logic
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(Color.WHITE);
        super.draw(batch, parentAlpha);
        boardActor.draw(batch, parentAlpha);
        playerActor.draw(batch, parentAlpha);
    }



}
