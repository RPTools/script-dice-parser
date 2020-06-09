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

/**
 * Interface for classes used to keep track of multiple calls of functions / procedures / blocks at
 * same scope level.
 *
 * <p>This is used from within {@link net.rptools.mtscript.interpreter.executor.RuntimeStack} to
 * help tracking stack frames. While the variable memory management could all be handled by a stack
 * only it would mean that searching for a symbol of the correct scoping level would require
 * traversing the stack backwards until you hit that scope level and as recursive calls can add
 * multiple stack frames traversing that stack backwards could become inefficient. So the intent of
 * this interface is to provide an optimised way to track stack frames by symbol scope level.
 */
public interface RuntimeScopeStack {

  /**
   * Returns the top {@link net.rptools.mtscript.interpreter.executor.StackFrame} from the call
   * stack for the specified scope level.
   *
   * @param level the scope level to get the top stack frame for.
   * @return the top {@link java.util.Stack} from the call stack.
   */
  StackFrame getTopFrame(int level);

  /**
   * Pushes a {@link net.rptools.mtscript.interpreter.executor.StackFrame} on top of the call stack
   * for a scope.
   *
   * @param frame the {@link net.rptools.mtscript.interpreter.executor.StackFrame} to push on the
   *     top of the stack.
   */
  void push(StackFrame frame);

  /**
   * Pops a {@link net.rptools.mtscript.interpreter.executor.StackFrame} from the top of the call
   * stack for a scope.
   *
   * @param level the scope level.
   */
  void pop(int level);
}
