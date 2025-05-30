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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.StoneDigger.model.Boards.BoardGenerators.ELevelType;
import io.github.StoneDigger.view.Game.GameStart;

import static io.github.StoneDigger.view.Assets.REGULAR_FONT_GENERATOR;

public class MenuScreen extends ScreenAdapter {
    private final GameStart gameStart;

    private Viewport viewport;
    private Stage stage;

    private BitmapFont font;
    private Texture texture;

    private ELevelType levelType = ELevelType.STANDARD;
    public MenuScreen(GameStart gameStart) {
        this.gameStart = gameStart;
    }

    @Override
    public void show() {
        viewport = new ScalingViewport(Scaling.fit, 800, 480);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        createStage();
    }

    private void createStage() {
        /*
        label font parameters
         */
        FreeTypeFontGenerator generator = REGULAR_FONT_GENERATOR;
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 60;

        parameter.color = Color.WHITE; // Text color
        parameter.borderColor = Color.BLACK; // Outline color
        parameter.borderWidth = 2f; // Thickness of the border

        parameter.shadowColor = new Color(0, 0, 0, 0.4f); // Semi-transparent black shadow
        parameter.shadowOffsetX = 2;
        parameter.shadowOffsetY = 2;

        parameter.characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789:!?.,'\"()-=+/\\ "; // Optimization (optional)

        font = generator.generateFont(parameter);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;

        /*
        creating labels
         */

        /*
        title label
         */
        Label titleLabel = new Label("STONE \n DIGGER", labelStyle);

        /*
        press enter label
         */
        Label pressEnterLabel = new Label("PRESS <ENTER>\n TO START", labelStyle);

        /*
        button style parameters
         */
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.SKY);
        pixmap.fill();
        texture = new Texture(pixmap);
        TextureRegionDrawable whiteBg = new TextureRegionDrawable(new TextureRegion(texture));

        /*
        standard style
         */
        TextButton.TextButtonStyle styleStandard = new TextButton.TextButtonStyle();
        styleStandard.up = whiteBg;      // default button background
        styleStandard.down = whiteBg.tint(Color.FIREBRICK); // pressed background
        styleStandard.font = font;
        styleStandard.fontColor = Color.FIREBRICK; // text color

        /*
        random style
         */
        TextButton.TextButtonStyle styleRandom = new TextButton.TextButtonStyle();
        styleRandom.up = whiteBg;      // default button background
        styleRandom.down = whiteBg.tint(Color.FIREBRICK); // pressed background
        styleRandom.font = font;
        styleRandom.fontColor = Color.GRAY; // text color

        /*
        standard button
         */
        TextButton standardButton = new TextButton("STANDARD", styleStandard);
        standardButton.setSize(300, 80);

        /*
        random button
         */
        TextButton randomButton = new TextButton("RANDOM", styleRandom);
        randomButton.setSize(300, 80);

        /*
        creating table
         */
        Table table = new Table();
        table.setFillParent(true);
        table.add(titleLabel).expand().padBottom(30).row();
        table.add(standardButton).expand().padBottom(10).row();
        table.add(randomButton).expand().padBottom(30).row();
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
                    standardButton.getStyle().fontColor = Color.FIREBRICK;
                    randomButton.getStyle().fontColor = Color.GRAY;
                    return true;
                }
                if (keycode == Input.Keys.DOWN) {
                    if (levelType == ELevelType.RANDOM) return true;
                    levelType = ELevelType.RANDOM;
                    standardButton.getStyle().fontColor = Color.GRAY;
                    randomButton.getStyle().fontColor = Color.FIREBRICK;
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
                standardButton.getStyle().fontColor = Color.FIREBRICK;
                randomButton.getStyle().fontColor = Color.GRAY;
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
                standardButton.getStyle().fontColor = Color.GRAY;
                randomButton.getStyle().fontColor = Color.FIREBRICK;
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.53f, 0.81f, 0.92f, 1f);
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
        texture.dispose();
    }

}
