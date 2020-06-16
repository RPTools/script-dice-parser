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

import static org.junit.jupiter.api.Assertions.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;

class PredefinedTypeTest {

  @Test
  public void getName() {
    assertEquals("@NONE", PredefinedType.NONE.getName());
    assertEquals("@ANY", PredefinedType.ANY.getName());
    assertEquals("integer", PredefinedType.INTEGER.getName());
    assertEquals("number", PredefinedType.NUMBER.getName());
    assertEquals("list", PredefinedType.LIST.getName());
    assertEquals("dict", PredefinedType.DICT.getName());
    assertEquals("roll", PredefinedType.ROLL.getName());
    assertEquals("string", PredefinedType.STRING.getName());
  }

  @Test
  public void getDefaultValue() {
    assertEquals("", PredefinedType.NONE.getDefaultValue());
    assertEquals("", PredefinedType.ANY.getDefaultValue());
    assertEquals(0, PredefinedType.INTEGER.getDefaultValue());
    assertEquals(0.0, PredefinedType.NUMBER.getDefaultValue());
    assertTrue(PredefinedType.LIST.getDefaultValue() instanceof JsonArray);
    assertEquals(0, ((JsonArray) PredefinedType.LIST.getDefaultValue()).size());
    assertTrue(PredefinedType.DICT.getDefaultValue() instanceof JsonObject);
    assertEquals(0, ((JsonObject) PredefinedType.DICT.getDefaultValue()).size());
    assertEquals(0, PredefinedType.ROLL.getDefaultValue());
    assertEquals("", PredefinedType.STRING.getDefaultValue());
  }
}
