import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class SQLHelper {
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public SQLHelper() throws Exception {
        try {
            Class.forName("org.postgresql.Driver");

            connect = DriverManager.getConnection("jdbc:postgresql://reddwarf.cs.rit.edu/p32002h",
                    "p32002h", "pass");

            statement = connect.createStatement();

        } catch (Exception e) {
            throw e;
        }
    }

    public boolean arbitraryQuery(String query) throws Exception{
        return statement.execute(query);
    }

    public boolean insertMastery(String values) throws Exception{
        //System.out.println("INSERT INTO \"Mastery\"(\"MasteryName\", \"Description\", \"Type\") VALUES("+values+");");
        return statement.execute("INSERT INTO \"Mastery\"(\"MasteryName\", \"Description\", \"Type\", \"ID\") VALUES("+values+");");
    }

    public boolean insertChampion(String values) throws Exception{
	return statement.execute("INSERT INTO \"Champion\"(\"ChampName\", \"PassiveName\", \"QName\", \"WName\", \"EName\", \"RName\", \"PassiveDesc\", \"QDesc\", \"WDesc\", \"EDesc\", \"RDesc\", \"HP\", \"HPRegenBy5\",\"Mana\", \"ManaRegenBy5\", \"MoveSpeed\", \"Damage\", \"AttackSpeed\",\"Range\", \"Armor\", \"MagicResist\") VALUES("+values+");");
    }

    public boolean insertGame(String values) throws Exception{
	return statement.execute("INSERT INTO \"Game\"(\"GameID\", \"Date\", \"RegName\", \"ChampBan1\", \"ChampBan2\", \"ChampBan3\",\"ChampBan4\", \"ChampBan5\", \"ChampBan6\", \"Champion1\", \"Champion2\",\"Champion3\", \"Champion4\", \"Champion5\", \"Champion6\", \"Champion7\",\"Champion8\", \"Champion9\", \"Champion10\") VALUES("+values+");");
    }

    public boolean insertItem(String values) throws Exception{
	return statement.execute("INSERT INTO \"Item\"(\"ItemName\", \"Description\", \"Price\", \"Component1\", \"Component2\",\"Component3\", \"Component4\", \"ID\") VALUES("+values+");");
    }

    public boolean insertItemSet(String values) throws Exception{
	return statement.execute("INSERT INTO \"ItemSet\"(\"GameID\", \"Champion\", \"Item1\", \"Item2\", \"Item3\", \"Item4\", \"Item5\",\"Item6\", \"Trinket\") VALUES("+values+");");
    }

    public boolean insertMasterySet(String values) throws Exception{
    return statement.execute("INSERT INTO \"MasterySet\" VALUES("+values+");");
    }

    public boolean insertRegion(String values) throws Exception{
	return statement.execute("INSERT INTO \"Region\"(\"RegionName\", \"CreateDate\", \"Population\") VALUES("+values+");");
    }

    public boolean insertRune(String values) throws  Exception{
	return statement.execute("INSERT INTO \"Rune\"(\"RuneName\", \"Description\", \"Price\", \"Tier\", \"ID\") VALUES("+values+");");
    }

    public boolean insertSummonerSpell(String values) throws Exception{
	return statement.execute("INSERT INTO \"SummonerSpell\"(\"SummonerSpellName\", \"Description\") VALUES("+values+");");
    }

    public boolean insertSummonerSpellSet(String values) throws Exception{
	return statement.execute("INSERT INTO \"SummonerSpellSet\"(\"GameID\", \"Champion\", \"SummonerSpell1\", \"SummonerSpell2\") VALUES("+values+");");
    }
    
    public boolean insertPlayer(String values) throws Exception{
	return statement.execute("INSERT INTO \"Player\"(\"Name\", \"Lp\", \"Wins\", \"Losses\") VALUES("+values+");");
    }

    public boolean insertRuneSet(String values) throws Exception {
    return statement.execute("INSERT INTO \"RuneSet\"(\"Rune1\", \"Rune2\", \"Rune3\", \"Rune4\", \"Rune5\", \"Rune6\", \"Rune7\", \"Rune8\", \"Rune9\", \"Rune10\", \"Rune11\", \"Rune12\", \"Rune13\", \"Rune14\", \"Rune15\", \"Rune16\", \"Rune17\", \"Rune18\", \"Rune19\", \"Rune20\", \"Rune21\", \"Rune22\", \"Rune23\", \"Rune24\", \"Rune25\", \"Rune26\", \"Rune27\", \"Rune28\", \"Rune29\", \"Rune30\", \"PlayerName\", \"RuneSetName\") VALUES("+values+");");
    }

    //THE SELECT STATEMENTS
   
    public ResultSet selectAllChampion() throws Exception{
	return statement.executeQuery("SELECT * FROM \"Champion\" ORDER BY \"ChampName\"");
    }

    public ResultSet selectAllRegion() throws Exception{
	return statement.executeQuery("SELECT * FROM \"Region\" ORDER BY \"RegionName\"");
    }

    public ResultSet selectAllGame() throws Exception{
	return statement.executeQuery("SELECT * FROM \"Game\"");
    }

    public ResultSet selectAllParticipates() throws Exception{
	return statement.executeQuery("SELECT * FROM \"Participates\"");
    }

    public ResultSet selectAllItem() throws Exception{
	return statement.executeQuery("SELECT DISTINCT ON (\"ItemName\") * FROM \"Item\" ORDER BY \"ItemName\"");
    }
 
    public ResultSet selectAllRune() throws Exception{
	return statement.executeQuery("SELECT * FROM \"Rune\" ORDER BY \"RuneName\"");
    }

    public ResultSet selectAllMastery() throws Exception{
	return statement.executeQuery("SELECT * FROM \"Mastery\" ORDER BY \"MasteryName\"");
    }
 
    public ResultSet selectAllSummonerSpell() throws Exception{
	return statement.executeQuery("SELECT * FROM \"SummonerSpell\" ORDER BY \"SummonerSpellName\"");
    }

    public ResultSet selectAllItemSet() throws Exception{
	return statement.executeQuery("SELECT * FROM \"ItemSet\"");
    }

    public ResultSet selectAllRuneSet() throws Exception{
	return statement.executeQuery("SELECT * FROM \"RuneSet\"");
    }

    public ResultSet selectAllMasterySet() throws Exception{
	return statement.executeQuery("SELECT * FROM \"MasterySet\"");
    }

    public ResultSet selectAllSummonerSpellSet() throws Exception{
	return statement.executeQuery("SELECT * FROM \"SummonerSpellSet\"");
    }

    public ResultSet selectChampionByName(String name) throws Exception{
    return statement.executeQuery("SELECT * FROM \"Champion\" WHERE \"ChampName\" = '" + name + "'");
    }
    
    public ResultSet selectItemByName(String name) throws Exception{
    return statement.executeQuery("SELECT * FROM \"Item\" WHERE \"ItemName\" = '" + name + "'");
    }
    
    public ResultSet selectMasteryByName(String name) throws Exception{
    return statement.executeQuery("SELECT * FROM \"Mastery\" WHERE \"MasteryName\" = '" + name + "'");	
    }
    
    public ResultSet selectRuneByName(String name) throws Exception{
    return statement.executeQuery("SELECT * FROM \"Rune\" WHERE \"RuneName\" = '" + name + "'");
    }
    
    public ResultSet selectSummonerSpellByName(String name) throws Exception{
    return statement.executeQuery("SELECT * FROM \"SummonerSpell\" WHERE \"SummonerSpellName\" = '" + name + "'");
    }
    
    public ResultSet selectRuneSetByPlayerName(String name) throws Exception{
    return statement.executeQuery("SELECT * FROM \"RuneSet\" WHERE \"PlayerName\" = '" + name +"'");	
    }

    public ResultSet selectMasterySetByPlayerName(String name) throws Exception{
    return statement.executeQuery("SELECT * FROM \"MasterySet\" WHERE \"PlayerName\" = '" + name + "'");
    }

public ResultSet selectAllPlayers() throws Exception {
    return statement.executeQuery("SELECT * FROM \"Player\" ORDER BY \"Lp\" DESC");
    }

    public void close() throws Exception{
        try {
            if (connect != null) connect.close();
        } catch (Exception e) {
            throw e;
        }
    }
}
