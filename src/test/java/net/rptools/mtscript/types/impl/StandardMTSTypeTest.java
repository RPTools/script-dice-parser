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
package net.rptools.mtscript.types.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import net.rptools.mtscript.symboltable.SymbolTableEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StandardMTSTypeTest {

  private SymbolTableEntry[] symbolTableEntries;
  private String[] names;

  @BeforeEach
  public void setup() {
    symbolTableEntries =
        new SymbolTableEntry[] {mock(SymbolTableEntry.class), mock(SymbolTableEntry.class)};
    names = new String[] {"Entry 0", "Entry 1"};
    for (int i = 0; i < symbolTableEntries.length; i++)
      when(symbolTableEntries[i].getName()).thenReturn(names[i]);
  }

  @Test
  public void getSymbolTableEntry() {
    for (int i = 0; i < symbolTableEntries.length; i++) {
      var type = new StandardMTSType(symbolTableEntries[i], i);
      assertEquals(symbolTableEntries[i], type.getSymbolTableEntry());
    }
  }

  @Test
  public void getName() {
    for (int i = 0; i < symbolTableEntries.length; i++) {
      var type = new StandardMTSType(symbolTableEntries[i], i);
      assertEquals(names[i], type.getName());
    }
  }

  @Test
  public void getDefaultValue() {
    for (int i = 0; i < symbolTableEntries.length; i++) {
      var type = new StandardMTSType(symbolTableEntries[i], i);
      assertEquals(i, type.getDefaultValue());
    }

    for (int i = 0; i < symbolTableEntries.length; i++) {
      var type = new StandardMTSType(symbolTableEntries[i], names[i]);
      assertEquals(names[i], type.getDefaultValue());
    }
  }
}
