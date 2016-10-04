package cs126.mchang19.astar.base;

import java.util.List;

/**
 * Created by Museum2015 on 2/10/2016.
 */
public abstract class Position<c, t, d> {
    public abstract boolean equalsTo(c rhs);
    public abstract List<c> findNeighbors(t[] routes, d[] allLocations);
}
