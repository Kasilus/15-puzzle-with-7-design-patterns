import java.util.ArrayList;
import java.util.List;


public class Caretaker implements Observable {

    private List<Memento> mementos = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();

    public List<Memento> getMementos() {
        return mementos;
    }

    public void addMemento(Memento m){
        mementos.add(m);
        notifyObservers();
    }

    public Memento getMemento(){
        return mementos.get(mementos.size()-1);
    }

    public Memento undo() {

        mementos.remove(mementos.size()-1);
        return getMemento();

    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o: observers) {
            o.update();
        }
    }
}
