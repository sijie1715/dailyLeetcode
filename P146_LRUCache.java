package Solutions;

import java.util.HashMap;

public class P146_LRUCache {
    // LinkedList to keep track of key used order
    // HashMap to store key-Node pair
    private Node head, tail;
    private int capacity;
    HashMap<Integer, Node> map;

    private class Node {
        int key;
        int val;
        Node pre;
        Node next;
    }

    private void addNode(Node node) {
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
        node.next = null;
        node.pre = null;
    }

    public void LRUCache(int capacity) {
        this.capacity = capacity;
        this.head = new Node();
        this.tail = new Node();
        head.pre = null;
        head.next = tail;
        tail.pre = head;
        tail.next = null;
        map = new HashMap<>();
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        } else {
            removeNode(node);
            addNode(node);
        }
        return node.val;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node == null) {
            // add new node
            node = new Node();
            node.key = key;
            node.val = value;
            addNode(node);
            map.put(key, node);
        } else {
            node.val = value;
            removeNode(node);
            addNode(node);
        }
        if (map.size() > capacity) {
            map.remove(tail.pre.key);
            removeNode(tail.pre);
        }
    }
}
