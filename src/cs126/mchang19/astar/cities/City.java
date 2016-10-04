package cs126.mchang19.astar.cities;
import cs126.mchang19.astar.base.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Museum2015 on 1/10/2016.
 */
public class City extends Position<City, Routes, City> {

    private String name;
    private float latitude;
    private float longitude;
    public List<City> neighbors;

    public String getName(){return name;}
    public void setName(String x){name = x;}
    public float getLatitude(){return latitude;}
    public void setLatitude(float x){latitude = x;}
    public float getLongitude(){return longitude;}
    public void setLongitude(float x){longitude = x;}

    /**
     * An operator = function that checks if the two objects are the same
     * @param rhs the object that is compared with the operand
     * @return True if the two objects are the same
     */
    @Override
    public Boolean equalsTo(City rhs){
        if (rhs.latitude == latitude && rhs.longitude == longitude) return true;
        else return false;
    }


    /**
     * Checks all the possible routes to and from a location and lists the neighbors of that city
     * @param routes all possible routes
     * @param allLocations all possible locations
     * @return all neighbors of the city in a list
     */
    @Override
    public List<City> findNeighbors(Routes[] routes, City[] allLocations) {
        List<City> allNeighbors = new ArrayList<>();
        for (int i=0; i<routes.length; i++){
            if (routes[i].getNode1().equals(this.getName())){
                allNeighbors.add(Routes.toObject(routes[i].getNode2(), allLocations));
            }

            if (routes[i].getNode2().equals(this.getName())){
                allNeighbors.add(Routes.toObject(routes[i].getNode1(), allLocations));
            }
        }
        this.neighbors = allNeighbors;
        return allNeighbors;
    }


}

