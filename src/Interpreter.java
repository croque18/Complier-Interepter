import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class Interpreter {
    public void interpretFunction(FunctionNode functionNode) {
        HashMap<String, InterpreterDataType> localVariables = new HashMap<>();
        for (VariableNode variableNode : functionNode.getVariableList()) {
            InterpreterDataType IDT = new InterpreterDataType();
            IDT.setVariableNode(variableNode);
            localVariables.put(variableNode.getName(), IDT);
        }
        interpretBlock(functionNode.getStatementList(), localVariables);
    }

    public void interpretBlock(ArrayList<StatementNode> statementList, HashMap<String, InterpreterDataType> localVariables) {
        for (StatementNode statementNode : statementList) {
            if (statementNode instanceof AssignmentNode) {
                return;
            } else if (statementNode instanceof MathOpNode) {
                MathOpNode mathOpNode = (MathOpNode) statementNode;
                Node left = mathOpNode.getLeft();
                Node right = mathOpNode.getRight();
                if (left instanceof IntegerNode && right instanceof IntegerNode) {
                    IntegerNode leftInt = (IntegerNode) left;
                    IntegerNode rightInt = (IntegerNode) right;
                    int leftValue = leftInt.getValue();
                    int rightValue = rightInt.getValue();
                    int result = 0;
                } else if (left instanceof IntegerNode && right instanceof VariableReferenceNode) {
                    IntegerNode leftInt = (IntegerNode) left;
                    VariableReferenceNode rightVar = (VariableReferenceNode) right;
                    int leftValue = leftInt.getValue();
                    int rightValue = 0;
                    if (localVariables.containsKey(rightVar.getName())) {
                        InterpreterDataType IDT = localVariables.get(rightVar.getName());
                        rightValue = Integer.parseInt(IDT.toString());
                    } else {
                        throw new RuntimeException("Variable " + rightVar.getName() + " does not exist");
                    }
                    int result = 0;
                } else if (left instanceof VariableReferenceNode && right instanceof IntegerNode) {
                    VariableReferenceNode leftVar = (VariableReferenceNode) left;
                    IntegerNode rightInt = (IntegerNode) right;
                    int leftValue = 0;
                    if (localVariables.containsKey(leftVar.getName())) {
                        InterpreterDataType IDT = localVariables.get(leftVar.getName());
                        leftValue = Integer.parseInt(IDT.toString());
                    } else {
                        throw new RuntimeException("Variable " + leftVar.getName() + " does not exist");
                    }
                    int rightValue = rightInt.getValue();
                    int result = 0;
                } else if (left instanceof VariableReferenceNode && right instanceof VariableReferenceNode) {
                    VariableReferenceNode leftVar = (VariableReferenceNode) left;
                    VariableReferenceNode rightVar = (VariableReferenceNode) right;
                    int leftValue = 0;
                    int rightValue = 0;
                    if (localVariables.containsKey(leftVar.getName())) {
                        InterpreterDataType IDT = localVariables.get(leftVar.getName());
                        leftValue = Integer.parseInt(IDT.toString());
                    } else {
                        throw new RuntimeException("Variable " + leftVar.getName() + " does not exist");
                    }
                    if (localVariables.containsKey(rightVar.getName())) {
                        InterpreterDataType IDT = localVariables.get(rightVar.getName());
                        rightValue = Integer.parseInt(IDT.toString());
                    } else {
                        throw new RuntimeException("Variable " + rightVar.getName() + " does not exist");
                    }
                    int result = 0;
                }
            } else if (statementNode instanceof IfNode) {
                IfNode ifNode = (IfNode) statementNode;
                BooleanCompareNode booleanCompareNode = ifNode.getCondition();
                if (booleanCompareNode.getLeft() instanceof IntegerNode && booleanCompareNode.getRight() instanceof IntegerNode) {
                    IntegerNode leftInt = (IntegerNode) booleanCompareNode.getLeft();
                    IntegerNode rightInt = (IntegerNode) booleanCompareNode.getRight();
                    int leftValue = leftInt.getValue();
                    int rightValue = rightInt.getValue();
                    if (booleanCompareNode.getCompareType().equals("==")) {
                        if (leftValue == rightValue) {
                            interpretBlock(ifNode.getStatementList(), localVariables);
                        }
                    } else if (booleanCompareNode.getCompareType().equals("!=")) {
                        if (leftValue != rightValue) {
                            interpretBlock(ifNode.getStatementList(), localVariables);
                        }
                    } else if (booleanCompareNode.getCompareType().equals("<")) {
                        if (leftValue < rightValue) {
                            interpretBlock(ifNode.getStatementList(), localVariables);
                        }
                    } else if (booleanCompareNode.getCompareType().equals(">")) {
                        if (leftValue > rightValue) {
                            interpretBlock(ifNode.getStatementList(), localVariables);
                        }
                    } else if (booleanCompareNode.getCompareType().equals("<=")) {
                        if (leftValue <= rightValue) {
                            interpretBlock(ifNode.getStatementList(), localVariables);
                        }
                    } else if (booleanCompareNode.getCompareType().equals(">=")) {
                        if (leftValue >= rightValue) {
                            interpretBlock(ifNode.getStatementList(), localVariables);
                        }
                    }
                }
            } else if (statementNode instanceof ForNode) {
                ForNode forNode = (ForNode) statementNode;
                String name = String.valueOf(forNode.getTo());
                if (localVariables.containsKey(name)) {
                    InterpreterDataType IDT = localVariables.get(name);
                    if (IDT instanceof IntegerDataType) {
                        IntegerDataType integerDataType = (IntegerDataType) IDT;
                        int start = integerDataType.getValue();
                        Node end = forNode.getFrom();
                        Node step = forNode.getBody();
                        for (int i = start; i < Integer.parseInt(end.toString()); i += Integer.parseInt(step.toString())) {
                            interpretBlock(forNode.getStatementList(), localVariables);
                        }
                    }
                } else {
                    throw new RuntimeException("Variable " + name + " does not exist");
                }
            } else if (statementNode instanceof AssignmentNode) {
                AssignmentNode assignmentNode = (AssignmentNode) statementNode;
                String name = assignmentNode.getName();
                if (localVariables.containsKey(name)) {
                    InterpreterDataType IDT = localVariables.get(name);
                    if (IDT instanceof IntegerDataType) {
                        IntegerDataType integerDataType = (IntegerDataType) IDT;
                        Node right = assignmentNode.getExpression();
                        if (right instanceof IntegerNode) {
                            IntegerNode integerNode = (IntegerNode) right;
                            integerDataType.setValue(integerNode.getValue());
                        }
                    }
                } else {
                    throw new RuntimeException("Variable " + name + " does not exist");
                }
            } else if (statementNode instanceof FunctionCallNode) {
                FunctionCallNode functionCallNode = (FunctionCallNode) statementNode;
                String name = functionCallNode.getName();
                if (name.equals("print")) {
                    Node arg = functionCallNode.getArguments().get(0);
                    if (arg instanceof IntegerNode) {
                        IntegerNode integerNode = (IntegerNode) arg;
                        System.out.println(integerNode.getValue());
                    } else if (arg instanceof StringNode) {
                        StringNode stringNode = (StringNode) arg;
                        System.out.println(stringNode.getValue());
                    }
                }
            }
        }
    }

    public InterpreterDataType interpretExpression(Node node, HashMap<String, InterpreterDataType> localVariables) {
        if (node instanceof IntegerNode) {
            IntegerNode integerNode = (IntegerNode) node;
            InterpreterDataType IDT = new InterpreterDataType();
            IDT.setIntegerNode(integerNode);
            return IDT;
        } else if (node instanceof RealNode) {
            RealNode realNode = (RealNode) node;
            InterpreterDataType IDT = new InterpreterDataType();
            IDT.setRealNode(realNode);
            return IDT;
        } else if (node instanceof VariableReferenceNode) {
            VariableReferenceNode variableReferenceNode = (VariableReferenceNode) node;
            String name = variableReferenceNode.getName();
            if (localVariables.containsKey(name)) {
                InterpreterDataType IDT = localVariables.get(name);
                return IDT;
            } else {
                throw new RuntimeException("Variable " + name + " does not exist");
            }
        } else if (node instanceof FunctionNode) {
            FunctionNode functionNode = (FunctionNode) node;
            List<String> arguments = functionNode.getArguments();
            List<InterpreterDataType> args = new ArrayList<>();
            for (String argument : arguments) {
                args.add(interpretExpression(argument, localVariables));
            }
            functionNode.Execute(args);
        } else if (node instanceof MathOpNode) {
            MathOpNode mathOpNode = (MathOpNode) node;
            Node left = mathOpNode.getLeft();
            Node right = mathOpNode.getRight();
            if (left instanceof IntegerNode && right instanceof IntegerNode) {
                IntegerNode leftInt = (IntegerNode) left;
                IntegerNode rightInt = (IntegerNode) right;
                int leftValue = leftInt.getValue();
                int rightValue = rightInt.getValue();
                int result = 0;
                if (mathOpNode.getOperator().equals("+")) {
                    result = leftValue + rightValue;
                } else if (mathOpNode.getOperator().equals("-")) {
                    result = leftValue - rightValue;
                } else if (mathOpNode.getOperator().equals("*")) {
                    result = leftValue * rightValue;
                } else if (mathOpNode.getOperator().equals("/")) {
                    result = leftValue / rightValue;
                } else if (mathOpNode.getOperator().equals("%")) {
                    result = leftValue % rightValue;
                }
                InterpreterDataType IDT = new InterpreterDataType();
            }
        } else if (node instanceof VariableReferenceNode) {
            VariableReferenceNode variableReferenceNode = (VariableReferenceNode) node;
            String name = variableReferenceNode.getName();
            if (localVariables.containsKey(name)) {
                InterpreterDataType IDT = localVariables.get(name);
                System.out.println(IDT.toString());
            } else {
                throw new RuntimeException("Variable " + name + " does not exist");
            }
        }
        return null;
    }

    private InterpreterDataType interpretExpression(String argument, HashMap<String,InterpreterDataType> localVariables) {
        return null;
    }



}