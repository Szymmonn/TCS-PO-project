package io.github.StoneDigger.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.StoneDigger.model.Boards.BoardGenerators.ELevelType;
import io.github.StoneDigger.view.Game.GameStart;
import io.github.StoneDigger.view.configs.MenuScreenProperties;
import io.github.StoneDigger.view.configs.MenuScreenPropertiesLoader;

import static io.github.StoneDigger.view.Assets.REGULAR_FONT_GENERATOR;

public class MenuScreen extends ScreenAdapter {
    private final GameStart gameStart;
    private MenuScreenProperties properties;

    private Viewport viewport;
    private Stage stage;

    private BitmapFont font;
    private Texture backgroundTexture;
    private Texture buttonBackgroundTexture;

    private ELevelType levelType = ELevelType.STANDARD;
    public MenuScreen(GameStart gameStart) {
        this.gameStart = gameStart;
        properties = MenuScreenPropertiesLoader.getInstance();
    }

    @Override
    public void show() {
        viewport = new ScalingViewport(Scaling.fit, properties.worldWidth, properties.worldHeight);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        createStage();
    }

    private void createStage() {
        /*
        font
         */
        FreeTypeFontGenerator generator = REGULAR_FONT_GENERATOR;
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = (int) properties.fontSize;

        parameter.color = Color.valueOf(properties.fontColor);
        parameter.borderColor = Color.valueOf(properties.fontBorderColor);
        parameter.borderWidth = properties.fontBorderWidth;

        parameter.shadowColor = Color.valueOf(properties.fontShadowColor);
        parameter.shadowOffsetX = (int) properties.fontShadowOffsetX;
        parameter.shadowOffsetY = (int) properties.fontShadowOffsetY;

        parameter.characters = properties.fontCharacterOptimization;

        font = generator.generateFont(parameter);

        /*
        labels and buttons
         */
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;

        Label titleLabel = new Label("STONE \n DIGGER", labelStyle);
        Label pressEnterLabel = new Label("PRESS <ENTER>\n TO START", labelStyle);

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.valueOf(properties.backgroundColor));
        pixmap.fill();
        backgroundTexture = new Texture(pixmap);
        TextureRegionDrawable bg = new TextureRegionDrawable(new TextureRegion(backgroundTexture));

        Pixmap pixmap2 = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap2.setColor(Color.valueOf(properties.buttonStyleBackgroundColor));
        pixmap2.fill();
        buttonBackgroundTexture = new Texture(pixmap2);
        TextureRegionDrawable buttonBg = new TextureRegionDrawable(new TextureRegion(buttonBackgroundTexture));

        TextButton.TextButtonStyle styleStandard = new TextButton.TextButtonStyle();
        styleStandard.up = bg;
        styleStandard.down = buttonBg;
        styleStandard.font = font;
        styleStandard.fontColor = Color.valueOf(properties.buttonStyleFontColor1);

        TextButton.TextButtonStyle styleRandom = new TextButton.TextButtonStyle();
        styleRandom.up = bg;
        styleRandom.down = buttonBg;
        styleRandom.font = font;
        styleRandom.fontColor = Color.valueOf(properties.buttonStyleFontColor2);

        TextButton standardButton = new TextButton("STANDARD", styleStandard);
        standardButton.setSize(properties.buttonWidth, properties.buttonHeight);

        TextButton randomButton = new TextButton("RANDOM", styleRandom);
        randomButton.setSize(properties.buttonWidth, properties.buttonHeight);

        /*
        table
         */
        Table table = new Table();
        table.setFillParent(true);
        table.add(titleLabel).expand().padBottom(properties.tableTitleStandardBottomPad).row();
        table.add(standardButton).expand().padBottom(properties.tableStandardRandomBottomPad).row();
        table.add(randomButton).expand().padBottom(properties.tableRandomEnterBottomPad).row();
        table.add(pressEnterLabel).expand();
        stage.addActor(table);

        /*
        adding listeners
         */
        /*
        enter listener
         */
        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if(keycode != Input.Keys.ENTER)
                    return false;
                Gdx.input.setInputProcessor(null);
                gameStart.setScreen(new GameScreen(gameStart, levelType));
                return true;
            }
        });
        /*
        arrow up and down listener
         */
        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.UP) {
                    if (levelType == ELevelType.STANDARD) return true;
                    levelType = ELevelType.STANDARD;
                    standardButton.getStyle().fontColor = Color.valueOf(properties.buttonStyleFontColor1);
                    randomButton.getStyle().fontColor = Color.valueOf(properties.buttonStyleFontColor2);
                    return true;
                }
                if (keycode == Input.Keys.DOWN) {
                    if (levelType == ELevelType.RANDOM) return true;
                    levelType = ELevelType.RANDOM;
                    standardButton.getStyle().fontColor = Color.valueOf(properties.buttonStyleFontColor2);
                    randomButton.getStyle().fontColor = Color.valueOf(properties.buttonStyleFontColor1);
                    return true;
                }
                return false;
            }
        });

        /*
        hover over standard button listener
         */
        standardButton.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (levelType == ELevelType.STANDARD) return;
                levelType = ELevelType.STANDARD;
                standardButton.getStyle().fontColor = Color.valueOf(properties.buttonStyleFontColor1);
                randomButton.getStyle().fontColor = Color.valueOf(properties.buttonStyleFontColor2);
            }
        });

        /*
        hover over random button listener
         */
        randomButton.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (levelType == ELevelType.RANDOM) return;
                levelType = ELevelType.RANDOM;
                standardButton.getStyle().fontColor = Color.valueOf(properties.buttonStyleFontColor2);
                randomButton.getStyle().fontColor = Color.valueOf(properties.buttonStyleFontColor1);
            }
        });
    }


    @Override
    public void render(float delta) {
        Color background = Color.valueOf(properties.backgroundColor);
        Gdx.gl.glClearColor(background.r, background.g, background.b, background.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {
        viewport.update(i,i1, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
        font.dispose();
        backgroundTexture.dispose();
        buttonBackgroundTexture.dispose();
    }

}
