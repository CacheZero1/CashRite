package euorg.nuvoprojects.cachezero1;

import java.awt.List;
import java.util.LinkedHashMap;

import javax.swing.UIManager;

import euorg.nuvoprojects.cachezero1.cryptor.Cryptor;
import euorg.nuvoprojects.cachezero1.gui.MainWindow;
import euorg.nuvoprojects.cachezero1.literates.LanguageHandler;
import euorg.nuvoprojects.cachezero1.literates.SaveHandler;

public class Main {

    private static LanguageHandler langHandler = new LanguageHandler();
    private static SaveHandler handler = new SaveHandler(".", langHandler);

    private static Boolean disclaimerAccepted = false;
    private static Boolean passwordSet = false;
    private static Boolean saveOnExit = true;
    private static Boolean isDarkMode = false;

    public static void main(String[] args) {

        /*
         * Date of creation: 04.08.2024
         * Developers: CacheZero1
         * License: MIT
        */

        final String version = "v0.1";


        // Create save
        handler.setupSave();

        // Check for dark mode
        //try {
        //    if (handler.getDataMap().get(Utility.modeName).equals(Utility.darkMode)) {
        //        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        //        isDarkMode = true;
        //    }
        //} catch (Exception lookAndFeelException) {}

        // Main window
        MainWindow mainWindow = new MainWindow(version, handler, langHandler, saveOnExit, isDarkMode);
        mainWindow.setVisible(true);

    }    

}