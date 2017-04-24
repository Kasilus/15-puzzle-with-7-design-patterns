import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage pStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        pStage = primaryStage;

        int[][] numbers = new int[4][4];

//        numbers = GeneratorNewState.generate();


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                numbers[i][j] = i * 4 + j + 1;
            }
            numbers[3][3] = 15;
        }

        numbers[3][2] = 0;
        numbers[3][1] = 14;


        CurrentPosition currentPosition = new CurrentPosition(numbers);
        GamePanel.getInstance().getCaretaker().addObserver(new ObserverWin());
        GamePanel.getInstance().getCaretaker().addObserver(new ObserverCount());


        GamePanel.getInstance().repaintGrid();


        primaryStage.setTitle("Puzzle 15");
        Scene scene = new Scene(GamePanel.getInstance().getRoot(), 400, 500, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static Stage getPrimaryStage() {
        return pStage;
    }

}
