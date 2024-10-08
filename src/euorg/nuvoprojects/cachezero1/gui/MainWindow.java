package euorg.nuvoprojects.cachezero1.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;

import euorg.nuvoprojects.cachezero1.Utility;
import euorg.nuvoprojects.cachezero1.literates.LanguageHandler;
import euorg.nuvoprojects.cachezero1.literates.SaveHandler;

public class MainWindow extends JFrame implements ActionListener {

    // Settings
    private static SaveHandler saveHandler;
    private static LanguageHandler languageHandler;

    private static String infoViewModeToggle;
    private final String textInfoMode = "stat";
    private final String imageInfoMode = "graph";

    private static Boolean changesSaved;
    private static Boolean saveOnExit;
    private static Boolean isDarkMode;

    // Components (JMenu)
    private JMenuBar menuBar;
    //
    private JMenu cashRiteMenu;
    private JMenuItem saveMenuItem;
    private JMenuItem convertMenuItem;
    private JMenuItem exportMenuItem;
    private JMenuItem importMenuItem;
    private JMenuItem aboutMenuItem;
    private JMenuItem exitMenuItem;
    //
    private JMenu sheetMenu;
    private JMenuItem newSheetMenuItem;
    private JMenuItem deleteSheetMenuItem;
    private JMenuItem renameSheetMenuItem;
    //
    private JMenu viewMenu;
    private JMenuItem dailyMenuItem;
    private JMenuItem weeklyMenuItem;
    private JMenuItem monthlyMenuItem;
    private JMenuItem yearlyMenuItem;
    //
    private JMenu settingsMenu;
    private JMenuItem fontMenuItem;
    private JMenuItem colourMenuItem;
    private JMenuItem languageMenuItem;
    //
    private JMenu helpMenu;
    private JMenuItem encryptionMenuItem;
    private JMenuItem usageMenuItem;

    // Components (Positioning)
    private JScrollPane sheetSelectionPanel;
    private JPanel leftPanel;

    private JScrollPane tablePanel;
    private JPanel tableRootPanel;
    private JPanel optionPanel;

    private JScrollPane infoPanel;
    private JPanel infoRootPanel;
    private JPanel statisticPanel;
    private JPanel graphPanel;
    private JScrollPane reminderPanel;

    // Components (Functional)
    private static List<JButton> sheetSelectionButtonList;
    private static JButton defaultSheetButton;
    private static JTable mainTable;

    private JButton graphStatisticToggleButton;
    private JTextArea reminderTextArea;

    private JLabel cellActionLabel;
    private JButton newEntryButton;
    private JButton deleteEntryButton;
    private JButton editEntryButton;

    private JLabel filteringActionLabel;
    private JButton findEntryButton;
    private JComboBox<String> filteringArgBox;
    private JTextField filteringArgsTextField;
    

    public MainWindow(String version, SaveHandler handler, LanguageHandler langHandler, Boolean exitSave, Boolean darkMode) {

        // Globals
        saveHandler = handler;
        languageHandler = langHandler;

        saveOnExit = exitSave;
        isDarkMode = darkMode;

        // Normal settings
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setMinimumSize(new Dimension(900, 600));
        this.setTitle("CashRite - (" + version + ")");
        this.setLayout(new BorderLayout());

        infoViewModeToggle = textInfoMode;

        // Settings with exception chance
        try {
            this.setIconImage(ImageIO.read(getClass().getResource("/images/icon_128px.png")));
        } catch (Exception e) {
            HashMap<String, String> langMap = languageHandler.getLangMap(saveHandler.getDataMapLang());
            new ErrorPane(darkMode, new ArrayList<String>(Arrays.asList(langMap.get(Utility.minErr), langMap.get(Utility.graErr), langMap.get(Utility.accept))));
        }

        // Instances

        // Populate GUI
        createJMenu();
        createPositioningComponents();
        createFunctionalComponents();
        applyTheme();
        addGUIComponents();
        applyTexts();
        applyFont();

    }


    // Create menu
    private void createJMenu() {

        // Main Menus
        menuBar = new JMenuBar();

        cashRiteMenu = new JMenu("CashRite");
        sheetMenu = new JMenu();
        viewMenu = new JMenu();
        settingsMenu = new JMenu();
        helpMenu = new JMenu();

        // CashRite Menu
        saveMenuItem = new JMenuItem();
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        convertMenuItem = new JMenuItem();
        exportMenuItem = new JMenuItem();
        importMenuItem = new JMenuItem();
        aboutMenuItem = new JMenuItem();
        exitMenuItem = new JMenuItem();
        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        
        // Sheet Menu
        newSheetMenuItem = new JMenuItem();
        deleteSheetMenuItem = new JMenuItem();
        renameSheetMenuItem = new JMenuItem();

        // View Menu
        dailyMenuItem = new JMenuItem();
        weeklyMenuItem = new JMenuItem();
        monthlyMenuItem = new JMenuItem();
        yearlyMenuItem = new JMenuItem();

        // Settings Menu
        fontMenuItem = new JMenuItem();
        colourMenuItem = new JMenuItem();
        languageMenuItem = new JMenuItem();

        // Help
        encryptionMenuItem = new JMenuItem();
        usageMenuItem = new JMenuItem();


        // ActionListeners
        saveMenuItem.addActionListener(this);
        convertMenuItem.addActionListener(this);
        exportMenuItem.addActionListener(this);
        importMenuItem.addActionListener(this);
        aboutMenuItem.addActionListener(this);
        exitMenuItem.addActionListener(exitAction -> {
            System.out.println("Saved!"); //TODO: save
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {}
            System.exit(0);
        });

        newSheetMenuItem.addActionListener(this);
        deleteSheetMenuItem.addActionListener(this);
        renameSheetMenuItem.addActionListener(this);

        dailyMenuItem.addActionListener(this);
        weeklyMenuItem.addActionListener(this);
        monthlyMenuItem.addActionListener(this);
        yearlyMenuItem.addActionListener(this);

        fontMenuItem.addActionListener(this);
        colourMenuItem.addActionListener(this);
        languageMenuItem.addActionListener(this);

        encryptionMenuItem.addActionListener(this);
        usageMenuItem.addActionListener(this);

    }

    // Create positioners
    private void createPositioningComponents() {

        // Sheet selection bar
        sheetSelectionPanel = new JScrollPane();
        sheetSelectionPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sheetSelectionPanel.setPreferredSize(new Dimension(sheetSelectionPanel.getWidth(), 33));

        // Table & action pane container
        leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());

        // Table & table option containers
        tableRootPanel = new JPanel();

        tablePanel = new JScrollPane(tableRootPanel);
        tablePanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tablePanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        optionPanel = new JPanel();
        optionPanel.setPreferredSize(new Dimension(optionPanel.getWidth(), 100));
        optionPanel.setLayout(new GridLayout(2, 4, 10, 10));

        // Info & graph & statistic & reminder containers
        infoRootPanel = new JPanel();
        infoRootPanel.setLayout(new BoxLayout(infoRootPanel, BoxLayout.Y_AXIS));

        infoPanel = new JScrollPane(infoRootPanel);
        infoPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        infoPanel.setPreferredSize(new Dimension(300, infoPanel.getHeight()));
        
        statisticPanel = new JPanel();

        graphPanel = new JPanel();

        reminderPanel = new JScrollPane();
        reminderPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        
    }

    // Create functionals
    private void createFunctionalComponents() {

        // Table
        mainTable = new JTable();

        // Option labels
        cellActionLabel = new JLabel();
        cellActionLabel.setFocusable(false);
        cellActionLabel.setHorizontalAlignment(JLabel.CENTER);

        filteringActionLabel = new JLabel();
        filteringActionLabel.setFocusable(false);
        filteringActionLabel.setHorizontalAlignment(JLabel.CENTER);

        // Option buttons & textfield & combobox
        newEntryButton = new JButton();
        newEntryButton.setFocusable(false);
        newEntryButton.addActionListener(this);

        deleteEntryButton = new JButton();
        deleteEntryButton.setFocusable(false);
        deleteEntryButton.addActionListener(this);

        editEntryButton = new JButton();
        editEntryButton.setFocusable(false);
        editEntryButton.addActionListener(this);

        findEntryButton = new JButton();
        findEntryButton.setFocusable(false);
        findEntryButton.addActionListener(this);

        filteringArgBox = new JComboBox<>();
        filteringArgBox.setFocusable(false);
        filteringArgBox.setEditable(false);

        filteringArgsTextField = new JTextField();

        // Reminder
        reminderTextArea = new JTextArea();
        reminderTextArea.setLineWrap(true);

    }

    // Add all to JFrame
    private void addGUIComponents() {

        // JMenu
        cashRiteMenu.add(saveMenuItem);
        cashRiteMenu.add(convertMenuItem);
        cashRiteMenu.add(exportMenuItem);
        cashRiteMenu.add(importMenuItem);
        cashRiteMenu.add(aboutMenuItem);
        cashRiteMenu.add(exitMenuItem);

        sheetMenu.add(newSheetMenuItem);
        sheetMenu.add(deleteSheetMenuItem);
        sheetMenu.add(renameSheetMenuItem);

        viewMenu.add(dailyMenuItem);
        viewMenu.add(weeklyMenuItem);
        viewMenu.add(monthlyMenuItem);
        viewMenu.add(yearlyMenuItem);

        settingsMenu.add(fontMenuItem);
        settingsMenu.add(colourMenuItem);
        settingsMenu.add(languageMenuItem);

        helpMenu.add(encryptionMenuItem);
        helpMenu.add(usageMenuItem);

        menuBar.add(cashRiteMenu);
        menuBar.add(sheetMenu);
        menuBar.add(viewMenu);
        menuBar.add(settingsMenu);
        menuBar.add(helpMenu);

        this.setJMenuBar(menuBar);

        // Left panel & table & table options
        optionPanel.add(cellActionLabel);
        optionPanel.add(newEntryButton);
        optionPanel.add(deleteEntryButton);
        optionPanel.add(editEntryButton);

        optionPanel.add(filteringActionLabel);
        optionPanel.add(filteringArgBox);
        optionPanel.add(filteringArgsTextField);
        optionPanel.add(findEntryButton);

        leftPanel.add(tablePanel);
        leftPanel.add(optionPanel, BorderLayout.SOUTH);

        // Right panel & info & reminder
        reminderPanel.setViewportView(reminderTextArea);

        infoRootPanel.add(statisticPanel);
        infoRootPanel.add(reminderPanel);

        // Add to this
        this.add(sheetSelectionPanel, BorderLayout.NORTH);
        this.add(leftPanel);
        this.add(infoPanel, BorderLayout.EAST);
        

    }

    // Set texts
    private void applyTexts() {

        HashMap<String, String> textMap = languageHandler.getLangMap(saveHandler.getDataMapLang());

        // Menu
        saveMenuItem.setText(textMap.get(Utility.menSave));
        convertMenuItem.setText(textMap.get(Utility.menExchangeRates));
        exportMenuItem.setText(textMap.get(Utility.menExport));
        importMenuItem.setText(textMap.get(Utility.menImport));
        aboutMenuItem.setText(textMap.get(Utility.menAbout));
        exitMenuItem.setText(textMap.get(Utility.menExit));

        sheetMenu.setText(textMap.get(Utility.menSheet));
        newSheetMenuItem.setText(textMap.get(Utility.menNewSheet));
        deleteSheetMenuItem.setText(textMap.get(Utility.menDeleteSheet));
        renameSheetMenuItem.setText(textMap.get(Utility.menRenameSheet));

        viewMenu.setText(textMap.get(Utility.menView));
        dailyMenuItem.setText(textMap.get(Utility.menDaily));
        weeklyMenuItem.setText(textMap.get(Utility.menWeekly));
        monthlyMenuItem.setText(textMap.get(Utility.menMonthly));
        yearlyMenuItem.setText(textMap.get(Utility.menYearly));

        settingsMenu.setText(textMap.get(Utility.menSettings));
        fontMenuItem.setText(textMap.get(Utility.menFont));
        colourMenuItem.setText(textMap.get(Utility.menColour));
        languageMenuItem.setText(textMap.get(Utility.menLanguage));

        helpMenu.setText(textMap.get(Utility.menHelp));
        encryptionMenuItem.setText(textMap.get(Utility.menEncryption));
        usageMenuItem.setText(textMap.get(Utility.menUsage));

        // Options
        cellActionLabel.setText(textMap.get(Utility.optEntry));
        newEntryButton.setText(textMap.get(Utility.optNewEntry));
        deleteEntryButton.setText(textMap.get(Utility.optDeleteEntry));
        editEntryButton.setText(textMap.get(Utility.optEditEntry));

        filteringActionLabel.setText(textMap.get(Utility.optFilter));
        filteringArgBox.addItem(textMap.get(Utility.optFilterboxDate));
        filteringArgBox.addItem(textMap.get(Utility.optFilterboxValue));
        filteringArgBox.addItem(textMap.get(Utility.optFilterboxString));
        findEntryButton.setText(textMap.get(Utility.optFilterSearch));

    }

    // Apply fonts
    private void applyFont() {

        

    }

    // Set theme
    private void applyTheme() {

        if (isDarkMode) {
            // ------- <Set backgrounds> -------
            // ------- </Set backgrounds> -------

            // ------- <Set text colour> -------
            // ------- </Set text colour> -------
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // ------- <Cipher selection> -------
        // ------- </Cipher selection> -------
    }
    
}
