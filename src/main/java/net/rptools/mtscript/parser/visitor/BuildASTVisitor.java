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

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import net.rptools.mtscript.ast.ASTNode;
import net.rptools.mtscript.ast.BlockNode;
import net.rptools.mtscript.ast.BlockStatementNode;
import net.rptools.mtscript.ast.ChatNode;
import net.rptools.mtscript.ast.DeclarationNode;
import net.rptools.mtscript.ast.ExportNode;
import net.rptools.mtscript.ast.ImportNode;
import net.rptools.mtscript.ast.LiteralNode;
import net.rptools.mtscript.ast.ScriptModuleNode;
import net.rptools.mtscript.ast.ScriptNode;
import net.rptools.mtscript.ast.TextNode;
import net.rptools.mtscript.ast.VariableNode;
import net.rptools.mtscript.ast.VariableNode.Scope;
import net.rptools.mtscript.parser.MTScript2Lexer;
import net.rptools.mtscript.parser.MTScript2Parser;
import net.rptools.mtscript.parser.MTScript2ParserBaseVisitor;
import net.rptools.mtscript.parser.MTScript2ParserVisitor;
import org.apache.commons.text.StringEscapeUtils;

/**
 * This class provides a visitor for conversion of {@link ParseTree} into a proper AST Tree for
 * later interpreting.
 */
public class BuildASTVisitor extends MTScript2ParserBaseVisitor<ASTNode>
    implements MTScript2ParserVisitor<ASTNode> {

  /** Entry point for input from chat. */
  @Override
  public ASTNode visitChat(MTScript2Parser.ChatContext ctx) {
    List<ASTNode> children =
        ctx.children.stream().map(c -> c.accept(this)).collect(Collectors.toList());
    return new ChatNode(children);
  }

  /** Entry point for macros. */
  @Override
  public ASTNode visitScript(MTScript2Parser.ScriptContext ctx) {
    if (ctx.scriptBody().getChildCount() == 0) {
      return new ScriptNode(Collections.emptyList());
    }
    List<ASTNode> children =
        ctx.scriptBody().children.stream().map(c -> c.accept(this)).collect(Collectors.toList());
    return new ScriptNode(children);
  }

  /** Entry point for modules. */
  @Override
  public ASTNode visitScriptModule(MTScript2Parser.ScriptModuleContext ctx) {
    MTScript2Parser.ScriptModuleDefinitionContext definition = ctx.scriptModuleDefinition();
    System.out.println(definition.version.getStart());
    System.out.println(definition.version.getText());

    String name = definition.name.getText();
    String version = definition.version.getText();
    String description = parseStringLiteral(definition.desc.getText());

    List<ImportNode> imports =
        ctx.scriptImports().stream()
            .map(
                u ->
                    new ImportNode(
                        u.name.getText(),
                        u.semverVersion().getText(),
                        u.as != null ? u.as.getText() : null))
            .collect(Collectors.toList());

    List<DeclarationNode> declarationNodes =
        ctx.scriptModuleBody().stream()
            .map(
                n -> {
                  return DeclarationNode.class.cast(n.accept(this));
                })
            .collect(Collectors.toList());

    List<ExportNode.Export> exports =
        ctx.scriptExports().stream()
            .flatMap(
                n -> {
                  return n.exported().stream()
                      .map(
                          e -> {
                            // TODO Implement as
                            return new ExportNode.Export(e.getText());
                          });
                })
            .collect(Collectors.toList());

    ExportNode exportNode = new ExportNode(exports);

    return new ScriptModuleNode(name, version, description, imports, declarationNodes, exportNode);
  }

  /** Node for holding plain text. */
  @Override
  public ASTNode visitText(MTScript2Parser.TextContext ctx) {
    return new TextNode(ctx.getText());
  }

  /** Node to hold a variable when it is being **called or used.** */
  @Override
  public ASTNode visitVariable(MTScript2Parser.VariableContext ctx) {
    switch (ctx.scope.getTokenIndex()) {
      case MTScript2Lexer.LOCAL_VAR_LEADER:
        return new VariableNode(Scope.LOCAL, ctx.IDENTIFIER().getText());
      case MTScript2Lexer.GLOBAL_VAR_LEADER:
        return new VariableNode(Scope.GLOBAL, ctx.IDENTIFIER().getText());
      case MTScript2Lexer.PROPERTY_VAR_LEADER:
        return new VariableNode(Scope.PROPERTY, ctx.IDENTIFIER().getText());
      default:
        throw new IllegalStateException("Unknown variable scope encountered!");
    }
  }

  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitParenGroup(MTScript2Parser.ParenGroupContext ctx) {
    return visitChildren(ctx);
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitBraceGroup(MTScript2Parser.BraceGroupContext ctx) {
    return visitChildren(ctx);
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitDice(MTScript2Parser.DiceContext ctx) {
    return visitChildren(ctx);
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitNumDice(MTScript2Parser.NumDiceContext ctx) {
    return visitChildren(ctx);
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitDiceSides(MTScript2Parser.DiceSidesContext ctx) {
    return visitChildren(ctx);
  }

  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitDiceArguments(MTScript2Parser.DiceArgumentsContext ctx) {
    return visitChildren(ctx);
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitDiceArgumentList(MTScript2Parser.DiceArgumentListContext ctx) {
    return visitChildren(ctx);
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitDiceArgument(MTScript2Parser.DiceArgumentContext ctx) {
    return visitChildren(ctx);
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitDargIdentifier(MTScript2Parser.DargIdentifierContext ctx) {
    return visitChildren(ctx);
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitDargVariable(MTScript2Parser.DargVariableContext ctx) {
    return visitChildren(ctx);
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitDargString(MTScript2Parser.DargStringContext ctx) {
    return visitChildren(ctx);
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitDargInteger(MTScript2Parser.DargIntegerContext ctx) {
    return visitChildren(ctx);
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitDargDouble(MTScript2Parser.DargDoubleContext ctx) {
    return visitChildren(ctx);
  }

  /** DO NOT USE. */
  @Override
  public ASTNode visitScriptModuleDefinition(MTScript2Parser.ScriptModuleDefinitionContext ctx) {
    throw new IllegalStateException("This should not be hit directly.");
  }

  /** DO NOT USE. */
  @Override
  public ASTNode visitScriptImports(MTScript2Parser.ScriptImportsContext ctx) {
    throw new IllegalStateException("This should not be hit directly.");
  }

  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitSemverVersion(MTScript2Parser.SemverVersionContext ctx) {
    throw new IllegalStateException("This should not be hit directly.");
  }

  /** Do not use. */
  @Override
  public ASTNode visitScriptExports(MTScript2Parser.ScriptExportsContext ctx) {
    throw new IllegalStateException("Node not directly visitable");
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitExported(MTScript2Parser.ExportedContext ctx) {
    return visitChildren(ctx);
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitExportDest(MTScript2Parser.ExportDestContext ctx) {
    return visitChildren(ctx);
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitScriptBody(MTScript2Parser.ScriptBodyContext ctx) {
    return visitChildren(ctx);
  }

  /** Integer literal */
  @Override
  public ASTNode visitLiteralInteger(MTScript2Parser.LiteralIntegerContext ctx) {
    MTScript2Parser.IntegerLiteralContext intCtx = ctx.integerLiteral();
    Integer x = null;
    if (intCtx.DECIMAL_LITERAL() != null) {
      x = Integer.parseInt(intCtx.getText());
    } else if (intCtx.HEX_LITERAL() != null) {
      x = Integer.parseInt(intCtx.getText().substring(2), 16);
    }

    if (x == null) {
      // Ummmmm.... Wut?
      throw new IllegalStateException("Encountered an integer literal that I didn't understand!");
    }
    return new LiteralNode.IntegerLiteralNode(x);
  }

  @Override
  public ASTNode visitLiteralNumber(MTScript2Parser.LiteralNumberContext ctx) {
    return new LiteralNode.NumberLiteralNode(Double.parseDouble(ctx.getText()));
  }

  @Override
  public ASTNode visitLiteralString(MTScript2Parser.LiteralStringContext ctx) {
    String literal = ctx.getText();
    return new LiteralNode.StringLiteralNode(parseStringLiteral(literal));
  }

  @Override
  public ASTNode visitLiteralBool(MTScript2Parser.LiteralBoolContext ctx) {
    return new LiteralNode.BooleanLiteralNode(Boolean.parseBoolean(ctx.getText()));
  }

  @Override
  public ASTNode visitLiteralNull(MTScript2Parser.LiteralNullContext ctx) {
    return new LiteralNode.NullLiteralNode();
  }

  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitIntegerLiteral(MTScript2Parser.IntegerLiteralContext ctx) {
    return visitChildren(ctx);
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitMethodDeclaration(MTScript2Parser.MethodDeclarationContext ctx) {
    return visitChildren(ctx);
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitFormalParameters(MTScript2Parser.FormalParametersContext ctx) {
    return visitChildren(ctx);
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitFormalParameterList(MTScript2Parser.FormalParameterListContext ctx) {
    return visitChildren(ctx);
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitFormalParameter(MTScript2Parser.FormalParameterContext ctx) {
    return visitChildren(ctx);
  }
  /** Contains a block of statements. */
  @Override
  public ASTNode visitBlock(MTScript2Parser.BlockContext ctx) {
    List<BlockStatementNode> children =
        ctx.blockStatement().stream()
            .map(c -> c.accept(this))
            .map(c -> BlockStatementNode.class.cast(c))
            .collect(Collectors.toList());
    return new BlockNode(children);
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitBlockStatement(MTScript2Parser.BlockStatementContext ctx) {
    return visitChildren(ctx);
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitLocalVariableDeclaration(
      MTScript2Parser.LocalVariableDeclarationContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public ASTNode visitStmtBlock(MTScript2Parser.StmtBlockContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public ASTNode visitStmtAssert(MTScript2Parser.StmtAssertContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public ASTNode visitStmtIf(MTScript2Parser.StmtIfContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public ASTNode visitStmtFor(MTScript2Parser.StmtForContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public ASTNode visitStmtWhile(MTScript2Parser.StmtWhileContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public ASTNode visitStmtDoWhile(MTScript2Parser.StmtDoWhileContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public ASTNode visitStmtTry(MTScript2Parser.StmtTryContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public ASTNode visitStmtSwitch(MTScript2Parser.StmtSwitchContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public ASTNode visitStmtReturn(MTScript2Parser.StmtReturnContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public ASTNode visitStmtThrow(MTScript2Parser.StmtThrowContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public ASTNode visitStmtBreak(MTScript2Parser.StmtBreakContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public ASTNode visitStmtContinue(MTScript2Parser.StmtContinueContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public ASTNode visitStmtSemi(MTScript2Parser.StmtSemiContext ctx) {
    return visitChildren(ctx);
  }

  @Override
  public ASTNode visitStmtExpr(MTScript2Parser.StmtExprContext ctx) {
    return visitChildren(ctx);
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitCatchClause(MTScript2Parser.CatchClauseContext ctx) {
    return visitChildren(ctx);
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitFinallyBlock(MTScript2Parser.FinallyBlockContext ctx) {
    return visitChildren(ctx);
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitSwitchBlockStatementGroup(
      MTScript2Parser.SwitchBlockStatementGroupContext ctx) {
    return visitChildren(ctx);
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitSwitchLabel(MTScript2Parser.SwitchLabelContext ctx) {
    return visitChildren(ctx);
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitForControlBasic(MTScript2Parser.ForControlBasicContext ctx) {
    return visitChildren(ctx);
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitForInit(MTScript2Parser.ForInitContext ctx) {
    return visitChildren(ctx);
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitForControlForeach(MTScript2Parser.ForControlForeachContext ctx) {
    return visitChildren(ctx);
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitParExpression(MTScript2Parser.ParExpressionContext ctx) {
    return visitChildren(ctx);
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitExpressionList(MTScript2Parser.ExpressionListContext ctx) {
    return visitChildren(ctx);
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitExprMethodCall(MTScript2Parser.ExprMethodCallContext ctx) {
    return visitChildren(ctx);
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitExpression(MTScript2Parser.ExpressionContext ctx) {
    return visitChildren(ctx);
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitExternalProperty(MTScript2Parser.ExternalPropertyContext ctx) {
    return visitChildren(ctx);
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitExternalPropertyName(MTScript2Parser.ExternalPropertyNameContext ctx) {
    return visitChildren(ctx);
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitFieldDeclaration(MTScript2Parser.FieldDeclarationContext ctx) {
    return visitChildren(ctx);
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitConstantDeclaration(MTScript2Parser.ConstantDeclarationContext ctx) {
    return visitChildren(ctx);
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitConstantDeclarator(MTScript2Parser.ConstantDeclaratorContext ctx) {
    return visitChildren(ctx);
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitVariableDeclarators(MTScript2Parser.VariableDeclaratorsContext ctx) {
    return visitChildren(ctx);
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitVariableDeclarator(MTScript2Parser.VariableDeclaratorContext ctx) {
    return visitChildren(ctx);
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitVariableDeclaratorId(MTScript2Parser.VariableDeclaratorIdContext ctx) {
    return visitChildren(ctx);
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitVariableInitializer(MTScript2Parser.VariableInitializerContext ctx) {
    return visitChildren(ctx);
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitArrayInitializer(MTScript2Parser.ArrayInitializerContext ctx) {
    return visitChildren(ctx);
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitArguments(MTScript2Parser.ArgumentsContext ctx) {
    return visitChildren(ctx);
  }
  /**
   * {@inheritDoc}
   *
   * <p>The default implementation returns the result of calling {@link #visitChildren} on {@code
   * ctx}.
   */
  @Override
  public ASTNode visitType(MTScript2Parser.TypeContext ctx) {
    return visitChildren(ctx);
  }

  private String parseStringLiteral(String literal) {
    // Trim off the leading and trailing double quotes
    // TODO Is this going to handle single quotes properly? Write a test!
    String str = literal.subSequence(1, literal.length() - 1).toString();
    // Unescape the rest
    // TODO Should we use unescapeEcmaScript instead here?
    return StringEscapeUtils.unescapeJava(str);
  }
}
