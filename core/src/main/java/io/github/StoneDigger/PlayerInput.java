//package io.github.StoneDigger;
//
//import com.badlogic.gdx.Input;
//import com.badlogic.gdx.InputAdapter;
//
//public class PlayerInput extends InputAdapter {
//    Player player;
//
//    public PlayerInput(Player player) {
//        this.player = player;
//    }
//
//    @Override
//    public boolean keyDown(int keycode) {
//        switch (keycode) {
//            case Input.Keys.RIGHT: player.move(1, 0); break;
//            case Input.Keys.LEFT:  player.move(-1, 0); break;
//            case Input.Keys.UP:    player.move(0, 1); break;
//            case Input.Keys.DOWN:  player.move(0, -1); break;
//        }
//        return true;
//    }
//}
