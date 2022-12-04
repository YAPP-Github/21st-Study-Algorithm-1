import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;

public class Main {
    private static final Queue<String> kQueue = new LinkedList<>();
    private static final Queue<String> cQueue = new LinkedList<>();
    private static int n;
    private static int left;
    private static int right;
    private static boolean isKoosagaTurn = true;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String koosaga = bf.readLine();
        String cubelover = bf.readLine();
        setVariables(koosaga);
        System.out.println(solution(koosaga, cubelover));
    }

    private static void setVariables(String str) {
        n = str.length();
        left = 0;
        right = n - 1;
    }

    private static String solution(String koosaga, String cubelover) {
        initQueue(koosaga, cubelover);
        return getCompanyName();
    }

    private static void initQueue(String koosaga, String cubelover) {
        String[] koo = koosaga.split("");
        String[] cube = cubelover.split("");
        sorting(koo, cube);
        addAllToStacks(koo, cube);
    }

    private static void sorting(String[] koo, String[] cube) {
        Arrays.sort(koo);
        Arrays.sort(cube, Collections.reverseOrder());
    }

    private static void addAllToStacks(String[] koo, String[] cube) {
        kQueue.addAll(Arrays.asList(koo));
        cQueue.addAll(Arrays.asList(cube));
    }


    private static String getCompanyName() {
        String[] companyName = new String[n];
        for (int i = 0; i < n; i++) {
            setWordInCompanyName(companyName, i);
            changeTurn();
        }

        return String.join("", companyName);
    }

    private static void changeTurn() {
        isKoosagaTurn = !isKoosagaTurn;
    }

    private static void setWordInCompanyName(String[] companyName, int i) {
        String selected = selectWord(i);
        Queue<String> originQueue = isKoosagaTurn ? kQueue : cQueue;
        Queue<String> alterQueue = isKoosagaTurn ? cQueue : kQueue;

        if (isAbleToBenefit(selected, alterQueue)) {
            companyName[left++] = selected;
            originQueue.poll();
        } else
            companyName[right--] = ((LinkedList<String>) originQueue).removeLast();

    }

    private static String selectWord(int i) {
        if (i % 2 == 0)
            return kQueue.peek();
        return cQueue.peek();
    }

    private static boolean isAbleToBenefit(String selected, Queue<String> queue) {
        if (isKoosagaTurn)
            return 0 >= selected.compareTo(queue.peek());
        return 0 <= selected.compareTo(queue.peek());
    }

}