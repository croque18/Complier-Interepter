import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Parser {
    List<Token> tokens;
    int index = 0;
    private HashMap<String, FunctionNode> functionNodeHashMap;
    private FunctionNode programNode;

    public Parser(List<Token> tokens) {
        functionNodeHashMap = new HashMap<>();
    }

    public boolean parse() throws Exception {
        FunctionNode functionNode;
        while ((functionNode = function()) != null) {
            functionNodeHashMap.put(functionNode.getFunctionName(), functionNode);
        }
        return true;
    }

    private Node parseExpression() throws Exception {
        Node left = parseTerm();
        if(tokens.size() == 0) {
            return left;
        }
        MathOpNode.Operator operator;

        if (matchAndRemove(Token.tokenType.PLUS) != null) {
            operator = MathOpNode.Operator.PLUS;
        } else if (matchAndRemove(Token.tokenType.MINUS) != null) {
            operator = MathOpNode.Operator.MINUS;
        } else {
            return left;
        }
        Node right = parseTerm();
        if (right == null) {
            throw new Exception("No right operand." + tokens.get(0).toString());
        }
        while (matchAndRemove(Token.tokenType.ENDOFLINE) != null) {
            left = new MathOpNode(operator, left, right);
            if (matchAndRemove(Token.tokenType.PLUS) != null) {
                operator = MathOpNode.Operator.PLUS;
            } else if (matchAndRemove(Token.tokenType.MINUS) != null) {
                operator = MathOpNode.Operator.MINUS;
            } else {
                return left;
            }
            right = parseTerm();
            if (right == null) {
                throw new Exception("No right operand." + tokens.get(0).toString());
            }
        }
        return new MathOpNode(operator, left, right);
    }

    private Node parseTerm() throws Exception {
        Node left = parseFactor();
        MathOpNode.Operator operator;
        if (peek(0).getType() == Token.tokenType.DIVIDE) {
            index++;
            operator = MathOpNode.Operator.DIVIDE;
        } else if (peek(0).getType() == Token.tokenType.MULTIPLY) {
            index++;
            operator = MathOpNode.Operator.MULTIPLY;
        } else {
            return left;
        }
        Node right = parseFactor();
        if (right == null) {
            throw new Exception("No right operand." + tokens.get(0).toString());
        }
        while (matchAndRemove(Token.tokenType.ENDOFLINE) != null) {
            left = new MathOpNode(operator, left, right);
            if (peek(0).getType() == Token.tokenType.DIVIDE) {
                index++;
                operator = MathOpNode.Operator.DIVIDE;
            } else if (peek(0).getType() == Token.tokenType.MULTIPLY) {
                index++;
                operator = MathOpNode.Operator.MULTIPLY;
            } else {
                return left;
            }
            right = parseFactor();
            if (right == null) {
                throw new Exception("No right operand." + tokens.get(0).toString());
            }
        }
        return new MathOpNode(operator, left, right);
    }

    private Node parseFactor() throws Exception {
        Token token = tokens.get(index);
        if (peek(0).getType() == Token.tokenType.LeftParenthesis) {
            index++;
            Node node = parseExpression();
            if (peek(0).getType() == Token.tokenType.RightParenthesis) {
                index++;
                return node;
            } else {
                throw new Exception("Expected right parenthesis.");
            }
        } else if (peek(0).getType() == Token.tokenType.NUMBER) {
            index++;
            return new IntegerNode(token.getValue());
        } else if (peek(0).getType() == Token.tokenType.IDENTIFIER) {
            VariableReferenceNode tempVarRef;
            Token tempToken = tokens.get(index);
            Node indexNode = null;
            if (matchAndRemove(Token.tokenType.LeftBracket) != null) {
                indexNode = parseExpression();
                if (matchAndRemove(Token.tokenType.RightBracket) == null) {
                    throw new Exception("Expected right bracket.");
                }
            }
            index++;
            return new RealNode(token.getValue());
        } else {
            throw new Exception("Expected number or identifier.");
        }
    }

    private Token matchAndRemove(Token.tokenType type) throws Exception {
        if (index < tokens.size() && tokens.get(index).getType() == type) {
            index++;
            return tokens.get(index - 1);
        } else {
            return null;
        }
    }

    private void expectEndOfLine() throws Exception {
        if (matchAndRemove(Token.tokenType.ENDOFLINE) == null) {
            throw new Exception("Expected End of line");
        } else {
            index++;
        }
    }

    private Token peek(int index) {
        return tokens.get(index);
    }

    private FunctionNode function() throws Exception {
        Token token = matchAndRemove(Token.tokenType.FUNCTION);
        if (token == null) {
            return null;
        }
        String functionName = token.getValue();
        if (matchAndRemove(Token.tokenType.LeftParenthesis) == null) {
            throw new Exception("Expected left parenthesis.");
        }
        List<String> parameters = new ArrayList<>();
        while (true) {
            token = matchAndRemove(Token.tokenType.IDENTIFIER);
            if (token == null) {
                throw new Exception("Expected identifier.");
            }
            parameters.add(token.getValue());
            if (matchAndRemove(Token.tokenType.RightParenthesis) != null) {
                break;
            }
            if (matchAndRemove(Token.tokenType.COMMA) == null) {
                throw new Exception("Expected comma.");
            }
        }
        return new FunctionNode();
    }

    private List<VariableNode> parameterDeclarations() throws Exception {
        List<VariableNode> variableNodes = new ArrayList<>();
        while (true) {
            Token token = matchAndRemove(Token.tokenType.IDENTIFIER);
            if (token == null) {
                break;
            }
            variableNodes.add(new VariableNode(token.getValue()));
            if (matchAndRemove(Token.tokenType.COMMA) == null) {
                break;
            }
        }
        return variableNodes;
    }

    private List<VariableNode> constants() throws Exception {
        List<VariableNode> variableNodes = new ArrayList<>();
        while (true) {
            Token token = matchAndRemove(Token.tokenType.IDENTIFIER);
            if (token == null) {
                break;
            }
            variableNodes.add(new VariableNode(token.getValue()));
            if (matchAndRemove(Token.tokenType.COMMA) == null) {
                break;
            }
        }
        return variableNodes;
    }

    private List<VariableNode> variables() throws Exception {
        List<VariableNode> variableNodes = new ArrayList<>();
        while (true) {
            Token token = matchAndRemove(Token.tokenType.IDENTIFIER);
            if (token == null) {
                break;
            }
            VariableNode var = new VariableNode(token.getValue());
            Token from = matchAndRemove(Token.tokenType.FROM);
            if (from != null) {
                Token Start = matchAndRemove(Token.tokenType.NUMBER);
                Token to = matchAndRemove(Token.tokenType.TO);
                Token End = matchAndRemove(Token.tokenType.NUMBER);
            } else {
                variableNodes.add(var);
            }
            if (matchAndRemove(Token.tokenType.COMMA) == null) {
                break;
            }
        }
        return variableNodes;
    }

    public Node boolCompare() throws Exception {
        Node left = parseExpression();
        BooleanCompareNode.CompareType operator;
        Token.tokenType type = peek(0).getType();
        switch (type) {
            case EQUAL:
                index++;
                operator = BooleanCompareNode.CompareType.EQUAL;
                break;
            case NOT_EQUAL:
                index++;
                operator = BooleanCompareNode.CompareType.NOT_EQUAL;
                break;
            case LESS_THAN:
                index++;
                operator = BooleanCompareNode.CompareType.LESS_THAN;
                break;
            case GREATER_THAN:
                index++;
                operator = BooleanCompareNode.CompareType.GREATER_THAN;
                break;
            case LESS_THAN_OR_EQUAL:
                index++;
                operator = BooleanCompareNode.CompareType.LESS_THAN_OR_EQUAL;
                break;
            case GREATER_THAN_OR_EQUAL:
                index++;
                operator = BooleanCompareNode.CompareType.GREATER_THAN_OR_EQUAL;
                break;
            default:
                return left;
        }
        return new BooleanCompareNode(operator, left, parseExpression());
    }

    public AssignmentNode assignment() throws Exception {
        Token token = matchAndRemove(Token.tokenType.IDENTIFIER);
        if (token == null) {
            return null;
        }
        String variableName = token.getValue();
        if (matchAndRemove(Token.tokenType.ASSIGNMENT) == null) {
            throw new Exception("Expected assignment operator.");
        }
        return new AssignmentNode(variableName, parseExpression());
    }

    public StatementNode statement() throws Exception {
        ArrayList<StatementNode> statementNodes = new ArrayList<>();
        if (matchAndRemove(Token.tokenType.INDENTATION) == null) {
            throw new Exception("Expected indentation.");
        }
        while (matchAndRemove(Token.tokenType.DEDENTATION) != null) {
            statementNodes.add(statement());

        }
        return statementNodes.get(0);
    }

    public ForNode parseFor() throws Exception {
        Token token = matchAndRemove(Token.tokenType.FOR);
        if (token == null) {
            return null;
        }
        String variableName = token.getValue();
        if (matchAndRemove(Token.tokenType.IN) == null) {
            throw new Exception("Expected in.");
        }
        Node start = parseExpression();
        if (matchAndRemove(Token.tokenType.TO) == null) {
            throw new Exception("Expected to.");
        }
        Node end = parseExpression();
        if (matchAndRemove(Token.tokenType.INDENTATION) == null) {
            throw new Exception("Expected indentation.");
        }
        List<StatementNode> statementNode = new ArrayList<>();
        while (matchAndRemove(Token.tokenType.DEDENTATION) != null) {
            statementNode.add(statement());
        }
        return new ForNode(parseFor().getFrom(), parseFor().getTo(), parseFor().getBody());
    }

    public WhileNode parseWhile() throws Exception {
        Token token = matchAndRemove(Token.tokenType.WHILE);
        if (token == null) {
            return null;
        }
        Node condition = parseExpression();
        if (matchAndRemove(Token.tokenType.INDENTATION) == null) {
            throw new Exception("Expected indentation.");
        }
        List<StatementNode> statementNode = new ArrayList<>();
        while (matchAndRemove(Token.tokenType.DEDENTATION) != null) {
            statementNode.add(statement());
        }
        return new WhileNode(parseWhile().getCondition(), parseWhile().getStatementList());
    }

    public IfNode parseIf() throws Exception {
        Token token = matchAndRemove(Token.tokenType.IF);
        if (token == null) {
            return null;
        }
        Node condition = parseExpression();
        if (matchAndRemove(Token.tokenType.INDENTATION) == null) {
            throw new Exception("Expected indentation.");
        }
        List<StatementNode> statementNode = new ArrayList<>();
        while (matchAndRemove(Token.tokenType.DEDENTATION) != null) {
            statementNode.add(statement());
        }
        return new IfNode(parseIf().getCondition(), parseIf().getStatementList(), parseIf().getNextIf());
    }

    public FunctionCallNode parseFunctionCall() throws Exception {
        Token token = matchAndRemove(Token.tokenType.IDENTIFIER);
        if (token == null) {
            return null;
        }
        String functionName = token.getValue();
        if (matchAndRemove(Token.tokenType.LeftParenthesis) == null) {
            throw new Exception("Expected left parenthesis.");
        }
        List<Node> parameters = new ArrayList<>();
        while (true) {
            parameters.add(parseExpression());
            if (matchAndRemove(Token.tokenType.RightParenthesis) != null) {
                break;
            }
            if (matchAndRemove(Token.tokenType.COMMA) == null) {
                throw new Exception("Expected comma.");
            }
        }
        return new FunctionCallNode(functionName, parameters);
    }

    public FunctionNode getProgramNode() {
        return programNode;
    }
}

