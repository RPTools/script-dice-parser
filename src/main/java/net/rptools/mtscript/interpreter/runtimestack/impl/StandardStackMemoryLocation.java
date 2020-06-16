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
package net.rptools.mtscript.interpreter.runtimestack.impl;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import net.rptools.mtscript.interpreter.runtimestack.StackMemoryLocation;
import net.rptools.mtscript.types.MTSType;

/** "Memory" locations within the stack frame. */
public class StandardStackMemoryLocation implements StackMemoryLocation {

  /** The type of value in this "memory" location. */
  private final MTSType type;

  /** The value in this "memory" location. */
  private Object value;

  /**
   * Creates a new {@code StandardStackMemoryLocation} object.
   *
   * @param type the {@link MTSType} of the memory location.
   */
  @Inject
  StandardStackMemoryLocation(@Assisted MTSType type) {
    this.type = type;
    value = type.getDefaultValue();
  }

  @Override
  public MTSType getType() {
    return type;
  }

  @Override
  public Object getValue() {
    return value;
  }

  @Override
  public void setValue(Object value) {
    this.value = value;
  }
}
