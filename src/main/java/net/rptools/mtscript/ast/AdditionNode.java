package net.rptools.mtscript.ast;

/**
 * Abstract syntax tree node that represents addition of two values.
 */
public class AdditionNode extends BinaryOperatorNode {

  /**
   * Create a new AST node represents addition of two values.
   *
   * @param left The left hand side of the addition.
   * @param right The right hand side of the addition.
   */
  public AdditionNode(ASTNode left, ASTNode right) {
    super(left, right);
  }

}
