import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import java.util.Iterator;

public class getDynamic {
    public static void main(String[] args) throws Exception {
        SQLHelper inserter = new SQLHelper();
        JsonHelper jsoner = new JsonHelper();
        stringCleaner sc = new stringCleaner();

        inserter.arbitraryQuery("truncate \"Player\"");
        inserter.arbitraryQuery("truncate \"RuneSet\"");
        ArrayList<JSONObject> players = jsoner.getChallengerPlayers();

        for(int i = 0; i < players.size(); i++) {
            String name = sc.cleanString(
                players.get(i).get("playerOrTeamName").toString());
            String id = players.get(i).get("playerOrTeamId").toString();

            String lp = players.get(i).get("leaguePoints").toString();

            String wins = players.get(i).get("wins").toString();

            String losses = jsoner.getLosses(id);

            inserter.insertPlayer(name + ", " + lp + ", " + wins + ", " + losses);

            //RUNES

            ArrayList<JSONObject> runes = jsoner.getPlayerRunes(id);

            for(int j = 0; j < runes.size(); j++) {
                JSONObject page = runes.get(j);

                //System.out.println(page.toString());

                if(page.get("name") == null) continue;

                String pageName = sc.cleanString(
                    page.get("name").toString());

                JSONArray runePage = (JSONArray)page.get("slots");
                if(runePage == null) continue;
                String runeString = "";
                boolean first = true;

                Iterator<JSONObject> iter = runePage.iterator();
                int count = 0;
                while(iter.hasNext()) {
                    count++;
                    JSONObject rune = iter.next();
                    if(first){
                        runeString = runeString + sc.cleanString(
                            rune.get("runeId").toString());
                        first = false;
                    } else {
                        runeString = runeString + ", " + sc.cleanString(
                            rune.get("runeId").toString());
                    }
                }
                if(count != 30) continue;

                inserter.insertRuneSet(runeString + ", " + name + ", " + pageName);
            }

            //MASTERIES
            
            ArrayList<JSONObject> masteries = jsoner.getPlayerMasteries(id);

            for(int j = 0; j < masteries.size(); j++) {
                JSONObject page = masteries.get(j);

                if(page.get("name") == null) continue;

                String pageName = sc.cleanString(
                    page.get("name").toString());

                JSONArray masteryPage = (JSONArray)page.get("masteries");
                if(masteryPage == null) continue;
                String masteryString = "";
                
                Iterator<JSONObject> iter = masteryPage.iterator();
                int count = 30;
                while(iter.hasNext()) {
                    count--;
                    JSONObject mastery = iter.next();
                    masteryString = masteryString + sc.cleanString(
                        mastery.get("id").toString()) + ", ";
                }

                for(int k = 0; k < count; k++)
                    masteryString = masteryString + "null, ";

                inserter.insertMasterySet(masteryString + name + ", " + pageName);
                }

            Thread.sleep(3000);
            System.out.print(".");
        }
    }
}
