package net.rptools.mtscript.ast;

/**
 * Abstract syntax tree node that represents a bit wise and operation.
 */
public class BitWiseAndNode extends BinaryOperatorNode implements BooleanExpressionNode {

  /**
   * Creates the AST node for a bit wise or operation.
   *
   * @param left The value on the left hand side of the and.
   * @param right The value on the right hand side of the and.
   */
  public BitWiseAndNode(ASTNode left, ASTNode right) {
    super(left, right);
  }

}
