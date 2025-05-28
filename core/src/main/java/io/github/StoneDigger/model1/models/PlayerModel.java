package io.github.StoneDigger.model1.models;

public class PlayerModel {
    private int positionX;
    private int positionY;

    public PlayerModel(int startX, int startY) {
        positionX = startX;
        positionY = startY;
    }

    public void moveTo(int x, int y) {
        positionX = x;
        positionY = y;
    }

    public int getPositionX() {
        return positionX;
    }
    public int getPositionY() {
        return positionY;
    }
}
