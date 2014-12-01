import java.awt.BorderLayout;
import java.sql.ResultSet;

import javax.swing.JPanel;
import javax.swing.JTable;
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
		tableModel.addRow(new String[]{"Q:"});
		tableModel.addRow(new String[]{"W:"});
		tableModel.addRow(new String[]{"E:"});
		tableModel.addRow(new String[]{"R:"});
		
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
		table.setRowHeight(11,50);
		table.setRowHeight(12,50);
		table.setRowHeight(13,50);
		table.setRowHeight(14,50);
		
				
		this.add(table, BorderLayout.WEST);

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
			tableModel.addRow(new String[]{"Q:", championInfo.getString("QDesc")});
			tableModel.addRow(new String[]{"W:", championInfo.getString("WDesc")});
			tableModel.addRow(new String[]{"E:", championInfo.getString("EDesc")});
			tableModel.addRow(new String[]{"R:", championInfo.getString("RDesc")});
			
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
			table.setRowHeight(11,50);
			table.setRowHeight(12,50);
			table.setRowHeight(13,50);
			table.setRowHeight(14,50);
			
					
			this.add(table, BorderLayout.WEST);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
