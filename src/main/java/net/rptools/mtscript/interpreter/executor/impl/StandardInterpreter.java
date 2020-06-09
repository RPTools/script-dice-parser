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
import java.util.Optional;
import net.rptools.mtscript.ast.ASTNode;
import net.rptools.mtscript.interpreter.executor.InstructionExecutorFactory;
import net.rptools.mtscript.interpreter.executor.Interpreter;
import net.rptools.mtscript.interpreter.runtimestack.RuntimeStack;
import net.rptools.mtscript.interpreter.runtimestack.StackFrameFactory;
import net.rptools.mtscript.symboltable.SymbolTableAttributeKey;
import net.rptools.mtscript.symboltable.SymbolTableEntry;
import net.rptools.mtscript.symboltable.SymbolTableStack;

public class StandardInterpreter implements Interpreter {

  /** The {@link SymbolTableStack}. used for execution. */
  private final SymbolTableStack symbolTableStack;

  /** */
  private final InstructionExecutorFactory instructionExecutorFactory;

  private final StackFrameFactory stackFrameFactory;

  private final RuntimeStack runtimeStack;

  @Inject
  StandardInterpreter(
      @Assisted SymbolTableStack symbolTableStack,
      RuntimeStack runtimeStack,
      InstructionExecutorFactory instructionExecutorFactor,
      StackFrameFactory stackFrameFactory) {
    this.symbolTableStack = symbolTableStack;
    this.runtimeStack = runtimeStack;
    this.instructionExecutorFactory = instructionExecutorFactor;
    this.stackFrameFactory = stackFrameFactory;

    runtimeStack.push(stackFrameFactory.createStackFrame(symbolTableStack.getLocalSymbolTable()));
  }

  @Override
  public SymbolTableStack getSymbolTableStack() {
    return symbolTableStack;
  }

  @Override
  public void execute(String symbolName) {
    Optional<SymbolTableEntry> symbol = symbolTableStack.lookup(symbolName);
    if (symbol.isEmpty()) {
      throw new IllegalStateException("Unknown Symbol Name " + symbolName);
    }

    Optional<ASTNode> ast =
        symbol.get().getAttribute(SymbolTableAttributeKey.CODE_AST, ASTNode.class);
    if (ast.isEmpty()) {
      throw new IllegalStateException(
          "Symbol " + symbolName + " does not contain executable code.");
    }

    execute(ast.get());
  }

  @Override
  public void execute(ASTNode ast) {
    var executor = instructionExecutorFactory.get(ast.getType());
    executor.execute(ast, runtimeStack);
  }
}
