package org.dupe;

import java.util.List;

import org.dupe.ast.ASTNode;
import org.dupe.interpreter.Interpreter;
import org.dupe.parser.LispParser;

public final class Main {

  public static void main(String[] args) {
    var testExprs = List.of(
            "1",                                    // 1
            "(+ 2 3)",                              // 5
            "(- 3 4)",                              // -1
            "(+ (+ 2 3) 4)",                        // 9
            "(+ (+ 4 5) (- 10 3))",                 // 16
            "(max 2 (+ 3 4))",                      // 7
            "(max (+ (+ 4 5) (+ 1 1)) (- 10 3))"    // 11
    );
    testExprs.forEach(Main::parseTestExpr);
  }

  private static void parseTestExpr(String expr) {
    //var output = String.format(
            //"Parsing %s...\n %s",
            //expr,
            //LispParser.parse(expr)
    //);
    //System.out.println(output);
    //System.out.println();
    ASTNode tree = LispParser.parse(expr);
    Long result = Interpreter.eval(tree);
    System.out.println(result);
  }
}

