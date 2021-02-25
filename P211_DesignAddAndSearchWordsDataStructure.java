package Solutions;

public class P211_DesignAddAndSearchWordsDataStructure {
    /**
     * Initialize your data structure here.
     */
    private TrieNode root;

//    public WordDictionary() {
//        root = new TrieNode();
//    }

    public void addWord(String word) {
        TrieNode p = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (p.next[idx] == null) p.next[idx] = new TrieNode();
            p = p.next[idx];
        }
        p.isWord = true;
    }

    public boolean search(String word) {
        TrieNode p = root;
        return search(word, 0, p);
    }

    private boolean search(String s, int i, TrieNode p) {
        if (p == null) return false;
        if (i == s.length()) return p.isWord;
        char c = s.charAt(i);
        if (c == '.') {
            for (int j = 0; j < 26; j++) {
                if (search(s, i + 1, p.next[j])) return true;
            }
        } else {
            int idx = c - 'a';
            return search(s, i + 1, p.next[idx]);
        }
        return false;
    }

    private class TrieNode {
        boolean isWord;
        TrieNode[] next;

        TrieNode() {
            isWord = false;
            next = new TrieNode[26];
        }
    }
}
