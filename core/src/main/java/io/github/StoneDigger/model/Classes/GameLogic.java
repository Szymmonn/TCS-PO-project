package io.github.StoneDigger.model.Classes;

import io.github.StoneDigger.model.Interfaces.IGameLogic;

public class GameLogic implements IGameLogic {
    private final SelfUpdateManager selfUpdateManager;

    public GameLogic() {
        selfUpdateManager = new SelfUpdateManager();
    }

    @Override
    public void startGameCycle(float delta) {
        for(int i = 0; i < 50000000; i++) {
            selfUpdateManager.updateAll();
        }
    }
}
