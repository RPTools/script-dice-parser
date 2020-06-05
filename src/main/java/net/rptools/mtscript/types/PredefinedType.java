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

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/** Temporary holder for Predefined Types */
public enum PredefinedType {
  NONE("@NONE", ""),
  ANY("@ANY", ""), // Special type used for external procedures/functions that can accept any type.
  INTEGER("integer", 0),
  NUMBER("number", 0.0),
  LIST("list", new JsonArray()),
  DICT("dict", new JsonObject()),
  ROLL("roll", 0), // TODO
  STRING("string", "");

  /** The name of the type. */
  private final String name;

  /** The default "initialized" value for variables of this type. */
  private final Object defaultValue;

  PredefinedType(String name, Object defaultValue) {
    this.name = name;
    this.defaultValue = defaultValue;
  }

  /**
   * Returns the name of the predefined type.
   *
   * @return the name of the predefined type.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the default "uninitialised" value for variables of this type.
   *
   * @return the default "uninitialised" value for variables of this type.
   */
  public Object getDefaultValue() {
    return defaultValue;
  }
}
