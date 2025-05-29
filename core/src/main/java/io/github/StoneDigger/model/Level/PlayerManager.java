package io.github.StoneDigger.model.Level;

import io.github.StoneDigger.model.GameObjects.Entities.Player;

public abstract class PlayerManager {
    private static Player player = null;

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        PlayerManager.player = player;
    }
}
