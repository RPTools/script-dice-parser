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
package net.rptools.mtscript.parser.visitor.impl;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import net.rptools.mtscript.ast.ASTAttributeKey;
import net.rptools.mtscript.ast.ASTNode;
import net.rptools.mtscript.ast.ASTNodeFactory;
import net.rptools.mtscript.ast.ASTNodeType;
import net.rptools.mtscript.parser.MTScript2Parser.ChatContext;
import net.rptools.mtscript.parser.MTScript2Parser.ScriptBodyContext;
import net.rptools.mtscript.parser.MTScript2Parser.ScriptContext;
import net.rptools.mtscript.parser.MTScript2Parser.TextContext;
import net.rptools.mtscript.parser.MTScript2ParserBaseVisitor;
import net.rptools.mtscript.parser.visitor.BuildASTVisitor;
import net.rptools.mtscript.symboltable.SymbolTable;
import net.rptools.mtscript.symboltable.SymbolTableAttributeKey;
import net.rptools.mtscript.symboltable.SymbolTableEntry;
import net.rptools.mtscript.symboltable.SymbolTableStack;
import net.rptools.mtscript.types.MTSType;
import net.rptools.mtscript.types.MTSTypeFactory;
import net.rptools.mtscript.types.PredefinedType;
import net.rptools.mtscript.util.MTScriptConstants;

/**
 * This class provides a visitor for conversion of {@code ParseTree} into a proper AST Tree for
 * later interpreting.
 */
public class BuildASTVisitorImpl extends MTScript2ParserBaseVisitor<ASTNode>
    implements BuildASTVisitor {

  /** The symbol table stack built up during parsing. */
  private final SymbolTableStack symbolTableStack;

  /** The constants used through out the parser. */
  private final MTScriptConstants constants;

  /** The factory used to create {@link ASTNode}s. */
  private final ASTNodeFactory astNodeFactory;

  /** The factory used to create {@link MTSType}s. */
  private final MTSTypeFactory mtsTypeFactory;

  /** Cached map of {@link PredefinedType} to {@link SymbolTableEntry} for performance, */
  private final Map<PredefinedType, MTSType> predefinedTypesMap = new HashMap<>();

  /** The name excluding prefix of the symbol used to hold the entry point. */
  private final String ENTRY_POINT_SYMBOL_NAME_PART = "entry point";
  /** The name including prefix of the symbol used to hold the entry point. */
  private final String ENTRY_POINT_SYMBOL_NAME;

  /**
   * Creates a new {@code BuildASTVisitor}.
   *
   * @param symbolTableStack the symbol table stack used for the AST.
   * @param constants constants used through out the parsing / execution process.
   * @param astNodeFactory the factory used to create {@link ASTNode}s.
   * @param mtsTypeFactory the factory used to create {@link MTSType}s.
   */
  @Inject
  public BuildASTVisitorImpl(
      @Assisted SymbolTableStack symbolTableStack,
      MTScriptConstants constants,
      ASTNodeFactory astNodeFactory,
      MTSTypeFactory mtsTypeFactory) {
    this.symbolTableStack = symbolTableStack;
    this.astNodeFactory = astNodeFactory;
    this.mtsTypeFactory = mtsTypeFactory;
    this.constants = constants;

    ENTRY_POINT_SYMBOL_NAME = constants.getInternalSymbolPrefix() + ENTRY_POINT_SYMBOL_NAME_PART;

    for (PredefinedType pt : PredefinedType.values()) {
      Optional<SymbolTableEntry> entry = symbolTableStack.lookup(pt.getName());
      if (entry.isPresent()) {
        MTSType mtsType = entry.get().getType();
        predefinedTypesMap.put(pt, mtsType);
      }
    }
  }

  @Override
  public ASTNode visitChat(ChatContext ctx) {
    SymbolTableEntry chatSymbolTableEntry = symbolTableStack.create(ENTRY_POINT_SYMBOL_NAME);
    ASTNode astNode = astNodeFactory.create(ASTNodeType.CHAT);
    astNode.setMTSType(predefinedTypesMap.get(PredefinedType.NONE));

    chatSymbolTableEntry.setAttribute(SymbolTableAttributeKey.CODE_AST, astNode);

    SymbolTable chatScope = symbolTableStack.push();
    chatSymbolTableEntry.setAttribute(SymbolTableAttributeKey.SYMBOL_TABLE, chatScope);

    ctx.children.forEach(c -> astNode.addChild(visit(c)));

    return astNode;
  }

  @Override
  public ASTNode visitText(TextContext ctx) {
    // TODO next three lines need to be replaced with lookup of 'writeString' procedure symbol
    ASTNode astNode = astNodeFactory.create(ASTNodeType.CALL_PROCEDURE);
    astNode.setAttribute(ASTAttributeKey.VALUE, "writeString");
    astNode.setMTSType(predefinedTypesMap.get(PredefinedType.NONE));

    ASTNode paramNode = astNodeFactory.create(ASTNodeType.PARAMETERS);
    paramNode.setMTSType(predefinedTypesMap.get(PredefinedType.NONE));
    astNode.addChild(paramNode);

    ASTNode firstParam = astNodeFactory.create(ASTNodeType.LITERAL);
    firstParam.setAttribute(ASTAttributeKey.VALUE, ctx.getText());
    firstParam.setMTSType(predefinedTypesMap.get(PredefinedType.STRING));
    paramNode.addChild(firstParam);

    return astNode;
  }

  @Override
  public ASTNode visitScript(ScriptContext ctx) {
    // Just return the child, as while this level is needed for parsing its no longer required in
    // AST.
    return visitScriptBody(ctx.scriptBody());
  }

  @Override
  public ASTNode visitScriptBody(ScriptBodyContext ctx) {
    ASTNode astNode = astNodeFactory.create(ASTNodeType.SCRIPT);
    // TODO need to think about what MTSType this node will have, as well as how to do equivalent of
    // [h: ... ]
    astNode.setMTSType(predefinedTypesMap.get(PredefinedType.STRING));

    return astNode;
  }
}
