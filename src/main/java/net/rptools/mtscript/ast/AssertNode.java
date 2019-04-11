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

public class AssertNode implements ASTNode {

  private final ASTNode conditionalNode;
  private final ASTNode valueNode;

  /**
   * Creates a new AssertNode with a provided conditional and optional value nodes.
   *
   * @param conditionalNode The conditional node.
   * @param valueNode The optional value node.
   */
  AssertNode(ASTNode conditionalNode, ASTNode valueNode) {
    this.conditionalNode = conditionalNode;
    this.valueNode = valueNode;
  }

  /**
   * Returns the conditional node.
   *
   * @return the conditional node.
   */
  public ASTNode getConditionalNode() {
    return conditionalNode;
  }

  /**
   * Returns the value node.
   *
   * @return the value node or null.
   */
  public ASTNode getValueNode() {
    return valueNode;
  }
}
