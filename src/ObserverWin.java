
public class ObserverWin implements Observer {

    @Override
    public void update() {
        Visitor v = new VisitorCheckWin();
        GamePanel.getInstance().getOriginator().accept(v);
    }
}
