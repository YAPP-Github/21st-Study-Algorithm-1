import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.valueOf(st.nextToken());
        int M = Integer.valueOf(st.nextToken());
        int max = 0;

        Integer[] officeTimeArray = new Integer[N];
        for (int i = 0; i < N; i++) {
            officeTimeArray[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, officeTimeArray[i]);
        }

        long left = 0L;
        long right = (max) * 1000000000L;
        long rtn = 0L;
        while (left <= right) {
            long mid = (left + right) / 2;
            long cnt = 0;
            for (int i = 0; i < N; i++) {
                cnt += (mid / officeTimeArray[i]);

            }
            if (cnt >= M) {
                rtn = mid;
                right = mid - 1;
            } else if (cnt < M) {
                left = mid + 1;

            }
        }
        sb.append(rtn);
        System.out.println(sb);
    }

}
