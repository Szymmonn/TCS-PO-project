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

import static io.github.StoneDigger.view.Assets.SAD_MINER_TEXTURE;
import static io.github.StoneDigger.view.Assets.REGULAR_FONT_GENERATOR;

public class LoserScreen extends ScreenAdapter {
    private final GameStart gameStart;
    private Viewport viewport;
    private Stage stage;

    private Label youLostlabel;
    private Label goBackToMenuLabel;

    private Image bg;

    public LoserScreen(final GameStart gameStart) {
        this.gameStart = gameStart;
    }

    @Override
    public void show() {
        viewport = new ScalingViewport(Scaling.fit, 800, 480);
        stage = new Stage(viewport);
        setInputProcessor();

        createLabel();
        createImage();
        addListener();
        addActors();
    }

    private void setInputProcessor() {
        Gdx.input.setInputProcessor(stage);
    }

    private void createLabel() {
        BitmapFont lostFont = createFont(70, Color.BLACK);
        Label.LabelStyle lostStyle = new Label.LabelStyle(lostFont, Color.RED);

        youLostlabel = new Label("YOU   LOST", lostStyle);
        youLostlabel.setSize(700, 100);
        youLostlabel.setPosition(64, 350);

        BitmapFont backFont = createFont(30, Color.BLACK);
        Label.LabelStyle backStyle = new Label.LabelStyle(backFont, Color.BLUE);

        goBackToMenuLabel = new Label("Press ENTER to go back\n\nto the menu", backStyle);
        goBackToMenuLabel.setAlignment(Align.center);
        goBackToMenuLabel.setSize(700, 100);
        goBackToMenuLabel.setPosition(50, 50);
    }

    private BitmapFont createFont(int size, Color borderColor) {
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.borderColor = borderColor;
        return REGULAR_FONT_GENERATOR.generateFont(parameter);
    }

    private void createImage() {
        bg = new Image(new TextureRegion(SAD_MINER_TEXTURE));
        bg.setPosition(0,0);
        bg.setSize(800, 480);
    }

    private void addListener() {
        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if(keycode == Input.Keys.ENTER) {
                    gameStart.setScreen(new MenuScreen(gameStart));
                    return true;
                }
                return false;
            }
        });
    }

    private void addActors() {
        stage.addActor(bg);
        stage.addActor(youLostlabel);
        stage.addActor(goBackToMenuLabel);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);   // any color is good // black is ok
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
