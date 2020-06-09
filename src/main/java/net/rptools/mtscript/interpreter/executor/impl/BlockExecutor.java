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

import java.util.Optional;
import net.rptools.mtscript.ast.ASTAttributeKey;
import net.rptools.mtscript.ast.ASTNode;
import net.rptools.mtscript.interpreter.executor.InstructionExecutor;
import net.rptools.mtscript.interpreter.runtimestack.RuntimeStack;
import net.rptools.mtscript.interpreter.runtimestack.StackFrame;
import net.rptools.mtscript.interpreter.runtimestack.StackFrameFactory;
import net.rptools.mtscript.symboltable.SymbolTable;

/** Class used for executing "block" AST instructions. */
public class BlockExecutor implements InstructionExecutor {

  /** Factory object used for creating {@link StackFrame}s. */
  private final StackFrameFactory stackFrameFactory;

  /**
   * Creates a new {@link BlockExecutor}.
   *
   * @param stackFrameFactory
   */
  public BlockExecutor(StackFrameFactory stackFrameFactory) {
    this.stackFrameFactory = stackFrameFactory;
  }

  @Override
  public Optional<Object> execute(ASTNode node, RuntimeStack runtimeStack) {
    System.out.println("=====================>  In block world");
    Optional<SymbolTable> symbolTable =
        node.getAttribute(ASTAttributeKey.SYMBOL_TABLE, SymbolTable.class);
    symbolTable.ifPresent(
        st -> {
          runtimeStack.push(stackFrameFactory.createStackFrame(st));
        });
    return Optional.empty();
  }
}
