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
import net.rptools.mtscript.interpreter.runtimestack.StackFrame;
import net.rptools.mtscript.interpreter.runtimestack.StackFrameFactory;
import net.rptools.mtscript.interpreter.runtimestack.StackMemory;
import net.rptools.mtscript.interpreter.runtimestack.StackMemoryFactory;
import net.rptools.mtscript.symboltable.SymbolTable;

/** Factory class for creating a {@link StackFrame}. */
public class StackFrameFactoryImpl implements StackFrameFactory {

  /** Factory class for creating {@link StackMemory} that lives on the stack frame. */
  private final StackMemoryFactory stackMemoryFactory;

  @Inject
  private StackFrameFactoryImpl(StackMemoryFactory stackMemoryFactory) {
    this.stackMemoryFactory = stackMemoryFactory;
  }

  @Override
  public StackFrame createStackFrame(SymbolTable symbolTable) {
    return new StandardStackFrame(symbolTable, stackMemoryFactory);
  }
}
