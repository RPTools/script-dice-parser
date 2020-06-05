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
package net.rptools.mtscript.ast.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import net.rptools.mtscript.ast.ASTAttributeKey;
import net.rptools.mtscript.ast.ASTNode;
import net.rptools.mtscript.ast.ASTNodeType;
import net.rptools.mtscript.types.MTSType;

/** Class that represents nodes in our abstract syntax tree. */
public class GenericASTNode implements ASTNode {

  /** The parent of this {@code ASTNode}. */
  private ASTNode parent;

  /** The type of node. */
  private final ASTNodeType type;

  /** The type resulting from executing this {@code ASTNode}. */
  private MTSType mtsType;

  /**
   * Creates a new {@code GenericASTNode}.
   *
   * @param type the type of the node.
   */
  GenericASTNode(ASTNodeType type) {
    this.type = type;
  }

  /** Attributes for the {@code ASTNode}. */
  private final Map<ASTAttributeKey, Object> attributeMap = new HashMap<>();

  /** The children of this {@code ASTNode}. */
  private final ArrayList<ASTNode> children = new ArrayList<>();

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

  @Override
  public MTSType getMTSType() {
    return mtsType;
  }

  @Override
  public void setMTSType(MTSType type) {
    mtsType = type;
  }
}
