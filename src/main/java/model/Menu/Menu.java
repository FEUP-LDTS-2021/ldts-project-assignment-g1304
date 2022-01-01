package model.Menu;

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
                case LeaderBoard, Exit -> selected = MenuItem.Exit;
            };
        }
    }

    public void selectprevious(){
        if(!choosed) {
            switch (selected) {
                case Play, LeaderBoard -> selected = MenuItem.Play;
                case Exit -> selected = MenuItem.LeaderBoard;
            };
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