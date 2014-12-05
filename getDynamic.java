import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class getDynamic {
    public static void main(String[] args) throws Exception {
        SQLHelper inserter = new SQLHelper();
        JsonHelper jsoner = new JsonHelper();
        stringCleaner sc = new stringCleaner();

        inserter.arbitraryQuery("truncate \"Player\"");
        ArrayList<JSONObject> players = jsoner.getChallengerPlayers();

        for(int i = 0; i < players.size(); i++) {
            String name = sc.cleanString(
                players.get(i).get("playerOrTeamName").toString());
            String id = players.get(i).get("playerOrTeamId").toString();

            String lp = players.get(i).get("leaguePoints").toString();

            String wins = players.get(i).get("wins").toString();

            String losses = jsoner.getLosses(id);

            inserter.insertPlayer(name + ", " + lp + ", " + wins + ", " + losses);

            Thread.sleep(1000);
            System.out.print(".");
        }
    }
}
