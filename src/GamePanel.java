import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


public class GamePanel {

    private static GamePanel gamePanel;
    BorderPane root = new BorderPane();
    private MenuBar menuBar;
    private GridPane gridPane;
    private Label stepsLabel = new Label("Steps : 0");
    private Button undoButton = new Button("Undo");
    private Caretaker caretaker = new Caretaker();
    private Originator originator = new Originator();
    private Theme theme;

    public BorderPane getRoot() {
        return root;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    public Caretaker getCaretaker() {
        return caretaker;
    }

    public Button getUndoButton() {
        return undoButton;
    }

    public Originator getOriginator() {
        return originator;
    }

    public Label getStepsLabel() {
        return stepsLabel;
    }

    public Theme getTheme() {
        return theme;
    }

    public static GamePanel getInstance() {
        GamePanel localGamePanel = gamePanel;
        if (localGamePanel == null) {
            gamePanel = localGamePanel = new GamePanel();
            gamePanel.init();
        }
        return localGamePanel;
    }

    public void init() {
        theme = FactoryMethod.getTheme("Classic");
        initMenu(theme);
        root.setStyle(theme.getBackgroundColor());
        initGridPane();
        initBottom();
    }

    private void initMenu(Theme theme) {

        menuBar = theme.getMenu();
        root.setTop(menuBar);

    }

    private void initGridPane() {

        gridPane = new GridPane();

        gridPane.setHgap(4);
        gridPane.setVgap(4);

        Insets insets = new Insets(40, 40, 40, 40);
        gridPane.setPadding(insets);

        root.setCenter(gridPane);
    }

    private void initBottom() {

        undoButton.setOnAction(event -> {
            if (caretaker.getMementos().size() > 1) {
                CurrentPosition currentPosition = caretaker.undo().getCurrentPosition();
                originator.setState(currentPosition);
                caretaker.notifyObservers();
                currentPosition.setButtonStyle();
                repaintGrid();
            } else {
                System.out.println("It's the first position");
            }
        });

        VBox vBox = new VBox();
        vBox.getChildren().addAll(stepsLabel, undoButton);
        vBox.setPadding(new Insets(0,0,15,0));
        vBox.setAlignment(Pos.TOP_CENTER);

        root.setBottom(vBox);
    }

    public void repaintGrid() {

        gridPane.getChildren().clear();

        Iterator iterator = originator.restore().getIterator();
        Button currentButton;
        int position = 0;

        while (iterator.hasNext()) {
            currentButton = (Button) iterator.next();
            GamePanel.getInstance().getGridPane().add(currentButton, position % 4, position / 4);
            if (Integer.parseInt(currentButton.getText()) == 0) {
                currentButton.setVisible(false);
            }
            position++;
        }
    }

    public void newGame() {

        gridPane.getChildren().clear();
        stepsLabel.setText("Steps : 0");
        caretaker = new Caretaker();
        originator = new Originator();
        int[][] numbers = GeneratorNewState.generate();
        CurrentPosition currentPosition = new CurrentPosition(numbers);

        GamePanel.getInstance().getCaretaker().addObserver(new ObserverWin());
        GamePanel.getInstance().getCaretaker().addObserver(new ObserverCount());
        GamePanel.getInstance().repaintGrid();

    }
}



