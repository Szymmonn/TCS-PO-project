package io.github.StoneDigger.TryingToDraw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import io.github.StoneDigger.Actors.PlayerActor;
import com.badlogic.gdx.graphics.GL20;
import io.github.StoneDigger.BoardGenerators.Board;
import io.github.StoneDigger.BoardGenerators.TileType;

import static io.github.StoneDigger.Assets.SIZE_TEXTURE;


public class MyGameScreen implements Screen {
    private Stage stage;    // sets up the stage
    OrthographicCamera myCamera;    // sets up the camera

    final MyGame game;
    final MyBoard myBoard;
    final MyBackground myBackground;
    final PlayerActor playerActor;
    final BitmapFont font = new BitmapFont();
    private int diamondsCollected = 0;

   public MyGameScreen(final MyGame game) {
        this.game = game;
        myBoard = new MyBoard();
        myBackground = new MyBackground();
        playerActor = new PlayerActor(myBoard.getBoard());
   }

   @Override
   public void show() {    // when the stage is (mainly but not exclusive) created
       myCamera = new OrthographicCamera();
       myCamera.setToOrtho(false, MyGame.WIDTH, MyGame.HEIGHT);   // makes camera static pointing 'down' (bc false)

       stage = new Stage();
       Gdx.input.setInputProcessor(stage); // stage is now a manager of inputs // inputs are directed to stage
       stage.addActor(myBackground);
       stage.addActor(myBoard);
       stage.addActor(playerActor);

       stage.addListener(new InputListener() {
           @Override
           public boolean keyDown(InputEvent event, int keycode) {
               return false;
           }
       });
   }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();

//        Batch batch = stage.getBatch();
//        batch.setProjectionMatrix(stage.getViewport().getCamera().combined);
//
//        batch.begin();
//        float iconX = MyGame.WIDTH  - SIZE_TEXTURE - 20;
//        float iconY = MyGame.HEIGHT - SIZE_TEXTURE - 20;
//        batch.draw(TileType.getTexture(TileType.DIAMOND),
//                iconX, iconY,
//                SIZE_TEXTURE, SIZE_TEXTURE);
//
//        font.setColor(Color.BLUE);
//        String text = "diaxy " + diamondsCollected;
//        float textX = iconX - 8 - font.getRegion().getRegionWidth() * text.length() * 0.3f;
//        float textY = iconY + SIZE_TEXTURE / 2 + font.getCapHeight() / 2;
//        font.draw(batch, text, textX, textY);
//        batch.end();
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
