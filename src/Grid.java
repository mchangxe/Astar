/**
 * Created by Museum2015 on 21/9/2016.
 */
public class Grid {

    private int dimension;
    private Position start;
    private Position end;
    private Position[] obstacles;

    int getDimension(){ return dimension; }
    Position getStart(){ return start; }
    Position getEnd(){ return end; }
    Position[] getObstacles(){ return obstacles; }
}
