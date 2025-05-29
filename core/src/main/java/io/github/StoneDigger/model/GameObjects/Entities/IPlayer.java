package io.github.StoneDigger.model.GameObjects.Entities;

import io.github.StoneDigger.model.GameObjects.Entities.Hunting.IPredator;
import io.github.StoneDigger.model.GameObjects.IMovable;

public interface IPlayer extends IMovable, ICollectable, IPredator {}
