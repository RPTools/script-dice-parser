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

import com.google.inject.Guice;
import com.google.inject.Injector;
import net.rptools.mtscript.ast.ASTNode;
import net.rptools.mtscript.injection.ScriptModule;
import net.rptools.mtscript.parser.MTScript2Lexer;
import net.rptools.mtscript.parser.MTScript2Parser;
import net.rptools.mtscript.parser.visitor.BuildASTVisitor;
import net.rptools.mtscript.symboltable.SymbolTableStack;
import org.antlr.v4.runtime.ANTLRErrorStrategy;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * Temporary class for running the parser/
 */
public class MapToolScript {
  private final Injector injector;

  private SymbolTableStack symbolTableStack;

  public MapToolScript() {
    injector = Guice.createInjector(new ScriptModule());
  }

  public void parse(String script) {
    MTScript2Parser parser = createParser(script, false);
    ParseTree parseTree = parser.chat();
    symbolTableStack = injector.getInstance(SymbolTableStack.class);

    // Add Global Symbols here
    BuildASTVisitor visitor = new BuildASTVisitor(symbolTableStack);
    ASTNode root = parseTree.accept(visitor);

  }

  private MTScript2Parser createParser(String script, boolean parsingModule) {
    MTScript2Lexer lexer = new MTScript2Lexer(CharStreams.fromString(script));
    lexer.parsingModule = parsingModule;
    CommonTokenStream tokenStream = new CommonTokenStream(lexer);
    MTScript2Parser parser = new MTScript2Parser(tokenStream);
    parser.setErrorHandler(injector.getInstance(ANTLRErrorStrategy.class));
    return parser;
  }
}
