package asteroids.view;

import com.googlecode.lanterna.TextColor;

public enum Color{
    White(TextColor.Factory.fromString("#FFFFFF")),
    Black(TextColor.Factory.fromString("#000000")),
    Red(TextColor.Factory.fromString("#FF0000")),
    DarkBlue(TextColor.Factory.fromString("#205591")),
    LightBlue(TextColor.Factory.fromString("#7BB1EC")),
    LightGray(TextColor.Factory.fromString("#B5B5B5")),
    MediumGray(TextColor.Factory.fromString("#787978")),
    DarkGray(TextColor.Factory.fromString("#5A5A5A")),
    LightBlack(TextColor.Factory.fromString("#1E1E1E"));

    private final TextColor color;

    Color(TextColor color){
        this.color = color;
    }

    public char getChar() {
        return c;
    }

    public static Color getColor(char c)

    public TextColor getColor() {
        return color;
    }
}