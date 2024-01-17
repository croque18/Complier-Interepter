import java.util.ArrayList;
import java.util.List;

public class SemanticAnalysis {

    private static void checkAssignments(String argument, String argument1) {
    }

    private static void checkAssignments(StatementNode statement) {
        if (statement instanceof AssignmentNode) {
            checkAssignments((AssignmentNode) statement);
        } else if (statement instanceof IfNode) {
            checkAssignments(statement);
        } else if (statement instanceof WhileNode) {
            checkAssignments(statement);
        } else if (statement instanceof ForNode) {
            checkAssignments(statement);
        } else if (statement instanceof FunctionCallNode) {
            checkAssignments(statement);
        }
    }

    private static void checkAssignments(AssignmentNode assignment) {
        String variableName = assignment.getName();
        Node variable = assignment.getExpression();
        if (variable == null) {
            throw new RuntimeException("Variable " + variableName + " is not defined");
        }
        checkAssignments((VariableNode) variable, assignment.getExpression());
    }

    private static void checkAssignments(VariableNode variable, Node expression) {
        if (expression instanceof VariableNode variableNode) {
            if (!variable.getName().equals(variableNode.getName())) {
                throw new RuntimeException("Variable " + variable.getName() + " is not of type " + variableNode.getName());
            }
        } else if (expression instanceof MathOpNode mathOpNode) {
            checkAssignments(variable, mathOpNode.getLeft());
            checkAssignments(variable, mathOpNode.getRight());
        } else if (expression instanceof BooleanCompareNode booleanCompareNode) {
            checkAssignments(variable, booleanCompareNode.getLeft());
            checkAssignments(variable, booleanCompareNode.getRight());
        } else if (expression instanceof FunctionCallNode functionCallNode) {
            checkAssignments(variable, (Node) functionCallNode.getArguments());
        }
    }

    public static void checkFunctionCall(FunctionCallNode functionCallNode) {
        String functionName = functionCallNode.getName();
        FunctionNode functionNode = (FunctionNode) functionCallNode.getArguments();
        if (functionNode == null) {
            throw new RuntimeException("Function " + functionName + " is not defined");
        }
        ArrayList<Node> arguments = (ArrayList<Node>) functionCallNode.getArguments();
        if (arguments.size() != functionNode.getArguments().size() && !functionNode.isVariadic()) {
            throw new RuntimeException("Function " + functionName + " is not defined");
        }
        ArrayList<InterpreterDataType> values = new ArrayList<>();
        for (Node argument : arguments) {
            Node expression = argument.expression();
            InterpreterDataType idt = new InterpreterDataType(expression);
            values.add(idt);
        }
        if (functionNode instanceof FunctionNode) {
            functionNode.expression();
        }
        for (InterpreterDataType value : values) {
            if (functionNode.isVariadic() || functionNode.isVariadic()) {
                value.getVariable().setValue(value.getValue());
            } else if (!functionNode.isVariadic() && !functionNode.isVariadic()) {
                value.getVariable().setValue(value.getValue());
            } else {
                throw new RuntimeException("Function " + functionName + " is not defined");
            }
        }
    }

    public static void checkIf(IfNode ifNode) {
        Node condition = ifNode.getCondition();
        if (condition instanceof BooleanCompareNode booleanCompareNode) {
            checkAssignments((VariableNode) booleanCompareNode.getLeft(), booleanCompareNode.getLeft());
            checkAssignments((VariableNode) booleanCompareNode.getRight(), booleanCompareNode.getRight());
        }
        ArrayList<StatementNode> statementList = ifNode.getStatementList();
        for (StatementNode statement : statementList) {
            checkAssignments(statement);
        }
    }

    public static void checkWhile(WhileNode whileNode) {
        Node condition = whileNode.getCondition();
        if (condition instanceof BooleanCompareNode booleanCompareNode) {
            checkAssignments((VariableNode) booleanCompareNode.getLeft(), booleanCompareNode.getLeft());
            checkAssignments((VariableNode) booleanCompareNode.getRight(), booleanCompareNode.getRight());
        }
        ArrayList<StatementNode> statementList = whileNode.getStatementList();
        for (StatementNode statement : statementList) {
            checkAssignments(statement);
        }
    }

    public static void checkFor(ForNode forNode) {
        Node condition = forNode.getCondition();
        if (condition instanceof BooleanCompareNode booleanCompareNode) {
            checkAssignments((VariableNode) booleanCompareNode.getLeft(), booleanCompareNode.getLeft());
            checkAssignments((VariableNode) booleanCompareNode.getRight(), booleanCompareNode.getRight());
        }
        ArrayList<StatementNode> statementList = forNode.getStatementList();
        for (StatementNode statement : statementList) {
            checkAssignments(statement);
        }
    }

    public static void checkFunction(FunctionNode functionNode) {
        List<String> arguments = functionNode.getArguments();
        for (String argument : arguments) {
            checkAssignments(argument, argument);
        }
        ArrayList<StatementNode> statementList = functionNode.getStatementList();
        for (StatementNode statement : statementList) {
            checkAssignments(statement);
        }
    }

    private static void checkStatements(ProgramNode program, ArrayList<StatementNode> statements) {
        for (StatementNode statement : statements) {
            if (statement instanceof AssignmentNode) {
                checkAssignments((AssignmentNode) statement);
            } else if (statement instanceof IfNode) {
                checkStatements(program, statement.getStatementList());
            } else if (statement instanceof WhileNode) {
                checkStatements(program, statement.getStatementList());
            } else if (statement instanceof ForNode) {
                checkStatements(program, statement.getStatementList());
            } else if (statement instanceof FunctionCallNode) {
                checkStatements(program, statement.getStatementList());
            }
        }
        for (FunctionNode function : program.getVariables().values()) {
            while (function.getIterator().hasNext()) {
                FunctionNode functionNode = function.getIterator().next();
                checkStatements(program, functionNode.getStatementList());
            }
        }
    }
}
