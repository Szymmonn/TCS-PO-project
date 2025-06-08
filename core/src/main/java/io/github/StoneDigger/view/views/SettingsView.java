package io.github.StoneDigger.view.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
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
import io.github.StoneDigger.view.configs.GlobalControls;

import java.util.*;

import static io.github.StoneDigger.view.Assets.*;

public class SettingsView extends Group {
    private final GameStart gameStart;
    private final Viewport viewport;
    private final float VISIBLE_WORLD_WIDTH;
    private final float VISIBLE_WORLD_HEIGHT;

    private final SettingsViewProperties properties = SettingsViewPropertiesLoader.getInstance();

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

    private final Map<TextButton, String> buttonKeyMap = new HashMap<>();
    private final Map<String, Integer> editedKeyCodes = new HashMap<>();
    private TextButton activeButton = null;

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

    private void resetButtonStyle(TextButton button) {
        button.getLabel().setColor(Color.valueOf(properties.createTextButtonFontColor));
    }

    private void prepareKeyBindButton(TextButton button, String propertyKey, int keyCode) {
        button.setText(Input.Keys.toString(keyCode));
        buttonKeyMap.put(button, propertyKey);
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (activeButton != null) resetButtonStyle(activeButton);
                activeButton = button;
                button.setText("");
                button.getLabel().setColor(Color.RED);
            }
        });
    }

    private int getKeyFromMap(String propertyKey) {
        switch (propertyKey) {
            case "moveUpKey":
                return GlobalControls.moveUpKey;
            case "moveDownKey":
                return GlobalControls.moveDownKey;
            case "moveLeftKey":
                return GlobalControls.moveLeftKey;
            case "moveRightKey":
                return GlobalControls.moveRightKey;
            default:
                return Input.Keys.UNKNOWN;
        }
    }

    private void applyEditedKeys() {
        if (new HashSet<>(editedKeyCodes.values()).size() == 4) {
            GlobalControls.moveUpKey = editedKeyCodes.get("moveUpKey");
            GlobalControls.moveDownKey = editedKeyCodes.get("moveDownKey");
            GlobalControls.moveLeftKey = editedKeyCodes.get("moveLeftKey");
            GlobalControls.moveRightKey = editedKeyCodes.get("moveRightKey");
            editedKeyCodes.clear();
        }
        updateApplyButtonState();
    }

    private void cancelEditedKeys() {
        for (Map.Entry<TextButton, String> entry : buttonKeyMap.entrySet()) {
            int original = getKeyFromMap(entry.getValue());
            entry.getKey().setText(Input.Keys.toString(original));
            resetButtonStyle(entry.getKey());
        }
        editedKeyCodes.clear();
        activeButton = null;
        updateApplyButtonState();
    }

    private void updateApplyButtonState() {
        Collection<Integer> keycodes = editedKeyCodes.isEmpty()
            ? Arrays.asList(GlobalControls.moveUpKey, GlobalControls.moveDownKey, GlobalControls.moveLeftKey, GlobalControls.moveRightKey)
            : Arrays.asList(
            editedKeyCodes.getOrDefault("moveUpKey", GlobalControls.moveUpKey),
            editedKeyCodes.getOrDefault("moveDownKey", GlobalControls.moveDownKey),
            editedKeyCodes.getOrDefault("moveLeftKey", GlobalControls.moveLeftKey),
            editedKeyCodes.getOrDefault("moveRightKey", GlobalControls.moveRightKey)
        );

        boolean hasDuplicates = new HashSet<>(keycodes).size() < 4;

        applyKeyBindsButton.setDisabled(hasDuplicates);
        applyKeyBindsButton.getLabel().setColor(hasDuplicates ? Color.GRAY : Color.valueOf(properties.createTextButtonFontColor));
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
        settingsButton.setPosition(VISIBLE_WORLD_WIDTH + properties.settingsButtonOffsetX, VISIBLE_WORLD_HEIGHT);
        settingsButton.setSize(properties.settingsButtonSize, properties.settingsButtonSize);
        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isEnabled = !isEnabled;
                mainTable.setTouchable(isEnabled ? Touchable.enabled : Touchable.disabled);
                buttonTable.setTouchable(isEnabled && isKeyBindsOn ? Touchable.enabled : Touchable.disabled);
                if(buttonTable.isTouchable()) getStage().setKeyboardFocus(buttonTable);
            }
        });
    }

    private void createSliders() {
        sliderMusic = createCustomSlider();
        sliderMusic.setValue(musicVolume / musicVolumeMultiplier * sliderMusic.getMaxValue());

        sliderSound = createCustomSlider();
        sliderSound.setValue(properties.sliderMaxValue);
    }

    private Slider createCustomSlider() {
        Skin skin = new Skin();
        Slider.SliderStyle style = new Slider.SliderStyle();
        style.background = createTrackDrawable((int) properties.sliderWidth, (int) properties.sliderHeight, Color.valueOf(properties.sliderColor));
        style.knob = createRectKnobDrawable((int) properties.knobWidth, (int) properties.knobHeight, Color.valueOf(properties.knobColor), Color.valueOf(properties.knobBorderColor));
        skin.add(properties.sliderStyleName, style);
        return new Slider(properties.sliderMinValue, properties.sliderMaxValue, properties.sliderStepValue, false, skin, properties.sliderStyleName);
    }

    private void createLabels() {
        Label.LabelStyle labelStyle = new Label.LabelStyle(createFont((int) properties.musicSoundLabelFontSize, Color.valueOf(properties.musicSoundLabelFontBorderColor)), Color.valueOf(properties.musicSoundLabelFontColor));
        musicLabel = new Label(properties.musicText, labelStyle);
        soundLabel = new Label(properties.soundText, labelStyle);

        titleLabel = new Label(properties.settingsTitleText, new Label.LabelStyle(createFont((int) properties.settingsTitleFontSize, Color.valueOf(properties.settingsTitleFontBorderColor)), Color.valueOf(properties.settingsTitleFontColor)));

        Label.LabelStyle keyStyle = new Label.LabelStyle(createFont((int) properties.keyStyleFontSize, Color.valueOf(properties.keyStyleFontBorderColor)), Color.valueOf(properties.keyStyleFontBorderColor));
        keyStyle.background = createBg(Color.valueOf(properties.keyStyleBgColor), Color.valueOf(properties.keyStyleBorderColor));

        keybindsTitle = new Label(properties.keybindsTitleText, new Label.LabelStyle(createFont((int) properties.keybindsTitleFontSize, Color.valueOf(properties.keybindsTitleFontBorderColor)), Color.valueOf(properties.keybindsTitleFontColor)));

        moveUpLabel = new Label(properties.moveUpText, keyStyle);
        moveDownLabel = new Label(properties.moveDownText, keyStyle);
        moveLeftLabel = new Label(properties.moveLeftText, keyStyle);
        moveRightLabel = new Label(properties.moveRightText, keyStyle);

        moveUpLabel.setAlignment(Align.center);
        moveDownLabel.setAlignment(Align.center);
        moveLeftLabel.setAlignment(Align.center);
        moveRightLabel.setAlignment(Align.center);
    }

    private void createButtons() {
        keyUpButton = createTextButton(properties.keyUpText);
        keyDownButton = createTextButton(properties.keyDownText);
        keyLeftButton = createTextButton(properties.keyLeftText);
        keyRightButton = createTextButton(properties.keyRightText);
        backToGameButton = createTextButton(properties.backToGameText);
        bindsButton = createTextButton(properties.bindsButtonText);

        applyKeyBindsButton = createTextButton(properties.applyText);
        backKeyBindsButton = createTextButton(properties.backText);

         prepareKeyBindButton(keyUpButton, "moveUpKey", GlobalControls.moveUpKey);
         prepareKeyBindButton(keyDownButton, "moveDownKey", GlobalControls.moveDownKey);
         prepareKeyBindButton(keyLeftButton, "moveLeftKey", GlobalControls.moveLeftKey);
         prepareKeyBindButton(keyRightButton, "moveRightKey", GlobalControls.moveRightKey);
    }

    private TextButton createTextButton(String text) {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = createFont((int) properties.createTextButtonFontSize, Color.valueOf(properties.createTextButtonFontColor));
        style.up = createBg(Color.valueOf(properties.createTextButtonStyleUpMainColor), Color.valueOf(properties.createTextButtonStyleUpBorderColor));
        style.down = createBg(Color.valueOf(properties.createTextButtonStyleDownMainColor), Color.valueOf(properties.createTextButtonStyleDownBorderColor));
        TextButton button = new TextButton(text, style);
        button.pack();
        return button;
    }

    private void createTables() {
        mainTable = new Table();
        mainTable.setSize(properties.mainTableWidth, properties.mainTableHeight);
        mainTable.setPosition(properties.mainTablePosX, properties.mainTablePosY);
        mainTable.setTouchable(Touchable.disabled);
        mainTable.setDebug(false);

        mainTable.add(titleLabel).height(200).colspan(2).padBottom(properties.labelPadBottom).center();
        mainTable.row();
        mainTable.add(musicLabel).width(properties.labelWidth).padRight(properties.labelPadRight).left();
        mainTable.add(sliderMusic).expandX().fillX().height(properties.sliderHeightRow).padBottom(properties.labelPadBottom);
        mainTable.row();
        mainTable.add(soundLabel).width(properties.labelWidth).padRight(properties.labelPadRight).left();
        mainTable.add(sliderSound).expandX().fillX().height(properties.sliderHeightRow);
        mainTable.row();
        mainTable.add(bindsButton).expandX().fillX().padTop(properties.bindsButtonPadTop).width(properties.bindsButtonWidth).colspan(2);
        mainTable.row();
        mainTable.add(backToGameButton).expandX().fillX().center().padTop(properties.backToGameButtonPadTop).width(properties.backToGameButtonWidth).colspan(2);

        buttonTable = new Table();
        buttonTable.setSize(properties.mainTableWidth, properties.mainTableHeight);
        buttonTable.setPosition(properties.mainTablePosX, properties.mainTablePosY);

        buttonTable.add(keybindsTitle).colspan(2).padBottom(properties.labelPadBottom);
        buttonTable.row();

        buttonTable.add(createKeyGroup(moveUpLabel, keyUpButton)).expandX().fill().pad(properties.keyGroupPad);
        buttonTable.add(createKeyGroup(moveDownLabel, keyDownButton)).expandX().fill().pad(properties.keyGroupPad);
        buttonTable.row();
        buttonTable.add(createKeyGroup(moveLeftLabel, keyLeftButton)).expandX().fill().pad(properties.keyGroupPad);
        buttonTable.add(createKeyGroup(moveRightLabel, keyRightButton)).expandX().fill().pad(properties.keyGroupPad);
        buttonTable.row();

        Table buttonRow = new Table();
        buttonRow.add(applyKeyBindsButton).width(properties.keybindsButtonWidth).pad(properties.keyGroupPad);
        buttonRow.add(backKeyBindsButton).width(properties.keybindsButtonWidth).pad(properties.keyGroupPad);

        buttonTable.add(buttonRow).colspan(2).padTop(properties.keybindsButtonRowPadTop).padRight(23);
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
                if(buttonTable.isTouchable()) getStage().setKeyboardFocus(buttonTable);
            }
        });

        backKeyBindsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                cancelEditedKeys();
                isKeyBindsOn = false;
                mainTable.setTouchable(Touchable.enabled);
                buttonTable.setTouchable(Touchable.disabled);

                // force reset to current bindings
                keyUpButton.setText(Input.Keys.toString(GlobalControls.moveUpKey));
                keyDownButton.setText(Input.Keys.toString(GlobalControls.moveDownKey));
                keyLeftButton.setText(Input.Keys.toString(GlobalControls.moveLeftKey));
                keyRightButton.setText(Input.Keys.toString(GlobalControls.moveRightKey));
            }
        });

        applyKeyBindsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!applyKeyBindsButton.isDisabled()) {
                    applyEditedKeys();
                    isKeyBindsOn = false;
                    mainTable.setTouchable(Touchable.enabled);
                    buttonTable.setTouchable(Touchable.disabled);
                }
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
                    if(buttonTable.isTouchable()) getStage().setKeyboardFocus(buttonTable);
                    return true;
                }
                return false;
            }
        });

        buttonTable.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (activeButton != null) {
                    activeButton.setText(Input.Keys.toString(keycode));
                    editedKeyCodes.put(buttonKeyMap.get(activeButton), keycode);
                    resetButtonStyle(activeButton);
                    activeButton = null;
                    updateApplyButtonState();
                    return true;
                }
                return false;
            }
        });

        buttonTable.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (activeButton != null) {
                    resetButtonStyle(activeButton);
                    activeButton.setText(Input.Keys.toString(getKeyFromMap(buttonKeyMap.get(activeButton))));
                    activeButton = null;
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
