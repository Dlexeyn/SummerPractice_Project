package view;

import java.awt.Color;

public enum Colors {
    WHITE_WITH_YELLOW(254, 247, 235),
    LIGHT_YELLOW(249, 204, 121),
    ORANGE(245, 171, 35),
    DARK_ORANGE(194, 128, 9),
    BROWN(140, 93, 7),
    GREY(142, 142, 147),
    RED(255, 59, 48),
    GREEN(76, 217, 100); 

    private final int r;
    private final int g;
    private final int b;
    private final String rgb;

    private Colors(final int r,final int g,final int b) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.rgb = r + ", " + g + ", " + b;
    }

    public Color getColor()
    {
        return new Color(r, g, b);
    }

    public String getRGB() {
        return rgb;
    }

    //You can add methods like this too
    public int getRed(){
        return r;
    }

    public int getGreen(){
        return g;
    }

    public int getBlue(){
        return b;
    }

    //Or even these
    // public Color getColor(){
    //     return new Color(r,g,b);
    // }
}
