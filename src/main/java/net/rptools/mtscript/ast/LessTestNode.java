package net.rptools.mtscript.ast;

/**
 * Abstract syntax tree node that represents a less than test.
 */
public class LessTestNode extends BinaryOperatorNode implements BooleanExpressionNode {

  /**
   * Creates the AST node for a less than test.
   *
   * @param left The value on the left hand side of the comparison.
   * @param right The value on the right hand side of the comparison.
   */
  public LessTestNode(ASTNode left, ASTNode right) {
    super(left, right);
  }

}
