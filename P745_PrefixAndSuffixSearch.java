package Solutions;

public class P745_PrefixAndSuffixSearch {
    // Take "abc" as an example, we will insert add "abc{abc", "bc{abc", "c{abc", "{abc" into the Trie Tree.
    // If the query is: prefix = "a", suffix = "c", we can find it by querying our trie for "c{a".

    private TrieNode root;

    public P745_PrefixAndSuffixSearch(String[] words) {
        root = new TrieNode();
        for (int idx = 0; idx < words.length; idx++) {
            String word = words[idx] + "{";         // "abc{"
            // put abc{abc, bc{abc, c{abc, {abc in trie
            for (int i = 0; i < word.length(); i++) {
                TrieNode cur = root;
                // link with the latest/max idx
                cur.idx = idx;
                // exclude the last "{"
                for (int j = i; j < word.length() * 2 - 1; j++) {
                    int k = word.charAt(j % word.length()) - 'a';
                    if (cur.next[k] == null) cur.next[k] = new TrieNode();
                    cur = cur.next[k];
                    cur.idx = idx;
                }
            }
        }
    }

    public int f(String prefix, String suffix) {
        TrieNode cur = root;
        String word = suffix + "{" + prefix;
        for (char c : word.toCharArray()) {
            if (cur.next[c - 'a'] == null) return -1;
            cur = cur.next[c - 'a'];
        }
        return cur.idx;
    }

    class TrieNode {
        TrieNode[] next;
        int idx;

        TrieNode() {
            next = new TrieNode[27];
            idx = 0;
        }
    }
}
