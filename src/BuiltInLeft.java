import java.util.ArrayList;
import java.util.List;

public class BuiltInLeft extends FunctionNode {
    public BuiltInLeft(String name, ArrayList<VariableNode> ParamList, ArrayList<VariableNode> variableList, ArrayList<StatementNode> StateList) {
        super(name, ParamList, variableList, StateList);
    }

    public void Execute(List<InterpreterDataType> args) {
        if (args.size() != 3) {
            throw new RuntimeException("Invalid number of arguments");
        }
        String arguments = args.get(0).toString();
        int integer = Integer.parseInt(args.get(1).toString());
        String empty = "";
        for (int i = 0; i < integer; i++) {
            empty += arguments.charAt(i);
        }
        args.get(2).FromString(empty);
        args.get(0).FromString(String.valueOf(args.get(0).getClass()));
    }
}