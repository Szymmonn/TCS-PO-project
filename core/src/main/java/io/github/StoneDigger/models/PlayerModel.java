package io.github.StoneDigger.models;

public class PlayerModel {
    private int positionX;
    private int positionY;

    public PlayerModel() {
        positionX = -1;
        positionY = -1;
    }
    public PlayerModel(int startX, int startY) {
        positionX = startX;
        positionY = startY;
    }

    public void moveTo(int x, int y) {
        positionX = x;
        positionY = y;
    }
    public void moveRight() {
        positionX++;
    }
    public void moveLeft() {
        positionX--;
    }
    public void moveUp() {
        positionY++;
    }
    public void moveDown() {
        positionY--;
    }
    public int getPositionX() {
        return positionX;
    }
    public int getPositionY() {
        return positionY;
    }
}
