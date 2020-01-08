package net.rptools.mtscript.ast;

import static java.util.Objects.requireNonNull;

import java.util.List;

public class  ArrayInitializerNode implements VariableInitializerNode {

  private final List<VariableInitializerNode> nodes;

  public ArrayInitializerNode(List<VariableInitializerNode> nodes) {
    this.nodes = requireNonNull(nodes, "Passed null for node list");
  }

  public List<VariableInitializerNode> getNodes() {
    return nodes;
  }
}
