package net.rptools.mtscript.ast;

import static java.util.Objects.requireNonNull;

public class AssignmentNode implements ExpressionNode {

  private final VariableNode variable;
  private final ExpressionNode expression;

  public AssignmentNode(VariableNode variable, ExpressionNode expression) {
    this.variable = requireNonNull(variable, "variable");
    this.expression = requireNonNull(expression, "expression");
  }

  public VariableNode getVariable() { return variable; }
  public ExpressionNode getExpression() { return expression; }
}
