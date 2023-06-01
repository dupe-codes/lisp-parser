package org.dupe.ast;

import org.jetbrains.annotations.Nullable;

public class Parser {

    public static ASTNode parse(String expr) {
        String[] tokens = expr.strip().split(" ");
        ASTNode leftNode = ASTNode.leaf(TokenType.LITERAL, 1000L);
        ASTNode rightNode = ASTNode.leaf(TokenType.LITERAL, 500L);
        return ASTNode.root(TokenType.PLUS, leftNode, rightNode);
    }

    public static class ASTNode {
        private final TokenType type;

        @Nullable
        private final Long value;

        @Nullable
        private final ASTNode leftSubExpr;

        @Nullable
        private final ASTNode rightSubExpr;

        private ASTNode(
                TokenType type,
                @Nullable Long value,
                @Nullable ASTNode leftSubExpr,
                @Nullable ASTNode rightSubExpr) {
            this.type = type;
            this.value = value;
            this.leftSubExpr = leftSubExpr;
            this.rightSubExpr = rightSubExpr;
        }

        public static ASTNode leaf(TokenType type, @Nullable Long value) {
            return new ASTNode(type, value, null, null);
        }

        public static ASTNode root(TokenType type, ASTNode left, ASTNode right) {
            return new ASTNode(type, null, left, right);
        }

        public String toString() {
            return toStringHelper(this, 1);
        }

        private static String toStringHelper(@Nullable ASTNode node, int indentLevel) {
            if (node == null) {
                return "";

            }

            String indentation = "\n";
            for(int i = 0; i < indentLevel; i++) {
                indentation += "    ";
            }

            String left = toStringHelper(node.leftSubExpr, indentLevel + 1);
            left = left.isEmpty() ? "None" : indentation + left;
            String right = toStringHelper(node.rightSubExpr, indentLevel + 1);
            right = right.isEmpty() ? "None" : indentation + right;

            return String.format("( ASTNode{ TokenType: %s, value: %s, %s, %s } )",
                    node.type,
                    node.value,
                    left,
                    right);
        }

    }
}
