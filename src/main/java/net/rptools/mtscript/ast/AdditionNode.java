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

/** Abstract syntax tree node that represents addition of two values. */
public class AdditionNode extends BinaryOperatorNode {

  /**
   * Create a new AST node represents addition of two values.
   *
   * @param left The left hand side of the addition.
   * @param right The right hand side of the addition.
   */
  public AdditionNode(ASTNode left, ASTNode right) {
    super(left, right);
  }
}
