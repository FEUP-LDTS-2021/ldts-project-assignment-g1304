package asteroids.view.screens;

import asteroids.Color;
import asteroids.view.Game.View;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import asteroids.model.Menu.MenuItem;

public class MenuItemView extends View {

    private final MenuItem menuItem;
    private TerminalPosition position;
    private boolean selected;
    public static final Color selectedColor = Color.Red;
    public static final Color notSelectedColor = Color.White;

    public MenuItemView(MenuItem menuItem){
        this.menuItem = menuItem;
        this.selected = false;
        this.position = null;
    }

    public void draw(){
        if (isSelected())
            drawSelected();
        else
            drawNotSelected();
    }

    public void drawSelected(){
        getGraphics().setForegroundColor(selectedColor.getColor());
        getGraphics().putString(getPosition(), menuItem.toString());
    }
    public void drawNotSelected(){
        getGraphics().setForegroundColor(notSelectedColor.getColor());
        getGraphics().putString(getPosition(), menuItem.toString());
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setPosition(TerminalPosition position) {
        this.position = position;
    }

    public TerminalPosition getPosition() {
        return position;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }
}
