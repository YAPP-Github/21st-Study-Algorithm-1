import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    // number of accessed Ids
    private static int n;
    // size of the cache
    private static int m;
    private static final Stack<Integer> ids = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] s = bufferedReader.readLine().split(" ");

        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);
        for (int i = 0; i < n; i++)
            ids.add(Integer.parseInt(bufferedReader.readLine()));

        System.out.println(solution());
    }

    private static String solution() {
        List<Integer> cache = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            if(ids.empty())
                break;
            Integer pop = ids.pop();
            if (!cache.contains(pop))
                cache.add(pop);
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (Integer integer : cache) {
            stringBuilder.append(integer).append("\n");
        }

        return stringBuilder.toString();
    }
}

