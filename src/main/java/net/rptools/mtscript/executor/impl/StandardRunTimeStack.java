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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.rptools.mtscript.executor.RuntimeScopeStack;
import net.rptools.mtscript.executor.RuntimeStack;
import net.rptools.mtscript.executor.StackFrame;

/** Class implementing the {@link RuntimeStack} for execution engine. */
public class StandardRunTimeStack implements RuntimeStack {

  /** The stack of {@link StackFrame}s. */
  private final ArrayList<StackFrame> stack = new ArrayList<>();
  /**
   * The {@link RuntimeScopeStack} used to keep track of {@link StackFrame}s by nesting scope level.
   */
  private final RuntimeScopeStack runTimeCallStack;
  /** The top of the stack. */
  private int stackTop;

  @Inject
  StandardRunTimeStack(RuntimeScopeStack stack) {
    runTimeCallStack = stack;
    stackTop = -1;
  }

  @Override
  public List<StackFrame> getStackFrames() {
    return Collections.unmodifiableList(stack);
  }

  @Override
  public StackFrame getTopFrame() {
    return stack.get(stackTop);
  }

  @Override
  public StackFrame getTopFrame(int nestingLevel) {
    return runTimeCallStack.getTopFrame(nestingLevel);
  }

  @Override
  public int getNestingLevel() {
    if (stackTop > -0) {
      return stack.get(stackTop).getNestingLevel();
    } else {
      return -1;
    }
  }

  @Override
  public void pop() {
    runTimeCallStack.pop(getNestingLevel());
    stack.remove(stackTop);
    stackTop--;
  }

  @Override
  public void push(StackFrame frame) {
    stackTop++;
    stack.add(frame);
    runTimeCallStack.push(frame);
  }
}
