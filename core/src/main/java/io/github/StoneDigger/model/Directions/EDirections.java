package io.github.StoneDigger.model.Directions;

public enum EDirections {
    UP( 0, 1),
    DOWN( 0,  -1),
    LEFT(-1,  0),
    RIGHT(1,  0);

    private final int dx;
    private final int dy;

    EDirections(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() { return dx; }
    public int getDy() { return dy; }

    public static EDirections of(int dx, int dy) {
        for (EDirections dir : values()) {
            if (dir.dx == dx && dir.dy == dy) return dir;
        }
        throw new IllegalArgumentException("No direction for "+dx+","+dy);
    }
}
