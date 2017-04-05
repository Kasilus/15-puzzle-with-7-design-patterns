import java.util.Random;

public class Pyatnashki {
    public static void main(String[] args) {


        Random random = new Random();
        int numbers[] = new int[16];


        do {

            for (int i=0;i<numbers.length;i++){
                numbers[i] = -1;
            }

            int r;
            for (int i=0;i<16;i++) {


                do {
                    r = random.nextInt(16) ;
                } while (numbers[r] != -1);

                numbers[r] = i;

            }

            for (int i=0; i<16; i++){
                System.out.print(numbers[i] + ", ");
            }

        } while (!isSolved(numbers));




    }

    private static boolean isSolved(int[] numbers) {
        int sum = 0;

        for (int i = 0; i < 16; i++) {
            if (numbers[i] == 0) {
                sum += i / 4;
                continue;
            }

            for (int j = i + 1; j < 16; j++) {
                if (numbers[j] < numbers[i])
                    sum++;
            }

        }
        System.out.println(sum%2 == 0);
        return (sum%2 == 0);
    }
}