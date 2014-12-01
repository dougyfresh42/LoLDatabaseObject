import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JSplitPane;
import javax.swing.JLayeredPane;
import javax.swing.JTabbedPane;
import javax.swing.JList;

import java.awt.ScrollPane;
import java.sql.ResultSet;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;


public class MainWindow extends JFrame {

	private JPanel contentPane;
	
	private SQLHelper sql;
	
	private DefaultListModel<String> championsModel = new DefaultListModel<String>();
	private DefaultListModel<String> itemsModel = new DefaultListModel<String>();
	private DefaultListModel<String> masteriesModel = new DefaultListModel<String>();
	private DefaultListModel<String> runesModel = new DefaultListModel<String>();
	private DefaultListModel<String> summonerSpellsModel = new DefaultListModel<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		try
		{
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 650, 500);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			setContentPane(contentPane);
			
			sql = new SQLHelper();
			
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			contentPane.add(tabbedPane, BorderLayout.CENTER);
			
			// Champion Tab
			JSplitPane championPane = new JSplitPane();
			championPane.setResizeWeight(.25);
			tabbedPane.addTab("Champions", null, championPane, null);
			
			ResultSet champs = sql.selectAllChampion();
			while(champs.next())
			{
				championsModel.addElement(champs.getString("ChampName"));
			}
			
			JList<String> championNameList = new JList<String>(championsModel);
			championNameList.addListSelectionListener(new ListSelectionListener(){
				public void valueChanged(ListSelectionEvent arg0) {
					String name = championNameList.getSelectedValue();
					championPane.setRightComponent(new ChampionPanel(name, sql));
				}
			});
			JScrollPane champScrollPane = new JScrollPane();
			champScrollPane.setViewportView(championNameList);
			championPane.setLeftComponent(champScrollPane);	
			
			championPane.setRightComponent(new ChampionPanel());
				
			
			// Item Tab
			JSplitPane itemPane = new JSplitPane();
			itemPane.setResizeWeight(.25);
			tabbedPane.addTab("Items", null, itemPane, null);
			
			ResultSet items = sql.selectAllItem();
			while(items.next())
			{
				itemsModel.addElement(items.getString("ItemName"));
			}
			
			JList<String> itemNameList = new JList<String>(itemsModel);
			itemNameList.addListSelectionListener(new ListSelectionListener(){
				public void valueChanged(ListSelectionEvent arg0) {
					String name = itemNameList.getSelectedValue();
					itemPane.setRightComponent(new ItemPanel(name, sql));
				}
			});
			JScrollPane itemScrollPane = new JScrollPane();
			itemScrollPane.setViewportView(itemNameList);
			itemPane.setLeftComponent(itemScrollPane);	
			
			itemPane.setRightComponent(new ItemPanel());
			
			// Masteries Tab
			JSplitPane masteriesPane = new JSplitPane();
			masteriesPane.setResizeWeight(.25);
			tabbedPane.addTab("Masteries", null, masteriesPane, null);
			
			ResultSet masteries = sql.selectAllMastery();
			while(masteries.next())
			{
				masteriesModel.addElement(masteries.getString("MasteryName"));
			}
			
			JList<String> masteryNameList = new JList<String>(masteriesModel);
			masteryNameList.addListSelectionListener(new ListSelectionListener(){
				public void valueChanged(ListSelectionEvent arg0) {
					String name = masteryNameList.getSelectedValue();
					masteriesPane.setRightComponent(new MasteryPanel(name, sql));
				}
			});
			JScrollPane masteryScrollPane = new JScrollPane();
			masteryScrollPane.setViewportView(masteryNameList);
			masteriesPane.setLeftComponent(masteryScrollPane);	
			
			masteriesPane.setRightComponent(new MasteryPanel());
			
			// Runes Tab
			JSplitPane runePane = new JSplitPane();
			runePane.setResizeWeight(.25);
			tabbedPane.addTab("Rune", null, runePane, null);
			
			ResultSet runes = sql.selectAllRune();
			while(runes.next())
			{
				runesModel.addElement(runes.getString("RuneName"));
			}
			
			JList<String> runeNameList = new JList<String>(runesModel);
			runeNameList.addListSelectionListener(new ListSelectionListener(){
				public void valueChanged(ListSelectionEvent arg0) {
					String name = runeNameList.getSelectedValue();
					runePane.setRightComponent(new RunePanel(name, sql));
				}
			});
			JScrollPane runeScrollPane = new JScrollPane();
			runeScrollPane.setViewportView(runeNameList);
			runePane.setLeftComponent(runeScrollPane);	
			
			runePane.setRightComponent(new RunePanel());
			
			// Summoner Spells Tab
			JSplitPane summonerSpellsPane = new JSplitPane();
			summonerSpellsPane.setResizeWeight(.25);
			tabbedPane.addTab("Summoner Spells", null, summonerSpellsPane, null);
			
			ResultSet summonerSpells = sql.selectAllSummonerSpell();
			while(summonerSpells.next())
			{
				summonerSpellsModel.addElement(summonerSpells.getString("SummonerSpellName"));
			}
			
			JList<String> summonerSpellNameList = new JList<String>(summonerSpellsModel);
			summonerSpellNameList.addListSelectionListener(new ListSelectionListener(){
				public void valueChanged(ListSelectionEvent arg0) {
					String name = summonerSpellNameList.getSelectedValue();
					summonerSpellsPane.setRightComponent(new SummonerSpellPanel(name, sql));
				}
			});
			JScrollPane ssScrollPane = new JScrollPane();
			ssScrollPane.setViewportView(summonerSpellNameList);
			summonerSpellsPane.setLeftComponent(ssScrollPane);	
			
			summonerSpellsPane.setRightComponent(new SummonerSpellPanel());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
