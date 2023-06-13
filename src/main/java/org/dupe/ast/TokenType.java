package org.dupe.ast;

public enum TokenType {
    LITERAL, // Only supports Longs for now
    BUILTIN_FN, // Plan to implement a symbol table for built-in functions
    OPEN_PAREN,
    PLUS,
    MINUS;

    public static TokenType from(String token) {
        switch(token) {
            case "+":
                return PLUS;
            case "-":
                return MINUS;
            case "(":
                return OPEN_PAREN;
            default:
                try {
                    Long.parseLong(token);
                    return TokenType.LITERAL;
                } catch (NumberFormatException e) {
                    // If it's not a number, assume the token is a reference
                    // to a built-in function
                    return BUILTIN_FN;
                }
        }
    }

    public static long eval(TokenType type, long left, long right) {
        switch(type) {
            case PLUS:
                return left + right;
            case MINUS:
                return left - right;
            default:
                throw new IllegalArgumentException(
                        String.format("Cannot evaluate token type: %s", type)
                );
        }
    }
}
