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

import java.util.ArrayList;
import java.util.List;
import net.rptools.mtscript.interpreter.runtimestack.RuntimeScopeStack;
import net.rptools.mtscript.interpreter.runtimestack.StackFrame;

/** Class used to keep track of the call stack at the nesting cope level. */
public class StandardRunTimeScopeStack implements RuntimeScopeStack {

  /** Record used to hold the {@link StackFrame} and next. */
  private static record StackFrameLink(StackFrame frame, StackFrameLink next) {}
  ;

  /** The List of stacks. */
  private final List<StackFrameLink> scopeStack = new ArrayList<>();

  @Override
  public StackFrame getTopFrame(int level) {
    return scopeStack.get(level).frame();
  }

  @Override
  public void push(StackFrame frame) {
    StackFrameLink existing;
    int level = frame.getNestingLevel();
    if (scopeStack.size() > level) {
      existing = scopeStack.get(level);
    } else {
      existing = null;
    }
    StackFrameLink link = new StackFrameLink(frame, existing);
    if (scopeStack.size() <= level) {
      scopeStack.add(null); // Hit a new high for
    }
    scopeStack.set(level, link);
  }

  @Override
  public void pop(int level) {
    StackFrameLink existing = scopeStack.get(level);
    if (existing.next() != null) {
      scopeStack.set(level, existing.next());
    } else {
      scopeStack.set(level, null);
    }
  }
}
