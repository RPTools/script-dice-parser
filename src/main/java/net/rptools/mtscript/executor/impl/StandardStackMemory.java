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
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.rptools.mtscript.executor.StackFrame;
import net.rptools.mtscript.executor.StackMemory;
import net.rptools.mtscript.executor.StackMemoryLocation;
import net.rptools.mtscript.executor.StackMemoryLocationFactory;
import net.rptools.mtscript.symboltable.SymbolTable;
import net.rptools.mtscript.symboltable.SymbolTableEntry;
import net.rptools.mtscript.types.MTSType;
import net.rptools.mtscript.types.MTSTypeDefinition;

/** "memory" that is part of the {@link StackFrame}. */
public class StandardStackMemory implements StackMemory {

  /** Mapping between symbol names and "memory" locations. */
  private final Map<String, StackMemoryLocation> memoryMap = new HashMap<>();

  /**
   * Creates a new {@code StandardStackMemory} object.
   *
   * @param symbolTable the {@link SymbolTable} associated with this "memory".
   * @param factory the {@link StackMemoryLocationFactory} factory for creating "memory" locations.
   */
  @Inject
  StandardStackMemory(@Assisted SymbolTable symbolTable, StackMemoryLocationFactory factory) {
    symbolTable.getEntries().stream()
        .sorted(Comparator.comparing(SymbolTableEntry::getName))
        .forEach(
            e -> {
              MTSTypeDefinition typeDefinition = e.getTypeDefinition();
              if (typeDefinition == MTSTypeDefinition.CONSTANT
                  || typeDefinition == MTSTypeDefinition.PARAMETER
                  || typeDefinition == MTSTypeDefinition.VARIABLE) {
                String name = e.getName();
                MTSType type = e.getType();
                memoryMap.put(name, factory.create(type));
              }
            });
  }

  @Override
  public StackMemoryLocation getMemoryLocation(String name) {
    return memoryMap.get(name);
  }

  @Override
  public Set<String> getMemoryLocationNames() {
    return new HashSet<>(memoryMap.keySet());
  }
}
