package org.dupe.ast;

import org.jetbrains.annotations.Nullable;

public class ASTNode {

    private final TokenType type;

    /**
     * Holds the value of literal expressions, null for non-literal expressions
     */
    @Nullable
    private final Long value;

    /**
     * Holds the name of built-in functions, null for all other expressions
     */
    @Nullable
    private final String fnName;

    // TODO: Assumes binary operators/functions. Expand to handle unary and multi-arg
    @Nullable
    private final ASTNode leftSubExpr;
    @Nullable
    private final ASTNode rightSubExpr;

    private ASTNode(
            TokenType type,
            @Nullable Long value,
            @Nullable String fnName,
            @Nullable ASTNode leftSubExpr,
            @Nullable ASTNode rightSubExpr) {
        this.type = type;
        this.value = value;
        this.fnName = fnName;
        this.leftSubExpr = leftSubExpr;
        this.rightSubExpr = rightSubExpr;
    }

    public static ASTNode literal(@Nullable Long value) {
        return new ASTNode(TokenType.LITERAL, value, null, null, null);
    }

    public static ASTNode operator(TokenType type, ASTNode left, ASTNode right) {
        return new ASTNode(type, null, null, left, right);
    }

    public static ASTNode builtinFn(String fnName, ASTNode left, ASTNode right) {
        return new ASTNode(TokenType.BUILTIN_FN, null, fnName, left, right);
    }

    public TokenType getType() {
        return type;
    }

    public ASTNode withSubExprs(ASTNode left, ASTNode right) {
        return new ASTNode(type, value, fnName, left, right);
    }

    public String toString() {
        return toStringHelper(this, 1);
    }

    private static String toStringHelper(@Nullable ASTNode node, int indentLevel) {
        if (node == null) {
            return "";

        }

        String indentation = createIndentationString(indentLevel);
        String left = toStringHelper(node.leftSubExpr, indentLevel + 1);
        left = left.isEmpty() ? "None" : indentation + left;
        String right = toStringHelper(node.rightSubExpr, indentLevel + 1);
        right = right.isEmpty() ? "None" : indentation + right;

        return String.format("( ASTNode{ TokenType: %s, literal: %s, fn: %s, %s, %s } )",
                node.type,
                node.value,
                node.fnName,
                left,
                right);
    }

    private static String createIndentationString(int indentLevel) {
        String indentation = "\n";
        for(int i = 0; i < indentLevel; i++) {
            indentation += "    ";
        }
        return indentation;
    }
}

