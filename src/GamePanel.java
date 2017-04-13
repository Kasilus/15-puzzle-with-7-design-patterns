import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

public class GamePanel {

    private static GamePanel gamePanel;
    private GridPane gridPane;

    GamePanel() {
    }

    ;


    public GridPane getGridPane() {
        return gridPane;
    }


    public static GamePanel getInstance() {
        GamePanel localGamePanel = gamePanel;
        if (localGamePanel == null) {
            gamePanel = localGamePanel = new GamePanel();
            gamePanel.initGridPane();
        }
        return localGamePanel;
    }

    private void initGridPane() {

        gridPane = new GridPane();

        gridPane.setHgap(4);
        gridPane.setVgap(4);

        Insets insets = new Insets(40, 40, 40, 40);
        gridPane.setPadding(insets);
    }


}
