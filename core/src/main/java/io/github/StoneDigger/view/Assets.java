package io.github.StoneDigger.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import io.github.StoneDigger.view.configs.MenuScreenPropertiesLoader;

public class Assets {
    public static Texture ROCK_TEXTURE;
    public static Texture DIRT_TEXTURE;
    public static Texture DIAMOND_TEXTURE;
    public static Texture BRICK_TEXTURE;
    public static Texture PLAYER_TEXTURE;
    public static Texture START_TEXTURE;
    public static Texture END_TEXTURE;
    public static Texture HEART_TEXTURE;
    public static Texture SETTINGS_TEXTURE;
    public static Texture BORDER_TEXTURE;
    public static FreeTypeFontGenerator REGULAR_FONT_GENERATOR;

    public static String menuScreenPropertiesPath;

    public static void load() {
        ROCK_TEXTURE = new Texture("images/rock-removebg-preview.png");
        DIRT_TEXTURE = new Texture("images/dirt-removebg-preview.png");
        DIAMOND_TEXTURE = new Texture("images/diamond-removebg-preview.png");
        BRICK_TEXTURE = new Texture("images/wall.png");
        PLAYER_TEXTURE = new Texture("images/player-removebg-preview.png");
        START_TEXTURE = new Texture("images/start-removebg-preview.png");
        END_TEXTURE = new Texture("images/exit-removebg-preview.png");
        HEART_TEXTURE = new Texture("images/pixel_heart.jpg");
        SETTINGS_TEXTURE = new Texture("images/settings_icon.png");
        /*
        TO CHANGE
         */
        BORDER_TEXTURE = new Texture("images/blaszka.png");

        REGULAR_FONT_GENERATOR = new FreeTypeFontGenerator(Gdx.files.internal("fonts/PressStart2P-Regular.ttf"));

        menuScreenPropertiesPath = "config/MenuScreenProperties.properties";
    }

    public static void dispose() {
        ROCK_TEXTURE.dispose();
        DIAMOND_TEXTURE.dispose();
        DIAMOND_TEXTURE.dispose();
        BRICK_TEXTURE.dispose();
        PLAYER_TEXTURE.dispose();
        START_TEXTURE.dispose();
        END_TEXTURE.dispose();
        HEART_TEXTURE.dispose();
        SETTINGS_TEXTURE.dispose();
        BORDER_TEXTURE.dispose();

        REGULAR_FONT_GENERATOR.dispose();
    }


}
