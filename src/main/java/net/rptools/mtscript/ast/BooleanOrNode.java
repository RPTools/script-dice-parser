package net.rptools.mtscript.ast;

/**
 * Abstract syntax tree node that represents a boolean or operation.
 */
public class BooleanOrNode extends BinaryOperatorNode implements BooleanExpressionNode {

  /**
   * Creates the AST node for a boolean or operation.
   *
   * @param left The value on the left hand side of the or.
   * @param right The value on the right hand side of the or.
   */
  public BooleanOrNode(ASTNode left, ASTNode right) {
    super(left, right);
  }

}
