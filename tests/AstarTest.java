import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import cs126.mchang19.astar.CoordinateMap.Astar;
import cs126.mchang19.astar.CoordinateMap.Grid;
import cs126.mchang19.astar.CoordinateMap.Node;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static cs126.mchang19.astar.CoordinateMap.Astar.*;
import static org.junit.Assert.*;

/**
 * Created by Museum2015 on 25/9/2016.
 */
public class AstarTest {
    public AstarTest() throws FileNotFoundException {
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
        FileReader jsonFileReader = new FileReader("/Users/Museum2015/Documents/Astar/sample.json");
        JsonReader reader = new JsonReader(jsonFileReader);
        Grid map = gson.fromJson(reader, Grid.class);

        Node start = map.getStart();
        Node end = map.getEnd();
        List<String> checkPath = Astar(start, end, map);
        List<String> answer = new ArrayList<>();
        answer.add("3,2");
        answer.add("4,2");
        answer.add("5,2");
        answer.add("6,2");
        answer.add("6,3");
        answer.add("6,4");
        answer.add("6,5");
        answer.add("5,5");
        answer.add("4,5");
        answer.add("4,4");
        answer.add("3,4");
        answer.add("2,4");
        answer.add("1,4");
        answer.add("0,4");
        answer.add("0,3");
        answer.add("0,2");
        answer.add("0,1");
        answer.add("0,0");
        assertTrue(checkPath.equals(answer));
    }

    /**
     * Checks if the main astar function runs correctly
     * @return True since the two Lists are the same
     */
    @org.junit.Test
    public void astar2() throws Exception {
        Gson gson = new Gson();
        FileReader jsonFileReader = new FileReader("/Users/Museum2015/Documents/Astar/sample2.json");
        JsonReader reader = new JsonReader(jsonFileReader);
        Grid map = gson.fromJson(reader, Grid.class);

        Node start = map.getStart();
        Node end = map.getEnd();
        List<String> checkPath = Astar(start, end, map);
        List<String> answer = new ArrayList<>();
        answer.add("0,8");
        answer.add("0,7");
        answer.add("0,6");
        answer.add("0,5");
        answer.add("0,4");
        answer.add("0,3");
        answer.add("0,2");
        answer.add("0,1");
        answer.add("0,0");
        assertTrue(checkPath.equals(answer));
    }

    /**
     * Checks if the main astar function runs correctly. Uses a bad json input with the goal point being
     * an obstacle
     * @return True since the function should throw an exception
     */
    @org.junit.Test
    public void astar3() throws Exception {
        try {
            Gson gson = new Gson();
            FileReader jsonFileReader = new FileReader("/Users/Museum2015/Documents/Astar/sample2.json");
            JsonReader reader = new JsonReader(jsonFileReader);
            Grid map = gson.fromJson(reader, Grid.class);

            Node start = map.getStart();
            Node end = map.getEnd();
            List<String> checkPath = Astar(start, end, map);
        }catch (Exception e){
            assertTrue(e.equals("Exception in thread \"main\" java.lang.Exception: Something went wrong"));
        }


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
        Map<String,String> cameFromPath = new HashMap();
        cameFromPath.put("1,2", "2,3");
        cameFromPath.put("2,3", "2,8");
        String endPoint = "1,2";
        List<String> result = reconstruct_path(endPoint, cameFromPath);
        List<String> answer = new ArrayList<String>(Arrays.asList("1,2", "2,3", "2,8"));
        assertTrue(result.equals(answer));
    }

    /**
     * Checks if the reconstruct_path() function runs correctly
     * @return True since the function output List should end at 2,3 since 2,3 is not a key in the list.
     */
    @org.junit.Test
    public void reconstruct_pathFail() throws Exception {
        Map<String,String> cameFromPath = new HashMap();
        cameFromPath.put("1,2", "2,3");
        cameFromPath.put("2,5", "2,8");
        String endPoint = "1,2";
        List<String> result =reconstruct_path(endPoint, cameFromPath);
        List<String> answer = new ArrayList<String>(Arrays.asList("1,2", "2,3", "2,8"));
        assertFalse(result.equals(answer));
    }


    /////////////////////////////////////////////////////////////////////////


                        //Testing dist_between()


    /////////////////////////////////////////////////////////////////////////

    /**
     * Checks if the dist_between() function runs correctly
     * @return True since the function return the correct distance cost. The cost is the distance between * 10.
     */
    @org.junit.Test
    public void dist_betweenTrue1() throws Exception {
        Node x = new Node(0,0);
        Node y = new Node(10,10);
        assertTrue(dist_between(x,y) == 200);
    }

    /**
     * Checks if the dist_between() function runs correctly
     * @return True since the function return the correct distance cost. The cost is the distance between * 10.
     */
    @org.junit.Test
    public void dist_betweenTrue2() throws Exception {
        Node x = new Node(5,5);
        Node y = new Node(10,10);
        assertTrue(dist_between(x,y) == 100);
    }

    /**
     * Checks if the dist_between() function runs correctly
     * @return True since the function return the correct distance cost. The cost is the distance between * 10.
     */
    @org.junit.Test
    public void dist_betweenTrue3() throws Exception {
        Node x = new Node(-5,-5);
        Node y = new Node(10,10);
        assertTrue(dist_between(x,y) == 300);
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
        Node x = new Node(0,0);
        Node y = new Node(10,10);
        assertTrue(heuristicCalc(x,y) == 20);
    }

    /**
     * Checks if the heuristicCalc() function runs correctly
     * @return True since the function return the correct estimate using the manhattan formula.
     */
    @org.junit.Test
    public void heuristicCalcTrue2() throws Exception {
        Node x = new Node(-20,-20);
        Node y = new Node(10,10);
        assertTrue(heuristicCalc(x,y) == 60);
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
        HashMap<String, Integer> gScore = new HashMap();
        gScore.put("1,0", 5);
        gScore.put("3,0", 10);
        gScore.put("10,0", 100);
        gScore.put("2,0", 30);
        Set<String> openSet = new HashSet<>();
        openSet.add("1,0");
        openSet.add("3,0");
        openSet.add("10,0");
        openSet.add("2,0");
        String result = Astar.findLowestF(openSet, gScore);
        assertTrue(result.equals("1,0"));
    }

}