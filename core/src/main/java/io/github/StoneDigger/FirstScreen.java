package io.github.StoneDigger;

import com.badlogic.gdx.Gdx;
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
        //Gdx.input.setInputProcessor(new PlayerInput(player));
    }

    @Override
    public void render(float delta) {
        input();
        logic();
        draw();
    }

    private void input() {
        player.updateMotion();
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
