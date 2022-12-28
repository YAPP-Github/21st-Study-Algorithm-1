import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int n;
    private static int[] buildings;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());
        buildings = new int[n];

        for (int i = 0; i < n; i++) {
            buildings[i] = Integer.parseInt(bufferedReader.readLine());
        }

        System.out.println(solution());
    }

    private static long solution() {
        long result = 0;

        for (int i = 0; i < buildings.length; i++) {
            int building = buildings[i];
            int stack = 0;

            for (int j = i + 1; j < buildings.length; j++) {
                int targetBuilding = buildings[j];
                if (targetBuilding < building)
                    stack++;
                else
                    break;
            }

            result += stack;
        }

        return result;
    }
}
