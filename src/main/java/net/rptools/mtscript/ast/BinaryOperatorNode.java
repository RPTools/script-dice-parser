package net.rptools.mtscript.ast;


/**
 * Node for abstract syntax tree which represents an operator with two arguments.
 */
abstract class BinaryOperatorNode implements ASTNode {
  private final ASTNode left;
  private final ASTNode right;

  /**
   * Creates a new AST node that represents an operation with two arguments.
   *
   * @param leftNode The argument to the left of the operator.
   * @param rightNode The argument to the right of the operator.
   */
  BinaryOperatorNode(ASTNode leftNode, ASTNode rightNode)  {
    left = leftNode;
    right= rightNode;
  }

  /**
   * Returns the left hand side of the operator.
   *
   *@return the left hand side of the operator.
   */
  public ASTNode getLeft() {
    return left;
  }

  /**
   * Returns the right hand side of the operator.
   *
   * @return the right hand side of the operator.
   */
  public ASTNode getRight() {
    return right;
  }

}
