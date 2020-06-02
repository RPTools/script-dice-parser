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

/** Interface for factory class the can create a {@link SymbolTableEntry}. */
public interface SymbolTableEntryFactory {

  /**
   * Creates a new {@link SymbolTableEntry}.
   *
   * @param symbolTable the {@link SymbolTable} that the {@link SymbolTableEntry} belongs to.
   * @param name the name of the {@link SymbolTableEntry}.
   * @return the newly created {@link SymbolTableEntry}.
   */
  SymbolTableEntry create(String name, SymbolTable symbolTable);
}
