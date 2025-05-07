package io.github.StoneDigger.TryingToDraw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.StoneDigger.Actors.PlayerActor;
import com.badlogic.gdx.graphics.GL20;
import io.github.StoneDigger.BoardGenerators.Board;
import io.github.StoneDigger.BoardGenerators.TileType;

import static io.github.StoneDigger.BoardGenerators.TileType.BLOCK_SIZE;
import static io.github.StoneDigger.BoardGenerators.TileType.GAP_SIZE;


public class MyGameScreen implements Screen {
    private Stage stage;    // sets up the stage
    OrthographicCamera myCamera;    // sets up the camera

    final MyGame game;
    final MyBoard myBoard;
    final MyBackground myBackground;
    final PlayerActor playerActor;
    final BitmapFont font = new BitmapFont();
    public static int diamondsCollected = 0;

   public MyGameScreen(final MyGame game) {
        this.game = game;
        myBoard = new MyBoard();
        myBackground = new MyBackground(Math.max(myBoard.BoardSizeX, myBoard.BoardSizeY));
        playerActor = new PlayerActor(myBoard.getBoard());
   }

   @Override
   public void show() {    // when the stage is (mainly but not exclusive) created
       myCamera = new OrthographicCamera();
       myCamera.setToOrtho(false, MyGame.WIDTH, MyGame.HEIGHT);   // makes camera static pointing 'down' (bc false)

       stage = new Stage(new ScreenViewport(myCamera));
       Gdx.input.setInputProcessor(stage); // stage is now a manager of inputs // inputs are directed to stage
       stage.addActor(myBackground);
       stage.addActor(myBoard);
       stage.addActor(playerActor);

       stage.addListener(new InputListener() {
          @Override
          public boolean keyDown(InputEvent event, int keycode) {
              if(keycode == Input.Keys.UP) {
                  myCamera.translate(0, BLOCK_SIZE + GAP_SIZE);
              }
              if(keycode == Input.Keys.DOWN) {
                  myCamera.translate(0, -BLOCK_SIZE - GAP_SIZE);
              }
              if(keycode == Input.Keys.RIGHT) {
                  myCamera.translate(BLOCK_SIZE + GAP_SIZE, 0);
              }
              if(keycode == Input.Keys.LEFT) {
                  myCamera.translate(-BLOCK_SIZE - GAP_SIZE, 0);
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
        float iconX = MyGame.WIDTH  - BLOCK_SIZE - 20;
        float iconY = MyGame.HEIGHT - BLOCK_SIZE - 20;
        batch.draw(TileType.getTexture(TileType.DIAMOND),
            iconX, iconY,
            BLOCK_SIZE, BLOCK_SIZE);

        font.setColor(Color.BLUE);
        String text = "DIAXY " + diamondsCollected;
        float textX = iconX - 8 - font.getRegion().getRegionWidth() * text.length() * 0.3f;
        float textY = iconY + BLOCK_SIZE / 2 + font.getCapHeight() / 2;
        font.draw(batch, text, textX, textY);
        batch.end();
    }


    @Override
    public void resize(int i, int i1) {

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
