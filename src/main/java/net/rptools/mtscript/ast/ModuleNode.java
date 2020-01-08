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

import java.util.List;

/** ASTNode to hold a module. */
public class ModuleNode implements ASTNode {

    private final String name;
    private final String version;
    private final String description;
    private final List<ImportNode> imports;
    private final List<DeclarationNode> body;
    private final List<ExportNode> exports;

    public ModuleNode(
            String name,
            String version,
            String description,
            List<ImportNode> imports,
            List<DeclarationNode> body,
            List<ExportNode> exports) {
        this.name = requireNonNull(name, "name");
        this.version = requireNonNull(version, "version"); // TODO: Maybe we should make this a SEMVER class
        this.description = description;
        this.imports = requireNonNull(imports, "imports");
        this.body = requireNonNull(body, "body");
        this.exports = requireNonNull(exports, "exports");
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getDescription() {
        return description;
    }

    public List<ImportNode> getImports() {
        return imports;
    }

    public List<DeclarationNode> getBody() {
        return body;
    }

    public List<ExportNode> getExports() {
        return exports;
    }
}
