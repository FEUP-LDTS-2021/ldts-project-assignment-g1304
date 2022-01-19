package asteroids.view;

import com.googlecode.lanterna.TextColor;

public enum Color{
    White(TextColor.Factory.fromString("#FFFFFF"), 'W'),
    Black(TextColor.Factory.fromString("#000000"), 'B'),
    Red(TextColor.Factory.fromString("#FF0000"),   'R'),
    DarkBlue(TextColor.Factory.fromString("#205591"),'A'),
    LightBlue(TextColor.Factory.fromString("#7BB1EC"),'b'),
    LightGray(TextColor.Factory.fromString("#B5B5B5"),'c'),
    MediumGray(TextColor.Factory.fromString("#787978"),'C'),
    DarkGray(TextColor.Factory.fromString("#5A5A5A"),'p'),
    LightBlack(TextColor.Factory.fromString("#1E1E1E"),'P');

    private final TextColor color;
    private final char c;

    Color(TextColor color, char c){
        this.color = color;
        this.c = c;
    }

    public char getChar() {
        return c;
    }

    public static Color getColor(char c) {
        for (Color color : Color.values())
            if (color.getChar() == c)
                return color;
        return null;
    }

    public TextColor getColor() {
        return color;
    }
}