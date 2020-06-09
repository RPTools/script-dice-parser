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

import net.rptools.mtscript.ast.ASTNodeType;
import net.rptools.mtscript.interpreter.executor.InstructionExecutor;
import net.rptools.mtscript.interpreter.executor.InstructionExecutorFactory;

public class InstructionExecutorFactoryImpl implements InstructionExecutorFactory {

  @Override
  public InstructionExecutor get(ASTNodeType nodeType) {
    // Top Level Entries
    switch (nodeType) {
      case SCRIPT:
      case TEXT:
      case MODULE:

        // Top Level Module Entries
      case FIELD:
      case FUNCTION:
      case PROCEDURE:
      case IMPORT:
      case EXPORT:

        // Statements
      case BLOCK:
        return new BlockExecutor();
      case ASSERT:
      case IF:
      case FOR:
      case WHILE:
      case DO:
      case TRY:
      case SWITCH:
      case RETURN:
      case THROW:
      case BREAK:
      case NO_OP:

        // Relational
      case EQUAL:
      case NOT_EQUAL:
      case GREATER_THAN:
      case GREATER_THAN_EQUAL:
      case LESS_THAN_EQUAL:
      case INSTANCE_OF:

        // Boolean operations
      case AND:
      case OR:
      case NOT:

        // Bitwise operations
      case BIT_OR:
      case BIT_AND:
      case BIT_XOR:
      case BIT_NOT:

        // operations
      case ADD:
      case SUBTRACT:
      case MULTIPLY:
      case DIVIDE:
      case MODULUS:

        // Expressions
      case VARIABLE:
      case POST_FIX_INCREMENT:
      case POST_FIX_DECREMENT:
      case CALL_FUNCTION:
      case CALL_PROCEDURE:
      case LITERAL:

        // Others
      case PARAMETERS:
    }

    return null; // TODO
  }
}
