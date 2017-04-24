
public class Result {

    private int steps;
    private String name;

    public Result(int steps, String name) {
        this.steps = steps;
        this.name = new String(name);
    }

    public int getSteps() {
        return steps;
    }

    public String getName() {
        return name;
    }


}
