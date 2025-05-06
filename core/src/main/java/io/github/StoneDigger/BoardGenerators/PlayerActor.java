package io.github.StoneDigger.BoardGenerators;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import io.github.StoneDigger.Player;

import static io.github.StoneDigger.Assets.PLAYER_TEXTURE;

public class PlayerActor extends Actor {
    private final Player player;

    public PlayerActor() {
        player = new Player();
    }
    public PlayerActor(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        float x_pos = 40 * player.getX_position();
        float y_pos = 24 * player.getY_position();
        // definitely to MODIFICATION
        batch.draw(PLAYER_TEXTURE, x_pos, y_pos, 40, 28);
    }
}
