import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;

public class MenuClassic extends MenuAbstract {

    public MenuClassic() {
        super();

        for (int i = 0; i < labelsString.length; i++) {
            labels[i] = new Label(labelsString[i]);
            labels[i].setStyle("-fx-text-fill: black");
        }

        menuGame.setGraphic(labels[0]);
        itmNew.setGraphic(labels[1]);
        itmExit.setGraphic(labels[2]);
        menuView.setGraphic(labels[3]);
        itmClassic.setGraphic(labels[4]);
        itmDragula.setGraphic(labels[5]);

        itmClassic.setOnAction(event -> {
            System.out.println("Classic theme is choosed");
        });

        itmDragula.setOnAction(event -> {

            GamePanel.getInstance().setTheme(FactoryMethod.getTheme("Dargula"));

            MenuBar menuBar = GamePanel.getInstance().getTheme().getMenu();
            GamePanel.getInstance().getRoot().setTop(menuBar);

            GamePanel.getInstance().getRoot().setStyle(GamePanel.getInstance().getTheme().getBackgroundColor());

            GamePanel.getInstance().getCaretaker().getMemento().getCurrentPosition().setButtonStyle();
            GamePanel.getInstance().getUndoButton().setStyle(GamePanel.getInstance().getTheme().getButtonStyle());
        });


    }
}
