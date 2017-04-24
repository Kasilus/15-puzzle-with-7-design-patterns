import javafx.scene.control.MenuBar;

public class ThemeClassic implements Theme {

    MenuAbstract menu;

    @Override
    public MenuBar getMenu() {

        if (menu==null){
            menu = new MenuClassic();
        }

        return menu.getMenuBar();
    }

    @Override
    public String getBackgroundColor() {
        return "-fx-background-color: white";
    }

    @Override
    public String getButtonStyle() {
        return "";
    }

}
