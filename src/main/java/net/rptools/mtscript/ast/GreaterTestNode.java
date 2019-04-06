package net.rptools.mtscript.ast;

/**
 * Abstract syntax tree node that represents a greater than test.
 */
public class GreaterTestNode extends BinaryOperatorNode implements BooleanExpressionNode {

  /**
   * Creates the AST node for a greater than test.
   *
   * @param left The value on the left hand side of the comparison.
   * @param right The value on the right hand side of the comparison.
   */
  public GreaterTestNode(ASTNode left, ASTNode right) {
    super(left, right);
  }

}
