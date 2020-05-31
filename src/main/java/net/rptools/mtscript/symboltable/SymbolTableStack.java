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

public interface SymbolTableStack {

  /**
   * Returns the scope level at the top of the stack which will be the local scope.
   *
   * @return the scope level at the top of the stack which will be the local scope.
   */
  int getLocalLevel();

  /**
   * Returns the {@link SymbolTable} at the top of the stack which will be the {@link SymbolTable}
   * for the local scope.
   *
   * @return the {@link SymbolTable} at the top of the stack which will be the {@link SymbolTable}
   *     for the local scope.
   */
  SymbolTable getLocalSymbolTable();

  /**
   * Creates a new {@link SymbolTable} and pushes it on top of the stack making it the new local
   * scope.
   *
   * @return the newly created {@link SymbolTable}.
   */
  SymbolTable push();

  /**
   * Pops the local {@link SymbolTable} from the top of the stack making the parent scope the local
   * scope.
   *
   * @return the {@link SymbolTable} that was popped/
   */
  SymbolTable pop();

  /**
   * Creates a new {@link SymbolTableEntry} in the local (top) scope.
   *
   * @param name the name of the new {@link SymbolTableEntry} to create/
   * @return the {@link SymbolTableEntry} that was created.
   */
  SymbolTableEntry create(String name);

  /**
   * Returns the defined {@link SymbolTableEntry} for the specified name. This method will search
   * through all of the scopes on the stack starting at the top (local) and working down to the
   * bottom of the stack.
   *
   * @param name the name of the {@link SymbolTableEntry} to retrieve.
   * @return the {@link SymbolTableEntry}
   */
  Optional<SymbolTableEntry> lookup(String name);


  /**
   * Return the {@link SymbolTable} at the specified scope level.
   * @param level the scope level to get the symbol table for.
   * @return the {@link SymbolTable} for the scope level.
   */
  SymbolTable getSymbolTable(int level);
}
