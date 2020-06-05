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
import java.util.List;
import net.rptools.mtscript.executor.RuntimeCallStack;
import net.rptools.mtscript.executor.RuntimeStack;
import net.rptools.mtscript.executor.StackFrame;

public class StandardRunTimeStack implements RuntimeStack {

  private final RuntimeCallStack runTimeCallStack;

  @Inject
  StandardRunTimeStack(RuntimeCallStack stack) {
    runTimeCallStack = stack;
  }

  @Override
  public List<StackFrame> getStackFrames() {
    return null;
  }

  @Override
  public StackFrame getTopFrame() {
    return null;
  }

  @Override
  public int getNestingLevel() {
    return 0;
  }

  @Override
  public void pop() {}

  @Override
  public void push(StackFrame frame) {}
}
