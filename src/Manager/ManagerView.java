/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Manager;

import Customer.StorePage;
import SQL.SQLImages;
import Global.Discount;
import Global.Game;
import Global.GameDisplay;
import Global.Lists;
import Global.Methods;
import Global.User;
import Global.Variables;
import Login.LogonPage;
import Manager.CreateAccountPage;
import SQL.SQLManager;
import SQL.SQLStore;
import com.mysql.cj.jdbc.Blob;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author karso
 */
public class ManagerView extends javax.swing.JFrame {

    /**
     * Creates new form ManagerView
     */
    LogonPage login = null;
    StorePage store = null;

    ArrayList<JLabel> errorMessages = new ArrayList<JLabel>();

    //another array list for error messages but for discounts
    ArrayList<JLabel> discountEditErrors = new ArrayList<JLabel>();
    ArrayList<JLabel> discountCreateErrors = new ArrayList<JLabel>();

    //this is an array list for invetory errors
    ArrayList<JLabel> inventoryErrors = new ArrayList<JLabel>();

    public ManagerView(LogonPage login, StorePage store) {
        initComponents();

        this.login = login;
        this.store = store;

        //now add all error messages
        errorMessages.add(usernameError);
        errorMessages.add(passwordError);
        errorMessages.add(nameError);
        errorMessages.add(addressError);
        errorMessages.add(cityError);
        errorMessages.add(stateError);
        errorMessages.add(zipError);
        errorMessages.add(emailError);
        errorMessages.add(phoneError);

        discountEditErrors.add(codeEditError);
        discountEditErrors.add(descEditError);
        discountEditErrors.add(amountEditError);

        discountCreateErrors.add(codeError);
        discountCreateErrors.add(descError);
        discountCreateErrors.add(amountError);
        discountCreateErrors.add(gameError);

        inventoryErrors.add(editTitleError);
        inventoryErrors.add(editDescError);
        inventoryErrors.add(priceError);
        
        userDisplay.getVerticalScrollBar().setUnitIncrement(16);
        itemDisplay.getVerticalScrollBar().setUnitIncrement(16);
        shownDiscounts.getVerticalScrollBar().setUnitIncrement(16);

    }

    //a couple booleans to help with searching users
    public boolean customerSearch = true;
    public boolean managerSearch = true;
    public boolean disabledAccountSearch = true;
    private boolean enabledAccountSearch = true;

    //array list to help us validate the user information
    ArrayList<String> usernameErrors = new ArrayList<String>();
    ArrayList<String> passwordErrors = new ArrayList<String>();
    ArrayList<String> nameErrors = new ArrayList<String>();
    ArrayList<String> addressErrors = new ArrayList<String>();
    ArrayList<String> cityErrors = new ArrayList<String>();
    ArrayList<String> stateErrors = new ArrayList<String>();
    ArrayList<String> zipErrors = new ArrayList<String>();
    ArrayList<String> emailErrors = new ArrayList<String>();
    ArrayList<String> phoneErrors = new ArrayList<String>();

    //an array list to keep track of displayed users
    ArrayList<User> displayedUsers = new ArrayList<User>();

    //other pages
    CreateAccountPage createAccount = new CreateAccountPage(this);
    NotificationsView notifs = new NotificationsView(this);
    PastTransactions pastPurchases = new PastTransactions(this);
    CreateGame gameCreate = new CreateGame(this);

    //DISCOUNTS
    //A couple booleans to help with searching discounts
    public boolean expiredSearch = true;
    public boolean unexpiredSearch = true;
    public boolean activeSearch = true;
    public boolean inactiveSearch = true;

    //some array lists to help with errors
    //only three error types will use them because they are the only ones used twice
    ArrayList<String> codeErrors = new ArrayList<String>();
    ArrayList<String> amountErrors = new ArrayList<String>();
    ArrayList<String> descErrors = new ArrayList<String>();

    //some date variables
    LocalDateTime currentDate = LocalDateTime.now();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    LocalDateTime selectedStartDate = currentDate;
    LocalDateTime selectedEndDate = selectedStartDate.plusDays(1);

    String discountType = "Percent";
    String discountLevel = "Cart";

    //an array list to store the displayed discounts
    ArrayList<Discount> displayedDiscounts = new ArrayList<Discount>();

    //Al of things for inventory we can just copy and paste from the store page
    //and edit
    //most of the ui is and fucntionality is remaing the same so no need to remake all of it
    Dimension originalSize = new Dimension(898, 400);

    //am array list for the game display
    ArrayList<GameDisplay> gamesDisplayed = new ArrayList<GameDisplay>();

    //an index for the game display
    int selectedGame = 0;
    int previousIndex = 0;

    //an array list for storing filters
    ArrayList<JCheckBox> consoleFiltersList = new ArrayList<JCheckBox>();
    ArrayList<JCheckBox> genreFiltersList = new ArrayList<JCheckBox>();

    static int index = 0;

    //a boolean to check for if we want to see disabled games
    public boolean disabledGameCheck = true;

    GregorianCalendar newDate = new GregorianCalendar();

    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    //an array list for errors
    ArrayList<String> priceErrors = new ArrayList<String>();

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        inventoryMenu = new javax.swing.JPanel();
        controlPanelInventory = new javax.swing.JPanel();
        refreshButton = new javax.swing.JButton();
        quitButton = new javax.swing.JButton();
        newGameButton = new javax.swing.JButton();
        logOutButton = new javax.swing.JButton();
        controls3 = new javax.swing.JLabel();
        controls2 = new javax.swing.JLabel();
        controls1 = new javax.swing.JLabel();
        controlsLabel = new javax.swing.JLabel();
        notificationsButton = new javax.swing.JButton();
        notifAmount = new javax.swing.JLabel();
        inventoryReportBttuon = new javax.swing.JButton();
        salesReportPanel = new javax.swing.JPanel();
        minMonth = new javax.swing.JComboBox<>();
        minDay = new javax.swing.JComboBox<>();
        minYear = new javax.swing.JComboBox<>();
        maxYear = new javax.swing.JComboBox<>();
        maxMonth = new javax.swing.JComboBox<>();
        maxDay = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        salesReportButton = new javax.swing.JButton();
        helpButton = new javax.swing.JButton();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        inventoryFiltersPanel = new javax.swing.JPanel();
        consoleLabel = new javax.swing.JLabel();
        genreLabel = new javax.swing.JLabel();
        genreAll = new javax.swing.JButton();
        consoleAll = new javax.swing.JButton();
        filterInstructions = new javax.swing.JLabel();
        consoleFilters = new javax.swing.JScrollPane();
        consoleFiltersPanel = new javax.swing.JPanel();
        genreFilters = new javax.swing.JScrollPane();
        genreFiltersPanel = new javax.swing.JPanel();
        searchPanel = new javax.swing.JPanel();
        searchBar = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        filtersButton = new javax.swing.JToggleButton();
        disabledGamesCheck = new javax.swing.JCheckBox();
        itemDisplay = new javax.swing.JScrollPane();
        itemContainer = new javax.swing.JPanel();
        contextPanel = new javax.swing.JPanel();
        panelContainers = new javax.swing.JLayeredPane();
        gamePanel = new javax.swing.JPanel();
        image = new javax.swing.JLabel();
        quantityAvailable = new javax.swing.JSpinner();
        amountBuyingLabel = new javax.swing.JLabel();
        returnButton = new javax.swing.JButton();
        infoContainer = new javax.swing.JLayeredPane();
        gameTitle = new javax.swing.JTextArea();
        gameSystem = new javax.swing.JLabel();
        gameGenre = new javax.swing.JLabel();
        editTitleError = new javax.swing.JLabel();
        warning1 = new javax.swing.JLabel();
        warning2 = new javax.swing.JLabel();
        gameDescription = new javax.swing.JTextArea();
        priceField = new javax.swing.JTextField();
        priceLabel = new javax.swing.JLabel();
        restockLabel = new javax.swing.JLabel();
        restockThreshold = new javax.swing.JSpinner();
        gameSaveButton = new javax.swing.JButton();
        thumbnailButton = new javax.swing.JButton();
        mainImageButton = new javax.swing.JButton();
        gameEnabledBox = new javax.swing.JCheckBox();
        editDescError = new javax.swing.JLabel();
        descLabel = new javax.swing.JLabel();
        imageWarning = new javax.swing.JLabel();
        imageInstruction = new javax.swing.JLabel();
        priceError = new javax.swing.JLabel();
        usersMenu = new javax.swing.JPanel();
        controlPanelUsers = new javax.swing.JPanel();
        refreshButton1 = new javax.swing.JButton();
        quitButton1 = new javax.swing.JButton();
        createButton = new javax.swing.JButton();
        logOutButton1 = new javax.swing.JButton();
        helpButton1 = new javax.swing.JButton();
        usersShown = new javax.swing.JPanel();
        filtersPanel = new javax.swing.JPanel();
        customerBox = new javax.swing.JCheckBox();
        managerBox = new javax.swing.JCheckBox();
        disabledAccount = new javax.swing.JCheckBox();
        enabledAccount = new javax.swing.JCheckBox();
        userDisplay = new javax.swing.JScrollPane();
        userTable = new javax.swing.JTable();
        userInfo = new javax.swing.JLayeredPane();
        selectedUser = new javax.swing.JPanel();
        levelLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        showLabel = new javax.swing.JLabel();
        firstLabel = new javax.swing.JLabel();
        lastLabel = new javax.swing.JLabel();
        firstField = new javax.swing.JTextField();
        lastField = new javax.swing.JTextField();
        emailLabel = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        line1Label = new javax.swing.JLabel();
        line1 = new javax.swing.JTextField();
        line2 = new javax.swing.JTextField();
        line2Label = new javax.swing.JLabel();
        line3 = new javax.swing.JTextField();
        line3Label = new javax.swing.JLabel();
        phoneLabel = new javax.swing.JLabel();
        phoneField = new javax.swing.JTextField();
        saveButton = new javax.swing.JButton();
        enabledBox = new javax.swing.JCheckBox();
        passwordError = new javax.swing.JLabel();
        usernameError = new javax.swing.JLabel();
        nameError = new javax.swing.JLabel();
        addressError = new javax.swing.JLabel();
        emailError = new javax.swing.JLabel();
        phoneError = new javax.swing.JLabel();
        cityField = new javax.swing.JTextField();
        cityLabel = new javax.swing.JLabel();
        stateBox = new javax.swing.JComboBox<>();
        stateLabel = new javax.swing.JLabel();
        zipLabel = new javax.swing.JLabel();
        zipField = new javax.swing.JTextField();
        cityError = new javax.swing.JLabel();
        stateError = new javax.swing.JLabel();
        zipError = new javax.swing.JLabel();
        returnUserButton = new javax.swing.JButton();
        userErrorInstruction = new javax.swing.JLabel();
        userErrorLabel = new javax.swing.JLabel();
        userSuccessLabel = new javax.swing.JLabel();
        posButton = new javax.swing.JButton();
        viewButton = new javax.swing.JButton();
        discountsMenu = new javax.swing.JPanel();
        controlPanelDiscounts = new javax.swing.JPanel();
        discountRefresh = new javax.swing.JButton();
        quitButton2 = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        logOutButton2 = new javax.swing.JButton();
        helpButton2 = new javax.swing.JButton();
        filtersPanelDiscounts = new javax.swing.JPanel();
        expiredBox = new javax.swing.JCheckBox();
        unexpiredBox = new javax.swing.JCheckBox();
        inactiveCheck = new javax.swing.JCheckBox();
        activeDiscountCheck = new javax.swing.JCheckBox();
        shownDiscounts = new javax.swing.JScrollPane();
        discountsTable = new javax.swing.JTable();
        displayDiscounts = new javax.swing.JLayeredPane();
        createDiscounts = new javax.swing.JPanel();
        chooseType = new javax.swing.JLabel();
        percentButton = new javax.swing.JButton();
        dollarButton = new javax.swing.JButton();
        typeChosen = new javax.swing.JLabel();
        createAmountLabel = new javax.swing.JLabel();
        amountField = new javax.swing.JTextField();
        formatSuggestion = new javax.swing.JLabel();
        createCodeLabel = new javax.swing.JLabel();
        createCodeField = new javax.swing.JTextField();
        createDiscountButton = new javax.swing.JButton();
        exitCreateButton = new javax.swing.JButton();
        activeBox = new javax.swing.JCheckBox();
        createDescriptionLabel = new javax.swing.JLabel();
        descriptionField = new javax.swing.JTextArea();
        startDateLabel = new javax.swing.JLabel();
        startDateArrows = new javax.swing.JSpinner();
        endDateArrows = new javax.swing.JSpinner();
        endDateLabel = new javax.swing.JLabel();
        startDateField = new javax.swing.JTextField();
        endDateField = new javax.swing.JTextField();
        discountErrorLabel = new javax.swing.JLabel();
        discountErrorInstruction = new javax.swing.JLabel();
        levelChosen = new javax.swing.JLabel();
        cartButton = new javax.swing.JButton();
        itemButton = new javax.swing.JButton();
        chooseLevel = new javax.swing.JLabel();
        gameBox = new javax.swing.JComboBox<>();
        gameLabelDiscount = new javax.swing.JLabel();
        gameError = new javax.swing.JLabel();
        amountError = new javax.swing.JLabel();
        descError = new javax.swing.JLabel();
        codeError = new javax.swing.JLabel();
        selectedDiscount = new javax.swing.JPanel();
        saveButtonDiscounts = new javax.swing.JButton();
        activeCheck = new javax.swing.JCheckBox();
        typeLabel = new javax.swing.JLabel();
        codeLabel = new javax.swing.JLabel();
        codeField = new javax.swing.JTextField();
        amountLabel = new javax.swing.JLabel();
        startLabel = new javax.swing.JLabel();
        dateStart = new javax.swing.JLabel();
        endLabel = new javax.swing.JLabel();
        dateEnd = new javax.swing.JLabel();
        satusLabel = new javax.swing.JLabel();
        currentStatus = new javax.swing.JLabel();
        descriptionLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptionText = new javax.swing.JTextArea();
        discountErrorInstruction1 = new javax.swing.JLabel();
        discountErrorLabel1 = new javax.swing.JLabel();
        successLabel = new javax.swing.JLabel();
        discountReturnButton = new javax.swing.JButton();
        amountOff = new javax.swing.JTextField();
        amountEditError = new javax.swing.JLabel();
        codeEditError = new javax.swing.JLabel();
        descEditError = new javax.swing.JLabel();
        gameDiscountedLabel = new javax.swing.JLabel();
        gameDiscountedTitle = new javax.swing.JLabel();
        formatSuggestion1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/UsedGamesLogo.png")).getImage());

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        controlPanelInventory.setBackground(new java.awt.Color(65, 146, 217));
        controlPanelInventory.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        refreshButton.setBackground(new java.awt.Color(0, 48, 90));
        refreshButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        refreshButton.setForeground(new java.awt.Color(255, 255, 255));
        refreshButton.setText("Refresh");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        quitButton.setBackground(new java.awt.Color(0, 48, 90));
        quitButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        quitButton.setForeground(new java.awt.Color(255, 255, 255));
        quitButton.setText("Quit");
        quitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitButtonActionPerformed(evt);
            }
        });

        newGameButton.setBackground(new java.awt.Color(0, 48, 90));
        newGameButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        newGameButton.setForeground(new java.awt.Color(255, 255, 255));
        newGameButton.setText("Add Game");
        newGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGameButtonActionPerformed(evt);
            }
        });

        logOutButton.setBackground(new java.awt.Color(0, 48, 90));
        logOutButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        logOutButton.setForeground(new java.awt.Color(255, 255, 255));
        logOutButton.setText("Log Out");
        logOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutButtonActionPerformed(evt);
            }
        });

        controls3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        controls3.setText("SPACE to select");

        controls2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        controls2.setText("Arrow keys to scroll");

        controls1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        controls1.setText("WASD to move");

        controlsLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        controlsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        controlsLabel.setText("Controls");

        notificationsButton.setBackground(new java.awt.Color(0, 48, 90));
        notificationsButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        notificationsButton.setForeground(new java.awt.Color(255, 255, 255));
        notificationsButton.setLabel("Notifications");
        notificationsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notificationsButtonActionPerformed(evt);
            }
        });

        notifAmount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        notifAmount.setText("Notifications: ");
        notifAmount.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        inventoryReportBttuon.setBackground(new java.awt.Color(0, 48, 90));
        inventoryReportBttuon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        inventoryReportBttuon.setForeground(new java.awt.Color(255, 255, 255));
        inventoryReportBttuon.setText("Inventory Report");
        inventoryReportBttuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inventoryReportBttuonActionPerformed(evt);
            }
        });

        salesReportPanel.setBackground(new java.awt.Color(65, 146, 217));

        minMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        minMonth.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                minMonthItemStateChanged(evt);
            }
        });

        minDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        minYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2021", "2022", "2023", "2024", "2025", "2026" }));

        maxYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2021", "2022", "2023", "2024", "2025", "2026" }));

        maxMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        maxMonth.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                maxMonthItemStateChanged(evt);
            }
        });

        maxDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Start");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("End");

        salesReportButton.setBackground(new java.awt.Color(0, 48, 90));
        salesReportButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        salesReportButton.setForeground(new java.awt.Color(255, 255, 255));
        salesReportButton.setText("Sales Report");
        salesReportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salesReportButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout salesReportPanelLayout = new javax.swing.GroupLayout(salesReportPanel);
        salesReportPanel.setLayout(salesReportPanelLayout);
        salesReportPanelLayout.setHorizontalGroup(
            salesReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(salesReportPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(salesReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(minYear, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(salesReportPanelLayout.createSequentialGroup()
                        .addComponent(minMonth, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(minDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(maxYear, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(salesReportPanelLayout.createSequentialGroup()
                        .addComponent(maxMonth, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(maxDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(salesReportButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        salesReportPanelLayout.setVerticalGroup(
            salesReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(salesReportPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(salesReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(minMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(minYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(salesReportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maxMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maxDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(maxYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(salesReportButton)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        helpButton.setBackground(new java.awt.Color(0, 48, 90));
        helpButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        helpButton.setForeground(new java.awt.Color(255, 255, 255));
        helpButton.setText("Help");
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout controlPanelInventoryLayout = new javax.swing.GroupLayout(controlPanelInventory);
        controlPanelInventory.setLayout(controlPanelInventoryLayout);
        controlPanelInventoryLayout.setHorizontalGroup(
            controlPanelInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(refreshButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(quitButton, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
            .addComponent(newGameButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(logOutButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(notificationsButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(inventoryReportBttuon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(salesReportPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, controlPanelInventoryLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(controlPanelInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(notifAmount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(controls3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(controls2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(controlsLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(controls1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(helpButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        controlPanelInventoryLayout.setVerticalGroup(
            controlPanelInventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, controlPanelInventoryLayout.createSequentialGroup()
                .addComponent(newGameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(helpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(controlsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(controls1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(controls2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(controls3)
                .addGap(18, 18, 18)
                .addComponent(notificationsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(notifAmount)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(inventoryReportBttuon, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(salesReportPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(quitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        inventoryFiltersPanel.setBackground(new java.awt.Color(65, 146, 217));
        inventoryFiltersPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        consoleLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        consoleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        consoleLabel.setText("Console Filters");

        genreLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        genreLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        genreLabel.setText("Genre Filters");

        genreAll.setBackground(new java.awt.Color(0, 48, 90));
        genreAll.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        genreAll.setForeground(new java.awt.Color(255, 255, 255));
        genreAll.setText("Check All");
        genreAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genreAllActionPerformed(evt);
            }
        });

        consoleAll.setBackground(new java.awt.Color(0, 48, 90));
        consoleAll.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        consoleAll.setForeground(new java.awt.Color(255, 255, 255));
        consoleAll.setText("Check All");
        consoleAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consoleAllActionPerformed(evt);
            }
        });

        filterInstructions.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        filterInstructions.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        filterInstructions.setText("Click to toggle filters");

        consoleFilters.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout consoleFiltersPanelLayout = new javax.swing.GroupLayout(consoleFiltersPanel);
        consoleFiltersPanel.setLayout(consoleFiltersPanelLayout);
        consoleFiltersPanelLayout.setHorizontalGroup(
            consoleFiltersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 191, Short.MAX_VALUE)
        );
        consoleFiltersPanelLayout.setVerticalGroup(
            consoleFiltersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 216, Short.MAX_VALUE)
        );

        consoleFilters.setViewportView(consoleFiltersPanel);

        genreFilters.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout genreFiltersPanelLayout = new javax.swing.GroupLayout(genreFiltersPanel);
        genreFiltersPanel.setLayout(genreFiltersPanelLayout);
        genreFiltersPanelLayout.setHorizontalGroup(
            genreFiltersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 191, Short.MAX_VALUE)
        );
        genreFiltersPanelLayout.setVerticalGroup(
            genreFiltersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 216, Short.MAX_VALUE)
        );

        genreFilters.setViewportView(genreFiltersPanel);

        javax.swing.GroupLayout inventoryFiltersPanelLayout = new javax.swing.GroupLayout(inventoryFiltersPanel);
        inventoryFiltersPanel.setLayout(inventoryFiltersPanelLayout);
        inventoryFiltersPanelLayout.setHorizontalGroup(
            inventoryFiltersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventoryFiltersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(inventoryFiltersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(consoleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(genreLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(genreAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(consoleAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(filterInstructions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(consoleFilters)
                    .addComponent(genreFilters, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        inventoryFiltersPanelLayout.setVerticalGroup(
            inventoryFiltersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventoryFiltersPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(filterInstructions)
                .addGap(18, 18, 18)
                .addComponent(consoleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(consoleAll)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(consoleFilters, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(genreLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(genreAll)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(genreFilters, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        searchPanel.setBackground(new java.awt.Color(122, 186, 242));
        searchPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        searchButton.setBackground(new java.awt.Color(0, 48, 90));
        searchButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        searchButton.setForeground(new java.awt.Color(255, 255, 255));
        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        filtersButton.setBackground(new java.awt.Color(0, 48, 90));
        filtersButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        filtersButton.setForeground(new java.awt.Color(255, 255, 255));
        filtersButton.setText("Browse Filters");
        filtersButton.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                filtersButtonItemStateChanged(evt);
            }
        });
        filtersButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                filtersButtonKeyPressed(evt);
            }
        });

        disabledGamesCheck.setBackground(new java.awt.Color(122, 186, 242));
        disabledGamesCheck.setSelected(true);
        disabledGamesCheck.setText("Check for disabled games");
        disabledGamesCheck.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                disabledGamesCheckItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(disabledGamesCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(filtersButton)
                .addContainerGap())
        );
        searchPanelLayout.setVerticalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton)
                    .addComponent(filtersButton)
                    .addComponent(disabledGamesCheck))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        itemDisplay.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        itemDisplay.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        itemDisplay.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                itemDisplayFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                itemDisplayFocusLost(evt);
            }
        });
        itemDisplay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                itemDisplayKeyPressed(evt);
            }
        });

        itemContainer.setBackground(new java.awt.Color(0, 75, 141));
        itemContainer.setPreferredSize(new java.awt.Dimension(898, 571));

        javax.swing.GroupLayout itemContainerLayout = new javax.swing.GroupLayout(itemContainer);
        itemContainer.setLayout(itemContainerLayout);
        itemContainerLayout.setHorizontalGroup(
            itemContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 898, Short.MAX_VALUE)
        );
        itemContainerLayout.setVerticalGroup(
            itemContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 752, Short.MAX_VALUE)
        );

        itemDisplay.setViewportView(itemContainer);

        jLayeredPane1.setLayer(inventoryFiltersPanel, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(searchPanel, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(itemDisplay, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(itemDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
                    .addComponent(searchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                    .addContainerGap(573, Short.MAX_VALUE)
                    .addComponent(inventoryFiltersPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(itemDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addGap(45, 45, 45)
                    .addComponent(inventoryFiltersPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(126, Short.MAX_VALUE)))
        );

        gamePanel.setBackground(new java.awt.Color(65, 146, 217));

        image.setBackground(new java.awt.Color(51, 51, 51));
        image.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MainImagePlaceholder.png"))); // NOI18N
        image.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        quantityAvailable.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                quantityAvailableStateChanged(evt);
            }
        });

        amountBuyingLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        amountBuyingLabel.setText("Quantity");

        returnButton.setBackground(new java.awt.Color(0, 48, 90));
        returnButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        returnButton.setForeground(new java.awt.Color(255, 255, 255));
        returnButton.setText("Return");
        returnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnButtonActionPerformed(evt);
            }
        });

        gameTitle.setColumns(20);
        gameTitle.setLineWrap(true);
        gameTitle.setRows(5);
        gameTitle.setWrapStyleWord(true);
        gameTitle.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                gameTitleKeyReleased(evt);
            }
        });

        gameSystem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gameSystem.setText("System");

        gameGenre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gameGenre.setText("Genre");

        editTitleError.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        editTitleError.setForeground(java.awt.Color.red);
        editTitleError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        editTitleError.setText("Title is required");

        infoContainer.setLayer(gameTitle, javax.swing.JLayeredPane.DEFAULT_LAYER);
        infoContainer.setLayer(gameSystem, javax.swing.JLayeredPane.DEFAULT_LAYER);
        infoContainer.setLayer(gameGenre, javax.swing.JLayeredPane.DEFAULT_LAYER);
        infoContainer.setLayer(editTitleError, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout infoContainerLayout = new javax.swing.GroupLayout(infoContainer);
        infoContainer.setLayout(infoContainerLayout);
        infoContainerLayout.setHorizontalGroup(
            infoContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoContainerLayout.createSequentialGroup()
                .addGroup(infoContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(infoContainerLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(infoContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(editTitleError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(gameTitle)))
                    .addGroup(infoContainerLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(gameSystem, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(gameGenre, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addContainerGap())
        );
        infoContainerLayout.setVerticalGroup(
            infoContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoContainerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(gameTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editTitleError)
                .addGap(5, 5, 5)
                .addGroup(infoContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gameSystem, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gameGenre, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62))
        );

        warning1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        warning1.setForeground(java.awt.Color.red);
        warning1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        warning1.setText("An Error Occured");

        warning2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        warning2.setForeground(java.awt.Color.red);
        warning2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        warning2.setText("Please try again or refresh");

        gameDescription.setColumns(20);
        gameDescription.setLineWrap(true);
        gameDescription.setRows(5);
        gameDescription.setToolTipText("");
        gameDescription.setWrapStyleWord(true);
        gameDescription.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                gameDescriptionKeyReleased(evt);
            }
        });

        priceField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                priceFieldKeyReleased(evt);
            }
        });

        priceLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        priceLabel.setText("Price");

        restockLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        restockLabel.setText("Restock Threshold");

        restockThreshold.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                restockThresholdStateChanged(evt);
            }
        });

        gameSaveButton.setBackground(new java.awt.Color(0, 48, 90));
        gameSaveButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        gameSaveButton.setForeground(new java.awt.Color(255, 255, 255));
        gameSaveButton.setText("Save");
        gameSaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gameSaveButtonActionPerformed(evt);
            }
        });

        thumbnailButton.setBackground(new java.awt.Color(0, 48, 90));
        thumbnailButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        thumbnailButton.setForeground(new java.awt.Color(255, 255, 255));
        thumbnailButton.setText("Upload Thumbnail");
        thumbnailButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thumbnailButtonActionPerformed(evt);
            }
        });

        mainImageButton.setBackground(new java.awt.Color(0, 48, 90));
        mainImageButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        mainImageButton.setForeground(new java.awt.Color(255, 255, 255));
        mainImageButton.setText("Upload Main Image");
        mainImageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainImageButtonActionPerformed(evt);
            }
        });

        gameEnabledBox.setBackground(new java.awt.Color(65, 146, 217));
        gameEnabledBox.setText("Enabled");

        editDescError.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        editDescError.setForeground(java.awt.Color.red);
        editDescError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        editDescError.setText("Description is Required");

        descLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        descLabel.setText("Description");

        imageWarning.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        imageWarning.setForeground(java.awt.Color.red);
        imageWarning.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageWarning.setText("Image didnt upload");

        imageInstruction.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        imageInstruction.setForeground(java.awt.Color.red);
        imageInstruction.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageInstruction.setText("Try Again");

        priceError.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        priceError.setForeground(java.awt.Color.red);
        priceError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        priceError.setText("Price is required");

        javax.swing.GroupLayout gamePanelLayout = new javax.swing.GroupLayout(gamePanel);
        gamePanel.setLayout(gamePanelLayout);
        gamePanelLayout.setHorizontalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gamePanelLayout.createSequentialGroup()
                .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imageWarning, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(priceError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(amountBuyingLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(quantityAvailable, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(priceField)
                    .addComponent(priceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(restockThreshold)
                    .addComponent(restockLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(gameDescription, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(descLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editDescError, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(imageInstruction, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(gamePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(infoContainer)
                            .addComponent(returnButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(warning1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(warning2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(gameSaveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(thumbnailButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mainImageButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(gamePanelLayout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addComponent(gameEnabledBox, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        gamePanelLayout.setVerticalGroup(
            gamePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gamePanelLayout.createSequentialGroup()
                .addComponent(image, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(infoContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(descLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gameDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(editDescError)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(priceLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(priceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(priceError)
                .addGap(4, 4, 4)
                .addComponent(amountBuyingLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(quantityAvailable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(restockLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(restockThreshold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(warning1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(warning2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(thumbnailButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainImageButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imageWarning)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imageInstruction)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(gameEnabledBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(gameSaveButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(returnButton)
                .addContainerGap())
        );

        panelContainers.setLayer(gamePanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout panelContainersLayout = new javax.swing.GroupLayout(panelContainers);
        panelContainers.setLayout(panelContainersLayout);
        panelContainersLayout.setHorizontalGroup(
            panelContainersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gamePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelContainersLayout.setVerticalGroup(
            panelContainersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gamePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout contextPanelLayout = new javax.swing.GroupLayout(contextPanel);
        contextPanel.setLayout(contextPanelLayout);
        contextPanelLayout.setHorizontalGroup(
            contextPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelContainers)
        );
        contextPanelLayout.setVerticalGroup(
            contextPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelContainers)
        );

        javax.swing.GroupLayout inventoryMenuLayout = new javax.swing.GroupLayout(inventoryMenu);
        inventoryMenu.setLayout(inventoryMenuLayout);
        inventoryMenuLayout.setHorizontalGroup(
            inventoryMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventoryMenuLayout.createSequentialGroup()
                .addComponent(controlPanelInventory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(457, Short.MAX_VALUE))
            .addGroup(inventoryMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(inventoryMenuLayout.createSequentialGroup()
                    .addGap(968, 968, 968)
                    .addComponent(contextPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        inventoryMenuLayout.setVerticalGroup(
            inventoryMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(controlPanelInventory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(inventoryMenuLayout.createSequentialGroup()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 103, Short.MAX_VALUE))
            .addGroup(inventoryMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(contextPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Inventory", inventoryMenu);

        controlPanelUsers.setBackground(new java.awt.Color(65, 146, 217));
        controlPanelUsers.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        refreshButton1.setBackground(new java.awt.Color(0, 48, 90));
        refreshButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        refreshButton1.setForeground(new java.awt.Color(255, 255, 255));
        refreshButton1.setText("Refresh");
        refreshButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButton1ActionPerformed(evt);
            }
        });

        quitButton1.setBackground(new java.awt.Color(0, 48, 90));
        quitButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        quitButton1.setForeground(new java.awt.Color(255, 255, 255));
        quitButton1.setText("Quit");
        quitButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitButton1ActionPerformed(evt);
            }
        });

        createButton.setBackground(new java.awt.Color(0, 48, 90));
        createButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        createButton.setForeground(new java.awt.Color(255, 255, 255));
        createButton.setText("Create User");
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonActionPerformed(evt);
            }
        });

        logOutButton1.setBackground(new java.awt.Color(0, 48, 90));
        logOutButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        logOutButton1.setForeground(new java.awt.Color(255, 255, 255));
        logOutButton1.setText("Log Out");
        logOutButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutButton1ActionPerformed(evt);
            }
        });

        helpButton1.setBackground(new java.awt.Color(0, 48, 90));
        helpButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        helpButton1.setForeground(new java.awt.Color(255, 255, 255));
        helpButton1.setText("Help");
        helpButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout controlPanelUsersLayout = new javax.swing.GroupLayout(controlPanelUsers);
        controlPanelUsers.setLayout(controlPanelUsersLayout);
        controlPanelUsersLayout.setHorizontalGroup(
            controlPanelUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(refreshButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(quitButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
            .addComponent(createButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(logOutButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(helpButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        controlPanelUsersLayout.setVerticalGroup(
            controlPanelUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, controlPanelUsersLayout.createSequentialGroup()
                .addComponent(createButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(helpButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(refreshButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logOutButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(quitButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        filtersPanel.setBackground(new java.awt.Color(122, 186, 242));
        filtersPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        customerBox.setBackground(new java.awt.Color(122, 186, 242));
        customerBox.setSelected(true);
        customerBox.setText("Customers");
        customerBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                customerBoxItemStateChanged(evt);
            }
        });

        managerBox.setBackground(new java.awt.Color(122, 186, 242));
        managerBox.setSelected(true);
        managerBox.setText("Managers");
        managerBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                managerBoxItemStateChanged(evt);
            }
        });
        managerBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                managerBoxKeyPressed(evt);
            }
        });

        disabledAccount.setBackground(new java.awt.Color(122, 186, 242));
        disabledAccount.setSelected(true);
        disabledAccount.setText("Disabled");
        disabledAccount.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                disabledAccountItemStateChanged(evt);
            }
        });

        enabledAccount.setBackground(new java.awt.Color(122, 186, 242));
        enabledAccount.setSelected(true);
        enabledAccount.setText("Enabled");
        enabledAccount.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                enabledAccountItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout filtersPanelLayout = new javax.swing.GroupLayout(filtersPanel);
        filtersPanel.setLayout(filtersPanelLayout);
        filtersPanelLayout.setHorizontalGroup(
            filtersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filtersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(disabledAccount)
                .addGap(3, 3, 3)
                .addComponent(enabledAccount)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(customerBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(managerBox)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        filtersPanelLayout.setVerticalGroup(
            filtersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filtersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(customerBox, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                .addComponent(managerBox)
                .addComponent(disabledAccount)
                .addComponent(enabledAccount))
        );

        userTable.setBackground(new java.awt.Color(122, 186, 242));
        userTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Username", "First Name", "Last Name", "Phone Number"
            }
        ));
        userTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        userTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        userTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userTableMouseClicked(evt);
            }
        });
        userTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                userTableKeyPressed(evt);
            }
        });
        userDisplay.setViewportView(userTable);

        javax.swing.GroupLayout usersShownLayout = new javax.swing.GroupLayout(usersShown);
        usersShown.setLayout(usersShownLayout);
        usersShownLayout.setHorizontalGroup(
            usersShownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(filtersPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(userDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
        );
        usersShownLayout.setVerticalGroup(
            usersShownLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(usersShownLayout.createSequentialGroup()
                .addComponent(filtersPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userDisplay)
                .addContainerGap())
        );

        selectedUser.setBackground(new java.awt.Color(65, 146, 217));

        levelLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        levelLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        levelLabel.setText("Level");

        usernameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        usernameLabel.setText("Username");

        usernameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                usernameFieldKeyReleased(evt);
            }
        });

        passwordLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        passwordLabel.setText("Password");

        passwordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                passwordFieldKeyReleased(evt);
            }
        });

        showLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        showLabel.setText("Show");
        showLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                showLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                showLabelMouseExited(evt);
            }
        });

        firstLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        firstLabel.setText("First Name");

        lastLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lastLabel.setText("Last Name");

        firstField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                firstFieldKeyReleased(evt);
            }
        });

        lastField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lastFieldKeyReleased(evt);
            }
        });

        emailLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        emailLabel.setText("Email");

        emailField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                emailFieldKeyReleased(evt);
            }
        });

        line1Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        line1Label.setText("Address 1");

        line1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                line1KeyReleased(evt);
            }
        });

        line2Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        line2Label.setText("Address 2");

        line3Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        line3Label.setText("Address 3");

        phoneLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        phoneLabel.setText("Phone Number");

        phoneField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                phoneFieldKeyReleased(evt);
            }
        });

        saveButton.setBackground(new java.awt.Color(0, 48, 90));
        saveButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        saveButton.setForeground(new java.awt.Color(255, 255, 255));
        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        enabledBox.setBackground(new java.awt.Color(65, 146, 217));
        enabledBox.setText("Enabled");

        passwordError.setForeground(java.awt.Color.red);
        passwordError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        passwordError.setText("error");

        usernameError.setForeground(java.awt.Color.red);
        usernameError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        usernameError.setText("error");

        nameError.setForeground(java.awt.Color.red);
        nameError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameError.setText("jLabel1");

        addressError.setForeground(java.awt.Color.red);
        addressError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addressError.setText("jLabel1");

        emailError.setForeground(java.awt.Color.red);
        emailError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        emailError.setText("jLabel1");

        phoneError.setForeground(java.awt.Color.red);
        phoneError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        phoneError.setText("jLabel1");

        cityField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cityFieldKeyReleased(evt);
            }
        });

        cityLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cityLabel.setText("City");

        stateBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "N/A", "AE", "AK", "AL", "AP", "AR", "AZ", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "IA", "ID", "IL", "IN", "KS", "KY", "LA", "MA", "MD", "ME", "MI", "MN", "MO", "MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VA", "VT", "WA", "WI", "WV", "WY" }));
        stateBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                stateBoxItemStateChanged(evt);
            }
        });

        stateLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        stateLabel.setText("State");

        zipLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        zipLabel.setText("Zipcode");

        zipField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                zipFieldKeyReleased(evt);
            }
        });

        cityError.setForeground(java.awt.Color.red);
        cityError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cityError.setText("jLabel1");

        stateError.setForeground(java.awt.Color.red);
        stateError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        stateError.setText("jLabel1");

        zipError.setForeground(java.awt.Color.red);
        zipError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        zipError.setText("jLabel1");

        returnUserButton.setBackground(new java.awt.Color(0, 48, 90));
        returnUserButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        returnUserButton.setForeground(new java.awt.Color(255, 255, 255));
        returnUserButton.setText("Return");
        returnUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnUserButtonActionPerformed(evt);
            }
        });

        userErrorInstruction.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        userErrorInstruction.setForeground(java.awt.Color.red);
        userErrorInstruction.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userErrorInstruction.setText("Please Try Again");

        userErrorLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        userErrorLabel.setForeground(java.awt.Color.red);
        userErrorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userErrorLabel.setText("An Error Occured");

        userSuccessLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        userSuccessLabel.setForeground(new java.awt.Color(0, 204, 153));
        userSuccessLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userSuccessLabel.setText("Editing Succeeded");

        posButton.setBackground(new java.awt.Color(0, 48, 90));
        posButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        posButton.setForeground(new java.awt.Color(255, 255, 255));
        posButton.setText("Go To POS");
        posButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                posButtonActionPerformed(evt);
            }
        });

        viewButton.setBackground(new java.awt.Color(0, 48, 90));
        viewButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        viewButton.setForeground(new java.awt.Color(255, 255, 255));
        viewButton.setText("View Past Transactions");
        viewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout selectedUserLayout = new javax.swing.GroupLayout(selectedUser);
        selectedUser.setLayout(selectedUserLayout);
        selectedUserLayout.setHorizontalGroup(
            selectedUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectedUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(selectedUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(levelLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, selectedUserLayout.createSequentialGroup()
                        .addGroup(selectedUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(passwordError, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(usernameError, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(usernameLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(usernameField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameError, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(passwordField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, selectedUserLayout.createSequentialGroup()
                                .addComponent(passwordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(showLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 55, Short.MAX_VALUE))
                            .addGroup(selectedUserLayout.createSequentialGroup()
                                .addGroup(selectedUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(firstField, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                                    .addComponent(firstLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(selectedUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lastLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lastField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(selectedUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(line1Label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(line2)
                            .addComponent(line2Label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(line3)
                            .addComponent(line3Label, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addressError, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(zipLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(selectedUserLayout.createSequentialGroup()
                                .addGroup(selectedUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cityField, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                                    .addComponent(cityLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(selectedUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(stateBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(stateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 59, Short.MAX_VALUE))
                            .addComponent(zipField)
                            .addComponent(line1, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(emailError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(emailLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(emailField)
                    .addComponent(phoneLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(phoneField)
                    .addComponent(phoneError, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(returnUserButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(userErrorInstruction, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, selectedUserLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(selectedUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cityError, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stateError, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(zipError, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(userErrorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(userSuccessLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(posButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(saveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(viewButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, selectedUserLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(enabledBox, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(341, 341, 341))
        );
        selectedUserLayout.setVerticalGroup(
            selectedUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectedUserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(levelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(selectedUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameLabel)
                    .addComponent(line1Label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(selectedUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(line1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(selectedUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(selectedUserLayout.createSequentialGroup()
                        .addComponent(usernameError)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(selectedUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(passwordLabel)
                            .addComponent(showLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(passwordError)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(selectedUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(firstLabel)
                            .addComponent(lastLabel))
                        .addGroup(selectedUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(firstField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lastField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addComponent(nameError))
                    .addGroup(selectedUserLayout.createSequentialGroup()
                        .addComponent(line2Label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(line2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(line3Label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(line3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addressError)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(selectedUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cityLabel)
                            .addComponent(stateLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(selectedUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stateBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(12, 12, 12)
                .addComponent(zipLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(zipField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cityError)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stateError)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(zipError)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(emailLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emailError)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(phoneLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(phoneField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(phoneError)
                .addGap(18, 18, 18)
                .addComponent(userSuccessLabel)
                .addGap(18, 18, 18)
                .addComponent(userErrorLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userErrorInstruction)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addComponent(enabledBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(posButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(returnUserButton)
                .addContainerGap())
        );

        userInfo.setLayer(selectedUser, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout userInfoLayout = new javax.swing.GroupLayout(userInfo);
        userInfo.setLayout(userInfoLayout);
        userInfoLayout.setHorizontalGroup(
            userInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(selectedUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        userInfoLayout.setVerticalGroup(
            userInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(selectedUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout usersMenuLayout = new javax.swing.GroupLayout(usersMenu);
        usersMenu.setLayout(usersMenuLayout);
        usersMenuLayout.setHorizontalGroup(
            usersMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(usersMenuLayout.createSequentialGroup()
                .addComponent(controlPanelUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usersShown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userInfo)
                .addContainerGap())
        );
        usersMenuLayout.setVerticalGroup(
            usersMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(controlPanelUsers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(usersShown, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, usersMenuLayout.createSequentialGroup()
                .addComponent(userInfo)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Users", usersMenu);

        controlPanelDiscounts.setBackground(new java.awt.Color(65, 146, 217));
        controlPanelDiscounts.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        discountRefresh.setBackground(new java.awt.Color(0, 48, 90));
        discountRefresh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        discountRefresh.setForeground(new java.awt.Color(255, 255, 255));
        discountRefresh.setText("Refresh");
        discountRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discountRefreshActionPerformed(evt);
            }
        });

        quitButton2.setBackground(new java.awt.Color(0, 48, 90));
        quitButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        quitButton2.setForeground(new java.awt.Color(255, 255, 255));
        quitButton2.setText("Quit");
        quitButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitButton2ActionPerformed(evt);
            }
        });

        addButton.setBackground(new java.awt.Color(0, 48, 90));
        addButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addButton.setForeground(new java.awt.Color(255, 255, 255));
        addButton.setText("New Coupon");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        logOutButton2.setBackground(new java.awt.Color(0, 48, 90));
        logOutButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        logOutButton2.setForeground(new java.awt.Color(255, 255, 255));
        logOutButton2.setText("Log Out");
        logOutButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutButton2ActionPerformed(evt);
            }
        });

        helpButton2.setBackground(new java.awt.Color(0, 48, 90));
        helpButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        helpButton2.setForeground(new java.awt.Color(255, 255, 255));
        helpButton2.setText("Help");
        helpButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout controlPanelDiscountsLayout = new javax.swing.GroupLayout(controlPanelDiscounts);
        controlPanelDiscounts.setLayout(controlPanelDiscountsLayout);
        controlPanelDiscountsLayout.setHorizontalGroup(
            controlPanelDiscountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(discountRefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(quitButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
            .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(logOutButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(helpButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        controlPanelDiscountsLayout.setVerticalGroup(
            controlPanelDiscountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, controlPanelDiscountsLayout.createSequentialGroup()
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(helpButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(discountRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logOutButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(quitButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        filtersPanelDiscounts.setBackground(new java.awt.Color(122, 186, 242));
        filtersPanelDiscounts.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        expiredBox.setBackground(new java.awt.Color(122, 186, 242));
        expiredBox.setSelected(true);
        expiredBox.setText("Expired");
        expiredBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                expiredBoxItemStateChanged(evt);
            }
        });

        unexpiredBox.setBackground(new java.awt.Color(122, 186, 242));
        unexpiredBox.setSelected(true);
        unexpiredBox.setText("Unexpired");
        unexpiredBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                unexpiredBoxItemStateChanged(evt);
            }
        });
        unexpiredBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                unexpiredBoxKeyPressed(evt);
            }
        });

        inactiveCheck.setBackground(new java.awt.Color(122, 186, 242));
        inactiveCheck.setSelected(true);
        inactiveCheck.setText("Inactive");
        inactiveCheck.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                inactiveCheckItemStateChanged(evt);
            }
        });

        activeDiscountCheck.setBackground(new java.awt.Color(122, 186, 242));
        activeDiscountCheck.setSelected(true);
        activeDiscountCheck.setText("Active");
        activeDiscountCheck.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                activeDiscountCheckItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout filtersPanelDiscountsLayout = new javax.swing.GroupLayout(filtersPanelDiscounts);
        filtersPanelDiscounts.setLayout(filtersPanelDiscountsLayout);
        filtersPanelDiscountsLayout.setHorizontalGroup(
            filtersPanelDiscountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filtersPanelDiscountsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(inactiveCheck)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(activeDiscountCheck)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(expiredBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(unexpiredBox)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        filtersPanelDiscountsLayout.setVerticalGroup(
            filtersPanelDiscountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(filtersPanelDiscountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(expiredBox, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                .addComponent(unexpiredBox)
                .addComponent(inactiveCheck)
                .addComponent(activeDiscountCheck))
        );

        discountsTable.setBackground(new java.awt.Color(122, 186, 242));
        discountsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Code", "Type", "Amount", "Active", "Start Date", "End Date"
            }
        ));
        discountsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                discountsTableMouseClicked(evt);
            }
        });
        discountsTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                discountsTableKeyPressed(evt);
            }
        });
        shownDiscounts.setViewportView(discountsTable);

        createDiscounts.setBackground(new java.awt.Color(65, 146, 217));

        chooseType.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chooseType.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chooseType.setText("Choose Type");

        percentButton.setBackground(new java.awt.Color(0, 48, 90));
        percentButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        percentButton.setForeground(new java.awt.Color(255, 255, 255));
        percentButton.setText("Percent");
        percentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                percentButtonActionPerformed(evt);
            }
        });

        dollarButton.setBackground(new java.awt.Color(0, 48, 90));
        dollarButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        dollarButton.setForeground(new java.awt.Color(255, 255, 255));
        dollarButton.setText("Dollar");
        dollarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dollarButtonActionPerformed(evt);
            }
        });

        typeChosen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        typeChosen.setText("Type: ");

        createAmountLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        createAmountLabel.setText("Amount");

        amountField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                amountFieldKeyReleased(evt);
            }
        });

        formatSuggestion.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        formatSuggestion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        formatSuggestion.setText("Only raw numbers are needed. No decimals if not needed");

        createCodeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        createCodeLabel.setText("Code");

        createCodeField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                createCodeFieldKeyReleased(evt);
            }
        });

        createDiscountButton.setBackground(new java.awt.Color(0, 48, 90));
        createDiscountButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        createDiscountButton.setForeground(new java.awt.Color(255, 255, 255));
        createDiscountButton.setText("Create Discount");
        createDiscountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createDiscountButtonActionPerformed(evt);
            }
        });

        exitCreateButton.setBackground(new java.awt.Color(0, 48, 90));
        exitCreateButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        exitCreateButton.setForeground(new java.awt.Color(255, 255, 255));
        exitCreateButton.setText("Return");

        activeBox.setBackground(new java.awt.Color(65, 146, 217));
        activeBox.setText("Is Active");

        createDescriptionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        createDescriptionLabel.setText("Description");

        descriptionField.setColumns(20);
        descriptionField.setLineWrap(true);
        descriptionField.setRows(5);
        descriptionField.setWrapStyleWord(true);
        descriptionField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                descriptionFieldKeyReleased(evt);
            }
        });

        startDateLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        startDateLabel.setText("Start Date");

        startDateArrows.setPreferredSize(new java.awt.Dimension(15, 22));
        startDateArrows.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                startDateArrowsStateChanged(evt);
            }
        });

        endDateArrows.setPreferredSize(new java.awt.Dimension(17, 22));
        endDateArrows.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                endDateArrowsStateChanged(evt);
            }
        });

        endDateLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        endDateLabel.setText("End Date");

        startDateField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        endDateField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        discountErrorLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        discountErrorLabel.setForeground(java.awt.Color.red);
        discountErrorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        discountErrorLabel.setText("An Error Occured");

        discountErrorInstruction.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        discountErrorInstruction.setForeground(java.awt.Color.red);
        discountErrorInstruction.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        discountErrorInstruction.setText("Please Try Again");

        levelChosen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        levelChosen.setText("Level: ");

        cartButton.setBackground(new java.awt.Color(0, 48, 90));
        cartButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cartButton.setForeground(new java.awt.Color(255, 255, 255));
        cartButton.setText("Cart");
        cartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cartButtonActionPerformed(evt);
            }
        });

        itemButton.setBackground(new java.awt.Color(0, 48, 90));
        itemButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        itemButton.setForeground(new java.awt.Color(255, 255, 255));
        itemButton.setText("Item");
        itemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemButtonActionPerformed(evt);
            }
        });

        chooseLevel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        chooseLevel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chooseLevel.setText("Choose Level");

        gameBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gameBox.setEnabled(false);
        gameBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                gameBoxItemStateChanged(evt);
            }
        });

        gameLabelDiscount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gameLabelDiscount.setText("Game");

        gameError.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        gameError.setForeground(java.awt.Color.red);
        gameError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gameError.setText("Game is Required");

        amountError.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        amountError.setForeground(java.awt.Color.red);
        amountError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        amountError.setText("Amount is Required");

        descError.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        descError.setForeground(java.awt.Color.red);
        descError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        descError.setText("Description is required");

        codeError.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        codeError.setForeground(java.awt.Color.red);
        codeError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        codeError.setText("Code Required");

        javax.swing.GroupLayout createDiscountsLayout = new javax.swing.GroupLayout(createDiscounts);
        createDiscounts.setLayout(createDiscountsLayout);
        createDiscountsLayout.setHorizontalGroup(
            createDiscountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createDiscountsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(createDiscountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gameBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(amountError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createDiscountsLayout.createSequentialGroup()
                        .addGroup(createDiscountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(codeError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(gameError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(gameLabelDiscount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createDiscountsLayout.createSequentialGroup()
                                .addComponent(endDateField)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(endDateArrows, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createDiscountsLayout.createSequentialGroup()
                                .addComponent(startDateField)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(startDateArrows, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(createDiscountButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(createCodeField, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(typeChosen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(chooseType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(createAmountLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(amountField)
                            .addComponent(formatSuggestion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(createCodeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(exitCreateButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(createDescriptionLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(startDateLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(endDateLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(discountErrorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(discountErrorInstruction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(levelChosen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(chooseLevel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(descriptionField, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(descError, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
            .addGroup(createDiscountsLayout.createSequentialGroup()
                .addGroup(createDiscountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(createDiscountsLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(cartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(itemButton, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(createDiscountsLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(percentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dollarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, createDiscountsLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(activeBox)
                .addGap(205, 205, 205))
        );
        createDiscountsLayout.setVerticalGroup(
            createDiscountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createDiscountsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chooseType)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(createDiscountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(percentButton)
                    .addComponent(dollarButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(typeChosen)
                .addGap(18, 18, 18)
                .addComponent(chooseLevel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(createDiscountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cartButton)
                    .addComponent(itemButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(levelChosen)
                .addGap(17, 17, 17)
                .addComponent(gameLabelDiscount)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gameBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gameError)
                .addGap(2, 2, 2)
                .addComponent(createAmountLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(amountField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(formatSuggestion)
                .addGap(2, 2, 2)
                .addComponent(amountError)
                .addGap(18, 18, 18)
                .addComponent(createCodeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(createCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(codeError)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(createDescriptionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descriptionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(descError)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(startDateLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(createDiscountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startDateArrows, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(endDateLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(createDiscountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(endDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(endDateArrows, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(discountErrorLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(discountErrorInstruction)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(activeBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(createDiscountButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exitCreateButton)
                .addContainerGap())
        );

        selectedDiscount.setBackground(new java.awt.Color(65, 146, 217));

        saveButtonDiscounts.setBackground(new java.awt.Color(0, 48, 90));
        saveButtonDiscounts.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        saveButtonDiscounts.setForeground(new java.awt.Color(255, 255, 255));
        saveButtonDiscounts.setText("Save");
        saveButtonDiscounts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonDiscountsActionPerformed(evt);
            }
        });

        activeCheck.setBackground(new java.awt.Color(65, 146, 217));
        activeCheck.setText("Is Active");

        typeLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        typeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        typeLabel.setText("Type");

        codeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        codeLabel.setText("Code");

        codeField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                codeFieldKeyReleased(evt);
            }
        });

        amountLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        amountLabel.setText("Amount");

        startLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        startLabel.setText("Start Date");

        dateStart.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dateStart.setText("jLabel1");

        endLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        endLabel.setText("End Date");

        dateEnd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dateEnd.setText("jLabel1");

        satusLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        satusLabel.setText("Status");

        currentStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        currentStatus.setText("jLabel1");

        descriptionLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        descriptionLabel.setText("Description");

        descriptionText.setColumns(20);
        descriptionText.setLineWrap(true);
        descriptionText.setRows(5);
        descriptionText.setWrapStyleWord(true);
        descriptionText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                descriptionTextKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(descriptionText);

        discountErrorInstruction1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        discountErrorInstruction1.setForeground(java.awt.Color.red);
        discountErrorInstruction1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        discountErrorInstruction1.setText("Please Try Again");

        discountErrorLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        discountErrorLabel1.setForeground(java.awt.Color.red);
        discountErrorLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        discountErrorLabel1.setText("An Error Occured");

        successLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        successLabel.setForeground(new java.awt.Color(0, 204, 153));
        successLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        successLabel.setText("Editing Succeeded");

        discountReturnButton.setBackground(new java.awt.Color(0, 48, 90));
        discountReturnButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        discountReturnButton.setForeground(new java.awt.Color(255, 255, 255));
        discountReturnButton.setText("Return");
        discountReturnButton.setToolTipText("");
        discountReturnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discountReturnButtonActionPerformed(evt);
            }
        });

        amountOff.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                amountOffKeyReleased(evt);
            }
        });

        amountEditError.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        amountEditError.setForeground(java.awt.Color.red);
        amountEditError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        amountEditError.setText("Amount is Required");

        codeEditError.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        codeEditError.setForeground(java.awt.Color.red);
        codeEditError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        codeEditError.setText("Code is Required");

        descEditError.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        descEditError.setForeground(java.awt.Color.red);
        descEditError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        descEditError.setText("Description is Required");

        gameDiscountedLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gameDiscountedLabel.setText("Game Discounted");

        gameDiscountedTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gameDiscountedTitle.setText("jLabel3");

        formatSuggestion1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        formatSuggestion1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        formatSuggestion1.setText("Only raw numbers are needed. No decimals if not needed");
        formatSuggestion1.setToolTipText("");

        javax.swing.GroupLayout selectedDiscountLayout = new javax.swing.GroupLayout(selectedDiscount);
        selectedDiscount.setLayout(selectedDiscountLayout);
        selectedDiscountLayout.setHorizontalGroup(
            selectedDiscountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, selectedDiscountLayout.createSequentialGroup()
                .addGroup(selectedDiscountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(descriptionLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(selectedDiscountLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(selectedDiscountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(formatSuggestion1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(gameDiscountedTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(gameDiscountedLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(discountErrorInstruction1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(successLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(typeLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(codeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(codeField)
                            .addComponent(amountLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, selectedDiscountLayout.createSequentialGroup()
                                .addGroup(selectedDiscountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(dateStart, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(startLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(selectedDiscountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(endLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                                    .addComponent(dateEnd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(satusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(currentStatus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
                            .addComponent(discountReturnButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(discountErrorLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(saveButtonDiscounts, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(amountOff, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(amountEditError, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(codeEditError, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(descEditError, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, selectedDiscountLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(activeCheck)
                .addGap(213, 213, 213))
        );
        selectedDiscountLayout.setVerticalGroup(
            selectedDiscountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, selectedDiscountLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(typeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(codeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(codeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(codeEditError)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(amountLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(amountOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(formatSuggestion1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(amountEditError)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gameDiscountedLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(gameDiscountedTitle)
                .addGap(7, 7, 7)
                .addGroup(selectedDiscountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(selectedDiscountLayout.createSequentialGroup()
                        .addComponent(startLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateStart))
                    .addGroup(selectedDiscountLayout.createSequentialGroup()
                        .addComponent(endLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateEnd)))
                .addGap(18, 18, 18)
                .addComponent(satusLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(currentStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descriptionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descEditError)
                .addGap(22, 22, 22)
                .addComponent(successLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(discountErrorLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(discountErrorInstruction1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 209, Short.MAX_VALUE)
                .addComponent(activeCheck)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveButtonDiscounts)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(discountReturnButton)
                .addGap(14, 14, 14))
        );

        displayDiscounts.setLayer(createDiscounts, javax.swing.JLayeredPane.MODAL_LAYER);
        displayDiscounts.setLayer(selectedDiscount, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout displayDiscountsLayout = new javax.swing.GroupLayout(displayDiscounts);
        displayDiscounts.setLayout(displayDiscountsLayout);
        displayDiscountsLayout.setHorizontalGroup(
            displayDiscountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(selectedDiscount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(displayDiscountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(displayDiscountsLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(createDiscounts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        displayDiscountsLayout.setVerticalGroup(
            displayDiscountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(selectedDiscount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(displayDiscountsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(displayDiscountsLayout.createSequentialGroup()
                    .addComponent(createDiscounts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout discountsMenuLayout = new javax.swing.GroupLayout(discountsMenu);
        discountsMenu.setLayout(discountsMenuLayout);
        discountsMenuLayout.setHorizontalGroup(
            discountsMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(discountsMenuLayout.createSequentialGroup()
                .addComponent(controlPanelDiscounts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(discountsMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(shownDiscounts, javax.swing.GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE)
                    .addComponent(filtersPanelDiscounts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(displayDiscounts)
                .addContainerGap())
        );
        discountsMenuLayout.setVerticalGroup(
            discountsMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(controlPanelDiscounts, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(discountsMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(discountsMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(displayDiscounts)
                    .addGroup(discountsMenuLayout.createSequentialGroup()
                        .addComponent(filtersPanelDiscounts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(shownDiscounts)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Discounts", discountsMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitButtonActionPerformed
        //Quit the program
        System.exit(0);
    }//GEN-LAST:event_quitButtonActionPerformed

    private void logOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutButtonActionPerformed
        this.dispose();

        login.run();
    }//GEN-LAST:event_logOutButtonActionPerformed

    private void quitButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitButton1ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_quitButton1ActionPerformed

    private void logOutButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();

        login.run();
    }//GEN-LAST:event_logOutButton1ActionPerformed

    private void userTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userTableMouseClicked

        if (userTable.getSelectedRow() != -1) {

            displayUser();
            Variables.currentUser = displayedUsers.get(userTable.getSelectedRow()).userName;

        } else {

            selectedUser.setVisible(false);

        }
    }//GEN-LAST:event_userTableMouseClicked

    private void userTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_userTableKeyPressed

        //first lets make going through it as smooth as possible
        if (evt.getKeyCode() == KeyEvent.VK_TAB) {

            if (userTable.getSelectedRow() + 1 >= userTable.getRowCount()) {

                //here we will  focus on something else
                if (selectedUser.isVisible()) {

                    usernameField.requestFocus();

                } else {

                    jTabbedPane1.requestFocus();

                }

            } else {

                userTable.changeSelection(userTable.getSelectedRow() + 1, 0, false, false);

            }

        }

        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {

            displayUser();
            Variables.currentUser = displayedUsers.get(userTable.getSelectedRow()).userName;

        }

    }//GEN-LAST:event_userTableKeyPressed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed

        //this is where we will save the new informaion and rewrite the old
        //what we want to do is bascially rewrite the user in the list
        //and then use that as reference to save into the database
        //we will call two methods
        //this is so we can let the user object do all the work we need
        usernameErrors = Methods.checkUsername(usernameField.getText(), true, true);
        Methods.checkForErrors(usernameErrors, usernameError);

        passwordErrors = Methods.checkPassword(Methods.getPassword(passwordField.getPassword()));
        Methods.checkForErrors(passwordErrors, passwordError);

        nameErrors = Methods.checkName(firstField.getText(), lastField.getText());
        Methods.checkForErrors(nameErrors, nameError);

        addressErrors = Methods.checkAddress(line1.getText());
        Methods.checkForErrors(addressErrors, addressError);

        cityErrors = Methods.checkCity(cityField.getText());
        Methods.checkForErrors(cityErrors, cityError);

        stateErrors = Methods.checkState(stateBox.getSelectedIndex());
        Methods.checkForErrors(stateErrors, stateError);

        zipErrors = Methods.checkZip(zipField.getText());
        Methods.checkForErrors(zipErrors, zipError);

        emailErrors = Methods.checkEmail(emailField.getText());
        Methods.checkForErrors(emailErrors, emailError);

        phoneErrors = Methods.checkPhone(phoneField.getText());
        Methods.checkForErrors(phoneErrors, phoneError);

        boolean hasErrors = false;

        for (JLabel error : errorMessages) {

        }

        displayedUsers.get(userTable.getSelectedRow()).save(firstField.getText(), lastField.getText(), emailField.getText(), phoneField.getText(), line1.getText(), line2.getText(), line3.getText(), cityField.getText(), stateBox.getSelectedItem().toString(), zipField.getText(), usernameField.getText(), Methods.getPassword(passwordField.getPassword()), enabledBox.isSelected());

        if (displayedUsers.get(userTable.getSelectedRow()).databaseSave()) {

            setUserTables();
            userSuccessLabel.setVisible(true);
            userErrorLabel.setVisible(false);
            userErrorInstruction.setVisible(false);

        } else {

            userSuccessLabel.setVisible(false);
            userErrorLabel.setVisible(true);
            userErrorInstruction.setVisible(true);

        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void usernameFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameFieldKeyReleased
        usernameErrors = Methods.checkUsername(usernameField.getText(), false, false);

        //Now check for the errors
        //This will happen in others to
        Methods.checkForErrors(usernameErrors, usernameError);
    }//GEN-LAST:event_usernameFieldKeyReleased

    private void passwordFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordFieldKeyReleased
        // TODO add your handling code here:
        passwordErrors = Methods.checkPassword(Methods.getPassword(passwordField.getPassword()));

        Methods.checkForErrors(passwordErrors, passwordError);
    }//GEN-LAST:event_passwordFieldKeyReleased

    private void firstFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_firstFieldKeyReleased
        nameErrors = Methods.checkName(firstField.getText(), lastField.getText());

        Methods.checkForErrors(nameErrors, nameError);
    }//GEN-LAST:event_firstFieldKeyReleased

    private void lastFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lastFieldKeyReleased
        nameErrors = Methods.checkName(firstField.getText(), lastField.getText());

        Methods.checkForErrors(nameErrors, nameError);
    }//GEN-LAST:event_lastFieldKeyReleased

    private void line1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_line1KeyReleased
        addressErrors = Methods.checkAddress(line1.getText());

        Methods.checkForErrors(addressErrors, addressError);
    }//GEN-LAST:event_line1KeyReleased

    private void cityFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cityFieldKeyReleased
        cityErrors = Methods.checkCity(cityField.getText());

        Methods.checkForErrors(cityErrors, cityError);
    }//GEN-LAST:event_cityFieldKeyReleased

    private void stateBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_stateBoxItemStateChanged
        stateErrors = Methods.checkState(stateBox.getSelectedIndex());

        Methods.checkForErrors(stateErrors, stateError);
    }//GEN-LAST:event_stateBoxItemStateChanged

    private void zipFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_zipFieldKeyReleased
        zipErrors = Methods.checkZip(zipField.getText());

        Methods.checkForErrors(zipErrors, zipError);
    }//GEN-LAST:event_zipFieldKeyReleased

    private void emailFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emailFieldKeyReleased

        emailErrors = Methods.checkEmail(emailField.getText());

        Methods.checkForErrors(emailErrors, emailError);

    }//GEN-LAST:event_emailFieldKeyReleased

    private void phoneFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phoneFieldKeyReleased
        phoneErrors = Methods.checkPhone(phoneField.getText());

        Methods.checkForErrors(phoneErrors, phoneError);
    }//GEN-LAST:event_phoneFieldKeyReleased

    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonActionPerformed
        //we want to pull up a create account page
        createAccount.open();

        this.dispose();
    }//GEN-LAST:event_createButtonActionPerformed

    private void quitButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitButton2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_quitButton2ActionPerformed

    private void logOutButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutButton2ActionPerformed
        this.dispose();

        login.run();
    }//GEN-LAST:event_logOutButton2ActionPerformed

    private void discountsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_discountsTableMouseClicked

        //we will display the discount info and its editable fields
        if (discountsTable.getSelectedRow() != -1) {

            displayDiscount(discountsTable.getSelectedRow());
            createDiscounts.setVisible(false);

        }

    }//GEN-LAST:event_discountsTableMouseClicked

    private void discountsTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_discountsTableKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_TAB) {

            if (discountsTable.getSelectedRow() + 1 >= discountsTable.getRowCount()) {

                //here we will  focus on something else
                if (createDiscounts.isVisible()) {

                    percentButton.requestFocus();

                } else if (selectedDiscount.isVisible()) {

                    codeField.requestFocus();

                } else {

                    jTabbedPane1.requestFocus();

                }

            } else {

                discountsTable.changeSelection(discountsTable.getSelectedRow() + 1, 0, false, false);

            }

        }

        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {

            displayDiscount(discountsTable.getSelectedRow());
            createDiscounts.setVisible(false);

        }
    }//GEN-LAST:event_discountsTableKeyPressed

    private void discountRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discountRefreshActionPerformed
        try {
            SQLManager.loadDiscounts();
        } catch (ParseException ex) {
            Logger.getLogger(ManagerView.class.getName()).log(Level.SEVERE, null, ex);
        }

        setDiscountsTable();
    }//GEN-LAST:event_discountRefreshActionPerformed

    private void refreshButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButton1ActionPerformed
        SQLManager.loadUsers();

        //now set the tables
        setUserTables();

        selectedUser.setVisible(false);
    }//GEN-LAST:event_refreshButton1ActionPerformed

    private void saveButtonDiscountsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonDiscountsActionPerformed

        //we want to take the current selected index and then use that object to save
        //first though we want to do some validation
        codeErrors = Methods.checkCode(codeField, true, true);
        Methods.checkForErrors(codeErrors, codeEditError);

        amountErrors = Methods.checkAmount(amountOff);
        Methods.checkForErrors(amountErrors, amountEditError);

        descErrors = Methods.checkDesc(descriptionText);
        Methods.checkForErrors(descErrors, descEditError);

        boolean hasError = false;

        for (JLabel error : discountEditErrors) {

            if (error.isVisible()) {

                hasError = true;

            }

        }

        if (!hasError) {
            displayedDiscounts.get(discountsTable.getSelectedRow()).save(codeField.getText(), activeCheck.isSelected(), descriptionText.getText(), amountOff.getText());
            if (displayedDiscounts.get(discountsTable.getSelectedRow()).databaseSave()) {

                successLabel.setVisible(true);

            } else {

                discountErrorLabel1.setVisible(true);
                discountErrorInstruction1.setVisible(true);
                successLabel.setVisible(false);

            }
        }

    }//GEN-LAST:event_saveButtonDiscountsActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        displayCreateDiscounts();
    }//GEN-LAST:event_addButtonActionPerformed

    private void startDateArrowsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_startDateArrowsStateChanged

        //check if it went positive or negative
        if ((int) startDateArrows.getValue() == 1) {

            selectedStartDate = selectedStartDate.plusDays(1);

        } else if ((int) startDateArrows.getValue() == -1) {

            selectedStartDate = selectedStartDate.minusDays(1);

        }

        //check if it still is within current date and if the end date is still at least one day later
        if (selectedStartDate.isBefore(currentDate)) {

            selectedStartDate = currentDate;

        }

        if (selectedStartDate.isEqual(selectedEndDate) || selectedStartDate.isAfter(selectedEndDate)) {

            selectedEndDate = selectedStartDate.plusDays(1);

        }

        startDateField.setText(selectedStartDate.format(formatter));
        endDateField.setText(selectedEndDate.format(formatter));

        startDateArrows.setValue(0);

    }//GEN-LAST:event_startDateArrowsStateChanged

    private void endDateArrowsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_endDateArrowsStateChanged

        if ((int) endDateArrows.getValue() == 1) {

            selectedEndDate = selectedEndDate.plusDays(1);

        } else if ((int) endDateArrows.getValue() == -1) {

            selectedEndDate = selectedEndDate.minusDays(1);

        }

        if (selectedEndDate.isEqual(selectedStartDate) || selectedEndDate.isBefore(selectedStartDate)) {

            selectedEndDate = selectedStartDate.plusDays(1);

        }

        startDateField.setText(selectedStartDate.format(formatter));
        endDateField.setText(selectedEndDate.format(formatter));

        endDateArrows.setValue(0);
    }//GEN-LAST:event_endDateArrowsStateChanged

    private void percentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_percentButtonActionPerformed

        discountType = "Percent";

        typeChosen.setText("Type: " + discountType);

    }//GEN-LAST:event_percentButtonActionPerformed

    private void dollarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dollarButtonActionPerformed

        discountType = "Dollar";

        typeChosen.setText("Type: " + discountType);

    }//GEN-LAST:event_dollarButtonActionPerformed

    private void createDiscountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createDiscountButtonActionPerformed

        //We need to call upon the sql and create a brand new discount
        //we should do a little validation first to make sure we are using proper data
        if (discountLevel.equals("Item") && gameBox.getSelectedIndex() == 0) {

            gameError.setVisible(true);

        } else {

            gameError.setVisible(false);

        }

        descErrors = Methods.checkDesc(descriptionField);
        Methods.checkForErrors(descErrors, descError);

        codeErrors = Methods.checkCode(createCodeField, true, true);
        Methods.checkForErrors(codeErrors, codeError);

        amountErrors = Methods.checkAmount(amountField);
        Methods.checkForErrors(amountErrors, amountError);

        boolean hasErrors = false;

        for (JLabel error : discountCreateErrors) {

            if (error.isVisible()) {

                hasErrors = true;

            }

        }

        //first we should do some checking to het more exact data in the database
        if (!hasErrors) {
            int isActive = 0;

            int type = 0;

            int level = 0;

            float discountAmount = 0.00f;

            if (discountType.equals("Percent")) {

                type = 0;
                discountAmount = Float.parseFloat(amountField.getText()) / 100;

            } else {

                type = 1;
                discountAmount = Float.parseFloat(amountField.getText());

            }

            if (activeBox.isSelected()) {

                isActive = 1;

            } else {

                isActive = 0;

            }

            //now we create the discount
            if (SQLManager.createDiscount(type, createCodeField.getText().toUpperCase(), descriptionField.getText(), discountAmount, startDateField.getText(), endDateField.getText(), isActive, discountLevel, gameBox.getSelectedItem().toString())) {

                try {
                    SQLManager.loadDiscounts();
                } catch (ParseException ex) {
                    Logger.getLogger(ManagerView.class.getName()).log(Level.SEVERE, null, ex);
                }
                setDiscountsTable();

                discountErrorLabel.setVisible(false);
                discountErrorInstruction.setVisible(false);

                createDiscounts.setVisible(false);

            } else {

                //display an error
                discountErrorLabel.setVisible(true);
                discountErrorInstruction.setVisible(true);

            }
        }

    }//GEN-LAST:event_createDiscountButtonActionPerformed

    private void returnUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnUserButtonActionPerformed
        selectedUser.setVisible(false);
    }//GEN-LAST:event_returnUserButtonActionPerformed

    private void genreAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genreAllActionPerformed

        for (int x = 0; x < genreFiltersList.size(); x++) {

            genreFiltersList.get(x).setSelected(true);

        }

        searchGames();
    }//GEN-LAST:event_genreAllActionPerformed

    private void consoleAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consoleAllActionPerformed

        //go through the entire list and set all the searched fors to yes
        for (int x = 0; x < consoleFiltersList.size(); x++) {

            consoleFiltersList.get(x).setSelected(true);

        }
        searchGames();
    }//GEN-LAST:event_consoleAllActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed

        //use a method
        searchGames();
    }//GEN-LAST:event_searchButtonActionPerformed

    private void filtersButtonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_filtersButtonItemStateChanged

        if (filtersButton.isSelected()) {

            inventoryFiltersPanel.setVisible(true);

            filtersButton.setText("Hide Filters");

        } else if (!filtersButton.isSelected()) {

            inventoryFiltersPanel.setVisible(false);
            filtersButton.setText("Browse Filters");

        }

    }//GEN-LAST:event_filtersButtonItemStateChanged

    private void filtersButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_filtersButtonKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_TAB && !filtersPanel.isVisible()) {

            itemDisplay.requestFocus();

            //selectedGame = 0;
            gamesDisplayed.get(selectedGame).highlight();

        }
    }//GEN-LAST:event_filtersButtonKeyPressed

    private void itemDisplayFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_itemDisplayFocusGained
        // TODO add your handling code here:

        gamesDisplayed.get(selectedGame).highlight();

        controlsLabel.setVisible(true);
        controls1.setVisible(true);
        controls2.setVisible(true);
        controls3.setVisible(true);
    }//GEN-LAST:event_itemDisplayFocusGained

    private void itemDisplayFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_itemDisplayFocusLost
        gamesDisplayed.get(selectedGame).revert();

        controlsLabel.setVisible(false);
        controls1.setVisible(false);
        controls2.setVisible(false);
        controls3.setVisible(false);
    }//GEN-LAST:event_itemDisplayFocusLost

    private void itemDisplayKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_itemDisplayKeyPressed

        previousIndex = selectedGame;

        if (evt.getKeyCode() == KeyEvent.VK_W) {

            selectedGame -= 3;

            if (selectedGame < 0) {

                selectedGame = gamesDisplayed.size() - 1;

            }

            gamesDisplayed.get(selectedGame).highlight();
            gamesDisplayed.get(previousIndex).revert();

        } else if (evt.getKeyCode() == KeyEvent.VK_S) {

            selectedGame += 3;

            if (selectedGame >= gamesDisplayed.size()) {

                selectedGame = 0;

            }

            gamesDisplayed.get(selectedGame).highlight();
            gamesDisplayed.get(previousIndex).revert();

        } else if (evt.getKeyCode() == KeyEvent.VK_A) {

            selectedGame -= 1;

            if (selectedGame < 0) {

                selectedGame = gamesDisplayed.size() - 1;

            }

            gamesDisplayed.get(selectedGame).highlight();
            gamesDisplayed.get(previousIndex).revert();

        } else if (evt.getKeyCode() == KeyEvent.VK_D) {

            selectedGame += 1;

            if (selectedGame >= gamesDisplayed.size()) {

                selectedGame = 0;

            }

            gamesDisplayed.get(selectedGame).highlight();
            gamesDisplayed.get(previousIndex).revert();

        }

        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {

            displayGame(gamesDisplayed.get(selectedGame).indexHeld);

        }
    }//GEN-LAST:event_itemDisplayKeyPressed

    private void quantityAvailableStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_quantityAvailableStateChanged

        //Here we will check for the amount being asked for and act accordingly
        //We need to store the value
        int tempAmount = (int) quantityAvailable.getValue();

        //First lets check if it becomes negative
        if (tempAmount < 0) {

            quantityAvailable.setValue(0);
            //Just set it back to 0

        }


    }//GEN-LAST:event_quantityAvailableStateChanged

    private void returnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnButtonActionPerformed
        gamePanel.setVisible(false);
        Variables.chosenGame = -1;

        //request focus again for the scroll pane
        itemDisplay.requestFocus();
    }//GEN-LAST:event_returnButtonActionPerformed

    private void restockThresholdStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_restockThresholdStateChanged

        int tempAmount = (int) restockThreshold.getValue();

        //First lets check if it becomes negative
        if (tempAmount < 0) {

            restockThreshold.setValue(0);
            //Just set it back to 0

        }

    }//GEN-LAST:event_restockThresholdStateChanged

    private void gameSaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gameSaveButtonActionPerformed

        warning1.setVisible(false);
        warning2.setVisible(false);

        //Do some validation first as usual
        priceErrors = Methods.checkAmount(priceField);

        Methods.checkForErrors(priceErrors, priceError);

        if (!gameTitle.getText().isBlank()) {

            editTitleError.setVisible(false);

        } else {

            editTitleError.setVisible(true);

        }

        if (!gameDescription.getText().isBlank()) {

            editDescError.setVisible(false);

        } else {

            editDescError.setVisible(true);

        }

        boolean hasErrors = false;

        for (JLabel error : inventoryErrors) {

            if (error.isVisible()) {

                hasErrors = true;

            }
        }

        if (!hasErrors) {
            int chosen = 0;

            if (gameEnabledBox.isSelected()) {

                chosen = 1;

            }

            Lists.games.get(index).save(gameTitle.getText(), Float.parseFloat(priceField.getText()), (int) quantityAvailable.getValue(), gameDescription.getText(), (int) restockThreshold.getValue(), chosen);

            if (Lists.games.get(index).databaseSave()) {

                searchGames();

            } else {

                warning1.setVisible(true);
                warning2.setVisible(true);

            }
        }

    }//GEN-LAST:event_gameSaveButtonActionPerformed

    private void thumbnailButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thumbnailButtonActionPerformed
        try {
            uploadImage(0);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ManagerView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_thumbnailButtonActionPerformed

    private void mainImageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainImageButtonActionPerformed
        try {
            uploadImage(1);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ManagerView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mainImageButtonActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        gamePanel.setVisible(false);
        inventoryFiltersPanel.setVisible(false);
        selectedUser.setVisible(false);
        selectedDiscount.setVisible(false);
        createDiscounts.setVisible(false);

    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void discountReturnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discountReturnButtonActionPerformed

        selectedDiscount.setVisible(false);

    }//GEN-LAST:event_discountReturnButtonActionPerformed

    private void managerBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_managerBoxKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            userTable.changeSelection(0, 0, false, false);
        }

    }//GEN-LAST:event_managerBoxKeyPressed

    private void unexpiredBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_unexpiredBoxKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_TAB) {
            discountsTable.changeSelection(0, 0, false, false);
        }

    }//GEN-LAST:event_unexpiredBoxKeyPressed

    private void newGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGameButtonActionPerformed

        //all we need to do here is create a new view for the new game create screen
        //well it will already be made just load it here
        this.dispose();

        gameCreate.open();

    }//GEN-LAST:event_newGameButtonActionPerformed

    private void posButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_posButtonActionPerformed

        this.dispose();

        //before we open the POS system make sure we save the customer ID as well
        System.out.println(userTable.getSelectedRow());
        Variables.customerID = displayedUsers.get(userTable.getSelectedRow()).userID;

        store.openPOS(this);
    }//GEN-LAST:event_posButtonActionPerformed

    private void viewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewButtonActionPerformed

        Variables.customerID = displayedUsers.get(userTable.getSelectedRow()).userID;

        this.dispose();

        pastPurchases.open();
    }//GEN-LAST:event_viewButtonActionPerformed

    private void notificationsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notificationsButtonActionPerformed
        this.dispose();

        notifs.open();
    }//GEN-LAST:event_notificationsButtonActionPerformed

    private void inventoryReportBttuonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inventoryReportBttuonActionPerformed
        try {
            //we will bring up a report for all the inventory
            SQLManager.inventoryReport();
        } catch (Exception ex) {
            Logger.getLogger(ManagerView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_inventoryReportBttuonActionPerformed

    private void cartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cartButtonActionPerformed
        discountLevel = "Cart";

        gameBox.setSelectedIndex(0);

        levelChosen.setText("Level: " + discountLevel);

        gameBox.setEnabled(discountLevel.equals("Item"));

        gameError.setVisible(false);
    }//GEN-LAST:event_cartButtonActionPerformed

    private void itemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemButtonActionPerformed
        discountLevel = "Item";

        levelChosen.setText("Level: " + discountLevel);

        gameBox.setEnabled(discountLevel.equals("Item"));
    }//GEN-LAST:event_itemButtonActionPerformed

    private void minMonthItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_minMonthItemStateChanged
        //Now we remove the days

        newDate.set(newDate.MONTH, minMonth.getSelectedIndex() + 1);
        newDate.set(newDate.DAY_OF_MONTH, 1);
        newDate.set(newDate.YEAR, Integer.parseInt(minYear.getSelectedItem().toString()));

        minDay.removeAllItems();

        minDay.addItem("" + newDate.get(newDate.DAY_OF_MONTH));

        for (int x = 0; x < 32; x++) {

            //Same principle as the months
            newDate.roll(newDate.DAY_OF_MONTH, 1);

            if (newDate.get(newDate.DAY_OF_MONTH) == 1) {
                break;
            }

            minDay.addItem("" + newDate.get(newDate.DAY_OF_MONTH));

        }
    }//GEN-LAST:event_minMonthItemStateChanged

    private void maxMonthItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_maxMonthItemStateChanged
        newDate.set(newDate.MONTH, maxMonth.getSelectedIndex() + 1);
        newDate.set(newDate.DAY_OF_MONTH, 1);
        newDate.set(newDate.YEAR, Integer.parseInt(maxYear.getSelectedItem().toString()));

        maxDay.removeAllItems();

        maxDay.addItem("" + newDate.get(newDate.DAY_OF_MONTH));

        for (int x = 0; x < 32; x++) {

            //Same principle as the months
            newDate.roll(newDate.DAY_OF_MONTH, 1);

            if (newDate.get(newDate.DAY_OF_MONTH) == 1) {
                break;
            }

            maxDay.addItem("" + newDate.get(newDate.DAY_OF_MONTH));

        }
    }//GEN-LAST:event_maxMonthItemStateChanged

    private void salesReportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salesReportButtonActionPerformed

        //we will now get a report based on the dates given
        //make two calender objects
        GregorianCalendar startDate = new GregorianCalendar();
        GregorianCalendar endDate = new GregorianCalendar();

        startDate.set(startDate.YEAR, Integer.parseInt(minYear.getSelectedItem().toString()));
        startDate.set(startDate.MONTH, minMonth.getSelectedIndex());
        startDate.set(startDate.DAY_OF_MONTH, Integer.parseInt(minDay.getSelectedItem().toString()));

        endDate.set(endDate.YEAR, Integer.parseInt(maxYear.getSelectedItem().toString()));
        endDate.set(endDate.MONTH, maxMonth.getSelectedIndex());
        endDate.set(endDate.DAY_OF_MONTH, Integer.parseInt(maxDay.getSelectedItem().toString()));

        //now format them
        String formatStartDate = dateFormatter.format(startDate.getTime());
        String formatEndDate = dateFormatter.format(endDate.getTime());

        try {
            //now we will call upon a method in sql manager
            //there will be two different type of sales reports
            //so use a boolean to differentiate
            SQLManager.salesReport(false, formatStartDate, formatEndDate);
        } catch (Exception ex) {
            Logger.getLogger(ManagerView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_salesReportButtonActionPerformed

    private void codeFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codeFieldKeyReleased
        codeErrors = Methods.checkCode(codeField, false, false);

        Methods.checkForErrors(codeErrors, codeEditError);
    }//GEN-LAST:event_codeFieldKeyReleased

    private void amountOffKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_amountOffKeyReleased
        amountErrors = Methods.checkAmount(amountOff);

        Methods.checkForErrors(amountErrors, amountEditError);
    }//GEN-LAST:event_amountOffKeyReleased

    private void descriptionTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descriptionTextKeyReleased
        descErrors = Methods.checkDesc(descriptionText);

        Methods.checkForErrors(descErrors, descEditError);
    }//GEN-LAST:event_descriptionTextKeyReleased

    private void gameBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_gameBoxItemStateChanged

        if (discountLevel.equals("Item") && gameBox.getSelectedIndex() == 0) {

            gameError.setVisible(true);

        } else {

            gameError.setVisible(false);

        }

    }//GEN-LAST:event_gameBoxItemStateChanged

    private void amountFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_amountFieldKeyReleased
        amountErrors = Methods.checkAmount(amountField);

        Methods.checkForErrors(amountErrors, amountError);
    }//GEN-LAST:event_amountFieldKeyReleased

    private void createCodeFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_createCodeFieldKeyReleased
        codeErrors = Methods.checkCode(createCodeField, false, false);

        Methods.checkForErrors(codeErrors, codeError);
    }//GEN-LAST:event_createCodeFieldKeyReleased

    private void descriptionFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descriptionFieldKeyReleased
        descErrors = Methods.checkDesc(descriptionField);

        Methods.checkForErrors(descErrors, descError);
    }//GEN-LAST:event_descriptionFieldKeyReleased

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        SQLStore.loadGames();

        searchGames();
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void priceFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_priceFieldKeyReleased
        priceErrors = Methods.checkAmount(priceField);

        Methods.checkForErrors(priceErrors, priceError);
    }//GEN-LAST:event_priceFieldKeyReleased

    private void gameTitleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_gameTitleKeyReleased
        if (!gameTitle.getText().isBlank()) {

            editTitleError.setVisible(false);

        } else {

            editTitleError.setVisible(true);

        }
    }//GEN-LAST:event_gameTitleKeyReleased

    private void gameDescriptionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_gameDescriptionKeyReleased
        if (!gameDescription.getText().isBlank()) {

            editDescError.setVisible(false);

        } else {

            editDescError.setVisible(true);

        }
    }//GEN-LAST:event_gameDescriptionKeyReleased

    private void showLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showLabelMouseEntered
        passwordField.setEchoChar((char) 0);
    }//GEN-LAST:event_showLabelMouseEntered

    private void showLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showLabelMouseExited
        passwordField.setEchoChar('*');
    }//GEN-LAST:event_showLabelMouseExited

    private void inactiveCheckItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_inactiveCheckItemStateChanged
        if (inactiveCheck.isSelected()) {

            inactiveSearch = true;
            //now reset the discount table
            setDiscountsTable();

        } else if (!inactiveCheck.isSelected()) {

            inactiveSearch = false;
            //now reset the discount table
            setDiscountsTable();

        }
    }//GEN-LAST:event_inactiveCheckItemStateChanged

    private void activeDiscountCheckItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_activeDiscountCheckItemStateChanged
        if (activeDiscountCheck.isSelected()) {

            activeSearch = true;
            setDiscountsTable();

        } else if (!activeDiscountCheck.isSelected()) {

            activeSearch = false;
            setDiscountsTable();

        }
    }//GEN-LAST:event_activeDiscountCheckItemStateChanged

    private void expiredBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_expiredBoxItemStateChanged
        if (expiredBox.isSelected()) {

            expiredSearch = true;
            setDiscountsTable();

        } else if (!expiredBox.isSelected()) {

            expiredSearch = false;
            setDiscountsTable();

        }
    }//GEN-LAST:event_expiredBoxItemStateChanged

    private void unexpiredBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_unexpiredBoxItemStateChanged
        if (unexpiredBox.isSelected()) {

            unexpiredSearch = true;
            setDiscountsTable();

        } else if (!unexpiredBox.isSelected()) {

            unexpiredSearch = false;
            setDiscountsTable();

        }
    }//GEN-LAST:event_unexpiredBoxItemStateChanged

    private void disabledAccountItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_disabledAccountItemStateChanged
        if (disabledAccount.isSelected()) {
            disabledAccountSearch = true;

        } else {
            disabledAccountSearch = false;
        }

        setUserTables();
    }//GEN-LAST:event_disabledAccountItemStateChanged

    private void enabledAccountItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_enabledAccountItemStateChanged
        if (enabledAccount.isSelected()) {
            enabledAccountSearch = true;

        } else {
            enabledAccountSearch = false;
        }

        setUserTables();
    }//GEN-LAST:event_enabledAccountItemStateChanged

    private void customerBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_customerBoxItemStateChanged
        //check its state
        if (customerBox.isSelected()) {

            customerSearch = true;

        } else {

            customerSearch = false;

        }

        //now reset the user table
        setUserTables();
    }//GEN-LAST:event_customerBoxItemStateChanged

    private void managerBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_managerBoxItemStateChanged
        if (managerBox.isSelected()) {

            managerSearch = true;

        } else {

            managerSearch = false;

        }

        //now reset the user table
        setUserTables();

    }//GEN-LAST:event_managerBoxItemStateChanged

    private void disabledGamesCheckItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_disabledGamesCheckItemStateChanged
        if (disabledGamesCheck.isSelected()) {

            disabledGameCheck = true;

        } else {

            disabledGameCheck = false;

        }

        searchGames();
    }//GEN-LAST:event_disabledGamesCheckItemStateChanged

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed
        try {
            //Open up a pdf for a help file
            URL helpURL = new URL("https://drive.google.com/file/d/1XmwVKQ2Ppe4ZDsV-GkjQqICAFxHSWWmJ/view?usp=sharing");
            try {
                Desktop.getDesktop().browse(helpURL.toURI());
            } catch (URISyntaxException ex) {
                Logger.getLogger(StorePage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(StorePage.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(StorePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_helpButtonActionPerformed

    private void helpButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButton1ActionPerformed
        try {
            //Open up a pdf for a help file
            URL helpURL = new URL("https://drive.google.com/file/d/1XmwVKQ2Ppe4ZDsV-GkjQqICAFxHSWWmJ/view?usp=sharing");
            try {
                Desktop.getDesktop().browse(helpURL.toURI());
            } catch (URISyntaxException ex) {
                Logger.getLogger(StorePage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(StorePage.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(StorePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_helpButton1ActionPerformed

    private void helpButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButton2ActionPerformed
        try {
            //Open up a pdf for a help file
            URL helpURL = new URL("https://drive.google.com/file/d/1XmwVKQ2Ppe4ZDsV-GkjQqICAFxHSWWmJ/view?usp=sharing");
            try {
                Desktop.getDesktop().browse(helpURL.toURI());
            } catch (URISyntaxException ex) {
                Logger.getLogger(StorePage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(StorePage.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(StorePage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_helpButton2ActionPerformed

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
//            java.util.logging.Logger.getLogger(ManagerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ManagerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ManagerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ManagerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ManagerView().setVisible(true);
//            }
//        });
//    }
    public void open() throws ParseException {

        //Whenever it opens we need to get users
        //Id say users is something we shouldnt load at first because it may not be needed at all
        //unlike games which will be available in multiple views
        SQLManager.loadUsers();

        //now set the tables
        setUserTables();

        selectedUser.setVisible(false);

        //DISCOUNTS
        SQLManager.loadDiscounts();

        setDiscountsTable();

        //INVENTORY
        disabledGamesCheck.setSelected(true);
        //disabledGameCheck = true;
        createFilters();
        SQLStore.loadGames();

        displayGames("ALL");

        //Change the current page variable
        Methods.currentPage = "Manager";

        gamePanel.setVisible(false);
        inventoryFiltersPanel.setVisible(false);
        selectedUser.setVisible(false);
        selectedDiscount.setVisible(false);
        createDiscounts.setVisible(false);

        //also edit notifications
        createNotificationsCount();

        //lets also set the discounts game box
        gameBox.removeAllItems();

        gameBox.addItem("N/A");

        for (Game game : Lists.games) {

            //make a boolean
            boolean doesExist = false;

            for (int x = 0; x < gameBox.getItemCount(); x++) {

                if (game.name.equals(gameBox.getItemAt(x))) {

                    doesExist = true;

                }

            }

            if (doesExist == false) {

                gameBox.addItem(game.name);

            }

        }

        levelChosen.setText("Level: Cart");

        discountLevel = "Cart";

        //also set the dates back to default
        minYear.setSelectedIndex(0);
        maxYear.setSelectedIndex(0);

        minMonth.setSelectedIndex(1);
        minMonth.setSelectedIndex(0);
        maxMonth.setSelectedIndex(1);
        maxMonth.setSelectedIndex(0);

        passwordField.setEchoChar('*');
        //this is just to make the state change and we call the ecent to edit the days
        //therefore its back to defauult even if opened for the first time

        controlsLabel.setVisible(false);
        controls1.setVisible(false);
        controls2.setVisible(false);
        controls3.setVisible(false);

        this.setVisible(true);

    }

    public void setUserTables() {

        //first we need to create a table model
        DefaultTableModel users = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {

                return false;

            }

        };

        users.addColumn("User Level");
        users.addColumn("Username");
        users.addColumn("First Name");
        users.addColumn("Last Name");

        boolean searchedFor = false;

        displayedUsers.clear();

        for (User user : Lists.users) {

            //we need to check if we are searching for this level
            searchedFor = false;

            //the first thing we need to check if they are disabled or enabled
            if (user.enabled == 1) {

                if (enabledAccountSearch) {
                    if (user.userLevel.equals("Manager")) {

                        //now check
                        if (managerSearch) {

                            searchedFor = true;

                        } else {

                            searchedFor = false;

                        }

                    } else if (user.userLevel.equals("Customer")) {

                        //now check
                        if (customerSearch) {

                            searchedFor = true;

                        } else {

                            searchedFor = false;

                        }

                    }
                } else {

                    searchedFor = false;

                }
            } else {

                if (disabledAccountSearch) {

                    if (user.userLevel.equals("Manager")) {

                        //now check
                        if (managerSearch) {

                            searchedFor = true;

                        } else {

                            searchedFor = false;

                        }

                    } else if (user.userLevel.equals("Customer")) {

                        //now check
                        if (customerSearch) {

                            searchedFor = true;

                        } else {

                            searchedFor = false;

                        }

                    }

                } else {

                    searchedFor = false;

                }
            }

            if (searchedFor) {

                String[] displayedInfo = {user.userLevel, user.userName, user.userFirstName, user.userLastName};

                users.addRow(displayedInfo);

                displayedUsers.add(user);

            }
        }

        userTable.setModel(users);

        selectedUser.setVisible(false);

    }

    public void displayUser() {

        selectedUser.setVisible(true);
        usernameError.setVisible(false);
        passwordError.setVisible(false);
        nameError.setVisible(false);
        addressError.setVisible(false);
        cityError.setVisible(false);
        stateError.setVisible(false);
        zipError.setVisible(false);
        emailError.setVisible(false);
        phoneError.setVisible(false);

        //we use the selected row in order to display the user
        levelLabel.setText(displayedUsers.get(userTable.getSelectedRow()).userLevel);
        usernameField.setText(displayedUsers.get(userTable.getSelectedRow()).userName);
        passwordField.setText(displayedUsers.get(userTable.getSelectedRow()).password);

        firstField.setText(displayedUsers.get(userTable.getSelectedRow()).userFirstName);
        lastField.setText(displayedUsers.get(userTable.getSelectedRow()).userLastName);

        line1.setText(displayedUsers.get(userTable.getSelectedRow()).address1);
        line2.setText(displayedUsers.get(userTable.getSelectedRow()).address2);
        line3.setText(displayedUsers.get(userTable.getSelectedRow()).address3);

        cityField.setText(displayedUsers.get(userTable.getSelectedRow()).city);
        stateBox.setSelectedItem(displayedUsers.get(userTable.getSelectedRow()).state);
        zipField.setText(displayedUsers.get(userTable.getSelectedRow()).zip);

        emailField.setText(displayedUsers.get(userTable.getSelectedRow()).userEmail);
        phoneField.setText(displayedUsers.get(userTable.getSelectedRow()).phoneNum);

        enabledBox.setSelected(displayedUsers.get(userTable.getSelectedRow()).enabled == 1);

        userSuccessLabel.setVisible(false);
        userErrorLabel.setVisible(false);
        userErrorInstruction.setVisible(false);

        //only set it visible when its a customer
        posButton.setVisible(displayedUsers.get(userTable.getSelectedRow()).userLevel.equals("Customer"));
        viewButton.setVisible(displayedUsers.get(userTable.getSelectedRow()).userLevel.equals("Customer"));

    }

    public void setDiscountsTable() {

        displayedDiscounts.clear();

        DefaultTableModel discounts = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {

                return false;

            }

        };

        discounts.addColumn("Code");
        discounts.addColumn("Type");
        discounts.addColumn("Amount");
        discounts.addColumn("Active");
        discounts.addColumn("Start Date");
        discounts.addColumn("End Date");

        boolean searchedFor = false;

        for (Discount discount : Lists.discounts) {

            //we need to check if we are searching for it if its expired or unexpired
            searchedFor = false;

            //creating a new date will create a date of the current day
            //so do not worry
            if (discount.active == 1) {
                if (activeSearch) {
                    if (discount.endDate.before(new Date())) {

                        //now check
                        if (expiredSearch) {

                            searchedFor = true;

                        } else {

                            searchedFor = false;

                        }

                    } else if (discount.endDate.after(new Date())) {

                        //now check
                        if (unexpiredSearch) {

                            searchedFor = true;

                        } else {

                            searchedFor = false;

                        }

                    }
                } else {

                    searchedFor = false;

                }
            }

            if (discount.active == 0) {
                if (inactiveSearch) {
                    if (discount.endDate.before(new Date())) {

                        //now check
                        if (expiredSearch) {

                            searchedFor = true;

                        } else {

                            searchedFor = false;

                        }

                    } else if (discount.endDate.after(new Date())) {

                        //now check
                        if (unexpiredSearch) {

                            searchedFor = true;

                        } else {

                            searchedFor = false;

                        }

                    }
                } else {

                    searchedFor = false;

                }
            }

            if (searchedFor) {

                String type = "";

                String amount = "";

                //we need to get a visual representation of the type
                //and also its amount
                if (discount.discountType == 0) {

                    type = "% Percent";

                    amount = String.format("%.0f", discount.discountAmount * 100) + "%";

                } else {

                    type = "$ Dollar";

                    amount = "$" + String.format("%.2f", discount.discountAmount);

                }

                Object[] displayedInfo = {discount.discountCode, type, amount, discount.active == 1, dateFormatter.format(discount.startDate), dateFormatter.format(discount.endDate)};

                discounts.addRow(displayedInfo);

                displayedDiscounts.add(discount);

            }

        }

        discountsTable.setModel(discounts);

        //also set the displays to invisible
        createDiscounts.setVisible(false);
        selectedDiscount.setVisible(false);

    }

    public void displayDiscount(int index) {

        if (index != -1) {

            //that means we have selected a row
            //now display
            //for some data we will need to do some checking
            if (displayedDiscounts.get(index).level == 0) {
                if (displayedDiscounts.get(index).discountType == 0) {

                    typeLabel.setText("Precentage Off At Cart Level");
                    amountOff.setText("" + String.format("%.0f", displayedDiscounts.get(index).discountAmount * 100));

                } else {

                    typeLabel.setText("Dollar Amount Off At Cart Level");
                    amountOff.setText("" + String.format("%.0f", displayedDiscounts.get(index).discountAmount));

                }
            } else {

                if (displayedDiscounts.get(index).discountType == 0) {

                    typeLabel.setText("Precentage Off At Item Level");
                    amountOff.setText("" + String.format("%.0f", displayedDiscounts.get(index).discountAmount * 100));

                } else {

                    typeLabel.setText("Dollar Amount Off At Item Level");
                    amountOff.setText("" + String.format("%.0f", displayedDiscounts.get(index).discountAmount));

                }

            }
            codeField.setText(displayedDiscounts.get(index).discountCode);
            gameDiscountedTitle.setText(displayedDiscounts.get(index).game);

            dateStart.setText(dateFormatter.format(displayedDiscounts.get(index).startDate));
            dateEnd.setText(dateFormatter.format(displayedDiscounts.get(index).endDate));

            if (displayedDiscounts.get(index).endDate.before(new Date())) {

                currentStatus.setText("Expired");

            } else {

                currentStatus.setText("Not Expired");

            }

            activeCheck.setSelected(displayedDiscounts.get(index).active == 1);

            descriptionText.setText(displayedDiscounts.get(index).description);

            selectedDiscount.setVisible(true);
            createDiscounts.setVisible(false);

            successLabel.setVisible(false);
            discountErrorLabel1.setVisible(false);
            discountErrorInstruction1.setVisible(false);
            codeEditError.setVisible(false);
            descEditError.setVisible(false);
            amountEditError.setVisible(false);

            Variables.selectedCode = displayedDiscounts.get(index).discountCode;

        }

    }

    public void displayCreateDiscounts() {

        //first thing we should do is make everything its default value
        amountField.setText("");
        createCodeField.setText("");
        descriptionField.setText("");

        discountType = "Percent";

        //for the date we need to get a bit more creative
        //what we will do is have a date variable that will be added and subtracted and be displayed in the spinnerboxes
        //right now just set their default values
        selectedStartDate = currentDate;
        selectedEndDate = selectedStartDate.plusDays(1);
        startDateField.setText(selectedStartDate.format(formatter));
        endDateField.setText(selectedEndDate.format(formatter));

        typeChosen.setText("Type: " + discountType);

        activeBox.setSelected(true);

        gameBox.setSelectedIndex(0);
        gameBox.setEnabled(false);
        discountLevel = "Cart";
        levelChosen.setText("Level: Cart");

        selectedDiscount.setVisible(false);
        createDiscounts.setVisible(true);

        discountErrorLabel.setVisible(false);
        discountErrorInstruction.setVisible(false);

        codeError.setVisible(false);
        descError.setVisible(false);
        amountError.setVisible(false);
        gameError.setVisible(false);

    }

    //INVENTORY
    //we are starting off by copying and pasting some inventory functions
    public void displayGames(String search) {

        gamesDisplayed.clear();
        selectedGame = 0;

        //It is very simple what we will do here
        //create these panels as sort of like items on as tore page for each game in the lsit
        //We need to clear the original panel first so we can run this different times
        itemContainer.removeAll();

        //also set it back to the default size
        //itemContainer.setPreferredSize(PREVIOUS_SIZE);
        //We will need to create variables in order to seperate the items
        int addX = 0;
        int addY = 0;

        //also reset the size of the container
        itemContainer.setPreferredSize(originalSize);

        //Now check the search
        if (search.equals("ALL")) {

            int gamesShown = 0;

            for (int x = 0; x < Lists.games.size(); x++) {

                //First create a new game display object
                //Before we do we need to check if the filters are satisfied
                //We need to do another for loop
                //we will make variables that need to be checked to proceed with creating the object
                boolean consoleSearchable = false;
                boolean genreSearchable = false;
                boolean statusSearchable = false;

                for (int y = 0; y < consoleFiltersList.size(); y++) {

                    if (consoleFiltersList.get(y).getText().equals(Lists.games.get(x).system)) {

                        //Now check if its being searched for or not
                        if (consoleFiltersList.get(y).isSelected()) {

                            consoleSearchable = true;
                            y = consoleFiltersList.size();

                        } else {

                            consoleSearchable = false;
                            y = consoleFiltersList.size();

                        }

                    }

                }

                for (int z = 0; z < genreFiltersList.size(); z++) {

                    if (genreFiltersList.get(z).getText().equals(Lists.games.get(x).genre)) {

                        //Now check if its being searched for or not
                        if (genreFiltersList.get(z).isSelected()) {

                            genreSearchable = true;
                            z = consoleFiltersList.size();

                        } else {

                            consoleSearchable = false;
                            z = consoleFiltersList.size();

                        }

                    }

                }

                if (Lists.games.get(x).active == 1) {

                    statusSearchable = true;

                } else {

                    if (disabledGameCheck) {

                        statusSearchable = true;

                    } else {

                        statusSearchable = false;

                    }

                }

                if (consoleSearchable && genreSearchable && statusSearchable) {
                    GameDisplay gameItem = new GameDisplay();
                    System.out.println(x);

                    gameItem.gameTitle.setText(Lists.games.get(x).name);
                    gameItem.gamePrice.setText("$" + String.format("%.2f", Lists.games.get(x).price));
                    gameItem.systemLabel.setText(Lists.games.get(x).system);
                    gameItem.quantityLabel.setText(Lists.games.get(x).quantity + " Left");
                    gameItem.genreLabel.setText(Lists.games.get(x).genre);

                    //System.out.println(System.getProperty("user.dir") + "\\ThumbnailPlaceholder.png");
                    //ImageIcon icon = new ImageIcon(System.getProperty("user.dir") + "\\src\\ThumbnailPlaceholder.png");
                    //gameItem.image.setIcon(icon);
                    //Make sure you give the index as well
                    //This is so we can use it to display things in the context panel
                    for (Object[] desiredImage : Lists.images) {

                        //check if we get an image
                        if ((int) desiredImage[0] == Lists.games.get(x).gameID && (int) desiredImage[1] == 0) {


                            byte[] imgBlob = (byte[]) desiredImage[2];
                            //byte[] b = imgBlob.getBytes(1, (int) imgBlob.length());
                            ImageIcon newImage = new ImageIcon(imgBlob);
                            Image tempImage = newImage.getImage();
                            gameItem.image.setIcon(new ImageIcon(tempImage.getScaledInstance(226, 126, 0)));

                        }

                    }

                    gameItem.indexHeld = x;

                    //a little print statement for testing
                    System.out.println(gameItem.gameTitle.getText());

                    //Check if were on an even number
                    //Any factor of two will be start of the next row
                    if (gamesShown % 3 == 0) {

                        //Set the x back to default
                        addX = 10;

                        //now chaqnge the y value
                        addY += 370;

                        Dimension newSize = new Dimension(itemContainer.getWidth(), itemContainer.getPreferredSize().height + 370);

                        itemContainer.setPreferredSize(newSize);
                        itemContainer.repaint();
                        itemDisplay.repaint();
                        System.out.println(x + "2");

                    }

//                if (x % 4 == 0) {
//
//                    
//                    Dimension newSize = new Dimension(itemContainer.getWidth(), itemContainer.getPreferredSize().height + 370);
//
//                    itemContainer.setPreferredSize(newSize);
//                    itemContainer.repaint();
//                    itemDisplay.repaint();
//
//                }
                    //Now edit the display when we put the 4th item on
                    //this is so we dont overshow the display
                    gameItem.setBounds(itemContainer.getX() + addX, 0 - 380 + addY, 226, 350);

                    System.out.println(Lists.games.get(x).name);

                    addX += 250;

                    //And now add the game display item to the item container
                    itemContainer.add(gameItem);

                    gameItem.setVisible(true);

                    itemContainer.repaint();
                    itemDisplay.repaint();

                    gamesShown++;

                    gamesDisplayed.add(gameItem);

                }
            }
        } else {

            //since were working for a search we need to use a counter variable to count how many games we put up
            int gamesShown = 0;

            //lets create a pattern object
            Pattern gamePattern = Pattern.compile(search + "+");

            //Now repeat the same thing but this time use the funtion and/or filters
            for (int x = 0; x < Lists.games.size(); x++) {

                //one thing we will do differently is check if the criteria is met
                //Make a matcher object
                Matcher gameMatch = gamePattern.matcher(Lists.games.get(x).name.toLowerCase());

                if (gameMatch.find()) {

                    boolean consoleSearchable = false;
                    boolean genreSearchable = false;
                    boolean statusSearchable = false;

                    for (int y = 0; y < consoleFiltersList.size(); y++) {

                        if (consoleFiltersList.get(y).getText().equals(Lists.games.get(x).system)) {

                            //Now check if its being searched for or not
                            if (consoleFiltersList.get(y).isSelected()) {

                                consoleSearchable = true;
                                y = consoleFiltersList.size();

                            } else {

                                consoleSearchable = false;
                                y = consoleFiltersList.size();

                            }

                        }

                    }

                    for (int z = 0; z < genreFiltersList.size(); z++) {

                        if (genreFiltersList.get(z).getText().equals(Lists.games.get(x).genre)) {

                            //Now check if its being searched for or not
                            if (genreFiltersList.get(z).isSelected()) {

                                genreSearchable = true;
                                z = consoleFiltersList.size();

                            } else {

                                consoleSearchable = false;
                                z = consoleFiltersList.size();

                            }

                        }

                    }

                    if (Lists.games.get(x).active == 1) {

                        statusSearchable = true;

                    } else {

                        if (disabledGameCheck) {

                            statusSearchable = true;

                        } else {

                            statusSearchable = false;

                        }

                    }

                    if (consoleSearchable && genreSearchable && statusSearchable) {

                        GameDisplay gameItem = new GameDisplay();

                        gameItem.gameTitle.setText(Lists.games.get(x).name);
                        gameItem.gamePrice.setText("$" + String.format("%.2f", Lists.games.get(x).price));
                        gameItem.systemLabel.setText(Lists.games.get(x).system);
                        gameItem.quantityLabel.setText(Lists.games.get(x).quantity + " Left");
                        gameItem.genreLabel.setText(Lists.games.get(x).genre);

                        //ImageIcon icon = new ImageIcon(System.getProperty("user.dir") + "\\src\\ThumbnailPlaceholder.png");
                        //gameItem.image.setIcon(icon);
                        //Make sure you give the index as well
                        //This is so we can use it to display things in the context panel
                        for (Object[] desiredImage : Lists.images) {

                            //check if we get an image
                            if ((int) desiredImage[0] == Lists.games.get(x).gameID && (int) desiredImage[1] == 0) {


                                byte[] imgBlob = (byte[]) desiredImage[2];
                                //byte[] b = imgBlob.getBytes(1, (int) imgBlob.length());
                                
                                ImageIcon newImage = new ImageIcon(imgBlob);
                                Image tempImage = newImage.getImage();
                                gameItem.image.setIcon(new ImageIcon(tempImage.getScaledInstance(226, 126, 0)));

                            }

                        }

                        gameItem.indexHeld = x;

                        //a little print statement for testing
                        System.out.println(gameItem.gameTitle.getText());

                        //Check if were on an even number
                        //Any factor of two will be start of the next row
                        if (gamesShown % 3 == 0) {

                            //Set the x back to default
                            addX = 10;

                            //now chaqnge the y value
                            addY += 370;

                            //Make the item container bigger in height to fit more items
                            //First make a new dimension to store it
                            Dimension newSize = new Dimension(itemContainer.getWidth(), itemContainer.getHeight() + addY);

                            itemContainer.setPreferredSize(newSize);
                            itemContainer.repaint();
                            itemDisplay.repaint();
                            System.out.println("Is running");

                        }

                        gameItem.setBounds(itemContainer.getX() + addX, 0 - 380 + addY, 226, 350);

                        addX += 250;

                        //And now add the game display item to the item container
                        itemContainer.add(gameItem);

                        gameItem.setVisible(true);

                        itemContainer.repaint();
                        itemDisplay.repaint();

                        gamesShown += 1;

                        gamesDisplayed.add(gameItem);
                    }
                }

            }
        }

        //Now we will reset the search panel
        itemDisplay.revalidate();

        createNotificationsCount();

        gamePanel.setVisible(false);

    }

    public static void displayGame(int x) {

        //Make the item display show
        if (x != -1) {

            gamePanel.setVisible(true);

            //set the index to
            index = x;

            gameTitle.setText(Lists.games.get(index).name);
            gameSystem.setText(Lists.games.get(index).system);
            gameGenre.setText(Lists.games.get(index).genre);
            priceField.setText(String.format("%.2f", Lists.games.get(index).price));
            quantityAvailable.setValue(Lists.games.get(index).quantity);
            restockThreshold.setValue(Lists.games.get(index).restock);
            gameDescription.setText(Lists.games.get(index).description);

            gameDescription.setVisible(true);

            priceError.setVisible(false);
            editDescError.setVisible(false);
            editTitleError.setVisible(false);

            imageWarning.setVisible(false);
            imageInstruction.setVisible(false);

            gameEnabledBox.setSelected(Lists.games.get(index).active == 1);

            //ImageIcon icon = new ImageIcon(System.getProperty("user.dir") + "\\src\\MainImagePlaceholder.png");
            //image.setText("");
            //image.setIcon(icon);
            warning1.setVisible(false);
            warning2.setVisible(false);

            boolean hasImage = false;

            for (Object[] desiredImage : Lists.images) {

                //check if we get an image
                if ((int) desiredImage[0] == Lists.games.get(x).gameID && (int) desiredImage[1] == 1) {


                    byte[] imgBlob = (byte[]) desiredImage[2];
                    //byte[] b = imgBlob.getBytes(1, (int) imgBlob.length());
                    
                    ImageIcon newImage = new ImageIcon(imgBlob);
                    Image tempImage = newImage.getImage();
                    image.setIcon(new ImageIcon(tempImage.getScaledInstance(256, 126, 0)));
                    hasImage = true;

                }

            }

            if (!hasImage) {

                //set the default image
                image.setIcon((Icon) Lists.images.get(0)[2]);
            }

            gamePanel.repaint();

        }

    }

    //we need to create filters with this method
    public void createFilters() {

        Dimension filterPanelPreffered = new Dimension(191, Lists.consoles.size() * 30);

        consoleFiltersPanel.setPreferredSize(filterPanelPreffered);

        for (int x = 0; x < Lists.consoles.size(); x++) {

            JCheckBox newFilter = new JCheckBox(Lists.consoles.get(x));
            newFilter.setBounds(50, x * 30, 100, 20);
            consoleFiltersPanel.add(newFilter);

            newFilter.setSelected(true);

            //and now add the new filter to the console
            consoleFiltersList.add(newFilter);

            newFilter.addItemListener(new java.awt.event.ItemListener() {
                public void itemStateChanged(java.awt.event.ItemEvent evt) {
                    filterChanged(evt);
                }
            });

        }

        filterPanelPreffered = new Dimension(191, Lists.genres.size() * 30);
        genreFiltersPanel.setPreferredSize(filterPanelPreffered);

        for (int x = 0; x < Lists.genres.size(); x++) {

            JCheckBox newFilter = new JCheckBox(Lists.genres.get(x));
            newFilter.setBounds(50, x * 30, 100, 20);
            genreFiltersPanel.add(newFilter);

            newFilter.setSelected(true);

            genreFiltersList.add(newFilter);

            newFilter.addItemListener(new java.awt.event.ItemListener() {
                public void itemStateChanged(java.awt.event.ItemEvent evt) {
                    filterChanged(evt);
                }
            });

        }

    }

    //everytime a filter is changed run this method
    public void filterChanged(ItemEvent e) {

        searchGames();

    }

    public void searchGames() {

        if (searchBar.getText().isBlank()) {

            displayGames("ALL");

        } else {

            displayGames(searchBar.getText().toLowerCase());

        }

    }

    public void uploadImage(int imageType) throws FileNotFoundException {

        imageWarning.setVisible(false);
        imageInstruction.setVisible(false);

        FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("Image Files", "png", "jpg");

        JFileChooser newImage = new JFileChooser();

        newImage.setAcceptAllFileFilterUsed(false);
        newImage.setFileFilter(fileFilter);

        newImage.setMultiSelectionEnabled(false);

        newImage.showOpenDialog(this);

        //now we get the image selected
        File image = newImage.getSelectedFile();

        //now we upload it and use it
        //it being uploaded to the database will be done in a new sql class
        //incase uploading fails do not change any images until its uploaded
        FileInputStream imageFile = new FileInputStream(image.getPath());

        try {
            byte[] b = new byte[imageFile.available()];

            imageFile.read(b);

            boolean deleteImage = false;
            int imageIndex = 0;

            if (SQLImages.uploadImage(Lists.games.get(index).gameID, imageType, b)) {

                //image succeeded in uploading now we can add the image
                //however we may run into an issue if the this a new image on an already existing image
                //so we will go through the array list and check if they exist and if so we will delete and overwrite
                for (Object[] imageInfo : Lists.images) {

                    if ((int) imageInfo[0] == Lists.games.get(index).gameID) {

                        //were not done yet we need to see the type matches as well
                        if ((int) imageInfo[1] == imageType) {

                            imageIndex = Lists.images.indexOf(imageInfo);
                            deleteImage = true;

                        }

                    }

                }

                if (deleteImage) {

                    Lists.images.remove(imageIndex);

                }

                //and once its done we will upload the image to image infro
                ImageIcon newIcon = new ImageIcon(b);

                Image tempIcon = newIcon.getImage();

                //also make sure to make it a blob to add it to the list
                byte[] newBlob = (byte[]) SQLImages.retrieveImage(Lists.games.get(index).gameID, imageType);

                Object[] newInfo = {Lists.games.get(index).gameID, imageType, newBlob};

                Lists.images.add(newInfo);
                displayGame(index);
                searchGames();
                imageWarning.setVisible(false);
                imageInstruction.setVisible(false);

            } else {

                //we need to have the error appear
                imageWarning.setVisible(true);
                imageInstruction.setVisible(true);

            }

        } catch (IOException ex) {

            System.out.println("Can not read file");
            ex.printStackTrace();
            imageWarning.setVisible(true);
            imageInstruction.setVisible(true);

        }

    }

    public void createNotificationsCount() {

        int notificationAmount = 0;

        for (Game game : Lists.games) {

            //first check what the threshold is
            if (game.restock == 0) {

                //make sure its
                if (game.restock >= game.quantity) {

                    notificationAmount++;

                }

            } else {

                if (game.restock > game.quantity) {

                    notificationAmount++;

                }

            }

            notifAmount.setText("Notifications: " + notificationAmount);

        }

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox activeBox;
    private javax.swing.JCheckBox activeCheck;
    private javax.swing.JCheckBox activeDiscountCheck;
    private javax.swing.JButton addButton;
    private javax.swing.JLabel addressError;
    private javax.swing.JLabel amountBuyingLabel;
    private javax.swing.JLabel amountEditError;
    private javax.swing.JLabel amountError;
    private javax.swing.JTextField amountField;
    private javax.swing.JLabel amountLabel;
    private javax.swing.JTextField amountOff;
    private javax.swing.JButton cartButton;
    private javax.swing.JLabel chooseLevel;
    private javax.swing.JLabel chooseType;
    private javax.swing.JLabel cityError;
    private javax.swing.JTextField cityField;
    private javax.swing.JLabel cityLabel;
    private javax.swing.JLabel codeEditError;
    private javax.swing.JLabel codeError;
    private javax.swing.JTextField codeField;
    private javax.swing.JLabel codeLabel;
    private javax.swing.JButton consoleAll;
    private javax.swing.JScrollPane consoleFilters;
    private javax.swing.JPanel consoleFiltersPanel;
    private javax.swing.JLabel consoleLabel;
    private javax.swing.JPanel contextPanel;
    private javax.swing.JPanel controlPanelDiscounts;
    private javax.swing.JPanel controlPanelInventory;
    private javax.swing.JPanel controlPanelUsers;
    private javax.swing.JLabel controls1;
    private javax.swing.JLabel controls2;
    private javax.swing.JLabel controls3;
    private javax.swing.JLabel controlsLabel;
    private javax.swing.JLabel createAmountLabel;
    private javax.swing.JButton createButton;
    private javax.swing.JTextField createCodeField;
    private javax.swing.JLabel createCodeLabel;
    private javax.swing.JLabel createDescriptionLabel;
    private javax.swing.JButton createDiscountButton;
    private javax.swing.JPanel createDiscounts;
    private javax.swing.JLabel currentStatus;
    private javax.swing.JCheckBox customerBox;
    private javax.swing.JLabel dateEnd;
    private javax.swing.JLabel dateStart;
    private javax.swing.JLabel descEditError;
    private javax.swing.JLabel descError;
    private javax.swing.JLabel descLabel;
    private javax.swing.JTextArea descriptionField;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JTextArea descriptionText;
    private javax.swing.JCheckBox disabledAccount;
    private javax.swing.JCheckBox disabledGamesCheck;
    private javax.swing.JLabel discountErrorInstruction;
    private javax.swing.JLabel discountErrorInstruction1;
    private javax.swing.JLabel discountErrorLabel;
    private javax.swing.JLabel discountErrorLabel1;
    private javax.swing.JButton discountRefresh;
    private javax.swing.JButton discountReturnButton;
    private javax.swing.JPanel discountsMenu;
    private javax.swing.JTable discountsTable;
    private javax.swing.JLayeredPane displayDiscounts;
    private javax.swing.JButton dollarButton;
    private static javax.swing.JLabel editDescError;
    private static javax.swing.JLabel editTitleError;
    private javax.swing.JLabel emailError;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JCheckBox enabledAccount;
    private javax.swing.JCheckBox enabledBox;
    private javax.swing.JSpinner endDateArrows;
    private javax.swing.JTextField endDateField;
    private javax.swing.JLabel endDateLabel;
    private javax.swing.JLabel endLabel;
    private javax.swing.JButton exitCreateButton;
    private javax.swing.JCheckBox expiredBox;
    private javax.swing.JLabel filterInstructions;
    private javax.swing.JToggleButton filtersButton;
    private javax.swing.JPanel filtersPanel;
    private javax.swing.JPanel filtersPanelDiscounts;
    private javax.swing.JTextField firstField;
    private javax.swing.JLabel firstLabel;
    private javax.swing.JLabel formatSuggestion;
    private javax.swing.JLabel formatSuggestion1;
    private javax.swing.JComboBox<String> gameBox;
    private static javax.swing.JTextArea gameDescription;
    private javax.swing.JLabel gameDiscountedLabel;
    private javax.swing.JLabel gameDiscountedTitle;
    private static javax.swing.JCheckBox gameEnabledBox;
    private javax.swing.JLabel gameError;
    private static javax.swing.JLabel gameGenre;
    private javax.swing.JLabel gameLabelDiscount;
    private static javax.swing.JPanel gamePanel;
    private javax.swing.JButton gameSaveButton;
    private static javax.swing.JLabel gameSystem;
    private static javax.swing.JTextArea gameTitle;
    private javax.swing.JButton genreAll;
    private javax.swing.JScrollPane genreFilters;
    private javax.swing.JPanel genreFiltersPanel;
    private javax.swing.JLabel genreLabel;
    private javax.swing.JButton helpButton;
    private javax.swing.JButton helpButton1;
    private javax.swing.JButton helpButton2;
    private static javax.swing.JLabel image;
    private static javax.swing.JLabel imageInstruction;
    private static javax.swing.JLabel imageWarning;
    private javax.swing.JCheckBox inactiveCheck;
    private javax.swing.JLayeredPane infoContainer;
    private javax.swing.JPanel inventoryFiltersPanel;
    private javax.swing.JPanel inventoryMenu;
    private javax.swing.JButton inventoryReportBttuon;
    private javax.swing.JButton itemButton;
    private javax.swing.JPanel itemContainer;
    private javax.swing.JScrollPane itemDisplay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField lastField;
    private javax.swing.JLabel lastLabel;
    private javax.swing.JLabel levelChosen;
    private javax.swing.JLabel levelLabel;
    private javax.swing.JTextField line1;
    private javax.swing.JLabel line1Label;
    private javax.swing.JTextField line2;
    private javax.swing.JLabel line2Label;
    private javax.swing.JTextField line3;
    private javax.swing.JLabel line3Label;
    private javax.swing.JButton logOutButton;
    private javax.swing.JButton logOutButton1;
    private javax.swing.JButton logOutButton2;
    private javax.swing.JButton mainImageButton;
    private javax.swing.JCheckBox managerBox;
    private javax.swing.JComboBox<String> maxDay;
    private javax.swing.JComboBox<String> maxMonth;
    private javax.swing.JComboBox<String> maxYear;
    private javax.swing.JComboBox<String> minDay;
    private javax.swing.JComboBox<String> minMonth;
    private javax.swing.JComboBox<String> minYear;
    private javax.swing.JLabel nameError;
    private javax.swing.JButton newGameButton;
    private javax.swing.JLabel notifAmount;
    private javax.swing.JButton notificationsButton;
    private javax.swing.JLayeredPane panelContainers;
    private javax.swing.JLabel passwordError;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JButton percentButton;
    private javax.swing.JLabel phoneError;
    private javax.swing.JTextField phoneField;
    private javax.swing.JLabel phoneLabel;
    private javax.swing.JButton posButton;
    private static javax.swing.JLabel priceError;
    private static javax.swing.JTextField priceField;
    private javax.swing.JLabel priceLabel;
    private static javax.swing.JSpinner quantityAvailable;
    private javax.swing.JButton quitButton;
    private javax.swing.JButton quitButton1;
    private javax.swing.JButton quitButton2;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton refreshButton1;
    private javax.swing.JLabel restockLabel;
    private static javax.swing.JSpinner restockThreshold;
    private javax.swing.JButton returnButton;
    private javax.swing.JButton returnUserButton;
    private javax.swing.JButton salesReportButton;
    private javax.swing.JPanel salesReportPanel;
    private javax.swing.JLabel satusLabel;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton saveButtonDiscounts;
    private javax.swing.JTextField searchBar;
    private javax.swing.JButton searchButton;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JPanel selectedDiscount;
    private javax.swing.JPanel selectedUser;
    private javax.swing.JLabel showLabel;
    private javax.swing.JScrollPane shownDiscounts;
    private javax.swing.JSpinner startDateArrows;
    private javax.swing.JTextField startDateField;
    private javax.swing.JLabel startDateLabel;
    private javax.swing.JLabel startLabel;
    private javax.swing.JComboBox<String> stateBox;
    private javax.swing.JLabel stateError;
    private javax.swing.JLabel stateLabel;
    private javax.swing.JLabel successLabel;
    private javax.swing.JButton thumbnailButton;
    private javax.swing.JLabel typeChosen;
    private javax.swing.JLabel typeLabel;
    private javax.swing.JCheckBox unexpiredBox;
    private javax.swing.JScrollPane userDisplay;
    private javax.swing.JLabel userErrorInstruction;
    private javax.swing.JLabel userErrorLabel;
    private javax.swing.JLayeredPane userInfo;
    private javax.swing.JLabel userSuccessLabel;
    private javax.swing.JTable userTable;
    private javax.swing.JLabel usernameError;
    private javax.swing.JTextField usernameField;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JPanel usersMenu;
    private javax.swing.JPanel usersShown;
    private javax.swing.JButton viewButton;
    private static javax.swing.JLabel warning1;
    private static javax.swing.JLabel warning2;
    private javax.swing.JLabel zipError;
    private javax.swing.JTextField zipField;
    private javax.swing.JLabel zipLabel;
    // End of variables declaration//GEN-END:variables
}
