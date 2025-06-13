package io.github.StoneDigger.view.views.utility;

import com.badlogic.gdx.graphics.Texture;
import io.github.StoneDigger.model.GameObjects.Tiles.*;

import static io.github.StoneDigger.view.Assets.*;

public class TileToTexture {

    public static Texture getTexture(ATile tile) {
        if(tile instanceof StartTile)       { return START_TEXTURE; }
        if(tile instanceof EndTile)         { return END_TEXTURE; }
        if(tile instanceof EmptyTile)       { return null; }
        if(tile instanceof RockTile)        { return ROCK_TEXTURE; }
        if(tile instanceof DirtTile)        { return DIRT_TEXTURE; }
        if(tile instanceof DiamondTile)     { return DIAMOND_TEXTURE; }
        if(tile instanceof BrickTile)       { return BRICK_TEXTURE; }
        if(tile instanceof BorderTile)      { return BORDER_TEXTURE; }
        if(tile instanceof DeactivatedEndTile)      { return END_DESK_TEXTURE; }
        if(tile instanceof  ShelterTile) { return SHELTER_TEXTURE;}

        return null;
    }
}
