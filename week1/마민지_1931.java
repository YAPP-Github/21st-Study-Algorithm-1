import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int totalMeeting = 0;
        int prevEndMeetingTime = 0;

        N = Integer.parseInt(st.nextToken());

        int[][] inputArr = new int[N][2];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            inputArr[i][0] = Integer.parseInt(st.nextToken());
            inputArr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(inputArr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1]==o2[1]){
                    return o1[0] - o2[0];
                }else{
                    return o1[1] - o2[1];
                }
            }
        });

        for(int i = 0; i < N; i++) {
            if(prevEndMeetingTime <= inputArr[i][0]) {
                prevEndMeetingTime = inputArr[i][1];
                totalMeeting++;
            }
        }

        System.out.println(totalMeeting);
    }

}
