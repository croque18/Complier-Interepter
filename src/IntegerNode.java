import java.util.ArrayList;

public class IntegerNode extends Node {
    private int val;
    public IntegerNode(String value) {
        this.val = Integer.parseInt(value);
    }
    public int getValue() {
        return val;
    }

    @Override
    public String toString() {
        return Integer.toString(val);
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
