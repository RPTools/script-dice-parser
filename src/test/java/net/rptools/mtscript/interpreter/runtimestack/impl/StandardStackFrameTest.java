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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Random;
import java.util.Set;
import net.rptools.mtscript.interpreter.runtimestack.StackMemory;
import net.rptools.mtscript.interpreter.runtimestack.StackMemoryFactory;
import net.rptools.mtscript.interpreter.runtimestack.StackMemoryLocation;
import net.rptools.mtscript.symboltable.SymbolTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StandardStackFrameTest {

  private SymbolTable symbolTable;
  private StackMemoryFactory stackMemoryFactory;
  private StandardStackFrame standardStackFrame;
  private StackMemory stackMemory;

  @BeforeEach
  public void setup() {
    symbolTable = mock(SymbolTable.class);
    stackMemoryFactory = mock(StackMemoryFactory.class);
    stackMemory = mock(StackMemory.class);

    when(stackMemoryFactory.createMemory(symbolTable)).thenReturn(stackMemory);

    standardStackFrame = new StandardStackFrame(symbolTable, stackMemoryFactory);
  }

  @Test
  public void getSymbolTable() {
    assertEquals(symbolTable, standardStackFrame.getSymbolTable());
  }

  @Test
  public void memoryLocations() {
    StackMemoryLocation mem1 = mock(StackMemoryLocation.class);
    StackMemoryLocation mem2 = mock(StackMemoryLocation.class);

    when(stackMemory.getMemoryLocation("no1")).thenReturn(mem1);
    when(stackMemory.getMemoryLocation("no2")).thenReturn(mem2);
    when(stackMemory.getMemoryLocationNames()).thenReturn(Set.of("no1", "no2"));

    assertTrue(standardStackFrame.getMemoryLocation("no1").isPresent());
    assertEquals(mem1, standardStackFrame.getMemoryLocation("no1").get());
    assertTrue(standardStackFrame.getMemoryLocation("no2").isPresent());
    assertEquals(mem2, standardStackFrame.getMemoryLocation("no2").get());

    assertTrue(standardStackFrame.getMemoryLocation("blah").isEmpty());

    assertEquals(2, standardStackFrame.getMemoryLocationNames().size());
    assertTrue(standardStackFrame.getMemoryLocationNames().contains("no1"));
    assertTrue(standardStackFrame.getMemoryLocationNames().contains("no2"));
  }

  @Test
  public void nestingLevel() {
    Random random = new Random();

    for (int i = 0; i < 100; i++) {
      int num = random.nextInt(200);
      when(symbolTable.getLevel()).thenReturn(num);

      assertEquals(num, standardStackFrame.getNestingLevel());
    }
  }
}
