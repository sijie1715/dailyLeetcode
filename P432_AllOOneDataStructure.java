package Solutions;

import java.util.HashMap;
import java.util.HashSet;

public class P432_AllOOneDataStructure {
    // <key, count>
    // <count, node>
    // List<Node>: head-nodes-tail
    // Node: count, HashSet<String>, pre, next
    HashMap<String, Integer> counts;
    HashMap<Integer, Node> nodes;
    private Node head;
    private Node tail;

    /**
     * Initialize your data structure here.
     */
    public void AllOne() {
        head = new Node(Integer.MIN_VALUE);
        tail = new Node(Integer.MAX_VALUE);
        head.next = tail;
        tail.pre = head;
        counts = new HashMap<>();
        nodes = new HashMap<>();
    }

    private class Node {
        int count;
        Node pre;
        Node next;
        HashSet<String> s;

        Node(int cnt) {
            this.count = cnt;
            s = new HashSet<>();
        }
    }

    /**
     * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
     */
    public void inc(String key) {
        if (counts.containsKey(key)) {
            changeCount(key, 1);
        } else {
            if (head.next.count != 1) {
                addNodeAfter(new Node(1), head);
            }
            nodes.put(1, head.next);
            counts.put(key, 1);
            head.next.s.add(key);
        }
    }

    /**
     * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
     */
    public void dec(String key) {
        if (counts.containsKey(key)) {
            int count = counts.get(key);
            if (count == 1) {
                // remove from counts and node
                counts.remove(key);
                removeKeyFromNode(key, nodes.get(count));
            } else {
                changeCount(key, -1);
            }
        }
    }

    /**
     * Returns one of the keys with maximal value.
     */
    public String getMaxKey() {
        return tail.pre == head ? "" : (String) tail.pre.s.iterator().next();
    }

    /**
     * Returns one of the keys with Minimal value.
     */
    public String getMinKey() {
        return head.next == tail ? "" : (String) head.next.s.iterator().next();
    }

    private void changeCount(String key, int offset) {
        int curCount = counts.get(key);
        counts.put(key, curCount + offset);
        Node curNode = nodes.get(curCount);
        Node newNode;
        if (!nodes.containsKey(curCount + offset)) {
            newNode = new Node(curCount + offset);
            nodes.put(curCount + offset, newNode);
            addNodeAfter(newNode, offset == 1 ? curNode : curNode.pre);
        } else {
            newNode = nodes.get(curCount + offset);
        }

        removeKeyFromNode(key, curNode);
        newNode.s.add(key);

    }

    private void addNodeAfter(Node node, Node preNode) {
        node.pre = preNode;
        node.next = preNode.next;
        preNode.next.pre = node;
        preNode.next = node;

    }

    private void removeKeyFromNode(String key, Node node) {
        node.s.remove(key);
        if (node.s.size() == 0) {
            removeNode(node);
            nodes.remove(node.count);
        }
    }

    private void removeNode(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
        node.pre = null;
        node.next = null;
    }
}
