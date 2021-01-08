package Solutions;

import java.util.*;

public class P1203_SortItemsByGroupsRespectingDependencies {
    Map<Integer, List<Integer>> groupGraph;
    Map<Integer, List<Integer>> itemGraph;
    int[] groupIndegree;
    int[] itemIndegree;

    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        // change all -1 groups into individual groups
        // toposort group (based on beforeItems)
        // toposort items
        // HashMap<group, List<items>> to store grouped items info

        for (int i = 0; i < n; i++) {
            if (group[i] == -1) {
                group[i] = m++;
            }
        }
        groupGraph = new HashMap<>();
        itemGraph = new HashMap<>();
        groupIndegree = new int[m];
        itemIndegree = new int[n];

        for (int i = 0; i < m; i++) {
            groupGraph.put(i, new ArrayList());
        }
        for (int i = 0; i < n; i++) {
            itemGraph.put(i, new ArrayList());
        }

        buildGroupGraph(group, beforeItems, m);
        buildItemGraph(beforeItems, n);

        List<Integer> groupList = toposortUtil(groupGraph, groupIndegree, m);
        List<Integer> itemList = toposortUtil(itemGraph, itemIndegree, n);

        // check for cycles
        if (groupList.size() == 0 || itemList.size() == 0) return new int[0];

        Map<Integer, List<Integer>> groupedItems = new HashMap<>();
        for (int item : itemList) {
            List<Integer> items = groupedItems.getOrDefault(group[item], new ArrayList());
            items.add(item);
            groupedItems.put(group[item], items);
        }
        int[] ans = new int[n];
        int idx = 0;
        for (int grp : groupList) {
            List<Integer> items = groupedItems.getOrDefault(grp, new ArrayList());
            for (int item : items) {
                ans[idx++] = item;
            }
        }
        return ans;
    }

    private void buildGroupGraph(int[] group, List<List<Integer>> beforeItems, int m) {
        for (int i = 0; i < group.length; i++) {
            int toGroup = group[i];
            List<Integer> fromItems = beforeItems.get(i);
            for (int fromItem : fromItems) {
                int fromGroup = group[fromItem];
                if (fromGroup != toGroup) {
                    groupGraph.get(fromGroup).add(toGroup);
                    groupIndegree[toGroup]++;
                }
            }
        }
    }

    private void buildItemGraph(List<List<Integer>> beforeItems, int n) {
        for (int i = 0; i < n; i++) {
            List<Integer> items = beforeItems.get(i);
            for (int item : items) {
                itemGraph.get(item).add(i);
                itemIndegree[i]++;
            }
        }
    }

    private List<Integer> toposortUtil(Map<Integer, List<Integer>> graph, int[] indegree, int n) {
        List<Integer> list = new ArrayList<>();
        Deque<Integer> d = new ArrayDeque<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) d.add(i);
        }
        while (!d.isEmpty()) {
            int curr = d.poll();
            list.add(curr);
            n--;
            for (int next : graph.get(curr)) {
                indegree[next]--;
                if (indegree[next] == 0) d.add(next);
            }
        }
        return n == 0 ? list : new ArrayList();
    }
}
