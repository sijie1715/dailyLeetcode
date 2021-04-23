package Solutions;

public class P307_RangeSumQueryMutable {
    private SegmentTreeNode root = null;

    class SegmentTreeNode {
        int start, end;
        SegmentTreeNode left, right;
        int sum;

        private SegmentTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
            this.left = null;
            this.right = null;
            this.sum = 0;
        }
    }

    // O(N)
    public void NumArray(int[] nums) {
        root = buildTree(nums, 0, nums.length - 1);
    }

    private SegmentTreeNode buildTree(int[] nums, int start, int end) {
        if (start > end) return null;
        SegmentTreeNode newNode = new SegmentTreeNode(start, end);
        if (start == end) {
            newNode.sum = nums[start];
        } else {
            int mid = start + (end - start) / 2;
            newNode.left = buildTree(nums, start, mid);
            newNode.right = buildTree(nums, mid + 1, end);
            newNode.sum = newNode.left.sum + newNode.right.sum;
        }
        return newNode;
    }

    // O(logN)
    public void update(int index, int val) {
        update(root, index, val);
    }

    private void update(SegmentTreeNode root, int index, int val) {
        // converged to index
        if (root.start == root.end) {
            root.sum = val;
        } else {
            int mid = root.start + (root.end - root.start) / 2;
            if (index <= mid) {
                update(root.left, index, val);
            } else {
                update(root.right, index, val);
            }
            root.sum = root.left.sum + root.right.sum;
        }
    }

    public int sumRange(int left, int right) {
        return sumRange(root, left, right);
    }

    // O(logN)
    private int sumRange(SegmentTreeNode root, int start, int end) {
        if (root.start == start && root.end == end) {
            return root.sum;
        }
        int mid = root.start + (root.end - root.start) / 2;
        // target range on: right half, left half, cross middle
        if (start > mid) {
            return sumRange(root.right, start, end);
        } else if (end <= mid) {
            return sumRange(root.left, start, end);
        } else {
            return sumRange(root.left, start, mid) + sumRange(root.right, mid + 1, end);
        }
    }
}
