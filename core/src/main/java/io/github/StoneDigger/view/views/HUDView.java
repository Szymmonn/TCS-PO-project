package io.github.StoneDigger.view.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import io.github.StoneDigger.model.Level.ILevelStats;
import io.github.StoneDigger.model.Level.LevelStats;
import io.github.StoneDigger.view.views.utility.BackgroundFactory;

import java.time.Duration;

import static io.github.StoneDigger.view.Assets.*;
import static io.github.StoneDigger.view.screen.GameScreen.*;

public class HUDView extends Group {
    private final ILevelStats levelStats;
    private Label diamondCountLabel;
    private Label diamondCollectedLabel;
   // private Label hpLabel;        // currently not in use
    private Label timeElapsedLabel;
    private Label levelNumberLabel;
    private Image diamondImage;
    private Image[] heartImage;
    private final Image background;

    private final Table diamondTable;
    private final Table hpTable;

    // used in act to check for labels changes
    private int prevHp;
    private int prevDiamonds;
    private int prevLevelNumber;

    public HUDView(ILevelStats levelStatus) {
        this.levelStats = levelStatus;
        prevHp = levelStatus.getHP();
        prevDiamonds = levelStatus.getDiamondCount();
        prevLevelNumber = levelStatus.getLevelNumber();

        createLabels();
        createImages();
        background = createBackground();

        diamondTable = createDiamondTable();
        hpTable = createHpTable();
    }

    private Image createBackground() {
        /*
        background properties
         */
        float background_position_x = 0;
        float background_position_y = VISIBLE_WORLD_HEIGHT;
        float background_width = VISIBLE_WORLD_WIDTH;
        float background_height = HUD_SIZE;

        return BackgroundFactory.createSolidBackground(background_position_x, background_position_y, background_width, background_height, Color.FIREBRICK);
    }

    private void createLabels() {
        FreeTypeFontGenerator generator = REGULAR_FONT_GENERATOR;
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        BitmapFont font = generator.generateFont(parameter);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;

        diamondCollectedLabel = new Label(prevDiamonds < 10 ? "0" + prevDiamonds : String.valueOf(prevDiamonds), labelStyle);
        int temp = levelStats.getDiamondCount();
        diamondCountLabel = new Label(temp < 10 ? "0" + temp : String.valueOf(temp) , labelStyle);
        //hpLabel = new Label(String.valueOf(prevHp), labelStyle);
        timeElapsedLabel = new Label("00 : 00", labelStyle);
        setupTimeElapsedLabel();
        levelNumberLabel = new Label("L" + (prevLevelNumber < 10 ? "0" + prevLevelNumber : String.valueOf(prevDiamonds)) , labelStyle);
        setupLevelLabel();
    }

    private void createImages() {
        TextureRegion region = new TextureRegion(DIAMOND_TEXTURE);
        diamondImage = new Image(region);

        region = new TextureRegion(HEART_TEXTURE);
        heartImage = new Image[3];
        for(int i=3-1;i>=0; i--) {
            heartImage[i] = new Image(region);
        }
    }

    @Override
    public void act(float delta) {
        int currDiamonds = levelStats.getScore();
        if(prevDiamonds != currDiamonds) {
            prevDiamonds = currDiamonds;
            diamondCountLabel.setText(currDiamonds < 10 ? "0" + currDiamonds : String.valueOf(currDiamonds));
        }

        int currHP = levelStats.getHP();
        if(prevHp != currHP) {
            prevHp = currHP;
            for(int i=3-1;i>=currHP;i--) {
                hpTable.removeActor(heartImage[i]);
            }
        }

        Duration currDuration = levelStats.getTimeElapsed();
        long minutes = currDuration.getSeconds() /60;
        long seconds = currDuration.getSeconds() %60;
        String minS = minutes < 10 ? "0"+minutes : String.valueOf(minutes);
        String secS = seconds < 10 ? "0" + seconds : String.valueOf(seconds);

        timeElapsedLabel.setText(minS + " : " + secS);

        int currLevel = levelStats.getLevelNumber();
        if(prevLevelNumber != currLevel) {
            prevLevelNumber = currLevel;
            levelNumberLabel.setText("L" + (prevLevelNumber < 10 ? "0"+prevLevelNumber : String.valueOf(prevLevelNumber)));
        }

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color prev = batch.getColor();
        batch.setColor(1,1,1,1);

        /*
        where to draw background parameters
         */

        background.draw(batch, parentAlpha);
        diamondTable.draw(batch, parentAlpha);
        hpTable.draw(batch, parentAlpha);
        timeElapsedLabel.draw(batch, parentAlpha);


        batch.setColor(prev);
    }

    private Table createDiamondTable() {
    /*
    table parameters
     */
        float table_position_x = 0;
        float table_position_y = VISIBLE_WORLD_HEIGHT;
        float table_width = VISIBLE_WORLD_WIDTH;
        float table_height = HUD_SIZE;

        Table table = new Table();
        table.setPosition(table_position_x, table_position_y);
        table.setSize(table_width, table_height);

    /*
    paddings for all
     */
        float pad_left = 10;
        float pad_right = 10;
        float pad_top = 10;
        float pad_bottom = 10;

    /*
    diamondCountLabel parameters
     */
        float diamondCountLabel_size_x = 100;
        float diamondCountLabel_size_y = 80;

    /*
    diamondCountLabel font parameters
    */
        diamondCountLabel.getStyle().font.getData().setScale(100 , 80);

        table.add(diamondCountLabel)
            .pad(pad_top, pad_left, pad_bottom, pad_right)
            .width(diamondCountLabel_size_x)
            .height(diamondCountLabel_size_y);

    /*
    diamondImage parameters
     */
        float diamondImage_size_x = 80;
        float diamondImage_size_y = 80;

        table.add(diamondImage)
            .pad(pad_top, pad_left, pad_bottom, pad_right)
            .width(diamondImage_size_x)
            .height(diamondImage_size_y);

    /*
    diamondCollectedLabel parameters
     */
        float diamondCollectedLabel_size_x = 80;
        float diamondCollectedLabel_size_y = 80;

        table.add(diamondCollectedLabel)
            .pad(pad_top, pad_left, pad_bottom, pad_right)
            .width(diamondCollectedLabel_size_x)
            .height(diamondCollectedLabel_size_y);

        table.left();

        return table;
    }

    private Table createHpTable() {
        /*
        table parameters
         */
        float table_position_x = 0;
        float table_position_y = VISIBLE_WORLD_HEIGHT;
        float table_width = VISIBLE_WORLD_WIDTH;
        float table_height = HUD_SIZE;

        Table table = new Table();
        table.setPosition(table_position_x, table_position_y);
        table.setSize(table_width, table_height);

        /*
        paddings for all
         */
        float pad_left = 10;
        float pad_right = 10;
        float pad_top = 10;
        float pad_bottom = 10;

        /*
        heart parameters
         */
        float heart_width = 80;
        float heart_height = 80;

        for(int i = 0;i < prevHp; i++) {
            table.add(heartImage[i])
                .pad(pad_top, pad_left, pad_bottom, pad_right)
                .size(heart_width, heart_height);
        }

        table.right();

        return table;
    }

    private void setupTimeElapsedLabel() {
        /*
        label parameters
         */
        float timer_position_x = VISIBLE_WORLD_WIDTH * 0.60f;
        float timer_position_y = VISIBLE_WORLD_HEIGHT + 10;
        float timer_width = 4*80 + 50;  // no specified
        float timer_height = 80;

        /*
        timer font parameters
         */
        timeElapsedLabel.getStyle().font.setColor(Color.OLIVE);
        timeElapsedLabel.getStyle().font.getData().setScale(100, 80);

        timeElapsedLabel.setPosition(timer_position_x, timer_position_y);
        timeElapsedLabel.setSize(timer_width, timer_height);
    }

    private void setupLevelLabel() {
        /*
        label parameters
         */
        float level_position_x = VISIBLE_WORLD_WIDTH * 0.40f;
        float level_position_y = VISIBLE_WORLD_HEIGHT + 10;
        float level_width = 2*80 + 20;
        float level_height = 80;

        /*
        level font parameters
         */
        levelNumberLabel.getStyle().font.setColor(Color.WHITE);
        levelNumberLabel.getStyle().font.getData().setScale(100, 80);

        levelNumberLabel.setPosition(level_position_x, level_position_y);
        levelNumberLabel.setSize(level_width, level_height);
    }

}
