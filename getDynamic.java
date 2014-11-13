import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class getDynamic {
    public static void main(String[] args) throws Exception {
        SQLHelper inserter = new SQLHelper();
        JsonHelper jsoner = new JsonHelper();
        stringCleaner sc = new stringCleaner();

        //masteries
        inserter.arbitraryQuery("truncate \"Mastery\"");
        ArrayList<JSONObject> masteries = jsoner.getMasteries();

        for(int i = 0; i < masteries.size(); i++) {
            String name = masteries.get(i).get("name").toString();
            name = sc.cleanString(name);

            String description = masteries.get(i).get("sanitizedDescription").toString();
            description = sc.cleanString(description);

            String tree = "\'A\'";

            inserter.insertMastery(name + ", " + description + ", " + tree);
        }

        
    }
}
