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

import static java.util.Objects.requireNonNull;

public class WhileNode implements StatementNode {

  private final BooleanExpressionNode conditionalNode;
  private final ASTNode bodyNode;

  /**
   * Creates a new AST Node that represents a while loop.
   *
   * @param conditionalNode The condtion for this if statement.
   * @param bodyNode The body to execute if condition while true.
   */
  WhileNode(BooleanExpressionNode conditionalNode, ASTNode bodyNode) {
    this.conditionalNode = conditionalNode; // TODO Should we requireNonNull here?
    this.bodyNode = requireNonNull(bodyNode, "body");
  }

  /**
   * Returns the conditional node.
   *
   * @return the conditional node.
   */
  public BooleanExpressionNode getConditionalNode() {
    return conditionalNode;
  }

  /**
   * Returns the body node.
   *
   * @return the body node.
   */
  public ASTNode getBodyNode() {
    return bodyNode;
  }
}
