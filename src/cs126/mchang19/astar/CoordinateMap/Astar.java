package cs126.mchang19.astar.CoordinateMap;

import java.io.FileNotFoundException;
import java.util.*;
import java.lang.*;

/**
 * Created by Museum2015 on 21/9/2016.
 */
public class Astar {

    /**
     * The main Astar search function from wikipedia pseudocode. Takes a starting point, an ending point and the map
     * that the search is ran on. Returns a list of coordinates in the form (x,y) of the shortest path from start to
     * end point.
     *
     * @param  startPoint The starting point of the search
     * @param  endPoint The goal of the search
     * @return  A list of coordinates in the shortest path.
     */
    public static List<String> Astar(Node startPoint, Node endPoint, Grid map) throws Exception,FileNotFoundException {

        Set<String> closedSet = new HashSet<>();
        Set<String> openSet = new HashSet<>();

        Node[] allObstacles = map.getObstacles();

        Map<String,String> cameFrom = new HashMap();

        int dimension = map.getDimension();

        HashMap<String, Integer> gScore = new HashMap();
        gScore.put(startPoint.getStringForm(), 0);

        HashMap<String, Integer> fScore = new HashMap();
        fScore.put(startPoint.getStringForm(), heuristicCalc(startPoint, endPoint));

        openSet.add(startPoint.getStringForm());

        int tentative_gScore = 0;

        while (!openSet.isEmpty()){

            Node current = new Node(findLowestF(openSet, fScore));

            //Compares the two coordinates in string form to avoid errors in comparing objects.
            if (current.getStringForm().equals(endPoint.getStringForm())){
                return reconstruct_path(current.getStringForm(), cameFrom);
            }

            openSet.remove(current.getStringForm());
            closedSet.add(current.getStringForm());
            current.findNeighbors(allObstacles, dimension);


            for (Node currNeighbor: current.getNeighbors()){

                if (closedSet.contains(currNeighbor.getStringForm())){
                    continue;
                }
                tentative_gScore = gScore.get(current.getStringForm()) + dist_between(current, currNeighbor);

                if (!openSet.contains(currNeighbor.getStringForm())){
                    openSet.add(currNeighbor.getStringForm());
                }else{
                    if (tentative_gScore >= gScore.get(currNeighbor.getStringForm())){
                        continue;
                    }
                }
                cameFrom.put(currNeighbor.getStringForm(), current.getStringForm());
                gScore.put(currNeighbor.getStringForm(), tentative_gScore);
                fScore.put(currNeighbor.getStringForm(), gScore.get(currNeighbor.getStringForm()) + heuristicCalc(currNeighbor, endPoint));

            }

        }

        //If did not reach return line in the above while loop, then something went wrong
        throw new Exception("Something went wrong");

    }

    /**
     * Helper function that prints a list of coordinates of a path
     *
     * @param path The list of coordinates of a path
     */
    static void printPath(List<String> path){
        if (path == null){
            System.out.println("List is null.");
        }else {
            for (String curr : path) {
                System.out.println(curr);
            }
        }
    }

    /**
     * Helper function that prints a list of coordinates of a path
     *
     * @param endPoint The last point of the graph which should be the goal point of the search.
     * @return A list of coordinates of the path that is in the right direction from the starting point to end point.
     */
    public static List<String> reconstruct_path(String endPoint, Map<String, String> came){
        List<String> total_path = new ArrayList();
        total_path.add(endPoint);
        String current = endPoint;
        while (came.keySet().contains(current)){
            current = came.get(current);
            total_path.add(current);
        }
        return total_path;
    }

    /**
     * Calculates the distance/cost of moving from 1 point to another that is nearby. Only vertical and horizontal
     * movements are allowed and each movement costs 10 units.
     *
     * @param start The first point
     * @param end The second point
     * @return The cost of moving from 1 point to 1 of its nearby neighbors.
     */
    public static int dist_between(Node start, Node end){
        int distance =0;
        distance = (Math.abs(start.getX()-end.getX())) + (Math.abs(start.getY()-end.getY()));
        distance = distance * 10;

        return distance;
    }

    /**
     * Calculates the heuristic estimate of distance from 1 point to another using the manhattan method.
     *
     * @param start The first point
     * @param end The second point
     * @return The distance between two points
     */
    public static int heuristicCalc(Node start, Node end){

        int horizontal = Math.abs(end.getX() - start.getX());
        int vertical = Math.abs(end.getY() - start.getY());

        return horizontal+vertical;
    }

    /**
     * Find the lowest coordinate in a list that has the lowest fScore.
     *
     * @param open List of coordinates available
     * @return The coordinate with the lowest fScore in string format (x,y)
     */
    public static String findLowestF(Set<String> open, HashMap<String, Integer> fs){

        int currFScore = Integer.MAX_VALUE;
        String lowestFPosition = null;
        for (String curr : open){
            if (fs.get(curr) < currFScore) {
                currFScore = fs.get(curr);
                lowestFPosition = curr;
            }
        }

        return lowestFPosition;
    }





}
