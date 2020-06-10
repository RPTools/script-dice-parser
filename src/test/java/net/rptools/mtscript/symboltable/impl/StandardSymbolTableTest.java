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
package net.rptools.mtscript.symboltable.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Random;
import net.rptools.mtscript.symboltable.SymbolTableEntry;
import net.rptools.mtscript.symboltable.SymbolTableEntryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StandardSymbolTableTest {

  private final Random random = new Random();
  private SymbolTableEntryFactory symbolTableEntryFactory;
  private SymbolTableEntry symbolTableEntry;

  @BeforeEach
  public void setup() {
    symbolTableEntryFactory = mock(SymbolTableEntryFactory.class);
    symbolTableEntry = mock(SymbolTableEntry.class);
  }

  @Test
  public void level() {
    for (int i = 0; i < 100; i++) {
      int num = random.nextInt(100) + 1;
      var stable = new StandardSymbolTable(symbolTableEntryFactory, num);
      assertEquals(num, stable.getLevel());
    }
  }

  @Test
  public void createExistsAlready() {
    var stable = new StandardSymbolTable(symbolTableEntryFactory, 1);
    when(symbolTableEntryFactory.create("test", stable)).thenReturn(symbolTableEntry);

    stable.create("test");

    assertThrows(IllegalStateException.class, () -> stable.create("test"));
  }

  @Test
  public void lookup() {
    var stable = new StandardSymbolTable(symbolTableEntryFactory, 1);
    when(symbolTableEntryFactory.create("test", stable)).thenReturn(symbolTableEntry);

    stable.create("test");

    assertTrue(stable.lookup("test").isPresent());
    assertEquals(symbolTableEntry, stable.lookup("test").get());

    assertTrue(stable.lookup("test2").isEmpty());
  }

  @Test
  public void getEntriesEmpty() {
    var stable = new StandardSymbolTable(symbolTableEntryFactory, 1);
    SymbolTableEntry symbolTableEntry2 = mock(SymbolTableEntry.class);
    when(symbolTableEntryFactory.create("test", stable)).thenReturn(symbolTableEntry);
    when(symbolTableEntryFactory.create("test2", stable)).thenReturn(symbolTableEntry2);

    assertTrue(stable.getEntries().isEmpty());

    stable.create("test");
    assertEquals(1, stable.getEntries().size());

    stable.create("test2");
    assertEquals(2, stable.getEntries().size());

    assertTrue(stable.getEntries().contains(symbolTableEntry));
    assertTrue(stable.getEntries().contains(symbolTableEntry2));
  }
}
