package cs126.mchang19.astar.cities;

import cs126.mchang19.astar.cities.*;

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
    public static List<City> AstarAlg(City startPoint, City endPoint, CitiesImplementation map) throws Exception {

        Set<City> closedSet = new HashSet<>();
        Set<City> openSet = new HashSet<>();

        Map<City,City> cameFrom = new HashMap();

        HashMap<City, Float> gScore = new HashMap();
        gScore.put(startPoint, 0f);

        HashMap<City, Float> fScore = new HashMap();
        fScore.put(startPoint, map.heuristicCalc(startPoint, endPoint));

        openSet.add(startPoint);

        float tentative_gScore = 0f;

        while (!openSet.isEmpty()){

            City current = findLowestF(openSet, fScore);

            //Compares the two coordinates in string form to avoid errors in comparing objects.
            if (current.equalsTo(endPoint)){
                return reconstruct_path(current, cameFrom);
            }

            openSet.remove(current);
            closedSet.add(current);

            current.findNeighbors(map.getRoutes(), map.getCities());

            for (City currNeighbor: current.neighbors){

                if (closedSet.contains(currNeighbor)){
                    continue;
                }
                tentative_gScore = gScore.get(current) + map.dist_between(current, currNeighbor);

                if (!openSet.contains(currNeighbor)){
                    openSet.add(currNeighbor);
                }else{
                    if (tentative_gScore >= gScore.get(currNeighbor)){
                        continue;
                    }
                }
                cameFrom.put(currNeighbor, current);
                gScore.put(currNeighbor, tentative_gScore);
                fScore.put(currNeighbor, gScore.get(currNeighbor) + map.heuristicCalc(currNeighbor, endPoint));

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
    public static void printPath(List<City> path){
        if (path == null){
            System.out.println("List is null.");
        }else {
            for (City curr : path) {
                System.out.println(curr.getName());
            }
        }
    }

    /**
     * Helper function that prints a list of coordinates of a path
     *
     * @param endPoint The last point of the graph which should be the goal point of the search.
     * @return A list of coordinates of the path that is in the right direction from the starting point to end point.
     */
    public static List<City> reconstruct_path(City endPoint, Map<City, City> came){
        List<City> total_path = new ArrayList();
        total_path.add(endPoint);
        City current = endPoint;
        while (came.keySet().contains(current)){
            current = came.get(current);
            total_path.add(current);
        }
        return total_path;
    }

    /**
     * Find the lowest coordinate in a list that has the lowest fScore.
     *
     * @param open List of coordinates available
     * @return The coordinate with the lowest fScore in string format (x,y)
     */
    public static City findLowestF(Set<City> open, HashMap<City, Float> fs){

        float currFScore = Float.MAX_VALUE;
        City lowestFPosition = null;
        for (City curr : open){
            if (fs.get(curr) < currFScore) {
                currFScore = fs.get(curr);
                lowestFPosition = curr;
            }
        }

        return lowestFPosition;
    }





}

