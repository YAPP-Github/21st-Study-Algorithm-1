import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    // 식물의 갯수
    private static int n;
    // 점프
    private static int k;
    private static Queue<String> commands;
    private static List<PlantPosition> plants = new LinkedList<>();
    private static HashMap<String, Predicate<PlantPosition>> directions = new HashMap<>();

    //현재 개구리
    private static PlantPosition now = null;


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] s = bufferedReader.readLine().split(" ");
        commands = new LinkedList<>();
        commands.addAll(List.of(bufferedReader.readLine().split("")));
        n = Integer.parseInt(s[0]);
        k = Integer.parseInt(s[1]);

        for (int i = 0; i < n; i++) {
            List<Integer> position = Arrays.stream(bufferedReader.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toList());

            PlantPosition pos = new PlantPosition(position.get(0), position.get(1));
            if (now != null)
                plants.add(pos);
            else
                now = pos;
        }
        setDirections();
        solution();
        System.out.println(now.toString());
    }

    // P는 양의 정수인데, 0은 양의 정수가 아니므로 대각으로만 갈 수 있음
    private static void setDirections() {
        directions.put("A", (plantPosition -> now.getX() < plantPosition.getX() && now.getY() < plantPosition.getY()));
        directions.put("B", (plantPosition -> now.getX() < plantPosition.getX() && now.getY() > plantPosition.getY()));
        directions.put("C", (plantPosition -> now.getX() > plantPosition.getX() && now.getY() < plantPosition.getY()));
        directions.put("D", (plantPosition -> now.getX() > plantPosition.getX() && now.getY() > plantPosition.getY()));
    }

    private static void solution() {
        while (k != 0 && !commands.isEmpty()) {
            String command = commands.poll();
            Predicate<PlantPosition> plantPositionPredicate = directions.get(command);
            List<PlantPosition> finds = plants.stream()
                    .filter(plantPositionPredicate)
                    .collect(Collectors.toList());



            if (finds.size() > 1)
                finds.sort((o1, o2) -> (int) (o1.hypotenuse() - o2.hypotenuse()));

            if (finds.size() > 0) {
                now = finds.get(0);
                plants.remove(now);
                k--;
            }
        }
    }

    static class PlantPosition {

        private final int x;
        private final int y;

        public PlantPosition(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public double hypotenuse() {
            int xLength = now.getX() - x;
            int yLength = now.getY() - y;
            return Math.sqrt(Math.pow(xLength, 2) + Math.pow(yLength, 2));
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public String toString() {
            return x + " " + y;
        }
    }
}