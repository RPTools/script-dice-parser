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
package net.rptools.mtscript.types;

/** Temporary holder for Predefined Types */
public enum PredefinedType {
  NONE("@NONE@"),
  INTEGER("integer"),
  NUMBER("number"),
  LIST("list"),
  DICT("dict"),
  ROLL("roll"),
  STRING("string");

  /** The name of the type. */
  private final String name;

  PredefinedType(String name) {
    this.name = name;
  }

  /**
   * Returns the name of the predefined type.
   *
   * @return the name of the predefined type.
   */
  public String getName() {
    return name;
  }
}
