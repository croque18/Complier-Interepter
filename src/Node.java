import java.util.ArrayList;

public abstract class Node {
    public abstract String toString();

    public abstract ArrayList<StatementNode> getStatementList();

    public abstract StatementNode[] getConstantList();

    public Node expression() {
        return null;
    }
}
