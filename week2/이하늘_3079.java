import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] values = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = values[0];
        int m = values[1];

        int[] times = new int[n];
        for (int i = 0; i < n; i++) {
            times[i] = scanner.nextInt();
        }
        solution(m, times);
    }

    private static void solution(int m, int[] times) {
        Arrays.sort(times);
        long left = 0;
        long right = (long) times[times.length - 1] * m;

        while (left <= right) {
            long mid = (left + right) / 2;
            long sum = 0;
            for (int time : times) sum += (mid / time);

            if (sum < m) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(left);
    }
}