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
import io.github.StoneDigger.view.configs.WinnerScreenProperties;
import io.github.StoneDigger.view.configs.WinnerScreenPropertiesLoader;

import static io.github.StoneDigger.view.Assets.HAPPY_MINER_TEXTURE;
import static io.github.StoneDigger.view.Assets.REGULAR_FONT_GENERATOR;

public class WinnerScreen extends ScreenAdapter {
    private final GameStart gameStart;
    private Viewport viewport;
    private Stage stage;

    private Image bg;
    private Label youWonLabel;
    private Label goBackToMenuLabel;

    private final WinnerScreenProperties config = WinnerScreenPropertiesLoader.getInstance();

    public WinnerScreen(final GameStart gameStart) {
        this.gameStart = gameStart;
    }

    @Override
    public void show() {
        viewport = new ScalingViewport(Scaling.fit, config.worldWidth, config.worldHeight);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        createLabels();
        createImage();
        addListener();
        addActors();
    }

    private void createLabels() {
        // "YOU WON" label
        BitmapFont wonFont = createFont(config.wonFontSize, Color.valueOf(config.wonFontBorderColor));
        Label.LabelStyle wonStyle = new Label.LabelStyle(wonFont, Color.valueOf(config.wonFontColor));

        youWonLabel = new Label("YOU   WON", wonStyle);
        youWonLabel.setSize(config.wonLabelWidth, config.wonLabelHeight);
        youWonLabel.setPosition(config.wonLabelPositionX, config.wonLabelPositionY);

        // Back to menu label
        BitmapFont backFont = createFont(config.backFontSize, Color.valueOf(config.backFontBorderColor));
        Label.LabelStyle backStyle = new Label.LabelStyle(backFont, Color.valueOf(config.backFontColor));

        goBackToMenuLabel = new Label("Press ENTER to go back\n\n to the menu", backStyle);
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

    private void createImage() {
        bg = new Image(new TextureRegion(HAPPY_MINER_TEXTURE));
        bg.setPosition(0, 0);
        bg.setSize(config.worldWidth, config.worldHeight);
    }

    private void addListener() {
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
        stage.addActor(youWonLabel);
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
