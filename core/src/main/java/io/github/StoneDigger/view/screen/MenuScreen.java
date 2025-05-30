package io.github.StoneDigger.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.StoneDigger.view.Game.GameStart;

import static io.github.StoneDigger.view.Assets.REGULAR_FONT_GENERATOR;

public class MenuScreen extends ScreenAdapter {
    private final GameStart gameStart;
    private Label label;
    private Stage stage;

    public MenuScreen(GameStart gameStart) {
        this.gameStart = gameStart;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        FreeTypeFontGenerator generator = REGULAR_FONT_GENERATOR;
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 50; // Font size in pixels

        parameter.color = Color.WHITE; // Text color
        parameter.borderColor = Color.BLACK; // Outline color
        parameter.borderWidth = 2f; // Thickness of the border

        parameter.shadowColor = new Color(0, 0, 0, 0.4f); // Semi-transparent black shadow
        parameter.shadowOffsetX = 2;
        parameter.shadowOffsetY = 2;

        parameter.characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789:!?.,'\"()-=+/\\ "; // Optimization (optional)

        BitmapFont font = generator.generateFont(parameter);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        label = new Label("PRESS <ENTER>\n TO START", labelStyle);

        Table table = new Table();
        table.setFillParent(true);
        table.center();
        table.add(label);

        stage.addActor(table);

        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if(keycode != Input.Keys.ENTER)
                    return false;
                gameStart.setScreen(new GameScreen(gameStart));
                Gdx.input.setInputProcessor(null);
                return true;
            }
        });
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0.53f, 0.81f, 0.92f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(v);
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {
        float old = Math.min(stage.getViewport().getWorldHeight(), stage.getViewport().getWorldWidth());
        float new_ = Math.min(i ,i1);
        stage.getViewport().update(i,i1, true);
        applyScale(new_/old);
    }
    // need to change that in some way
    // not yet know how
    private void applyScale(float scale) {
        float labelNewSize = label.getFontScaleY();
        label.setFontScale(labelNewSize*scale);
    }

    @Override
    public void dispose() {stage.dispose(); label.getStyle().font.dispose();}

}
