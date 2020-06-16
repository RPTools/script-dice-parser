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
import java.util.Optional;
import net.rptools.mtscript.types.MTSType;

/** Interface that represents nodes in our abstract syntax tree. */
public interface ASTNode {

  /**
   * Returns the type of the {@code ASTNode}.
   *
   * @return the type of the {@code ASTNode}.
   */
  ASTNodeType getType();

  /**
   * Sets the parent of this {@code ASTNode}.
   *
   * @param parent the parent of this {@code ASTNode}.
   */
  void setParent(ASTNode parent);

  /**
   * Returns the parent of this {@code ASTNode}.
   *
   * @return the parent of this {@code ASTNode}.
   */
  ASTNode getParent();

  /**
   * Adds a child {@code ASTNode} to this node.
   *
   * @param node adds a child node to this node.
   * @return the node that was added.
   */
  ASTNode addChild(ASTNode node);

  /**
   * Returns the children of this node.
   *
   * @return the children of this node.
   */
  List<ASTNode> getChildren();

  /**
   * Sets the specified attribute for this node.
   *
   * @param key the attribute key to set.
   * @param value the value to set for the attribute.
   */
  void setAttribute(ASTAttributeKey key, Object value);

  /**
   * Returns the specified attribute for this node.
   *
   * @param key the key for the attribute to return.
   * @return the value of the attribute.
   */
  Optional<Object> getAttribute(ASTAttributeKey key);

  /**
   * Returns the specified attribute for this node.
   *
   * @param <T> the attribute type.
   * @param key the key for the attribute to return.
   * @param clazz the attribute class.
   * @return the value of the attribute.
   */
  <T> Optional<T> getAttribute(ASTAttributeKey key, Class<T> clazz);

  /**
   * Returns the {@link MTSType} resulting from the {@code ASTNode}.
   *
   * @return the {@link MTSType} resulting from the {@code ASTNode}.
   */
  MTSType getMTSType();

  /**
   * Sets the {@link MTSType} resulting from the [@code ASTNode}.
   *
   * @param type the {@link MTSType} resulting from the [@code ASTNode}.
   */
  void setMTSType(MTSType type);
}
