package net.rptools.mtscript.ast;

/**
 * Abstract syntax tree node that represents multiplication of two values.
 */
public class MultiplicationNode extends BinaryOperatorNode {

  /**
   * Create a new AST node represents the multiplication of two values.
   *
   * @param left the value to be multiplied.
   * @param right the value to multiply by.
   */
  public MultiplicationNode(ASTNode left, ASTNode right) {
    super(left, right);
  }

}
