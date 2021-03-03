package Solutions;

import java.util.*;

public class P642_DesignSearchAutocompleteSystem {
    class AutocompleteSystem {

        private String prefix;
        private TrieNode root;

        public AutocompleteSystem(String[] sentences, int[] times) {
            prefix = "";
            root = new TrieNode();
            for (int i = 0; i < sentences.length; i++) {
                add2Trie(sentences[i], times[i]);
            }
        }

        public List<String> input(char c) {
            if (c == '#') {
                add2Trie(prefix, 1);
                prefix = "";
                return new ArrayList<>();
            }

            TrieNode cur = root;
            prefix += c;
            for (char ch : prefix.toCharArray()) {
                TrieNode nextNode = cur.next.get(ch);
                if (nextNode == null) {
                    return new ArrayList<>();
                }
                cur = nextNode;
            }
            // pull all seentences from current node
            PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((a, b) -> (a.getValue() == b.getValue()) ? a.getKey().compareTo(b.getKey()) : -a.getValue() + b.getValue());
            pq.addAll(cur.counts.entrySet());

            List<String> res = new ArrayList<>();
            int k = 3;
            while (!pq.isEmpty() && k > 0) {
                res.add(pq.poll().getKey());
                k--;
            }
            return res;
        }

        private void add2Trie(String s, int count) {
            TrieNode cur = root;
            for (char c : s.toCharArray()) {
                // cur.next.computeIfAbsent(c, k -> new TrieNode());
                cur.next.putIfAbsent(c, new TrieNode());
                cur = cur.next.get(c);
                // need to put sentence count at every node
                cur.counts.put(s, cur.counts.getOrDefault(s, 0) + count);
            }
        }

        private class TrieNode {
            Map<Character, TrieNode> next;
            Map<String, Integer> counts;

            TrieNode() {
                next = new HashMap<>();
                counts = new HashMap<>();
            }
        }
    }

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
}
