package io.github.StoneDigger.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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
    public static Texture OPPONENT_TEXTURE;
    public static FreeTypeFontGenerator REGULAR_FONT_GENERATOR;

    public static String menuScreenPropertiesPath;
    public static String gameScreenPropertiesPath;
    public static String hudViewPropertiesPath;
    public static String settingsViewPropertiesPath;

    public static float musicVolumeMultiplier = 0.4f;
    public static float musicVolume = musicVolumeMultiplier; // not the best place but fine for a simple game
    public static Music gameMusic;
    public static Music menuMusic;

    public static float soundVolume = 1f;
    public static Sound fallingDiamondSound;
    public static Sound diamondCollectedSound;
    public static Sound dieingSound;

    public static Sound dirt1Sound;
    public static Sound dirt2Sound;
    public static Sound dirt3Sound;

    public static Sound levelEndSound;

    public static Sound rockFallingSound;

    public static void load() {
        ROCK_TEXTURE = new Texture("images/rock-removebg-preview.png");
        DIRT_TEXTURE = new Texture("images/dirt-removebg-preview.png");
        DIAMOND_TEXTURE = new Texture("images/diamond-removebg-preview.png");
        BRICK_TEXTURE = new Texture("images/wall.png");
        PLAYER_TEXTURE = new Texture("images/player-removebg-preview.png");
        START_TEXTURE = new Texture("images/start-removebg-preview.png");
        END_TEXTURE = new Texture("images/exit-removebg-preview.png");
        HEART_TEXTURE = new Texture("images/pixel_heart.png");
        SETTINGS_TEXTURE = new Texture("images/settings_icon.png");
        OPPONENT_TEXTURE = new Texture("images/police.png");
        BORDER_TEXTURE = new Texture("images/blaszka.png");

        REGULAR_FONT_GENERATOR = new FreeTypeFontGenerator(Gdx.files.internal("fonts/PressStart2P-Regular.ttf"));

        menuScreenPropertiesPath = "config/MenuScreenProperties.properties";
        gameScreenPropertiesPath = "config/GameScreenProperties.properties";
        hudViewPropertiesPath = "config/HudViewProperties.properties";
        settingsViewPropertiesPath = "config/SettingsViewProperties.properties";

        menuMusic = Gdx.audio.newMusic(Gdx.files.internal("music/Eric Skiff - Underclocked (underunderclocked mix).mp3"));
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("music/Kevin MacLeod - Itty Bitty 8 Bit.mp3"));

        fallingDiamondSound = Gdx.audio.newSound(Gdx.files.internal("sounds/falling_diamond.mp3"));
        diamondCollectedSound = Gdx.audio.newSound(Gdx.files.internal("sounds/diamondCollected.mp3"));

        rockFallingSound = Gdx.audio.newSound(Gdx.files.internal("sounds/dinig_sound.mp3"));
        dieingSound = Gdx.audio.newSound(Gdx.files.internal("sounds/explosion.mp3"));

        dirt1Sound = Gdx.audio.newSound(Gdx.files.internal("sounds/walk-on-dirt-1-291981.mp3"));
        dirt2Sound = Gdx.audio.newSound(Gdx.files.internal("sounds/walk-on-dirt-2-291982.mp3"));
        dirt3Sound = Gdx.audio.newSound(Gdx.files.internal("sounds/walk-on-dirt-3-291983.mp3"));

        levelEndSound = Gdx.audio.newSound(Gdx.files.internal("sounds/winfantasia-6912.mp3"));

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
        OPPONENT_TEXTURE.dispose();

        REGULAR_FONT_GENERATOR.dispose();

        gameMusic.dispose();
        menuMusic.dispose();

        fallingDiamondSound.dispose();
        diamondCollectedSound.dispose();
        dieingSound.dispose();

        dirt1Sound.dispose();
        dirt2Sound.dispose();
        dirt3Sound.dispose();

        levelEndSound.dispose();

        rockFallingSound.dispose();
    }


}
