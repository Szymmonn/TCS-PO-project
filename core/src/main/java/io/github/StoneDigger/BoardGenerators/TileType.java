package io.github.StoneDigger.BoardGenerators;

import com.badlogic.gdx.graphics.Texture;
import static io.github.StoneDigger.Assets.*;


public enum TileType {
    EMPTY,
    DIRT,
    WALL,
    ROCK,
    DIAMOND,
    START,
    EXIT;

    public static final int BLOCK_SIZE = 100;
    public static final int GAP_SIZE = 20;

    public static Texture getTexture(TileType type) {
        if (type == DIRT) return DIRT_TEXTURE;
        if (type == WALL) return WALL_TEXTURE;
        if (type == ROCK) return ROCK_TEXTURE;
        if (type == DIAMOND) return DIAMOND_TEXTURE;
        if(type == EMPTY) return EMPTY_TEXTURE;
        if (type == START) return START_TEXTURE;
        if(type == EXIT) return EXIT_TEXTURE;
        return null;
    }
}
