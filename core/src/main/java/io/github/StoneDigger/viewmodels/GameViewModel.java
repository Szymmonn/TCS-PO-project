package io.github.StoneDigger.viewmodels;


import io.github.StoneDigger.models.BoardModel;
import io.github.StoneDigger.models.PlayerModel;
import io.github.StoneDigger.views.BoardView;
import io.github.StoneDigger.views.PlayerView;

public class GameViewModel {
    private PlayerView playerView;
    private BoardView boardView;


    GameViewModel(PlayerModel playerModel, BoardModel boardModel) {
        this.playerView = new PlayerView(playerModel);
        this.boardView = new BoardView(boardModel);
    }

    public void handleInput() {}

    public void updateView() {}
}
