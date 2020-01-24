package net.rptools.mtscript.ast;

import static java.util.Objects.requireNonNull;

public class VariableDeclaratorNode implements ASTNode {

    private final VariableNode variable;
    private final int arrayDepth;
    private final VariableInitializerNode initializer;

    public VariableDeclaratorNode(VariableNode variable, int arrayDepth, VariableInitializerNode initializer) {
        this.variable = requireNonNull(variable, "Passed a null variable node");
        this.arrayDepth = arrayDepth;
        this.initializer = initializer;
    }

    public VariableNode getVariable() { return variable; }
    public VariableInitializerNode getInitializer() { return initializer; }
    public int getArrayDepth() { return arrayDepth; }
    public boolean isArray() { return arrayDepth > 0; }
}
