import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class JsonHelper 
{	
	// The key to pass with the url on JSON calls
	private String API_KEY = "1b7f166f-f7f3-42be-8fc3-3c8d15232c1b";
	
	// The common lead for the url on all JSON calls
	private String BASE_URL = "https://na.api.pvp.net/api/lol/";
	
	// The maximum number of games that can be found at a time
	private int MAX_GAMES = 1000;
	
	// Used for parsing JSON strings to JSONObjects/JSONArrays
	private JSONParser parser = new JSONParser();
	
	public static enum Region {
		BR, EUNE, EUW, KR, LAN, LAS, NA, OCE, RU, TR 
	}
	
	public JsonHelper()
	{
	}
	
	/**
	 * Given a BufferedReader build a string with every line in the reader
	 * 
	 * @param reader BufferedReader from which the string will be built
	 * @return String
	 * @throws IOException
	 */
	public String readAll(BufferedReader reader) throws IOException
	{
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = reader.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	}
	
	/**
	 * Returns a list of JSONObjects containing information for each possible Champion
	 * 
	 * @return ArrayList<JSONObject>
	 * @throws IOException
	 * @throws ParseException
	 */
	public ArrayList<JSONObject> getChampions() throws IOException, ParseException
	{
		URL url = new URL(BASE_URL + "static-data/na/v1.2/champion?champData=passive,spells,stats&api_key=" + API_KEY);
		InputStream is = url.openStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		String jsonText = readAll(reader);
		Map map = (Map)parser.parse(jsonText);
		Map champMap = (Map)map.get("data");
		return new ArrayList<JSONObject>(champMap.values());
	}
	
	/**
	 * Returns a list of JSONObjects containing information for each possible Item
	 * 
	 * @return ArrayList<JSONObject>
	 * @throws IOException
	 * @throws ParseException
	 */
	public ArrayList<JSONObject> getItems() throws IOException, ParseException
	{
		URL url = new URL(BASE_URL + "static-data/na/v1.2/item?itemListData=from,gold,groups,sanitizedDescription&api_key=" + API_KEY);
		InputStream is = url.openStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		String jsonText = readAll(reader);
		Map map = (Map)parser.parse(jsonText);
		Map itemMap = (Map)map.get("data");
		return new ArrayList<JSONObject>(itemMap.values());
	}
	
	/**
	 * Returns a list of JSONObjects containing information for each possible Mastery
	 * 
	 * @return ArrayList<JSONObject>
	 * @throws IOException
	 * @throws ParseException
	 */
	public ArrayList<JSONObject> getMasteries() throws IOException, ParseException
	{
		URL url = new URL(BASE_URL + "static-data/na/v1.2/mastery?masteryListData=prereq,ranks,sanitizedDescription&api_key=" + API_KEY);
		InputStream is = url.openStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		String jsonText = readAll(reader);
		Map map = (Map)parser.parse(jsonText);
		Map masteriesMap = (Map)map.get("data");
		return new ArrayList<JSONObject>(masteriesMap.values());
	}
	
	/**
	 * Returns a list of JSONObjects containing information for each possible Rune
	 * 
	 * @return ArrayList<JSONObject>
	 * @throws IOException
	 * @throws ParseException
	 */
	public ArrayList<JSONObject> getRunes() throws IOException, ParseException
	{
		URL url = new URL(BASE_URL + "static-data/na/v1.2/rune?runeListData=basic,from,gold&api_key=" + API_KEY);
		InputStream is = url.openStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		String jsonText = readAll(reader);
		Map map = (Map)parser.parse(jsonText);
		Map runesMap = (Map)map.get("data");
		return new ArrayList<JSONObject>(runesMap.values());
	}
	
	/**
	 * Returns a list of JSONObjects containing information for each possible Summoner Spell
	 * 
	 * @return ArrayList<JSONObject>
	 * @throws IOException
	 * @throws ParseException
	 */
	public ArrayList<JSONObject> getSummonerSpells() throws IOException, ParseException
	{
		URL url = new URL(BASE_URL + "static-data/na/v1.2/summoner-spell?spellData=sanitizedDescription&api_key=" + API_KEY);
		InputStream is = url.openStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		String jsonText = readAll(reader);
		Map map = (Map)parser.parse(jsonText);
		Map summonerSpellMap = (Map)map.get("data");
		return new ArrayList<JSONObject>(summonerSpellMap.values());
	}
	
	/**
	 * Given a player id and a region return a JSONArray of recent matches (Max 10)
	 * 
	 * @param id The player id
	 * @param region One of the possible regions of players
	 * @return JSONArray
	 * @throws IOException
	 * @throws ParseException
	 */
	public JSONArray getMatchesbyId(String id, Region region) throws IOException, ParseException
	{
		String regionStr;
		switch(region) {
			case BR: 
				regionStr = "br";
				break;
				
			case EUNE:
				regionStr = "eune";
				break;
				
			case EUW:
				regionStr = "euw";
				break;
				
			case KR:
				regionStr = "kr";
				break;
				
			case LAN:
				regionStr = "lan";
				break;
				
			case LAS:
				regionStr = "las";
				break;
				
			case NA:
				regionStr = "na";
				break;
				
			case OCE:
				regionStr = "oce";
				break;
				
			case RU:
				regionStr = "ru";
				break;
				
			case TR:
				regionStr = "tr";
				break;
			
			default:
				regionStr = "na";
				break;
		}
		
		URL url = new URL(BASE_URL + regionStr + "/v1.3/game/by-summoner/22040428/recent?api_key=" + API_KEY);
		InputStream is = url.openStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		String jsonText = readAll(reader);
		JSONObject obj = (JSONObject) parser.parse(jsonText);
		JSONArray games = (JSONArray) obj.get("games");
		return games;
	}
	
	/**
	 * Returns a list of JSONObjects representing recent match info for the given region. 
	 * Challenger League Only.
	 * 
	 * @param numMatches The size of the list returned. Size will max out at MAX_GAMES.
	 * @param region One of the possible regions of players
	 * @return ArrayList<JSONObject>
	 * @throws ParseException
	 * @throws IOException
	 */
	public ArrayList<JSONObject> getRecentMatches(int numMatches, Region region) throws ParseException, IOException
	{
		// Make sure numMatches does not exceed MAX_GAMES
		ArrayList<JSONObject> matches = new ArrayList<JSONObject>();
		if(numMatches > MAX_GAMES)
		{
			numMatches = MAX_GAMES;
		}
		int count = 0;
		
		String regionStr;
		switch(region) {
			case BR: 
				regionStr = "br";
				break;
				
			case EUNE:
				regionStr = "eune";
				break;
				
			case EUW:
				regionStr = "euw";
				break;
				
			case KR:
				regionStr = "kr";
				break;
				
			case LAN:
				regionStr = "lan";
				break;
				
			case LAS:
				regionStr = "las";
				break;
				
			case NA:
				regionStr = "na";
				break;
				
			case OCE:
				regionStr = "oce";
				break;
				
			case RU:
				regionStr = "ru";
				break;
				
			case TR:
				regionStr = "tr";
				break;
			
			default:
				regionStr = "na";
				break;
		}
		
		// Get list of players for Challenger League
		URL url = new URL(BASE_URL + regionStr + "/v2.5/league/challenger?type=RANKED_SOLO_5x5&api_key=" + API_KEY);
		InputStream is = url.openStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		String jsonText = readAll(reader);
		JSONObject obj = (JSONObject) parser.parse(jsonText);
		JSONArray players = (JSONArray)obj.get("entries");
		
		// Iterate over the list of players
		Iterator<JSONObject> iter = players.iterator();
		while(iter.hasNext() && count < numMatches)
		{
			JSONObject player = (JSONObject) iter.next();
			try
			{
				// Get recent matches for the current player and add them to the matches list
				JSONArray playerMatches = getMatchesbyId(player.get("playerOrTeamId").toString(), region);
				Iterator<JSONObject> matchIter = playerMatches.iterator();
				try {
					while(matchIter.hasNext() && count < numMatches)
					{
						JSONObject match = matchIter.next();
						matches.add(match);
						count++;
					}
					// Sleep to prevent exceeding rate limit
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			catch(IOException e)
			{
				// Rate limit was exceeded so sleep and continue later
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		}
		return matches;
	}

    public ArrayList<JSONObject> getChallengerPlayers() throws ParseException, IOException
    {
        ArrayList<JSONObject> playersArr = new ArrayList<JSONObject>();

        URL url = new URL(BASE_URL + "na" + "/v2.5/league/challenger?type=RANKED_SOLO_5x5&api_key=" + API_KEY);
        InputStream is = url.openStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        String jsonText = readAll(reader);
        JSONObject obj = (JSONObject) parser.parse(jsonText);
        JSONArray players = (JSONArray)obj.get("entries");

        Iterator<JSONObject> iter = players.iterator();
        while(iter.hasNext())
            playersArr.add((JSONObject)iter.next());

        return playersArr;
	}

    public ArrayList<JSONObject> getPlayerRunes(String playerID) throws IOException, ParseException
    {
        ArrayList<JSONObject> masteryPages = new ArrayList<JSONObject>();

        URL url = new URL(BASE_URL + "na" + "/v1.4/summoner/" + playerID + "/masteries?api_key=" + API_KEY);
        InputStream is = url.openStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        String jsonText = readAll(reader);
        JSONObject obj = (JSONObject) parser.parse(jsonText);
        JSONObject pgs = (JSONObject)obj.get(playerID);
        JSONArray pages = (JSONArray)pgs.get("pages");

        Iterator<JSONObject> iter = pages.iterator();
        while(iter.hasNext())
            masteryPages.add((JSONObject)iter.next());

        return masteryPages;
    }

    public String getLosses(String playerID) throws IOException, ParseException
    {
        int losses = 500;

        URL url = new URL(BASE_URL + "na" + "/v1.3/stats/by-summoner/" + playerID + "/summary?season=SEASON4&api_key=" + API_KEY);
        InputStream is = url.openStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        String jsonText = readAll(reader);
        JSONObject obj = (JSONObject) parser.parse(jsonText);
        JSONArray summs = (JSONArray) obj.get("playerStatSummaries");

        Iterator<JSONObject> iter = summs.iterator();
        while(iter.hasNext()){
            JSONObject currStats = (JSONObject)iter.next();
            String type = currStats.get("playerStatSummaryType").toString();
            if(type.equals("RankedSolo5x5"))
                return currStats.get("losses").toString();
        }
        return "500";
    }

	public static void main(String[] args)
	{
		JsonHelper j = new JsonHelper();
		try {
			ArrayList<JSONObject> list = j.getRecentMatches(100, JsonHelper.Region.NA);
			System.out.println("here");
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
