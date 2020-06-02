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
package net.rptools.mtscript.symboltable;

import java.util.Optional;
import net.rptools.mtscript.types.MTSTypeDefinition;

/** Interface implemented by classes that store information in a {@link SymbolTable}. */
public interface SymbolTableEntry {

  /**
   * Returns the name of the symbol.
   *
   * @return the name of the symbol.
   */
  String getName();

  /**
   * Returns the {@link SymbolTable} that this {@code SymbolTableEntry} belongs to.
   *
   * @return the {@link SymbolTable} that this {@code SymbolTableEntry} belongs to.
   */
  SymbolTable getSymbolTable();

  /**
   * Sets an attribute for the symbol table entry.
   *
   * @param key the key for the attribute.
   * @param value the value of the attribute.
   */
  void setAttribute(SymbolTableAttributeKey key, Object value);

  /**
   * Returns the value of an attribute that is set for the symbol table entry.
   *
   * @param key the key for the attribute to return.
   * @return the value set for the attribute.
   */
  Optional<Object> getAttribute(SymbolTableAttributeKey key);

  /**
   * Returns the value of an attribute that is set for the symbol table entry.
   *
   * @param key the key for the attribute to return.
   * @param clazz the class type to return the attribute as.
   * @param <T> the type to return the attrubute as.
   * @return the value set for the attribute.
   */
  <T> Optional<T> getAttribute(SymbolTableAttributeKey key, Class<T> clazz);

  /**
   * Returns the {@link MTSTypeDefinition} for this symbol
   *
   * @return the {@link MTSTypeDefinition} for this symbol
   */
  MTSTypeDefinition getTypeDefinition();

  /**
   * Sets the {@link MTSTypeDefinition} for this symbol.
   *
   * @param typeDefinition {@link MTSTypeDefinition} for this symbol.
   */
  void setTypeDefinition(MTSTypeDefinition typeDefinition);
}
