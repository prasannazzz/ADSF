import java.util.*;
public class InfixToPostfix {
    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }
    private static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        return -1;
    }

    public static String infixToPostfix(String expression) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            // If the character is an operand, add it to the result
            if (Character.isLetterOrDigit(ch)) {
                result.append(ch);
            }
            // If the character is an operator
            else if (isOperator(ch)) {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(ch)) {//Most Important
                    result.append(stack.pop());
                }
                stack.push(ch);
            }
        }
        // Pop all the operators from the stack
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        return result.toString();//Important
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Infix to Postfix Converter ===");
            System.out.println("1. Convert Infix to Postfix");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter an infix expression: ");
                    String infixExpression = scanner.nextLine();
                    String postfixExpression = infixToPostfix(infixExpression);
                    System.out.println("Postfix Expression: " + postfixExpression);
                    break;
                case 2:
                    System.out.println("Exiting the program. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please enter 1 or 2.");
            }
        }
    }
}
