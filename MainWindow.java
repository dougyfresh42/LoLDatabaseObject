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

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JLabel;


public class MainWindow extends JFrame {

	private JPanel contentPane;
	
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		// Champion Tab
		JSplitPane championPane = new JSplitPane();
		championPane.setResizeWeight(.25);
		tabbedPane.addTab("Champions", null, championPane, null);
		
		championsModel.addElement("One");
		championsModel.addElement("Two");
		championsModel.addElement("Three");
		
		JList<String> championNameList = new JList<String>(championsModel);
		championPane.setLeftComponent(championNameList);		
		
		championPane.setRightComponent(new ChampionPanel());		
		
		// Item Tab
		JSplitPane itemPane = new JSplitPane();
		itemPane.setResizeWeight(.25);
		tabbedPane.addTab("Items", null, itemPane, null);
		
		itemsModel.addElement("One");
		itemsModel.addElement("Two");
		itemsModel.addElement("Three");
		
		JList<String> itemNameList = new JList<String>(itemsModel);
		itemPane.setLeftComponent(itemNameList);
		
		itemPane.setRightComponent(new ItemPanel());
		
		// Masteries Tab
		JSplitPane masteriesPane = new JSplitPane();
		masteriesPane.setResizeWeight(.25);
		tabbedPane.addTab("Masteries", null, masteriesPane, null);
		
		masteriesModel.addElement("One");
		masteriesModel.addElement("Two");
		masteriesModel.addElement("Three");
		
		JList<String> masteryNameList = new JList<String>(masteriesModel);
		masteriesPane.setLeftComponent(masteryNameList);
		
		masteriesPane.setRightComponent(new MasteryPanel());
		
		// Runes Tab
		JSplitPane runePane = new JSplitPane();
		runePane.setResizeWeight(.25);
		tabbedPane.addTab("Rune", null, runePane, null);
		
		runesModel.addElement("One");
		runesModel.addElement("Two");
		runesModel.addElement("Three");
		
		JList<String> runeNameList = new JList<String>(runesModel);
		runePane.setLeftComponent(runeNameList);
		
		runePane.setRightComponent(new RunePanel());
		
		// Summoner Spells Tab
		JSplitPane summonerSpellsPane = new JSplitPane();
		summonerSpellsPane.setResizeWeight(.25);
		tabbedPane.addTab("Summoner Spells", null, summonerSpellsPane, null);
		
		summonerSpellsModel.addElement("One");
		summonerSpellsModel.addElement("Two");
		summonerSpellsModel.addElement("Three");
		
		JList<String> summonerSpellsNameList = new JList<String>(summonerSpellsModel);
		summonerSpellsPane.setLeftComponent(summonerSpellsNameList);
		
		summonerSpellsPane.setRightComponent(new SummonerSpellPanel());
	}

}
