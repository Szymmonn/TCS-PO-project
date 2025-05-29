package io.github.StoneDigger.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.StoneDigger.model.Boards.Board;
import io.github.StoneDigger.model.Boards.BoardGenerators.RandomBoardGenerator;
import io.github.StoneDigger.model.Level.LevelManagement.LevelManager;
import io.github.StoneDigger.model.GameObjects.Entities.Player;
import io.github.StoneDigger.model1.models.LevelStatus1;
import io.github.StoneDigger.view.Game.GameStart;
import io.github.StoneDigger.view.PlayerInputReceiver.GameController;
import io.github.StoneDigger.view.views.BoardView;
import io.github.StoneDigger.view.views.hud.HUDView;
import io.github.StoneDigger.view.views.PlayerView;
import io.github.StoneDigger.viewmodel.viewmodels.GameViewModel;

import static io.github.StoneDigger.model1.models.Constants.BLOCK_SIZE;
import static io.github.StoneDigger.model1.models.Constants.GAP_SIZE;

public class GameScreen extends ScreenAdapter {
    public static float VISIBLE_WORLD_WIDTH = 9*(BLOCK_SIZE + GAP_SIZE);
    public static float VISIBLE_WORLD_HEIGHT = 7*(BLOCK_SIZE + GAP_SIZE);
    public static float HUD_SIZE = 100;

    private final GameStart gameStart;

    public static int BOARD_HEIGHT = 20;
    public static int BOARD_WIDTH = 30;

    private GameViewModel gameViewModel;
    private GameController gameController;


    private OrthographicCamera gameCamera;
    private Viewport gameViewport;
    private Stage gameStage;

    private Viewport hudViewport;
    private Stage hudStage;

    public GameScreen(GameStart gameStart) {
        this.gameStart = gameStart;
    }

    @Override
    public void show() {
        /*
        // init__
         */
        // TODO: change with starting position
        Board board = new RandomBoardGenerator(0.1f, 0.1f, 0.1f, new LevelManager()).generate(new GridPoint2(BOARD_WIDTH, BOARD_HEIGHT), new GridPoint2(1,1));
        Player player = new Player(board);
        /*
        HAS TO CHANGE - no ensurance that this is the same player
         */
        gameViewModel = new GameViewModel(player);
        gameController = new GameController(gameViewModel);

        /*
        // viewport - game
         */
        gameViewport = new ScalingViewport(Scaling.fit, VISIBLE_WORLD_WIDTH, VISIBLE_WORLD_HEIGHT  + HUD_SIZE);
        gameCamera = (OrthographicCamera) gameViewport.getCamera();

        gameCamera.position.set(gameViewport.getWorldWidth(), gameViewport.getWorldHeight(),0);
        gameCamera.update();

        gameStage = new Stage(gameViewport);
        gameStage.addActor(new BoardView(board));
        gameStage.addActor(new PlayerView(player));

        /*
        // viewport - hud
         */
        hudViewport = new ScalingViewport(Scaling.fit, VISIBLE_WORLD_WIDTH, VISIBLE_WORLD_HEIGHT + HUD_SIZE);
        OrthographicCamera hudCamera = (OrthographicCamera) hudViewport.getCamera();

        hudCamera.position.set(gameViewport.getWorldWidth(), HUD_SIZE, 0);
        hudCamera.update();

        hudStage = new Stage(hudViewport);
        hudStage.addActor(new HUDView(new LevelStatus1()));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameViewport.apply();
        gameStage.act(delta);
        gameStage.draw();

        hudViewport.apply();
        hudStage.act(delta);
        hudStage.draw();

        gameViewport.apply();
        if(gameController.isKeyPressed(delta)) {
            float cameraX = GAP_SIZE /2f; // + gameViewModel.getPlayerPosition().x*(BLOCK_SIZE+GAP_SIZE) + BLOCK_SIZE/2f;
            float cameraY = GAP_SIZE /2f; // + gameViewModel.getPlayerPosition().y.x*(BLOCK_SIZE+GAP_SIZE) + BLOCK_SIZE/2f;

            if(cameraX < VISIBLE_WORLD_WIDTH/2) cameraX = VISIBLE_WORLD_WIDTH/2;
            else if(cameraX > BOARD_WIDTH*(BLOCK_SIZE+GAP_SIZE) - VISIBLE_WORLD_WIDTH/2) cameraX = BOARD_WIDTH*(BLOCK_SIZE + GAP_SIZE) - VISIBLE_WORLD_WIDTH/2;
            if(cameraY < VISIBLE_WORLD_HEIGHT/2) cameraY = VISIBLE_WORLD_HEIGHT/2;
            else if(cameraY > BOARD_HEIGHT*(BLOCK_SIZE+GAP_SIZE) - VISIBLE_WORLD_HEIGHT/2) cameraY = BOARD_HEIGHT*(BLOCK_SIZE + GAP_SIZE) - VISIBLE_WORLD_HEIGHT/2;

            gameCamera.position.set(cameraX, cameraY + 50, 0);
            // need to smoothen camera movement
            gameCamera.update();
        }
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
