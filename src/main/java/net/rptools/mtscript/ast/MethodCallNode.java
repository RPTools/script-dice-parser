package net.rptools.maptool.ast;

public class MethodCallNode {

  private final String identifier;
  private final List<ASTNode> parameters;

  MethodCallNode(String identifier, List<ASTNode> parameters) {
    this.identifier = identifier;
    this.parameters = parameters;
  }

  public String getIdentifier() {
    return identifier;
  }

  public List<ASTNode> getParameters() {
    return parameters;
  }
}
