import java.util.Scanner;

public class BuiltInRead extends FunctionNode{
    public BuiltInRead() {
        super("read");
    }
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        int wordCount = 0;
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            for (int i = 0; i < input.length(); i++) {
                if (input.equals("exit")) {
                    arguments.get(i).toString();
                    wordCount++;
                } else {
                    System.out.println("Invalid input");
                }
                System.exit(0);
            }
        }
    }
}
