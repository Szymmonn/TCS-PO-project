package io.github.StoneDigger.model.Interfaces;

import io.github.StoneDigger.model.GameObjects.Entities.Hunting.IHunting;
import io.github.StoneDigger.model.GameObjects.Entities.Hunting.IPredator;
import io.github.StoneDigger.model.GameObjects.IMovable;
import io.github.StoneDigger.model.GameObjects.ISelfUpdate;

public interface IOpponent extends IMovable, IPredator, IHunting, ISelfUpdate {}
