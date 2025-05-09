package io.github.StoneDigger.models;

public class PlayerModel {
    private int positionX;
    private int positionY;

    PlayerModel() {
        positionX = -1;
        positionY = -1;
    }
    PlayerModel(int x, int y) {
        positionX = x;
        positionY = y;
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
