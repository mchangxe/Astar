import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import cs126.mchang19.astar.cities.Astar;
import cs126.mchang19.astar.cities.CitiesImplementation;
import cs126.mchang19.astar.cities.City;
import cs126.mchang19.astar.cities.Routes;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static org.junit.Assert.*;



public class CitiesImplementationTest {
    public CitiesImplementationTest() throws FileNotFoundException {
    }

    /////////////////////////////////////////////////////////////////////////


                        //Testing main astar() function


    /////////////////////////////////////////////////////////////////////////

    /**
     * Checks if the main astar function runs correctly
     * @return True since the two Lists are the same
    */
    @org.junit.Test
    public void astar() throws Exception {
        Gson gson = new Gson();
        FileReader jsonFileReader = new FileReader("/Users/Museum2015/Documents/Astar/cities.json");
        JsonReader reader = new JsonReader(jsonFileReader);
        CitiesImplementation map = gson.fromJson(reader, CitiesImplementation.class);
        map.citiesToMap();
        map.setAllRoutes();

        String startP = "Champaign";
        String endP = "Lincoln";
        City start = Routes.toObject(startP, map.getCities());
        City end = Routes.toObject(endP, map.getCities());
        List<City> checkPath = Astar.AstarAlg(start, end, map);
        List<City> answer = new ArrayList<>();
        String bloom = "Bloomington";
        City bloomington = Routes.toObject(bloom, map.getCities());
        answer.add(end);
        answer.add(bloomington);
        answer.add(start);
        assertTrue(checkPath.equals(answer));
    }

    /**
     * Checks if the main astar function runs correctly and if the printPath function prints the output correctly
     * @return True since the function should throw an exception
     */
    @org.junit.Test
    public void astar3() throws Exception {
        Gson gson = new Gson();
        //uses a different json file where an edge of the graph is deleted
        FileReader jsonFileReader = new FileReader("/Users/Museum2015/Documents/Astar/cities2.json");
        JsonReader reader = new JsonReader(jsonFileReader);
        CitiesImplementation map = gson.fromJson(reader, CitiesImplementation.class);
        map.citiesToMap();
        map.setAllRoutes();

        String startP = "Champaign";
        String endP = "Lincoln";
        City start = Routes.toObject(startP, map.getCities());
        City end = Routes.toObject(endP, map.getCities());
        List<City> checkPath = Astar.AstarAlg(start, end, map);
        Astar.printPath(checkPath);
    }


    /////////////////////////////////////////////////////////////////////////


                            //Testing reconstruct_path()


    /////////////////////////////////////////////////////////////////////////

    /**
     * Checks if the reconstruct_path() function runs correctly
     * @return True since the function should create a list with coordinates.
    */
    @org.junit.Test
    public void reconstruct_pathSuccess() throws Exception {
        Map<City,City> cameFromPath = new HashMap();
        City a = new City();
        a.setName("City A");
        City b = new City();
        b.setName("City B");
        City c = new City();
        c.setName("City C");
        cameFromPath.put(a, b);
        cameFromPath.put(b, c);
        City endPoint = a;
        List<City> result = Astar.reconstruct_path(endPoint, cameFromPath);
        List<City> answer = new ArrayList<City>(Arrays.asList(a, b, c));
        assertTrue(result.equals(answer));
    }

    /**
     * Checks if the reconstruct_path() function runs correctly
     * @return True since the function output List should end at 2,3 since 2,3 is not a key in the list.
     */
    @org.junit.Test
    public void reconstruct_pathFail() throws Exception {
        Map<City,City> cameFromPath = new HashMap();
        City a = new City();
        a.setName("City A");
        City b = new City();
        b.setName("City B");
        City c = new City();
        c.setName("City C");
        cameFromPath.put(a, b);
        cameFromPath.put(c, b);
        City endPoint = a;
        List<City> result = Astar.reconstruct_path(endPoint, cameFromPath);
        List<City> answer = new ArrayList<City>(Arrays.asList(a, b, c));
        assertFalse(result.equals(answer));
    }

    /////////////////////////////////////////////////////////////////////////


    //Testing heuristicCalc()


    /////////////////////////////////////////////////////////////////////////

    /**
     * Checks if the heuristicCalc() function runs correctly
     * @return True since the function return the correct estimate using the manhattan formula.
     */
    @org.junit.Test
    public void heuristicCalcTrue1() throws Exception {
        City x = new City();
        x.setLatitude(0f);
        x.setLongitude(0f);
        City y = new City();
        y.setLatitude(39.9075000f);
        y.setLongitude(116.3972300f);

        CitiesImplementation a = new CitiesImplementation();
        assertTrue(a.heuristicCalc(x,y) == 156.30473f);
    }

    /**
     * Checks if the heuristicCalc() function runs correctly
     * @return True since the function return the correct estimate using the manhattan formula.
     */
    @org.junit.Test
    public void heuristicCalcTrue2() throws Exception {
        City x = new City();
        x.setLatitude(-39.9075f);
        x.setLongitude(-116.39723f);
        City y = new City();
        y.setLatitude(39.9075000f);
        y.setLongitude(116.3972300f);

        CitiesImplementation a = new CitiesImplementation();
        assertTrue(a.heuristicCalc(x,y) == 312.60946f);
    }



    /////////////////////////////////////////////////////////////////////////


    //Testing findLowestF()


    /////////////////////////////////////////////////////////////////////////

    /**
     * Checks if the findLowestF() function runs correctly
     * @return True since the function returns the correct coordinate with the lowest fScore
     */
    @org.junit.Test
    public void findLowestF() throws Exception {
        HashMap<City, Float> gScore = new HashMap();
        City a = new City();
        City b = new City();
        City c = new City();
        gScore.put(a, 5f);
        gScore.put(b, 10f);
        gScore.put(c, 100f);
        Set<City> openSet = new HashSet<>();
        openSet.add(a);
        openSet.add(b);
        openSet.add(c);
        City result = Astar.findLowestF(openSet, gScore);
        assertTrue(result.equals(a));
    }


}

