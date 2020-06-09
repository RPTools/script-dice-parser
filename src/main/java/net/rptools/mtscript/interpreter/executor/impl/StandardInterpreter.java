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
package net.rptools.mtscript.interpreter.executor.impl;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import net.rptools.mtscript.ast.ASTNode;
import net.rptools.mtscript.interpreter.executor.InstructionExecutorFactory;
import net.rptools.mtscript.interpreter.executor.Interpreter;
import net.rptools.mtscript.interpreter.runtimestack.RuntimeStack;
import net.rptools.mtscript.symboltable.SymbolTableStack;

public class StandardInterpreter implements Interpreter {

  /** The {@link SymbolTableStack}. used for execution. */
  private final SymbolTableStack symbolTableStack;

  /** */
  private final InstructionExecutorFactory factory;

  private final RuntimeStack runtimeStack;

  @Inject
  StandardInterpreter(
      @Assisted SymbolTableStack symbolTableStack,
      RuntimeStack runtimeStack,
      InstructionExecutorFactory factory) {
    this.symbolTableStack = symbolTableStack;
    this.runtimeStack = runtimeStack;
    this.factory = factory;
  }

  @Override
  public SymbolTableStack getSymbolTableStack() {
    return symbolTableStack;
  }

  @Override
  public void execute(String symbolName) {}

  @Override
  public void execute(ASTNode ast) {
    var executor = factory.get(ast.getType());
    executor.execute(ast, runtimeStack);
  }
}
