package net.rptools.mtscript.ast;

public class IfNode implements ASTNode {

  private final List<ConditionalPair> conditionalPairs;
  private final ASTNode elseNode;

  /**
   * Creates a new AST Node that represents an if statement.
   *
   * Takes a list of {@link ConditionalPair} objects containing each
   * section of the 'if' 'else if' structure.
   *
   * @param conditionalPairs A list of {@link ConditionalPair} objects.
   * @param elseNode The optional body to execute if condition is false.
   */
  IfNode(List<ConditionalPair> conditionalPairs, ASTNode elseNode) {
    this.conditionalPairs = conditionalPairs;
    this.elseNode = elseNode;
  }

  /**
   * Returns the list of {@link ConditionalPair}s.
   *
   * @return the list of {@link ConditionalPair}s
   */
  public List<ConditionalPair> getConditionalPairs() {
    return conditionalPairs;
  }

  /**
   * Returns the else node.
   * 
   * @return the else node.
   */
  public ASTNode getElseNode() {
    return elseNode;
  }

  /**
   * Class to represent a pair of a conditional with a block.
   */
  public static class ConditionPair {
    private final ASTNode conditionalNode;
    private final ASTNode blockNode;

    public ConditionalPair(ASTNode conditionalNode, ASTNode blockNode) {
      this.conditionalNode = conditionalNode;
      this.blockNode = blockNode;
    }

    public ASTNode getConditionalNode() {
      return conditionalNode;
    }

    public ASTNode getBlockNode() {
      return blockNode;
    }
  }
}
