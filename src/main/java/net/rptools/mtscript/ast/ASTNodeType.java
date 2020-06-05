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
package net.rptools.mtscript.ast;

/**
 * AST Node types.
 */
public enum ASTNodeType {

  // Top Level Entries
  CHAT,
  SCRIPT,
  TEXT,
  MODULE,

  // Top Level Module Entries
  FIELD,
  FUNCTION,
  PROCEDURE,
  IMPORT,
  EXPORT,

  // Statements
  BLOCK,
  ASSERT,
  IF,
  FOR,
  WHILE,
  DO,
  TRY,
  SWITCH,
  RETURN,
  THROW,
  BREAK,
  NO_OP, // Not sure if we will need.

  // Relational
  EQUAL,
  NOT_EQUAL,
  GREATER_THAN,
  GREATER_THAN_EQUAL,
  LESS_THAN_EQUAL,
  INSTANCE_OF,

  // Boolean operations
  AND,
  OR,
  NOT,

  // Bitwise operations
  BIT_OR,
  BIT_AND,
  BIT_XOR,
  BIT_NOT,

  // operations
  ADD,
  SUBTRACT,
  MULTIPLY,
  DIVIDE,
  MODULUS,

  // Expressions
  VARIABLE,
  POST_FIX_INCREMENT,
  POST_FIX_DECREMENT,
  CALL_FUNCTION,
  CALL_PROCEDURE,
  LITERAL,

  // Other
  PARAMETERS
}
