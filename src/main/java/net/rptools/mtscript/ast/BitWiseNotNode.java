package net.rptools.mtscript.ast;

/**
 * Represents an abstract tree node for bit wise negation.
 */
public class BitWiseNotNode implements BooleanExpressionNode {

  private final ASTNode child;

  /**
   * Creates AST node to represent bit wise negation.
   * @param childValue the value to negate.
   */
  public BitWiseNotNode(ASTNode childValue) {
    child = childValue;
  }
}
