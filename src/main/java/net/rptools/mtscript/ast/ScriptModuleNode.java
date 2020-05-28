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

public class ScriptModuleNode implements ASTNode {
  private final String name;
  private final String version;
  private final String description;
  private final List<ImportNode> imports;
  private final List<DeclarationNode> declarations;
  private final ExportNode
      exports; // TODO Revisit this. Should ExportNode be a thing? Should it just be a
  // List<ExportNode.Export>?

  public ScriptModuleNode(
      String name,
      String version,
      String description,
      List<ImportNode> imports,
      List<DeclarationNode> declarations,
      ExportNode exports) {
    this.name = requireNonNull(name, "name");
    this.version = requireNonNull(version, "version");
    this.description = requireNonNull(description, "description");
    this.imports = requireNonNull(imports, "imports");
    this.declarations = requireNonNull(declarations, "declarations");
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

  public List<DeclarationNode> getDeclarations() {
    return declarations;
  }

  public ExportNode getExports() {
    return exports;
  }
}
