package io.github.StoneDigger.view.musicAndSounds;

import io.github.StoneDigger.viewmodel.viewmodels.WhatChanged;

import static io.github.StoneDigger.view.Assets.*;

public class SoundEffects {
    private final WhatChanged whatChanged;
    private int whichDirtSound = 0;

    public SoundEffects(WhatChanged whatChanged) {
        this.whatChanged = whatChanged;
    }

    public void update(float delta) {
        if(whatChanged.hasLevelEnded()) {
            levelEndSound.play(soundVolume);
            return;
        }
        if(whatChanged.hasJustDiamondCollected()) {
            diamondCollectedSound.play(soundVolume);
        }
        if(whatChanged.hasDiamondsFell()) {
            fallingDiamondSound.play(soundVolume);
        }
        if(whatChanged.havePlayerJustDied()) {
            dieingSound.play();
        }
        if(whatChanged.hasPlayerMovedOnDirt()) {
            switch (whichDirtSound) {
                case 0: dirt1Sound.play(soundVolume); break;
                case 1: dirt2Sound.play(soundVolume); break;
                case 2: dirt3Sound.play(soundVolume); break;
            }
            whichDirtSound = (whichDirtSound + 1) % 3;
        }
        if(whatChanged.hasRockFell()) {
            rockFallingSound.play(soundVolume);
        }
    }
}
