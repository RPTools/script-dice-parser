package net.rptools.mtscript.ast;

import static java.util.Objects.requireNonNull;

public class VariableDeclaratorNode implements ASTNode {

  private final VariableNode variable;
  private final VariableInitializerNode initializer;

  public VariableDeclaratorNode(VariableNode variable, VariableInitializerNode initializer) {
    this.variable = requireNonNull(variable, "Passed a null variable node");
    this.initializer = initializer;
  }

  public VariableNode getVariable() { return variable; }
  public VariableInitializerNode getInitializer() { return initializer; }
}
