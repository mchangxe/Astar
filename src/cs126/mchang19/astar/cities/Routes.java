package cs126.mchang19.astar.cities;

/**
 * Created by Museum2015 on 1/10/2016.
 */
public class Routes {

    private String node1;
    private String node2;
    private int weight;

    public String getNode1(){return node1;}
    public String getNode2(){return node2;}
    public int getWeight(){return weight;}

    /**
     * Converts a city string name to the actualy city object
     * @param a city a
     * @param allCities array of all cities
     * @return the distance between them
     */
    public static City toObject(String a, City[] allCities){
        for (int i=0; i<allCities.length; i++){
            if (allCities[i].getName().equals(a)){
                return allCities[i];
            }
        }
        return null;
    }

}

