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
package net.rptools.mtscript.types;

import net.rptools.mtscript.symboltable.SymbolTableEntry;

/** Interface for factory to create {@link MTSType}s. */
public interface MTSTypeFactory {

  /**
   * Creates a new {@link MTSType}.
   *
   * @param symbolTableEntry The {@link SymbolTableEntry} where the type is defined.
   * @param defaultValue the default "initialized" value for variables of this type.
   * @return the newly created {@link MTSType}.
   */
  MTSType create(SymbolTableEntry symbolTableEntry, Object defaultValue);
}
