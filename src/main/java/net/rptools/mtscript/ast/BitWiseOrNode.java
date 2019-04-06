package net.rptools.mtscript.ast;

/**
 * Abstract syntax tree node that represents a bit wise or operation.
 */
public class BitWiseOrNode extends BinaryOperatorNode implements BooleanExpressionNode {

  /**
   * Creates the AST node for a bit wise or operation.
   *
   * @param left The value on the left hand side of the or.
   * @param right The value on the right hand side of the or.
   */
  public BitWiseOrNode(ASTNode left, ASTNode right) {
    super(left, right);
  }

}
