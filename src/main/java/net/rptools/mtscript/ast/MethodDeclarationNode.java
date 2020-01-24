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
package net.rptools.mtscript.ast;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.Set;

public class MethodDeclarationNode extends DeclarationNode {

    private final String name;
    private final Set<MethodParameter> parameters;
    private final ASTNode body;

    public MethodDeclarationNode(String name, Set<MethodParameter> parameters, ASTNode body) {
        this.name = requireNonNull(name, "name");
        this.parameters = requireNonNull(parameters, "parameters");
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public Set<MethodParameter> getParameters() {
        return parameters;
    }

    public ASTNode getBody() {
        return body;
    }

    public static class MethodParameter {
        private final Type type;
        private final VariableDeclaratorIdNode id;

        public MethodParameter(Type type, VariableDeclaratorIdNode id) {
            this.type = requireNonNull(type, "type");
            this.id = requireNonNull(id, "id");
        }

        public Type getType() { return type; }
        public VariableDeclaratorIdNode getID() { return id; }

        @Override
        public boolean equals(Object o) {
            if ( o == null) {
                return false;
            } else if (this == o) {
                return true;
            }

            if (!(o instanceof MethodParameter)) {
                return false;
            }

            MethodParameter p = (MethodParameter)o;

            return Objects.equals(type, p.type) && Objects.equals(id, p.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(type, id);
        }
    }
}
