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
package net.rptools.mtscript.interpreter.executor;

import java.util.Optional;
import net.rptools.mtscript.ast.ASTNode;
import net.rptools.mtscript.interpreter.runtimestack.RuntimeStack;

/** Interface for classes that execute instructions in {@link ASTNode}. */
public interface InstructionExecutor {

  /**
   * Executes the instruction.
   *
   * @param node the {@link ASTNode} containing the instruction.
   * @param runtimeStack the {@link RuntimeStack} which contains the execution context.j
   * @return the result of executing the instruction.
   */
  Optional<Object> execute(ASTNode node, RuntimeStack runtimeStack);
}
