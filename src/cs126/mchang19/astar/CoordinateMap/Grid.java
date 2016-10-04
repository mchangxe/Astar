package cs126.mchang19.astar.CoordinateMap;


/**
 * Created by Museum2015 on 21/9/2016.
 */
public class Grid {

    private int dimension;
    private Node start;
    private Node end;
    private Node[] obstacles;

    public int getDimension(){ return dimension; }
    public Node getStart(){ return start; }
    public Node getEnd(){ return end; }
    public Node[] getObstacles(){ return obstacles; }
}
