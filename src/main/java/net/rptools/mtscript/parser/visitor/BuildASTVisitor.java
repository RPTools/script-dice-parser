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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.text.StringEscapeUtils;

import net.rptools.mtscript.ast.ASTNode;
import net.rptools.mtscript.ast.ArrayInitializerNode;
import net.rptools.mtscript.ast.AssertNode;
import net.rptools.mtscript.ast.AssignmentNode;
import net.rptools.mtscript.ast.BlockNode;
import net.rptools.mtscript.ast.BlockStatementNode;
import net.rptools.mtscript.ast.BooleanExpressionNode;
import net.rptools.mtscript.ast.BooleanLiteralNode;
import net.rptools.mtscript.ast.BreakNode;
import net.rptools.mtscript.ast.ChatNode;
import net.rptools.mtscript.ast.ConstantDeclarationNode;
import net.rptools.mtscript.ast.ContinueNode;
import net.rptools.mtscript.ast.DeclarationNode;
import net.rptools.mtscript.ast.ExportNode;
import net.rptools.mtscript.ast.ExpressionNode;
import net.rptools.mtscript.ast.FieldDeclarationNode;
import net.rptools.mtscript.ast.ForNode;
import net.rptools.mtscript.ast.ForNode.ForControl;
import net.rptools.mtscript.ast.IfNode;
import net.rptools.mtscript.ast.ImportNode;
import net.rptools.mtscript.ast.IntegerLiteralNode;
import net.rptools.mtscript.ast.MethodCallNode;
import net.rptools.mtscript.ast.MethodDeclarationNode;
import net.rptools.mtscript.ast.ModuleNode;
import net.rptools.mtscript.ast.NoopNode;
import net.rptools.mtscript.ast.NullLiteralNode;
import net.rptools.mtscript.ast.NumberLiteralNode;
import net.rptools.mtscript.ast.ScriptNode;
import net.rptools.mtscript.ast.StringLiteralNode;
import net.rptools.mtscript.ast.TextNode;
import net.rptools.mtscript.ast.Type;
import net.rptools.mtscript.ast.VariableDeclaratorNode;
import net.rptools.mtscript.ast.VariableDeclaratorIdNode;
import net.rptools.mtscript.ast.VariableInitializerNode;
import net.rptools.mtscript.ast.VariableNode;
import net.rptools.mtscript.parser.MTScript2Parser;
import net.rptools.mtscript.parser.MTScript2ParserBaseVisitor;
import net.rptools.mtscript.parser.MTScript2ParserVisitor;

/**
 * This class provides a {@link MTScript2ParserVisitor} which creates an
 * abstract syntax tree which can be used for further processing.
 */
public class BuildASTVisitor extends MTScript2ParserBaseVisitor<ASTNode> {
    /**
     * {@inheritDoc}
     *
     * <p>
     * Main entry point for this visitor. Returns a {@link ChatNode}.
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
     * Returns a {@link ScriptNode}.
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
     * Returns a {@TextNode} containing the text to display.
     */
    @Override
    public ASTNode visitText(MTScript2Parser.TextContext ctx) {
        return new TextNode(ctx.getText());
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Returns an {@link AssignmentNode} for assigning things to variables.
     * </p>
     */
    @Override
    public ASTNode visitAssignment(MTScript2Parser.AssignmentContext ctx) {
        VariableNode variable = VariableNode.class.cast(ctx.variable().accept(this));
        ExpressionNode expression = ExpressionNode.class.cast(ctx.right.accept(this));
        return new AssignmentNode(variable, expression);
    }

    /**
     * {@inheritDoc}
     *
     * Returns a variable node. For using variables.
     */
    @Override
    public ASTNode visitVariable(MTScript2Parser.VariableContext ctx) {
        VariableNode variableNode = VariableNode.fromName(ctx.getText());
        return variableNode;
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
     * Entry point for a script module.
     * </p>
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

        List<DeclarationNode> declarationNodes = ctx.scriptModuleBody().stream().map(n -> {
            if (n.constantDeclaration() != null) {
                return ConstantDeclarationNode.class.cast(n.constantDeclaration().accept(this));
            } else if (n.fieldDeclaration() != null) {
                return FieldDeclarationNode.class.cast(n.fieldDeclaration().accept(this));
            } else if (n.methodDeclaration() != null) {
                return MethodDeclarationNode.class.cast(n.methodDeclaration().accept(this));
            } else {
                // Unsupported declaration!
                throw new IllegalStateException("Unknown node type found in module body");
            }
        }).collect(Collectors.toList());

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
     * Do not use.
     *
     * {@inheritDoc}
     */
    @Override
    public ASTNode visitScriptModuleDefinition(MTScript2Parser.ScriptModuleDefinitionContext ctx) {
        throw new UnsupportedOperationException("Should not hit this method directly");
    }

    /**
     * Do not use.
     *
     * {@inheritDoc}
     */
    @Override
    public ASTNode visitScriptImports(MTScript2Parser.ScriptImportsContext ctx) {
        throw new UnsupportedOperationException("Should not hit this method directly");
    }

    /**
     * Do not use.
     *
     * {@inheritDoc}
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
     * Do not use.
     *
     * {@inheritDoc}
     */
    @Override
    public ASTNode visitScriptVersion(MTScript2Parser.ScriptVersionContext ctx) {
        throw new UnsupportedOperationException("Should not hit this method directly");
    }

    /**
     * Do not use.
     * {@inheritDoc}
     */
    @Override
    public ASTNode visitScriptExports(MTScript2Parser.ScriptExportsContext ctx) {
        throw new UnsupportedOperationException("Should not hit this method directly");
    }

    /**
     * Do not use.
     *
     * {@inheritDoc}
     */
    @Override
    public ASTNode visitExported(MTScript2Parser.ExportedContext ctx) {
        throw new UnsupportedOperationException("Should not hit this method directly");
    }

    /**
     * Do not use.
     *
     * {@inheritDoc}
     */
    @Override
    public ASTNode visitExportDest(MTScript2Parser.ExportDestContext ctx) {
        throw new UnsupportedOperationException("Should not hit this method directly");
    }

    /**
     * Do not use.
     *
     * {@inheritDoc}
     */
    @Override
    public ASTNode visitScriptBody(MTScript2Parser.ScriptBodyContext ctx) {
        throw new UnsupportedOperationException("Should not hit visitScriptBody directly");
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Returns the correct type of literal node, containing the value.
     * </p>
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
     * Do not use.
     *
     * {@inheritDoc}
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
        String name = ctx.IDENTIFIER().getText();

        Set<MethodDeclarationNode.MethodParameter> parameters;
        MTScript2Parser.FormalParameterListContext formalParameterList = ctx.formalParameters().formalParameterList();
        if (formalParameterList == null) {
            parameters = Collections.emptySet();
        } else {
            parameters = formalParameterList.formalParameter().stream().map(n -> {
                Type type = Type.valueOf(n.type().t.getText());
                VariableDeclaratorIdNode id =
                  VariableDeclaratorIdNode.class.cast(n.variableDeclaratorId().accept(this));
                return new MethodDeclarationNode.MethodParameter(type, id);
            }).collect(Collectors.toSet());
        }

        BlockNode block = BlockNode.class.cast(ctx.block().accept(this));

        return new MethodDeclarationNode(name, parameters, block);
        // TODO AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
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
        List<BlockStatementNode> children = ctx.children.stream()
          .map(c -> c.accept(this))
          .map(n -> BlockStatementNode.class.cast(n))
          .collect(Collectors.toList());
        return new BlockNode(children);
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
        // TODO The rest of this...
        if (ctx.KEYWORD_ASSERT() != null) {
            try {
                ExpressionNode conditional = ExpressionNode.class.cast(
                        ctx.expression(0).accept(this));
                ExpressionNode value = null;
                if (ctx.expression().size() == 2) {
                    value = ExpressionNode.class.cast(ctx.expression(1).accept(this));
                }
                return new AssertNode(conditional, value);
            } catch (ClassCastException e) {
                throw new IllegalStateException("Expected expression node", e);
            }
        } else if (ctx.KEYWORD_IF() != null && ctx.KEYWORD_IF().size() > 0) {
            ArrayList<IfNode.ConditionalPair> conditionalPairs = new ArrayList<>();
            ASTNode elseNode = null;

            for (int x = 0; x < ctx.parExpression().size(); x ++) {
                try {
                    BooleanExpressionNode condition = BooleanExpressionNode.class.cast(
                            ctx.parExpression().get(x).accept(this));
                    ASTNode block = ctx.block().get(x).accept(this);
                    conditionalPairs.add(new IfNode.ConditionalPair(condition, block));
                } catch (ClassCastException e) {
                    throw new IllegalStateException("Error casting node", e);
                }
            }

            // Collect the else block, if there is one.
            if (ctx.block().size() > ctx.parExpression().size()) {
                elseNode = ctx.block().get(ctx.block().size()-1).accept(this);
            }

            return new IfNode(conditionalPairs, elseNode);

        } else if (ctx.KEYWORD_FOR() != null) {
            ForControl control = null;
            if (ctx.forControl() != null) {
                control = ForControl.class.cast(ctx.forControl().accept(this));
            }

            ASTNode body = ctx.block().get(0).accept(this);

            return new ForNode(control, body);
        } else if (ctx.KEYWORD_DO() != null) {
        } else if (ctx.KEYWORD_WHILE() != null) {
        } else if (ctx.KEYWORD_TRY() != null) {
        } else if (ctx.KEYWORD_SWITCH() != null) {
        } else if (ctx.KEYWORD_RETURN() != null) {
        } else if (ctx.KEYWORD_THROW() != null) {
        } else if (ctx.KEYWORD_BREAK() != null) {
            return new BreakNode();
        } else if (ctx.KEYWORD_CONTINUE() != null) {
            return new ContinueNode();
        } else if (ctx.statementExpression != null) {
            return ctx.statementExpression.accept(this);
        } else if (ctx.SEMI() != null) {
            // Just a semicolon, nothing else.
            return new NoopNode();
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
        // TODO Write me
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
     * Do not use.
     *
     * {@inheritDoc}
     */
    @Override
    public ASTNode visitExpressionList(MTScript2Parser.ExpressionListContext ctx) {
        throw new UnsupportedOperationException("Should not hit visitExpressionList");
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Returns a MethodCallNode. For calling methods.
     * </p>
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
     * Returns a {@link FieldDeclarationNode}. For declaring fields.
     * </p>
     * ctx}.
     */
    @Override
    public ASTNode visitFieldDeclaration(MTScript2Parser.FieldDeclarationContext ctx) {
        Type type = Type.valueOf(ctx.type().t.getText());
        Set<VariableDeclaratorNode> vars = ctx.variableDeclarators().variableDeclarator().stream()
                .map(n -> n.accept(this)).map(VariableDeclaratorNode.class::cast).collect(Collectors.toSet());
        return new FieldDeclarationNode(type, vars);
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Returns a {@link ConstantDeclarationNode}. For declaring Constants.
     */
    @Override
    public ASTNode visitConstantDeclaration(MTScript2Parser.ConstantDeclarationContext ctx) {
        // FIXME Support arrays
        Type type = Type.valueOf(ctx.type().t.getText());
        Set<Map.Entry<String, VariableInitializerNode>> consts = ctx.constantDeclarator().stream().map(n -> {
            String name = n.IDENTIFIER().getText();

            VariableInitializerNode initializer = null;
            try {
                initializer = VariableInitializerNode.class.cast(n.variableInitializer().accept(this));
            } catch (ClassCastException e) {
                throw new IllegalArgumentException("Found non-variable initializer node where we expected one", e);
            }
            return Map.entry(name, initializer);
        }).collect(Collectors.toSet());
        Map<String, VariableInitializerNode> constMap = Map.ofEntries(consts.toArray(new Map.Entry[0]));
        return new ConstantDeclarationNode(type, constMap);
    }

    /**
     * Do not use.
     *
     * {@inheritDoc}
     */
    @Override
    public ASTNode visitConstantDeclarator(MTScript2Parser.ConstantDeclaratorContext ctx) {
        throw new UnsupportedOperationException("Do not hit this method directly!");
    }

    /**
     * Do not use.
     *
     * {@inheritDoc}
     */
    @Override
    public ASTNode visitVariableDeclarators(MTScript2Parser.VariableDeclaratorsContext ctx) {
        throw new UnsupportedOperationException("Should not hit visitVariableDeclarators directly");
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Returns a {@link VariableDeclaratorNode}. For declaring variables.
     */
    @Override
    public ASTNode visitVariableDeclarator(MTScript2Parser.VariableDeclaratorContext ctx) {
      VariableDeclaratorIdNode id = VariableDeclaratorIdNode.class.cast(
          ctx.variableDeclaratorId().accept(this));

        VariableInitializerNode initializer = null;
        if (ctx.variableInitializer() != null) {
            try {
                initializer = VariableInitializerNode.class.cast(ctx.variableInitializer().accept(this));
            } catch (ClassCastException e) {
                throw new IllegalArgumentException("Found non-variable initializer node where we expected one", e);
            }
        }

        return new VariableDeclaratorNode(id.getVariable(), id.getArrayDepth(), initializer);
    }

    /**
     * Used in other places
     *
     * {@inheritDoc}
     */
    @Override
    public ASTNode visitVariableDeclaratorId(MTScript2Parser.VariableDeclaratorIdContext ctx) {
      // $thing[]
      String name = ctx.scope.getText() + ctx.IDENTIFIER().getText();
      VariableNode variable = VariableNode.fromName(name);
      int arrayDepth = ctx.LBRACK().size();
      return new VariableDeclaratorIdNode(variable, arrayDepth);
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
     * Do not use.
     *
     * {@inheritDoc}
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
        throw new UnsupportedOperationException("Do not call visitType directly");
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
