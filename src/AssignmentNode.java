import java.util.ArrayList;

public class AssignmentNode extends StatementNode {
    private String variable;
    private Node expression;

    public AssignmentNode(String variable, Node expression) {
        this.variable = variable;
        this.expression = expression;
    }

    public String getVariable() {
        return variable;
    }

    public Node getExpression() {
        return expression;
    }

    @Override
    public String toString() {
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

    @Override
    public String getName() {
        return null;
    }
}
