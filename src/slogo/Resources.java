package slogo;

public class Resources {
    // Main
    public static final int framesPerSecond = 30;
    public static final int millisecondDelay = 1000 / framesPerSecond;
    public static final double secondDelay = 1.0 / framesPerSecond;
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;

    // GUI
    public static final String TITLE = "SLogo";
    public static final String RUN_BUTTON_LABEL = "Run";
    public static final String HBOX_BACKGROUND_COLOR = "-fx-background-color: #336699;";

    // CommandWindow
    public static final String ERROR_TEXT_STYLE = "-fx-text-fill: rgb(255,0,0)";
    public static final String CONSOLE_PROMPT_STR = "\n> ";

    private Resources () {
        // Do nothing
    }

    // public static methods

}
