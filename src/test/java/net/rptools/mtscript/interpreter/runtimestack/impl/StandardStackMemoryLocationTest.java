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
package net.rptools.mtscript.interpreter.runtimestack.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import net.rptools.mtscript.types.MTSType;
import org.junit.jupiter.api.Test;

class StandardStackMemoryLocationTest {

  @Test
  public void getType() {
    MTSType mtsType = mock(MTSType.class);

    var memLoc = new StandardStackMemoryLocation(mtsType);
    assertEquals(mtsType, memLoc.getType());
  }

  @Test
  public void value() {
    MTSType mtsType = mock(MTSType.class);

    var memLoc = new StandardStackMemoryLocation(mtsType);

    Random random = new Random();

    for (int i = 0; i < 100; i++) {
      int num = random.nextInt(100) + 1;
      memLoc.setValue(num);
      assertEquals(num, memLoc.getValue());

      String val = " ".repeat(num);
      memLoc.setValue(val);
      assertEquals(val, memLoc.getValue());
    }

    var list = new ArrayList<Object>();
    memLoc.setValue(list);
    assertEquals(list, memLoc.getValue());

    var map = new HashMap<Object, Object>();
    memLoc.setValue(map);
    assertEquals(map, memLoc.getValue());
  }
}
