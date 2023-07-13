package view;

import java.awt.Color;

public enum Colors {
    WHITE_WITH_GREEN(215,251,232),
    LIGHT_GREEN(157,243,196),
    GREEN(98,210,162),
    DARK_GREEN(31,171,137),
    DARKEST_GREEN(47,131,111),
    GREY(142, 142, 147),
    START(227,38,54),
    STOCK(106,90,205);


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
