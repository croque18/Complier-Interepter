import java.util.ArrayList;
import java.util.List;

public class BuiltInEnd extends FunctionNode{
    public BuiltInEnd(String name, ArrayList<VariableNode> ParamList, ArrayList<VariableNode> variableList, ArrayList<StatementNode> StateList) {
        super(name, ParamList, variableList, StateList);
    }

    public void Execute(List<InterpreterDataType> args) {
        if (args.size() != 2) {
            throw new RuntimeException("Invalid number of arguments");
        }
        int integer = Integer.parseInt(args.get(0).toString());
        args.get(0).FromString(String.valueOf((float) integer));
    }
}
