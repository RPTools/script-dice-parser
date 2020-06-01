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
package net.rptools.mtscript.util;

import java.util.Comparator;
import java.util.Optional;
import net.rptools.mtscript.ast.ASTNode;
import net.rptools.mtscript.symboltable.SymbolTable;
import net.rptools.mtscript.symboltable.SymbolTableAttributeKey;
import net.rptools.mtscript.symboltable.SymbolTableEntry;
import net.rptools.mtscript.symboltable.SymbolTableStack;

public class SymbolTablePrinter {

  /**
   * Class to print out the contents of a {@link SymbolTable}.
   *
   * @param symbolTable the {@link SymbolTable} to print the contents of.
   * @param indentLevel the level of indentation.
   */
  public void printSymbolTable(SymbolTable symbolTable, int indentLevel) {
    symbolTable.getEntries().stream()
        .sorted(Comparator.comparing(SymbolTableEntry::getName))
        .forEach(
            ste -> {
              System.out.println("Name: " + ste.getName());

              Optional<SymbolTable> st =
                  ste.getAttribute(SymbolTableAttributeKey.SYMBOL_TABLE, SymbolTable.class);
              st.ifPresent(table -> printSymbolTable(table, indentLevel + 2));

              Optional<ASTNode> ast =
                  ste.getAttribute(SymbolTableAttributeKey.CODE_AST, ASTNode.class);

              ast.ifPresent(node -> new ASTPrinter().print(node, indentLevel + 2));
            });
  }

  /**
   * Prints out the contents of a {@link SymbolTableStack}.
   *
   * @param stack the {@link SymbolTableStack} to print out the contents of.
   */
  public void printSymbolTableStack(SymbolTableStack stack) {
    System.out.println("Symbol Table Stack");
    for (int i = 0; i <= stack.getLocalLevel(); i++) {
      printSymbolTable(stack.getSymbolTable(i), 0);
    }
  }
}
