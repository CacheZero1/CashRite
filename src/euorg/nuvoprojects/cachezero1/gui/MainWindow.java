package euorg.nuvoprojects.cachezero1.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;

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

    private static Boolean saveOnExit;
    private static Boolean isDarkMode;

    // Components (JMenu)
    private JMenuBar menuBar;
    //
    private JMenu cashRiteMenu;
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
    private JScrollPane sheetSelectionPane;
    private JPanel leftPanel;
    private JPanel rightPanel;

    private JScrollPane tablePane;
    private JPanel optionPanel;

    private JPanel cellActionPane;
    private JPanel filteringActionPane;

    private JScrollPane infoPanel;
    private JPanel statisticPanel;
    private JPanel graphPanel;
    private JPanel reminderPanel;
    private JButton graphStatisticToggleButton;

    // Components (Functional)
    private static List<JButton> sheetSelectionButtonList;
    private static JTable mainTable;

    private JLabel cellActionLabel;
    private JButton newEntryButton;
    private JButton deleteEntryButton;
    private JButton editEntryButton;

    private JLabel filteringActionLabel;
    private JButton findEntryButton;

    private JButton saveButton;


    

    public MainWindow(String version, SaveHandler handler, LanguageHandler langHandler, Boolean exitSave, Boolean darkMode) {

        // Globals
        saveHandler = handler;
        languageHandler = langHandler;

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
        exportMenuItem = new JMenuItem();
        importMenuItem = new JMenuItem();
        aboutMenuItem = new JMenuItem();
        exitMenuItem = new JMenuItem();
        
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

        
    }

    // Create functionals
    private void createFunctionalComponents() {

        // Table
        mainTable = new JTable();

    }

    // Add all to JFrame
    private void addGUIComponents() {

        // JMenu
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

    }

    // Set texts
    private void applyTexts() {

        HashMap<String, String> textMap = languageHandler.getLangMap(saveHandler.getDataMapLang());

        // Menu
        

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
