import java.util.ArrayList;

public class MathOpNode extends StatementNode {
    enum Operator {
        PLUS,
        MINUS,
        DIVIDE,
        MULTIPLY,
    }
    private Operator op;
    private Node left;
    private Node right;

    public MathOpNode(Operator op, Node left, Node right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public Operator getOperator() {
        return op;
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " " + op.toString() + " " + right.toString() + ")";
    }

    @Override
    public String getName() {
        return null;
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
