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

import net.rptools.mtscript.symboltable.SymbolTableEntry;

/** Interface for creating {@link StackFrame} objects. */
public interface StackFrameFactory {

  /**
   * Creates a new {@link StackFrame} object.
   *
   * @param symbolTableEntry The {@link SymbolTableEntry} for the stack frame.
   * @param memFactory The factory used to create {@link StackMemory} objects.
   * @param locFactory The Factory used to create {@link StackMemoryLocation} objects.
   * @return the newly created {@link StackFrame}.
   */
  StackFrame createStackFrame(
      SymbolTableEntry symbolTableEntry,
      StackMemoryFactory memFactory,
      StackMemoryLocationFactory locFactory);
}
