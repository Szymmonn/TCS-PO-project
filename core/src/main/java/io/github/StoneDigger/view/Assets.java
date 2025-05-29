package io.github.StoneDigger.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Assets {
    public static Texture ROCK_TEXTURE;
    public static Texture DIRT_TEXTURE;
    public static Texture DIAMOND_TEXTURE;
    public static Texture WALL_TEXTURE;
    public static Texture PLAYER_TEXTURE;
    public static Texture START_TEXTURE;
    public static Texture EXIT_TEXTURE;
    public static Texture HEART_TEXTURE;
    public static Texture SETTINGS_TEXTURE;
    public static FreeTypeFontGenerator REGULAR_FONT_GENERATOR;

    public static void load() {
        ROCK_TEXTURE = new Texture("images/rock.png");
        DIRT_TEXTURE = new Texture("images/dirt.png");
        DIAMOND_TEXTURE = new Texture("images/diamond.png");
        WALL_TEXTURE = new Texture("images/wall.png");
        PLAYER_TEXTURE = new Texture("images/player.png");
        START_TEXTURE = new Texture("images/start.png");
        EXIT_TEXTURE = new Texture("images/exit.png");
        HEART_TEXTURE = new Texture("images/pixel_heart.jpg");
        SETTINGS_TEXTURE = new Texture("images/settings_icon.png");
        REGULAR_FONT_GENERATOR = new FreeTypeFontGenerator(Gdx.files.internal("fonts/PressStart2P-Regular.ttf"));

    }

    public static void dispose() {
        ROCK_TEXTURE.dispose();
        DIAMOND_TEXTURE.dispose();
        DIAMOND_TEXTURE.dispose();
        WALL_TEXTURE.dispose();
        PLAYER_TEXTURE.dispose();
        START_TEXTURE.dispose();
        EXIT_TEXTURE.dispose();
        HEART_TEXTURE.dispose();
        SETTINGS_TEXTURE.dispose();
        REGULAR_FONT_GENERATOR.dispose();
    }


}
