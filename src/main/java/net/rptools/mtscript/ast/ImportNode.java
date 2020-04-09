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

public class ImportNode implements ASTNode {
  private final String id;
  private final String version;
  private final String as;

  public ImportNode(String id, String version, String as) {
    this.id = requireNonNull(id, "id");
    this.version = requireNonNull(version, "version");
    this.as = as;
  }

  public String getId() {
    return id;
  }

  public String getVersion() {
    return version;
  }

  public String getAs() {
    return as;
  }
}
