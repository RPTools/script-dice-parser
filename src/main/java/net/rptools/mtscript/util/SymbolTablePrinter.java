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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.rptools.mtscript.ast.ASTNode;
import net.rptools.mtscript.symboltable.SymbolTable;
import net.rptools.mtscript.symboltable.SymbolTableAttributeKey;
import net.rptools.mtscript.symboltable.SymbolTableEntry;
import net.rptools.mtscript.symboltable.SymbolTableStack;

/**
 * Utility class used for printing out the contents of a {@link SymbolTableStack}.
 */
public class SymbolTablePrinter {

  /**
   * Class to print out the contents of a {@link SymbolTable}.
   *
   * @param symbolTable the {@link SymbolTable} to print the contents of.
   * @param indentLevel the level of indentation.
   */
  public void printSymbolTable(SymbolTable symbolTable, int indentLevel) {
    String indent = " ".repeat(indentLevel);
    System.out.println(
        ANSIEscape.WHITE_BOLD
            + "Symbol Table Nesting Level "
            + symbolTable.getLevel()
            + ANSIEscape.RESET);
    if (symbolTable.getEntries().isEmpty()) {
      System.out.println(indent + "  " + ANSIEscape.RED + "No Entries" + ANSIEscape.RESET);
    }
    symbolTable.getEntries().stream()
        .sorted(Comparator.comparing(SymbolTableEntry::getName))
        .forEach(
            ste -> {
              System.out.println("Name: " + ste.getName());

              Optional<Object> cnst = ste.getAttribute(SymbolTableAttributeKey.CONSTANT);
              cnst.ifPresent(c -> System.out.println(formatValue(c.toString(), indentLevel + 2)));

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
    System.out.println(ANSIEscape.WHITE_BOLD_BRIGHT + "Symbol Table Stack" + ANSIEscape.RESET);
    for (int i = 0; i <= stack.getNestingLevel(); i++) {
      printSymbolTable(stack.getSymbolTable(i), 0);
    }
  }

  /**
   * Returns a formatted version of the value passed in.
   * @param value The value to format.
   * @param indentLevel the indentation level for the formatting.
   * @return a formatted version of the value.
   */
  private String formatValue(String value, int indentLevel) {
    String indent = " ".repeat(indentLevel);
    Pattern pattern = Pattern.compile("^", Pattern.MULTILINE);
    Matcher matcher = pattern.matcher(value);
    return matcher.replaceAll(indent + ANSIEscape.GREEN + ">" + ANSIEscape.RESET);
  }
}
