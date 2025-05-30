package io.github.StoneDigger.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import io.github.StoneDigger.view.Game.GameStart;
import io.github.StoneDigger.view.PlayerInputReceiver.GameController;
import io.github.StoneDigger.view.views.*;
import io.github.StoneDigger.viewmodel.viewmodels.GameViewModel;

public class GameScreen extends ScreenAdapter {

    // --- Constants ---
    public static final int BLOCK_SIZE = 100;
    public static final int GAP_SIZE = 0;
    public static final int BOARD_WIDTH = 100;
    public static final int BOARD_HEIGHT = 100;

    public static final float VISIBLE_WORLD_WIDTH = 21 * (BLOCK_SIZE + GAP_SIZE);
    public static final float VISIBLE_WORLD_HEIGHT = 12 * (BLOCK_SIZE + GAP_SIZE);
    public static final float HUD_SIZE = 100f;

    // --- Dependencies ---
    private final GameStart gameStart;

    // --- View/Model/Controller ---
    private GameViewModel gameViewModel;
    private GameController gameController;

    // --- Rendering ---
    private SpriteBatch spriteBatch;

    // --- Viewports ---
    private Viewport gameViewport;
    private OrthographicCamera gameCamera;

    private Viewport hudViewport;

    // --- Views ---
    private PlayerView playerView;
    private BoardView boardView;

    private Stage hudStage;

    public GameScreen(GameStart gameStart) {
        this.gameStart = gameStart;
    }

    @Override
    public void show() {
        gameViewModel = new GameViewModel();
        gameController = new GameController(gameViewModel);

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
        Gdx.input.setInputProcessor(hudStage);
    }

    @Override
    public void render(float delta) {
        gameViewModel.update(delta);
        boolean needToUpdateCamera = gameController.isKeyPressed(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (needToUpdateCamera) updateCamera();

        drawGameLayer(delta);
        drawHudLayer(delta);
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

    private void updateCamera() {
        GridPoint2 pos = playerView.getPlayerPosition();

        float cameraX = clampCenter(pos.x * (BLOCK_SIZE + GAP_SIZE) + BLOCK_SIZE / 2f, VISIBLE_WORLD_WIDTH / 2f, BOARD_WIDTH * (BLOCK_SIZE + GAP_SIZE));
        float cameraY = clampCenter(pos.y * (BLOCK_SIZE + GAP_SIZE) + BLOCK_SIZE / 2f, VISIBLE_WORLD_HEIGHT / 2f, BOARD_HEIGHT * (BLOCK_SIZE + GAP_SIZE));

        gameCamera.position.set(cameraX, cameraY + 50, 0);  // TODO: smoothen movement
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
