package asteroids.model.Menu;

public class Menu {

    private MenuItem selected;
    private boolean choosed;


    public Menu(){
        selected = MenuItem.Play;
        choosed = false;
    }

    public void selectNext(){
        if(!choosed) {
            switch (selected) {
                case Play -> selected = MenuItem.LeaderBoard;
                case LeaderBoard -> selected = MenuItem.Instructions;
                case Instructions,Exit -> selected = MenuItem.Exit;
            };
        }
    }

    public void selectprevious(){
        if(!choosed) {
            switch (selected) {
                case Play, LeaderBoard -> selected = MenuItem.Play;
                case Instructions -> selected = MenuItem.LeaderBoard;
                case Exit -> selected = MenuItem.Instructions;
            }
        }
    }

    public MenuItem getSelected() {
        return selected;
    }

    public void choose() {
        this.choosed = true;
    }

    public boolean isChoosed() {
        return choosed;
    }
}