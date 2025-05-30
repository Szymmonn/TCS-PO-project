package io.github.StoneDigger.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.StoneDigger.model.Level.LevelStats;
import io.github.StoneDigger.view.Game.GameStart;
import io.github.StoneDigger.view.PlayerInputReceiver.GameController;
import io.github.StoneDigger.view.views.BoardView;
import io.github.StoneDigger.view.views.HUDView;
import io.github.StoneDigger.view.views.PlayerView;
import io.github.StoneDigger.viewmodel.viewmodels.GameViewModel;


public class GameScreen extends ScreenAdapter {
    /*
    view parameters
     */
    public static int BLOCK_SIZE = 100;
    public static int GAP_SIZE = 0;

    public static float VISIBLE_WORLD_WIDTH = 21*(BLOCK_SIZE + GAP_SIZE);
    public static float VISIBLE_WORLD_HEIGHT = 12*(BLOCK_SIZE + GAP_SIZE);
    public static float HUD_SIZE = 100;

    private final GameStart gameStart;

    public static int BOARD_HEIGHT = 16;
    public static int BOARD_WIDTH = 21;

    private GameViewModel gameViewModel;
    private GameController gameController;


    private Viewport gameViewport;
    private OrthographicCamera gameCamera;
    private PlayerView playerView;
    private BoardView boardView;

    private Viewport hudViewport;
    private OrthographicCamera hudCamera;
    private HUDView hudView;

    private SpriteBatch spriteBatch;

    public GameScreen(GameStart gameStart) {
        this.gameStart = gameStart;
    }

    @Override
    public void show() {
        /*
        // init__
         */
        // TODO: change with starting position
        //Board board = new RandomBoardGenerator(0.1f, 0.1f, 0.1f, new LevelManager()).generate(new GridPoint2(BOARD_WIDTH, BOARD_HEIGHT), new GridPoint2(1,1));
        //Player player = new Player(board);
        /*
        HAS TO CHANGE - no ensurance that this is the same player
         */
        gameViewModel = new GameViewModel();
        gameController = new GameController(gameViewModel);


        /*
        TO CHANGE - when viewmodel is working
         */
        //boardView = new BoardView(board);
        //playerView = new PlayerView(player);
        boardView = new BoardView(gameViewModel.getBoard());
        playerView = new PlayerView(gameViewModel.getPlayer());

        /*
        viewport - game
         */
        gameViewport = new ScalingViewport(Scaling.fit, VISIBLE_WORLD_WIDTH, VISIBLE_WORLD_HEIGHT  + HUD_SIZE);
        gameCamera = (OrthographicCamera) gameViewport.getCamera();

        updateCamera();
        /*
        viewport - hud
         */
        hudViewport = new ScalingViewport(Scaling.fit, VISIBLE_WORLD_WIDTH, VISIBLE_WORLD_HEIGHT + HUD_SIZE);
        hudCamera = (OrthographicCamera) hudViewport.getCamera();

        hudCamera.position.set(gameViewport.getWorldWidth(), HUD_SIZE, 0);
        hudCamera.update();

        hudView = new HUDView(gameViewModel.getLevelStats());

        spriteBatch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        /*
        updating the game
         */
        gameViewModel.update(delta);
        boolean needToUpdateCamera = gameController.isKeyPressed(delta);

        /*
        drawing
         */

        // black background
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


       // if(needToUpdateCamera)
        updateCamera();

        /*
        board and player drawing
         */
        spriteBatch.setProjectionMatrix(gameCamera.combined);
        gameViewport.apply();
        boardView.act(delta);
        playerView.act(delta);

        spriteBatch.begin();
        boardView.draw(spriteBatch, 1);
        playerView.draw(spriteBatch, 1);
        spriteBatch.end();

        /*
        hud drawing
         */
        spriteBatch.setProjectionMatrix(hudCamera.combined);
        hudViewport.apply();
        hudView.act(delta);

        spriteBatch.begin();
        hudView.draw(spriteBatch, 1);
        spriteBatch.end();


    }

    void updateCamera() {
        gameViewport.apply();

        GridPoint2 playerPosition = playerView.getPlayerPosition();

        float cameraX = GAP_SIZE /2f + playerPosition.x*(BLOCK_SIZE+GAP_SIZE) + BLOCK_SIZE/2f;
        float cameraY = GAP_SIZE /2f + playerPosition.y*(BLOCK_SIZE+GAP_SIZE) + BLOCK_SIZE/2f;

        if(cameraX < VISIBLE_WORLD_WIDTH/2) cameraX = VISIBLE_WORLD_WIDTH/2;
        else if(cameraX > BOARD_WIDTH*(BLOCK_SIZE+GAP_SIZE) - VISIBLE_WORLD_WIDTH/2) cameraX = BOARD_WIDTH*(BLOCK_SIZE + GAP_SIZE) - VISIBLE_WORLD_WIDTH/2;
        if(cameraY < VISIBLE_WORLD_HEIGHT/2) cameraY = VISIBLE_WORLD_HEIGHT/2;
        else if(cameraY > BOARD_HEIGHT*(BLOCK_SIZE+GAP_SIZE) - VISIBLE_WORLD_HEIGHT/2) cameraY = BOARD_HEIGHT*(BLOCK_SIZE + GAP_SIZE) - VISIBLE_WORLD_HEIGHT/2;

        gameCamera.position.set(cameraX, cameraY + 50, 0);
        // need to smoothen camera movement
        gameCamera.update();
    }

    @Override
    public void resize(int a, int b) {
        gameViewport.update(a,b,true);
        hudViewport.update(a,b,true);
    }

    @Override
    public void dispose() {

    }

}
