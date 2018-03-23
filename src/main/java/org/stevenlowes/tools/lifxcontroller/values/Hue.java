package org.stevenlowes.tools.lifxcontroller.values;

/*
 * Constant values to be Assigned to Hue in HSBK Objects
 */

public final class Hue {
    public static final int RED = 0;
    public static final int ORANGE = (int) (30F / 360F * Levels.MAX);
    public static final int YELLOW = (int) (60F / 360F * Levels.MAX);
    public static final int LIME = (int) (90 / 360F * Levels.MAX);
    public static final int GREEN = (int) (120F / 360F * Levels.MAX);
    public static final int TURQUOISE = (int) (150F / 360F * Levels.MAX);
    public static final int CYAN = (int) (180F / 360F * Levels.MAX);
    public static final int COBALT = (int) (210F / 360F * Levels.MAX);
    public static final int BLUE = (int) (240F / 360F * Levels.MAX);
    public static final int VIOLET = (int) (270F / 360F * Levels.MAX);
    public static final int MAGENTA = (int) (300 / 360F * Levels.MAX);
    public static final int CRIMSON = (int) (330F / 360 * Levels.MAX);
    private Hue() {
    }            // Prevent Instantiation

}
