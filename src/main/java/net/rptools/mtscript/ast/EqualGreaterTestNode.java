package net.rptools.mtscript.ast;

/**
 * Abstract syntax tree node that represents an equal or greater than test.
 */
public class EqualGreaterTestNode extends BinaryOperatorNode implements BooleanExpressionNode {

  /**
   * Creates the AST node for an equal or greater than test.
   *
   * @param left The value on the left hand side of the comparison.
   * @param right The value on the right hand side of the comparison.
   */
  public EqualGreaterTestNode(ASTNode left, ASTNode right) {
    super(left, right);
  }

}
