package net.rptools.mtscript.ast;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class ImportNode implements ASTNode {

    private final String name;
    private final String version;
    private final String as;

    public ImportNode(String name, String version, String as) {
        this.name = requireNonNull(name, "name");
        this.version = requireNonNull(version, "version");
        if (as == null) {
            this.as = name;
        } else {
            this.as = as;
        }
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getAs() {
        return as;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, version, as);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof ImportNode)) {
            return false;
        } else if (other == this) {
            return true;
        }

        ImportNode o = (ImportNode) other;
        return Objects.equals(this.name, o.name)
                && Objects.equals(this.version, o.version)
                && Objects.equals(this.as, o.as);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
