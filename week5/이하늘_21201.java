import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static int minute = 0;

    public static void main(String[] args) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        DoublyLinkedList doublyLinkedList = new DoublyLinkedList();
        int n = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        for (int i = 0; i < n; i++)
            doublyLinkedList.append(Integer.parseInt(stringTokenizer.nextToken()));

        while (true) {
            String deleteNode = doublyLinkedList.findDeleteNode();
            if (deleteNode.equals(""))
                break;

            stringBuilder.append(deleteNode).append("\n");
            minute++;
        }

        System.out.println(minute);
        if (stringBuilder.length() != 0)
            System.out.println(stringBuilder.toString().strip());

        doublyLinkedList.display();
    }

    static class Node {

        public int value;
        public Node next;
        public Node prev;

        public Node(int value) {
            this.value = value;
            this.next = null;
            this.prev = null;
        }

        public boolean toBeDeleted() {
            int prevValue = (prev != null) ? prev.value : 0;
            int nextValue = (next != null) ? next.value : 0;
            return this.value < prevValue || this.value < nextValue;
        }

        @Override
        public String toString() {
            return Integer.toString(value);
        }
    }

    static class DoublyLinkedList {

        public Node head;
        public Node tail;

        //다음 라운드에 판단할 노드들
        private Set<Node> nextNodes;

        public DoublyLinkedList() {
            this.head = null;
            this.tail = null;
            this.nextNodes = new LinkedHashSet<>();
        }

        public void append(int value) {
            Node node = new Node(value);
            if (this.head == null) {
                this.head = node;
            } else {
                this.tail.next = node;
                node.prev = this.tail;
            }
            this.tail = node;
        }

        public void remove(Node node) {
            Node prev = node.prev;

            //if target is first node
            if (prev == null) {
                if (node.next != null) {
                    this.head = node.next;
                    node.next.prev = null;
                } else {
                    this.head = null;
                    this.tail = null;
                }

                return;
            }

            //npe safe
            if (node.next == null) {
                prev.next = null;
                this.tail = prev;
            } else {
                prev.next = node.next;
                node.next.prev = prev;
            }
        }

        public String findDeleteNode() {
            StringBuilder stringBuilder = new StringBuilder();
            List<Node> deleted = new ArrayList<>();
            if (nextNodes.isEmpty()) {
                Node current = this.head;
                while (current != null) {
                    if (current.toBeDeleted()) {
                        stringBuilder.append(current).append(" ");
                        deleted.add(current);

                        //다음 실행 노드 캐싱
                        nextNodes.add(current.prev);
                        nextNodes.add(current.next);
                    }
                    current = current.next;
                }
            } else {
                Set<Node> temp = new LinkedHashSet<>();
                for (Node current : nextNodes) {
                    if (current == null)
                        continue;

                    if (!current.toBeDeleted())
                        continue;

                    stringBuilder.append(current).append(" ");
                    deleted.add(current);

                    //다음 실행 노드 캐싱
                    temp.add(current.prev);
                    temp.add(current.next);
                }
                nextNodes = temp;
            }

            // 다음 실행 노드 중 삭제 예정인 노드 제외
            nextNodes = this.nextNodes.stream()
                    .filter(node -> !deleted.contains(node))
                    .collect(Collectors.toCollection(LinkedHashSet::new));


            deleted.forEach(this::remove);
            return stringBuilder.toString().trim();
        }

        public void display() {
            StringBuilder stringBuilder = new StringBuilder();
            Node current = this.head;

            while (current != null) {
                stringBuilder.append(current).append(" ");
                current = current.next;
            }

            System.out.println(stringBuilder.toString().trim());
        }
    }
}