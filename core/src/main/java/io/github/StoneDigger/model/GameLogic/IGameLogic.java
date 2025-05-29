package io.github.StoneDigger.model.GameLogic;

public interface IGameLogic {
    void init();
    void tick(float delta);
    boolean isGameOver();
}
