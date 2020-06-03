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

import java.util.List;
import net.rptools.mtscript.symboltable.SymbolTableEntry;

/** Interface for Stack Frames in the execution engine. */
public interface StackFrame {

  /**
   * Returns the {@link SymbolTableEntry} that represents the code block this stack frame is for.
   *
   * @return the {@link SymbolTableEntry} that represents the code block this stack frame is for.
   */
  SymbolTableEntry getSymbolTableEntry();

  /**
   * Returns the "memory" value for a given name.
   *
   * @param name the name of the symbol.
   * @return the {@link StackMemory} for the symbol name.
   */
  StackMemory getMemory(String name);

  /** Returns the name of the "memory" locations. */
  List<String> getMemoryNames();

  /**
   * Returns the nesting level for the {@code StackFrame}.
   *
   * @return the nesting level for the {@code StackFrame}.
   */
  int getNestingLevel();

  /**
   * Returns the next {@code StackFrame}.
   *
   * @return the next {@code StackFrame}.
   */
  StackFrame getNext();

  /**
   * Sets the next {@code StackFrame}.
   *
   * @param frame the {@code StackFrame} to set as the next one.
   */
  void setNext(StackFrame frame);
}
