import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.util.Random;

public class Pyatnashki extends Application {

    Random random = new Random();

    int[][] numbers = new int[4][4];
    Button[] buttons;
    Pane root = new Pane();
    GridPane gridPane = new GridPane();


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {


//        do {
//            fillNumbers(numbers);
//        } while (!isSolved(numbers));

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                numbers[i][j] = i * 4 + j + 1;
            }
            numbers[3][3] = 15;
        }

        numbers[3][2] = 0;
        numbers[3][1] = 14;

        isSolved(numbers);

        System.out.println();

        outputNumbers();


        gridPane.setHgap(4);
        gridPane.setVgap(4);

        Insets insets = new Insets(40, 40, 40, 40);
        gridPane.setPadding(insets);


        fillButtonsByNumbers();
        root.getChildren().add(gridPane);

        primaryStage.setTitle("Puzzle 15");
        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();


    }


    private void fillNumbers(int[][] numbers) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                numbers[i][j] = -1;
            }
        }

        int r;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                do {
                    r = random.nextInt(16);
                } while (numbers[r / 4][r % 4] != -1);

                numbers[r / 4][r % 4] = i * 4 + j;

            }
        }

        outputNumbers();
    }

    private void outputNumbers() {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(numbers[i][j] + ", ");
            }
        }
    }

    private static boolean isSolved(int[][] numbers) {
        int sum = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (numbers[i][j] == 0) {
                    sum += (i + 1);
                    continue;
                }

                for (int z = i * 4 + j; z < 16; z++) {

                    if (numbers[z / 4][z % 4] < numbers[i][j] && numbers[z / 4][z % 4] != 0)
                        sum++;
                }

            }
        }
        System.out.println(sum % 2 == 0);
        return (sum % 2 == 0);
    }

    private void fillButtonsByNumbers() {

        buttons = new Button[16];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                buttons[i * 4 + j] = new Button(Integer.toString(numbers[i][j]));

                buttons[i * 4 + j].setPrefSize(80, 80);
                buttons[i * 4 + j].setOnAction(new OnClickListener());
                gridPane.add(buttons[i * 4 + j], j, i);
                if (numbers[i][j] == 0) {
                    buttons[i * 4 + j].setVisible(false);
                }
            }
        }
    }

    private void rePaint() {
        gridPane.getChildren().clear();
        fillButtonsByNumbers();

    }


    private class OnClickListener implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Button button = (Button) event.getSource();
            if (change(Integer.parseInt(button.getText()))) {
                rePaint();
                checkIfWin();
            }

        }
    }

    private void checkIfWin() {
        int i = 0;
        while (numbers[i / 4][i % 4] == i + 1) {
            i++;
        }
        if (i == 15) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("You win!");

            alert.showAndWait();
        }
    }


    private boolean change(int num) {
        int i = 0, j = 0;
        for (int k = 0; k < 4; k++) {
            for (int l = 0; l < 4; l++) {
                if (numbers[k][l] == num) {
                    i = k;
                    j = l;
                }
            }
        }
        if (i > 0) {
            if (numbers[i - 1][j] == 0) {
                numbers[i - 1][j] = num;
                numbers[i][j] = 0;
            }
        }
        if (i < 3) {
            if (numbers[i + 1][j] == 0) {
                numbers[i + 1][j] = num;
                numbers[i][j] = 0;
            }
        }
        if (j > 0) {
            if (numbers[i][j - 1] == 0) {
                numbers[i][j - 1] = num;
                numbers[i][j] = 0;
            }
        }
        if (j < 3) {
            if (numbers[i][j + 1] == 0) {
                numbers[i][j + 1] = num;
                numbers[i][j] = 0;
            }
        }
        return (numbers[i][j] == 0);
    }


}



