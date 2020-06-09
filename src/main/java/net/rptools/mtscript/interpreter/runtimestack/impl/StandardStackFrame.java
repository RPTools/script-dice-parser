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
import java.util.Set;
import net.rptools.mtscript.interpreter.runtimestack.StackFrame;
import net.rptools.mtscript.interpreter.runtimestack.StackMemory;
import net.rptools.mtscript.interpreter.runtimestack.StackMemoryFactory;
import net.rptools.mtscript.interpreter.runtimestack.StackMemoryLocation;
import net.rptools.mtscript.symboltable.SymbolTable;

/** Stack Frames in the execution engine. */
public class StandardStackFrame implements StackFrame {

  /** The {@link SymbolTable} of the code block that this {@code StackFrame} is for. */
  private final SymbolTable symbolTable;

  /** The "memory" for things on the stack. */
  private final StackMemory memory;

  /**
   * Creates a new {@code StandardStackFrame} for a code block.
   *
   * @param symbolTable the {@link SymbolTable} that this {@code StackFrame} is for.
   */
  @Inject
  StandardStackFrame(SymbolTable symbolTable, StackMemoryFactory memFactory) {
    this.symbolTable = symbolTable;
    memory = memFactory.createMemory(symbolTable);
  }

  @Override
  public SymbolTable getSymbolTable() {
    return symbolTable;
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
    return getSymbolTable().getLevel();
  }
}
