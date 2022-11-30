import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static HashMap<String, Integer> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String input =  st.nextToken();
            String[] splited = input.split("\\.");
            String target = splited[1];
            int value = Optional.ofNullable(map.get(target)).orElseGet(() -> 0);
            map.put(target, value + 1);
        }
        List<String> keyList = new ArrayList<>(map.keySet());
        keyList.sort((s1, s2) -> s1.compareTo(s2));
        for (String key : keyList) {
            bw.write(key + " " + map.get(key));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
}