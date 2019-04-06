package net.rptools.mtscript.ast;

/**
 * Abstract syntax tree node that represents an equality test.
 */
public class EqualityTestNode extends BinaryOperatorNode implements BooleanExpressionNode {

  /**
   * Creates the AST node for an equality test.
   *
   * @param left The value on the left hand side of the comparison.
   * @param right The value on the right hand side of the comparison.
   */
  public EqualityTestNode(ASTNode left, ASTNode right) {
    super(left, right);
  }

}
