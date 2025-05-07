package io.github.StoneDigger.Actors;

import java.util.List;

public class Cell {
    List<RockObserver> observers;

    public void addObserver(RockObserver o) {
        observers.add(o);
    }

    public void removeObserver(RockObserver o) {
        observers.remove(o);
    }

    public void notifyPositionChanged() {
    }
}
