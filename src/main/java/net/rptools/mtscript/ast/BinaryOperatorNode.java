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

/** Node for abstract syntax tree which represents an operator with two arguments. */
abstract class BinaryOperatorNode implements ASTNode {
  private final ASTNode left;
  private final ASTNode right;

  /**
   * Creates a new AST node that represents an operation with two arguments.
   *
   * @param leftNode The argument to the left of the operator.
   * @param rightNode The argument to the right of the operator.
   */
  BinaryOperatorNode(ASTNode leftNode, ASTNode rightNode) {
    left = leftNode;
    right = rightNode;
  }

  /**
   * Returns the left hand side of the operator.
   *
   * @return the left hand side of the operator.
   */
  public ASTNode getLeft() {
    return left;
  }

  /**
   * Returns the right hand side of the operator.
   *
   * @return the right hand side of the operator.
   */
  public ASTNode getRight() {
    return right;
  }
}
