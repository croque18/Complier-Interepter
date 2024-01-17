import java.util.ArrayList;

public class VariableNode extends Node {
    private String name;
    private Node value;
    private int to = 0;
    private int from = 0;
    private float toFloat = 0;
    private float fromFloat = 0;

    public VariableNode(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public Node getValue() {
        return value;
    }
    public void setValue(Node value) {
        this.value = value;
    }

    @Override
    public String toString() {
        if (value != null) {
            return name + " = " + value;
        } else if (from != 0 && to != 0) {
            return name + " = " + from + " to " + to;
        } else if (fromFloat != 0 && toFloat != 0) {
            return name + " = " + fromFloat + " to " + toFloat;
        } else {
            return name;
        }
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