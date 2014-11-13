import java.util.ArrayList;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class getStatic {
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

        //champions
        inserter.arbitraryQuery("truncate \"Champion\"");
        ArrayList<JSONObject> champions = jsoner.getChampions();

        for(int i = 0; i < champions.size(); i++) {
            JSONObject champ = champions.get(i);

            String name = 
             sc.cleanString(champ.get("name").toString());

            String passiveName =
             sc.cleanString(((JSONObject)champ.get("passive")).get("name").toString());

            String passiveDesc = 
             sc.cleanString(((JSONObject)champ.get("passive")).get("sanitizedDescription").toString());
            
            String qName = 
             sc.cleanString(((JSONObject)((JSONArray)champ.get("spells")).get(0)).get("name").toString());
            String qDesc =
             sc.cleanString(((JSONObject)((JSONArray)champ.get("spells")).get(0)).get("sanitizedDescription").toString());
            String wName = 
             sc.cleanString(((JSONObject)((JSONArray)champ.get("spells")).get(1)).get("name").toString());
            String wDesc =
             sc.cleanString(((JSONObject)((JSONArray)champ.get("spells")).get(1)).get("sanitizedDescription").toString());
            String eName = 
             sc.cleanString(((JSONObject)((JSONArray)champ.get("spells")).get(2)).get("name").toString());
            String eDesc =
             sc.cleanString(((JSONObject)((JSONArray)champ.get("spells")).get(2)).get("sanitizedDescription").toString());
            String rName = 
             sc.cleanString(((JSONObject)((JSONArray)champ.get("spells")).get(3)).get("name").toString());
            String rDesc =
             sc.cleanString(((JSONObject)((JSONArray)champ.get("spells")).get(3)).get("sanitizedDescription").toString());

            String hp =
             sc.cleanString(((JSONObject)champ.get("stats")).get("hp").toString());
            String hpReg =
             sc.cleanString(((JSONObject)champ.get("stats")).get("hpregen").toString());
            String mana =
             sc.cleanString(((JSONObject)champ.get("stats")).get("mp").toString());
            String manaReg =
             sc.cleanString(((JSONObject)champ.get("stats")).get("mpregen").toString());
            String ms =
             sc.cleanString(((JSONObject)champ.get("stats")).get("movespeed").toString());
            String ad =
             sc.cleanString(((JSONObject)champ.get("stats")).get("attackdamage").toString());
            String as =
             ((JSONObject)champ.get("stats")).get("attackspeedoffset").toString();
            double asi = Double.parseDouble(as);
            as = sc.cleanString(String.valueOf(0.625/(1+asi)));
            String range =
             sc.cleanString(((JSONObject)champ.get("stats")).get("attackrange").toString());
            String armor =
             sc.cleanString(((JSONObject)champ.get("stats")).get("armor").toString());
            String mr = 
             sc.cleanString(((JSONObject)champ.get("stats")).get("spellblock").toString());

            inserter.insertChampion(name + ", " + passiveName + ", " + qName +
            ", " + wName + ", " + eName + ", " + rName + ", " +
            passiveDesc + ", " + qDesc + ", " + wDesc + ", " + eDesc + ", " + 
            rDesc + ", " + hp + ", " + hpReg + ", " + mana + ", " + manaReg + 
            ", " + ms + ", " + ad + ", " + as + ", " + range + ", " + armor + 
            ", " + mr);
        }

        //summ spells
        inserter.arbitraryQuery("truncate \"SummonerSpell\"");
        ArrayList<JSONObject> ss = jsoner.getSummonerSpells();

        for(int i = 0; i < ss.size(); i++) {
            JSONObject spell = ss.get(i);
            String name =
             sc.cleanString(spell.get("name").toString());
            String desc =
             sc.cleanString(spell.get("sanitizedDescription").toString());

            inserter.insertSummonerSpell(name + ", " + desc);
        }

        //runes
        inserter.arbitraryQuery("truncate \"Rune\"");
        ArrayList<JSONObject> runes = jsoner.getRunes();

        for(int i = 0; i < runes.size(); i++) {
            JSONObject rune = runes.get(i);

            String name =
             sc.cleanString(rune.get("name").toString());

            String desc = 
             sc.cleanString(rune.get("description").toString());

            String price = "\'0.0\'";

            String tier = 
             sc.cleanString(((JSONObject)rune.get("rune")).get("tier").toString());

            inserter.insertRune(name + ", " + desc + ", " + price + ", " + tier);
        }

        //items
        inserter.arbitraryQuery("truncate \"Item\"");
        ArrayList<JSONObject> items = jsoner.getItems();

        for(int i = 0; i < items.size(); i++) {
            JSONObject item = items.get(i);
            String name = 
             sc.cleanString(item.get("name").toString());
            String desc =
             sc.cleanString(item.get("sanitizedDescription").toString());
            String price =
             sc.cleanString(((JSONObject)item.get("gold")).get("total").toString());
            String[] comp = new String[4];
            comp[0] =
             "null";
            comp[1] = 
             "null";
            comp[2] = 
             "null";
            comp[3] =
             "null";
            String id =
             sc.cleanString(item.get("id").toString());

            if(item.containsKey("from")) {
                JSONArray recipe = (JSONArray)item.get("from");
                for(int j = 0; j < recipe.size(); j++) {
                    String idkey = recipe.get(j).toString();
                    for(int k = 0; k < items.size(); k++) {
                        JSONObject it = items.get(k);
                        if(idkey.equals(it.get("id").toString())) {
                            comp[j] = sc.cleanString(it.get("name").toString());
                            break;
                        }
                    }
                }
            }

            inserter.insertItem(name + ", " + desc + ", " + price + ", " + 
            comp[0] + ", " + comp[1] + ", " + comp[2] + ", " + comp[3] + 
            ", " + id);
        }

        inserter.close();

    }
}
