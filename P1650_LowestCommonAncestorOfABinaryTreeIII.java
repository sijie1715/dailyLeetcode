package Solutions;

public class P1650_LowestCommonAncestorOfABinaryTreeIII {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }

    public Node lowestCommonAncestor(Node p, Node q) {
        int pdepth = getDepth(p), qdepth = getDepth(q);
        // s1 >= s2
        // get s1, s2 to the same distance from root
        Node s1 = pdepth >= qdepth ? p : q;
        Node s2 = s1 == p ? q : p;
        int diff = Math.abs(pdepth - qdepth);
        while (diff-- > 0) {
            s1 = s1.parent;
        }
        // start from same dist from root; once meet return
        while (s1 != s2) {
            s1 = s1.parent;
            s2 = s2.parent;
        }
        return s1;
    }

    private int getDepth(Node p) {
        int d = 0;
        while (p.parent != null) {
            d++;
            p = p.parent;
        }
        return d;
    }
}
