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

import QuickKarInput.AddSongInput;
import QuickKarController.Main;
import QuickKarController.SavingAndReadingController;
import QuickKarController.SongInfoInterface;
import QuickKarController.ThreadController;
import QuickKarInput.DialogDisplayContentInput;
import QuickKarInput.EditSongInput;
import QuickKarInput.ExportCSVSong;
import QuickKarInput.ImportCSVInput;
import QuickKarInput.PaginationPanel;
import QuickKarInput.TopRatePanel;
import QuickKarModel.facade.QuickKarMethod;
import QuickKarModel.facade.QuickKarModel;
import QuickKarModel.facade.QuickKarTableModel;
import QuickKarModel.facade.SongInfo;
import QuickKarModel.facade.TableCellRender;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * This is the GUI for Staff.
 * It is responsible for drawing and handling GUI events
 *
 * @author vuongdothanhhuy
 */
public class StaffView extends PaintedPanel implements SongInfoInterface {

    //properties here
    private static final int DOUBLE_CLICK = 2;
    private JPanel menuPanel, toolPanel, searchPanel;
    private JMenuBar menuBar;
    private JToolBar toolBar, searchBar;
    private JTable table;
    private DefaultTableModel tableModel;
    private JMenu fileMenu, viewMenu, helpMenu;
    private JMenuItem saveAction, exitAction, switchThemeAction,
            userGuideAction, aboutAction, deleteAllAction;
    private JTextField searchField;
    private JLabel resultLabel;
    private QuickKarModel model = QuickKarModel.getModel();
    private Map<String, SongInfo> songMap;
    private List<String> songList;
    private ExecutorService threadPool;
    private ContextMenu contextMenu;
    private int selectedRow;
    private Timer timer;
    private TableCellRender myTableCellRender;
    private PaginationPanel paginationPanel;
    private String username;
    private JCheckBox checkBoxAdvanceSearch;
    private JRadioButton anyOfTheKeyWord;
    private JRadioButton noneOfTheKeyWord;
    private ButtonGroup radioGroup;
    private JPanel panelRadioBtn;
    private TopRatePanel topRatePanel;
    private SortChooserPanel sortChoice;
    private ResourceBundle resources = model.getResource();
    private JButton switchLanguageButton;

    /**
     * This is GUI 1
     *
     * @param username
     */
    public StaffView(final String username) {

        super(BACKGROUND);
        this.username = username;
        //initial process
        songMap = model.getSongMap();
        songList = new ArrayList<String>(songMap.keySet());
        Collections.sort(songList);
        threadPool = ThreadController.getThreadPool();
        contextMenu = new ContextMenu();

        topRatePanel = new TopRatePanel();
        myTableCellRender = new TableCellRender();
        myTableCellRender.setUsername(username);
        myTableCellRender.setTopRatePanel(topRatePanel);

        paginationPanel = new PaginationPanel(songMap, songList, username);

        timer = new Timer(300, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                searchMethod();
                timer.stop();
            }
        });

        //create GUI here
        setLayout(new BorderLayout());
        PaintedPanel topPanel = new PaintedPanel(new BorderLayout(), MENU_PANEL);
        topPanel.setOpaque(false);

        menuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        menuBar = new JMenuBar();

        //add item in file menu
        fileMenu = new JMenu(resources.getString("fileMenu"));
        menuBar.add(fileMenu);
        saveAction = new JMenuItem(resources.getString("saveAction"));
        exitAction = new JMenuItem(resources.getString("exitAction"));
        fileMenu.add(saveAction);
        fileMenu.add(exitAction);
        saveAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
        exitAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_MASK));

        //add item in menu
        viewMenu = new JMenu(resources.getString("viewMenu"));
        switchThemeAction = new JMenuItem(resources.getString("switchThemeAction"));
        viewMenu.add(switchThemeAction);
        switchThemeAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.ALT_MASK));

        //add item in help menu
        helpMenu = new JMenu(resources.getString("helpMenu"));
        userGuideAction = new JMenuItem(resources.getString("userGuideAction"));
        aboutAction = new JMenuItem(resources.getString("aboutAction"));
        helpMenu.add(userGuideAction);
        helpMenu.add(aboutAction);
        userGuideAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        aboutAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.ALT_MASK));

        //add item in Tool
        JMenu toolMenu = new JMenu(resources.getString("toolMenu"));
        JMenuItem addSong = new JMenuItem(resources.getString("addSongAction"));
        JMenuItem editSong = new JMenuItem(resources.getString("editSongAction"));
        JMenuItem deleteSong = new JMenuItem(resources.getString("deleteSongAction"));
        deleteAllAction = new JMenuItem(resources.getString("deleteAllAction"));
        toolMenu.add(addSong);
        toolMenu.add(editSong);
        toolMenu.add(deleteSong);
        toolMenu.add(deleteAllAction);
        addSong.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
        editSong.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_MASK));
        deleteSong.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_MASK));
        deleteAllAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, KeyEvent.CTRL_MASK));

        //add menu
        menuBar.add(fileMenu);
        menuBar.add(viewMenu);
        menuBar.add(toolMenu);
        menuBar.add(helpMenu);

        //add menu bar to menu panel

        menuPanel.setOpaque(false);
        menuBar.setOpaque(false);

        menuPanel.add(menuBar);

        //action listener for menu
        aboutAction.addActionListener(new AboutActionListener());
        userGuideAction.addActionListener(new OpenHelpFile());
        switchThemeAction.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Main.changePanel(switchGUI2(StaffView.this.username));
            }
        });

        addSong.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                AddSongInput addInputPanel = new AddSongInput(songMap, tableModel);
            }
        });

        editSong.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                EditSongInput editInputPanel = new EditSongInput(songMap, paginationPanel, tableModel, selectedRow);
            }
        });

        deleteSong.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //delete Song
                deleteSong();
            }
        });

        deleteAllAction.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                model.deleteAllSong();
                paginationPanel.setSongList(new ArrayList<String>());
                paginationPanel.flushPage(1);
                table.repaint();
                SavingAndReadingController.saveSong();
            }
        });

        //create tool Panel which contain Icon's functions for Staff
        JButton addSongButton = new JButton(ADD_BUTTON);
        addSongButton.setOpaque(false);
        addSongButton.setToolTipText(resources.getString("addSongButton"));
        JButton editSongButton = new JButton(EDIT_BUTTON);
        editSongButton.setOpaque(false);
        editSongButton.setToolTipText(resources.getString("editSongButton"));
        JButton deleteSongButton = new JButton(DELETE_BUTTON);
        deleteSongButton.setOpaque(false);
        deleteSongButton.setToolTipText(resources.getString("deleteSongButton"));
        JButton switchGUIButton = new JButton(SWITCH_BUTTON);
        switchGUIButton.setToolTipText(resources.getString("switchThemeAction"));
        switchGUIButton.setOpaque(false);

        JButton exportListButton=new JButton(EXPORT_LOGO);

        exportListButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //delete Song
                ExportCSVSong exportCSV = new ExportCSVSong();
            }
        });
        switchLanguageButton=new JButton(resources.getString("language"));
        switchLanguageButton.addActionListener((new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            resources=model.switchReSource();
            Main.changePanel(new StaffView(username));
            }
        }));



        deleteSongButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //delete Song
                deleteSong();
            }
        });
        JButton importSongButton = new JButton(IMPORT_BUTTON);
        importSongButton.setOpaque(false);
        importSongButton.setToolTipText(resources.getString("importSongButton"));
        importSongButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //import csv file
                ImportCSVInput inputCSV = new ImportCSVInput();
            }
        });

        toolBar = new JToolBar(JToolBar.HORIZONTAL);

        toolBar.add(addSongButton);
        toolBar.add(importSongButton);
        toolBar.add(exportListButton);
        toolBar.add(editSongButton);
        toolBar.add(deleteSongButton);
        toolBar.add(switchGUIButton);
        toolBar.setOpaque(false);

        //acction of switchBUtton
        switchGUIButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Main.changePanel(switchGUI2(StaffView.this.username));
            }
        });

        toolPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        toolPanel.setOpaque(false);
        toolPanel.add(toolBar);

        //search panel
        searchField = new JTextField(50);
        JButton searchButton = new JButton(SEARCH_BUTTON);
        searchButton.setOpaque(false);
        //JButton listAllSongButton = new JButton(new ImageIcon("img/button/listAllButton.png"));
        JButton listAllSongButton = new JButton(resources.getString("listAllSongButton"));
        listAllSongButton.setOpaque(false);

        listAllSongButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    paginationPanel.setCurrentPage(1);
                    paginationPanel.listAllRecord();
                    table.repaint();
                } catch (Exception g) {
                    g.printStackTrace();
                }

            }
        });

        JButton exitButton = new JButton(LOGOUT_BUTTON);
        exitButton.setOpaque(false);
        listAllSongButton.setPreferredSize(new Dimension(50, 40));
        searchButton.setPreferredSize(new Dimension(40, 40));
        exitButton.setPreferredSize(new Dimension(40, 40));
        searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setOpaque(false);
        searchBar = new JToolBar(JToolBar.HORIZONTAL);
        searchBar.setFloatable(true);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(listAllSongButton);
        searchPanel.add(switchLanguageButton);
        searchPanel.add(exitButton);

        menuPanel.add(menuBar);

        resultLabel = new JLabel(resources.getString("resultLabel"));
        resultLabel.setOpaque(false);

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

        //
        sortChoice = new SortChooserPanel(paginationPanel, songList);

        JPanel option = new JPanel(new BorderLayout());
        option.setOpaque(false);
        option.add(advSearchPanel, BorderLayout.NORTH);
        option.add(sortChoice, BorderLayout.SOUTH);
        
        //create bottom panel
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.add(option, BorderLayout.NORTH);
        bottomPanel.add(searchPanel, BorderLayout.CENTER);
        bottomPanel.add(resultLabel, BorderLayout.SOUTH);

        topPanel.add(menuPanel, BorderLayout.NORTH);
        topPanel.add(toolPanel, BorderLayout.CENTER);
        topPanel.add(bottomPanel, BorderLayout.SOUTH);

        addSongButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                AddSongInput addInputPanel = new AddSongInput(songMap, tableModel);
            }
        });
        editSongButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    if (!songMap.isEmpty()) {
                        EditSongInput editInputPanel = new EditSongInput(songMap, paginationPanel, tableModel, selectedRow);
                    } else {
                    }
                } catch (Exception g) {
                }
            }
        });

        searchButton.setToolTipText(resources.getString("searchButton"));
        searchButton.setOpaque(false);
        exitButton.setOpaque(false);
        //searchButton.setContentAreaFilled(false);
        // exitButton.setContentAreaFilled(false);
        searchButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                //search function
                searchMethod();
            }
        });

        searchField.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                //search function
                try {
                    timer.stop();
                    searchMethod();
                } catch (Exception g) {
                }

            }
        });
        searchField.addKeyListener(new KeyAdapter() {

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

        saveAction.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                SavingAndReadingController.saveSong();
            }
        });
        exitAction.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (getDialogConfirmation(resources.getString("quitConfirm")) == JOptionPane.YES_OPTION) {
                    SavingAndReadingController.saveRateMap();
                    SavingAndReadingController.saveFavoriteMap();
                    Main.changePanel(new ChooseRoleView());
                }
            }
        });
        exitButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (getDialogConfirmation(resources.getString("quitConfirm")) == JOptionPane.YES_OPTION) {
                    SavingAndReadingController.saveRateMap();
                    SavingAndReadingController.saveFavoriteMap();
                    Main.changePanel(new ChooseRoleView());
                }
            }
        });

        String[] columnNames = new String[]{resources.getString("CodeJt"), resources.getString("SongNameJt"), resources.getString("LyricJt"), resources.getString("ComposerJt"), resources.getString("RateJt")};
        tableModel = new QuickKarTableModel(columnNames, 0, false);
        table = new JTable(tableModel);
        table.setRowHeight(30);
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table.getColumn(resources.getString("CodeJt")).setCellRenderer(myTableCellRender);
        table.getColumn(resources.getString("SongNameJt")).setCellRenderer(myTableCellRender);
        table.getColumn(resources.getString("LyricJt")).setCellRenderer(myTableCellRender);
        table.getColumn(resources.getString("ComposerJt")).setCellRenderer(myTableCellRender);
        table.getColumn(resources.getString("RateJt")).setCellRenderer(myTableCellRender);
        table.getColumn(resources.getString("RateJt")).setCellEditor(new DefaultCellEditor(new JComboBox(new Object[]{0, 1, 2, 3, 4, 5})));
        table.addMouseListener(new MouseTableListener());
        table.setOpaque(false);
        paginationPanel.setTableModel(tableModel);
        threadPool.execute(new Runnable() {

            public void run() {
                paginationPanel.setCurrentPage(1);
                paginationPanel.flushPage(1);
            }
        });

        JScrollPane listScroll = new JScrollPane(table);

        //RATTING PANEL

        JPanel contentPanel = new JPanel(new BorderLayout());

        topRatePanel.setOpaque(false);
        contentPanel.setOpaque(false);
        contentPanel.add(listScroll, BorderLayout.CENTER);
        contentPanel.add(topRatePanel, BorderLayout.EAST);
        topRatePanel.setPreferredSize(new Dimension(200, 400));

        JPanel listSongView = new JPanel(new BorderLayout());

        listSongView.add(contentPanel, BorderLayout.CENTER);
        //temporarily replace with paginationPanel
        listSongView.add(paginationPanel, BorderLayout.SOUTH);
        listScroll.setOpaque(false);
        listSongView.setOpaque(false);

        add(topPanel, BorderLayout.NORTH);
        add(listSongView, BorderLayout.CENTER);
        add(paginationPanel, BorderLayout.SOUTH);
    }

    /**
     * This is GUI 2
     *
     * @return
     */
    public JPanel switchGUI2(String username) {

        this.username = username;
        topRatePanel = new TopRatePanel();
        myTableCellRender = new TableCellRender();
        myTableCellRender.setUsername(username);
        myTableCellRender.setTopRatePanel(topRatePanel);

        JPanel superPanel = new JPanel(new BorderLayout());
        songMap = model.getSongMap();
        songList = new ArrayList<String>(songMap.keySet());
        Collections.sort(songList);
        threadPool = Executors.newCachedThreadPool();
        contextMenu = new ContextMenu();
        paginationPanel = new PaginationPanel(songMap, songList, username);

        //create GUI here
        setLayout(new BorderLayout());
        PaintedPanel topPanel = new PaintedPanel(new BorderLayout(), MENU_PANEL);
        topPanel.setOpaque(false);
        //result = model.getSongCodeList();
        menuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        menuBar = new JMenuBar();

        //add item in file menu
        fileMenu = new JMenu(resources.getString("fileMenu"));
        menuBar.add(fileMenu);
        saveAction = new JMenuItem(resources.getString("saveAction"));
        exitAction = new JMenuItem(resources.getString("exitAction"));
        fileMenu.add(saveAction);
        fileMenu.add(exitAction);
        saveAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_MASK));
        exitAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_MASK));

        JMenuItem viewTopRate = new JMenuItem(resources.getString("viewTopRate"));
        //add item in menu
        viewMenu = new JMenu(resources.getString("viewMenu"));
        switchThemeAction = new JMenuItem(resources.getString("switchThemeAction"));
        viewMenu.add(switchThemeAction);
        viewMenu.add(viewTopRate);
        switchThemeAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.ALT_MASK));
        viewTopRate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.CTRL_MASK));

        viewTopRate.addActionListener(new ActionListener() {

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

        //add item in Tool
        JMenu toolMenu = new JMenu(resources.getString("toolMenu"));
        JMenuItem addSong = new JMenuItem(resources.getString("addSongAction"));
        JMenuItem editSong = new JMenuItem(resources.getString("editSongAction"));
        JMenuItem deleteSong = new JMenuItem(resources.getString("deleteSongAction"));
        deleteAllAction = new JMenuItem(resources.getString("deleteAllAction"));
        toolMenu.add(addSong);
        toolMenu.add(editSong);
        toolMenu.add(deleteSong);
        toolMenu.add(deleteAllAction);
        addSong.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
        editSong.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_MASK));
        deleteSong.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_MASK));
        deleteAllAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, KeyEvent.CTRL_MASK));

        //add menu
        menuBar.add(fileMenu);
        menuBar.add(viewMenu);
        menuBar.add(toolMenu);
        menuBar.add(helpMenu);

        //add menu bar to menu panel

        menuPanel.setOpaque(false);
        menuBar.setOpaque(false);

        menuPanel.add(menuBar);

        //action listener for menu
        aboutAction.addActionListener(new AboutActionListener());
        userGuideAction.addActionListener(new OpenHelpFile());

        switchThemeAction.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Main.changePanel(new StaffView(StaffView.this.username));
            }
        });


        addSong.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                AddSongInput addInputPanel = new AddSongInput(songMap, tableModel);
            }
        });

        editSong.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                EditSongInput editInputPanel = new EditSongInput(songMap, paginationPanel, tableModel, selectedRow);
            }
        });

        deleteSong.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //delete Song
                deleteSong();
            }
        });

        deleteAllAction.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                model.deleteAllSong();
                paginationPanel.setSongList(new ArrayList<String>());
                paginationPanel.flushPage(1);
                table.repaint();
                SavingAndReadingController.saveSong();
            }
        });

        //create tool Panel which contain Icon's functions for Staff
        JButton addSongButton = new JButton(ADD_BUTTON);
        addSongButton.setOpaque(false);
        addSongButton.setToolTipText(resources.getString("addSongButton"));
        JButton editSongButton = new JButton(EDIT_BUTTON);
        editSongButton.setOpaque(false);
        editSongButton.setToolTipText(resources.getString("editSongButton"));
        JButton deleteSongButton = new JButton(DELETE_BUTTON);
        deleteSongButton.setOpaque(false);
        deleteSongButton.setToolTipText(resources.getString("deleteSongButton"));
        JButton switchGUIButton = new JButton(SWITCH_BUTTON);
        switchGUIButton.setToolTipText(resources.getString("switchThemeAction"));
        switchGUIButton.setOpaque(false);

        JButton exportListButton=new JButton(EXPORT_LOGO);

        exportListButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //delete Song
                ExportCSVSong exportCSV = new ExportCSVSong();
            }
        });

        deleteSongButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //delete Song
                deleteSong();
            }
        });
        JButton importSongButton = new JButton(IMPORT_BUTTON);
        importSongButton.setOpaque(false);
        importSongButton.setToolTipText(resources.getString("importSongButton"));
        importSongButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //import csv file
                ImportCSVInput inputCSV = new ImportCSVInput();
            }
        });

        toolBar = new JToolBar(JToolBar.VERTICAL);

        toolBar.add(addSongButton);
        toolBar.add(importSongButton);
        toolBar.add(exportListButton);
        toolBar.add(editSongButton);
        toolBar.add(deleteSongButton);
        toolBar.add(switchGUIButton);
        toolBar.setOpaque(false);

        toolPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        toolPanel.setOpaque(false);
        toolPanel.add(toolBar);

        //acction of switchBUtton
        switchGUIButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Main.changePanel(new StaffView(StaffView.this.username));
            }
        });
        //search panel
        searchField = new JTextField(50);
        JButton searchButton = new JButton(SEARCH_BUTTON);
        searchButton.setOpaque(false);
        //JButton listAllSongButton = new JButton(new ImageIcon("img/button/listAllButton.png"));
        JButton listAllSongButton = new JButton(resources.getString("listAllSongButton"));
        listAllSongButton.setOpaque(false);

        listAllSongButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    paginationPanel.setCurrentPage(1);
                    paginationPanel.listAllRecord();
                    table.repaint();
                } catch (Exception g) {
                    g.printStackTrace();
                }

            }
        });

        JButton exitButton = new JButton(LOGOUT_BUTTON);
        exitButton.setOpaque(false);
        listAllSongButton.setPreferredSize(new Dimension(50, 40));
        searchButton.setPreferredSize(new Dimension(40, 40));
        exitButton.setPreferredSize(new Dimension(40, 40));
        searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setOpaque(false);
        searchBar = new JToolBar(JToolBar.HORIZONTAL);
        searchBar.setFloatable(true);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(listAllSongButton);
        searchPanel.add(exitButton);

        menuPanel.add(menuBar);

        resultLabel = new JLabel(resources.getString("resultLabel"));
        resultLabel.setOpaque(false);

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
        //

        sortChoice = new SortChooserPanel(paginationPanel, songList);

        JPanel option = new JPanel(new BorderLayout());
        option.setOpaque(false);
        option.add(advSearchPanel, BorderLayout.NORTH);
        option.add(sortChoice, BorderLayout.SOUTH);

        //create bottom panel
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.add(searchPanel, BorderLayout.CENTER);
        bottomPanel.add(option, BorderLayout.NORTH);
        bottomPanel.add(resultLabel, BorderLayout.SOUTH);

        topPanel.add(menuPanel, BorderLayout.NORTH);
        // topPanel.add(toolPanel, BorderLayout.CENTER);
        topPanel.add(bottomPanel, BorderLayout.SOUTH);

        addSongButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                AddSongInput addInputPanel = new AddSongInput(songMap, tableModel);
            }
        });
        editSongButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    if (!songMap.isEmpty()) {
                        EditSongInput editInputPanel = new EditSongInput(songMap, paginationPanel, tableModel, selectedRow);
                    } else {
                    }
                } catch (Exception g) {
                    g.printStackTrace();
                }
            }
        });

        searchButton.setToolTipText(resources.getString("searchButton"));
        searchButton.setOpaque(false);
        exitButton.setOpaque(false);
        //searchButton.setContentAreaFilled(false);
        // exitButton.setContentAreaFilled(false);
        searchButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                //search function
                searchMethod();
            }
        });

        searchField.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                //search function
                try {
                    timer.stop();
                    searchMethod();
                } catch (Exception g) {
                }
            }
        });

        searchField.addKeyListener(new KeyAdapter() {

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

        saveAction.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                SavingAndReadingController.saveSong();
            }
        });
        exitAction.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (getDialogConfirmation(resources.getString("quitConfirm")) == JOptionPane.YES_OPTION) {
                    SavingAndReadingController.saveRateMap();
                    SavingAndReadingController.saveFavoriteMap();
                    Main.changePanel(new ChooseRoleView());
                }
            }
        });
        exitButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (getDialogConfirmation(resources.getString("quitConfirm")) == JOptionPane.YES_OPTION) {
                    SavingAndReadingController.saveRateMap();
                    SavingAndReadingController.saveFavoriteMap();
                    Main.changePanel(new ChooseRoleView());
                }
            }
        });

        String[] columnNames = new String[]{resources.getString("CodeJt"), resources.getString("SongNameJt"), resources.getString("LyricJt"), resources.getString("ComposerJt"), resources.getString("RateJt")};
        tableModel = new QuickKarTableModel(columnNames, 0, false);
        table = new JTable(tableModel);
        table.setRowHeight(30);
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table.getColumn(resources.getString("CodeJt")).setCellRenderer(myTableCellRender);
        table.getColumn(resources.getString("SongNameJt")).setCellRenderer(myTableCellRender);
        table.getColumn(resources.getString("LyricJt")).setCellRenderer(myTableCellRender);
        table.getColumn(resources.getString("ComposerJt")).setCellRenderer(myTableCellRender);
        table.getColumn(resources.getString("RateJt")).setCellRenderer(myTableCellRender);
        table.getColumn(resources.getString("RateJt")).setCellEditor(new DefaultCellEditor(new JComboBox(new Object[]{0, 1, 2, 3, 4, 5})));
        table.addMouseListener(new MouseTableListener());
        table.setOpaque(false);
        paginationPanel.setTableModel(tableModel);

        threadPool.execute(new Runnable() {

            public void run() {
                paginationPanel.setCurrentPage(1);
                paginationPanel.flushPage(1);
            }
        });

        superPanel.add(topPanel, BorderLayout.NORTH);
        superPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        superPanel.add(toolBar, BorderLayout.WEST);
        superPanel.add(paginationPanel, BorderLayout.SOUTH);
        return superPanel;

    }

    /**
     * Method for delete song.
     * It calls Model to handle the delete and update the GUI afterward.
     */
    private void deleteSong() {

        //delete song
        if (table.getSelectedRowCount() > 0) {
            if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, resources.getString("deleteConfirm"), resources.getString("confirmation"), JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)) {
                int[] selectedRows = table.getSelectedRows();
                for (int i = selectedRows.length - 1; i >= 0; i--) {
                    String songCode = (String) tableModel.getValueAt(selectedRows[i], SONGCODE);
                    tableModel.removeRow(selectedRows[i]);
                    model.deleteSongMap(songCode);
                    songList.remove(songCode);
                }
            }
        }
        if (tableModel.getRowCount() == 0) {
            paginationPanel.setSongList(songList);
            if (paginationPanel.getCurrentPage() != 1) {
                paginationPanel.flushPage(paginationPanel.getCurrentPage() - 1);
            } else {
                paginationPanel.flushPage(1);
            }
        } else {
            paginationPanel.flushPage(paginationPanel.getCurrentPage());
        }
        SavingAndReadingController.saveSong();
    }

    /**
     * Responsible for handling searching.
     * It calls search functions accroding to user choice.
     * It calculates the searching time.
     */
    private void searchMethod() {

        String text = searchField.getText().trim().toLowerCase();
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
        Collections.sort(songList);
        paginationPanel.setSongList(songList);
        sortChoice.setSongList(songList);
        if (!songList.isEmpty()) {
            myTableCellRender.setSearchText(text);
        }
        long end = System.nanoTime();
        double second = (end - start) / 1000000000.0;

        resultLabel.setText(resources.getString("searchResult1")+ QuickKarMethod.formatDouble(second) + " " + resources.getString("searchResult2") + songList.size());
        threadPool.execute(new Runnable() {

            public void run() {
                try {
                    //processs input in a seperate thread
                    paginationPanel.setCurrentPage(1);
                    sortChoice.sortBySelectedOption();
                    table.repaint();
                } catch (Exception g) {
                    g.printStackTrace();
                }
            }
        });
    }

    /**
     * Handling mouse action:
     *  Left click
     *  Right-click menu context.
     *  Dragging to select multiple items.
     */
    private class MouseTableListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            //find the row that is selected
            selectedRow = table.rowAtPoint(e.getPoint());
            if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == DOUBLE_CLICK) {
                //left double click do what?
            } else if (e.getButton() == e.BUTTON3) {
                //right click do what
            } else {
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //find the row that is selected
            selectedRow = table.rowAtPoint(e.getPoint());
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //right click menu
            int rowIndex = table.getSelectedRow();
            if (rowIndex < 0) {
                return;
            }
            if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
                contextMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }

    /**
     * This is the right-click menu on JTable.
     */
    public class ContextMenu extends JPopupMenu {

        private JMenuItem itemDelete = new JMenuItem(resources.getString("itemDelete"));
        private JMenuItem itemEdit = new JMenuItem(resources.getString("itemEdit"));

        public ContextMenu() {

            this.add(itemDelete);
            this.add(itemEdit);

            itemDelete.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    //del a song
                    deleteSong();
                }
            });
            itemEdit.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    //edit a song
                    EditSongInput editInputPanel = new EditSongInput(songMap, paginationPanel, tableModel, selectedRow);
                }
            });

        }
    }

    public int getDialogConfirmation(String message) {
        int choose = JOptionPane.showConfirmDialog(null, message, resources.getString("information"), JOptionPane.YES_NO_OPTION);
        return choose;
    }
}
