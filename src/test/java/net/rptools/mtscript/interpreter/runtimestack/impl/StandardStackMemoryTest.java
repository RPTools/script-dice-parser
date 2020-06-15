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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;
import net.rptools.mtscript.interpreter.runtimestack.StackMemoryLocation;
import net.rptools.mtscript.interpreter.runtimestack.StackMemoryLocationFactory;
import net.rptools.mtscript.symboltable.SymbolTable;
import net.rptools.mtscript.symboltable.SymbolTableEntry;
import net.rptools.mtscript.types.MTSType;
import net.rptools.mtscript.types.MTSTypeDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StandardStackMemoryTest {

  private final Set<SymbolTableEntry> symbolTableEntries = new HashSet<>();
  private SymbolTable symbolTable;
  private StandardStackMemory standardStackMemory;

  @BeforeEach
  public void setup() {
    symbolTable = mock(SymbolTable.class);
    StackMemoryLocationFactory stackMemoryLocationFactory = mock(StackMemoryLocationFactory.class);
    for (var mtd : MTSTypeDefinition.values()) {
      SymbolTableEntry entry = mock(SymbolTableEntry.class);
      when(entry.getName()).thenReturn("test " + mtd.name());
      when(entry.getTypeDefinition()).thenReturn(mtd);
      symbolTableEntries.add(entry);
    }

    when(symbolTable.getEntries()).thenReturn(symbolTableEntries);
    when(stackMemoryLocationFactory.create(any(MTSType.class)))
        .thenReturn(mock(StackMemoryLocation.class));
    when(stackMemoryLocationFactory.create(null)).thenReturn(mock(StackMemoryLocation.class));
    standardStackMemory = new StandardStackMemory(symbolTable, stackMemoryLocationFactory);
  }

  @Test
  public void create() {

    for (var entry : symbolTableEntries) {
      var mtd = entry.getTypeDefinition();
      switch (mtd) {
        case CONSTANT, VARIABLE, PARAMETER:
          verify(entry, times(1)).getType();
          break;
        default:
          verify(entry, times(0)).getType();
      }
    }
  }

  @Test
  public void getMemoryLocation() {
    for (var entry : symbolTable.getEntries()) {
      var mtd = entry.getTypeDefinition();
      switch (mtd) {
        case CONSTANT, VARIABLE, PARAMETER:
          assertTrue(standardStackMemory.getMemoryLocation(entry.getName()).isPresent());
          break;
        default:
          assertTrue(standardStackMemory.getMemoryLocation(entry.getName()).isEmpty());
      }
    }
  }

  @Test
  public void getMemoryLocationNames() {
    assertEquals(3, standardStackMemory.getMemoryLocationNames().size());
    for (var entry : symbolTable.getEntries()) {
      var mtd = entry.getTypeDefinition();
      switch (mtd) {
        case CONSTANT, VARIABLE, PARAMETER:
          assertTrue(standardStackMemory.getMemoryLocationNames().contains(entry.getName()));
          break;
        default:
          assertFalse(standardStackMemory.getMemoryLocationNames().contains(entry.getName()));
      }
    }
  }
}
