package net.rptools.mtscript.ast;

import static java.util.Objects.requireNonNull;
import java.util.List;

public class InlineRollNode implements ASTNode {

  private final List<DiceExprNode> rolls;

  public InlineRollNode(List<DiceExprNode> rolls) {
    this.rolls = requireNonNull(rolls, "rolls");
    rolls.forEach(r -> requireNonNull(r));
  }

  public List<DiceExprNode> getRolls() {
    return rolls;
  }
}
