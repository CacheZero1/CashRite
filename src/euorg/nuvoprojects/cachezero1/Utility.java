package euorg.nuvoprojects.cachezero1;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

public class Utility {

    // Valid characters
    public static final List<String> validCharsList = Arrays.asList(
    "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","ß",
    " ", ",", ".", "'", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "!", "§", "%", "&", "/", "(", ")", "=", "?", "*", "+", "#", ";", "$","\n", "\t", "|",
    "&#8352", "&#8353", "&#8354", "&#8355", "&#8356", "&#8357", "&#8358", "&#8359", "&#8360", "&#8361", "&#8362", "&#8363", "&#8364", "&#8365", "&#8366", "&#8367", "&#8368", "&#8369", "&#8370", "&#8371", "&#8372", "&#8373", "&#8374", "&#8375", "&#8376", "&#8377", "&#8378", "&#8379", "&#8380", "&#8381", "&#8382", "&#8383"
    );

    // ------- <Getters> -------
    // Font default
    public static final String fontDefaultFamily = "Default";

    // Fonts
    public static final String titleFontName = "title";
    public static final String subtitleFontName = "subtitle";
    public static final String selectionFontName = "selection";
    public static final String inputFontName = "input";

    // Font fragments
    public static final String titleFamilyName = "titleFamily";
    public static final String titleStyleName = "titleStyle";
    public static final String titleSizeName = "titleSize";

    public static final String subtitleFamilyName = "subtitleFamily";
    public static final String subtitleStyleName = "subtitleStyle";
    public static final String subtitleSizeName = "subtitleSize";

    public static final String selectionFamilyName = "selectionFamily";
    public static final String selectionStyleName = "selectionStyle";
    public static final String selectionSizeName = "selectionSize";

    public static final String inputFamilyName = "inputFamily";
    public static final String inputStyleName = "inputStyle";
    public static final String inputSizeName = "inputSize";

    // Data
    public static final String langName = "lang";
    public static final String modeName = "mode";
    // ------- </Getters> -------

    // ------- <Getters> -------
    // Exception messages
    public static final String graErr = "err1";
    public static final String calErr = "err2";
    public static final String wriFilErr = "err3";
    public static final String reaFilErr = "err4";
    public static final String savFilErr = "err5";
    public static final String minErr = "exc1";
    public static final String majErr = "exc2";

    // Main Window Menu
    public static final String menSave = "maiWinMenSav";
    public static final String menExchangeRates = "maiWinMenExcRat";
    public static final String menExport = "maiWinMenExp";
    public static final String menImport = "maiWinMenImp";
    public static final String menAbout = "maiWinMenAbo";
    public static final String menExit = "maiWinMenExi";
    public static final String menSheet = "maiWinMenShe";
    public static final String menNewSheet = "maiWinMenSheNew";
    public static final String menDeleteSheet = "maiWinMenSheDel";
    public static final String menRenameSheet = "maiWinMenSheRen";
    public static final String menView = "maiWinMenVie";
    public static final String menDaily = "maiWinMenVieDai";
    public static final String menWeekly = "maiWinMenVieWee";
    public static final String menMonthly = "maiWinMenVieMon";
    public static final String menYearly = "maiWinMenVieYea";
    public static final String menSettings = "maiWinMenSet";
    public static final String menFont = "maiWinMenSetFon";
    public static final String menColour = "maiWinMenSetCol";
    public static final String menLanguage = "maiWinMenSetLan";
    public static final String menHelp = "maiWinMenHel";
    public static final String menEncryption = "maiWinMenHelEnc";
    public static final String menUsage = "maiWinMenHelUsa";

    // Main Window Options
    public static final String optEntry = "maiWinOptEnt";
    public static final String optNewEntry = "maiWinOptEntNew";
    public static final String optDeleteEntry = "maiWinOptEntDel";
    public static final String optEditEntry = "maiWinOptEntEdi";
    public static final String optFilter = "maiWinOptFil";
    public static final String optFilterboxDate = "maiWinOptFilBox0";
    public static final String optFilterboxValue = "maiWinOptFilBox1";
    public static final String optFilterboxString = "maiWinOptFilBox2";
    public static final String optFilterSearch = "maiWinOptFilSea";

    // Cryptor Panel
    public static final String cryPanFor = "cryPanFor";
    public static final String cryPanNorTex = "cryPanNorTex";
    public static final String cryPanEncTex = "cryPanEncTex";
    public static final String cryPanSta = "cryPanSta";
    public static final String cryPanEnc = "cryPanEnc";
    public static final String cryPanDec = "cryPanDec";

    // Tartarus Panel
    public static final String tarPanRGBFor = "tarPanRGBFor";
    public static final String tarPanRGBRed = "tarPanRGBRed";
    public static final String tarPanRGBGreen = "tarPanRGBGre";
    public static final String tarPanRGBBlue = "tarPanRGBBlu";
    public static final String tarPanChooseImage = "tarPanImaCho";

    // Save Text Menu
    public static final String savTexSavTex = "savTexSavTex";
    public static final String savTexSav = "savTexSav";
    public static final String savTexLef = "savTexLef";
    public static final String savTexRig = "savTexRig";
    public static final String fonMenStyPla = "fonMenStyPla";
    public static final String fonMenStyBol = "fonMenStyBol";
    public static final String fonMenStyIta = "fonMenStyIta";
    public static final String fonMenFam = "fonMenFam";
    public static final String fonMenSty = "fonMenSty";
    public static final String fonMenSiz = "fonMenSiz";
    public static final String fonMenTit = "fonMenTit";
    public static final String fonMenSub = "fonMenSub";
    public static final String fonMenSel = "fonMenSel";
    public static final String fonMenInp = "fonMenInp";
    public static final String fonMenFonSet = "fonMenFonSet";
    public static final String aboMenTit = "aboMenTit";
    public static final String aboMenDes1 = "aboMenDes1";
    public static final String aboMenDes2 = "aboMenDes2";
    public static final String aboMenDes3 = "aboMenDes3";
    public static final String lanMenTit = "lanMenTit";
    public static final String colMenTit = "colMenTit";
    public static final String colMenRes = "colMenRes";

    // Save Image Menu
    public static final String savImgSavImg = "savImgSavImg";

    // General
    public static final String accept = "accept";
    public static final String close = "close";
    public static final String help = "help";
    public static final String cancel = "cancel";
    public static final String info = "info";
    // ------- </Getters> -------

    // ------- <Uniques> -------
    public static final String red = "r";
    public static final String green = "g";
    public static final String blue = "b";

    public static final String lightMode = "light";
    public static final String darkMode = "dark";

    public static final String saveOnClose = "soc";
    public static final String manualSave = "ms";
    // ------- </Uniques> -------

    // ------- <Colours> -------
    public static final Color backgroundDark = new Color(34, 37, 38);           // General background
    public static final Color optionBackgroundDark = new Color(45, 47, 49);     // JOptionPane background
    public static final Color titlebarDark = new Color(60, 61, 61);             // Menubar & Titlebar
    public static final Color comboboxDark = new Color(98, 99, 101);            // ComboBoxes
    public static final Color buttonDark = new Color(75, 75, 75);               // Buttons
    public static final Color sliderKnobDark = new Color(150, 151, 152);        // Slider knobs
    public static final Color sliderTickDark = new Color(87, 89, 90);           // Slider ticks
    public static final Color sliderBarDark = new Color(66, 68, 70);            // Slider bar

    public static final Color textColourDarkmode = new Color(240, 248, 255);
    // ------- </Colours> -------

}