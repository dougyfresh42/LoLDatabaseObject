import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class getStatic {
    public static void main(String[] args) throws Exception {
        SQLHelper inserter = new SQLHelper();
        JsonHelper jsoner = new JsonHelper();

        inserter.arbitraryQuery("truncate \"Mastery\"");
        
        ArrayList<JSONObject> masteries = jsoner.getMasteries();

        for(int i = 0; i < masteries.size(); i++) {

            String name = masteries.get(i).get("name").toString();
            name = name.replace("\'", "");
            //System.out.println(name);

            String description = masteries.get(i).get("sanitizedDescription").toString();
            description = description.replace("\'", "");
            description = description.replace("[", "");
            description = description.replace("]", "");
            description = description.replace("\"", "\'");
            description = description.replace("\',\'", " ");
            //System.out.println(desc);

            String tree = "A";

            inserter.insertMastery("\'" + name + "\', " + description + ", \'" + tree + "\'");
        }
        inserter.close();

    }
}
