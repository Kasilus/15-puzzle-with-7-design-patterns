import java.util.Queue;

public class ResultsHandlerImpl implements ResultsHandler {

    InputOutputHandler inputOutputHandler = new InputOutputHandler();


    @Override
    public void writeResults(String name, int steps) {
        inputOutputHandler.writeToFile(name, steps);
    }

    @Override
    public Queue<Result> readResults() {
        return inputOutputHandler.readFromFile();
    }
}
