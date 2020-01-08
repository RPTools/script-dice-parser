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

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import net.rptools.mtscript.listener.MTScript2TestListener;
import net.rptools.mtscript.parser.MTScript2Lexer;
import net.rptools.mtscript.parser.MTScript2Parser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InlineParseTest {

  @Test
  @DisplayName("Simple Inline Roll Parse Test.")
  void parseSimpleInlineRollParse() {
    final List<String> testText = List.of("This is a test! ", ", test one two");
    final List<String> testRolls = List.of("1d6", "2d10");

    // Create a lexer that feeds off of input CharStream
    MTScript2Lexer lexer =
        new MTScript2Lexer(
            CharStreams.fromString(
                testText.get(0)
                    + "[["
                    + testRolls.get(0)
                    + ";]]"
                    + testText.get(1)
                    + "[["
                    + testRolls.get(1)
                    + ";]]"));

    // create a buffer of tokens pulled from the lexer
    CommonTokenStream tokens = new CommonTokenStream(lexer);

    // create a parser that feeds off the tokens buffer
    MTScript2Parser parser = new MTScript2Parser(tokens);

    ParseTree parseTree = parser.chat();
    ParseTreeWalker walker = new ParseTreeWalker();

    MTScript2TestListener listener = new MTScript2TestListener();

    walker.walk(listener, parseTree);

    assertEquals(testText.size(), listener.getTextStrings().size());
    for (int i = 0; i < testText.size(); i++) {
      assertEquals(testText.get(i), listener.getTextStrings().get(i));
    }

    assertEquals(testRolls.size(), listener.getInlineRollStrings().size());
    for (int i = 0; i < testRolls.size(); i++) {
      assertEquals(testRolls.get(i), listener.getInlineRollStrings().get(i));
    }
  }

  @Test
  @DisplayName("Simple Text Parse with no rolls or inline script.")
  void parseSimpleText() {
    final List<String> testText = List.of("This is a test! ", ", test one two");

    // Create a lexer that feeds off of input CharStream
    MTScript2Lexer lexer =
        new MTScript2Lexer(CharStreams.fromString(testText.get(0) + testText.get(1)));

    // create a buffer of tokens pulled from the lexer
    CommonTokenStream tokens = new CommonTokenStream(lexer);

    // create a parser that feeds off the tokens buffer
    MTScript2Parser parser = new MTScript2Parser(tokens);

    ParseTree parseTree = parser.chat();
    ParseTreeWalker walker = new ParseTreeWalker();

    MTScript2TestListener listener = new MTScript2TestListener();

    walker.walk(listener, parseTree);

    assertEquals(1, listener.getTextStrings().size());
    String testVal = "";
    for (int i = 0; i < testText.size(); i++) {
      testVal += testText.get(i);
    }
    assertEquals(testVal, listener.getTextStrings().get(0));

    assertEquals(0, listener.getInlineRollStrings().size());
  }

  @Test
  @DisplayName("Simple Inline Script Parse Test.")
  void parseSimpleInlineScriptParse() {
    final List<String> testText = List.of("This is a test! ", ", test one two");
    final List<String> testScript = List.of("integer a;  a = 5;", "a = someFunc(); anotherFunc();");

    // Create a lexer that feeds off of input CharStream
    MTScript2Lexer lexer =
        new MTScript2Lexer(
            CharStreams.fromString(
                testText.get(0)
                    + "[["
                    + testScript.get(0)
                    + "]]"
                    + testText.get(1)
                    + "[["
                    + testScript.get(1)
                    + "]]"));

    // create a buffer of tokens pulled from the lexer
    CommonTokenStream tokens = new CommonTokenStream(lexer);

    // create a parser that feeds off the tokens buffer
    MTScript2Parser parser = new MTScript2Parser(tokens);

    ParseTree parseTree = parser.chat();
    ParseTreeWalker walker = new ParseTreeWalker();

    MTScript2TestListener listener = new MTScript2TestListener();

    walker.walk(listener, parseTree);

    assertEquals(testText.size(), listener.getTextStrings().size());
    for (int i = 0; i < testText.size(); i++) {
      assertEquals(testText.get(i), listener.getTextStrings().get(i));
    }

    assertEquals(testScript.size(), listener.getInlineScriptStrings().size());
    for (int i = 0; i < testScript.size(); i++) {
      assertEquals(
          testScript.get(i).replaceAll("\\s+", ""), listener.getInlineScriptStrings().get(i));
    }
  }

  @Test
  @DisplayName("Simple Script Module test.")
  void parseSimpleScriptParse() {

    // Create a lexer that feeds off of input CharStream
    MTScript2Lexer lexer =
        new MTScript2Lexer(
            CharStreams.fromString(
                "module my_module 1.4 'test mod'; uses other_mod 1.5; integer a = 43; number b = 4;"));

    // TODO need to fix this...
    lexer.parsingModule = true;
    // create a buffer of tokens pulled from the lexer
    CommonTokenStream tokens = new CommonTokenStream(lexer);

    // create a parser that feeds off the tokens buffer
    MTScript2Parser parser = new MTScript2Parser(tokens);

    ParseTree parseTree = parser.scriptModule();
    ParseTreeWalker walker = new ParseTreeWalker();

    MTScript2TestListener listener = new MTScript2TestListener();

    walker.walk(listener, parseTree);

    assertEquals("my_module", listener.getModuleName());
    assertEquals("1.4", listener.getModuleVersion());
    assertEquals("'test mod'", listener.getModuleDescription());
  }

  @Test
  @DisplayName("Test 'module' is not picked up incorrectly.")
  void parseSModuleInChatTest() {
    final List<String> testText = List.of("module This is a test! ", ", test one two");
    final List<String> testRolls = List.of("1d6", "2d10");

    // Create a lexer that feeds off of input CharStream
    MTScript2Lexer lexer =
        new MTScript2Lexer(
            CharStreams.fromString(
                testText.get(0)
                    + "[["
                    + testRolls.get(0)
                    + "]]"
                    + testText.get(1)
                    + "[["
                    + testRolls.get(1)
                    + "]]"));

    // create a buffer of tokens pulled from the lexer
    CommonTokenStream tokens = new CommonTokenStream(lexer);

    // create a parser that feeds off the tokens buffer
    MTScript2Parser parser = new MTScript2Parser(tokens);

    ParseTree parseTree = parser.chat();
    ParseTreeWalker walker = new ParseTreeWalker();

    MTScript2TestListener listener = new MTScript2TestListener();

    walker.walk(listener, parseTree);

    assertEquals(testText.size(), listener.getTextStrings().size());
    for (int i = 0; i < testText.size(); i++) {
      assertEquals(testText.get(i), listener.getTextStrings().get(i));
    }

    assertEquals(testRolls.size(), listener.getInlineRollStrings().size());
    for (int i = 0; i < testRolls.size(); i++) {
      assertEquals(testRolls.get(i), listener.getInlineRollStrings().get(i));
    }
  }
}
