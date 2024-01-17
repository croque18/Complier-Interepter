import java.util.ArrayList;
import java.util.List;

public class FunctionCallNode extends StatementNode{
    private String name;
    private List<Node> arguments;

    public FunctionCallNode(String name, List<Node> arguments) {
        this.name = name;
        this.arguments = arguments;
    }

    public FunctionCallNode() {
    }

    public String getName() {
        return name;
    }

    public List<Node> getArguments() {
        return arguments;
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
