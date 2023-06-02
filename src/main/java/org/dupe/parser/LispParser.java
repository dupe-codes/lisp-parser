package org.dupe.parser;

import java.util.Stack;

import org.dupe.ast.ASTNode;
import org.dupe.ast.TokenType;

public final class LispParser {

    private static final String OPEN_EXPR = "(";
    private static final String CLOSE_EXPR = ")";

    public static ASTNode parse(String expr) {
        // TODO: Add error handling for invalid expressions
        var tokens = tokenize(expr);

        Stack<ASTNode> nodes = new Stack<>();
        for (String token : tokens) {
            if (token.equals(OPEN_EXPR)) {
                continue;
            }

            // TODO: Change this to handle unary or multi argument operators
            // IDEA: have a token type for open parens, when closed paren is seen
            // pop off the stack until the open paren is seen, the node right before
            // the open paren is the root node, and all the other nodes popped
            // off are the children
            if (token.equals(CLOSE_EXPR)) {
                ASTNode right = nodes.pop();
                ASTNode left = nodes.pop();
                ASTNode root = nodes.pop();
                nodes.push(root.withSubExprs(left, right));
                continue;
            }

            var type = TokenType.from(token);
            if (type == TokenType.LITERAL) {
                nodes.push(ASTNode.literal(Long.parseLong(token)));
            } else if (type == TokenType.BUILTIN_FN) {
                nodes.push(ASTNode.builtinFn(token, null, null));
            } else {
                nodes.push(ASTNode.operator(type, null, null));
            }
        }

        return nodes.pop();
   }

   private static String[] tokenize(String expr) {
        // Expand parentheses with spaces so they can be split as their own tokens
        var parenExpanded = expr
            .replace("(", " ( ")
            .replace(")", " ) ");
        var trimmed = parenExpanded.strip();
        return trimmed.split("\\s+");
   }
}
