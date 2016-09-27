import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.FileReader;
import java.util.List;

/**
 * Created by Museum2015 on 25/9/2016.
 */
public class main {

    public static void main(String args[]) throws Exception {

        Gson gson = new Gson();
        FileReader jsonFileReader = new FileReader("/Users/Museum2015/Documents/Astar/sample1.json");
        JsonReader reader = new JsonReader(jsonFileReader);
        Grid map = gson.fromJson(reader, Grid.class);

        Position start = map.getStart();
        Position end = map.getEnd();
        List<String> checkPath = Astar.Astar(start, end, map);
        Astar.printPath(checkPath);
    }
}
