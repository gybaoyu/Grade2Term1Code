package ink.abalone.week16.T1;

import java.time.LocalDate;
import java.util.PriorityQueue;

public class PriorityQueueTest {
    public static void main(String[] args) {
        var pq = new PriorityQueue<LocalDate>();
        pq.add(LocalDate.of(2020, 1, 1));
        pq.add(LocalDate.of(2020, 1, 2));
        pq.add(LocalDate.of(2020, 1, 3));
        pq.add(LocalDate.of(2020, 1, 4));
        System.out.println("Iterating over elements");
        for (LocalDate date : pq) System.out.println(date);
        System.out.println("Removing elements");
        while(!pq.isEmpty()) System.out.println(pq.remove());
    }
}
