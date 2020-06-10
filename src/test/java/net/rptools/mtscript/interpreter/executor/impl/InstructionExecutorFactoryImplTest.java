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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import net.rptools.mtscript.ast.ASTNodeType;
import net.rptools.mtscript.interpreter.runtimestack.StackFrameFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class InstructionExecutorFactoryImplTest {

  private static InstructionExecutorFactoryImpl factory;

  @BeforeAll
  public static void setup() {
    StackFrameFactory stackFrameFactory = mock(StackFrameFactory.class);
    factory = new InstructionExecutorFactoryImpl(stackFrameFactory);
  }

  @Test
  public void script() {
    assertEquals(
        NoOperationExecutor.class,
        factory.get(ASTNodeType.SCRIPT).getClass()); // TODO update when factory is updated
  }

  @Test
  public void text() {
    assertEquals(
        NoOperationExecutor.class,
        factory.get(ASTNodeType.TEXT).getClass()); // TODO update when factory is updated
  }

  @Test
  public void module() {
    assertEquals(
        NoOperationExecutor.class,
        factory.get(ASTNodeType.MODULE).getClass()); // TODO update when factory is updated
  }

  @Test
  public void field() {
    assertEquals(
        NoOperationExecutor.class,
        factory.get(ASTNodeType.FIELD).getClass()); // TODO update when factory is updated
  }

  @Test
  public void function() {
    assertEquals(
        NoOperationExecutor.class,
        factory.get(ASTNodeType.FUNCTION).getClass()); // TODO update when factory is updated
  }

  @Test
  public void procedure() {
    assertEquals(
        NoOperationExecutor.class,
        factory.get(ASTNodeType.PROCEDURE).getClass()); // TODO update when factory is updated
  }

  @Test
  public void testImport() {
    assertEquals(
        NoOperationExecutor.class,
        factory.get(ASTNodeType.IMPORT).getClass()); // TODO update when factory is updated
  }

  @Test
  public void export() {
    assertEquals(
        NoOperationExecutor.class,
        factory.get(ASTNodeType.EXPORT).getClass()); // TODO update when factory is updated
  }

  @Test
  public void block() {
    assertEquals(BlockExecutor.class, factory.get(ASTNodeType.BLOCK).getClass());
  }

  @Test
  public void testAssert() {
    assertEquals(
        NoOperationExecutor.class,
        factory.get(ASTNodeType.ASSERT).getClass()); // TODO update when factory is updated
  }

  @Test
  public void testIf() {
    assertEquals(
        NoOperationExecutor.class,
        factory.get(ASTNodeType.IF).getClass()); // TODO update when factory is updated
  }

  @Test
  public void testFor() {
    assertEquals(
        NoOperationExecutor.class,
        factory.get(ASTNodeType.FOR).getClass()); // TODO update when factory is updated
  }

  @Test
  public void testWhile() {
    assertEquals(
        NoOperationExecutor.class,
        factory.get(ASTNodeType.WHILE).getClass()); // TODO update when factory is updated
  }

  @Test
  public void testDo() {
    assertEquals(
        NoOperationExecutor.class,
        factory.get(ASTNodeType.DO).getClass()); // TODO update when factory is updated
  }

  @Test
  public void testTry() {
    assertEquals(
        NoOperationExecutor.class,
        factory.get(ASTNodeType.TRY).getClass()); // TODO update when factory is updated
  }

  @Test
  public void testSwitch() {
    assertEquals(
        NoOperationExecutor.class,
        factory.get(ASTNodeType.SWITCH).getClass()); // TODO update when factory is updated
  }

  @Test
  public void testReturn() {
    assertEquals(
        NoOperationExecutor.class,
        factory.get(ASTNodeType.RETURN).getClass()); // TODO update when factory is updated
  }

  @Test
  public void testThrow() {
    assertEquals(
        NoOperationExecutor.class,
        factory.get(ASTNodeType.THROW).getClass()); // TODO update when factory is updated
  }

  @Test
  public void testBreak() {
    assertEquals(
        NoOperationExecutor.class,
        factory.get(ASTNodeType.BREAK).getClass()); // TODO update when factory is updated
  }

  @Test
  public void noOp() {
    assertEquals(NoOperationExecutor.class, factory.get(ASTNodeType.NO_OP).getClass());
  }

  @Test
  public void testEqual() {
    assertEquals(
        NoOperationExecutor.class,
        factory.get(ASTNodeType.EQUAL).getClass()); // TODO update when factory is updated
  }

  @Test
  public void notEqual() {
    assertEquals(
        NoOperationExecutor.class,
        factory.get(ASTNodeType.NOT_EQUAL).getClass()); // TODO update when factory is updated
  }

  @Test
  public void greaterThan() {
    assertEquals(
        NoOperationExecutor.class,
        factory.get(ASTNodeType.GREATER_THAN).getClass()); // TODO update when factory is updated
  }

  @Test
  public void greaterThanEqual() {
    assertEquals(
        NoOperationExecutor.class,
        factory.get(ASTNodeType.GREATER_THAN).getClass()); // TODO update when factory is updated
  }

  @Test
  public void lessThanEqual() {
    assertEquals(
        NoOperationExecutor.class,
        factory.get(ASTNodeType.LESS_THAN_EQUAL).getClass()); // TODO update when factory is updated
  }

  @Test
  public void instanceOf() {
    assertEquals(
        NoOperationExecutor.class,
        factory.get(ASTNodeType.INSTANCE_OF).getClass()); // TODO update when factory is updated
  }

  @Test
  public void and() {
    assertEquals(
        NoOperationExecutor.class,
        factory.get(ASTNodeType.AND).getClass()); // TODO update when factory is updated
  }

  @Test
  public void or() {
    assertEquals(
        NoOperationExecutor.class,
        factory.get(ASTNodeType.OR).getClass()); // TODO update when factory is updated
  }

  @Test
  public void not() {
    assertEquals(
        NoOperationExecutor.class,
        factory.get(ASTNodeType.NOT).getClass()); // TODO update when factory is updated
  }

  @Test
  public void bitOr() {
    assertEquals(
        NoOperationExecutor.class,
        factory.get(ASTNodeType.BIT_OR).getClass()); // TODO update when factory is updated
  }

  @Test
  public void bitAnd() {
    assertEquals(
        NoOperationExecutor.class,
        factory.get(ASTNodeType.BIT_AND).getClass()); // TODO update when factory is updated
  }

  @Test
  public void bitXor() {
    assertEquals(
        NoOperationExecutor.class,
        factory.get(ASTNodeType.BIT_XOR).getClass()); // TODO update when factory is updated
  }

  @Test
  public void bitNot() {
    assertEquals(
        NoOperationExecutor.class,
        factory.get(ASTNodeType.BIT_NOT).getClass()); // TODO update when factory is updated
  }

  @Test
  public void add() {
    assertEquals(
        NoOperationExecutor.class,
        factory.get(ASTNodeType.ADD).getClass()); // TODO update when factory is updated
  }

  @Test
  public void subtract() {
    assertEquals(
        NoOperationExecutor.class,
        factory.get(ASTNodeType.SUBTRACT).getClass()); // TODO update when factory is updated
  }

  @Test
  public void multiply() {
    assertEquals(
        NoOperationExecutor.class,
        factory.get(ASTNodeType.MULTIPLY).getClass()); // TODO update when factory is updated
  }

  @Test
  public void divide() {
    assertEquals(
        NoOperationExecutor.class,
        factory.get(ASTNodeType.DIVIDE).getClass()); // TODO update when factory is updated
  }

  @Test
  public void modulus() {
    assertEquals(
        NoOperationExecutor.class,
        factory.get(ASTNodeType.MODULUS).getClass()); // TODO update when factory is updated
  }

  @Test
  public void variable() {
    assertEquals(
        NoOperationExecutor.class,
        factory.get(ASTNodeType.VARIABLE).getClass()); // TODO update when factory is updated
  }

  @Test
  public void postFixIncrement() {
    assertEquals(
        NoOperationExecutor.class,
        factory
            .get(ASTNodeType.POST_FIX_DECREMENT)
            .getClass()); // TODO update when factory is updated
  }

  @Test
  public void postFixDecrement() {
    assertEquals(
        NoOperationExecutor.class,
        factory
            .get(ASTNodeType.POST_FIX_DECREMENT)
            .getClass()); // TODO update when factory is updated
  }

  @Test
  public void callFunction() {
    assertEquals(
        NoOperationExecutor.class,
        factory.get(ASTNodeType.CALL_FUNCTION).getClass()); // TODO update when factory is updated
  }

  @Test
  public void callProcedure() {
    assertEquals(
        NoOperationExecutor.class,
        factory.get(ASTNodeType.CALL_PROCEDURE).getClass()); // TODO update when factory is updated
  }

  @Test
  public void literal() {
    assertEquals(
        NoOperationExecutor.class,
        factory.get(ASTNodeType.LITERAL).getClass()); // TODO update when factory is updated
  }

  @Test
  public void parameters() {
    assertEquals(
        NoOperationExecutor.class,
        factory.get(ASTNodeType.PARAMETERS).getClass()); // TODO update when factory is updated
  }
}
