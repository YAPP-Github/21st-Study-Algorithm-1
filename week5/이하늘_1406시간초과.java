import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {

    private static final LinkedList<String> linkedList = new LinkedList<>();
    private static int m;
    private static int nowCursor;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        setValues(bufferedReader);

        for (int i = 0; i < m; i++) {
            String[] commands = bufferedReader.readLine().split(" ");
            processCommand(commands);
        }

        System.out.println(result());
    }

    private static void setValues(BufferedReader bufferedReader) throws IOException {
        linkedList.addAll(List.of(bufferedReader.readLine().split("")));
        m = Integer.parseInt(bufferedReader.readLine());
        nowCursor = linkedList.size();
    }

    private static void processCommand(String[] commands) {
        String command = commands[0];
        switch (command) {
            case "L":
                moveLeft();
                break;
            case "D":
                moveRight();
                break;
            case "B":
                deleteLeft();
                break;
            case "P":
                insertLeft(commands[1]);
                break;
        }
    }

    private static void insertLeft(String param) {
        linkedList.add(nowCursor, param);
        moveRight();
    }

    private static void deleteLeft() {
        if (nowCursor == 0) return;
        moveLeft();
        linkedList.remove(nowCursor);
    }

    private static void moveRight() {
        nowCursor = Math.min(nowCursor + 1, linkedList.size());
    }

    private static void moveLeft() {
        nowCursor = Math.max(nowCursor - 1, 0);
    }

    private static String result() {
        return String.join("", linkedList);
    }
}


