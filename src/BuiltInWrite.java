import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BuiltInWrite extends FunctionNode{
    public BuiltInWrite() {
        super("write");
    }
    public void Execute(Map<String, IntegerDataType> Array, List<InterpreterDataType> args) {
        for (InterpreterDataType arg : args) {
            Scanner scanner = new Scanner(System.in);
            int wordCount = 0;
            while (scanner.hasNext()) {
                String input = scanner.nextLine();
                for (int i = 0; i < input.length(); i++) {
                    if (input.equals("exit")) {
                        Array.get(i).FromString(input);
                        wordCount++;
                    } else {
                        System.out.println("Invalid input");
                    }
                    System.exit(0);
                }
            }
            System.out.println(arg.toString());
        }
    }
}
