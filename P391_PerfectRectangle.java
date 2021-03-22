package Solutions;

import java.util.PriorityQueue;
import java.util.TreeSet;

public class P391_PerfectRectangle {

    // line sweep in x
    // store start/end lines in pq; sum to see if yLen at each x equals border[1]-[0]

    private class Event implements Comparable<Event> {
        int time;
        int[] rect;

        public Event(int time, int[] rect) {
            this.time = time;
            this.rect = rect;
        }

        public int compareTo(Event that) {
            if (this.time == that.time) return this.rect[0] - that.rect[0];
            return this.time - that.time;
        }

    }

    public boolean isRectangleCover(int[][] rectangles) {
        PriorityQueue<Event> pq = new PriorityQueue<>();
        int[] border = {Integer.MAX_VALUE, Integer.MIN_VALUE};
        for (int[] rect : rectangles) {
            pq.add(new Event(rect[0], rect));
            pq.add(new Event(rect[2], rect));
            if (rect[1] < border[0]) border[0] = rect[1];
            if (rect[3] > border[1]) border[1] = rect[3];
        }

        // TreeSet to store y coverage at each x
        // if overlaps, return 0 -> set.add(rect) == false
        // rect will be added from pq.poll() so already sorted in x, only need to compare y
        TreeSet<int[]> set = new TreeSet<>((a, b) -> {
            if (a[3] <= b[1]) return -1;
            else if (b[3] <= a[1]) return 1;
            else return 0;
        });
        int yLen = 0;
        while (!pq.isEmpty()) {
            int time = pq.peek().time;
            // store same x lines
            while (!pq.isEmpty() && time == pq.peek().time) {
                int[] rect = pq.poll().rect;
                // delete from set if is ending line
                // add to yLen if is starting line
                // *** need to check if overlap when add
                if (rect[2] == time) {
                    set.remove(rect);
                    yLen -= rect[3] - rect[1];
                } else {
                    if (!set.add(rect)) return false;
                    yLen += rect[3] - rect[1];
                }
            }
            // check if yLen is border length
            if (!pq.isEmpty() && yLen != border[1] - border[0]) return false;
        }
        return true;
    }
}
