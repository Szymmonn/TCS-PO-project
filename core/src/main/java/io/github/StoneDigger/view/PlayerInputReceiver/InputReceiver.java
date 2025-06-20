package io.github.StoneDigger.view.PlayerInputReceiver;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import io.github.StoneDigger.view.configs.GlobalControls;
import io.github.StoneDigger.viewmodel.viewmodels.GameViewModel;

import java.util.HashSet;

import static io.github.StoneDigger.model.Directions.EDirections.*;

public class InputReceiver extends InputAdapter {
    private final GameViewModel gameViewModel;

    private final HashSet<Integer> heldKeys = new HashSet<>();
    private float timeSinceLast = 1f;
    private final float inputRepeatDelay = 0.25f; // delay between repeated actions

    public InputReceiver(GameViewModel gameViewModel) {
        this.gameViewModel = gameViewModel;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == GlobalControls.moveUpKey || keycode == GlobalControls.moveDownKey ||
            keycode == GlobalControls.moveLeftKey || keycode == GlobalControls.moveRightKey) {
            heldKeys.add(keycode); // mark key as held
            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == GlobalControls.moveUpKey || keycode == GlobalControls.moveDownKey ||
           keycode == GlobalControls.moveLeftKey || keycode == GlobalControls.moveRightKey){
            heldKeys.remove(keycode); // stop tracking when key is released
            return true;
        }
        return false;
    }

    public void update(float delta) {
        timeSinceLast += delta;
        if (heldKeys.isEmpty()) return;

        int keycode = heldKeys.iterator().next();

        if (timeSinceLast >= inputRepeatDelay) {
            if (keycode == GlobalControls.moveUpKey) {
                gameViewModel.handleInput(UP);
                timeSinceLast = 0f; // reset timer for next repeat
            } else if (keycode == GlobalControls.moveDownKey) {
                gameViewModel.handleInput(DOWN);
                timeSinceLast = 0f; // reset timer for next repeat
            } else if(keycode == GlobalControls.moveLeftKey) {
                gameViewModel.handleInput(LEFT);
                timeSinceLast = 0f; // reset timer for next repeat
            } else if(keycode == GlobalControls.moveRightKey) {
                gameViewModel.handleInput(RIGHT);
                timeSinceLast = 0f; // reset timer for next repeat
            }
        }
    }
}
