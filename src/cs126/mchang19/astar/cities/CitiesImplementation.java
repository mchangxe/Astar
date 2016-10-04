package cs126.mchang19.astar.cities;

import cs126.mchang19.astar.base.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Museum2015 on 1/10/2016.
 */
public class CitiesImplementation extends Astarable<City, Float> {
    private City[] nodes;
    private Routes[] edges;
    private Map<String, Integer> cityIndex;
    private float[][] allRoutesCost;

    public Routes[] getRoutes(){return edges;}
    public City[] getCities(){return nodes;}

    /**
     * Converts all the locations in to an index map
     */
    public void citiesToMap(){
        cityIndex = new HashMap<>();
        for (int i=0; i<nodes.length; i++){
            cityIndex.put(nodes[i].getName(), i);
        }
    }

    /**
     * Converts all routes into a 2D array with the array value being the weight of the edge
     */
    public void setAllRoutes(){
        allRoutesCost = new float[nodes.length][nodes.length];
        for (Routes a: edges){
            int startPoint = cityIndex.get(a.getNode1());
            int endPoint = cityIndex.get(a.getNode2());
            allRoutesCost[startPoint][endPoint] = a.getWeight();
            allRoutesCost[endPoint][startPoint] = a.getWeight();
        }
    }

    /**
     * Calculates distance between two cities
     * @param a city a
     * @param b city b
     * @return the distance between them
     */
    @Override
    public Float dist_between(City a, City b){
        Float distance = allRoutesCost[cityIndex.get(a.getName())][cityIndex.get(b.getName())];
        return distance;
    }

    /**
     * Calculates heuristic distance between the current city and the destination city
     * @param x city a
     * @param y city b, destination of the search
     * @return the distance between them
     */
    @Override
    public Float heuristicCalc(City x, City y) {
        float xDistance = Math.abs(x.getLatitude() - y.getLatitude());
        float yDistance = Math.abs(x.getLongitude() - y.getLongitude());
        return xDistance + yDistance;
    }


}



