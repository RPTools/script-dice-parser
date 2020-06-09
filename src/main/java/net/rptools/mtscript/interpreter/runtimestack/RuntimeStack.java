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
package net.rptools.mtscript.interpreter.runtimestack;

import java.util.List;

/** Interface the defines a {@link RuntimeStack}. */
public interface RuntimeStack {

  /**
   * Returns the {@link StackFrame}s on the run time stack.
   *
   * @return the {@link StackFrame} on run time stack.
   */
  List<StackFrame> getStackFrames();

  /**
   * Returns the top {@link StackFrame} from the run time stack.
   *
   * @return the top {@link StackFrame} from the run time stack.
   */
  StackFrame getTopFrame();

  /**
   * Returns the top {@link StackFrame} for the specified nested scope level.
   *
   * @param nestingLevel the specified nested scope level.
   * @return the top {@link StackFrame}.
   */
  StackFrame getTopFrame(int nestingLevel);

  /**
   * Returns the nesting level of the top of the run time stack.
   *
   * @returns level of the top of the run time stack.
   */
  int getNestingLevel();

  /** Pops the top stack frame from the run time stack. */
  void pop();

  /**
   * Pushes a {@link StackFrame} on to the top of the run time stack.
   *
   * @param frame the {@link StackFrame} to push on to the top of the run time stack.
   */
  void push(StackFrame frame);
}
