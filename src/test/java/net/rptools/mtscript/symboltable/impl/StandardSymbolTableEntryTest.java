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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.List;
import java.util.Map;
import java.util.Random;
import net.rptools.mtscript.symboltable.SymbolTable;
import net.rptools.mtscript.symboltable.SymbolTableAttributeKey;
import net.rptools.mtscript.types.MTSType;
import net.rptools.mtscript.types.MTSTypeDefinition;
import org.junit.jupiter.api.Test;

class StandardSymbolTableEntryTest {

  private static final Random random = new Random();

  @Test
  public void getName() {
    SymbolTable symbolTable = mock(SymbolTable.class);

    for (int i = 0; i < 100; i++) {
      int num = random.nextInt(100) + 1;
      String name = " ".repeat(num);

      var entry = new StandardSymbolTableEntry(name, symbolTable);
      assertEquals(name, entry.getName());
    }

    var entry = new StandardSymbolTableEntry("test", symbolTable);
    assertEquals("test", entry.getName());
  }

  @Test
  public void getSymbolTable() {
    SymbolTable symbolTable = mock(SymbolTable.class);

    var entry = new StandardSymbolTableEntry("test", symbolTable);
    assertEquals(symbolTable, entry.getSymbolTable());
  }

  @Test
  public void attributes() {
    SymbolTable symbolTable = mock(SymbolTable.class);
    var entry = new StandardSymbolTableEntry("test", symbolTable);

    for (var att : SymbolTableAttributeKey.values()) {
      assertTrue(entry.getAttribute(att).isEmpty());
      assertTrue(entry.getAttribute(att, Integer.class).isEmpty());
      assertTrue(entry.getAttribute(att, List.class).isEmpty());
      assertTrue(entry.getAttribute(att, Map.class).isEmpty());
      assertTrue(entry.getAttribute(att, String.class).isEmpty());
    }

    for (var att : SymbolTableAttributeKey.values()) {
      int num = random.nextInt(100) + 1;
      entry.setAttribute(att, num);
      assertTrue(entry.getAttribute(att).isPresent());
      assertEquals(entry.getAttribute(att).get(), num);
      assertTrue(entry.getAttribute(att, Integer.class).isPresent());
      assertEquals(entry.getAttribute(att, Integer.class).get(), num);

      String val = " ".repeat(num);
      entry.setAttribute(att, val);
      assertTrue(entry.getAttribute(att).isPresent());
      assertEquals(entry.getAttribute(att).get(), val);
      assertTrue(entry.getAttribute(att, String.class).isPresent());
      assertEquals(entry.getAttribute(att, String.class).get(), val);
    }
  }

  @Test
  public void type() {
    SymbolTable symbolTable = mock(SymbolTable.class);
    MTSType type = mock(MTSType.class);

    var entry = new StandardSymbolTableEntry("test", symbolTable);

    entry.setType(type);
    assertEquals(type, entry.getType());
  }

  @Test
  public void typeDefinition() {
    SymbolTable symbolTable = mock(SymbolTable.class);

    var entry = new StandardSymbolTableEntry("test", symbolTable);

    for (var typedef : MTSTypeDefinition.values()) {
      entry.setTypeDefinition(typedef);
      assertEquals(typedef, entry.getTypeDefinition());
    }
  }
}
