package io.github.StoneDigger.model.GameObjects.Entities;

import com.badlogic.gdx.math.GridPoint2;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Random;

import io.github.StoneDigger.model.GameObjects.Tiles.ATile;
import io.github.StoneDigger.model.Directions.*;
import io.github.StoneDigger.model.GameObjects.Tiles.EmptyTile;
import io.github.StoneDigger.model.Level.Managers.BoardManager;
import io.github.StoneDigger.model.Level.Managers.PlayerManager;
import io.github.StoneDigger.model.Level.Managers.UpdateManager;

import static java.lang.Math.min;


public class OpponentAI implements IOpponent {
    private float opponentMoveTime = 0;
    private EDirections moveDirection;
    private final BoardManager boardManager;
    private final UpdateManager updateManager;
    private final PlayerManager playerManager;

    private GridPoint2 pos;

    public OpponentAI(GridPoint2 start, BoardManager boardManager, UpdateManager updateManager, PlayerManager playerManager) {
        this.updateManager = updateManager;
        this.pos=start;
        this.boardManager = boardManager;
        this.moveDirection = EDirections.RIGHT;
        this.playerManager = playerManager;
    }

    @Override public GridPoint2 getPosition() { return pos; }
    public void setPosition(GridPoint2 p){ pos=p; }

    public boolean canMove(EDirections dir) {
        /// MAM TĄ FUNKCJE W DUPIE, PRZYKRO MI, JEST TROCHE USELESS
        return true;
    }

    public EDirections bfs(GridPoint2 start) {
        EDirections[] DIRS = {EDirections.RIGHT, EDirections.DOWN, EDirections.LEFT, EDirections.UP};
        boolean[][] visited = new boolean[boardManager.getHeight()][boardManager.getWidth()];

        Queue<Node> queue = new ArrayDeque<>();

        visited[start.y][start.x] = true;

        for (EDirections dir : DIRS) {
            int nx = start.x + dir.getDx();
            int ny = start.y + dir.getDy();

            if (!isInsideBoard(nx, ny)) continue;

            ATile tile = boardManager.getTile(new GridPoint2(nx, ny));
            if (!(tile instanceof EmptyTile)) continue;

            visited[ny][nx] = true;
            queue.add(new Node(new GridPoint2(nx, ny), dir));
        }

        while (!queue.isEmpty()) {
            Node node = queue.remove();
            GridPoint2 current = node.pos;
            EDirections firstMove = node.firstMove;

            if (current.equals(playerManager.getPosition())) {
                return firstMove;
            }

            for (EDirections dir : DIRS) {
                int nx = current.x + dir.getDx();
                int ny = current.y + dir.getDy();

                if (!isInsideBoard(nx, ny)) continue;
                if (visited[ny][nx]) continue;

                ATile tile = boardManager.getTile(new GridPoint2(nx, ny));
                if (!(tile instanceof EmptyTile)) continue;

                visited[ny][nx] = true;
                queue.add(new Node(new GridPoint2(nx, ny), firstMove));
            }
        }

        return null;
    }

    private static class Node {
        GridPoint2 pos;
        EDirections firstMove;

        Node(GridPoint2 pos, EDirections firstMove) {
            this.pos = pos;
            this.firstMove = firstMove;
        }
    }

    private boolean isInsideBoard(int x, int y) {
        return x >= 0 && x < boardManager.getWidth() && y >= 0 && y < boardManager.getHeight();
    }

    ///  Moving alongside a border

    public EDirections determineMovement() {
        return bfs(pos);
    }

    @Override public void move(EDirections dir) {
        if(canMove(dir)) pos.add(dir.getDx(), dir.getDy());
    }

    @Override
    public void update(float delta) {
        /// Killing player
        GridPoint2 playerPos = playerManager.getPosition();

        /// DO POPRAWY
        if(playerPos.equals(pos)) playerManager.getPlayer().setOnStartingPosition();

        /// Moving opponent
        opponentMoveTime+=delta;
        if(opponentMoveTime > 10f) {

            moveDirection = determineMovement();
            if(moveDirection != null) move(moveDirection);

            opponentMoveTime = 0;
        }
    }
}
