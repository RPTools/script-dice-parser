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

/** An AST Node for declaration of a fields. */
public class FieldDeclarationNode extends DeclarationNode {

  private final String name;
  private final Type type;
  private final ASTNode value;

  /**
   * Creates a FieldDeclarationNode for the declaration of one or more fields.
   *
   * @param name the name.
   * @param type the type.
   * @param value the value.
   */
  FieldDeclarationNode(String name, Type type, ASTNode value) {
    this.name = name;
    this.type = type;
    this.value = value;
  }

  /**
   * Returns the field name.
   *
   * @return the field name.
   */
  public String getName() {
    return name;
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
   * Returns the value.
   *
   * @return the value.
   */
  public ASTNode getValue() {
    return value;
  }
}
