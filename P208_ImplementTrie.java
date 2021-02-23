package Solutions;

import java.util.HashMap;
import java.util.Map;

public class P208_ImplementTrie {
    /**
     * Initialize your data structure here.
     */
    private TrieNode root;

//    public Trie() {
//        root = new TrieNode();
//    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            if (!cur.next.containsKey(c)) {
                cur.next.put(c, new TrieNode());
            }
            cur = cur.next.get(c);
        }
        cur.isWord = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            if (!cur.next.containsKey(c)) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        for (char c : prefix.toCharArray()) {
            if (!cur.next.containsKey(c)) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }

    private class TrieNode {
        Map<Character, TrieNode> next;
        boolean isWord;

        TrieNode() {
            next = new HashMap<>();
            isWord = false;
        }
    }
}
