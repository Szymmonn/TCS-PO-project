package io.github.StoneDigger.model.Classes.Tiles;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Classes.Board;
import io.github.StoneDigger.model.Interfaces.*;

public class RockTile implements ITile, ISelfUpdate {
    IBoard board;

    public void setBoard(IBoard board) {
        this.board = board;
    }

    @Override
    public boolean isWalkable() {
        return false;
    }

    @Override
    public void onWalkBy(IEntity entity) {

    }

    @Override
    public void update() {

    }
}
    //    private GridPoint2 rockPosition;
//    private float timer;
//    private int tilesFlown;
//
//    @Override
//    public boolean tryToKill() {
//        if(tilesFlown > 1 && Board.getEntity(new GridPoint2(rockPosition.x,rockPosition.y-1)) instanceof Player) return true;
//        return false;
//    }
//
//
//    @Override
//    public boolean update() {
//        rockDropTimer += delta;
//        if (rockDropTimer >= 0.3f) {
//            processFallingRocks();
//            rockDropTimer = 0;
//        }
//    }
//
//    @Override
//    public GridPoint2 getPosition() {
//        return rockPosition;
//    }
//
//    @Override
//    public void setPosition(GridPoint2 newPosition) {
//        rockPosition = newPosition;
//    }
//
//    @Override
//    public boolean tryToMove(EDirections directions) {
//        return false;
//    }
//    //dwayne


//    private final Board board;
//    private final Map<GridPoint2,Boolean> moved = new HashMap<>();
//    private float rockDropTimer = 0;
//
//
//    public void update(float delta) {
////        if (unstableRocks.isEmpty()) return;
//
//        rockDropTimer += delta;
//        if (rockDropTimer >= 0.3f) {
//            processFallingRocks();
//            rockDropTimer = 0;
//        }
//    }
//
//    private void processFallingRocks() {
//
//        for (int y = 1; y < board.getHeight(); y++) {
//            for (int x = 1; x < board.getWidth(); x++) {
//                GridPoint2 pos = new GridPoint2(x, y);
//                if(!(board.get(x,y)==TileType.ROCK)) continue;
//
//
//                if (tryFallDown(x, y)) {
//                    moved.put(new GridPoint2(x, y), false);
//                    moved.put(new GridPoint2(x, y - 1), true);
//                } else if (Boolean.TRUE.equals(moved.get(new GridPoint2(x, y))) && tryRollSideways(x, y) != 0) {
//                    int side = tryRollSideways(x, y);
//                    moved.put(new GridPoint2(x,y),false);
//                    moved.put(new GridPoint2(x + side, y),true); // Nowa pozycja
//                    if(side==1) x++;
//                } else {
//                    moved.put(new GridPoint2(x,y),false);
//                }
//            }
//        }
//
//       unstableRocks.clear();
//        unstableRocks.addAll(movedRocks);
//    }
//
//    private boolean tryFallDown(int x, int y) {
//        if (y > 0 && board.get(x, y - 1) == TileType.EMPTY) {
//            board.set(x, y - 1, TileType.ROCK);
//            board.set(x, y, TileType.EMPTY);
//            return true;
//        }
//        return false;
//    }
//
//    private int tryRollSideways(int x, int y) {
//        if (board.get(x, y - 1) != TileType.EMPTY) {
//            // Toczenie w prawo
//            if (x < board.getWidth() - 1 &&
//                    board.get(x + 1, y) == TileType.EMPTY &&
//                    board.get(x + 1, y - 1) == TileType.EMPTY) {
//
//                board.set(x + 1, y, TileType.ROCK);
//                board.set(x, y, TileType.EMPTY);
//                return 1;
//            }
//            // Toczenie w lewo
//            else if (x > 0 &&
//                    board.get(x - 1, y) == TileType.EMPTY &&
//                    board.get(x - 1, y - 1) == TileType.EMPTY) {
//
//                board.set(x - 1, y, TileType.ROCK);
//                board.set(x, y, TileType.EMPTY);
//                return -1;
//            }
//        }
//        return 0;
//    }
//}
