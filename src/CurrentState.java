import javafx.scene.control.Button;

public class CurrentState {

    private Button[] buttons;
    private int stepsCounter = 0;

    public int getStepsCounter() {
        return stepsCounter;
    }


    public Button[] getButtons() {
        return buttons;
    }

    public void setButtons(Button[] buttons) {
        this.buttons = buttons;
    }

    private Memento undo;


    private class Memento{

        Button[] undoButtons;

        Memento(){
            undoButtons = buttons;
        }

        public Button[] getUndoButtons() {
            return undoButtons;
        }
    }

    public void fixState(){
        undo = new Memento();
        stepsCounter++;
    }

    public void undoStep(){
        buttons = undo.getUndoButtons();
    }

}
