
public class ObserverCount implements Observer {

    int count = 0;

    @Override
    public void update() {

        count = GamePanel.getInstance().getCaretaker().getMementos().size();
        GamePanel.getInstance().getStepsLabel().setText("Steps : " + (count - 1));

    }
}
