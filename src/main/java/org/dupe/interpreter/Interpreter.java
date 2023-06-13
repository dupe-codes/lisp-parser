package org.dupe.interpreter;

import org.dupe.ast.ASTNode;
import org.dupe.ast.TokenType;

public class Interpreter {
    public static Long eval(ASTNode node){
        if (node.isLiteral()) {
            return node.getValue().get();
        }
        long left = eval(node.getLeftSubExpr().get());
        long right = eval(node.getRightSubExpr().get());
        return TokenType.eval(node.getType(),left, right); 
    }
}
