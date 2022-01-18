package asteroids;

import com.googlecode.lanterna.TextColor;

public enum Color{
    White(TextColor.Factory.fromString("#FFFFFF")),
    Black(TextColor.Factory.fromString("#000000")),
    Red(TextColor.Factory.fromString("#FF0000"));

    private final TextColor color;
    Color(TextColor color){
        this.color = color;
    }

    public TextColor getColor() {
        return color;
    }
}