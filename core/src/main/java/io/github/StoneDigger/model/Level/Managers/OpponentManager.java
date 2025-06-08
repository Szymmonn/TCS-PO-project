package io.github.StoneDigger.model.Level.Managers;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.GameObjects.Entities.Opponent;
import io.github.StoneDigger.model.GameObjects.Entities.OpponentAI;
import io.github.StoneDigger.model.Interfaces.IOpponent;
import io.github.StoneDigger.model.Interfaces.ISelfUpdate;

import java.util.ArrayList;
import java.util.List;

public class OpponentManager {
    private List<IOpponent> opponent;

    OpponentManager(BoardManager boardManager, UpdateManager updateManager,PlayerManager playerManager,int counterO,int counterP) {
        opponent = new ArrayList<>();

        for(int i=0;i<counterO;i++)
            opponent.add(new Opponent(new GridPoint2(1,1), boardManager, updateManager,playerManager));

        for(int i=0;i<counterP;i++)
            opponent.add(new OpponentAI(new GridPoint2(1,1), boardManager, updateManager,playerManager));
    }

    public List<IOpponent> getOpponents() {
        return opponent;
    }

    public void tryClearOpponents(float delta) {
        List<IOpponent> snapshot = new ArrayList<>(opponent);
        for(IOpponent opp : snapshot) if(!opp.isActive()) snapshot.remove(opp);
        opponent = snapshot;
    }

}
