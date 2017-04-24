import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class InputOutputHandler {


    public void writeToFile(String name, int steps) {

        try(FileWriter fw = new FileWriter("Results.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.println(name);
            out.println(steps);
        } catch (IOException e) {
            System.out.println("Writing exception!");
        }

    }

    public Queue<Result> readFromFile() {

        Comparator<Result> comparator = new Comparator<Result>() {

            @Override
            public int compare(Result o1, Result o2) {
                if( o1.getSteps() > o2.getSteps() ){
                    return 1;
                }
                if( o1.getSteps() < o2.getSteps() ){
                    return -1;
                }
                return 0;
            }
        };

        Queue<Result> priorityQueue = new PriorityQueue<>(10, comparator);

        try (BufferedReader br = new BufferedReader(new FileReader("Results.txt"))) {

            String name;
            int steps;

            while ((name = br.readLine()) != null) {
                steps = Integer.parseInt(br.readLine());
                priorityQueue.add(new Result(steps,name));
            }



        } catch (IOException e) {
            e.printStackTrace();
        }

        return priorityQueue;

    }



}
