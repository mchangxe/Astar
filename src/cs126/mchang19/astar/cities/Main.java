package cs126.mchang19.astar.cities;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;


import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Museum2015 on 1/10/2016.
 */
public class Main {
    public static void main(String args[]) throws Exception {

        Gson gson = new Gson();
        FileReader jsonFileReader = new FileReader("/Users/Museum2015/Documents/Astar/cities.json");
        JsonReader reader = new JsonReader(jsonFileReader);
        CitiesImplementation map = gson.fromJson(reader, CitiesImplementation.class);
        map.citiesToMap();
        map.setAllRoutes();

        Scanner input = new Scanner(System.in);
        System.out.println("Please enter your starting city:");
        String startPoint = input.nextLine();

        System.out.println("Please enter your destination city:");
        String endPoint = input.nextLine();

        City start = null;
        City end = null;



        while (start == null || end == null){

            for (City curr: map.getCities()){
                if (curr.getName().equals(startPoint)){
                    start = curr;
                }
            }

            for (City curr: map.getCities()){
                if (curr.getName().equals(endPoint)){
                    end = curr;
                }
            }

            if (start == null || end == null) {
                throw new Exception("Your starting city or destination does not exist, please try again:");
            }
        }


        List<City> checkPath = Astar.AstarAlg(start, end, map);
        Astar.printPath(checkPath);
    }
}
