import java.awt.BorderLayout;
import java.sql.ResultSet;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;


public class RunePanel extends JPanel {

	private JTable table;
	private DefaultTableModel tableModel = new DefaultTableModel();
	private SQLHelper sql;
	
	/**
	 * Create the panel.
	 */
	public RunePanel() 
	{
		this.setLayout(new BorderLayout());
		
		tableModel.setColumnCount(2);
		
		tableModel.addRow(new String[]{"Name:"});
		tableModel.addRow(new String[]{"Tier:"});
		
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
		
		table.setRowHeight(3, 40);
		
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
	
	public RunePanel(String name, SQLHelper sql) 
	{
		this.sql = sql;
		
		try {
			ResultSet runeInfo = sql.selectRuneByName(name);
			runeInfo.next();
		
			this.setLayout(new BorderLayout());
			
			tableModel.setColumnCount(2);
			
			tableModel.addRow(new String[]{"Name:", runeInfo.getString("RuneName")});
			tableModel.addRow(new String[]{"Tier:", runeInfo.getString("Tier")});
			
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
			
			table.setRowHeight(3, 40);
			
			this.add(table, BorderLayout.NORTH);
			
			// Description
			JTextArea descriptionArea = new JTextArea();
			descriptionArea.setSize(200, 300);
			descriptionArea.setLineWrap(true);
			descriptionArea.setWrapStyleWord(true);
			descriptionArea.setEditable(false);
			
			StringBuilder ss = new StringBuilder();
			ss.append("Description:\n");
			ss.append(runeInfo.getString("Description"));
			
			descriptionArea.setText(ss.toString());
			
			this.add(descriptionArea, BorderLayout.CENTER);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
