package io.github.StoneDigger.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import io.github.StoneDigger.model.Boards.BoardGenerators.ELevelType;
import io.github.StoneDigger.view.Game.GameStart;
import io.github.StoneDigger.view.PlayerInputReceiver.InputReceiver;
import io.github.StoneDigger.view.configs.GameScreenProperties;
import io.github.StoneDigger.view.configs.GameScreenPropertiesLoader;
import io.github.StoneDigger.view.views.*;
import io.github.StoneDigger.viewmodel.viewmodels.GameViewModel;

public class GameScreen extends ScreenAdapter {

    // --- Constants ---
    private final float BLOCK_SIZE;

    private final float VISIBLE_WORLD_WIDTH;
    private final float VISIBLE_WORLD_HEIGHT;
    private final float HUD_SIZE;

    // --- Dependencies ---
    private final GameStart gameStart;
    private final ELevelType levelType;

    // --- View/Model/Controller ---
    private GameViewModel gameViewModel;
    private InputReceiver gameController;

    // --- Rendering ---
    private SpriteBatch spriteBatch;

    // --- Viewports ---
    private Viewport gameViewport;
    private OrthographicCamera gameCamera;

    private Viewport hudViewport;

    // --- Views ---
    private PlayerView playerView;
    private BoardView boardView;
    private final Color bg;

    private Stage hudStage;

    public GameScreen(GameStart gameStart, ELevelType levelType) {
        this.gameStart = gameStart;
        this.levelType = levelType;
        GameScreenProperties config = GameScreenPropertiesLoader.getInstance();

        BLOCK_SIZE = config.blockSize;
        VISIBLE_WORLD_WIDTH = config.blocksInViewWidth * BLOCK_SIZE;
        VISIBLE_WORLD_HEIGHT = config.blocksInViewHeight * BLOCK_SIZE;
        HUD_SIZE = config.hudSize;
        bg = Color.valueOf(config.backgroundColor);
    }

    @Override
    public void show() {
        gameViewModel = new GameViewModel(levelType);
        gameController = new InputReceiver(gameViewModel);

        initGameViewport();
        initHudViewport();
        initViews();
        setupInputProcessor();

        spriteBatch = new SpriteBatch();
    }

    private void initGameViewport() {
        gameViewport = new ScalingViewport(Scaling.fit, VISIBLE_WORLD_WIDTH, VISIBLE_WORLD_HEIGHT + HUD_SIZE);
        gameCamera = (OrthographicCamera) gameViewport.getCamera();
        gameCamera.update();
    }

    private void initHudViewport() {
        hudViewport = new ScalingViewport(Scaling.fit, VISIBLE_WORLD_WIDTH, VISIBLE_WORLD_HEIGHT + HUD_SIZE);
        OrthographicCamera hudCamera = (OrthographicCamera) hudViewport.getCamera();
        hudCamera.position.set(VISIBLE_WORLD_WIDTH / 2f, VISIBLE_WORLD_HEIGHT + HUD_SIZE / 2f, 0);
        hudCamera.update();
    }

    private void initViews() {
        boardView = new BoardView(gameViewModel.getBoard());
        playerView = new PlayerView(gameViewModel.getPlayer());

        HUDView hudView = new HUDView(gameViewModel.getLevelStats(), gameStart);

        hudStage = new Stage(hudViewport);
        hudStage.addActor(hudView);
    }

    private void setupInputProcessor() {
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(hudStage);
        multiplexer.addProcessor(gameController);
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void render(float delta) {
        gameViewModel.update(delta);
        gameController.update(delta);
//        boolean needToUpdateCamera = gameController.isKeyPressed(delta);

        Gdx.gl.glClearColor(bg.r, bg.g, bg.b, bg.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        drawGameLayer(delta);
        drawHudLayer(delta);
        updateCamera();
    }

    private void drawGameLayer(float delta) {
        gameViewport.apply();
        spriteBatch.setProjectionMatrix(gameCamera.combined);

        boardView.act(delta);
        playerView.act(delta);

        spriteBatch.begin();
        boardView.draw(spriteBatch, 1);
        playerView.draw(spriteBatch, 1);
        spriteBatch.end();
    }

    private void drawHudLayer(float delta) {
        hudViewport.apply();
        hudStage.act(delta);
        hudStage.draw();
    }

    public void updateCamera() {
        GridPoint2 pos = playerView.getPlayerPosition();

        float cameraX = clampCenter(pos.x * BLOCK_SIZE + BLOCK_SIZE / 2f, VISIBLE_WORLD_WIDTH / 2f, boardView.getBoardWidth() * BLOCK_SIZE);
        float cameraY = clampCenter(pos.y * BLOCK_SIZE + BLOCK_SIZE / 2f, VISIBLE_WORLD_HEIGHT / 2f, boardView.getBoardHeight() * BLOCK_SIZE);

        gameCamera.position.set(cameraX, cameraY + HUD_SIZE/2, 0);  // TODO: smoothen movement
        gameCamera.update();
    }

    private float clampCenter(float value, float min, float max) {
        return Math.max(min, Math.min(value, max - min));
    }

    @Override
    public void resize(int width, int height) {
        gameViewport.update(width, height, true);
        hudViewport.update(width, height, true);
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        hudStage.dispose();
    }
}
