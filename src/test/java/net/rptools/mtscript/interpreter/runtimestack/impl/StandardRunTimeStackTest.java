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

import net.rptools.mtscript.interpreter.runtimestack.RuntimeScopeStack;
import net.rptools.mtscript.interpreter.runtimestack.StackFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StandardRunTimeStackTest {

  private RuntimeScopeStack runtimeScopeStack;
  private StandardRunTimeStack standardRunTimeStack;
  private StackFrame[] stackFrames;

  @BeforeEach
  public void setup() {
    runtimeScopeStack = mock(RuntimeScopeStack.class);
    standardRunTimeStack = new StandardRunTimeStack(runtimeScopeStack);
    stackFrames =
        new StackFrame[] {mock(StackFrame.class), mock(StackFrame.class), mock(StackFrame.class)};
  }

  @Test
  public void stackFrames() {
    assertTrue(standardRunTimeStack.getStackFrames().isEmpty());
    int count = 0;
    for (var sf : stackFrames) {
      standardRunTimeStack.push(sf);
      count++;
      assertEquals(count, standardRunTimeStack.getStackFrames().size());
      assertTrue(standardRunTimeStack.getStackFrames().contains(sf));
      assertEquals(sf, standardRunTimeStack.getTopFrame());
    }
  }

  @Test
  public void getTopFrame() {
    for (int i = 0; i < stackFrames.length; i++) {
      when(runtimeScopeStack.getTopFrame(i)).thenReturn(stackFrames[i]);
      assertEquals(stackFrames[i], standardRunTimeStack.getTopFrame(i));
    }
  }

  @Test
  public void nestingLevel() {
    assertEquals(-1, standardRunTimeStack.getNestingLevel());
    for (int i = 0; i < stackFrames.length; i++) {
      when(stackFrames[i].getNestingLevel()).thenReturn(i);
      standardRunTimeStack.push(stackFrames[i]);
      assertEquals(i, standardRunTimeStack.getNestingLevel());
    }
  }

  @Test
  public void pop() {
    assertThrows(IndexOutOfBoundsException.class, () -> standardRunTimeStack.pop());
    standardRunTimeStack.push(stackFrames[0]);
    standardRunTimeStack.push(stackFrames[1]);
    standardRunTimeStack.push(stackFrames[2]);

    assertEquals(stackFrames[2], standardRunTimeStack.getTopFrame());
    standardRunTimeStack.pop();
    assertEquals(stackFrames[1], standardRunTimeStack.getTopFrame());
    standardRunTimeStack.pop();
    assertEquals(stackFrames[0], standardRunTimeStack.getTopFrame());
    standardRunTimeStack.pop();
    assertThrows(IndexOutOfBoundsException.class, () -> standardRunTimeStack.pop());
  }
}
