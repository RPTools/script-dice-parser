package net.rptools.mtscript.ast;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

public class VariableDeclaratorIdNode implements ASTNode {

    private final VariableNode variable;
    private final int arrayDepth;

    public VariableDeclaratorIdNode(VariableNode variable, int arrayDepth) {
        this.variable = requireNonNull(variable, "Passed a null variable node");
        this.arrayDepth = arrayDepth;
    }

    public VariableNode getVariable() { return variable; }
    public int getArrayDepth() { return arrayDepth; }
    public boolean isArray() { return arrayDepth > 0; }

    public boolean equals(Object o) {
      if (this == o) {
        return true;
      } else if (o == null) {
        return false;
      }

      if (!(o instanceof VariableDeclaratorIdNode)) {
        return false;
      }

      VariableDeclaratorIdNode n = (VariableDeclaratorIdNode) o;

      return Objects.equals(variable, n.variable);
    }

    public int hashCode() {
      return Objects.hash(64243254, variable);
    }
}
