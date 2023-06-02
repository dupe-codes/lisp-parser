package org.dupe;

import org.dupe.ast.LispParser;

import java.util.List;

public class Main {
  public static void main(String[] args) {
    List<String> testExprs = List.of(
            "(+ 2 3)",
            "(- 3 4)",
            "(+ (+ 2 3) 4)",
            "(+ (+ 4 5) (- 10 3))",
            "(max 2 (+ 3 4))",
            "(max (+ (+ 4 5) (+ 1 1)) (- 10 3))"
    );

    testExprs.stream().forEach(Main::parseTestExpr);
  }

  private static void parseTestExpr(String expr) {
    String output = String.format(
            "Parsing %s...\n %s",
            expr,
            LispParser.parse(expr)
    );
    System.out.println(output);
    System.out.println();
  }
}

