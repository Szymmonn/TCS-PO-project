package io.github.StoneDigger.viewmodel.viewmodels;

public class WhatChanged {
    private int rockFell;
    private int diamondFell;
    private boolean diamondCollected;
    private boolean death;
    private boolean endedLevel;
    private int opponentMoved;
    private boolean playerMovedOnEmptyTile;

    public WhatChanged() {}

    public void update(float delta) {
        if(rockFell > 0)
            rockFell --;
        if(diamondFell > 0)
            diamondFell --;
        diamondCollected = false;
        death = false;
        endedLevel = false;
        if(opponentMoved > 0)
            opponentMoved --;
        playerMovedOnEmptyTile = false;
    }

    public boolean hasRockFell() {
        return rockFell > 0;
    }
    public boolean hasDiamondsFell() {
        return diamondFell > 0;
    }
    public boolean hasJustDiamondCollected() {
        return diamondCollected;
    }
    public boolean havePlayerJustDied() {
        return death;
    }
    public boolean hasLevelEnded() {
        return endedLevel;
    }
    public boolean hasOpponentsMoved() {
        return opponentMoved > 0;
    }
    public boolean hasPlayerMovedOnDirt() {
        return playerMovedOnEmptyTile;
    }

    /*
    increasers
     */
    public void diamondFell() {
        diamondFell ++;
    }
    public void diamondCollected() {
        diamondCollected = true;
    }
    public void rockFell() {
        rockFell ++;
    }
    public void playerMovedOnDirt() {playerMovedOnEmptyTile = true;}
    public void playerDied() { death = true; }
}
