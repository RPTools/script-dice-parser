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
import net.rptools.mtscript.ast.ScriptNode;
import net.rptools.mtscript.parser.MTScript2Lexer;
import net.rptools.mtscript.parser.MTScript2Parser;

public class BuildASTVisitorTest {

    @Test
    @DisplayName("Empty Script")
    public void testEmptyScript() {
        String input = getResourceAsString("scriptsamples/empty_script.mts2");
        MTScript2Parser parser = createParser(input, false);
        ParseTree ptree = parser.chat();
        BuildASTVisitor visitor = new BuildASTVisitor();
        ASTNode root = ptree.accept(visitor);

        assertNotNull(root);
        assertTrue(root instanceof ChatNode);
        ChatNode chat = (ChatNode) root;
        assertEquals(chat.getChildren().size(), 1);
        assertTrue(chat.getChildren().get(0) instanceof ScriptNode);
        ScriptNode script = (ScriptNode) chat.getChildren().get(0);
        assertTrue(script.getBody().isEmpty());
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
        System.out.println(root);
        // TODO Add proper asserts here
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
