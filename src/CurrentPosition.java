import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class CurrentPosition implements Iterable {

    private Button[] buttons;
    private int[][] numbers = new int[4][4];

    public CurrentPosition(int[][] numbers) {
        this.numbers = numbers;
        setData();
        fixState();
    }

    public Button[] getButtons() {
        return buttons;
    }

    public int[][] getNumbers() {
        return numbers;
    }


    private void setData() {

        buttons = new Button[16];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                buttons[i * 4 + j] = new Button(Integer.toString(numbers[i][j]));
                buttons[i*4+j].setStyle(GamePanel.getInstance().getTheme().getButtonStyle());
                buttons[i * 4 + j].setPrefSize(80, 80);
                buttons[i * 4 + j].setOnAction(new OnClickListener());
            }
        }


    }

    public void setButtonStyle(){

        Iterator iterator = this.getIterator();
        Button localButton;

        while (iterator.hasNext()) {
            localButton = (Button) iterator.next();
            localButton.setStyle(GamePanel.getInstance().getTheme().getButtonStyle());
        }
    }

    private void fixState() {
        GamePanel.getInstance().getOriginator().setState(this);
        GamePanel.getInstance().getCaretaker().addMemento(GamePanel.getInstance().getOriginator().save());
    }


    @Override
    public Iterator getIterator() {
        return new ButtonIterator();
    }


    private class OnClickListener implements EventHandler<ActionEvent> {
        CurrentPosition currentPosition;

        @Override
        public void handle(ActionEvent event) {

            Button button = (Button) event.getSource();

            int[][] localNumbers = new int[4][4];
            for (int i = 0; i<4; i++){
                for (int j=0; j<4; j++){
                    localNumbers[i][j] = numbers[i][j];
                }
            };

            if (change(Integer.parseInt(button.getText()),localNumbers)) {
                currentPosition = new CurrentPosition(localNumbers);
                GamePanel.getInstance().repaintGrid();
            }
        }

        private boolean change(int num,int[][] localNumbers) {
            int i = 0, j = 0;


            for (int k = 0; k < 4; k++) {
                for (int l = 0; l < 4; l++) {
                    if (localNumbers[k][l] == num) {
                        i = k;
                        j = l;
                    }
                }
            }

            boolean isChanged = checkChange(i, j, num, localNumbers);
            if (isChanged) {
                if (i > 0) {
                    if (localNumbers[i - 1][j] == 0) {
                        localNumbers[i - 1][j] = num;
                        localNumbers[i][j] = 0;
                    }
                }
                if (i < 3) {
                    if (localNumbers[i + 1][j] == 0) {
                        localNumbers[i + 1][j] = num;
                        localNumbers[i][j] = 0;
                    }
                }
                if (j > 0) {
                    if (localNumbers[i][j - 1] == 0) {
                        localNumbers[i][j - 1] = num;
                        localNumbers[i][j] = 0;
                    }
                }
                if (j < 3) {
                    if (localNumbers[i][j + 1] == 0) {
                        localNumbers[i][j + 1] = num;
                        localNumbers[i][j] = 0;
                    }
                }
            }
            return (isChanged);
        }

        private boolean checkChange(int i, int j, int num, int[][] localNumbers) {
            if (i > 0) {
                if (localNumbers[i - 1][j] == 0) {
                    return true;
                }
            }
            if (i < 3) {
                if (localNumbers[i + 1][j] == 0) {
                    return true;
                }
            }
            if (j > 0) {
                if (localNumbers[i][j - 1] == 0) {
                    return true;
                }
            }
            if (j < 3) {
                if (localNumbers[i][j + 1] == 0) {
                    return true;
                }
            }
            return false;
        }
    }

    private class ButtonIterator implements Iterator {

        int index;

        @Override
        public boolean hasNext() {

            if (index < buttons.length) {
                return true;
            }
            return false;
        }

        @Override
        public Button next() {
            if (this.hasNext()) {
                return buttons[index++];
            }
            return null;
        }
    }

}