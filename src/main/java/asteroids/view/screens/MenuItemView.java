package asteroids.view.screens;

import asteroids.view.Game.View;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import asteroids.model.Menu.MenuItem;

public class MenuItemView extends View {

    private final MenuItem menuItem;
    private TerminalPosition position;
    private boolean selected;
    public static final TextColor selectedColor = TextColor.Factory.fromString("#FF0000");
    public static final TextColor notSelectedColor = TextColor.Factory.fromString("#FFFFFF");

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
        getGraphics().setForegroundColor(selectedColor);
        getGraphics().putString(getPosition(), menuItem.toString());
    }
    public void drawNotSelected(){
        getGraphics().setForegroundColor(notSelectedColor);
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
