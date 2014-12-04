import java.awt.BorderLayout;
import java.sql.ResultSet;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;


public class SummonerSpellPanel extends JPanel {

	private JTable table;
	private DefaultTableModel tableModel = new DefaultTableModel();
	private SQLHelper sql;
	
	/**
	 * Create the panel.
	 */
	public SummonerSpellPanel() 
	{
		this.setLayout(new BorderLayout());
		
		tableModel.setColumnCount(2);
		
		tableModel.addRow(new String[]{"Name:"});
		
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
		
		table.setRowHeight(1, 40);
		
		this.add(table, BorderLayout.NORTH);
		
		// Description
		JTextArea descriptionArea = new JTextArea();
		descriptionArea.setSize(200, 300);
		descriptionArea.setLineWrap(true);
		descriptionArea.setWrapStyleWord(true);
		descriptionArea.setEditable(false);
		
		StringBuilder ss = new StringBuilder();
		ss.append("Description:\n");
		
		descriptionArea.setText(ss.toString());
		
		this.add(descriptionArea, BorderLayout.CENTER);
	}

	public SummonerSpellPanel(String name, SQLHelper sql) 
	{
		this.sql = sql;
		
		try {
			ResultSet ssInfo = sql.selectSummonerSpellByName(name);
			ssInfo.next();
			
			this.setLayout(new BorderLayout());
			
			tableModel.setColumnCount(2);
			
			tableModel.addRow(new String[]{"Name:", ssInfo.getString("SummonerSpellName")});
			
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
			
			table.setRowHeight(1, 40);
			
			this.add(table, BorderLayout.NORTH);
			
			// Description
			JTextArea descriptionArea = new JTextArea();
			descriptionArea.setSize(200, 300);
			descriptionArea.setLineWrap(true);
			descriptionArea.setWrapStyleWord(true);
			descriptionArea.setEditable(false);
			
			StringBuilder ss = new StringBuilder();
			ss.append("Description:\n");
			ss.append(ssInfo.getString("Description"));
			
			descriptionArea.setText(ss.toString());
			
			this.add(descriptionArea, BorderLayout.CENTER);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
