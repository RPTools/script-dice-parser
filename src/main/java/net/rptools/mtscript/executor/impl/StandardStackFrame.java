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
package net.rptools.mtscript.executor.impl;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import java.util.Set;
import net.rptools.mtscript.executor.StackFrame;
import net.rptools.mtscript.executor.StackMemory;
import net.rptools.mtscript.executor.StackMemoryFactory;
import net.rptools.mtscript.executor.StackMemoryLocation;
import net.rptools.mtscript.symboltable.SymbolTableEntry;

/** Stack Frames in the execution engine. */
public class StandardStackFrame implements StackFrame {

  /** The {@link SymbolTableEntry} of the code block that this {@code StackFrame} is for. */
  private final SymbolTableEntry symbolTableEntry;

  /** The "memory" for things on the stack. */
  private final StackMemory memory;

  /** The next {@link StackFrame} in the chain. */
  private StackFrame next;

  /**
   * Creates a new {@code StandardStackFrame} for a code block.
   *
   * @param symbolTableEntry the {@link SymbolTableEntry} that this {@code StackFrame} is for.
   */
  @Inject
  StandardStackFrame(@Assisted SymbolTableEntry symbolTableEntry, StackMemoryFactory memFactory) {
    this.symbolTableEntry = symbolTableEntry;
    memory = memFactory.createMemory(symbolTableEntry.getSymbolTable());
  }

  @Override
  public SymbolTableEntry getSymbolTableEntry() {
    return symbolTableEntry;
  }

  @Override
  public StackMemoryLocation getMemoryLocation(String name) {
    return memory.getMemoryLocation(name);
  }

  @Override
  public Set<String> getMemoryLocationNames() {
    return memory.getMemoryLocationNames();
  }

  @Override
  public int getNestingLevel() {
    return symbolTableEntry.getSymbolTable().getLevel();
  }

  @Override
  public StackFrame getNext() {
    return next;
  }

  @Override
  public void setNext(StackFrame frame) {
    next = frame;
  }
}
