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

import net.rptools.mtscript.interpreter.runtimestack.StackFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StandardRunTimeScopeStackTest {

  private StandardRunTimeScopeStack runTimeScopeStack;

  @BeforeEach
  public void setup() {
    runTimeScopeStack = new StandardRunTimeScopeStack();
  }

  @Test
  public void frame() {
    assertThrows(IndexOutOfBoundsException.class, () -> runTimeScopeStack.getTopFrame(0));

    var stackFrameLvl0 = mock(StackFrame.class);
    when(stackFrameLvl0.getNestingLevel()).thenReturn(0);
    runTimeScopeStack.push(stackFrameLvl0);

    assertEquals(stackFrameLvl0, runTimeScopeStack.getTopFrame(0));

    var stackFrameLvl1 = mock(StackFrame.class);
    when(stackFrameLvl1.getNestingLevel()).thenReturn(1);
    runTimeScopeStack.push(stackFrameLvl1);

    assertEquals(stackFrameLvl1, runTimeScopeStack.getTopFrame(1));

    var stackFrameLvl2_1 = mock(StackFrame.class);
    when(stackFrameLvl2_1.getNestingLevel()).thenReturn(2);

    var stackFrameLvl2_2 = mock(StackFrame.class);
    when(stackFrameLvl2_2.getNestingLevel()).thenReturn(2);

    runTimeScopeStack.push(stackFrameLvl2_1);
    runTimeScopeStack.push(stackFrameLvl2_2);

    assertEquals(stackFrameLvl2_2, runTimeScopeStack.getTopFrame(2));
  }

  @Test
  public void pop() {
    assertThrows(IndexOutOfBoundsException.class, () -> runTimeScopeStack.pop(0));

    var stackFrameLvl0 = mock(StackFrame.class);
    when(stackFrameLvl0.getNestingLevel()).thenReturn(0);
    runTimeScopeStack.push(stackFrameLvl0);

    runTimeScopeStack.pop(0);
    assertThrows(NullPointerException.class, () -> runTimeScopeStack.pop(0));

    var stackFrameLvl1 = mock(StackFrame.class);
    when(stackFrameLvl1.getNestingLevel()).thenReturn(1);
    runTimeScopeStack.push(stackFrameLvl1);

    runTimeScopeStack.pop(1);
    assertThrows(NullPointerException.class, () -> runTimeScopeStack.pop(1));

    var stackFrameLvl2_1 = mock(StackFrame.class);
    when(stackFrameLvl2_1.getNestingLevel()).thenReturn(2);

    var stackFrameLvl2_2 = mock(StackFrame.class);
    when(stackFrameLvl2_2.getNestingLevel()).thenReturn(2);

    runTimeScopeStack.push(stackFrameLvl2_1);
    runTimeScopeStack.push(stackFrameLvl2_2);

    runTimeScopeStack.pop(2);
    assertEquals(stackFrameLvl2_1, runTimeScopeStack.getTopFrame(2));

    runTimeScopeStack.pop(2);
    assertThrows(NullPointerException.class, () -> runTimeScopeStack.pop(2));
  }
}
