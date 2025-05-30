package io.github.StoneDigger.view.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import io.github.StoneDigger.model.Level.ILevelStats;
import io.github.StoneDigger.view.Game.GameStart;
import io.github.StoneDigger.view.screen.SettingsScreen;
import io.github.StoneDigger.view.views.utility.BackgroundFactory;

import java.time.Duration;

import static io.github.StoneDigger.view.Assets.*;
import static io.github.StoneDigger.view.screen.GameScreen.*;

public class HUDView extends Group {
    private final GameStart gameStart;
    private final ILevelStats levelStats;

    private final Image background;

    private Label diamondCountLabel;
    private Label diamondCollectedLabel;
    private Label timeElapsedLabel;
    private Label levelNumberLabel;

    private ImageButton settingsButton;

    private Image diamondImage;
    private Image[] heartImages;

    private Table diamondTable;
    private Table hpTable;

    private int prevHp;
    private int prevDiamonds;
    private int prevLevelNumber;

    public HUDView(ILevelStats levelStats, final GameStart gameStart) {
        this.gameStart = gameStart;
        this.levelStats = levelStats;
        this.prevHp = levelStats.getHP();
        this.prevDiamonds = levelStats.getDiamondCount();
        this.prevLevelNumber = levelStats.getLevelNumber();

        background = createBackground();
        createLabels();
        createImages();
        createSettingsButton();
        diamondTable = createDiamondTable();
        hpTable = createHpTable();

        addActors();
    }

    @Override
    public void act(float delta) {
        updateDiamonds();
        updateHp();
        updateTime();
        updateLevel();
    }

    private void addActors() {
        addActor(background);
        addActor(diamondTable);
        addActor(hpTable);
        addActor(timeElapsedLabel);
        addActor(levelNumberLabel);
        addActor(settingsButton);
    }

    private Image createBackground() {
        return BackgroundFactory.createSolidBackground(
            0, VISIBLE_WORLD_HEIGHT, VISIBLE_WORLD_WIDTH, HUD_SIZE, Color.FIREBRICK
        );
    }

    private void createLabels() {
        BitmapFont font = createFont(30, Color.BLACK);

        Label.LabelStyle style = new Label.LabelStyle(font, Color.WHITE);

        diamondCollectedLabel = new Label(formatNumber(prevDiamonds), style);
        diamondCountLabel = new Label(formatNumber(levelStats.getDiamondCount()), style);

        timeElapsedLabel = new Label("00:00", style);
        levelNumberLabel = new Label(formatLevelNumber(prevLevelNumber), style);

        configureTimeElapsedLabel();
        configureLevelLabel();
    }

    private void createSettingsButton() {
        settingsButton = new ImageButton(new TextureRegionDrawable(SETTINGS_TEXTURE));


        /*
        settings button parameters
         */
        float button_position_x = VISIBLE_WORLD_WIDTH - 100;
        float button_position_y = VISIBLE_WORLD_HEIGHT;
        float button_width = 100;
        float button_height = 100;

        settingsButton.setPosition(button_position_x, button_position_y);
        settingsButton.setSize(button_width, button_height);

        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                gameStart.setScreen(new SettingsScreen(gameStart));
            }
        });
    }

    private BitmapFont createFont(int size, Color borderColor) {
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.borderColor = borderColor;
        return REGULAR_FONT_GENERATOR.generateFont(parameter);
    }

    private void createImages() {
        diamondImage = new Image(new TextureRegionDrawable(DIAMOND_TEXTURE));
        heartImages = new Image[3];
        for (int i = 0; i < 3; i++) {
            heartImages[i] = new Image(new TextureRegionDrawable(HEART_TEXTURE));
        }
    }

    private Table createDiamondTable() {
        Table table = new Table();
        table.setPosition(0, VISIBLE_WORLD_HEIGHT);
        table.setSize(VISIBLE_WORLD_WIDTH, HUD_SIZE);
        table.left().pad(10);

        diamondCountLabel.getStyle().font.getData().setScale(3, 2);

        table.add(diamondCountLabel).pad(10);
        table.add(diamondImage).size(100).pad(10);
        table.add(diamondCollectedLabel).size(80).pad(10);

        return table;
    }

    private Table createHpTable() {
        Table table = new Table();
        table.setPosition(0, VISIBLE_WORLD_HEIGHT);
        table.setSize(VISIBLE_WORLD_WIDTH - 150, HUD_SIZE);
        table.right();

        for (int i = 0; i < prevHp; i++) {
            table.add(heartImages[i]).size(100).pad(5);
        }

        return table;
    }

    private void configureTimeElapsedLabel() {
        timeElapsedLabel.getStyle().font.setColor(Color.OLIVE);
        timeElapsedLabel.getStyle().font.getData().setScale(100, 80);
        timeElapsedLabel.setPosition(VISIBLE_WORLD_WIDTH * 0.50f, VISIBLE_WORLD_HEIGHT + 10);
        timeElapsedLabel.setSize(4 * 80 + 50, 80);
    }

    private void configureLevelLabel() {
        levelNumberLabel.getStyle().font.setColor(Color.WHITE);
        levelNumberLabel.getStyle().font.getData().setScale(100, 80);
        levelNumberLabel.setPosition(VISIBLE_WORLD_WIDTH * 0.30f, VISIBLE_WORLD_HEIGHT + 10);
        levelNumberLabel.setSize(2 * 80 + 20, 80);
    }

    private void updateDiamonds() {
        int current = levelStats.getScore();
        if (prevDiamonds != current) {
            prevDiamonds = current;
            diamondCountLabel.setText(formatNumber(current));
        }
    }

    private void updateHp() {
        int current = levelStats.getHP();
        if (prevHp != current) {
            for (int i = 2; i >= current; i--) {
                hpTable.removeActor(heartImages[i]);
            }
            prevHp = current;
        }
    }

    private void updateTime() {
        Duration duration = levelStats.getTimeElapsed();
        long minutes = duration.getSeconds() / 60;
        long seconds = duration.getSeconds() % 60;

        timeElapsedLabel.setText(formatNumber(minutes) + ":" + formatNumber(seconds));
    }

    private void updateLevel() {
        int current = levelStats.getLevelNumber();
        if (prevLevelNumber != current) {
            prevLevelNumber = current;
            levelNumberLabel.setText(formatLevelNumber(current));
        }
    }

    private String formatNumber(long number) {
        return number < 10 ? "0" + number : String.valueOf(number);
    }

    private String formatLevelNumber(int level) {
        return "L" + formatNumber(level);
    }
}
