import java.util.ArrayList;

public class BooleanCompareNode extends FunctionCallNode {
    private Node left;
    private Node right;
    private CompareType compare;

    enum CompareType {
        EQUAL,
        GREATER_THAN,
        GREATER_THAN_OR_EQUAL,
        LESS_THAN,
        LESS_THAN_OR_EQUAL,
        NOT_EQUAL

    }

    public BooleanCompareNode(CompareType compare, Node left, Node right) {
        this.left = left;
        this.right = right;
        this.compare = compare;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public CompareType getCompareType() {
        return compare;
    }

    @Override
    public String toString() {
        return String.format("(%s %s %s)", left.toString(), compare.toString(), right.toString());
    }

    @Override
    public ArrayList<StatementNode> getStatementList() {
        return null;
    }

    @Override
    public StatementNode[] getConstantList() {
        return new StatementNode[0];
    }

}
