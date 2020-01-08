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

import java.util.Set;

/** An AST Node for declaration of a fields. */
public class FieldDeclarationNode extends DeclarationNode {

  private final Type type;
  private final Set<VariableDeclaratorNode> nodes;

  /**
   * Creates a FieldDeclarationNode for the declaration of one or more fields.
   *
   * @param type the type.
   * @param nodes the variable declaration nodes.
   */
  public FieldDeclarationNode(Type type, Set<VariableDeclaratorNode> nodes) {
    this.type = requireNonNull(type, "Missing type");
    this.nodes = requireNonNull(nodes, "Missing variable declarator list");
  }

  /**
   * Returns the type.
   *
   * @return the type.
   */
  public Type getType() {
    return type;
  }

  /**
   * Returns the variable declarators.
   *
   * @return The variable declarators
   */
  public Set<VariableDeclaratorNode> getNodes() {
    return nodes;
  }
}
