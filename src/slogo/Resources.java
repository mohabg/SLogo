package slogo;

@Deprecated
public class Resources {
    // GUI
    public static final String RUN_MENU_LABEL = "Run";

    // CommandWindow
    public static final String ERROR_TEXT_STYLE = "-fx-text-fill: rgb(255,0,0)";
    public static final String CONSOLE_PROMPT_STR = "\n> ";

    // Debugging
    public static final boolean DEBUG_FLAG = true;

    private Resources () {
        // Do nothing
    }

    // public static methods
    public static void debugPrint (String out) {
        if (DEBUG_FLAG) {
            System.out.println(out);
        }
    }
}
