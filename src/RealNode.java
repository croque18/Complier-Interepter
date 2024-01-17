import java.util.ArrayList;

public class RealNode extends Node {
    private float val;
    public RealNode(String value) {
        this.val = Float.parseFloat(String.valueOf(value));
    }
    public float getValue() {
        return val;
    }

    @Override
    public String toString() {
        return Float.toString(val);
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