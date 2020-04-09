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

public class ExportNode implements ASTNode {
    private final List<Export> exports;

    public ExportNode(List<Export> exports) {
        this.exports = requireNonNull(exports, "exports");
    }

    public List<Export> get() {
        return exports;
    }

    public static class Export {
        private final String id;
        private String as = null;
        private String destination = null;

        public Export(String id) {
            this.id = requireNonNull(id, "id");
        }

        public Export withAs(String as) {
            this.as = as;
            return this;
        }

        public Export withDestination(String destination) {
            this.destination = destination;
            return this;
        }

        public String getId() {
            return id;
        }

        public String getAs() {
            return as;
        }

        public String getDestination() {
            return destination;
        }
    }
}
