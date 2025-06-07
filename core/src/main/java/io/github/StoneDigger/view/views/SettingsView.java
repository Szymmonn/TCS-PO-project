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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.StoneDigger.view.Game.GameStart;
import io.github.StoneDigger.view.configs.GameScreenProperties;
import io.github.StoneDigger.view.configs.GameScreenPropertiesLoader;
import io.github.StoneDigger.view.configs.HudViewProperties;
import io.github.StoneDigger.view.configs.HudViewPropertiesLoader;
import io.github.StoneDigger.view.screen.MenuScreen;
import io.github.StoneDigger.view.views.utility.BackgroundFactory;

import static io.github.StoneDigger.view.Assets.*;


public class SettingsView extends Group {
    private final GameStart gameStart;
    private boolean isEnabled;
    private final Viewport viewport;

    private final float VISIBLE_WORLD_WIDTH;
    private final float VISIBLE_WORLD_HEIGHT;

    private Image background;
    private Image windowBg;
    private ImageButton settingsButton;

    private Table mainTable;
    private Table buttonTable;

    private Label titleLabel;

    private Skin skinMusic;
    private Slider sliderMusic;
    private Skin skinSound;
    private Slider sliderSound;

    private Label musicLabel;
    private Label soundLabel;

    private Label moveUpLabel;
    private Label moveDownLabel;
    private Label moveRightLabel;
    private Label moveLeftLabel;

    private TextButton keyUpButton;
    private TextButton keyDownButton;
    private TextButton keyRightButton;
    private TextButton keyLeftButton;

    private TextButton backButton;
    private TextButton bindsButton;

    private ShapeRenderer shapeRenderer;


    // TODO: delete
    HudViewProperties config;

    public SettingsView(GameStart gameStart, Viewport viewport) {
        this.gameStart = gameStart;
        this.viewport = viewport;

        GameScreenProperties gameScreenProperties = GameScreenPropertiesLoader.getInstance();
        VISIBLE_WORLD_WIDTH = gameScreenProperties.blocksInViewWidth * gameScreenProperties.blockSize;
        VISIBLE_WORLD_HEIGHT = gameScreenProperties.blocksInViewHeight * gameScreenProperties.blockSize;

        // temporary
        config = HudViewPropertiesLoader.getInstance();

        create();

        addActor(settingsButton);
        addActor(mainTable);
    }

    /*
    CREATE  METHOD
     */
    private void create() {
        isEnabled = false;

        shapeRenderer = new ShapeRenderer();

        createSettingsButton();
        createSlider();
        createLabels();
        createButtons();
        addButtonsListeners();

        createTable();

        addEscapeListener();
    }


    /*
    background
     */
    public void drawRoundedRectWithBorder(ShapeRenderer shapeRenderer,
                                          float x, float y,
                                          float width, float height,
                                          float r, float borderWidth) {
        int segments = 20;

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        // === BORDER (ciemnoczerwony) ===
        shapeRenderer.setColor(0.5f, 0, 0, 1);
        drawRoundedRect(shapeRenderer, x, y, width, height, r, segments);

        // === ŚRODEK (szary) ===
        float innerR = borderWidth;// Math.max(r - borderWidth, 0);
        float inset = borderWidth;

        shapeRenderer.setColor(Color.DARK_GRAY);
        drawRoundedRect(
            shapeRenderer,
            x + inset,
            y + inset,
            width - 2 * inset,
            height - 2 * inset,
            innerR,
            segments
        );

        shapeRenderer.end();
    }

    private void drawRoundedRect(ShapeRenderer sr,
                                 float x, float y, float w, float h, float r, int segments) {

        // Środek
        sr.rect(x + r, y + r, w - 2 * r, h - 2 * r);

        // Boki
        sr.rect(x + r, y, w - 2 * r, r);                  // dół
        sr.rect(x + r, y + h - r, w - 2 * r, r);          // góra
        sr.rect(x, y + r, r, h - 2 * r);                  // lewy
        sr.rect(x + w - r, y + r, r, h - 2 * r);          // prawy

        // Rogi (ćwiartki okręgu)
        sr.arc(x + r, y + r, r, 180f, 90f, segments);             // lewy-dolny
        sr.arc(x + w - r, y + r, r, 270f, 90f, segments);         // prawy-dolny
        sr.arc(x + w - r, y + h - r, r, 0f, 90f, segments);       // prawy-górny
        sr.arc(x + r, y + h - r, r, 90f, 90f, segments);          // lewy-górny
    }

    /// ///////////////////////////////////////////////
    /// ///////////////////////////////////////////////
    /// ///////////////////////////////////////////////

    private void createSettingsButton() {
        settingsButton = new ImageButton(new TextureRegionDrawable(SETTINGS_TEXTURE));

        /*
        settings button parameters
         */
        float button_position_x = VISIBLE_WORLD_WIDTH + config.settingsButtonPositionXOffset;
        float button_position_y = VISIBLE_WORLD_HEIGHT;
        float button_width = config.settingsButtonWidth;
        float button_height = config.settingsButtonHeight;

        settingsButton.setPosition(button_position_x, button_position_y);
        settingsButton.setSize(button_width, button_height);

        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isEnabled = !isEnabled;
            }
        });
    }

    /*
    CREATE TABLE  METHOD
     */
    private void createTable() {
        // === MAIN SETTINGS TABLE ===
        mainTable = new Table();
        mainTable.setSize(1000, 900);
        mainTable.setPosition( 550, 150);

        mainTable.add(titleLabel)
            .height(200)
            .colspan(2)
            .padBottom(40)
            .center();
        mainTable.row();

        mainTable.add(musicLabel).width(200).padRight(20).left();
        mainTable.add(sliderMusic).expandX().fillX().height(60).padBottom(20);
        mainTable.row();

        mainTable.add(soundLabel).width(200).padRight(20).left();
        mainTable.add(sliderSound).expandX().fillX().height(60);
        mainTable.row();

        mainTable.add(bindsButton).expandX().fillX().padTop(100).width(400).colspan(2);
        mainTable.row();
        mainTable.add(backButton).expandX().fillX().center().padTop(50).width(600).colspan(2);

        mainTable.setDebug(false); // set to true if you want to see layout borders




        // === BUTTON TABLE (KEY BINDINGS) ===
        buttonTable = new Table();
        buttonTable.setSize(1000, 300);
        buttonTable.setPosition(
            (VISIBLE_WORLD_WIDTH - buttonTable.getWidth()) / 2f,
            mainTable.getY() - buttonTable.getHeight() - 40
        );

        // === BUTTON GROUPS ===
        Table keyUpGroup = new Table();
        keyUpGroup.add(moveUpLabel).expandX().fillX().row();
        keyUpGroup.add(keyUpButton).expandX().fillX();

        Table keyDownGroup = new Table();
        keyDownGroup.add(moveDownLabel).expandX().fillX().row();
        keyDownGroup.add(keyDownButton).expandX().fillX();

        Table keyLeftGroup = new Table();
        keyLeftGroup.add(moveLeftLabel).expandX().fillX().row();
        keyLeftGroup.add(keyLeftButton).expandX().fillX();

        Table keyRightGroup = new Table();
        keyRightGroup.add(moveRightLabel).expandX().fillX().row();
        keyRightGroup.add(keyRightButton).expandX().fillX();

        // === ADD BUTTON GROUPS TO TABLE ===
        buttonTable.add(keyUpGroup).expand().fill().pad(20);
        buttonTable.add(keyDownGroup).expand().fill().pad(20);
        buttonTable.row();
        buttonTable.add(keyLeftGroup).expand().fill().pad(20);
        buttonTable.add(keyRightGroup).expand().fill().pad(20);
    }

    ///  //////////////////////////////////////////////////////////
    ///  //////////////////////////////////////////////////////////
    ///  //////////////////////////////////////////////////////////


    private void createSlider() {
        //
        skinMusic = new Skin();

        Drawable trackDrawable = createTrackDrawable(700, 40, Color.GOLD);

        Drawable knobDrawable = createRectKnobDrawable(24, 70, Color.ORANGE, Color.RED);

        Slider.SliderStyle sliderStyle = new Slider.SliderStyle();
        sliderStyle.background = trackDrawable;
        sliderStyle.knob = knobDrawable;

        skinMusic.add("custom-sliderMusic", sliderStyle);

        sliderMusic = new Slider(0, 4, 1, false, skinMusic, "custom-sliderMusic");
        sliderMusic.setSize(700, 40);
        sliderMusic.setValue(musicVolume * sliderMusic.getMaxValue()); // initial value


        /// /////////////////////////////////////////////
        /// /////////////////////////////////////////////

        skinSound = new Skin();
        Drawable trackDrawable2 = createTrackDrawable(700, 40, Color.GOLD);

        Drawable knobDrawable2 = createRectKnobDrawable(24, 70, Color.ORANGE, Color.RED);

        Slider.SliderStyle sliderStyle2 = new Slider.SliderStyle();
        sliderStyle2.background = trackDrawable2;
        sliderStyle2.knob = knobDrawable2;

        skinSound.add("custom-sliderSound", sliderStyle2);

        sliderSound = new Slider(0, 4, 1, false, skinSound, "custom-sliderSound");
        sliderSound.setSize(700, 40);
        sliderSound.setValue(4); // initial value
    }

    // Create a rectangular track with a colored fill and outline
    private Drawable createTrackDrawable(int width, int height, Color fillColor) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);

        // Fill background
        pixmap.setColor(fillColor);
        pixmap.fillRectangle(0, 0, width, height);

        Texture texture = new Texture(pixmap);
        pixmap.dispose();

        return new TextureRegionDrawable(new com.badlogic.gdx.graphics.g2d.TextureRegion(texture));
    }

    // Create a rectangular knob
    private Drawable createRectKnobDrawable(int width, int height, Color fillColor, Color outlineColor) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);

        // Fill rectangle
        pixmap.setColor(fillColor);
        pixmap.fillRectangle(0, 0, width, height);

        // Draw outline rectangle 1px thick
        pixmap.setColor(outlineColor);
        pixmap.drawRectangle(0, 0, width - 1, height - 1);

        Texture texture = new Texture(pixmap);
        pixmap.dispose();

        return new TextureRegionDrawable(new com.badlogic.gdx.graphics.g2d.TextureRegion(texture));
    }


    ///  //////////////////////////////////////////////////////////
    ///  //////////////////////////////////////////////////////////
    ///  //////////////////////////////////////////////////////////


    ///  //////////////////////////////////////////////////////////
    ///  //////////////////////////////////////////////////////////
    ///  //////////////////////////////////////////////////////////


    private void createLabels() {
        //  TODO: config this
        BitmapFont musicFont = createFont(40, Color.valueOf(config.fontBorderColor));
//        font.getData().setScale(config.fontScaleX, config.fontScaleY);
        Label.LabelStyle style = new Label.LabelStyle(musicFont, Color.valueOf(config.fontColor));

        musicLabel = new Label("music", style);
        soundLabel = new Label("sound", style);

        BitmapFont titleFont = createFont(60, Color.BLACK);
        Label.LabelStyle titleStyle = new Label.LabelStyle(titleFont, Color.WHITE);

        titleLabel = new Label("SETTINGS", titleStyle);

        BitmapFont keyFont = createFont(24, Color.BLACK);
        Label.LabelStyle keyStyle = new Label.LabelStyle(keyFont, Color.BLACK);
        keyStyle.background = createBg(Color.RED, Color.GREEN);


        moveUpLabel = new Label("Move Up Key", keyStyle);
        moveDownLabel = new Label("Move Down Key", keyStyle);
        moveLeftLabel = new Label("Move Left Key", keyStyle);
        moveRightLabel = new Label("Move Right Key", keyStyle);
    }

    private BitmapFont createFont(int size, Color borderColor) {
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.color = borderColor;
        return REGULAR_FONT_GENERATOR.generateFont(parameter);
    }

    /*
    create buttons
     */
    private void createButtons() {
        keyUpButton = createTextButton("keyUp", 5, Color.LIGHT_GRAY, Color.BLUE);
        keyUpButton.setSize(200, 100);

        keyDownButton = createTextButton("keyDown", 5, Color.LIGHT_GRAY, Color.BLUE);
        keyDownButton.setSize(200, 100);

        keyLeftButton = createTextButton("keyLeft", 5, Color.LIGHT_GRAY, Color.BLUE);
        keyLeftButton.setSize(200, 100);

        keyRightButton = createTextButton("keyRight", 5, Color.LIGHT_GRAY, Color.BLUE);
        keyRightButton.setSize(200, 100);


        backButton = createTextButton("Back to Menu", 5, Color.LIGHT_GRAY, Color.BLUE);
        backButton.setSize(400, 100);

        bindsButton = createTextButton("Key Binds", 5, Color.LIGHT_GRAY, Color.BLUE);
    }

    private TextButton createTextButton(String text, int border_width, Color bgColor, Color borderColor) {
        // Tworzymy font z obrysem
        BitmapFont font = createFont(30, borderColor);

        // Tworzymy tło z obramowaniem
        int size = 64;  // przykładowy rozmiar kwadratu na teksturę, możesz dostosować

        TextureRegionDrawable background = createBg(bgColor, borderColor);

        // Tworzymy styl TextButton
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = font;
        style.up = background;
        style.down = background.tint(Color.DARK_GRAY);  // na kliknięcie

        // Tworzymy TextButton
        TextButton button = new TextButton(text, style);

        // Ustawiamy minimalny rozmiar na podstawie tekstu i paddingu
        button.pack();

        return button;
    }

    private TextureRegionDrawable createBg(Color main, Color border) {
        Pixmap pixmap = new Pixmap(200, 100, Pixmap.Format.RGBA8888);

        // Wypełnienie tła
        pixmap.setColor(main);
        pixmap.fill();

        // Obramowanie
        pixmap.setColor(border);
        for (int i = 0; i < 3; i++) {
            pixmap.drawRectangle(i, i, 200 - 1 - 2*i, 100 - 1 - 2*i);
        }

        Texture texture = new Texture(pixmap);
        pixmap.dispose();

        return new TextureRegionDrawable(texture);
    }

    ///  //////////////////////////////////////////////////////////
    ///  //////////////////////////////////////////////////////////
    ///  //////////////////////////////////////////////////////////

    private void addButtonsListeners() {
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameStart.setScreen(new MenuScreen(gameStart));
            }
        });

        sliderMusic.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                float value = sliderMusic.getValue();
                float maxValue = sliderMusic.getMaxValue();
                // TODO: save this in config - music_sound config
                musicVolume = value/maxValue;
                gameMusic.setVolume(musicVolume);
                menuMusic.setVolume(musicVolume);
            }
        });

        sliderSound.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                float value = sliderSound.getValue();
                float maxValue = sliderSound.getMaxValue();
                soundVolume = value/maxValue;
            }
        });
    }

    ///  //////////////////////////////////////////////////////////
    ///  //////////////////////////////////////////////////////////
    ///  //////////////////////////////////////////////////////////

    private void addEscapeListener() {
        addListener(new InputListener(){
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if(keycode == Input.Keys.ESCAPE) {
                    isEnabled = !isEnabled;
                    return true;
                }
                return false;
            }
        });
    }

    public boolean isSettingsOn() {
        return isEnabled;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color prev = batch.getColor();
        batch.setColor(1,1,1,1);

        settingsButton.draw(batch, 1);

        if(isEnabled) {
            settingsButton.draw(batch, 1);
            ///
            batch.end();
            shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
            drawRoundedRectWithBorder(shapeRenderer, 300, 150, 1500, 900, 50, 50);
            batch.begin();
            ///
            mainTable.draw(batch, parentAlpha);
            //buttonTable.draw(batch, parentAlpha);
        }

        batch.setColor(prev);
    }

}
