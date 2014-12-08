import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.JTextArea;


public class PlayerPanel extends JPanel{

	private SQLHelper sql;
	private HashMap<String, Integer> setMap = new HashMap<String, Integer>();
	private ResultSet runeInfo;
	
	public PlayerPanel(SQLHelper sql, String playerName)
	{		
		try {
			this.setLayout(new BorderLayout());
			
			this.sql = sql;
			
			runeInfo = sql.selectRuneSetByPlayerName(playerName);
			
			StringBuilder ss = new StringBuilder();
			
			while(runeInfo.next())
			{
				StringBuilder statSS = new StringBuilder();
				
				buildMap();
				
				ss.append(runeInfo.getString("RuneSetName"));
				ss.append(":\n");
				ss.append("\tRunes:\n");
				for(String key : setMap.keySet())
				{
					SQLHelper tempSql = new SQLHelper();
					ResultSet runeStatsInfo = tempSql.selectRuneByName(key);
					runeStatsInfo.next();
					
					ss.append("\t");
					ss.append(key);
					ss.append(" (");
					ss.append(runeStatsInfo.getString("Description"));
					ss.append(")");
					ss.append(" x");
					ss.append(setMap.get(key));
					ss.append("\n");
					
					tempSql.close();
				}
				ss.append("\n");
			}
			
			JTextArea runesArea = new JTextArea();
			runesArea.setLineWrap(true);
			runesArea.setWrapStyleWord(true);
			runesArea.setEditable(false);
			runesArea.setText(ss.toString());
			
			this.add(runesArea, BorderLayout.CENTER);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void buildMap() throws Exception
	{
		setMap.clear();
		
		String column;
		for(int i = 1; i <= 30; i++)
		{
			column = "Rune" + i;
			String name = runeInfo.getString(column);
			if(setMap.containsKey(name))
			{
				setMap.put(name, setMap.get(name) + 1);
			}
			else
			{
				setMap.put(name, 1);
			}
		}
	}
	
}
