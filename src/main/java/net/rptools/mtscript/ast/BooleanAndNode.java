package net.rptools.mtscript.ast;

/**
 * Abstract syntax tree node that represents a boolean and operation.
 */
public class BooleanAndNode extends BinaryOperatorNode implements BooleanExpressionNode {

  /**
   * Creates the AST node for a boolean and operation.
   *
   * @param left The value on the left hand side of the or.
   * @param right The value on the right hand side of the or.
   */
  public BooleanAndNode(ASTNode left, ASTNode right) {
    super(left, right);
  }

}
