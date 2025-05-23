package io.github.StoneDigger.view.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.StoneDigger.model.BoardGenerators.RandomBoardGenerator;
import io.github.StoneDigger.view.Game.GameStart;
import io.github.StoneDigger.view.PlayerInputReceiver.GameController;
import io.github.StoneDigger.model.models.BoardModel;
import io.github.StoneDigger.model.models.PlayerModel;
import io.github.StoneDigger.viewmodel.viewmodels.GameViewModel;
import io.github.StoneDigger.view.views.BoardView;
import io.github.StoneDigger.view.views.PlayerView;

import static io.github.StoneDigger.model.models.Constants.BLOCK_SIZE;
import static io.github.StoneDigger.model.models.Constants.GAP_SIZE;

public class GameScreen extends ScreenAdapter {
    public static float VISIBLE_WORLD_WIDTH = 9*(BLOCK_SIZE + GAP_SIZE);
    public static float VISIBLE_WORLD_HEIGHT = 7*(BLOCK_SIZE + GAP_SIZE);

    private final GameStart gameStart;

    public static int BOARD_HEIGHT = 20;
    public static int BOARD_WIDTH = 30;

    private GameViewModel gameViewModel;
    private GameController gameController;

    private Stage stage;

    private OrthographicCamera camera;
    private Viewport viewport;

    public GameScreen(GameStart gameStart) {
        this.gameStart = gameStart;
    }

    @Override
    public void show() {
        // TODO: change with starting position
        PlayerModel playerModel = new PlayerModel(1, 1);
        BoardModel boardModel = new RandomBoardGenerator(0.1f, 0.1f, 0.1f).generate(BOARD_WIDTH, BOARD_HEIGHT, 1, 1);

        gameViewModel = new GameViewModel(playerModel, boardModel);
        gameController = new GameController(gameViewModel);

        viewport = new FitViewport(VISIBLE_WORLD_WIDTH, VISIBLE_WORLD_HEIGHT);
        camera = (OrthographicCamera) viewport.getCamera();
        viewport.apply();
        camera.position.set(viewport.getWorldWidth(), viewport.getWorldHeight(),0);
        camera.update();

        stage = new Stage(viewport);

        stage.addActor(new BoardView(boardModel));
        stage.addActor(new PlayerView(playerModel));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();

        if(gameController.isKeyPressed(delta)) {
            float cameraX = GAP_SIZE /2f + gameViewModel.getPlayerPositionX()*(BLOCK_SIZE+GAP_SIZE) + BLOCK_SIZE/2f;
            float cameraY = GAP_SIZE /2f + gameViewModel.getPlayerPositionY()*(BLOCK_SIZE+GAP_SIZE) + BLOCK_SIZE/2f;

            if(cameraX < VISIBLE_WORLD_WIDTH/2) cameraX = VISIBLE_WORLD_WIDTH/2;
            else if(cameraX > BOARD_WIDTH*(BLOCK_SIZE+GAP_SIZE) - VISIBLE_WORLD_WIDTH/2) cameraX = BOARD_WIDTH*(BLOCK_SIZE + GAP_SIZE) - VISIBLE_WORLD_WIDTH/2;
            if(cameraY < VISIBLE_WORLD_HEIGHT/2) cameraY = VISIBLE_WORLD_HEIGHT/2;
            else if(cameraY > BOARD_HEIGHT*(BLOCK_SIZE+GAP_SIZE) - VISIBLE_WORLD_HEIGHT/2) cameraY = BOARD_HEIGHT*(BLOCK_SIZE + GAP_SIZE) - VISIBLE_WORLD_HEIGHT/2;

            camera.position.set(cameraX, cameraY, 0);
            // need to smoothen camera movement
            camera.update();
        }
    }

    @Override
    public void resize(int a, int b) {
        viewport.update(a,b,true);
    }

    @Override
    public void dispose() {stage.dispose();}
}
