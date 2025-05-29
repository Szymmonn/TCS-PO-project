package io.github.StoneDigger.model1.models;

import com.badlogic.gdx.graphics.Texture;
import static io.github.StoneDigger.view.Assets.*;


public enum TileType {
    EMPTY,
    DIRT,
    WALL,
    ROCK,
    DIAMOND,
    START,
    EXIT;

    public static Texture getTexture(TileType type) {
        if (type == DIRT) return DIRT_TEXTURE;
        if (type == WALL) return BRICK_TEXTURE;
        if (type == ROCK) return ROCK_TEXTURE;
        if (type == DIAMOND) return DIAMOND_TEXTURE;
        if(type == EMPTY) return null;
        if (type == START) return START_TEXTURE;
        if(type == EXIT) return END_TEXTURE;
        return null;
    }
}
