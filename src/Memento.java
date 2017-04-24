public class Memento {

    private CurrentPosition currentPosition;

    public Memento(CurrentPosition currentPosition){
        this.currentPosition = currentPosition;
    }

    public CurrentPosition getCurrentPosition() {
        return currentPosition;
    }
}