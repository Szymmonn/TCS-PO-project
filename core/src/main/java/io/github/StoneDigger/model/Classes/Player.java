package io.github.StoneDigger.model.Classes;

import com.badlogic.gdx.math.GridPoint2;

public class Player {
    private GridPoint2 position;

    public Player(GridPoint2 startPosition) {
        position = startPosition;
    }

    public void moveTo(GridPoint2 position) {
        this.position.x = position.x;
        this.position.y = position.y;
    }

    public GridPoint2 getPosition() {
        return position;
    }
}
