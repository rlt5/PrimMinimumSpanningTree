package Graph;

public class TreeNode extends Vertex {
    public TreeNode parent;

    public TreeNode(TreeNode parent) {
        this.parent = parent;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }
}
