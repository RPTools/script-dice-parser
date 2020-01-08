package net.rptools.mtscript.ast;

import static java.util.Objects.requireNonNull;

import java.util.List;

public class ScriptNode implements ASTNode {

  private final List<ASTNode> children;

  public ScriptNode(List<ASTNode> children) {
    this.children = requireNonNull(children, "Missing list of children");
    children.forEach(c -> requireNonNull(c, "Child was null!"));
  }

  public List<ASTNode> getChildren() {
    return children;
  }
}
