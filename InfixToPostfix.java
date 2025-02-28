
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class InfixToPostfix {
    
    private static boolean isOperator(char ch) {
        return "+-*/^".indexOf(ch) != -1;
    }
    
    private static int getPrecedence(char op) {
        switch (op) {
            case '+': case '-': return 1;
            case '*': case '/': return 2;
            case '^': return 3;
            default: return -1;
        }
    }
    
    private static boolean isValidExpression(String expr) {
        int balance = 0;
        char prev = ' ';
        
        for (char ch : expr.toCharArray()) {
            if (ch == '(') balance++;
            else if (ch == ')') balance--;
            
            if (balance < 0) return false;
            if (isOperator(ch) && isOperator(prev)) return false;
            if (!Character.isLetterOrDigit(ch) && !isOperator(ch) && ch != '(' && ch != ')' && ch != ' ') return false;
            
            prev = ch;
        }
        
        return balance == 0;
    }
    
    private static String convertToPostfix(String infix) {
        Stack<Character> stack = new Stack<>();
        StringBuilder postfix = new StringBuilder();
        
        for (char ch : infix.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                postfix.append(ch);
            } else if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                stack.pop();
            } else if (isOperator(ch)) {
                while (!stack.isEmpty() && getPrecedence(ch) <= getPrecedence(stack.peek())) {
                    postfix.append(stack.pop());
                }
                stack.push(ch);
            }
        }
        
        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }
        
        return postfix.toString();
    }
    
    public static void main(String[] args) {
        String filePath = (args.length > 0) ? args[0] : "/Users/lilaumkubp/Downloads/input1.csv";
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Infix: " + line);
                if (isValidExpression(line)) {
                    System.out.println("Valid Expression");
                    System.out.println("Postfix: " + convertToPostfix(line));
                } else {
                    System.out.println("Invalid Expression");
                }
            }
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }
}
