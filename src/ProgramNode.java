import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProgramNode extends Node {
    private Map<String, FunctionNode> variables;
    public ProgramNode() {
        this.variables = new HashMap<>();
    }
    public Map<String, FunctionNode> getVariables() {
        return variables;
    }
    public void addVariable(FunctionNode variable) {
        variables.put(variable.getFunctionName(), variable);
    }
    public void getVariable(Map<String, FunctionNode> variables) {
        this.variables = variables;
    }
    public FunctionNode getVariable(String name) {
        return variables.get(name);
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

}
