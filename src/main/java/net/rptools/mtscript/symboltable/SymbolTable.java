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

/** Interface that represents the script symbol table. */
public interface SymbolTable {

  /**
   * Returns the scope level of the symbol table.
   *
   * @return the scope level of the symbol table.
   */
  int getLevel();

  /**
   * Creates a new {@link SymbolTableEntry} in the symbol table.
   *
   * @param name the name of the symbol to create.
   * @return the newly create {@link SymbolTableEntry}.
   * @throws IllegalStateException if the symbol already exists.
   */
  SymbolTableEntry create(String name);

  /**
   * Returns the {@link SymbolTableEntry} for the specified symbol name.
   *
   * @param name the name of the symbol to retrieve.
   * @return the symbol for the specified name.
   */
  Optional<SymbolTableEntry> lookup(String name);
}
