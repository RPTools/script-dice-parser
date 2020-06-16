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

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.testing.fieldbinder.BoundFieldModule;
import net.rptools.mtscript.symboltable.SymbolTableEntryFactory;
import net.rptools.mtscript.symboltable.SymbolTableFactory;
import net.rptools.mtscript.symboltable.SymbolTableStack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SymbolTableModuleTest {

  @Inject private SymbolTableStack symbolTableStack;

  @Inject private SymbolTableEntryFactory symbolTableEntryFactory;

  @Inject private SymbolTableFactory symbolTableFactory;

  @BeforeEach
  public void setup() {
    Guice.createInjector(new SymbolTableModule(), BoundFieldModule.of(this)).injectMembers(this);
  }

  @Test
  public void symbolTableStack() {
    assertNotNull(symbolTableStack);
  }

  @Test
  public void symbolTableFactory() {
    assertNotNull(symbolTableFactory);
  }

  @Test
  public void symbolTableEntryFactory() {
    assertNotNull(symbolTableEntryFactory);
  }
}
