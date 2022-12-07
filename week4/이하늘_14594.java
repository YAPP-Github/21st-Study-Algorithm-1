import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static int n;
    private static int m;
    private static final List<List<Integer>> bigsMoving = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solution());

    }

    private static int solution() {
        return countRoom(breakWall(getWall()));
    }

    private static int countRoom(boolean[] wall) {
        int count = 0;
        for (int i = 0; i < n - 1; i++)
            if (wall[i])
                count++;
        
        return count + 1;
    }

    private static boolean[] breakWall(boolean[] wall) {
        for (int i = 0; i < m; i++) {
            int x = bigsMoving.get(i).get(0);
            int y = bigsMoving.get(i).get(1);
            for (int j = x - 1; j < y - 1; j++)
                wall[j] = false;
        }
        return wall;
    }

    private static boolean[] getWall() {
        boolean[] wall = new boolean[n - 1];
        for (int i = 0; i < n - 1; i++)
            wall[i] = true;

        return wall;
    }


    private static void init() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        setNM(bf);
        setMoving(bf);
    }

    private static void setMoving(BufferedReader bf) throws IOException {
        for (int i = 0; i < m; i++) {
            int[] xy = Arrays.stream(bf.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            ArrayList<Integer> temp = new ArrayList<>(List.of(xy[0], xy[1]));
            bigsMoving.add(temp);
        }
    }

    private static void setNM(BufferedReader bf) throws IOException {
        n = Integer.parseInt(bf.readLine());
        m = Integer.parseInt(bf.readLine());
    }
}