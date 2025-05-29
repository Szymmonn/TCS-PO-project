package io.github.StoneDigger.model.GameObjects.Entities;

import io.github.StoneDigger.model.GameObjects.Entities.Hunting.IPredator;
import io.github.StoneDigger.model.GameObjects.IMovable;
import io.github.StoneDigger.model.GameObjects.ISelfUpdate;

public interface IPlayer extends IMovable, ICollectable, IPredator, ISelfUpdate {

}
