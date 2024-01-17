import java.util.ArrayList;
import java.util.List;

public class BuiltInSquareRoot extends FunctionNode {
    public BuiltInSquareRoot(String name, ArrayList<VariableNode> ParamList, ArrayList<VariableNode> variableList, ArrayList<StatementNode> StateList) {
        super(name, ParamList, variableList, StateList);
    }

    public void Execute(List<InterpreterDataType> args) {
        if (args.size() != 1) {
            throw new RuntimeException("Invalid number of arguments");
        }
        args.get(0).FromString(String.valueOf(args.get(0).getClass()));
    }
}
