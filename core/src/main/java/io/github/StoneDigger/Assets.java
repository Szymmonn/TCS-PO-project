package io.github.StoneDigger;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.DistanceFieldFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
    public static int SIZE_TEXTURE = 100;

    public static Texture ROCK_TEXTURE;
    public static Texture DIRT_TEXTURE;
    public static Texture DIAMOND_TEXTURE;
    public static Texture WALL_TEXTURE;
    public static Texture PLAYER_TEXTURE;
    public static Texture START_TEXTURE;
    public static Texture EXIT_TEXTURE;

    public static void load() {
        ROCK_TEXTURE = new Texture("rock.png");
        DIRT_TEXTURE = new Texture("dirt.png");
        DIAMOND_TEXTURE = new Texture("diamond.png");
        WALL_TEXTURE = new Texture("wall.png");
        PLAYER_TEXTURE = new Texture("player.png");
        START_TEXTURE = new Texture("start.png");
        EXIT_TEXTURE = new Texture("exit.png");
    }

    public static void dispose() {
        ROCK_TEXTURE.dispose();
        DIAMOND_TEXTURE.dispose();
        DIAMOND_TEXTURE.dispose();
        WALL_TEXTURE.dispose();
        PLAYER_TEXTURE.dispose();
        START_TEXTURE.dispose();
        EXIT_TEXTURE.dispose();
    }


}
