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

import net.rptools.mtscript.executor.StackFrame;
import net.rptools.mtscript.executor.StackFrameFactory;
import net.rptools.mtscript.executor.StackMemory;
import net.rptools.mtscript.executor.StackMemoryFactory;
import net.rptools.mtscript.executor.StackMemoryLocation;
import net.rptools.mtscript.executor.StackMemoryLocationFactory;
import net.rptools.mtscript.symboltable.SymbolTable;
import net.rptools.mtscript.symboltable.SymbolTableEntry;
import net.rptools.mtscript.types.MTSType;

/** Factory for creating StackFrame related objects. */
public class StackFrameFactoryImpl
    implements StackFrameFactory, StackMemoryFactory, StackMemoryLocationFactory {

  @Override
  public StackFrame createStackFrame(
      SymbolTableEntry symbolTableEntry,
      StackMemoryFactory memFactory,
      StackMemoryLocationFactory locFactory) {
    return new StandardStackFrame(symbolTableEntry, memFactory, locFactory);
  }

  @Override
  public StackMemory createMemory(StackMemoryLocationFactory factory, SymbolTable symbolTable) {
    return new StandardStackMemory(symbolTable, factory);
  }

  @Override
  public StackMemoryLocation createMemoryLocation(MTSType type) {
    return new StandardStackMemoryLocation(type);
  }
}
