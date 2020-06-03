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
package net.rptools.mtscript.executor;

import net.rptools.mtscript.types.MTSType;

/** Interface for a "memory" location in the stack frame. */
public interface StackMemoryLocation {

  /**
   * Returns the {@link MTSType} of the value held in the "memory" location.
   *
   * @return the {@link MTSType} of the value held in the "memory" location.
   */
  MTSType getType();

  /**
   * Returns the value stored in the "memory" location.
   *
   * @return the value stored in the "memory" location.
   */
  Object getValue();

  /**
   * Sets the vale stored in the "memory" location.
   *
   * @param value the value to store in the "memory" location.
   */
  void setValue(Object value);
}
