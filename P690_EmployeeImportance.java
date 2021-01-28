package Solutions;

import java.util.*;

public class P690_EmployeeImportance {
    class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }

    public int getImportance(List<Employee> employees, int id) {
        // BFS
        int res = 0;
        Map<Integer, Employee> map = new HashMap<>();
        for (Employee e : employees) map.put(e.id, e);

        Queue<Integer> bfs = new LinkedList<>();
        bfs.offer(id);

        boolean[] seen = new boolean[2001];

        while (!bfs.isEmpty()) {
            Employee curr = map.get(bfs.poll());
            if (seen[curr.id]) continue;
            seen[curr.id] = true;
            res += curr.importance;

            for (int ne : curr.subordinates) {
                if (!seen[ne]) bfs.offer(ne);
            }
        }
        return res;
    }
}
