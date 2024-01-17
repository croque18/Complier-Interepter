import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class FunctionNode extends Node {
    private String functionName;
    protected List<String> arguments;
    private HashMap<String, FunctionNode> builtInFunctions = new HashMap<>();
    private ArrayList<VariableNode> variableList = new ArrayList<>();
    private ArrayList<StatementNode> stateList = new ArrayList<>();

    public FunctionNode(String functionName) {
        this.functionName = functionName;
        this.arguments = new ArrayList<>();
    }

    public FunctionNode(String functionName, ArrayList<VariableNode> parameters, ArrayList<VariableNode> variableList, ArrayList<StatementNode> stateList) {
        this.functionName = functionName;
        this.variableList = variableList;
        this.stateList = stateList;
        this.arguments = new ArrayList<>();
        for (VariableNode parameter : parameters) {
            this.arguments.add(parameter.getName());
        }
    }

    public FunctionNode(ArrayList<VariableNode> parameters) {
    }
    public FunctionNode() {
        this.functionName = null;
    }
    public String getFunctionName() {
        return functionName;
    }

    public boolean isVariadic() {
        return false;
    }

    public void Execute(List<InterpreterDataType> args) {
    }

    public List<String> getArguments() {
        return arguments;
    }

    @Override
    public String toString() {
        return "FunctionNode{" +
                "functionName='" + functionName + '\'' +
                ", arguments=" + arguments +
                '}';
    }

    @Override
    public ArrayList<StatementNode> getStatementList() {
        return null;
    }

    @Override
    public StatementNode[] getConstantList() {
        return new StatementNode[0];
    }

    public VariableNode[] getVariableList() {
        return null;
    }

    public Iterator<FunctionNode> getIterator() {
        return null;
    }
}
