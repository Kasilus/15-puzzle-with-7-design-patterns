import java.util.Queue;

public interface ResultsHandler {

    public void writeResults(String name, int steps);
    public Queue<Result> readResults();

}
