/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Login;

import SQL.SQLLogin;
import Global.Methods;
import java.awt.Color;

//Import the packages for the other pages here
import Customer.StorePage;
import Global.Lists;
import Global.Variables;
import Manager.ManagerView;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author karso
 */
public class LogonPage extends javax.swing.JFrame {

    /**
     * Creates new form LogonPage
     */
    public LogonPage() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loggedIn = new javax.swing.JDialog();
        Level = new javax.swing.JLabel();
        iconHolder = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        loginPanel = new javax.swing.JPanel();
        loginButton = new javax.swing.JButton();
        usernameField = new javax.swing.JTextField();
        usernameLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        passwordLabel = new javax.swing.JLabel();
        createAccountLabel = new javax.swing.JLabel();
        forgetPasswordLabel = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        showLabel = new javax.swing.JLabel();
        Instructions = new javax.swing.JLabel();
        guestButton = new javax.swing.JButton();
        statusLabel = new javax.swing.JLabel();

        loggedIn.setBounds(new java.awt.Rectangle(0, 0, 100, 100));

        Level.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        Level.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout loggedInLayout = new javax.swing.GroupLayout(loggedIn.getContentPane());
        loggedIn.getContentPane().setLayout(loggedInLayout);
        loggedInLayout.setHorizontalGroup(
            loggedInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loggedInLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Level, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addContainerGap())
        );
        loggedInLayout.setVerticalGroup(
            loggedInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loggedInLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Level, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addContainerGap())
        );

        iconHolder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/UsedGamesLogo.png"))); // NOI18N
        iconHolder.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login Page");
        setBackground(new java.awt.Color(0, 48, 90));
        setForeground(new java.awt.Color(0, 48, 90));
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/UsedGamesLogo.png")).getImage());

        jPanel1.setBackground(new java.awt.Color(122, 186, 242));

        loginPanel.setBackground(new java.awt.Color(122, 186, 242));

        loginButton.setBackground(new java.awt.Color(0, 48, 90));
        loginButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        loginButton.setForeground(new java.awt.Color(255, 255, 255));
        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        usernameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usernameFieldKeyPressed(evt);
            }
        });

        usernameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        usernameLabel.setLabelFor(usernameField);
        usernameLabel.setText("Username");

        passwordField.setToolTipText("");
        passwordField.setEchoChar('*');
        passwordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordFieldKeyPressed(evt);
            }
        });

        passwordLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        passwordLabel.setLabelFor(passwordField);
        passwordLabel.setText("Password");

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(loginButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(usernameField, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(passwordField, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usernameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(passwordLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(loginButton)
                .addContainerGap())
        );

        createAccountLabel.setForeground(new java.awt.Color(51, 102, 255));
        createAccountLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        createAccountLabel.setText("Create Account");
        createAccountLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                createAccountLabelMouseClicked(evt);
            }
        });

        forgetPasswordLabel.setForeground(new java.awt.Color(0, 102, 255));
        forgetPasswordLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        forgetPasswordLabel.setText("Forgot Password");
        forgetPasswordLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                forgetPasswordLabelMouseClicked(evt);
            }
        });

        title.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("Login Page");

        showLabel.setText("Show");
        showLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                showLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                showLabelMouseExited(evt);
            }
        });

        Instructions.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        Instructions.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Instructions.setText("Click one of these to create an account or change your password");

        guestButton.setBackground(new java.awt.Color(0, 48, 90));
        guestButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        guestButton.setForeground(new java.awt.Color(255, 255, 255));
        guestButton.setText("Continue as Guest");
        guestButton.setPreferredSize(new java.awt.Dimension(246, 23));
        guestButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guestButtonActionPerformed(evt);
            }
        });

        statusLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        statusLabel.setForeground(new java.awt.Color(255, 0, 51));
        statusLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        statusLabel.setText("Test");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(statusLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Instructions, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(156, 156, 156)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(createAccountLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                                    .addComponent(forgetPasswordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(guestButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(loginPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(showLabel)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(statusLabel)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(showLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(96, 96, 96))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(guestButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(createAccountLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(forgetPasswordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Instructions)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Create any global variables here
    CreateAccountPage accountPage = new CreateAccountPage(this);
    ForgotPasswordPage passwordPage = new ForgotPasswordPage(this);

    //Here we will create the other pages here as well
    //Dont worry about every single one, like this login page, the main or first pgaes in each package will create the other pages if there are any needed
    StorePage store = new StorePage(this);
    ManagerView managerPage = new ManagerView(this, store);

    private void createAccountLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createAccountLabelMouseClicked
        accountPage.open();
        this.setVisible(false);
    }//GEN-LAST:event_createAccountLabelMouseClicked

    private void forgetPasswordLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_forgetPasswordLabelMouseClicked

        this.setVisible(false);

        //Instead of just making it visible we will run a function to make it start over again
        passwordPage.open();
    }//GEN-LAST:event_forgetPasswordLabelMouseClicked

    private void showLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showLabelMouseEntered

        //When the mouse enters show the password
        passwordField.setEchoChar((char) 0);

    }//GEN-LAST:event_showLabelMouseEntered

    private void showLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showLabelMouseExited
        passwordField.setEchoChar('*');
    }//GEN-LAST:event_showLabelMouseExited

    private void guestButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guestButtonActionPerformed
        store.openGuest();

        Variables.currentLevel = "Customer";

        this.dispose();
    }//GEN-LAST:event_guestButtonActionPerformed

    private void usernameFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            passwordField.requestFocus();

        }
    }//GEN-LAST:event_usernameFieldKeyPressed

    private void passwordFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            //First get the password
            String password = Methods.getPassword(passwordField.getPassword());

            //Take the information in the fields and login
            String level = SQLLogin.checkCredentials(usernameField.getText(), password);

            //Now we do appropiate actions based on waht is returned
            //First check if None wasnt returned, which means no user in this case
            if (level.equals("None")) {

                statusLabel.setText("Either user does not exist or password is incorrect");
                statusLabel.setForeground(Color.red);
                statusLabel.setVisible(true);

            } else if (level.equals("ERROR")) {

                statusLabel.setText("An error occured, please try again");
                statusLabel.setForeground(Color.red);
                statusLabel.setVisible(true);

            } else if (level.equals("Customer")) {

                store.open();
                this.dispose();

            } else if (level.equals("Manager")) {

                try {
                    managerPage.open();
                } catch (ParseException ex) {
                    Logger.getLogger(LogonPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
                this.dispose();

            }

        }
    }//GEN-LAST:event_passwordFieldKeyPressed

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        //First get the password
        String password = Methods.getPassword(passwordField.getPassword());

        //Take the information in the fields and login
        String level = SQLLogin.checkCredentials(usernameField.getText(), password);

        //Now we do appropiate actions based on waht is returned
        //First check if None wasnt returned, which means no user in this case
        if (level.equals("None")) {

            statusLabel.setText("Either user does not exist or password is incorrect");
            statusLabel.setForeground(Color.red);
            statusLabel.setVisible(true);

        } else if (level.equals("ERROR")) {

            statusLabel.setText("An error occured, please try again");
            statusLabel.setForeground(Color.red);
            statusLabel.setVisible(true);

        } else if(level.equals("Customer")){
        
            store.open();
            this.dispose();
        
        } else if(level.equals("Manager")) {

            try {
                managerPage.open();
            } catch (ParseException ex) {
                Logger.getLogger(LogonPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            this.dispose();

        }
    }//GEN-LAST:event_loginButtonActionPerformed

    /**
     * //@param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(LogonPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(LogonPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(LogonPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(LogonPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new LogonPage().setVisible(true);
//            }
//        });
//    }
    public void setup() {

        //Setup the other frames and panels here
    }

    public void run() {

        usernameField.setText("");
        passwordField.setText("");

        statusLabel.setVisible(false);

        //Change the current Page variable
        Methods.currentPage = "Login";

        //lets clear some uneccessary lists to save memory
        Lists.games.clear();
        Lists.users.clear();
        Lists.discounts.clear();
        Lists.cart.clear();

        this.setVisible(true);

    }

    //a method tos et the status text incase were coming from another page
    public void setStatus(String text) {

        statusLabel.setForeground(Color.green);
        statusLabel.setText(text);
        statusLabel.setVisible(true);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JLabel Instructions;
    javax.swing.JLabel Level;
    javax.swing.JLabel createAccountLabel;
    javax.swing.JLabel forgetPasswordLabel;
    javax.swing.JButton guestButton;
    javax.swing.JLabel iconHolder;
    javax.swing.JPanel jPanel1;
    javax.swing.JDialog loggedIn;
    javax.swing.JButton loginButton;
    javax.swing.JPanel loginPanel;
    javax.swing.JPasswordField passwordField;
    javax.swing.JLabel passwordLabel;
    javax.swing.JLabel showLabel;
    javax.swing.JLabel statusLabel;
    javax.swing.JLabel title;
    javax.swing.JTextField usernameField;
    javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
