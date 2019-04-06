package net.rptools.mtscript.ast;

/**
 * Abstract syntax tree node that represents a non equality test.
 */
public class NonEqualityTestNode extends BinaryOperatorNode implements BooleanExpressionNode {

  /**
   * Creates the AST node for non equality test.
   *
   * @param left The value on the left hand side of the comparison.
   * @param right The value on the right hand side of the comparison.
   */
  public NonEqualityTestNode(ASTNode left, ASTNode right) {
    super(left, right);
  }

}
