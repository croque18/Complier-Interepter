import java.util.ArrayList;
import java.util.List;

public class BuiltInIntegerToReal extends FunctionNode {
    public BuiltInIntegerToReal(String name, ArrayList<VariableNode> ParamList, ArrayList<VariableNode> variableList, ArrayList<StatementNode> StateList) {
        super(name, ParamList, variableList, StateList);
    }
    public void Execute(List<InterpreterDataType> args) {
        if (args.size() != 1) {
            throw new RuntimeException("Invalid number of arguments");
        }
        InterpreterDataType arg = args.get(0);
        if (arg instanceof IntegerDataType) {
            IntegerDataType integer = (IntegerDataType) arg;
            RealDataType real = new RealDataType(Double.valueOf(integer.getValue()));
        } else {
            throw new RuntimeException("Invalid argument type");
        }
    }
}
