package io.github.StoneDigger.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.StoneDigger.view.Game.GameStart;
import io.github.StoneDigger.view.configs.LoserScreenProperties;
import io.github.StoneDigger.view.configs.LoserScreenPropertiesLoader;

import static io.github.StoneDigger.view.Assets.SAD_MINER_TEXTURE;
import static io.github.StoneDigger.view.Assets.REGULAR_FONT_GENERATOR;

public class LoserScreen extends ScreenAdapter {
    private final GameStart gameStart;
    private Viewport viewport;
    private Stage stage;

    private Label youLostLabel;
    private Label goBackToMenuLabel;
    private Image bg;

    private final LoserScreenProperties config = LoserScreenPropertiesLoader.getInstance();

    public LoserScreen(final GameStart gameStart) {
        this.gameStart = gameStart;
    }

    @Override
    public void show() {
        // use world dimensions from config
        viewport = new ScalingViewport(Scaling.fit, config.worldWidth, config.worldHeight);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        createLabels();
        createBackground();
        addInputListener();
        addActors();
    }

    private void createLabels() {
        // YOU LOST label
        BitmapFont lostFont = createFont(config.lostFontSize, Color.valueOf(config.lostFontBorderColor));
        Label.LabelStyle lostStyle = new Label.LabelStyle(lostFont, Color.valueOf(config.lostFontColor));

        youLostLabel = new Label("YOU   LOST", lostStyle);
        youLostLabel.setSize(config.lostLabelWidth, config.lostLabelHeight);
        youLostLabel.setPosition(config.lostLabelPositionX, config.lostLabelPositionY);

        // Back to menu label
        BitmapFont backFont = createFont(config.backFontSize, Color.valueOf(config.backFontBorderColor));
        Label.LabelStyle backStyle = new Label.LabelStyle(backFont, Color.valueOf(config.backFontColor));

        goBackToMenuLabel = new Label("Press ENTER to go back\n\nto the menu", backStyle);
        goBackToMenuLabel.setAlignment(Align.center);
        goBackToMenuLabel.setSize(config.backLabelWidth, config.backLabelHeight);
        goBackToMenuLabel.setPosition(config.backLabelPositionX, config.backLabelPositionY);
    }

    private BitmapFont createFont(int size, Color borderColor) {
        FreeTypeFontGenerator.FreeTypeFontParameter parameter =
            new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.borderColor = borderColor;
        return REGULAR_FONT_GENERATOR.generateFont(parameter);
    }

    private void createBackground() {
        bg = new Image(new TextureRegion(SAD_MINER_TEXTURE));
        bg.setPosition(0, 0);
        bg.setSize(config.worldWidth, config.worldHeight);
    }

    private void addInputListener() {
        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.ENTER) {
                    gameStart.setScreen(new MenuScreen(gameStart));
                    return true;
                }
                return false;
            }
        });
    }

    private void addActors() {
        stage.addActor(bg);
        stage.addActor(youLostLabel);
        stage.addActor(goBackToMenuLabel);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
