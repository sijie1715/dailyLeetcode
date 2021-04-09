package Solutions;

public class P331_VerifyPreorderSerializationOfABinaryTree {
    public boolean isValidSerialization(String preorder) {
        // diff = out - in
        // each node diff--; if not# diff+=2
        int diff = 1;
        String[] nodes = preorder.split(",");
        for (String node : nodes) {
            if (--diff < 0) return false;
            if (!node.equals("#")) diff += 2;
        }
        return diff == 0;
    }
}
