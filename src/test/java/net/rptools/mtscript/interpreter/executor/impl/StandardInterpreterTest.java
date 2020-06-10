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

import java.util.Optional;
import net.rptools.mtscript.ast.ASTNode;
import net.rptools.mtscript.ast.ASTNodeType;
import net.rptools.mtscript.interpreter.executor.InstructionExecutor;
import net.rptools.mtscript.interpreter.executor.InstructionExecutorFactory;
import net.rptools.mtscript.interpreter.runtimestack.RuntimeStack;
import net.rptools.mtscript.interpreter.runtimestack.StackFrameFactory;
import net.rptools.mtscript.symboltable.SymbolTableAttributeKey;
import net.rptools.mtscript.symboltable.SymbolTableEntry;
import net.rptools.mtscript.symboltable.SymbolTableStack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StandardInterpreterTest {

  private SymbolTableStack symbolTableStack;
  private RuntimeStack runtimeStack;
  private InstructionExecutorFactory instructionExecutorFactor;
  private StackFrameFactory stackFrameFactory;
  private StandardInterpreter interpreter;

  @BeforeEach
  private void setup() {
    symbolTableStack = mock(SymbolTableStack.class);
    runtimeStack = mock(RuntimeStack.class);
    instructionExecutorFactor = mock(InstructionExecutorFactory.class);
    stackFrameFactory = mock(StackFrameFactory.class);
    interpreter =
        new StandardInterpreter(
            symbolTableStack, runtimeStack, instructionExecutorFactor, stackFrameFactory);
  }

  @Test
  public void getSymbolTableStack() {
    assertEquals(symbolTableStack, interpreter.getSymbolTableStack());
  }

  @Test
  public void executeMissingSymbol() {
    when(symbolTableStack.lookup("test")).thenReturn(Optional.empty());
    assertThrows(IllegalStateException.class, () -> interpreter.execute("test"));
  }

  @Test
  public void executeInvalidSymbol() {
    SymbolTableEntry symbolTableEntry = mock(SymbolTableEntry.class);

    when(symbolTableStack.lookup("test")).thenReturn(Optional.of(symbolTableEntry));
    when(symbolTableEntry.getAttribute(SymbolTableAttributeKey.CODE_AST, ASTNode.class))
        .thenReturn(Optional.empty());

    assertThrows(IllegalStateException.class, () -> interpreter.execute("test"));
  }

  @Test
  public void executeSymbol() {
    SymbolTableEntry symbolTableEntry = mock(SymbolTableEntry.class);
    ASTNode astNode = mock(ASTNode.class);
    ASTNodeType astNodeType = ASTNodeType.values()[0];
    InstructionExecutor instructionExecutor = mock(InstructionExecutor.class);

    when(symbolTableStack.lookup("test")).thenReturn(Optional.of(symbolTableEntry));
    when(symbolTableEntry.getAttribute(SymbolTableAttributeKey.CODE_AST, ASTNode.class))
        .thenReturn(Optional.of(astNode));
    when(astNode.getType()).thenReturn(astNodeType);
    when(instructionExecutorFactor.get(astNodeType)).thenReturn(instructionExecutor);

    interpreter.execute("test");

    verify(instructionExecutor, times(1)).execute(astNode, runtimeStack);
  }
}
