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
package net.rptools.mtscript.symboltable.impl;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import java.util.HashMap;
import java.util.Optional;
import net.rptools.mtscript.symboltable.SymbolTable;
import net.rptools.mtscript.symboltable.SymbolTableAttributeKey;
import net.rptools.mtscript.symboltable.SymbolTableEntry;
import net.rptools.mtscript.types.MTSType;
import net.rptools.mtscript.types.MTSTypeDefinition;

/** Classes that store information in a {@link SymbolTable}. */
class StandardSymbolTableEntry implements SymbolTableEntry {

  /** The {@link SymbolTable} that this entry belongs to. */
  private final SymbolTable symbolTable;

  /** The name of the symbol table entry. */
  private final String name;

  /** Attribute values for the symbol table entry. */
  private final HashMap<SymbolTableAttributeKey, Object> attributeMap = new HashMap<>();

  /** The {@link MTSType} of this symbol. */
  private MTSType type;

  /** The type definition for this symbol. */
  private MTSTypeDefinition typeDefinition;

  /**
   * Creates a new {@code StandardSymbolTableEntry} object.
   *
   * @param name the mame of the symbol.
   * @param parent the {@link SymbolTable} that this entry belongs to.
   */
  @Inject
  StandardSymbolTableEntry(@Assisted String name, @Assisted SymbolTable parent) {
    this.name = name;
    symbolTable = parent;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public SymbolTable getSymbolTable() {
    return symbolTable;
  }

  @Override
  public void setAttribute(SymbolTableAttributeKey key, Object value) {
    attributeMap.put(key, value);
  }

  @Override
  public Optional<Object> getAttribute(SymbolTableAttributeKey key) {
    return Optional.ofNullable(attributeMap.get(key));
  }

  @Override
  public <T> Optional<T> getAttribute(SymbolTableAttributeKey key, Class<T> clazz) {
    return Optional.ofNullable(clazz.cast(attributeMap.get(key)));
  }

  @Override
  public MTSTypeDefinition getTypeDefinition() {
    return typeDefinition;
  }

  @Override
  public void setTypeDefinition(MTSTypeDefinition typeDefinition) {
    this.typeDefinition = typeDefinition;
  }

  @Override
  public MTSType getType() {
    return type;
  }

  @Override
  public void setType(MTSType type) {
    this.type = type;
  }
};
