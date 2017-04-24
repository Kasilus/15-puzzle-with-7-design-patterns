import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public abstract class MenuAbstract {

    MenuBar menuBar;
    Menu menuGame, menuView;
    MenuItem itmNew, itmExit, itmClassic, itmDragula;
    String[] labelsString = {"Game","New","Exit","Themes","Classic","Dragula"};
    Label[] labels = new Label[labelsString.length];

    public MenuAbstract() {
        menuBar = new MenuBar();

        menuGame = new Menu("");
        itmNew = new MenuItem("");
        itmExit = new MenuItem("");

        itmNew.setOnAction(event -> {
            GamePanel.getInstance().newGame();
        });

        itmExit.setOnAction(event -> {
            Main.getPrimaryStage().close();
        });

        menuGame.getItems().addAll(itmNew, itmExit);

        menuView = new Menu("");
        itmClassic = new MenuItem("");
        itmDragula = new MenuItem("");
        menuView.getItems().addAll(itmClassic, itmDragula);

        menuBar.getMenus().addAll(menuGame, menuView);
    }


    public MenuBar getMenuBar() {
        return menuBar;
    }
}
