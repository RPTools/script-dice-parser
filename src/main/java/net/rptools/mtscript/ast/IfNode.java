/*
 * This software Copyright by the RPTools.net development team, and
 * licensed under the Affero GPL Version 3 or, at your option, any later
 * version.
 *
 * RPTools Source Code is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * You should have received a copy of the GNU Affero General Public
 * License * along with this source Code.  If not, please visit
 * <http://www.gnu.org/licenses/> and specifically the Affero license
 * text at <http://www.gnu.org/licenses/agpl.html>.
 */
package net.rptools.mtscript.ast;

import java.util.List;

public class IfNode implements StatementNode {

  private final List<ConditionalPair> conditionalPairs;
  private final ASTNode elseNode;

  /**
   * Creates a new AST Node that represents an if statement.
   *
   * <p>Takes a list of {@link ConditionalPair} objects containing each section of the 'if' 'else
   * if' structure.
   *
   * @param conditionalPairs A list of {@link ConditionalPair} objects.
   * @param elseNode The optional body to execute if condition is false.
   */
  public IfNode(List<ConditionalPair> conditionalPairs, ASTNode elseNode) {
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

  /** Class to represent a pair of a conditional with a block. */
  public static class ConditionalPair {
    private final BooleanExpressionNode conditionalNode;
    private final ASTNode blockNode;

    public ConditionalPair(BooleanExpressionNode conditionalNode, ASTNode blockNode) {
      this.conditionalNode = conditionalNode;
      this.blockNode = blockNode;
    }

    public BooleanExpressionNode getConditionalNode() {
      return conditionalNode;
    }

    public ASTNode getBlockNode() {
      return blockNode;
    }
  }
}
