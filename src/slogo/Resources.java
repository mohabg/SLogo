package slogo;

public class Resources {
    // Main
    public static final int framesPerSecond = 60;
    public static final int millisecondDelay = 1000 / framesPerSecond;
    public static final double secondDelay = 1.0 / framesPerSecond;

    // GUI
    public static final String TITLE = "SLogo";

    // CommandWindow
    public static final String ERROR_TEXT_STYLE = "-fx-text-fill: rgb(255,0,0)";

    private Resources () {
        // Do nothing
    }

    // public static methods

}
