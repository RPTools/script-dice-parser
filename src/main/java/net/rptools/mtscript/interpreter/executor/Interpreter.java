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

import net.rptools.mtscript.ast.ASTNode;
import net.rptools.mtscript.symboltable.SymbolTableStack;

public interface Interpreter {

  /**
   * Returns the {@link SymbolTableStack} the back end is dealing with.
   *
   * @return the {@link SymbolTableStack} the back end is dealing with.
   */
  SymbolTableStack getSymbolTableStack();

  /**
   * Executes the code in the AST held symbol in the {@link SymbolTableStack}.
   *
   * @param symbolName the name of the symbol that holds the {@link ASTNode} to execute.
   */
  void execute(String symbolName);

  /**
   * Executes the code in the past in {@link ASTNode} using the supplied {@link SymbolTableStack}.
   *
   * @param ast the {@link @ASTNode} containing the code to be executed.
   */
  void execute(ASTNode ast);
}
