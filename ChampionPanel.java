import java.awt.BorderLayout;
import java.sql.ResultSet;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;


public class ChampionPanel extends JPanel {
	private JTable table;
	private DefaultTableModel tableModel = new DefaultTableModel();
	private SQLHelper sql;

	/**
	 * Create the panel.
	 */
	public ChampionPanel() {
		
		this.setLayout(new BorderLayout());

		tableModel.setColumnCount(2);
		
		tableModel.addRow(new String[]{"Name:"});
		tableModel.addRow(new String[]{"HP:"});
		tableModel.addRow(new String[]{"MP:"});
		tableModel.addRow(new String[]{"HP Regen:"});
		tableModel.addRow(new String[]{"MP Regen:"});
		tableModel.addRow(new String[]{"Attack Damage:"});
		tableModel.addRow(new String[]{"Attack Speed:"});
		tableModel.addRow(new String[]{"Range:"});
		tableModel.addRow(new String[]{"Defense:"});
		tableModel.addRow(new String[]{"Magic Defense:"});
		tableModel.addRow(new String[]{"Movement Speed:"});
		
		table = new JTable(tableModel)
		{
		      public boolean isCellEditable(int row, int column){  
		          return false;  
		      }
		};
		table.setShowGrid(false);
		table.setShowHorizontalLines(false);
		table.setShowVerticalLines(false);
		table.setCellSelectionEnabled(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		
				
		this.add(table, BorderLayout.NORTH);
		
		// Description
		JTextArea skillsArea = new JTextArea();
		skillsArea.setSize(200, 300);
		skillsArea.setLineWrap(true);
		skillsArea.setWrapStyleWord(true);
		skillsArea.setEditable(false);
		
		StringBuilder ss = new StringBuilder();
		ss.append("Q:\n");
		ss.append("W:\n");
		ss.append("E:\n");
		ss.append("R:\n");
		skillsArea.setText(ss.toString());
		
		this.add(skillsArea, BorderLayout.CENTER);
	}
	
	public ChampionPanel(String name, SQLHelper sql)
	{
		this.sql = sql;
		try {
			ResultSet championInfo = sql.selectChampionByName(name);

			championInfo.next();
			
			this.setLayout(new BorderLayout());
			
			tableModel.setColumnCount(2);
			
			tableModel.addRow(new String[]{"Name:", championInfo.getString("ChampName")});
			tableModel.addRow(new String[]{"HP:", championInfo.getString("HP")});
			tableModel.addRow(new String[]{"MP:", championInfo.getString("Mana")});
			tableModel.addRow(new String[]{"HP Regen:", championInfo.getString("HPRegenBy5")});
			tableModel.addRow(new String[]{"MP Regen:", championInfo.getString("ManaRegenBy5")});
			tableModel.addRow(new String[]{"Attack Damage:", championInfo.getString("Damage")});
			tableModel.addRow(new String[]{"Attack Speed:", championInfo.getString("AttackSpeed")});
			tableModel.addRow(new String[]{"Range:", championInfo.getString("Range")});
			tableModel.addRow(new String[]{"Armor:", championInfo.getString("Armor")});
			tableModel.addRow(new String[]{"Magic Resist:", championInfo.getString("MagicResist")});
			tableModel.addRow(new String[]{"Movement Speed:", championInfo.getString("MoveSpeed")});
			
			table = new JTable(tableModel)
			{
			      public boolean isCellEditable(int row, int column){  
			          return false;  
			      }
			};
			table.setShowGrid(false);
			table.setShowHorizontalLines(false);
			table.setShowVerticalLines(false);
			table.setCellSelectionEnabled(false);
			table.getColumnModel().getColumn(0).setPreferredWidth(100);
			table.getColumnModel().getColumn(1).setPreferredWidth(200);
			
					
			this.add(table, BorderLayout.NORTH);
			
			// Description
			JTextArea skillsArea = new JTextArea();
			skillsArea.setSize(200, 300);
			skillsArea.setLineWrap(true);
			skillsArea.setWrapStyleWord(true);
			skillsArea.setEditable(false);
			
			StringBuilder ss = new StringBuilder();
			ss.append("Q:\n");
			ss.append(championInfo.getString("QDesc"));
			ss.append("\n\n");
			ss.append("W:\n");
			ss.append(championInfo.getString("WDesc"));
			ss.append("\n\n");
			ss.append("E:\n");
			ss.append(championInfo.getString("EDesc"));
			ss.append("\n\n");
			ss.append("R:\n");
			ss.append(championInfo.getString("RDesc"));
			ss.append("\n\n");
			skillsArea.setText(ss.toString());
			
			this.add(skillsArea, BorderLayout.CENTER);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
