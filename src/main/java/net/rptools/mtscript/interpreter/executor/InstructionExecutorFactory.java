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

import net.rptools.mtscript.ast.ASTNodeType;

/**
 * Interface for factory class that will return the {@link
 * net.rptools.mtscript.interpreter.executor.InstructionExecutor} for a specific {@link
 * ASTNodeType}.
 */
public interface InstructionExecutorFactory {

  /**
   * Returns the {@link net.rptools.mtscript.interpreter.executor.InstructionExecutor} for the
   * specified {@link ASTNodeType}.
   *
   * @param nodeType the {@link ASTNodeType} to return the executor for.
   * @return the {@link net.rptools.mtscript.interpreter.executor.InstructionExecutor}.
   */
  InstructionExecutor get(ASTNodeType nodeType);
}
