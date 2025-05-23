package io.github.StoneDigger.view.PlayerInputReceiver;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import io.github.StoneDigger.viewmodel.viewmodels.GameViewModel;

import static io.github.StoneDigger.model.models.Direction.*;

public class GameController {
    private final GameViewModel gameViewModel;
    private float timeSinceLast;

    public GameController(GameViewModel gameViewModel) {
        this.gameViewModel = gameViewModel;
        timeSinceLast = 1f;
    }

    public boolean isKeyPressed(float delta) {
        if(timeSinceLast < 0.25f) {
            timeSinceLast += delta;
            return false;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.UP)) { timeSinceLast=0f; gameViewModel.handleInput(UP); return true;}
        else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) { timeSinceLast=0f; gameViewModel.handleInput(DOWN); return true;}
        else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) { timeSinceLast=0f; gameViewModel.handleInput(RIGHT); return true;}
        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) { timeSinceLast=0f; gameViewModel.handleInput(LEFT); return true;}

        return false;
    }
}
