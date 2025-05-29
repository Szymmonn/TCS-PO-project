package io.github.StoneDigger.model.Classes.BoardGenerators.BoardValidators;

import com.badlogic.gdx.math.GridPoint2;
import io.github.StoneDigger.model.Interfaces.IBoard;
import io.github.StoneDigger.model.Interfaces.IBoardValidator;
import io.github.StoneDigger.model.Interfaces.ITile;
import io.github.StoneDigger.model.Classes.Board;

import java.util.ArrayDeque;
import java.util.Queue;

public class SimpleBoardValidator implements IBoardValidator {
    private static final GridPoint2[] DIRS = {
        new GridPoint2(1,0), new GridPoint2(-1,0),
        new GridPoint2(0,1), new GridPoint2(0,-1)
    };

    @Override
    public boolean validate(IBoard board) {
        int w = board.getWidth(), h = board.getHeight();
        GridPoint2 start = board.getStartingPosition();
        boolean[][] visited = new boolean[w][h];
        Queue<GridPoint2> queue = new ArrayDeque<>();
        queue.add(start); visited[start.x][start.y] = true;
        while(!queue.isEmpty()){
            GridPoint2 p = queue.remove();
            if(p.x==w-2 && p.y==h-2) return true;
            for(GridPoint2 d:DIRS){
                int nx=p.x+d.x, ny=p.y+d.y;
                if(nx<0||ny<0||nx>=w||ny>=h) continue;
                if(visited[nx][ny]) continue;
                ITile t = board.getTile(new GridPoint2(nx,ny));
                if(!t.isWalkable()) continue;
                visited[nx][ny]=true; queue.add(new GridPoint2(nx,ny));
            }
        }
        return false;
    }
}
