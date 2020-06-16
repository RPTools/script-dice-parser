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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import net.rptools.mtscript.ast.ASTAttributeKey;
import net.rptools.mtscript.ast.ASTNode;
import net.rptools.mtscript.ast.ASTNodeType;
import net.rptools.mtscript.interpreter.executor.InstructionExecutor;
import net.rptools.mtscript.interpreter.executor.InstructionExecutorFactory;
import net.rptools.mtscript.interpreter.runtimestack.RuntimeStack;
import net.rptools.mtscript.interpreter.runtimestack.StackFrameFactory;
import net.rptools.mtscript.symboltable.SymbolTable;
import org.junit.jupiter.api.Test;

class BlockExecutorTest {

  @Test
  public void executeEmptyBlock() {
    ASTNode node = mock(ASTNode.class);
    when(node.getAttribute(ASTAttributeKey.SYMBOL_TABLE, SymbolTable.class))
        .thenReturn(Optional.empty());
    when(node.getChildren()).thenReturn(new ArrayList<>());

    BlockExecutor executor =
        new BlockExecutor(mock(StackFrameFactory.class), mock(InstructionExecutorFactory.class));
    assertTrue(executor.execute(node, mock(RuntimeStack.class)).isEmpty());
  }

  @Test
  public void executeEmptyBlockWithSymbolTable() {
    ASTNode node = mock(ASTNode.class);
    SymbolTable symbolTable = mock(SymbolTable.class);

    StackFrameFactory stackFrameFactory = mock(StackFrameFactory.class);
    InstructionExecutorFactory instructionExecutorFactory = mock(InstructionExecutorFactory.class);

    BlockExecutor executor = new BlockExecutor(stackFrameFactory, instructionExecutorFactory);
    when(node.getAttribute(ASTAttributeKey.SYMBOL_TABLE, SymbolTable.class))
        .thenReturn(Optional.of(symbolTable));

    assertTrue(executor.execute(node, mock(RuntimeStack.class)).isEmpty());
    verify(stackFrameFactory, times(1)).createStackFrame(symbolTable);
    verify(instructionExecutorFactory, times(0)).get(ASTNodeType.ADD);
  }

  @Test
  public void executeBlockWithStatement() {
    ASTNode node = mock(ASTNode.class);
    SymbolTable symbolTable = mock(SymbolTable.class);

    StackFrameFactory stackFrameFactory = mock(StackFrameFactory.class);
    RuntimeStack runtimeStack = mock(RuntimeStack.class);
    InstructionExecutorFactory instructionExecutorFactory = mock(InstructionExecutorFactory.class);
    InstructionExecutor instructionExecutor = mock(InstructionExecutor.class);

    when(instructionExecutorFactory.get(null)).thenReturn(instructionExecutor);

    ASTNode[] children = {mock(ASTNode.class), mock(ASTNode.class), mock(ASTNode.class)};
    BlockExecutor executor = new BlockExecutor(stackFrameFactory, instructionExecutorFactory);
    when(node.getAttribute(ASTAttributeKey.SYMBOL_TABLE, SymbolTable.class))
        .thenReturn(Optional.empty());
    when(node.getChildren()).thenReturn(List.of(children));

    assertTrue(executor.execute(node, runtimeStack).isEmpty());
    verify(stackFrameFactory, times(0)).createStackFrame(symbolTable);
    verify(instructionExecutor, times(1)).execute(children[0], runtimeStack);
    verify(instructionExecutor, times(1)).execute(children[1], runtimeStack);
    verify(instructionExecutor, times(1)).execute(children[2], runtimeStack);
  }
}
