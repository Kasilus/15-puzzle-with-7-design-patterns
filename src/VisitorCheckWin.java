import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class VisitorCheckWin implements Visitor {


    @Override
    public void visit(CurrentPosition currentPosition) {

        int[][] numbers = currentPosition.getNumbers();

        int i = 0;
        while (numbers[i / 4][i % 4] == i + 1) {
            i++;
        }
        if (i == 15) {

            spawnWinWindow();

        }
    }

    private void spawnWinWindow() {

        Stage stage = new Stage(StageStyle.UTILITY);
        BorderPane root = new BorderPane();

        root.setStyle(GamePanel.getInstance().getTheme().getBackgroundColor());


        GridPane gridPane = new GridPane();

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(40);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(60);
        gridPane.getColumnConstraints().addAll(col1,col2);

        gridPane.setVgap(6);
        gridPane.setHgap(2);
        gridPane.setPadding(new Insets(15,15,15,15));

        int steps = GamePanel.getInstance().getCaretaker().getMementos().size()-1;

        VBox vWin = new VBox();
        vWin.setAlignment(Pos.TOP_CENTER);
        vWin.getChildren().add(new Label("Congratulations!"));
        gridPane.add(vWin,0,0,2,1);

        gridPane.add(new Label("Steps : "),0,2);
        gridPane.add(new Label(Integer.toString(steps)),1,2);

        gridPane.add(new Label("Your name : "),0,3);
        TextField nameField = new TextField();
        gridPane.add(nameField,1,3);

        Button okButton = new Button("Ok");
        okButton.setStyle(GamePanel.getInstance().getTheme().getButtonStyle());
        okButton.setOnAction(event -> {
            ResultsHandler resultsHandler = new ResultsHandlerImpl();
            String name = nameField.getText();
            resultsHandler.writeResults(name, steps);
            GamePanel.getInstance().newGame();
            stage.close();
        });

        VBox vBox = new VBox();
        vBox.getChildren().add(okButton);
        vBox.setAlignment(Pos.BOTTOM_RIGHT);
        gridPane.add(vBox,1,5);

        root.setCenter(gridPane);

        Scene scene = new Scene(root,330,150);

        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(Main.getPrimaryStage().getScene().getWindow());
        stage.setTitle("Win!");
        stage.setScene(scene);
        stage.show();
    }

}
