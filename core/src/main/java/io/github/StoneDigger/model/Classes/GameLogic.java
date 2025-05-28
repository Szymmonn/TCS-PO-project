package io.github.StoneDigger.model.Classes;

import io.github.StoneDigger.model.Interfaces.ILogic;

public class GameLogic implements ILogic {
    private final SelfUpdateManager selfUpdateManager;

    public GameLogic() {
        selfUpdateManager = new SelfUpdateManager();
    }

    public void startCycle() {
        while(true) {

        }
    }
}
