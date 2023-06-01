package org.dupe;

import org.dupe.ast.Parser;

public class Main {
  public static void main(String[] args) {
    String testExpr = "(+ 2 3)";
    System.out.println(Parser.parse(testExpr));
  }
}

