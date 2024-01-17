import java.util.ArrayList;

public class ParameterNode extends Node{
    private String name;
    private VariableReferenceNode type;
    public ParameterNode() {
    }
    public ParameterNode(String name, VariableReferenceNode type) {
        this.name = name;
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public VariableReferenceNode getType() {
        return type;
    }

    @Override
    public String toString() {
        return type + " " + name;
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
