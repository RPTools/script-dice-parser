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

import java.util.Map;

/** An AST Node for declaration of one or more fields. */
public class FieldDeclarationNode extends DeclarationNode {

  private final String type; // TODO Replace this with a type enum, perhaps
  private final Map<String, ASTNode> fields;

  /**
   * Creates a FieldDeclarationNode for the declaration of one or more fields.
   *
   * @param type the type.
   * @param fields the fields.
   */
  FieldDeclarationNode(String type, Map<String, ASTNode> fields) {
    this.type = type;
    this.fields = fields;
  }

  /**
   * Returns the type.
   *
   * @return the type.
   */
  public String getType() {
    return type;
  }

  /**
   * Returns the fields.
   *
   * @return the fields.
   */
  public Map<String, ASTNode> getFields() {
    return fields;
  }
}
