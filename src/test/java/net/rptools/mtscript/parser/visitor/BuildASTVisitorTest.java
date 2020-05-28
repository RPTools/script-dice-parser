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
package net.rptools.mtscript.parser.visitor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import net.rptools.mtscript.ast.ASTNode;
import net.rptools.mtscript.ast.ChatNode;
import net.rptools.mtscript.ast.ExportNode;
import net.rptools.mtscript.ast.ScriptModuleNode;
import net.rptools.mtscript.ast.ScriptNode;
import net.rptools.mtscript.ast.TextNode;
import net.rptools.mtscript.parser.MTScript2Lexer;
import net.rptools.mtscript.parser.MTScript2Parser;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class BuildASTVisitorTest {

  @Test
  @DisplayName("Text")
  public void testText() throws IOException {
    String input = getResourceAsString("scriptsamples/text.mts2");
    MTScript2Parser parser = createParser(input, false);
    ParseTree ptree = parser.chat();
    BuildASTVisitor visitor = new BuildASTVisitor();
    ASTNode root = ptree.accept(visitor);

    assertNotNull(root);
    assertTrue(root instanceof ChatNode);
    ChatNode chat = (ChatNode) root;
    assertEquals(chat.getChildren().size(), 1);
    assertTrue(chat.getChildren().get(0) instanceof TextNode);
  }

  @Test
  @DisplayName("Empty Script")
  public void testEmptyScript() throws IOException {
    String input = getResourceAsString("scriptsamples/empty_script.mts2");
    MTScript2Parser parser = createParser(input, false);
    ParseTree ptree = parser.chat();
    BuildASTVisitor visitor = new BuildASTVisitor();
    ASTNode root = ptree.accept(visitor);

    ScriptNode script = assertAndGetChatScript(root);
    assertTrue(script.getBody().isEmpty());
  }

  @Test
  @DisplayName("Empty Module")
  public void testEmptyModule() throws IOException {
    String input = getResourceAsString("scriptsamples/empty_module.mts2");
    MTScript2Parser parser = createParser(input, true);
    ParseTree ptree = parser.scriptModule();
    BuildASTVisitor visitor = new BuildASTVisitor();
    ASTNode root = ptree.accept(visitor);

    assertNotNull(root);
    assertTrue(root instanceof ScriptModuleNode);
    ScriptModuleNode module = (ScriptModuleNode) root;
    assertEquals(module.getName(), "emptyName");
    assertEquals(module.getVersion(), "0.0.1");
    assertEquals(module.getDescription(), "An empty module");
    assertEquals(module.getImports().size(), 0);
    assertEquals(module.getDeclarations().size(), 0);
    ExportNode exports = module.getExports();
    assertEquals(exports.get().size(), 0);
  }

  @Test
  @DisplayName("Variable Definitions")
  @Disabled
  public void testVariableDefinitions() throws IOException {
    String input = getResourceAsString("scriptsamples/variables.mts2");
    MTScript2Parser parser = createParser(input, false);
    ParseTree ptree = parser.chat();
    BuildASTVisitor visitor = new BuildASTVisitor();
    ASTNode root = ptree.accept(visitor);

    ScriptNode script = assertAndGetChatScript(root);
    // TODO Add asserts for variable nodes once they exist.
  }

  @ParameterizedTest
  @MethodSource("semVerData")
  @DisplayName("SemVer parsing")
  public void testSemVer(String semVerString) {
    String input = "module name " + semVerString + " 'description';";
    MTScript2Parser parser = createParser(input, true);
    ParseTree ptree = parser.scriptModule();
    BuildASTVisitor visitor = new BuildASTVisitor();
    ASTNode root = ptree.accept(visitor);

    assertNotNull(root);
    assertTrue(root instanceof ScriptModuleNode);
    ScriptModuleNode module = (ScriptModuleNode) root;
    assertEquals(module.getName(), "name");
    assertEquals(module.getDescription(), "description");
    assertEquals(module.getVersion(), semVerString);
  }

  public static Object[][] semVerData() {
    return new Object[][] {
      {"1.2.3"}, {"0.0.1"}, {"0.1.3-alpha"}, {"0.0.0+1234"}, {"5.5.5-alpha.0+122.fred"}
      /*,
      {"1"},
      {"A"}*/
    };
  }

  /**
   * Useful for asserting that the root node is a {@link ChatNode} containing exactly one {@link
   * ScriptNode}.
   *
   * @param root {@link ASTNode} to check
   * @return {@link ScriptNode} contained via the ChatNode
   */
  private ScriptNode assertAndGetChatScript(ASTNode root) {
    assertNotNull(root);
    assertTrue(root instanceof ChatNode);
    ChatNode chat = (ChatNode) root;
    assertEquals(chat.getChildren().size(), 1);
    assertTrue(chat.getChildren().get(0) instanceof ScriptNode);
    return (ScriptNode) chat.getChildren().get(0);
  }

  private MTScript2Parser createParser(String input, boolean parsingModule) {
    MTScript2Lexer lexer = new MTScript2Lexer(CharStreams.fromString(input));
    lexer.parsingModule = parsingModule;
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    MTScript2Parser parser = new MTScript2Parser(tokens);
    parser.setErrorHandler(new TestErrorStrategy());
    return parser;
  }

  private String getResourceAsString(String filename) throws IOException {
    InputStream is = getClass().getClassLoader().getResourceAsStream(filename);
    if (is != null) {
      try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
        return reader.lines().collect(Collectors.joining(System.lineSeparator()));
      }
    }

    throw new IllegalStateException("Unable to get resource as string: " + filename);
  }

  private static class TestErrorStrategy extends BailErrorStrategy {
    @Override
    public void reportError(Parser recognizer, RecognitionException e) {
      String helpMe =
          new StringBuilder("Error parsing input. Expected: ")
              .append(e.getExpectedTokens().toString(recognizer.getVocabulary()))
              .append(" Found: ")
              .append(recognizer.getVocabulary().getDisplayName(e.getOffendingToken().getType()))
              .toString();
      System.err.println(helpMe);
      super.reportError(recognizer, e);
    }
  }
}
