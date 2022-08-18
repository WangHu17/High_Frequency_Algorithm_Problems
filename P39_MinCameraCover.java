/**
 * @author wanghu
 * @discrption： 给定一棵二叉树，将一个相机放在某个节点上，它能覆盖它的父节点和直接孩子节点。求要覆盖整棵树的所有节点需要至少多少个相机。
 * @create 2022-08-18 14:43
 */
public class P39_MinCameraCover {

    public int minCameraCover(TreeNode root) {
        Data data = process(root);
        return data.cameras + (data.status == Status.UNCOVERED ? 1 : 0);
    }

    public enum Status {
        UNCOVERED, COVERED_NO_CAMERA, COVERED_HAS_CAMERA
    }

    public class Data {
        public Status status;
        public int cameras;

        public Data(Status status, int cameras) {
            this.status = status;
            this.cameras = cameras;
        }
    }

    public Data process(TreeNode root) {
        if (root == null){
            return new Data(Status.COVERED_NO_CAMERA, 0);
        }
        Data left = process(root.left);
        Data right = process(root.right);
        int cameras = left.cameras + right.cameras;
        if (left.status == Status.UNCOVERED || right.status == Status.UNCOVERED) {
            return new Data(Status.COVERED_HAS_CAMERA, cameras + 1);
        }
        if (left.status == Status.COVERED_HAS_CAMERA || right.status == Status.COVERED_HAS_CAMERA) {
            return new Data(Status.COVERED_NO_CAMERA, cameras);
        }
        return new Data(Status.UNCOVERED, cameras);
    }

}
