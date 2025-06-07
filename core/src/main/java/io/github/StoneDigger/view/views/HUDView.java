package io.github.StoneDigger.view.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import io.github.StoneDigger.model.Level.ILevelStats;
import io.github.StoneDigger.view.Game.GameStart;
import io.github.StoneDigger.view.configs.GameScreenProperties;
import io.github.StoneDigger.view.configs.GameScreenPropertiesLoader;
import io.github.StoneDigger.view.configs.HudViewProperties;
import io.github.StoneDigger.view.configs.HudViewPropertiesLoader;
import io.github.StoneDigger.view.views.utility.BackgroundFactory;

import java.time.Duration;

import static io.github.StoneDigger.view.Assets.*;

public class HUDView extends Group {
    private final GameStart gameStart;
    private final ILevelStats levelStats;

    private final Image background;

    private Label diamondCountLabel;
    private Label diamondCollectedLabel;
    private Label timeElapsedLabel;
    private Label levelNumberLabel;

    private Image diamondImage;
    private Image[] heartImages;

    private final Table diamondTable;
    private final Table hpTable;

    private int prevHp;
    private int prevDiamonds;
    private int prevLevelNumber;

    private final float VISIBLE_WORLD_HEIGHT;
    private final float VISIBLE_WORLD_WIDTH;
    private final float HUD_SIZE;
    private final HudViewProperties config;

    public HUDView(ILevelStats levelStats, final GameStart gameStart) {
        this.gameStart = gameStart;
        this.levelStats = levelStats;
        this.prevHp = levelStats.getHP();
        this.prevDiamonds = levelStats.getDiamondCount();
        this.prevLevelNumber = levelStats.getLevelNumber();

        config = HudViewPropertiesLoader.getInstance();
        GameScreenProperties gameConfig = GameScreenPropertiesLoader.getInstance();
        VISIBLE_WORLD_HEIGHT = gameConfig.blocksInViewHeight * gameConfig.blockSize;
        VISIBLE_WORLD_WIDTH = gameConfig.blocksInViewWidth * gameConfig.blockSize;
        HUD_SIZE = gameConfig.hudSize;

        background = createBackground();
        createLabels();
        createImages();
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
    }

    private Image createBackground() {
        return BackgroundFactory.createSolidBackground(
            0, VISIBLE_WORLD_HEIGHT, VISIBLE_WORLD_WIDTH, HUD_SIZE, Color.valueOf(config.hudBgColor)
        );
    }

    private void createLabels() {
        BitmapFont font = createFont(config.fontSize, Color.valueOf(config.fontBorderColor));
        font.getData().setScale(config.fontScaleX, config.fontScaleY);
        Label.LabelStyle style = new Label.LabelStyle(font, Color.valueOf(config.fontColor));

        diamondCollectedLabel = new Label(formatNumber(prevDiamonds), style);
        diamondCountLabel = new Label(formatNumber(levelStats.getDiamondCount()), style);

        timeElapsedLabel = new Label("00:00", style);
        levelNumberLabel = new Label(formatLevelNumber(prevLevelNumber), style);

        configureTimeElapsedLabel();
        configureLevelLabel();
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
        table.left().pad(config.diamondTablePad);

        diamondCountLabel.getStyle().font.getData().setScale(3, 2);

        table.add(diamondCountLabel).pad(config.diamondTablePad).padTop(config.labelTopPad);
        table.add(diamondImage).size(config.diamondImageSize).pad(config.diamondTablePad);
        table.add(diamondCollectedLabel).size(config.diamondCollectedLabelSize).pad(config.diamondTablePad).padTop(config.labelTopPad);

        return table;
    }

    private Table createHpTable() {
        Table table = new Table();
        table.setPosition(0, VISIBLE_WORLD_HEIGHT);
        table.setSize(VISIBLE_WORLD_WIDTH + config.hpTableWidthOffset, HUD_SIZE);
        table.right();

        for (int i = 0; i < prevHp; i++) {
            table.add(heartImages[i]).size(config.hpImageSize).pad(config.hpImagePad);
        }

        return table;
    }

    private void configureTimeElapsedLabel() {
        timeElapsedLabel.setPosition(VISIBLE_WORLD_WIDTH * config.timeLabelPositionXMultiplier, VISIBLE_WORLD_HEIGHT + config.timeLabelPad);
        timeElapsedLabel.setSize(config.timeLabelWidth, config.levelLabelHeight);
    }

    private void configureLevelLabel() {
        levelNumberLabel.setPosition(VISIBLE_WORLD_WIDTH * config.levelLabelPositionXMultiplier, VISIBLE_WORLD_HEIGHT + config.levelLabelPositionYPad);
        levelNumberLabel.setSize(config.leveLabelWidth, config.levelLabelHeight);
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
            for (int i = prevHp; i > current; i--) {
                hpTable.removeActor(heartImages[i-1]);
            }
            prevHp = current;
        }
    }

    private void updateTime() {
        long duration = (long) levelStats.getTimeElapsed();
        long minutes = duration / 60;
        long seconds = duration % 60;

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
