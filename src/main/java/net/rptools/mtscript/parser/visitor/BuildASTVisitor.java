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
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.text.StringEscapeUtils;

import net.rptools.mtscript.ast.ASTNode;
import net.rptools.mtscript.ast.ArrayInitializerNode;
import net.rptools.mtscript.ast.AssignmentNode;
import net.rptools.mtscript.ast.BooleanLiteralNode;
import net.rptools.mtscript.ast.ChatNode;
import net.rptools.mtscript.ast.DeclarationNode;
import net.rptools.mtscript.ast.ExportNode;
import net.rptools.mtscript.ast.ExpressionNode;
import net.rptools.mtscript.ast.FieldDeclarationNode;
import net.rptools.mtscript.ast.ImportNode;
import net.rptools.mtscript.ast.IntegerLiteralNode;
import net.rptools.mtscript.ast.MethodCallNode;
import net.rptools.mtscript.ast.ModuleNode;
import net.rptools.mtscript.ast.NullLiteralNode;
import net.rptools.mtscript.ast.NumberLiteralNode;
import net.rptools.mtscript.ast.ScriptNode;
import net.rptools.mtscript.ast.StringLiteralNode;
import net.rptools.mtscript.ast.TextNode;
import net.rptools.mtscript.ast.Type;
import net.rptools.mtscript.ast.VariableDeclaratorNode;
import net.rptools.mtscript.ast.VariableInitializerNode;
import net.rptools.mtscript.ast.VariableNode;
import net.rptools.mtscript.parser.MTScript2Parser;
import net.rptools.mtscript.parser.MTScript2ParserBaseVisitor;
import net.rptools.mtscript.parser.MTScript2ParserVisitor;

/**
 * This class provides an empty implementation of
 * {@link MTScript2ParserVisitor}, which can be extended to create a visitor
 * which only needs to handle a subset of the available methods.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 *        operations with no return type.
 */
public class BuildASTVisitor extends MTScript2ParserBaseVisitor<ASTNode> {
    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitChat(MTScript2Parser.ChatContext ctx) {
        List<ASTNode> children = ctx.children.stream().map(c -> c.accept(this)).collect(Collectors.toList());
        return new ChatNode(children);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    // @Override
    // public ASTNode visitInlineRoll(MTScript2Parser.InlineRollContext ctx) {
    // try {
    // List<DiceExprNode> rollExprs = ctx.diceRolls().diceExprTopLevel().stream()
    // .map(d -> d.accept(this))
    // .map(DiceExprNode.class::cast)
    // .collect(Collectors.toList());
    // return new InlineRollNode(rollExprs);
    // } catch (ClassCastException e) {
    // throw new IllegalArgumentException("Unexpected non-DiceExprNode encountered",
    // e);
    // }
    // }
    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitScript(MTScript2Parser.ScriptContext ctx) {
        List<ASTNode> children = ctx.scriptBody().children.stream().map(c -> c.accept(this))
                .collect(Collectors.toList());
        return new ScriptNode(children);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitText(MTScript2Parser.TextContext ctx) {
        return new TextNode(ctx.getText());
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    // @Override
    // public ASTNode visitDiceExpr(MTScript2Parser.DiceExprContext ctx) {
    // return visitChildren(ctx);
    // }
    /**
     * {@inheritDoc}
     *
     * <p>
     * Roll assignment, for assigning roll results to variables
     * </p>
     */
    @Override
    public ASTNode visitAssignment(MTScript2Parser.AssignmentContext ctx) {
        VariableNode variable = VariableNode.class.cast(ctx.variable().accept(this));
        ExpressionNode expression = ExpressionNode.class.cast(ctx.right.accept(this));
        return new AssignmentNode(variable, expression);
    }

    @Override
    public ASTNode visitVariable(MTScript2Parser.VariableContext ctx) {
        if (ctx.getChildCount() != 1) {
            throw new IllegalStateException("Visited variable node without a singular child!");
        }
        return ctx.getChild(0).accept(this);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitExtPropName(MTScript2Parser.ExtPropNameContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitParenGroup(MTScript2Parser.ParenGroupContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitBraceGroup(MTScript2Parser.BraceGroupContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitDice(MTScript2Parser.DiceContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitNumDice(MTScript2Parser.NumDiceContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitDiceArguments(MTScript2Parser.DiceArgumentsContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitDiceArgumentList(MTScript2Parser.DiceArgumentListContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitDiceArgument(MTScript2Parser.DiceArgumentContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitDargIdentifier(MTScript2Parser.DargIdentifierContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitDargVariable(MTScript2Parser.DargVariableContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitDargString(MTScript2Parser.DargStringContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitDargInteger(MTScript2Parser.DargIntegerContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitDargDouble(MTScript2Parser.DargDoubleContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitScriptModule(MTScript2Parser.ScriptModuleContext ctx) {
        MTScript2Parser.ScriptModuleDefinitionContext definition = ctx.scriptModuleDefinition();

        List<ImportNode> imports = ctx.scriptImports().scriptUses().stream()
                .map(u -> new ImportNode(
                        u.name.getText(),
                        u.scriptVersion().getText(),
                        u.as != null ? u.as.getText() : null))
                .collect(Collectors.toList());

        List<DeclarationNode> declarationNodes = Collections.emptyList();
        ctx.scriptModuleBody().stream().forEach(n -> {
            System.err.println("WARNING Found script module body line, skipping!");
        });

        List<ExportNode> exports = ctx.scriptExports().stream()
                .flatMap(n -> {
                    return n.exported().stream()
                            .map(e -> new ExportNode(e.getText()));
                })
                .collect(Collectors.toList());

        return new ModuleNode(
                definition.name.getText(),
                definition.version.getText(),
                (definition.desc != null ? interpretStringLiteral(definition.desc.getText()) : null),
                imports,
                declarationNodes,
                exports);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitScriptModuleDefinition(MTScript2Parser.ScriptModuleDefinitionContext ctx) {
        throw new UnsupportedOperationException("Should not hit this method directly");
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitScriptImports(MTScript2Parser.ScriptImportsContext ctx) {
        throw new UnsupportedOperationException("Should not hit this method directly");
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitScriptUses(MTScript2Parser.ScriptUsesContext ctx) {
        throw new UnsupportedOperationException("Should not hit this method directly");
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitScriptModuleBody(MTScript2Parser.ScriptModuleBodyContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitScriptVersion(MTScript2Parser.ScriptVersionContext ctx) {
        throw new UnsupportedOperationException("Should not hit this method directly");
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitScriptExports(MTScript2Parser.ScriptExportsContext ctx) {
        throw new UnsupportedOperationException("Should not hit this method directly");
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitExported(MTScript2Parser.ExportedContext ctx) {
        throw new UnsupportedOperationException("Should not hit this method directly");
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitExportDest(MTScript2Parser.ExportDestContext ctx) {
        throw new UnsupportedOperationException("Should not hit this method directly");
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitScriptBody(MTScript2Parser.ScriptBodyContext ctx) {
        throw new UnsupportedOperationException("Should not hit visitScriptBody directly");
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitLiteral(MTScript2Parser.LiteralContext ctx) {
        if (ctx.STRING_LITERAL() != null) {
            // Trim off the leading and trailing doublequote.
            String str = ctx.STRING_LITERAL().getText();
            return new StringLiteralNode(interpretStringLiteral(str));
        } else if (ctx.BOOL_LITERAL() != null) {
            return new BooleanLiteralNode(Boolean.getBoolean(ctx.getText()));
        } else if (ctx.NUMBER_LITERAL() != null) {
            return new NumberLiteralNode(Double.valueOf(ctx.getText()));
        } else if (ctx.integerLiteral() != null) {
            return new IntegerLiteralNode(Integer.valueOf(ctx.getText()));
        } else if (ctx.NULL_LITERAL() != null) {
            return new NullLiteralNode();
        }
        throw new IllegalStateException("Unknown literal type encountered");
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitIntegerLiteral(MTScript2Parser.IntegerLiteralContext ctx) {
        throw new UnsupportedOperationException("visitIntegerLiteral should not be hit directly");
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitMethodDeclaration(MTScript2Parser.MethodDeclarationContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitFormalParameters(MTScript2Parser.FormalParametersContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitFormalParameterList(MTScript2Parser.FormalParameterListContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitFormalParameter(MTScript2Parser.FormalParameterContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitBlock(MTScript2Parser.BlockContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitBlockStatement(MTScript2Parser.BlockStatementContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitLocalVariableDeclaration(MTScript2Parser.LocalVariableDeclarationContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitStatement(MTScript2Parser.StatementContext ctx) {
        if (ctx.statementExpression != null) {
            return ctx.statementExpression.accept(this);
        }
        throw new UnsupportedOperationException("Not implemented, yet");
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitCatchClause(MTScript2Parser.CatchClauseContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitFinallyBlock(MTScript2Parser.FinallyBlockContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitSwitchBlockStatementGroup(MTScript2Parser.SwitchBlockStatementGroupContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitSwitchLabel(MTScript2Parser.SwitchLabelContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitForControl(MTScript2Parser.ForControlContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitForInit(MTScript2Parser.ForInitContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitForeachControl(MTScript2Parser.ForeachControlContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitParExpression(MTScript2Parser.ParExpressionContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitExpressionList(MTScript2Parser.ExpressionListContext ctx) {
        throw new UnsupportedOperationException("Should not hit visitExpressionList");
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitMethodCall(MTScript2Parser.MethodCallContext ctx) {
        String identifier = ctx.IDENTIFIER().getText();
        try {
            List<ExpressionNode> expressionList = ctx.expressionList().expression().stream().map(e -> e.accept(this))
                    .map(ExpressionNode.class::cast).collect(Collectors.toList());
            return new MethodCallNode(identifier, expressionList);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Found non-expression node where expression expected", e);
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitExpression(MTScript2Parser.ExpressionContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitExternalProperty(MTScript2Parser.ExternalPropertyContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitExternalPropertyName(MTScript2Parser.ExternalPropertyNameContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitFieldDeclaration(MTScript2Parser.FieldDeclarationContext ctx) {
        Type type = Type.valueOf(ctx.type().t.getText());
        Set<VariableDeclaratorNode> vars = ctx.variableDeclarators().variableDeclarator().stream()
                .map(d -> d.accept(this)).map(VariableDeclaratorNode.class::cast).collect(Collectors.toSet());
        return new FieldDeclarationNode(type, vars);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitConstantDeclaration(MTScript2Parser.ConstantDeclarationContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitConstantDeclarator(MTScript2Parser.ConstantDeclaratorContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitVariableDeclarators(MTScript2Parser.VariableDeclaratorsContext ctx) {
        throw new UnsupportedOperationException("Should not hit visitVariableDeclarators directly");
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitVariableDeclarator(MTScript2Parser.VariableDeclaratorContext ctx) {
        VariableNode variable = VariableNode.fromName(ctx.variableDeclaratorId().getText());

        VariableInitializerNode initializer = null;
        if (ctx.variableInitializer() != null) {
            try {
                initializer = VariableInitializerNode.class.cast(ctx.variableInitializer().accept(this));
            } catch (ClassCastException e) {
                throw new IllegalArgumentException("Found non-variable initializer node where we expected one", e);
            }
        }

        return new VariableDeclaratorNode(variable, initializer);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitVariableDeclaratorId(MTScript2Parser.VariableDeclaratorIdContext ctx) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitVariableInitializer(MTScript2Parser.VariableInitializerContext ctx) {
        if (ctx.arrayInitializer() != null) {
            try {
                List<VariableInitializerNode> initializers = ctx.arrayInitializer().variableInitializer().stream()
                        .map(n -> n.accept(this)).map(VariableInitializerNode.class::cast).collect(Collectors.toList());
                return new ArrayInitializerNode(initializers);
            } catch (ClassCastException e) {
                throw new IllegalArgumentException("Found non variable initializer where initializer was expected", e);
            }
        } else {
            return ExpressionNode.class.cast(ctx.expression().accept(this));
        }
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitArrayInitializer(MTScript2Parser.ArrayInitializerContext ctx) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitArguments(MTScript2Parser.ArgumentsContext ctx) {
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * The default implementation returns the result of calling
     * {@link #visitChildren} on {@code
     * ctx}.
     */
    @Override
    public ASTNode visitType(MTScript2Parser.TypeContext ctx) {
        return visitChildren(ctx);
    }

    private String interpretStringLiteral(String literal) {
        // Trim off the leading and trailing double quotes
        String str = literal.subSequence(1, literal.length() - 1).toString();
        // Unescape the rest
        // TODO Should we use unescapeEcmaScript instead here?
        str = StringEscapeUtils.unescapeJava(str);
        return str;
    }
}
