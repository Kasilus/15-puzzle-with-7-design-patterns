import javafx.scene.control.MenuBar;

public class ThemeBlack implements Theme {

    MenuAbstract menu;

    @Override
    public MenuBar getMenu() {

        if (menu==null){
            menu = new MenuBlack();
        }

        return menu.getMenuBar();
    }

    @Override
    public String getBackgroundColor() {
        return "-fx-background-color: red";
    }

    @Override
    public String getButtonStyle() {

        return "-fx-background-color: black ; -fx-text-fill: red ";
    }

}
