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
package net.rptools.mtscript.interpreter.runtimestack;

import java.util.Set;

/** Interface for "memory" that is part of the {@link StackFrame}. */
public interface StackMemory {

  /**
   * Returns the "memory" location associated with a symbol name.
   *
   * @param name th symbol name to get the "memory" location of.
   * @return the "memory" location.
   */
  StackMemoryLocation getMemoryLocation(String name);

  /**
   * Returns the names of all of the "memory" locations.
   *
   * @return the names of tall of the "memory" locations.
   */
  Set<String> getMemoryLocationNames();
}
