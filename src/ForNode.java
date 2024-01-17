import java.util.ArrayList;

public class ForNode extends StatementNode {
    private Node from;
    private Node to;
    private Node body;
    private ArrayList<StatementNode> statementList;

    public ForNode(Node from, Node to, Node body) {
        this.from = from;
        this.to = to;
        this.body = body;
    }

    public Node getFrom() {
        return from;
    }

    public Node getTo() {
        return to;
    }

    public Node getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "for (" + from + " to " + to + ") " + body;
    }

    @Override
    public String getName() {
        return null;
    }

    public ArrayList<StatementNode> getStatementList() {
        return statementList;
    }

    @Override
    public StatementNode[] getConstantList() {
        return new StatementNode[0];
    }

    public void setStatementList(ArrayList<StatementNode> statementList) {
        this.statementList = statementList;
    }

    public Node getCondition() {
        return null;
    }
}
