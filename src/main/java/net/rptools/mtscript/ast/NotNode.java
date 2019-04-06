package net.rptools.mtscript.ast;

/**
 * Represents an abstract tree node for boolean negation.
 */
public class NotNode implements BooleanExpressionNode {

  private final ASTNode child;

  /**
   * Creates AST node to represent boolean negation.
   * @param childValue the value to negate.
   */
  public NotNode(ASTNode childValue) {
    child = childValue;
  }
}
