import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Museum2015 on 21/9/2016.
 */
public class Position {

    private int x;
    private int y;
    private List<Position> neighbors = new ArrayList<Position>();

    Integer getX(){ return x; }
    void setX(int z){ x = x+z; }
    Integer getY(){ return y; }
    void setY(int z){ y = y+z; }
    List<Position> getNeighbors(){ return neighbors; }
    String getStringForm(){
        return getX().toString()+","+ getY().toString();
    }

    public Position(int xCoor, int yCoor){
        x = xCoor;
        y = yCoor;
    }
    public Position(String s){
        String[]t = s.split(",");
        x = Integer.valueOf(t[0]);
        y = Integer.valueOf(t[1]);
    }

    public void findNeighbors(Position[] listOfObstacles, int dimension){
        neighbors = new ArrayList();

        if (!(this.getX()+1>dimension)){
            Position current = new Position(this.getX()+1, this.getY());
            if (!isObstacle(current, listOfObstacles)) {
                    neighbors.add(current);
            }
        }

        if (!(this.getX()-1<0)){
            Position current = new Position(this.getX()-1, this.getY());
            if (!isObstacle(current, listOfObstacles)) {
                    neighbors.add(current);
            }
        }

        if (!(this.getY()+1>dimension)){
            Position current = new Position(this.getX(), this.getY()+1);
            if (!isObstacle(current, listOfObstacles)) {
                    neighbors.add(current);
            }
        }

        if (!(this.getY()-1<0)){
            Position current = new Position(this.getX(), this.getY()-1);
            if (!isObstacle(current, listOfObstacles)) {
                    neighbors.add(current);
            }
        }
    }

    public static boolean isObstacle(Position x, Position[] listOfObstacles){
        for (int i=0; i<listOfObstacles.length; i++){
            if (x.getX()==listOfObstacles[i].getX() && x.getY()==listOfObstacles[i].getY()){
                return true;
            }
        }
        return false;
    }


}
