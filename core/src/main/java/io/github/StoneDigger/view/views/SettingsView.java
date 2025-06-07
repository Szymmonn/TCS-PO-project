package io.github.StoneDigger.view.views;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;

import io.github.StoneDigger.view.Game.GameStart;
import io.github.StoneDigger.view.configs.*;
import io.github.StoneDigger.view.screen.MenuScreen;

import static io.github.StoneDigger.view.Assets.*;

public class SettingsView extends Group {
    private final GameStart gameStart;
    private final Viewport viewport;
    private final float VISIBLE_WORLD_WIDTH;
    private final float VISIBLE_WORLD_HEIGHT;

    private boolean isEnabled = false;
    private boolean isKeyBindsOn = false;

    private final ShapeRenderer shapeRenderer = new ShapeRenderer();

    private ImageButton settingsButton;
    private Table mainTable, buttonTable;
    private Slider sliderMusic, sliderSound;
    private Label titleLabel, musicLabel, soundLabel;
    private Label moveUpLabel, moveDownLabel, moveRightLabel, moveLeftLabel;
    private TextButton keyUpButton, keyDownButton, keyRightButton, keyLeftButton;
    private Label keybindsTitle;
    private TextButton backToGameButton, bindsButton;
    private TextButton backKeyBindsButton, applyKeyBindsButton;

    public SettingsView(GameStart gameStart, Viewport viewport) {
        this.gameStart = gameStart;
        this.viewport = viewport;

        GameScreenProperties gameScreenProperties = GameScreenPropertiesLoader.getInstance();
        VISIBLE_WORLD_WIDTH = gameScreenProperties.blocksInViewWidth * gameScreenProperties.blockSize;
        VISIBLE_WORLD_HEIGHT = gameScreenProperties.blocksInViewHeight * gameScreenProperties.blockSize;

        initUI();
        addActor(settingsButton);
        addActor(mainTable);
        addActor(buttonTable);
    }

    private void initUI() {
        createSettingsButton();
        createSliders();
        createLabels();
        createButtons();
        createTables();
        addListeners();
        addEscapeListener();
    }

    private void createSettingsButton() {
        settingsButton = new ImageButton(new TextureRegionDrawable(SETTINGS_TEXTURE));
        settingsButton.setPosition(VISIBLE_WORLD_WIDTH - 100, VISIBLE_WORLD_HEIGHT);
        settingsButton.setSize(100, 100);
        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isEnabled = !isEnabled;
                mainTable.setTouchable(isEnabled ? Touchable.enabled : Touchable.disabled);
                buttonTable.setTouchable(isEnabled && isKeyBindsOn ? Touchable.enabled : Touchable.disabled);
            }
        });
    }

    private void createSliders() {
        sliderMusic = createCustomSlider(700, 40);
        sliderMusic.setValue(musicVolume / musicVolumeMultiplier * sliderMusic.getMaxValue());

        sliderSound = createCustomSlider(700, 40);
        sliderSound.setValue(4);
    }

    private Slider createCustomSlider(int width, int height) {
        Skin skin = new Skin();
        Slider.SliderStyle style = new Slider.SliderStyle();
        style.background = createTrackDrawable(width, height, Color.GOLD);
        style.knob = createRectKnobDrawable(24, 70, Color.ORANGE, Color.RED);
        skin.add("custom-slider", style);
        return new Slider(0, 4, 1, false, skin, "custom-slider");
    }

    private void createLabels() {
        Label.LabelStyle labelStyle = new Label.LabelStyle(createFont(40, Color.BLACK), Color.WHITE);
        musicLabel = new Label("music", labelStyle);
        soundLabel = new Label("sound", labelStyle);

        titleLabel = new Label("SETTINGS", new Label.LabelStyle(createFont(60, Color.BLACK), Color.WHITE));

        Label.LabelStyle keyStyle = new Label.LabelStyle(createFont(24, Color.BLACK), Color.BLACK);
        keyStyle.background = createBg(Color.RED, Color.GREEN);

        // Center-align text
        keyStyle.font = createFont(24, Color.BLACK);

        keybindsTitle = new Label("KEY BINDS", new Label.LabelStyle(createFont(48, Color.BLACK), Color.WHITE));

        moveUpLabel = new Label("Move Up Key", keyStyle);
        moveDownLabel = new Label("Move Down Key", keyStyle);
        moveLeftLabel = new Label("Move Left Key", keyStyle);
        moveRightLabel = new Label("Move Right Key", keyStyle);

        moveUpLabel.setAlignment(Align.center);
        moveDownLabel.setAlignment(Align.center);
        moveLeftLabel.setAlignment(Align.center);
        moveRightLabel.setAlignment(Align.center);
    }

    private void createButtons() {
        keyUpButton = createTextButton("keyUp");
        keyDownButton = createTextButton("keyDown");
        keyLeftButton = createTextButton("keyLeft");
        keyRightButton = createTextButton("keyRight");
        backToGameButton = createTextButton("Back to Menu");
        bindsButton = createTextButton("Key Binds");

        // === ADD BACK & APPLY BUTTONS ===
        applyKeyBindsButton = createTextButton("Apply");
        backKeyBindsButton = createTextButton("Back");
    }

    private TextButton createTextButton(String text) {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = createFont(30, Color.BLUE);
        style.up = createBg(Color.LIGHT_GRAY, Color.BLUE);
        style.down = createBg(Color.DARK_GRAY, Color.BLUE);
        TextButton button = new TextButton(text, style);
        button.pack();
        return button;
    }

    private void createTables() {
        mainTable = new Table();
        mainTable.setSize(1000, 900);
        mainTable.setPosition(550, 150);
        mainTable.setTouchable(Touchable.disabled);
        mainTable.setDebug(false);

        mainTable.add(titleLabel).height(200).colspan(2).padBottom(40).center();
        mainTable.row();
        mainTable.add(musicLabel).width(200).padRight(20).left();
        mainTable.add(sliderMusic).expandX().fillX().height(60).padBottom(20);
        mainTable.row();
        mainTable.add(soundLabel).width(200).padRight(20).left();
        mainTable.add(sliderSound).expandX().fillX().height(60);
        mainTable.row();
        mainTable.add(bindsButton).expandX().fillX().padTop(100).width(400).colspan(2);
        mainTable.row();
        mainTable.add(backToGameButton).expandX().fillX().center().padTop(50).width(600).colspan(2);

        buttonTable = new Table();
        buttonTable.setSize(1000, 900);
        buttonTable.setPosition(550, 150);

        // Title for key binds screen
        buttonTable.add(keybindsTitle).colspan(2).padBottom(40);
        buttonTable.row();

        buttonTable.add(createKeyGroup(moveUpLabel, keyUpButton)).expandX().fill().pad(20);
        buttonTable.add(createKeyGroup(moveDownLabel, keyDownButton)).expandX().fill().pad(20);
        buttonTable.row();
        buttonTable.add(createKeyGroup(moveLeftLabel, keyLeftButton)).expandX().fill().pad(20);
        buttonTable.add(createKeyGroup(moveRightLabel, keyRightButton)).expandX().fill().pad(20);
        buttonTable.row();

        Table buttonRow = new Table();
        buttonRow.add(applyKeyBindsButton).width(300).pad(20);
        buttonRow.add(backKeyBindsButton).width(300).pad(20);

        buttonTable.add(buttonRow).colspan(2).padTop(40);
    }

    private Table createKeyGroup(Label label, TextButton button) {
        Table group = new Table();
        group.add(label).expandX().fillX().row();
        group.add(button).expandX().fillX();
        return group;
    }

    private void addListeners() {
        backToGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameStart.setScreen(new MenuScreen(gameStart));
            }
        });

        sliderMusic.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                float value = sliderMusic.getValue();
                musicVolume = value / sliderMusic.getMaxValue() * musicVolumeMultiplier;
                gameMusic.setVolume(musicVolume);
                menuMusic.setVolume(musicVolume);
            }
        });

        sliderSound.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                soundVolume = sliderSound.getValue() / sliderSound.getMaxValue();
            }
        });

        bindsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isKeyBindsOn = true;
                mainTable.setTouchable(Touchable.disabled);
                buttonTable.setTouchable(Touchable.enabled);
            }
        });

        backKeyBindsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isKeyBindsOn = false;
                mainTable.setTouchable(Touchable.enabled);
                buttonTable.setTouchable(Touchable.disabled);
            }
        });

        applyKeyBindsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // TODO: Implement saving logic for key binds
                isKeyBindsOn = false;
                mainTable.setTouchable(Touchable.enabled);
                buttonTable.setTouchable(Touchable.disabled);
            }
        });
    }

    private void addEscapeListener() {
        addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.ESCAPE) {
                    isEnabled = !isEnabled;
                    mainTable.setTouchable(isEnabled ? Touchable.enabled : Touchable.disabled);
                    buttonTable.setTouchable(isEnabled && isKeyBindsOn ? Touchable.enabled : Touchable.disabled);
                    return true;
                }
                return false;
            }
        });
    }

    private BitmapFont createFont(int size, Color borderColor) {
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size = size;
        param.color = borderColor;
        return REGULAR_FONT_GENERATOR.generateFont(param);
    }

    private Drawable createTrackDrawable(int width, int height, Color fillColor) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(fillColor);
        pixmap.fillRectangle(0, 0, width, height);
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return new TextureRegionDrawable(new TextureRegion(texture));
    }

    private Drawable createRectKnobDrawable(int width, int height, Color fillColor, Color outlineColor) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(fillColor);
        pixmap.fillRectangle(0, 0, width, height);
        pixmap.setColor(outlineColor);
        pixmap.drawRectangle(0, 0, width - 1, height - 1);
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return new TextureRegionDrawable(new TextureRegion(texture));
    }

    private TextureRegionDrawable createBg(Color main, Color border) {
        Pixmap pixmap = new Pixmap(200, 100, Pixmap.Format.RGBA8888);
        pixmap.setColor(main);
        pixmap.fill();
        pixmap.setColor(border);
        for (int i = 0; i < 3; i++) pixmap.drawRectangle(i, i, 200 - 1 - 2 * i, 100 - 1 - 2 * i);
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return new TextureRegionDrawable(texture);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color prevColor = batch.getColor();
        batch.setColor(Color.WHITE);
        settingsButton.draw(batch, 1);

        if (isEnabled) {
            batch.end();
            shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
            drawRoundedRectWithBorder(shapeRenderer, 300, 150, 1500, 900, 50, 50);
            batch.begin();

            if (isKeyBindsOn) buttonTable.draw(batch, parentAlpha);
            else mainTable.draw(batch, parentAlpha);
        }

        batch.setColor(prevColor);
    }

    private void drawRoundedRectWithBorder(ShapeRenderer sr, float x, float y, float w, float h, float r, float borderWidth) {
        int segments = 20;
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(0.5f, 0, 0, 1);
        drawRoundedRect(sr, x, y, w, h, r, segments);

        float innerR = borderWidth, inset = borderWidth;
        sr.setColor(Color.DARK_GRAY);
        drawRoundedRect(sr, x + inset, y + inset, w - 2 * inset, h - 2 * inset, innerR, segments);
        sr.end();
    }

    private void drawRoundedRect(ShapeRenderer sr, float x, float y, float w, float h, float r, int segments) {
        sr.rect(x + r, y + r, w - 2 * r, h - 2 * r);
        sr.rect(x + r, y, w - 2 * r, r);
        sr.rect(x + r, y + h - r, w - 2 * r, r);
        sr.rect(x, y + r, r, h - 2 * r);
        sr.rect(x + w - r, y + r, r, h - 2 * r);
        sr.arc(x + r, y + r, r, 180f, 90f, segments);
        sr.arc(x + w - r, y + r, r, 270f, 90f, segments);
        sr.arc(x + w - r, y + h - r, r, 0f, 90f, segments);
        sr.arc(x + r, y + h - r, r, 90f, 90f, segments);
    }

    public boolean isSettingsOn() {
        return isEnabled;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
