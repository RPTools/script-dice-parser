package net.rptools.mtscript.ast;

/**
 * Abstract syntax tree node that represents a bit wise exclusive or operation.
 */
public class BitWiseXorNode extends BinaryOperatorNode implements BooleanExpressionNode {

  /**
   * Creates the AST node for a bit wise or operation.
   *
   * @param left The value on the left hand side of the exclusive or.
   * @param right The value on the right hand side of the exclusive or.
   */
  public BitWiseXorNode(ASTNode left, ASTNode right) {
    super(left, right);
  }

}
