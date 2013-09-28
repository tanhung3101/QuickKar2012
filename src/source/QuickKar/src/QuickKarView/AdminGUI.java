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
import QuickKarModel.facade.AccountInfo;
import QuickKarModel.facade.QuickKarModel;
import QuickKarModel.facade.QuickKarTableModel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.Map;
import java.util.ResourceBundle;
import javax.annotation.Resource;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * This is the GUI for Admin.
 * It is responsible for drawing and handling GUI events
 *
 * @author vuongdothanhhuy
 */
public class AdminGUI extends PaintedPanel implements SongInfoInterface {

    private static final int DOUBLE_CLICK = 2;
    private static final int USERNAME = 0;
    private static final int PASSWORD = 1;
    private static final int NAME = 2;
    private static final int EMAIL_ADDRESS = 3;
    private static final int TELEPHONE_NUMBER = 4;
    private static final int OTHER_INFO = 5;
    private JTable table;
    private DefaultTableModel tableModel;
    private QuickKarModel model;
    private Map<String, AccountInfo> accountMap;
    private boolean isEdited;
    private int selectedRow;
    private final ContextMenu contextMenu;
    private ResourceBundle resources;
    private JButton switchLanguageButton;
    

    /**
     * Draw admin GUI.
     *
     */
    public AdminGUI() {

        super(BACKGROUND);
        //initial process
        model = QuickKarModel.getModel();
        accountMap = model.getAccountMap();
        contextMenu = new ContextMenu();
        resources=model.getResource();

        //make GUI process
        setLayout(new BorderLayout());
        PaintedPanel topPanel = new PaintedPanel(new BorderLayout(),MENU_PANEL);

        //menu panel
        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        menuPanel.setOpaque(false);
        JMenuBar menuBar = new JMenuBar();
        menuBar.setOpaque(false);

        //add item in file menu
        JMenu fileMenu = new JMenu(resources.getString("fileMenu"));
        JMenuItem openAction = new JMenuItem(resources.getString("openAction"));
        JMenuItem exitAction = new JMenuItem(resources.getString("exitAction"));
        fileMenu.add(openAction);
        fileMenu.add(exitAction);
        openAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK));
        openAction.setEnabled(false);

        //add item in menu
        JMenu viewMenu = new JMenu(resources.getString("viewMenu"));
        JMenuItem switchThemeAction = new JMenuItem(resources.getString("switchThemeAction"));
        viewMenu.add(switchThemeAction);
        switchThemeAction.setEnabled(false);
        switchThemeAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.SHIFT_MASK));
        switchThemeAction.setEnabled(false);

        //add item in Tool
        JMenu toolMenu = new JMenu(resources.getString("toolMenu"));
        JMenuItem addStaff = new JMenuItem(resources.getString("addStaff"));
        JMenuItem editStaff = new JMenuItem(resources.getString("editStaff"));
        JMenuItem deleteStaff = new JMenuItem(resources.getString("deleteStaff"));
        toolMenu.add(addStaff);
        toolMenu.add(editStaff);
        toolMenu.add(deleteStaff);
        addStaff.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_MASK));
        editStaff.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_MASK));
        deleteStaff.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_MASK));

        //add item in help menu
        JMenu helpMenu = new JMenu(resources.getString("helpMenu"));
        JMenuItem userGuideAction = new JMenuItem(resources.getString("userGuideAction"));
        JMenuItem aboutAction = new JMenuItem(resources.getString("aboutAction"));
        helpMenu.add(userGuideAction);
        helpMenu.add(aboutAction);
        userGuideAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
        aboutAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.SHIFT_MASK));

        //add menu
        menuBar.add(fileMenu);
        menuBar.add(viewMenu);
        menuBar.add(toolMenu);
        menuBar.add(helpMenu);

        //add menu bar to menu panel
        menuPanel.add(menuBar);

        //action listener for menu
        aboutAction.addActionListener(new AboutActionListener());
        userGuideAction.addActionListener(new OpenHelpFile());

        //tool panel
        JPanel toolPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JToolBar toolBar = new JToolBar(JToolBar.HORIZONTAL);
        toolBar.setFloatable(true);

        exitAction.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if (getDialogConfirmation(resources.getString("quitConfirm")) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        addStaff.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //add staff
                InputPanel inputPanel = new InputPanel();
            }
        });

        editStaff.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //edit staff
                isEdited = true;
                InputPanel inputPanel = new InputPanel();
            }
        });

        deleteStaff.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //delete Account
                deleteStaff();
            }
        });

        JButton addButton = new JButton(ADD_BUTTON);
        //addButton.setContentAreaFilled(false);//
        addButton.setOpaque(false);
        addButton.setToolTipText(resources.getString("addButton"));
        addButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //add staff
                InputPanel inputPanel = new InputPanel();
            }
        });

        JButton editButton = new JButton(EDIT_BUTTON);
        // editButton.setContentAreaFilled(false);//
        editButton.setToolTipText(resources.getString("editButton"));
        editButton.setOpaque(false);
        editButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //edit staff
                isEdited = true;
                InputPanel inputPanel = new InputPanel();
            }
        });

        JButton deleteButton = new JButton(DELETE_BUTTON);
        //deleteButton.setContentAreaFilled(false);//
        deleteButton.setToolTipText(resources.getString("deleteButton"));
        deleteButton.setOpaque(false);
        deleteButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //delete Account
                deleteStaff();
            }
        });

        switchLanguageButton=new JButton(resources.getString("language"));
        
        switchLanguageButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
             
                resources=model.switchReSource();
                Main.changePanel(new AdminGUI());
                
            }
        });

        final JButton logoutButton = new JButton(LOGOUT_BUTTON);
        logoutButton.setToolTipText(resources.getString("logoutButton"));
        //logoutButton.setContentAreaFilled(false);//
        logoutButton.setOpaque(false);
        logoutButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Main.changePanel(new ChooseRoleView());
            }
        });
        // toolBar.setOpaque(false);
        toolBar.add(addButton);
        toolBar.add(editButton);
        toolBar.add(deleteButton);
        toolBar.add(logoutButton);
        toolBar.add(switchLanguageButton);

        toolBar.setOpaque(false);
        //add tool bar to tool bar panel
        toolPanel.add(toolBar);
        toolPanel.setOpaque(false);

        topPanel.add(menuPanel, BorderLayout.NORTH);
        topPanel.add(toolPanel, BorderLayout.CENTER);

        //get table model and process data
        getTableModel();
        table = new JTable(tableModel);
        table.setOpaque(false);
        table.setRowHeight(30);
        table.getTableHeader().setReorderingAllowed(false);
        table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table.getColumn(resources.getString("passwordJTable")).setCellRenderer(new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                return new JPasswordField((String) value);
            }
        });

        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                //find the row that is selected
                selectedRow = table.rowAtPoint(e.getPoint());

                //double click on the record, display the edit menu
                if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == DOUBLE_CLICK) {
                    //edit staff
                    isEdited = true;
                    InputPanel inputPanel = new InputPanel();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //find the row that is selected
                selectedRow = table.rowAtPoint(e.getPoint());
            }

            @Override
            public void mouseReleased(MouseEvent e) {

                //preserve for future uses
                //context menu right click
                //right click menu
//                int r = table.rowAtPoint(e.getPoint());
//                if (r >= 0 && r < table.getRowCount()) {
//                    table.setRowSelectionInterval(r, r);
//                } else {
//                    table.clearSelection();
//                }

                int rowindex = table.getSelectedRow();
                if (rowindex < 0) {
                    return;
                }
                if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
                    contextMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    /**
     * Access to table model to get data and render the table.
     */
    private void getTableModel() {

        //column header
       
        String[] columnNames=new String[]{resources.getString("usernameJTable"),
        resources.getString("passwordJTable"),
        resources.getString("nameJTable"),
        resources.getString("emailJTable"),
        resources.getString("telephoneJTable"),
        resources.getString("otherInfoJTable")};
        tableModel = new QuickKarTableModel(columnNames, 0, true);
        for (Map.Entry<String, AccountInfo> entry : accountMap.entrySet()) {
            //create data
            Object[] objects = new Object[columnNames.length];
            objects[USERNAME] = entry.getKey();
            //get account infomation
            AccountInfo accountInfo = entry.getValue();
            //input into objects array
            objects[PASSWORD] = accountInfo.getPassword();
            objects[NAME] = accountInfo.getName();
            objects[EMAIL_ADDRESS] = accountInfo.getEmail();
            objects[TELEPHONE_NUMBER] = accountInfo.getTelephoneNo();
            objects[OTHER_INFO] = accountInfo.getOtherInfo();
            tableModel.addRow(objects);
        }
    }

    /**
     * Handling delete staff.
     *  Validation with model.
     *  Call model to delete.
     *  Update GUI.
     */
    private void deleteStaff() {

        //delete Account
        if (table.getSelectedRowCount() > 0) {
            if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, resources.getString("deleteConfirmLabl"), "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)) {
                int[] selectedRows = table.getSelectedRows();
                for (int i = selectedRows.length - 1; i >= 0; i--) {
                    if (!((String) tableModel.getValueAt(selectedRows[i], 0)).equals("admin")) {
                        model.deleteAccount((String) tableModel.getValueAt(selectedRows[i], USERNAME));
                        tableModel.removeRow(selectedRows[i]);
                    } else {
                        JOptionPane.showMessageDialog(null, resources.getString("deleteAdminWarning"), "Warning", JOptionPane.ERROR_MESSAGE);
                    }
                }
                SavingAndReadingController.saveAccount();
            }
        }
    }

    /**
     * Panel for add a new staff
     */
    private class InputPanel extends JDialog {

        private JTextField usernameTF = new JTextField(20);
        private JPasswordField passwordTF = new JPasswordField(20);
        private JPasswordField confirmPasswordTF = new JPasswordField(20);
        private JTextField nameTF = new JTextField(20);
        private JTextField emailTF = new JTextField(20);
        private JTextField telephoneNoTF = new JTextField(20);
        private JTextArea otherInfoTF = new JTextArea(5, 20);

        public InputPanel() {

            super();
            if (isEdited) {
                setTitle(resources.getString("EditPanelTitle"));
            } else {
                setTitle(resources.getString("AddPanelTitle"));
            }
            add(getInputPanel());
            pack();
            setVisible(true);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        }

        private JPanel getInputPanel() {

            JPanel inputPanel = new JPanel(new BorderLayout());
            //create top panel
            JPanel topPanel = new JPanel(new GridLayout(6, 2, 10, 10));

            if (isEdited) {
                String selectedUserName = (String) tableModel.getValueAt(selectedRow, USERNAME);
                if (selectedUserName.equals("admin")) {
                    usernameTF.setEditable(false);
                }
                usernameTF.setText(selectedUserName);
                //get account information
                AccountInfo accountInfo = accountMap.get(selectedUserName);
                //set Text for text box
                passwordTF.setText(accountInfo.getPassword());
                confirmPasswordTF.setText(accountInfo.getPassword());
                nameTF.setText(accountInfo.getName());
                emailTF.setText(accountInfo.getEmail());
                telephoneNoTF.setText(accountInfo.getTelephoneNo());
                otherInfoTF.setText(accountInfo.getOtherInfo());
            }
            //add item to top panel
            topPanel.add(new JLabel(resources.getString("usernameTFLabel")));
            topPanel.add(usernameTF);
            topPanel.add(new JLabel(resources.getString("passwordTFLabel")));
            topPanel.add(passwordTF);
            topPanel.add(new JLabel(resources.getString("confirmPasswordTFLabel")));
            topPanel.add(confirmPasswordTF);
            topPanel.add(new JLabel(resources.getString("nameTFLabel")));
            topPanel.add(nameTF);
            topPanel.add(new JLabel(resources.getString("emailTFLabel")));
            topPanel.add(emailTF);
            topPanel.add(new JLabel(resources.getString("telephoneNoTFLabel")));
            topPanel.add(telephoneNoTF);

            otherInfoTF.setLineWrap(true);

            JPanel otherInfoPanel = new JPanel(new GridLayout(1, 2, 10, 10));
            otherInfoPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
            otherInfoPanel.add(new JLabel(resources.getString("otherInfoTFLabel")));
            otherInfoPanel.add(otherInfoTF);

            // hook Enter key...
            KeyAdapter keyAdapter = new enterKey();
            usernameTF.addKeyListener(keyAdapter);
            passwordTF.addKeyListener(keyAdapter);
            confirmPasswordTF.addKeyListener(keyAdapter);
            nameTF.addKeyListener(keyAdapter);
            emailTF.addKeyListener(keyAdapter);
            telephoneNoTF.addKeyListener(keyAdapter);
            otherInfoTF.addKeyListener(keyAdapter);

            //create button panel
            JPanel buttonPanel = new JPanel();
            //create ok button and add validation listener
            JButton okButton = new JButton(resources.getString("okButton"));
            if (isEdited) {
                okButton.setText(resources.getString("okButton"));
            }
            okButton.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    //call validation and checking
                    listenerAction();
                }
            });

            //create cancel button and add dispose listener
            JButton cancelButton = new JButton(resources.getString("cancelButton"));
            cancelButton.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    isEdited = false;
                    dispose();
                }
            });

            buttonPanel.add(okButton);
            buttonPanel.add(cancelButton);

            inputPanel.add(topPanel, BorderLayout.NORTH);
            inputPanel.add(otherInfoPanel, BorderLayout.CENTER);
            inputPanel.add(buttonPanel, BorderLayout.SOUTH);

            return inputPanel;
        }

        public void listenerAction() {

            String username = usernameTF.getText().trim();
            String password = String.valueOf(passwordTF.getPassword()).trim();
            String confirmPassword = String.valueOf(confirmPasswordTF.getPassword()).trim();
            String fullName = nameTF.getText().trim();
            String email = emailTF.getText().trim();
            String telephoneNo = telephoneNoTF.getText().trim();
            String otherInfo = otherInfoTF.getText().trim();

            String message = "";

            if (username.matches("[a-zA-Z0-9!@#$%^&*()]{4,}")) {
                if (!isEdited && accountMap.containsKey(username)) {
                    message = resources.getString("duplicateUserNameMessage");
                }
            } else {
                message = resources.getString("invalidUserNameMessage");
            }
            if (password.isEmpty()) {
                message += resources.getString("invalidPasswordMessage");
            }
            if (!(password.equals(confirmPassword))) {
                message += resources.getString("invalidConfirmPasswordMessage");
            }
            if (!email.matches("^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[\\w]{2,4}$")) {
                message += resources.getString("invalidEmailMessage");
            }
            /*
            08-3776-1300   08-xxxx-xxxx
            3776-1300      xxxx-xxxx
            090-845-4798   09x-xxx-xxxx
            0122-468-5678  01xx-xxx-xxxx
             */
            if (!telephoneNo.matches("(((09[\\d]{1,1}|01[\\d]{2,2})-[\\d]{3,3}-[\\d]{4,4}|[\\d]{4,4}-[\\d]{4,4})|08-[\\d]{4,4}-[\\d]{4,4})")) {
                message += resources.getString("invalidTelephoneMessage");
            }
            if (message.isEmpty()) {
                if (isEdited) {
                    //is being edited
                    model.deleteAccount((String) tableModel.getValueAt(selectedRow, USERNAME));
                    tableModel.setValueAt(username, selectedRow, USERNAME);
                    tableModel.setValueAt(password, selectedRow, PASSWORD);
                    tableModel.setValueAt(fullName, selectedRow, NAME);
                    tableModel.setValueAt(email, selectedRow, EMAIL_ADDRESS);
                    tableModel.setValueAt(telephoneNo, selectedRow, TELEPHONE_NUMBER);
                    tableModel.setValueAt(otherInfo, selectedRow, OTHER_INFO);
                } else {
                    //add row to table model
                    tableModel.addRow(new Object[]{username, password, fullName, email, telephoneNo, otherInfo});
                }
                model.addAccount(username, password, email, email, telephoneNo, otherInfo);
                SavingAndReadingController.saveAccount();
                isEdited = false;
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, message, "Error message", JOptionPane.ERROR_MESSAGE);
            }
        }

        private class enterKey extends KeyAdapter {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    listenerAction();
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    isEdited = false;
                    dispose();
                }
            }
        }

        @Override
        protected void processWindowEvent(WindowEvent e) {

            if (e.getID() == WindowEvent.WINDOW_CLOSING) {
                isEdited = false;
                dispose();
            }
        }
    }

    /**
     * Right-click context menu for JTable.
     */
    private class ContextMenu extends JPopupMenu {


        private QuickKarModel model=QuickKarModel.getModel();
        private ResourceBundle resources=model.getResource();
        private JMenuItem itemDelete = new JMenuItem(resources.getString("itemDelete"));
        private JMenuItem itemEdit = new JMenuItem(resources.getString("itemEdit"));
        

        public ContextMenu() {

            this.add(itemDelete);
            this.add(itemEdit);

            itemDelete.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    deleteStaff();
                }
            });
            itemEdit.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    //edit staff
                    isEdited = true;
                    InputPanel inputPanel = new InputPanel();
                }
            });
        }
    }

    private int getDialogConfirmation(String message) {
        int choose = JOptionPane.showConfirmDialog(null, message, "Information", JOptionPane.YES_NO_OPTION);
        return choose;
    }
}
