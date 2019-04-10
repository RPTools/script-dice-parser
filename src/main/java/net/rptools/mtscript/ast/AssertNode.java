package net.rptools.mtscript.ast;


public class AssertNode implements ASTNode {

  private final ASTNode conditionalNode;
  private final ASTNode valueNode;

  /**
   * Creates a new AssertNode with a provided conditional and optional
   * value nodes.
   *
   * @param conditionalNode The conditional node.
   * @param valueNode The optional value node.
   */
  AssertNode(ASTNode conditionalNode, ASTNode valueNode) {
    this.conditionalNode = conditionalNode;
    this.valueNode = valueNode;
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
   * Returns the value node.
   *
   * @return the value node or null.
   */
  public ASTNode getValueNode() {
    return valueNode;
  }
}
