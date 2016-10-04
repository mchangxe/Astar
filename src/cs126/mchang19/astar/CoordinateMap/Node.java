package cs126.mchang19.astar.CoordinateMap;

import cs126.mchang19.astar.base.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Museum2015 on 21/9/2016.
 */
public class Node{

    private int x;
    private int y;
    private List<Node> neighbors = new ArrayList<Node>();

    Integer getX(){ return x; }
    void setX(int z){ x = x+z; }
    Integer getY(){ return y; }
    void setY(int z){ y = y+z; }
    List<Node> getNeighbors(){ return neighbors; }
    String getStringForm(){
        return getX().toString()+","+ getY().toString();
    }

    public Node(int xCoor, int yCoor){
        x = xCoor;
        y = yCoor;
    }

    public Node(String s){
        String[]t = s.split(",");
        x = Integer.valueOf(t[0]);
        y = Integer.valueOf(t[1]);
    }

    /**
     * Finds the neighbors of the current node.
     * @param listOfObstacles list of all obstacles that cannot be neighbors of a node
     * @param dimension the dimension of the map
     */
    public void findNeighbors(Node[] listOfObstacles, int dimension){
        neighbors = new ArrayList();

        if (!(this.getX()+1>dimension)){
            Node current = new Node(this.getX()+1, this.getY());
            if (!isObstacle(current, listOfObstacles)) {
                    neighbors.add(current);
            }
        }

        if (!(this.getX()-1<0)){
            Node current = new Node(this.getX()-1, this.getY());
            if (!isObstacle(current, listOfObstacles)) {
                    neighbors.add(current);
            }
        }

        if (!(this.getY()+1>dimension)){
            Node current = new Node(this.getX(), this.getY()+1);
            if (!isObstacle(current, listOfObstacles)) {
                    neighbors.add(current);
            }
        }

        if (!(this.getY()-1<0)){
            Node current = new Node(this.getX(), this.getY()-1);
            if (!isObstacle(current, listOfObstacles)) {
                    neighbors.add(current);
            }
        }
    }

    /**
     * Finds the neighbors of the current node.
     * @param x a node that could be an obstacle
     * @param listOfObstacles list of all obstacles on the map
     * @return true if the node is an obstacle, false if not an obstacle
     */
    public static boolean isObstacle(Node x, Node[] listOfObstacles){
        for (int i=0; i<listOfObstacles.length; i++){
            if (x.getX()==listOfObstacles[i].getX() && x.getY()==listOfObstacles[i].getY()){
                return true;
            }
        }
        return false;
    }


}
