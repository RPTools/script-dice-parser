package net.rptools.mtscript.ast;

/**
 * Abstract syntax tree node that represents division of one value by another.
 */
public class DivisionNode extends BinaryOperatorNode {

  /**
   * Create a new AST node represents addition of two values.
   *
   * @param left The dividend of the operation.
   * @param right The divisor of the operation.
   */
  public DivisionNode(ASTNode left, ASTNode right) {
    super(left, right);
  }

}
