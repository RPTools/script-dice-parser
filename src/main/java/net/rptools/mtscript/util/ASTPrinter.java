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

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.rptools.mtscript.ast.ASTAttributeKey;
import net.rptools.mtscript.ast.ASTNode;

/** Utility class used to print out the details of the tree of {@link ASTNode}s. */
public class ASTPrinter {

  /**
   * Prints out the tree of {@link ASTNode}s with root of {@code node}.
   *
   * @param node the root of the tree.
   * @param indentLevel the level of indentation for the output.
   */
  public void print(ASTNode node, int indentLevel) {
    String indent = " ".repeat(indentLevel);
    System.out.println(
        indent
            + node.getType()
            + ": MTSType="
            + ANSIEscape.YELLOW
            + "("
            + (node.getMTSType() == null ? "NULL type?" : node.getMTSType().getName())
            + ")"
            + ANSIEscape.RESET);
    Optional<String> valueAttr = node.getAttribute(ASTAttributeKey.VALUE, String.class);
    valueAttr.ifPresent(value -> System.out.println(formatValue(value, indentLevel + 2)));

    node.getChildren().forEach(n -> print(n, indentLevel + 2));
  }

  /**
   * Returns a formatted version of the value passed in.
   *
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
