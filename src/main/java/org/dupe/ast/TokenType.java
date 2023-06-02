package org.dupe.ast;

public enum TokenType {
    LITERAL,
    BUILTIN_FN,
    PLUS,
    MINUS;

    public static TokenType from(String token) {
        switch(token) {
            case "+":
                return PLUS;
            case "-":
                return MINUS;
            default:
                try {
                    Long.parseLong(token);
                    return TokenType.LITERAL;
                } catch (NumberFormatException e) {
                    // If it's not a number, assume the token is a reference
                    // to a built-in function.
                    return BUILTIN_FN;
                }
        }

    }
}
