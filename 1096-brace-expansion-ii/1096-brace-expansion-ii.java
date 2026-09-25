import java.util.*;

class Solution {
    public List<String> braceExpansionII(String expression) {
        Stack<Character> opStack = new Stack<>();
        Stack<Set<String>> valStack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            // Handle implicit concatenation operator
            if (i > 0) {
                char prev = expression.charAt(i - 1);
                if ((Character.isLetter(prev) || prev == '}') && (Character.isLetter(c) || c == '{')) {
                    // Implicit concatenation has higher precedence than ','
                    while (!opStack.isEmpty() && opStack.peek() == '.') {
                        evaluate(opStack, valStack);
                    }
                    opStack.push('.'); // Use '.' to represent concatenation
                }
            }

            if (Character.isLetter(c)) {
                Set<String> set = new HashSet<>();
                set.add(String.valueOf(c));
                valStack.push(set);
            } else if (c == '{') {
                opStack.push('{');
            } else if (c == ',') {
                // Evaluate all concatenation operations first
                while (!opStack.isEmpty() && opStack.peek() != '{') {
                    evaluate(opStack, valStack);
                }
                opStack.push(',');
            } else if (c == '}') {
                // Evaluate inside the brace until '{'
                while (!opStack.isEmpty() && opStack.peek() != '{') {
                    evaluate(opStack, valStack);
                }
                opStack.pop(); // Pop '{'
            }
        }

        // Evaluate any remaining operators
        while (!opStack.isEmpty()) {
            evaluate(opStack, valStack);
        }

        // Convert the final set into a sorted list
        List<String> result = new ArrayList<>(valStack.pop());
        Collections.sort(result);
        return result;
    }

    private void evaluate(Stack<Character> opStack, Stack<Set<String>> valStack) {
        char op = opStack.pop();
        Set<String> set2 = valStack.pop();
        Set<String> set1 = valStack.pop();
        Set<String> res = new HashSet<>();

        if (op == '.') { // Concatenation
            for (String s1 : set1) {
                for (String s2 : set2) {
                    res.add(s1 + s2);
                }
            }
        } else if (op == ',') { // Union
            res.addAll(set1);
            res.addAll(set2);
        }

        valStack.push(res);
    }
}