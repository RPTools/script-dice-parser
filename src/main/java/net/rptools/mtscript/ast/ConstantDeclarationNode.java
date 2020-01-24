package net.rptools.mtscript.ast;

import static java.util.Objects.requireNonNull;

import java.util.Map;

public class ConstantDeclarationNode extends DeclarationNode {

    // FIXME Support arrays

    private final Type type;
    private final Map<String, VariableInitializerNode> map;

    public ConstantDeclarationNode(Type type, Map<String, VariableInitializerNode> map) {
        this.type = requireNonNull(type, "type");
        this.map = Map.copyOf(requireNonNull(map, "map"));
    }

    public Type getType() { return type; }
    public Map<String, VariableInitializerNode> getMap() { return map; }
}
