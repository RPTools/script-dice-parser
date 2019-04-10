package net.rptools.mtscript.ast;


public class WhileNode implements ASTNode {

  private final ASTNode conditionalNode;
  private final ASTNode bodyNode;

  /**
   * Creates a new AST Node that represents a while loop.
   *
   * @param conditionalNode The condtion for this if statement.
   * @param bodyNode The body to execute if condition while true.
   */
  WhileNode(ASTNode conditionalNode, ASTNode bodyNode) {
    this.conditionNode = conditionalNode; // TODO Should we requireNonNull here?
    this.bodyNode = bodyNode;
  }

  /**
   * Returns the conditional node.
   *
   * @return the conditional node.
   */
  public ASTNode getConditionalNode() {
    return conditionalNode;
  }

  /**
   * Returns the body node.
   *
   * @return the body node.
   */
  public ASTNode getBodyNode() {
    return bodyNode;
  }
}
