package io.github.StoneDigger.TryingToDraw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import io.github.StoneDigger.Actors.PlayerActor;
import com.badlogic.gdx.graphics.GL20;
import io.github.StoneDigger.models.TileType;
import com.badlogic.gdx.utils.viewport.Viewport;


import static io.github.StoneDigger.models.Constants.*;
import static io.github.StoneDigger.TryingToDraw.MyGame.GAME_HEIGHT;


public class MyGameScreen implements Screen {
    public static int TILES_ON_SCREEN_WIDTH = 9;
    public static int TILES_ON_SCREEN_HEIGHT = 7;

    public static int BOARD_UNIT = BLOCK_SIZE + GAP_SIZE;
    public static float SCREEN_HEIGHT = TILES_ON_SCREEN_HEIGHT * BOARD_UNIT;
    public static float SCREEN_WIDTH = TILES_ON_SCREEN_WIDTH * BOARD_UNIT;

    private Stage stage;    // sets up the stage
    private OrthographicCamera myCamera;    // sets up the camera
    private Viewport myViewport;

    final MyGame game;
    final MyBoard myBoard;
    final MyBackground myBackground;
    final PlayerActor playerActor;
    final BitmapFont font = new BitmapFont();
    public static int diamondsCollected = 0;

    public final int BOARD_TILES_WIDTH;
    public final int BOARD_TILES_HEIGHT;
    public final int BOARD_WIDTH;
    public final int BOARD_HEIGHT;

   public MyGameScreen(final MyGame game) {
        this.game = game;
        myBoard = new MyBoard();
        myBackground = new MyBackground(Math.max(myBoard.BoardSizeX, myBoard.BoardSizeY));
        playerActor = new PlayerActor(myBoard.getBoard());

        BOARD_TILES_HEIGHT = myBoard.BoardSizeY;
        BOARD_TILES_WIDTH = myBoard.BoardSizeX;

        BOARD_HEIGHT = BOARD_TILES_HEIGHT* BOARD_UNIT;
        BOARD_WIDTH = BOARD_TILES_WIDTH* BOARD_UNIT;

   }

   @Override
   public void show() {    // when the stage is (mainly but not exclusive) created
       //myCamera = new OrthographicCamera();
       //myCamera.setToOrtho(false, TILES_ON_SCREEN_WIDTH*BOARD_UNIT, TILES_ON_SCREEN_HEIGHT*BOARD_UNIT);   // makes camera static pointing 'down' (bc false)
        myViewport = new FitViewport(TILES_ON_SCREEN_WIDTH * BOARD_UNIT, TILES_ON_SCREEN_HEIGHT * BOARD_UNIT);
        myViewport.apply();
        myCamera = (OrthographicCamera) myViewport.getCamera();

       stage = new Stage(myViewport);
       Gdx.input.setInputProcessor(stage); // stage is now a manager of inputs // inputs are directed to stage
       stage.addActor(myBackground);
       stage.addActor(myBoard);
       stage.addActor(playerActor);

       /// TODO : CLEAN UP THIS INPUT_LISTENER.   .
       stage.addListener(new InputListener() {
           @Override
          public boolean keyDown(InputEvent event, int keycode) {
               if(keycode == Input.Keys.UP) {
                  if(myCamera.position.y + myCamera.viewportHeight/2 <= BOARD_HEIGHT - BOARD_UNIT
                    && playerActor.getPositionY() > TILES_ON_SCREEN_HEIGHT /2 - 1)
                    myCamera.translate(0, BOARD_UNIT);
              }
              if(keycode == Input.Keys.DOWN) {
                  if(myCamera.position.y - myCamera.viewportHeight/2 - BOARD_UNIT >= 0
                  && playerActor.getPositionY() < BOARD_TILES_HEIGHT - TILES_ON_SCREEN_HEIGHT /2)
                    myCamera.translate(0, -BOARD_UNIT);
              }
              if(keycode == Input.Keys.RIGHT) {
                  if(myCamera.position.x + myCamera.viewportWidth/2 <= BOARD_WIDTH - BOARD_UNIT
                  && playerActor.getPositionX() > TILES_ON_SCREEN_WIDTH /2 - 1)
                    myCamera.translate(BOARD_UNIT, 0);
              }
              if(keycode == Input.Keys.LEFT) {
                  if(myCamera.position.x - myCamera.viewportWidth/2 - BOARD_UNIT >= 0
                  && playerActor.getPositionX() < BOARD_TILES_WIDTH - TILES_ON_SCREEN_WIDTH /2)
                    myCamera.translate(-BOARD_UNIT, 0);
              }
              myCamera.update();
              return super.keyDown(event, keycode);
          }
       });
   }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();

        Batch batch = stage.getBatch();
        batch.setProjectionMatrix(stage.getViewport().getCamera().combined);

        batch.begin();
        float iconX = MyGame.GAME_WIDTH - BLOCK_SIZE - 20;
        float iconY = GAME_HEIGHT - BLOCK_SIZE - 20;
        batch.draw(TileType.getTexture(TileType.DIAMOND),
            iconX, iconY,
            BLOCK_SIZE, BLOCK_SIZE);

        font.setColor(Color.BLUE);
        String text = "DIAXY " + diamondsCollected;
        float textX = iconX - 8 - font.getRegion().getRegionWidth() * text.length() * 0.3f;
        float textY = iconY + (float) BLOCK_SIZE / 2 + font.getCapHeight() / 2;
        font.draw(batch, text, textX, textY);
        batch.end();
    }


    @Override
    public void resize(int i, int i1) {
        myViewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
         stage.dispose();
    }
}
