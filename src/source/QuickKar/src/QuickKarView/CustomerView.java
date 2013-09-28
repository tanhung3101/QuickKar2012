/**
 * Course: Software Development: Process & Tools
 * Lecturer: Quang Tran
 * Assignment: 2-2012B
 * Assignment Title: QuickKar2012
 *
 *      RMIT International University Vietnam
 * Bachelor of Information Technology - Application
 *
 */
package QuickKarView;

import QuickKarController.Main;
import QuickKarController.SavingAndReadingController;
import QuickKarController.SongInfoInterface;
import QuickKarController.ThreadController;
import QuickKarInput.DialogDisplayContentInput;
import QuickKarInput.PaginationPanel;
import QuickKarInput.RatePanelWithMouseAction;
import QuickKarInput.SwitchGUI;
import QuickKarInput.TopRatePanel;
import QuickKarModel.facade.QuickKarMethod;
import QuickKarModel.facade.QuickKarModel;
import QuickKarModel.facade.QuickKarTableModel;
import QuickKarModel.facade.SongInfo;
import QuickKarModel.facade.TableCellRender;
import QuickKarInput.FavouriteSong;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.concurrent.ExecutorService;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 * This is the GUI for Customer.
 * It is responsible for drawing and handling GUI events
 *
 * @author vuongdothanhhuy
 */
public class CustomerView extends PaintedPanel implements SongInfoInterface {

    private JPanel menuPanel, toolPanel;
    private JMenuBar menuBar;
    private JToolBar toolBar;
    private JTable table;
    private DefaultTableModel tableModel;
    private JMenu fileMenu, viewMenu, helpMenu;
    private JMenuItem exitAction, switchThemeAction, userGuideAction, aboutAction;
    private JLabel resultLabel;
    private JTextField searchBar;
    private QuickKarModel model = QuickKarModel.getModel();
    private Map<String, SongInfo> songMap;
    private List<String> songList;
    private ExecutorService threadPool;
    private SwitchGUI switchAction = new SwitchGUI(1);
    private int themeNo = switchAction.getThemeNo();
    private JPanel GUI2Panel;
    private JLabel songCodeTF = new JLabel();
    private JLabel songNameTF = new JLabel();
    private JLabel songLyricTF = new JLabel();
    private JLabel songComposerTF = new JLabel();
    private RatePanelWithMouseAction ratePanelGUI2;
    private Timer timer;
    private TableCellRender myTableCellRender;
    private PaginationPanel paginationPanel;
    private JCheckBox checkBoxAdvanceSearch;
    private JRadioButton anyOfTheKeyWord;
    private JRadioButton noneOfTheKeyWord;
    private ButtonGroup radioGroup;
    private JPanel panelRadioBtn;
    private TopRatePanel topRatePanel;
    private String username;
    private int selectedRow;
    private SortChooserPanel sortChoice;
    private ResourceBundle resources = model.getResource();
    private JButton switchLanguageButton;

    /**
     * Initialize some common, share components
     *
     * @param username
     */
    public CustomerView(String username) {

        super(BACKGROUND);
        this.username = username;
        setLayout(new BorderLayout());
        
        songMap = model.getSongMap();
        songList = new ArrayList<String>(songMap.keySet());
        Collections.sort(songList);
        threadPool = ThreadController.getThreadPool();
        timer = new Timer(300, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                searchMethod();
                timer.stop();
            }
        });

        myTableCellRender = new TableCellRender();
        topRatePanel = new TopRatePanel();

        myTableCellRender.setUsername(username);
        myTableCellRender.setTopRatePanel(topRatePanel);
        ratePanelGUI2 = new RatePanelWithMouseAction(username, "");
        if (themeNo == 1) {
            add(switchGUI1());
        } else {
            add(switchGUI2());
        }
    }

    /**
     * Draw GUI 1
     *
     * @return
     */
    private JPanel switchGUI1() {

        PaintedPanel mainPanel = new PaintedPanel(new BorderLayout(), BACKGROUND);
        paginationPanel = new PaginationPanel(songMap, songList, username);

        //GUI
        PaintedPanel topPanel = new PaintedPanel(new BorderLayout(), MENU_PANEL);

        menuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        menuBar = new JMenuBar();

        //add item in file menu
        fileMenu = new JMenu(resources.getString("fileMenu"));
        menuBar.add(fileMenu);
        exitAction = new JMenuItem(resources.getString("exitAction"));
        fileMenu.add(exitAction);
        exitAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_MASK));

        //add item in menu
        viewMenu = new JMenu(resources.getString("viewMenu"));
        switchThemeAction = new JMenuItem(resources.getString("switchThemeAction"));
        viewMenu.add(switchThemeAction);
        switchThemeAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.SHIFT_MASK));

        //add item in help menu
        helpMenu = new JMenu(resources.getString("helpMenu"));
        userGuideAction = new JMenuItem(resources.getString("userGuideAction"));
        aboutAction = new JMenuItem(resources.getString("aboutAction"));
        helpMenu.add(userGuideAction);
        helpMenu.add(aboutAction);
        userGuideAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        aboutAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.SHIFT_MASK));

        userGuideAction.addActionListener(new OpenHelpFile());
        switchThemeAction.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                switchAction.switchThemeNo();
                Main.changePanel(switchGUI2());
            }
        });

        //add menu
        menuBar.add(fileMenu);
        menuBar.add(viewMenu);
        menuBar.add(helpMenu);
        menuBar.setOpaque(false);
        //add menu bar to menu panel
        menuPanel.add(menuBar);
        menuPanel.setOpaque(false);

        //tool panel
        searchBar = new JTextField(50);
        searchBar.setOpaque(false);
        JButton searchButton = new JButton(SEARCH_BUTTON);
        JButton exitButton = new JButton(LOGOUT_BUTTON);
        JButton switchGUIButton = new JButton(SWITCH_BUTTON);
        JButton listAllSongButton = new JButton(resources.getString("listAllSongButton"));

        switchLanguageButton=new JButton(resources.getString("language"));

        switchLanguageButton.setToolTipText(resources.getString("switchLanguage"));

        switchLanguageButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
               resources=model.switchReSource();
               Main.changePanel(new CustomerView(username));

            }
        });


        listAllSongButton.setOpaque(false);
        searchButton.setOpaque(false);
        exitButton.setOpaque(false);
        switchGUIButton.setOpaque(false);

        switchGUIButton.setToolTipText(resources.getString("switchThemeAction"));
        JPanel allToolPanel = new JPanel(new BorderLayout());
        JPanel switchGUIPanel = new JPanel(new FlowLayout());
        allToolPanel.setOpaque(false);
        switchGUIPanel.add(switchGUIButton);
        switchGUIPanel.setOpaque(false);

        switchGUIButton.setPreferredSize(new Dimension(40, 40));
        searchButton.setPreferredSize(new Dimension(30, 30));
        exitButton.setPreferredSize(new Dimension(30, 30));

        //
        JPanel panelChkbox = new JPanel();
        panelChkbox.setOpaque(false);
        checkBoxAdvanceSearch = new JCheckBox(resources.getString("checkBoxAdvanceSearch"));
        checkBoxAdvanceSearch.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (checkBoxAdvanceSearch.isSelected()) {
                    panelRadioBtn.setVisible(true);
                    anyOfTheKeyWord.setSelected(true);
                } else {
                    panelRadioBtn.setVisible(false);
                }
            }
        });
        panelChkbox.add(checkBoxAdvanceSearch);

        panelRadioBtn = new JPanel();
        panelRadioBtn.setOpaque(false);
        anyOfTheKeyWord = new JRadioButton(resources.getString("anyOfTheKeyWord"));
        noneOfTheKeyWord = new JRadioButton(resources.getString("noneOfTheKeyWord"));
        panelRadioBtn.add(anyOfTheKeyWord);
        panelRadioBtn.add(noneOfTheKeyWord);
        radioGroup = new ButtonGroup();
        radioGroup.add(anyOfTheKeyWord);
        radioGroup.add(noneOfTheKeyWord);
        panelRadioBtn.setVisible(false);

        JPanel advSearchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        advSearchPanel.setOpaque(false);
        advSearchPanel.add(panelChkbox);
        advSearchPanel.add(panelRadioBtn);

        sortChoice = new SortChooserPanel(paginationPanel, songList);

        JPanel option = new JPanel(new BorderLayout());
        option.setOpaque(false);
        option.add(advSearchPanel, BorderLayout.NORTH);
        option.add(sortChoice, BorderLayout.SOUTH);

        JButton favoriteButton = new JButton(resources.getString("favoriteButton"));

        toolPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        toolBar = new JToolBar(JToolBar.HORIZONTAL);
        toolBar.setFloatable(true);
        toolBar.setOpaque(false);
        toolPanel.add(searchBar);
        toolPanel.add(searchButton);
        toolPanel.add(listAllSongButton);
        toolPanel.add(favoriteButton);
        toolPanel.add(switchLanguageButton);
        toolPanel.add(exitButton);        
        toolPanel.setOpaque(false);
        allToolPanel.add(toolPanel, BorderLayout.WEST);
        allToolPanel.add(switchGUIPanel, BorderLayout.EAST);
        allToolPanel.add(option, BorderLayout.SOUTH);
        allToolPanel.setOpaque(false);
        menuPanel.add(menuBar);
        resultLabel = new JLabel(resources.getString("resultLabel"));

        topPanel.add(menuPanel, BorderLayout.NORTH);
        topPanel.add(allToolPanel, BorderLayout.CENTER);
        topPanel.add(resultLabel, BorderLayout.SOUTH);
        topPanel.setOpaque(false);
        switchGUIButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                switchAction.switchThemeNo();
                Main.changePanel(switchGUI2());
            }
        });

        searchButton.setToolTipText(resources.getString("searchFunction"));
        searchButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //search handling
                searchMethod();
            }
        });

        listAllSongButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    paginationPanel.listAllRecord();
                    table.repaint();
                } catch (Exception g) {
                    g.printStackTrace();
                }
            }
        });

        favoriteButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                FavouriteSong favoriteSongDialog = new FavouriteSong(username);
            }
        });
        searchBar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //search handling
                timer.stop();
                searchMethod();
            }
        });

        searchBar.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
                if (key != KeyEvent.VK_UP && key != KeyEvent.VK_DOWN
                        && key != KeyEvent.VK_LEFT && key != KeyEvent.VK_RIGHT) {
                    timer.start();
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                timer.stop();
            }
        });

        exitAction.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (getDialogConfrimation(resources.getString("quitConfirm")) == JOptionPane.YES_OPTION) {
                    SavingAndReadingController.saveRateMap();
                    SavingAndReadingController.saveFavoriteMap();
                    Main.changePanel(new ChooseRoleView());
                }
            }
        });
        exitButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (getDialogConfrimation(resources.getString("quitConfirm")) == JOptionPane.YES_OPTION) {
                    SavingAndReadingController.saveRateMap();
                    SavingAndReadingController.saveFavoriteMap();
                    Main.changePanel(new ChooseRoleView());
                }
            }
        });

        aboutAction.addActionListener(new AboutActionListener());

        String[] columnNames = new String[]{resources.getString("CodeJt"), resources.getString("SongNameJt"),
        resources.getString("LyricJt"), resources.getString("ComposerJt"), resources.getString("RateJt")};
        tableModel = new QuickKarTableModel(columnNames, 0, false);
        table = new JTable(tableModel);
        table.setRowHeight(30);
        table.getColumn(resources.getString("CodeJt")).setCellRenderer(myTableCellRender);
        table.getColumn(resources.getString("SongNameJt")).setCellRenderer(myTableCellRender);
        table.getColumn(resources.getString("LyricJt")).setCellRenderer(myTableCellRender);
        table.getColumn(resources.getString("ComposerJt")).setCellRenderer(myTableCellRender);
        table.getColumn(resources.getString("RateJt")).setCellRenderer(myTableCellRender);
        table.getColumn(resources.getString("RateJt")).setCellEditor(new DefaultCellEditor(new JComboBox(new Object[]{0, 1, 2, 3, 4, 5})));
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                //right click menu
                selectedRow = table.rowAtPoint(e.getPoint());
                if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
                    ContextMenu contextMenu = new ContextMenu();
                    contextMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        paginationPanel.setTableModel(tableModel);
        threadPool.execute(new Runnable() {

            public void run() {
                paginationPanel.flushPage(1);
                table.repaint();
            }
        });

        JScrollPane listScroll = new JScrollPane(table);

        JPanel contentPanel = new JPanel(new BorderLayout());
        topRatePanel.setOpaque(false);
        contentPanel.setOpaque(false);
        contentPanel.add(listScroll, BorderLayout.CENTER);
        contentPanel.add(topRatePanel, BorderLayout.EAST);
        topRatePanel.setPreferredSize(new Dimension(200, 100));

        JPanel listSongView = new JPanel(new BorderLayout());

        listSongView.add(contentPanel, BorderLayout.CENTER);
        listSongView.add(paginationPanel, BorderLayout.SOUTH);
        listScroll.setOpaque(false);
        listSongView.setOpaque(false);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(listSongView, BorderLayout.CENTER);
        mainPanel.setOpaque(false);
        return mainPanel;
    }

    /**
     * Draw GUI 2
     *
     * @return
     */
    private JPanel switchGUI2() {

        JPanel mainPanel = new JPanel(new BorderLayout());
        paginationPanel = new PaginationPanel(songMap, songList, username);
        //pagination initial step
        JPanel topPanel = new JPanel(new BorderLayout());

        menuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        menuBar = new JMenuBar();

        //add item in file menu
        fileMenu = new JMenu(resources.getString("fileMenu"));
        menuBar.add(fileMenu);
        exitAction = new JMenuItem(resources.getString("exitAction"));
        fileMenu.add(exitAction);
        exitAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_MASK));

        //add item in menu
        viewMenu = new JMenu(resources.getString("viewMenu"));
        JMenuItem topRateView = new JMenuItem(resources.getString("viewTopRate"));
        switchThemeAction = new JMenuItem(resources.getString("switchThemeAction"));
        viewMenu.add(switchThemeAction);
        viewMenu.add(topRateView);
        switchThemeAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.SHIFT_MASK));
        topRateView.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.CTRL_MASK));

        topRateView.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                topRatePanel.updateTopRate();
                DialogDisplayContentInput displayDialog = new DialogDisplayContentInput(topRatePanel);
            }
        });
        //add item in help menu
        helpMenu = new JMenu(resources.getString("helpMenu"));
        userGuideAction = new JMenuItem(resources.getString("userGuideAction"));
        aboutAction = new JMenuItem(resources.getString("aboutAction"));
        helpMenu.add(userGuideAction);
        helpMenu.add(aboutAction);
        userGuideAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        aboutAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.SHIFT_MASK));


        switchLanguageButton=new JButton(resources.getString("language"));

        switchLanguageButton.setToolTipText(resources.getString("switchLanguage"));

        switchLanguageButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
               resources=model.switchReSource();
               Main.changePanel(switchGUI2());

            }
        });

        //add menu
        menuBar.add(fileMenu);
        menuBar.add(viewMenu);
        menuBar.add(helpMenu);

        //add menu bar to menu panel
        menuBar.setOpaque(false);
        menuPanel.add(menuBar);
        menuPanel.setOpaque(false);

        //action listener for menu
        aboutAction.addActionListener(new AboutActionListener());
        switchThemeAction.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                switchAction.switchThemeNo();
                Main.changePanel(switchGUI1());
            }
        });
        userGuideAction.addActionListener(new OpenHelpFile());

        //tool panel
        searchBar = new JTextField(50);
        searchBar.setOpaque(false);
        JButton searchButton = new JButton(SEARCH_BUTTON);
        JButton exitButton = new JButton(LOGOUT_BUTTON);
        JButton switchGUIButton = new JButton(SWITCH_BUTTON);
        JPanel allToolPanel = new JPanel(new BorderLayout());
        JPanel switchGUIPanel = new JPanel(new FlowLayout());
        JButton listAllSongButton = new JButton(resources.getString("listAllSongButton"));
        JButton favoriteButton = new JButton(resources.getString("favoriteButton"));


          favoriteButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                FavouriteSong favoriteSongDialog = new FavouriteSong(username);
            }
        });

        listAllSongButton.setOpaque(false);
        searchButton.setOpaque(false);
        exitButton.setOpaque(false);
        switchGUIButton.setOpaque(false);

        switchGUIPanel.add(switchGUIButton);
        switchGUIPanel.setOpaque(false);

        switchGUIButton.setPreferredSize(new Dimension(40, 40));
        searchButton.setPreferredSize(new Dimension(30, 30));
        exitButton.setPreferredSize(new Dimension(30, 30));

        JPanel panelChkbox = new JPanel();
        panelChkbox.setOpaque(false);
        checkBoxAdvanceSearch = new JCheckBox(resources.getString("checkBoxAdvanceSearch") );
        checkBoxAdvanceSearch.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (checkBoxAdvanceSearch.isSelected()) {
                    panelRadioBtn.setVisible(true);
                    anyOfTheKeyWord.setSelected(true);
                } else {
                    panelRadioBtn.setVisible(false);
                }
            }
        });
        panelChkbox.add(checkBoxAdvanceSearch);

        panelRadioBtn = new JPanel();
        panelRadioBtn.setOpaque(false);
        anyOfTheKeyWord = new JRadioButton(resources.getString("anyOfTheKeyWord"));
        noneOfTheKeyWord = new JRadioButton(resources.getString("noneOfTheKeyWord"));
        panelRadioBtn.add(anyOfTheKeyWord);
        panelRadioBtn.add(noneOfTheKeyWord);
        radioGroup = new ButtonGroup();
        radioGroup.add(anyOfTheKeyWord);
        radioGroup.add(noneOfTheKeyWord);
        panelRadioBtn.setVisible(false);

        JPanel advSearchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        advSearchPanel.setOpaque(false);
        advSearchPanel.add(panelChkbox);
        advSearchPanel.add(panelRadioBtn);

        sortChoice = new SortChooserPanel(paginationPanel, songList);

        JPanel option = new JPanel(new BorderLayout());
        option.setOpaque(false);
        option.add(advSearchPanel, BorderLayout.NORTH);
        option.add(sortChoice, BorderLayout.CENTER);

        toolPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        toolBar = new JToolBar(JToolBar.HORIZONTAL);
        toolBar.setFloatable(true);
        toolPanel.add(searchBar);
        toolPanel.add(searchButton);
        toolPanel.add(listAllSongButton);
        toolPanel.add(favoriteButton);
        toolPanel.add(switchLanguageButton);
        toolPanel.add(exitButton);
        toolBar.setOpaque(false);
        toolPanel.setOpaque(false);
        allToolPanel.add(toolPanel, BorderLayout.WEST);
        allToolPanel.add(switchGUIPanel, BorderLayout.EAST);
        allToolPanel.add(option, BorderLayout.SOUTH);
        allToolPanel.setOpaque(false);

        menuPanel.add(menuBar);
        menuPanel.setOpaque(false);
        resultLabel = new JLabel(resources.getString("resultLabel"));
        resultLabel.setOpaque(false);
        topPanel.add(menuPanel, BorderLayout.NORTH);
        topPanel.add(allToolPanel, BorderLayout.CENTER);
        topPanel.add(resultLabel, BorderLayout.SOUTH);
        topPanel.setOpaque(false);
        //switchThemeAction
        switchGUIButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                switchAction.switchThemeNo();
                Main.changePanel(switchGUI1());
            }
        });
        searchButton.setToolTipText(resources.getString("searchFunction"));
        searchButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //search handling
                searchMethod();
            }
        });

        listAllSongButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                paginationPanel.listAllRecord();
                table.repaint();
            }
        });

        searchBar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //search handling
                timer.stop();
                searchMethod();
            }
        });

        searchBar.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
                if (key != KeyEvent.VK_UP && key != KeyEvent.VK_DOWN
                        && key != KeyEvent.VK_LEFT && key != KeyEvent.VK_RIGHT) {
                    timer.start();
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
                timer.stop();
            }
        });

        exitAction.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (getDialogConfrimation(resources.getString("quitConfirm")) == JOptionPane.YES_OPTION) {
                    SavingAndReadingController.saveRateMap();
                    SavingAndReadingController.saveFavoriteMap();
                    Main.changePanel(new ChooseRoleView());
                }
            }
        });
        exitButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (getDialogConfrimation(resources.getString("quitConfirm")) == JOptionPane.YES_OPTION) {
                    SavingAndReadingController.saveRateMap();
                    SavingAndReadingController.saveFavoriteMap();
                    Main.changePanel(new ChooseRoleView());
                }
            }
        });

        String[] columnNames = new String[]{resources.getString("CodeJt"),resources.getString("SongNameJt")};
        tableModel = new QuickKarTableModel(columnNames, 0, false);
        table = new JTable(tableModel);
        table.setRowHeight(30);
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionForeground(Color.BLUE);
        table.getColumn(resources.getString("CodeJt")).setCellRenderer(myTableCellRender);
        table.getColumn(resources.getString("SongNameJt")).setCellRenderer(myTableCellRender);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setShowGrid(false);
        paginationPanel.setTableModel(tableModel);

        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                selectedRow = table.rowAtPoint(e.getPoint());
                String songCode = (String) table.getValueAt(selectedRow, SONGCODE);
                songCodeTF.setText(resources.getString("CodeJt")+": " + songCode);
                SongInfo songInfo = songMap.get(songCode);
                songNameTF.setText(resources.getString("SongNameJt")+": "  + songInfo.getName());
                songLyricTF.setText(resources.getString("LyricJt")+": " + songInfo.getLyric());
                songComposerTF.setText(resources.getString("ComposerJt")+": " + songInfo.getComposer());
                GUI2Panel.remove(ratePanelGUI2);
                ratePanelGUI2 = new RatePanelWithMouseAction(username, songCode);
                GUI2Panel.add(ratePanelGUI2);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //right click menu
                selectedRow = table.getSelectedRow();
                if (selectedRow < 0) {
                    return;
                }
                if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
                    ContextMenu contextMenu = new ContextMenu();
                    contextMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        threadPool.execute(new Runnable() {

            public void run() {
                paginationPanel.flushPage(1);
            }
        });

        GUI2Panel = new JPanel(new GridLayout(5, 1));
        GUI2Panel.add(songCodeTF);
        GUI2Panel.add(songNameTF);
        GUI2Panel.add(songLyricTF);
        GUI2Panel.add(songComposerTF);
        GUI2Panel.add(ratePanelGUI2);
        GUI2Panel.setOpaque(false);
        songCodeTF.setText(resources.getString("CodeJt")+":   ");
        songNameTF.setText(resources.getString("SongNameJt")+": ");
        songLyricTF.setText(resources.getString("LyricJt")+": ");
        songComposerTF.setText(resources.getString("ComposerJt")+": ");

        mainPanel.add(topPanel, BorderLayout.NORTH);
        JSplitPane splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(table), new JScrollPane(GUI2Panel));
        splitPanel.setOpaque(false);
        mainPanel.add(splitPanel, BorderLayout.CENTER);
        mainPanel.add(paginationPanel, BorderLayout.SOUTH);
        mainPanel.setOpaque(false);
        return mainPanel;
    }

    /**
     * Call appropriate search function to perform search.
     * Calculate search time.
     */
    private void searchMethod() {

        String text = searchBar.getText().trim().toLowerCase();
        long start = System.nanoTime();
        if (text.equals("")) {
            songList = new ArrayList<String>(songMap.keySet());
        } else {
            if (checkBoxAdvanceSearch.isSelected()) {
                songList = model.advanceSearch(text, anyOfTheKeyWord.isSelected(), noneOfTheKeyWord.isSelected());
            } else {
                songList = model.advanceSearch(text, false, false);
            }
        }
        long end = System.nanoTime();
        paginationPanel.setSongList(songList);
        sortChoice.setSongList(songList);

        if (!songList.isEmpty()) {
            myTableCellRender.setSearchText(text);
        }

        Collections.sort(songList);
        double second = (end - start) / 1000000000.0;

        resultLabel.setText(resources.getString("searchResult1") + QuickKarMethod.formatDouble(second) + " " + resources.getString("searchResult2") + songList.size());
        threadPool.execute(new Runnable() {

            public void run() {
                //processs input in a seperate thread
                sortChoice.sortBySelectedOption();
                table.repaint();
            }
        });
    }

    /**
     * This is the right-click menu on JTable.
     */
    public class ContextMenu extends JPopupMenu {

        private JMenuItem itemAddFavorite = new JMenuItem(resources.getString("itemAddFavorite"));

        public ContextMenu() {
            this.add(itemAddFavorite);
            itemAddFavorite.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    QuickKarModel.getModel().addFavoriteSong(username, (String) table.getValueAt(selectedRow, SONGCODE));
                }
            });
        }
    }

    private int getDialogConfrimation(String message) {
        int choose = JOptionPane.showConfirmDialog(null, message, "Information", JOptionPane.YES_NO_OPTION);
        return choose;
    }
}
