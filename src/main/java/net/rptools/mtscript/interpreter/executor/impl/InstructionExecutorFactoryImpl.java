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
import net.rptools.mtscript.ast.ASTNodeType;
import net.rptools.mtscript.interpreter.executor.InstructionExecutor;
import net.rptools.mtscript.interpreter.executor.InstructionExecutorFactory;
import net.rptools.mtscript.interpreter.runtimestack.StackFrame;
import net.rptools.mtscript.interpreter.runtimestack.StackFrameFactory;

/** Factory class for creating {@link InstructionExecutor}s. */
public class InstructionExecutorFactoryImpl implements InstructionExecutorFactory {

  /** Factory object used for creating {@link StackFrame}s. */
  private final StackFrameFactory stackFrameFactory;

  @Inject
  InstructionExecutorFactoryImpl(StackFrameFactory stackFrameFactory) {
    this.stackFrameFactory = stackFrameFactory;
  }

  @Override
  public InstructionExecutor get(ASTNodeType nodeType) {
    // Top Level Entries
    switch (nodeType) {
      case SCRIPT:
        break;
      case TEXT:
        break;
      case MODULE:
        break;

        // Top Level Module Entries
      case FIELD:
        break;
      case FUNCTION:
        break;
      case PROCEDURE:
        break;
      case IMPORT:
        break;
      case EXPORT:
        break;

        // Statements
      case BLOCK:
        return new BlockExecutor(stackFrameFactory, this);
      case ASSERT:
        break;
      case IF:
        break;
      case FOR:
        break;
      case WHILE:
        break;
      case DO:
        break;
      case TRY:
        break;
      case SWITCH:
        break;
      case RETURN:
        break;
      case THROW:
        break;
      case BREAK:
        break;
      case NO_OP:
        break;

        // Relational
      case EQUAL:
        break;
      case NOT_EQUAL:
        break;
      case GREATER_THAN:
        break;
      case GREATER_THAN_EQUAL:
        break;
      case LESS_THAN_EQUAL:
        break;
      case INSTANCE_OF:
        break;

        // Boolean operations
      case AND:
        break;
      case OR:
        break;
      case NOT:
        break;

        // Bitwise operations
      case BIT_OR:
        break;
      case BIT_AND:
        break;
      case BIT_XOR:
        break;
      case BIT_NOT:
        break;

        // operations
      case ADD:
        break;
      case SUBTRACT:
        break;
      case MULTIPLY:
        break;
      case DIVIDE:
        break;
      case MODULUS:
        break;

        // Expressions
      case VARIABLE:
        break;
      case POST_FIX_INCREMENT:
        break;
      case POST_FIX_DECREMENT:
        break;
      case CALL_FUNCTION:
        break;
      case CALL_PROCEDURE:
        break;
      case LITERAL:
        break;

        // Others
      case PARAMETERS:
        break;
    }

    return new NoOperationExecutor(); // TODO, this is only temporary until above is completed
  }
}
