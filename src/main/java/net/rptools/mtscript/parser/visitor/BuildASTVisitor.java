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

import com.google.inject.Inject;
import net.rptools.mtscript.ast.ASTAttributeKey;
import net.rptools.mtscript.ast.ASTNode;
import net.rptools.mtscript.ast.ASTNodeFactory;
import net.rptools.mtscript.ast.ASTNodeType;
import net.rptools.mtscript.parser.MTScript2Parser.ChatContext;
import net.rptools.mtscript.parser.MTScript2Parser.ScriptBodyContext;
import net.rptools.mtscript.parser.MTScript2Parser.ScriptContext;
import net.rptools.mtscript.parser.MTScript2Parser.TextContext;
import net.rptools.mtscript.parser.MTScript2ParserBaseVisitor;
import net.rptools.mtscript.parser.MTScript2ParserVisitor;
import net.rptools.mtscript.symboltable.SymbolTableAttributeKey;
import net.rptools.mtscript.symboltable.SymbolTableEntry;
import net.rptools.mtscript.symboltable.SymbolTableStack;
import net.rptools.mtscript.util.MTScriptConstants;

/**
 * This class provides a visitor for conversion of {@code ParseTree} into a proper AST Tree for
 * later interpreting.
 */
public class BuildASTVisitor extends MTScript2ParserBaseVisitor<ASTNode>
    implements MTScript2ParserVisitor<ASTNode> {

  /** The symbol table stack built up during parsing. */
  private final SymbolTableStack symbolTableStack;

  /** The constants used through out the parser. */
  private final MTScriptConstants constants;

  /** Tge factory used to create {@link ASTNode}s. */
  private final ASTNodeFactory astNodeFactory;

  private final String CHAT_SYMBOL_NAME_PART = "chat";

  private final String CHAT_SYMBOL_NAME;

  /**
   * Creates a new {@code BuildASTVisitor}.
   *
   * @param symbolTableStack the symbol table stack used for the AST.
   * @param constants constants used through out the parsing / execution process.
   */
  @Inject
  public BuildASTVisitor(
      SymbolTableStack symbolTableStack,
      ASTNodeFactory astNodeFactory,
      MTScriptConstants constants) {
    this.symbolTableStack = symbolTableStack;
    this.astNodeFactory = astNodeFactory;
    this.constants = constants;

    CHAT_SYMBOL_NAME = constants.getInternalSymbolPrefix() + CHAT_SYMBOL_NAME_PART;
  }

  @Override
  public ASTNode visitChat(ChatContext ctx) {
    SymbolTableEntry symbolTableEntry = symbolTableStack.create(CHAT_SYMBOL_NAME);
    ASTNode astNode = astNodeFactory.create(ASTNodeType.CHAT);
    symbolTableEntry.setAttribute(SymbolTableAttributeKey.CODE_AST, astNode);

    ctx.children.forEach(
        c -> {
          astNode.addChild(visit(c));
        });

    return astNode;
  }

  @Override
  public ASTNode visitText(TextContext ctx) {
    ASTNode astNode = astNodeFactory.create(ASTNodeType.TEXT);
    astNode.setAttribute(ASTAttributeKey.VALUE, ctx.getText());
    return astNode;
  }

  @Override
  public ASTNode visitScript(ScriptContext ctx) {
    return visitScriptBody(ctx.scriptBody());
  }

  @Override
  public ASTNode visitScriptBody(ScriptBodyContext ctx) {

    return astNodeFactory.create(ASTNodeType.SCRIPT);
  }
}
