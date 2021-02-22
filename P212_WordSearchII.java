package Solutions;

import java.util.ArrayList;
import java.util.List;

public class P212_WordSearchII {
    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = buildTrie(words);
        List<String> res = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                bt(board, i, j, root, res);
            }
        }
        return res;
    }

    private void bt(char[][] b, int i, int j, TrieNode p, List<String> res) {
        char c = b[i][j];
        if (c == '#' || p.next[c - 'a'] == null) return;
        p = p.next[c - 'a'];
        if (p.word != null) {       // found one
            res.add(p.word);
            p.word = null;          // avoid duplicate
        }

        b[i][j] = '#';
        if (i > 0) bt(b, i - 1, j, p, res);
        if (j > 0) bt(b, i, j - 1, p, res);
        if (i < b.length - 1) bt(b, i + 1, j, p, res);
        if (j < b[0].length - 1) bt(b, i, j + 1, p, res);
        b[i][j] = c;
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode w = root;
            for (char c : word.toCharArray()) {
                int num = c - 'a';
                if (w.next[num] == null) w.next[num] = new TrieNode();
                w = w.next[num];
            }
            w.word = word;
        }
        return root;
    }

    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word;
    }
}
