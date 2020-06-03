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
import net.rptools.mtscript.ast.ASTNodeFactory;
import net.rptools.mtscript.injection.ScriptModule;
import net.rptools.mtscript.parser.MTScript2Lexer;
import net.rptools.mtscript.parser.MTScript2Parser;
import net.rptools.mtscript.parser.visitor.BuildASTVisitor;
import net.rptools.mtscript.symboltable.SymbolTableEntry;
import net.rptools.mtscript.symboltable.SymbolTableStack;
import net.rptools.mtscript.types.MTSType;
import net.rptools.mtscript.types.MTSTypeDefinition;
import net.rptools.mtscript.types.MTSTypeFactory;
import net.rptools.mtscript.types.PredefinedType;
import net.rptools.mtscript.util.MTScriptConstants;
import net.rptools.mtscript.util.SymbolTablePrinter;
import org.antlr.v4.runtime.ANTLRErrorStrategy;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

/** Temporary class for running the parser/ */
public class MapToolScript {
  private final Injector injector;

  private final SymbolTableStack symbolTableStack;
  private final MTScriptConstants constants;
  private final MTSTypeFactory mtsTypeFactory;

  public MapToolScript() {
    injector = Guice.createInjector(new ScriptModule());
    symbolTableStack = injector.getInstance(SymbolTableStack.class);
    constants = injector.getInstance(MTScriptConstants.class);
    mtsTypeFactory = injector.getInstance(MTSTypeFactory.class);
  }

  public void parseScript(String script) {
    parse(script, false);
  }

  public void parseModule(String module) {
    parse(module, true);
  }

  private void parse(String script, boolean isModule) {
    createPredefinedTypes(symbolTableStack);
    symbolTableStack.push();
    MTScript2Parser parser = createParser(script, isModule);
    ParseTree parseTree = parser.chat();

    ASTNodeFactory astNodeFactory = injector.getInstance(ASTNodeFactory.class);
    BuildASTVisitor visitor =
        new BuildASTVisitor(symbolTableStack, astNodeFactory, mtsTypeFactory, constants);
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

  public void printSymbolTable() {
    SymbolTablePrinter printer = new SymbolTablePrinter();
    printer.printSymbolTableStack(symbolTableStack);
  }

  private void createPredefinedTypes(SymbolTableStack symbolTableStack) {
    for (PredefinedType pt : PredefinedType.values()) {
      SymbolTableEntry entry = symbolTableStack.create(pt.getName());
      entry.setTypeDefinition(MTSTypeDefinition.TYPE);
      MTSType mtsType = mtsTypeFactory.create(entry);
      entry.setType(mtsType);
    }
  }
}
