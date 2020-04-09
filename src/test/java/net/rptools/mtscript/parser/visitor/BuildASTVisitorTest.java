package net.rptools.mtscript.parser.visitor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import net.rptools.mtscript.ast.ASTNode;
import net.rptools.mtscript.ast.ChatNode;
import net.rptools.mtscript.ast.ExportNode;
import net.rptools.mtscript.ast.ScriptModuleNode;
import net.rptools.mtscript.ast.ScriptNode;
import net.rptools.mtscript.ast.TextNode;
import net.rptools.mtscript.parser.MTScript2Lexer;
import net.rptools.mtscript.parser.MTScript2Parser;

public class BuildASTVisitorTest {

    @Test
    @DisplayName("Text")
    public void testText() {
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
    public void testEmptyScript() {
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
    public void testEmptyModule() {
        String input = getResourceAsString("scriptsamples/empty_module.mts2");
        MTScript2Parser parser = createParser(input, true);
        ParseTree ptree = parser.scriptModule();
        BuildASTVisitor visitor = new BuildASTVisitor();
        ASTNode root = ptree.accept(visitor);

        assertNotNull(root);
        assertTrue(root instanceof ScriptModuleNode);
        ScriptModuleNode module = (ScriptModuleNode) root;
        assertEquals(module.getImports().size(), 0);
        assertEquals(module.getDeclarations().size(), 0);
        ExportNode exports = module.getExports();
        assertEquals(exports.get().size(), 0);
    }

    @Test
    @DisplayName("Variable Definitions")
    @Disabled
    public void testVariableDefinitions() {
        String input = getResourceAsString("scriptsamples/variables.mts2");
        MTScript2Parser parser = createParser(input, false);
        ParseTree ptree = parser.chat();
        BuildASTVisitor visitor = new BuildASTVisitor();
        ASTNode root = ptree.accept(visitor);

        ScriptNode script = assertAndGetChatScript(root);
        // TODO Add asserts for variable nodes once they exist.
    }

    /**
     * Useful for asserting that the root node is a {@link ChatNode} containing exactly one {@link ScriptNode}.
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
        return parser;
    }

    private String getResourceAsString(String filename) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(filename);
        if (is != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        }

        throw new IllegalStateException("Unable to get resource as string: " + filename);
    }
}
