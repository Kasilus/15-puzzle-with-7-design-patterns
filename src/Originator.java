
public class Originator extends VisitorCheckWin implements Visitable {

    private CurrentPosition currentPosition;

    public void setState(CurrentPosition currentPosition) {
        System.out.println("Originator: Setting state to ");
        this.currentPosition = currentPosition;
    }

    public Memento save() {
        System.out.println("Originator: Saving to Memento.");
        return new Memento(currentPosition);
    }

    public CurrentPosition restore() {
        return currentPosition;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(currentPosition);
    }
}
