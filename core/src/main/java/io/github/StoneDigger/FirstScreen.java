package io.github.StoneDigger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;


/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {

    final Main game;
    Player player;


    public FirstScreen(Main game) {
        this.game = game;
        this.player = new Player(game);
    }

    @Override
    public void show() {
        // Prepare your screen here.
    }

    @Override
    public void render(float delta) {
        input();
        logic();
        draw();
    }

    private void input() {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player.move(1,0);
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player.move(-1,0);
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player.move(0,1);
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            player.move(0,-1);
        }
    }

    private void logic() {

    }

    private void draw() {
        ScreenUtils.clear(Color.BLACK);
        game.viewport.apply();
        game.spriteBatch.setProjectionMatrix(game.viewport.getCamera().combined);
        game.spriteBatch.begin();


        //Rysujemy na ekranie wszystkie struktury

        //Rysujemy playera
        player.draw();


        game.spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        // Resize your screen here. The parameters represent the new window size.
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        // Destroy screen's assets here.
    }
}
