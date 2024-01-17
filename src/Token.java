/**
 * This class is used to represent a token in the language.
 */
public class Token {
    //This enum is used to represent the type of token.
    enum tokenType{
        IDENTIFIER,
        NUMBER,
        ENDOFLINE,
        NONE,
        STRINGLITERAL,
        CHARACTERLITERAL,
        COMMENT,
        PLUS,
        MINUS,
        MULTIPLY,
        DIVIDE,
        INDENTATION,
        DEDENTATION,
        LeftParenthesis,
        RightParenthesis,
        MathOpNode,
        FUNCTION,
        COMMA,
        LeftBracket,
        RightBracket,
        ASSIGNMENT,
        EQUAL,
        NOT_EQUAL,
        LESS_THAN,
        GREATER_THAN,
        LESS_THAN_OR_EQUAL,
        GREATER_THAN_OR_EQUAL,
        FOR,
        IN,
        TO,
        IF,
        WHILE,
        FROM,

    }

    //This is the type of token.
    private tokenType type;
    //This is the value of the token.
    private String value = "";

    /**
     * This is the constructor for the token class.
     * @param type This is the type of token.
     * @param value This is the value of the token.
     */
    public Token(tokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    /**
     * This method will return the type and value of the token.
     * @return This will return the type and value of the token.
     */
    public String toString() {
        return "Type: " + type + " Value: " + value;
    }

    /**
     * This method will return the type of the token.
     * @return This will return the type of the token.
     */
    public tokenType getType() {
        return type;
    }

    /**
     * This method will return the value of the token.
     * @return This will return the value of the token.
     */
    public String getValue() {
        return value;
    }
}
