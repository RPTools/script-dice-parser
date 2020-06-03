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

/** Interface for Map Toll Script types. */
public interface MTSType {

  /**
   * Returns the {@link SymbolTableEntry} for this type.
   *
   * @return the {@link SymbolTableEntry} for this type.
   */
  SymbolTableEntry getSymbolTableEntry();

  /**
   * Returns the name of the type.
   *
   * @return the name of the type.
   */
  String getName();

  /**
   * Returns the default value for this type. This is the value that the variable will be set to if
   * it is uninitialised.
   *
   * @return the default value for this type.
   */
  Object getDefaultValue();
}
