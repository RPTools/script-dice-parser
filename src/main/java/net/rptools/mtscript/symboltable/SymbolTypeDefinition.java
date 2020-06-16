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

/** Enumeration for the different types of symbols stored in the symbol table. */
public enum SymbolTypeDefinition {
  /** Entry in the symbol table for a script type. */
  TYPE,
  /** Entry in the symbol table for a module. */
  MODULE,
  /** Entry in the symbol table for a constant value. */
  CONSTANT,
  /** Entry in the symbol table for a variable. */
  VARIABLE,
  /** Entry in the symbol table for a function. */
  FUNCTION,
  /** Entry in the symbol table for a procedure. */
  PROCEDURE,
  /** Entry in the symbol table for parameters. */
  PARAMETER,
  /** Entry in the symbol table for Roll definition. */
  ROLL,
  /** Entry in the symbol table for a dice roll. */
  DICE_ROLL,
  /** Entry in the symbol table for roll arguments. */
  ROLL_ARGUMENT
}
