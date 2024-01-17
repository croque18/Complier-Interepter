import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class is used to tokenize the input string.
 * It will take the input string and break it down into tokens.
 * It will then return the tokens in an array list.
 */
public class Lexer {

    private enum tokeType {
        START, END, FOR, WRITE, READ, LEFT, RIGHT,
        SUBSTRING, SQUAREROOT, INTEGERTOREAL, REALTOINTEGER,
        VARIABLES, CONSTANTS, DEFINE, IF, THEN, ELSIF, ELSE,
        WHILE, REPEAT, FROM, VAR, ARRAY, OF,
        STRING, REAL, CHARACTER, BOOLEAN, TO, READONLY,
        ALSOREADONLY, UNTIL;
    }

    HashMap<String, tokeType> knownWords = new HashMap<String, tokeType>();
    private ArrayList<Token> token = new ArrayList<Token>();

    public Lexer(List<String> lines) throws Exception {
        knownWords.put("start", tokeType.START);
        knownWords.put("end", tokeType.END);
        knownWords.put("for", tokeType.FOR);
        knownWords.put("write", tokeType.WRITE);
        knownWords.put("read", tokeType.READ);
        knownWords.put("left", tokeType.LEFT);
        knownWords.put("right", tokeType.RIGHT);
        knownWords.put("substring", tokeType.SUBSTRING);
        knownWords.put("squareRoot", tokeType.SQUAREROOT);
        knownWords.put("integerToReal", tokeType.INTEGERTOREAL);
        knownWords.put("realToInteger", tokeType.REALTOINTEGER);
        knownWords.put("variables", tokeType.VARIABLES);
        knownWords.put("constants", tokeType.CONSTANTS);
        knownWords.put("define", tokeType.DEFINE);
        knownWords.put("if", tokeType.IF);
        knownWords.put("then", tokeType.THEN);
        knownWords.put("elsif", tokeType.ELSIF);
        knownWords.put("else", tokeType.ELSE);
        knownWords.put("while", tokeType.WHILE);
        knownWords.put("repeat", tokeType.REPEAT);
        knownWords.put("from", tokeType.FROM);
        knownWords.put("var", tokeType.VAR);
        knownWords.put("array", tokeType.ARRAY);
        knownWords.put("of", tokeType.OF);
        knownWords.put("string", tokeType.STRING);
        knownWords.put("real", tokeType.REAL);
        knownWords.put("character", tokeType.CHARACTER);
        knownWords.put("boolean", tokeType.BOOLEAN);
        knownWords.put("to", tokeType.TO);
        knownWords.put("readonly", tokeType.READONLY);
        knownWords.put("alsoreadonly", tokeType.ALSOREADONLY);
        knownWords.put("until", tokeType.UNTIL);

        boolean doWeHaveStart = knownWords.containsKey("start");
        boolean doWeHaveEnd = knownWords.containsKey("end");
        boolean doWeHaveFor = knownWords.containsKey("for");
        boolean doWeHaveWrite = knownWords.containsKey("write");
        boolean doWeHaveRead = knownWords.containsKey("read");
        boolean doWeHaveLeft = knownWords.containsKey("left");
        boolean doWeHaveRight = knownWords.containsKey("right");
        boolean doWeHaveSubstring = knownWords.containsKey("substring");
        boolean doWeHaveSquareRoot = knownWords.containsKey("squareRoot");
        boolean doWeHaveIntegerToReal = knownWords.containsKey("integerToReal");
        boolean doWeHaveRealToInteger = knownWords.containsKey("realToInteger");
        boolean doWeHaveVariables = knownWords.containsKey("variables");
        boolean doWeHaveConstants = knownWords.containsKey("constants");
        boolean doWeHaveDefine = knownWords.containsKey("define");
        boolean doWeHaveIf = knownWords.containsKey("if");
        boolean doWeHaveThen = knownWords.containsKey("then");
        boolean doWeHaveElsif = knownWords.containsKey("elsif");
        boolean doWeHaveElse = knownWords.containsKey("else");
        boolean doWeHaveWhile = knownWords.containsKey("while");
        boolean doWeHaveRepeat = knownWords.containsKey("repeat");
        boolean doWeHaveFrom = knownWords.containsKey("from");
        boolean doWeHaveVar = knownWords.containsKey("var");
        boolean doWeHaveArray = knownWords.containsKey("array");
        boolean doWeHaveOf = knownWords.containsKey("of");
        boolean doWeHaveString = knownWords.containsKey("string");
        boolean doWeHaveReal = knownWords.containsKey("real");
        boolean doWeHaveCharacter = knownWords.containsKey("character");
        boolean doWeHaveBoolean = knownWords.containsKey("boolean");
        boolean doWeHaveTo = knownWords.containsKey("to");
        boolean doWeHaveReadonly = knownWords.containsKey("readonly");
        boolean doWeHaveAlsoreadonly = knownWords.containsKey("alsoreadonly");
        boolean doWeHaveUntil = knownWords.containsKey("until");

        tokeType startToken = knownWords.get("start");
        tokeType endToken = knownWords.get("end");
        tokeType forToken = knownWords.get("for");
        tokeType writeToken = knownWords.get("write");
        tokeType readToken = knownWords.get("read");
        tokeType leftToken = knownWords.get("left");
        tokeType rightToken = knownWords.get("right");
        tokeType substringToken = knownWords.get("substring");
        tokeType squareRootToken = knownWords.get("squareRoot");
        tokeType integerToRealToken = knownWords.get("integerToReal");
        tokeType realToIntegerToken = knownWords.get("realToInteger");
        tokeType variablesToken = knownWords.get("variables");
        tokeType constantsToken = knownWords.get("constants");
        tokeType defineToken = knownWords.get("define");
        tokeType ifToken = knownWords.get("if");
        tokeType thenToken = knownWords.get("then");
        tokeType elsifToken = knownWords.get("elsif");
        tokeType elseToken = knownWords.get("else");
        tokeType whileToken = knownWords.get("while");
        tokeType repeatToken = knownWords.get("repeat");
        tokeType fromToken = knownWords.get("from");
        tokeType varToken = knownWords.get("var");
        tokeType arrayToken = knownWords.get("array");
        tokeType ofToken = knownWords.get("of");
        tokeType stringToken = knownWords.get("string");
        tokeType realToken = knownWords.get("real");
        tokeType characterToken = knownWords.get("character");
        tokeType booleanToken = knownWords.get("boolean");
        tokeType toToken = knownWords.get("to");
        tokeType readonlyToken = knownWords.get("readonly");
        tokeType alsoreadonlyToken = knownWords.get("alsoreadonly");
        tokeType untilToken = knownWords.get("until");

        for (String line : lines) {
            Lex(line);
        }
    }

    private String line = "";
    private int ST = 0;
    private int currentIndentationLevel = 0;

    private int commentsOn = 0;


    /**
     * This method will take in the input string and break it down into tokens.
     * It will then return the tokens in an array list.
     *
     * @param content This is the input string. It will be broken down into tokens.
     * @throws Exception This will throw an exception if the input string is invalid.
     */
    public void Lex(String content) throws Exception {
        char d = content.charAt(0);
        int spaces = 0;
        int tabs = 0;
        char state = 't';

        char character = 'c';
        for (int i = 0; i < content.length(); i++) {
            character = content.charAt(i);

            if (commentsOn == 1) {
                ST = 6;
            }
            if (content.charAt(0) != ' ' && ST == 0) {
                currentIndentationLevel = 0;
                ST = 1;
            }
            if (ST == 0) {
                while (character == ' ') {
                    spaces++;
                    i++;
                    character = content.charAt(i);
                }
                ST = 1;
                //indent
                if (spaces > currentIndentationLevel) {
                    token.add(new Token(Token.tokenType.INDENTATION, Integer.toString(spaces)));
                    currentIndentationLevel = spaces;
                }
                //dedent
                else if (spaces < currentIndentationLevel) {
                    token.add(new Token(Token.tokenType.DEDENTATION, Integer.toString(currentIndentationLevel - spaces)));
                    currentIndentationLevel = spaces;
                }
                i--;
            } else {
                switch (ST) {
                    //This case will check for the first character of the input string.
                    case 1:
                        if (Character.isLetter(character)) {
                            line += character;
                            ST = 2;
                            state = 'c';
                        } else if (character == '.') {
                            line += character;
                            ST = 3;
                            state = 'n';
                        } else if (Character.isDigit(character)) {
                            line += character;
                            ST = 4;
                            state = 'n';
                        } else if (character == ' ') {
                            new Token(Token.tokenType.NONE, line);
                            line = "";
                            ST = 1;
                        } else if (character == '{') {
                            commentsOn = 1;
                            line = "";
                            ST = 6;
                        } else if (character == '(') {
                            new Token(Token.tokenType.LeftParenthesis, line);
                            line = "";
                            ST = 1;
                            state = 'a';
                        } else if (character == '+' || character == '-' || character == '*' || character == '/') {
                            token.add(new Token(Token.tokenType.MathOpNode, line));
                            line = "";
                            ST = 1;
                            line += character;
                            if (character == '+') {
                                token.add(new Token(Token.tokenType.PLUS, line));
                                line = "";
                                ST = 1;
                            } else if (character == '-') {
                                token.add(new Token(Token.tokenType.MINUS, line));
                                line = "";
                                ST = 1;
                                state = 'a';
                            } else if (character == '*') {
                                token.add(new Token(Token.tokenType.MULTIPLY, line));
                                line = "";
                                ST = 1;
                                state = 'a';
                            } else if (character == '/') {
                                token.add(new Token(Token.tokenType.DIVIDE, line));
                                line = "";
                                ST = 1;
                                state = 'a';
                            } else {
                                new Token(Token.tokenType.NONE, line);
                                line = "";
                                ST = 1;
                            }
                        }
                        break;
                    //This case will check for the second character of the input string.
                    case 2:
                        state = 'c';
                        if (Character.isLetterOrDigit(character)) {
                            line += character;
                            ST = 2;
                        } else if (character == ' ') {
                            token.add(new Token(Token.tokenType.IDENTIFIER, line));
                            line = "";
                            ST = 1;
                        } else if (character == '}') {
                            state = 'y';
                            line = "";
                            token.add(new Token(Token.tokenType.COMMENT, line));
                            ST = 1;
                        } else {
                            new Token(Token.tokenType.NONE, line);
                            line = "";
                            ST = 1;
                        }
                        break;
                    //This case will check for the third character of the input string.
                    case 3:
                        state = 'n';
                        if (Character.isDigit(character)) {
                            line += character;
                            ST = 3;
                        } else if (character == ' ') {
                            token.add(new Token(Token.tokenType.NUMBER, line));
                            line = "";
                            ST = 1;
                        } else {
                            new Token(Token.tokenType.NONE, line);
                            line = "";
                            ST = 1;
                        }
                        break;
                    //This case will check for the fourth character of the input string.
                    case 4:
                        state = 'n';
                        if (Character.isDigit(character)) {
                            line += character;
                            ST = 4;
                        } else if (character == '.') {
                            line += character;
                            ST = 3;
                        } else if (character == ' ') {
                            token.add(new Token(Token.tokenType.NUMBER, line));
                            line = "";
                            ST = 1;
                        } else {
                            new Token(Token.tokenType.NONE, line);
                            line = "";
                            ST = 1;
                        }
                        break;
                    //This case will check for the fifth character of the input string.
                    case 5:
                        if (character == '\"') {
                            token.add(new Token(Token.tokenType.STRINGLITERAL, line));
                            line = "";
                            ST = 5;
                        } else if (character == '\'') {
                            token.add(new Token(Token.tokenType.CHARACTERLITERAL, line));
                            line = "";
                            ST = 5;
                        } else if (character == '+' || character == '-' || character == '*' || character == '/') {
                            token.add(new Token(Token.tokenType.MathOpNode, line));
                            line = "";
                            ST = 1;
                            line += character;
                            if (character == '+') {
                                token.add(new Token(Token.tokenType.PLUS, line));
                                line = "";
                                ST = 1;
                            } else if (character == '-') {
                                token.add(new Token(Token.tokenType.MINUS, line));
                                line = "";
                                ST = 1;
                                state = 'a';
                            } else if (character == '*') {
                                token.add(new Token(Token.tokenType.MULTIPLY, line));
                                line = "";
                                ST = 1;
                                state = 'a';
                            } else if (character == '/') {
                                token.add(new Token(Token.tokenType.DIVIDE, line));
                                line = "";
                                ST = 1;
                                state = 'a';
                            }
                        } else {
                            line += character;
                            ST = 5;
                        }
                        break;

                    case 6:
                        state = 'y';
                        if (character == '}') {
                            commentsOn = 0;
                            token.add(new Token(Token.tokenType.COMMENT, line));
                            line = "";
                            state = 't';
                            ST = 1;
                        } else if (character == ')'){
                            token.add(new Token(Token.tokenType.RightParenthesis, line));
                            line = "";
                            ST = 1;
                            state = 'a';
                        }
                        else {
                            line += character;
                            ST = 6;
                        }
                        break;
                    default:
                        throw new Exception("Invalid expression.");
                }
            }
        }

        /**
         * This if statement will check if the line is empty.
         * If it is not empty it will add the token to the array list.
         */
        if (line != "") {
            if (state == 'c') {
                token.add(new Token(Token.tokenType.IDENTIFIER, line));
            } else if (state == 'n') {
                token.add(new Token(Token.tokenType.NUMBER, line));
            } else if (state == 'y') {
                line = "";
            } else if (state == 'a') {
                token.add(new Token(Token.tokenType.STRINGLITERAL, line));
                token.add(new Token(Token.tokenType.CHARACTERLITERAL, line));
                token.add(new Token(Token.tokenType.PLUS, line));
                token.add(new Token(Token.tokenType.MINUS, line));
                token.add(new Token(Token.tokenType.MULTIPLY, line));
                token.add(new Token(Token.tokenType.DIVIDE, line));
                token.add(new Token(Token.tokenType.LeftParenthesis, line));
                token.add(new Token(Token.tokenType.RightParenthesis, line));
            } else {
                token.add(new Token(Token.tokenType.NONE, line));
            }
            line = "";
            ST = 0;
        }
        //This will add the end of line token to the array list.
        if (state != 'y') {
            token.add(new Token(Token.tokenType.ENDOFLINE, line));
        }
    }

    /**
     * This method will return the array list of tokens.
     *
     * @return token This is the array list of tokens.
     */
    public ArrayList<Token> getTokens() {
        return token;
    }
}
