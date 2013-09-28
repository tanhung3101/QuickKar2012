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

import QuickKarModel.facade.QuickKarModel;
import java.awt.*;
import QuickKarController.*;
import java.awt.event.*;
import java.util.ResourceBundle;
import javax.swing.*;

/**
 * This is the first panel.
 * User can choose whether to use the system as Guest or login as staff.
 *
 * @author ???
 */
public class ChooseRoleView extends PaintedPanel implements SongInfoInterface {

    private JButton staffButton, customerButton, exitButton, switchLanguage;
    private JPanel welcomePanel;
    private JPanel loginPanel;
    private JPanel buttonPanel;
    private JLabel welcome, role;
    private JPanel panel2;
    private JPanel panel3;
    private QuickKarModel model = QuickKarModel.getModel();
    private JTextField nameField = new JTextField(15);
    private JPasswordField passField = new JPasswordField(15);
    private JButton loginButton;
    private JButton backButton;
    private JButton nextButton;
    private JTextField phoneInput;
    private JPanel phonePanel;
    private JLabel phoneLabel, nameLabel, passLabel;
    private ResourceBundle resources = null;

    public ChooseRoleView() {



        super(new GridBagLayout(), BACKGROUND);
        welcomePanel = new JPanel();
        resources = model.getResource();
        panel2 = new JPanel();
        panel3 = new JPanel();

        //change Language Button
        switchLanguage = new JButton(resources.getString("language"));

        switchLanguage.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                resources = model.switchReSource();
                Main.changePanel(new ChooseRoleView());
            }
        });


        staffButton = new JButton(resources.getString("staffButton"));
        customerButton = new JButton(resources.getString("customerButton"));
        exitButton = new JButton(resources.getString("exitButton"));
        exitButton.setHorizontalTextPosition(JButton.CENTER);
        exitButton.setOpaque(false);
        buttonPanel = new JPanel();

        staffButton.setOpaque(false);
        customerButton.setOpaque(false);
        exitButton.setOpaque(false);

        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(staffButton);
        buttonPanel.add(customerButton);
        buttonPanel.add(exitButton);
        buttonPanel.add(switchLanguage);
        welcomePanel.setLayout(new GridLayout(3, 1));
        welcome = new JLabel(resources.getString("welcomeMessage"));
        welcome.setFont(new Font(resources.getString("font"), Font.BOLD, 30));
        panel2.add(welcome);
        role = new JLabel(resources.getString("whoAreYouMessage"));
        role.setFont(new Font(resources.getString("font"), Font.BOLD, 25));
        role.setForeground(Color.red);
        panel3.add(role);
        buttonPanel.setOpaque(false);
        panel2.setOpaque(false);
        panel3.setOpaque(false);
        welcomePanel.setOpaque(false);
        welcomePanel.add(panel2);
        welcomePanel.add(panel3);
        welcomePanel.add(buttonPanel);

        loginPanel = getLoginPanel();

        //Action of Exit Button
        exitButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
        //Action of Staff Button
        staffButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                welcomePanel.removeAll();
                welcomePanel.setLayout(new BorderLayout());
                welcomePanel.add(loginPanel, BorderLayout.CENTER);
                loginPanel.setOpaque(false);
                updateUI();
                //move cursor to txtfield when startup.
                nameField.requestFocusInWindow();
            }
        });
        customerButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                welcomePanel.removeAll();
                welcomePanel.setLayout(new BorderLayout());

                phonePanel = getPhonePanel();

                welcomePanel.add(phonePanel, BorderLayout.CENTER);
                phonePanel.setOpaque(false);
                updateUI();
                //move cursor to txtfield when startup.
                phoneInput.requestFocusInWindow();
            }
        });

        //press Enter key to login.
        KeyAdapter ka = new enterKey();
        nameField.addKeyListener(ka);
        passField.addKeyListener(ka);

        //setLayout(new GridLayout(3, 3));

        add(welcomePanel);
    }

    /**
     * Panel to get phone number from user when they choose Guest.
     * Phone number is required to use rating system.
     *
     * @return
     */
    private JPanel getPhonePanel() {
        PaintedPanel mainPanel = new PaintedPanel(new GridLayout(3, 1), PHONE_PANEL);
        mainPanel.setPreferredSize(new Dimension(490, 125));

        switchLanguage = new JButton(resources.getString("language"));

        switchLanguage.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                resources = model.switchReSource();
                switchLanguage.setText(resources.getString("language"));
                phoneLabel.setText(resources.getString("phoneLable"));
                phoneLabel.setFont(new Font(resources.getString("font"), Font.BOLD, 17));

                        if (model.isIsEng()) {
                    backButton.setIcon(BACK_BUTTON);
                    nextButton.setIcon(NEXT_BUTTON);
                } else {
                    loginButton.setIcon(NEXT_BUTTON_VN);
                    nextButton.setIcon(BACK_BUTTON_VN);
                }
                updateUI();

            }
        });


        JPanel secondPanel = new JPanel();
        phoneLabel = new JLabel(resources.getString("phoneLable"));
        phoneLabel.setFont(new Font(resources.getString("font"), Font.BOLD, 17));
        phoneLabel.setOpaque(false);
        secondPanel.add(phoneLabel);
        secondPanel.setOpaque(false);

        JPanel firstPanel = new JPanel();
        phoneInput = new JTextField(15);
        phoneInput.setOpaque(false);
        firstPanel.add(phoneInput);
        firstPanel.setOpaque(false);

        JPanel confirmPanel = new JPanel();
        confirmPanel.setLayout(new FlowLayout());

        if (model.isIsEng()) {
                    backButton = new JButton(BACK_BUTTON);
                    nextButton = new JButton(NEXT_BUTTON);
                } else {
                    loginButton = new JButton(LOGIN_BUTTON_VN);
                    nextButton = new JButton(NEXT_BUTTON_VN);
                }

        nextButton.setPreferredSize(new Dimension(80, 28));
        backButton.setPreferredSize(new Dimension(80, 28));
        nextButton.setOpaque(false);
        backButton.setOpaque(false);
        confirmPanel.add(nextButton);
        confirmPanel.add(backButton);
        confirmPanel.add(switchLanguage);

        backButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
//                welcomePanel.removeAll();
//                welcomePanel.setLayout(new GridLayout(3, 1));
//                welcomePanel.add(panel2);
//                welcomePanel.add(panel3);
//                welcomePanel.add(buttonPanel);
                Main.changePanel(new ChooseRoleView());
                updateUI();
            }
        });

        nextButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (checkPhone()) {
                    Main.changePanel(new CustomerView(phoneInput.getText()));
                }
            }
        });

        phoneInput.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (checkPhone()) {
                        Main.changePanel(new CustomerView(phoneInput.getText()));
                    }
                }
            }
        });

        mainPanel.add(secondPanel);
        mainPanel.add(firstPanel);
        mainPanel.add(confirmPanel);

        confirmPanel.setOpaque(false);
        mainPanel.setOpaque(false);

        return mainPanel;
    }

    /**
     * Draw login form when user choose to login.
     *
     * @return
     */
    private JPanel getLoginPanel() {

        PaintedPanel mainPanel = new PaintedPanel(new GridLayout(1, 1), LOGIN_PANEL);
        // mainPanel.setPreferredSize(new Dimension(1000, 520));
        JPanel InforPanel = new JPanel();

        InforPanel.setLayout(new GridLayout(3, 1));





        JPanel namePanel = new JPanel();
        JPanel passPanel = new JPanel();
        JPanel confirmPanel = new JPanel();
        if (model.isIsEng()) {
                    backButton = new JButton(BACK_BUTTON);
                    loginButton = new JButton(LOGIN_BUTTON);
                } else {
                    loginButton = new JButton(LOGIN_BUTTON_VN);
                    backButton = new JButton(BACK_BUTTON_VN);
                }
        nameLabel = new JLabel(resources.getString("nameLabel"));
        passLabel = new JLabel(resources.getString("passLabel"));

        loginButton.setPreferredSize(new Dimension(80, 28));
        loginButton.setOpaque(false);

        backButton.setPreferredSize(new Dimension(80, 28));
        backButton.setOpaque(false);

        //change Langeuage Button
        switchLanguage = new JButton(resources.getString("language"));

        switchLanguage.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                resources = model.switchReSource();
                switchLanguage.setText(resources.getString("language"));
                nameLabel.setText(resources.getString("nameLabel"));
                passLabel.setText(resources.getString("passLabel"));
                if (model.isIsEng()) {
                    backButton.setIcon(BACK_BUTTON);
                    loginButton.setIcon(LOGIN_BUTTON);

                } else {
                    loginButton.setIcon(LOGIN_BUTTON_VN);
                    backButton.setIcon(BACK_BUTTON_VN);
                }
                updateUI();

            }
        });

        //create lock logo
        //PaintedPanel lockLogo=new PaintedPanel("img/login/padlock-icon.png");

        InforPanel.add(namePanel);
        InforPanel.add(passPanel);
        InforPanel.add(confirmPanel);

        namePanel.add(nameLabel);
        namePanel.add(nameField);
        namePanel.setOpaque(false);

        //passPanel.setLayout(new GridLayout(1, 2));
        passPanel.add(passLabel);
        passPanel.add(passField);
        passPanel.setOpaque(false);

        confirmPanel.setLayout(new FlowLayout());
        confirmPanel.add(loginButton);
        confirmPanel.add(backButton);
        confirmPanel.add(switchLanguage);
        confirmPanel.setOpaque(false);

        backButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

//                nameField.setText("");
//                passField.setText("");
//                welcomePanel.removeAll();
//                welcomePanel.setLayout(new GridLayout(3, 1));
//                welcomePanel.add(panel2);
//                welcomePanel.add(panel3);
//                welcomePanel.add(buttonPanel);
//                updateUI();
                Main.changePanel(new ChooseRoleView());
            }
        });

        loginButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                loginHandling();
            }
        });
        //mainPanel.add(lockLogo);

        InforPanel.setOpaque(false);
        mainPanel.add(InforPanel);
        mainPanel.setOpaque(false);
        return mainPanel;
    }

    /**
     * Handle Enter key event for user-friendly, easy-to-use.
     * User can press enter to login, instead of having to click on buttons.
     */
    private class enterKey extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            //press Enter key to login...
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                loginHandling();
            }
        }
    }

    /**
     * Process login information and process here.
     *  Compare input to database. Reject or accept.
     */
    public void loginHandling() {

        String username = nameField.getText().trim();
        String password = String.valueOf(passField.getPassword()).trim();

        if (username.equals("") && password.equals("")) {
            JOptionPane.showMessageDialog(null, resources.getString("mustEnterUserNameandPasswordError"), "Error", JOptionPane.ERROR_MESSAGE);
            nameField.requestFocusInWindow();
        } else if (username.equals("")) {
            JOptionPane.showMessageDialog(null, resources.getString("mustEnterUserNameError"), "Error", JOptionPane.ERROR_MESSAGE);
            nameField.requestFocusInWindow();
        } else if (password.equals("")) {
            JOptionPane.showMessageDialog(null, resources.getString("mustEnterPasswordError"), "Error", JOptionPane.ERROR_MESSAGE);
            passField.requestFocusInWindow();
        } else if (model.loginFunction(username, password)) {

            //all fields are entered
            if (username.equals("admin")) {
                //admin login
                Main.changePanel(new AdminGUI());
            } else {
                //staff
                Main.changePanel(new StaffView(username));
            }
        } else {
            //announce wrong username or password
            JOptionPane.showMessageDialog(null, resources.getString("WrongUserNameorPasswordError"), "Error", JOptionPane.ERROR_MESSAGE);
            passField.requestFocusInWindow();
        }
    }

    /**
     * Validate phone input for Customer login
     * 
     * @return
     */
    private boolean checkPhone() {
        /*
        08-3776-1300   08-xxxx-xxxx
        3776-1300      xxxx-xxxx
        090-845-4798   09x-xxx-xxxx
        0122-468-5678  01xx-xxx-xxxx
         */

        System.out.println("aaa" + phoneInput.getText());
        if (!phoneInput.getText().matches("(((09[\\d]{1,1}|01[\\d]{2,2})-[\\d]{3,3}-[\\d]{4,4}|[\\d]{4,4}-[\\d]{4,4})|08-[\\d]{4,4}-[\\d]{4,4})")) {

            JOptionPane.showMessageDialog(null, resources.getString("invalidPhoneError"));
            phoneInput.setText("");
            phoneInput.requestFocusInWindow();
           
            return false;
        } else {
            return true;
        }
    }
}

class NewPanel extends PaintedPanel {

    public NewPanel(ImageIcon background, JPanel superJPanel, JPanel subPanel) {
        superJPanel.setLayout(new BorderLayout());
        superJPanel.add(subPanel, BorderLayout.CENTER);

        add(superJPanel);
    }
}
