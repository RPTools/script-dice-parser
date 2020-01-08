package net.rptools.mtscript.ast;

import static java.util.Objects.requireNonNull;

public class VariableNode implements ASTNode {
  public static enum Scope {
    LOCAL,
    GLOBAL,
    PROPERTY,
  };

  private final Scope scope;
  private final String identifier;

  public static VariableNode fromName(String name) {
    requireNonNull(name);
    if (name.startsWith("$$")) {
      return new VariableNode(Scope.GLOBAL, name.substring(2));
    } else if (name.startsWith("$")) {
      return new VariableNode(Scope.LOCAL, name.substring(1));
    } else if (name.startsWith("@")) {
      return new VariableNode(Scope.PROPERTY, name.substring(1));
    } else {
      throw new IllegalArgumentException("Name does not fit a proper variable name pattern");
    }
  }

  public VariableNode(Scope scope, String identifier) {
    this.scope = requireNonNull(scope, "scope");
    this.identifier = requireNonNull(identifier, "identifier");
  }

  public Scope getScope() { return scope; }
  public String getIdentifier() { return identifier; }
}
