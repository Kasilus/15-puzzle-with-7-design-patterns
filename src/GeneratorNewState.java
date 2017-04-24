import java.util.Random;

public class GeneratorNewState {

    private static Random random = new Random();

    public static int[][] generate() {

        int[][] numbers = new int[4][4];

        do {
            numbers = fillNumbers(numbers);
        } while (!isSolved(numbers));
        return numbers;
    }

    private static int[][] fillNumbers(int[][] numbers) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                numbers[i][j] = -1;
            }
        }

        int r;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                do {
                    r = random.nextInt(16);
                } while (numbers[r / 4][r % 4] != -1);

                numbers[r / 4][r % 4] = i * 4 + j;

            }
        }
        return numbers;
    }

    private static boolean isSolved(int[][] numbers) {
        int sum = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (numbers[i][j] == 0) {
                    sum += (i + 1);
                    continue;
                }

                for (int z = i * 4 + j; z < 16; z++) {

                    if (numbers[z / 4][z % 4] < numbers[i][j] && numbers[z / 4][z % 4] != 0)
                        sum++;
                }

            }
        }
        System.out.println(sum % 2 == 0);
        return (sum % 2 == 0);
    }
}
