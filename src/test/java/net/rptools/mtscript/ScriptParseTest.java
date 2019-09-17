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
package net.rptools.mtscript;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import net.rptools.mtscript.ast.ASTNode;
import net.rptools.mtscript.ast.ChatNode;
import net.rptools.mtscript.ast.ExpressionNode;
import net.rptools.mtscript.ast.MethodCallNode;
import net.rptools.mtscript.ast.ScriptNode;
import net.rptools.mtscript.ast.StringLiteralNode;
import net.rptools.mtscript.parser.MTScript2Lexer;
import net.rptools.mtscript.parser.MTScript2Parser;
import net.rptools.mtscript.parser.visitor.BuildASTVisitor;

class ScriptParseTest {

  @Test
  @DisplayName("Simple In-line Script Parse.")
  void parseSimpleHelloWorld() {
    String script = getResourceAsString("scriptsamples/hello_world.mts2");
    MTScript2Parser parser = createParser(script);
    ParseTree parseTree = parser.chat();
    BuildASTVisitor visitor = new BuildASTVisitor();
    ASTNode root = parseTree.accept(visitor);
    assertNotNull(root);
    ChatNode chatNode = ChatNode.class.cast(root);
    assertEquals(chatNode.getChildren().size(), 1, "Should be 1 node");
    ScriptNode scriptNode = ScriptNode.class.cast(chatNode.getChildren().get(0));
    assertEquals(scriptNode.getChildren().size(), 1, "Script node should contain one node");
    MethodCallNode methodCallNode = MethodCallNode.class.cast(scriptNode.getChildren().get(0));
    assertEquals(methodCallNode.getIdentifier(), "print");
    List<ExpressionNode> args = methodCallNode.getParameters();
    assertNotNull(args);
    assertEquals(args.size(), 1);
    StringLiteralNode str = StringLiteralNode.class.cast(args.get(0));
    assertEquals(str.getValue(), "Hello world");
  }

  private MTScript2Parser createParser(String input) {
    MTScript2Lexer lexer = new MTScript2Lexer(CharStreams.fromString(input));
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    MTScript2Parser parser = new MTScript2Parser(tokens);
    return parser;
  }

  private String getResourceAsString(String fileName) {
    InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
    if (is != null) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(is));
      return reader.lines().collect(Collectors.joining(System.lineSeparator()));
    }
    return null;
  }
}
