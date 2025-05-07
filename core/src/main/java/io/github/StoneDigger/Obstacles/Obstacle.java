package io.github.StoneDigger.Obstacles;



abstract public class Obstacle implements IObstacle {
    private boolean status;
    private int x_position;
    private int y_position;

    Obstacle() {
        this(false, -1, -1);
    }
    Obstacle(int x_position, int y_position) {
        this(true, x_position, y_position);
    }
    Obstacle(boolean status, int x_position, int y_position) {
        this.status = status;
        this.x_position = x_position;
        this.y_position = y_position;
    }

    @Override
    public void moveObstacle(int x, int y) { }  // x and y are destination coordinates

    public int getX_position() { return x_position; }
    public int getY_position() { return y_position; }
    public boolean getStatus() { return status; }

    public void setX_position(int x_position) { this.x_position = x_position; }
    public void setY_position(int y_position) { this.y_position = y_position; }
    public void setStatus(boolean status) { this.status = status; }
}
