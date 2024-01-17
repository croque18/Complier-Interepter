import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * This is the main class that will run the lexer. It will read the file and print out the tokens.
 * It will also print out the tokens in the console.
 */
public class Shank {
    public static <parser> void main(String[] args) throws Exception {

        Path file = Paths.get("/Users/chelsearoque/IdeaProjects/Parser 4/src/Test.txt");
        List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);

        //This will print out the tokens in the console.
        Lexer obj = new Lexer(lines);
        for (int i = 0; i < obj.getTokens().size(); i++) {
            switch (obj.getTokens().get(i).getType()) {
                case IDENTIFIER:
                    System.out.print("IDENTIFIER(" + obj.getTokens().get(i).getValue() + ") ");
                    break;
                case NUMBER:
                    System.out.print("NUMBER(" + obj.getTokens().get(i).getValue() + ") ");
                    break;
                case ENDOFLINE:
                    System.out.println("ENDOFLINE()");
                    break;
                case NONE:
                    System.out.print("NONE(" + obj.getTokens().get(i).getValue() + ")");
                    break;
                case STRINGLITERAL:
                    System.out.print("STRINGLITERAL(" + obj.getTokens().get(i).getValue() + ") ");
                    break;
                case CHARACTERLITERAL:
                    System.out.print("CHARACTERLITERAL(" + obj.getTokens().get(i).getValue() + ") ");
                    break;
                case COMMENT:
                    System.out.print("COMMENT() ");
                    break;
                case PLUS:
                    System.out.print("PLUS(" + obj.getTokens().get(i).getValue() + ") ");
                    break;
                case MINUS:
                    System.out.print("MINUS(" + obj.getTokens().get(i).getValue() + ") ");
                    break;
                case MULTIPLY:
                    System.out.print("MULTIPLY(" + obj.getTokens().get(i).getValue() + ") ");
                    break;
                case DIVIDE:
                    System.out.print("DIVIDE(" + obj.getTokens().get(i).getValue() + ") ");
                    break;
                case INDENTATION:
                    System.out.print("INDENT(" + obj.getTokens().get(i).getValue() + ") ");
                    break;
                case DEDENTATION:
                    System.out.print("DEDENT(" + obj.getTokens().get(i).getValue() + ") ");
                    break;
                case LeftParenthesis:
                    System.out.print("LeftParenthesis(" + obj.getTokens().get(i).getValue() + ") ");
                    break;
                case RightParenthesis:
                    System.out.print("RightParenthesis(" + obj.getTokens().get(i).getValue() + ") ");
                    break;
            }

        }
        Parser parser = new Parser(obj.getTokens());
        parser = new Parser(obj.getTokens());

        parser.assignment();

        Interpreter interpreter = new Interpreter();
        interpreter.interpretFunction(parser.getProgramNode());
    }
}