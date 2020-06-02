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
package net.rptools.mtscript.symboltable;

/** Key values for attributes in the symbol table. */
public enum SymbolTableAttributeKey {
  /** Attribute contains a constant value. */
  CONSTANT,
  /** Attribute contains the root of an AST tree ({@link net.rptools.mtscript.ast.ASTNode)}. */
  CODE_AST,
  /** Attribute contains a {@link net.rptools.mtscript.symboltable.SymbolTable}. */
  SYMBOL_TABLE,
  /** Attribute contains a Type. */
  TYPE
}
