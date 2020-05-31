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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/** Class that represents nodes in our abstract syntax tree. */
public class GenericASTNode implements ASTNode {

  /** The parent of this {@code ASTNode}. */
  private ASTNode parent;

  /** The type of node. */
  private ASTNodeType type;

  /** Attributes for the {@code ASTNode}. */
  private final Map<ASTAttributeKey, Object> attributeMap = new HashMap<>();

  /** The children of this {@code ASTNode}. */
  private final ArrayList<ASTNode> children = new ArrayList<>();

  @Override
  public void setType(ASTNodeType type) {
    this.type = type;
  }

  @Override
  public ASTNodeType getType() {
    return type;
  }

  @Override
  public void setParent(ASTNode parent) {
    this.parent = parent;
  }

  @Override
  public ASTNode getParent() {
    return parent;
  }

  @Override
  public ASTNode addChild(ASTNode node) {
    node.setParent(this);
    children.add(node);
    return node;
  }

  @Override
  public List<ASTNode> getChildren() {
    return Collections.unmodifiableList(children);
  }

  @Override
  public void setAttribute(ASTAttributeKey key, Object value) {
    attributeMap.put(key, value);
  }

  @Override
  public Optional<Object> getAttribute(ASTAttributeKey key) {
    return Optional.ofNullable(attributeMap.get(key));
  }

  @Override
  public <T> Optional<T> getAttribute(ASTAttributeKey key, Class<T> clazz) {
    return Optional.ofNullable(clazz.cast(attributeMap.get(key)));
  }
}
