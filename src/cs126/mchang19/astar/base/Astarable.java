package cs126.mchang19.astar.base;

import java.util.List;

/**
 * Created by Museum2015 on 1/10/2016.
 */
public abstract class Astarable<a,b> {

    public abstract b dist_between(a x, a y);
    public abstract b heuristicCalc(a x, a y);

}



