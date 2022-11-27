import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] liquid = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] answer = solution(liquid);
        System.out.println(answer[0] + " " + answer[1]);
    }

    private static int[] solution(int[] liquid) {
        int[] answer = {0, 0};
        Arrays.sort(liquid);
        int min = Integer.MAX_VALUE;
        int left = 0;
        int right = liquid.length - 1;

        while (left < right) {
            int sum = liquid[left] + liquid[right];
            if (sum == 0) return new int[]{liquid[left], liquid[right]};
            if (Math.abs(sum) < min) {
                min = Math.abs(sum);
                answer[0] = liquid[left];
                answer[1] = liquid[right];
            }
            if (sum < 0) left++;
            else right--;
        }

        return answer;
    }
}