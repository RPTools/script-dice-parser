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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.testing.fieldbinder.BoundFieldModule;
import net.rptools.mtscript.interpreter.runtimestack.RuntimeScopeStack;
import net.rptools.mtscript.interpreter.runtimestack.StackFrameFactory;
import net.rptools.mtscript.symboltable.SymbolTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RuntimeStackModuleTest {

  private static class MockModule extends AbstractModule {

    @Override
    protected void configure() {
      // bind(StackFrameFactory.class).toInstance(mock(StackFrameFactory.class));
      // bind(RuntimeStack.class).toInstance(mock(RuntimeStack.class));
    }
  }

  @Inject private RuntimeStackProvider runtimeStackProvider;

  @Inject private RuntimeScopeStack runtimeScopeStack;

  @Inject private StackFrameFactory stackFrameFactory;

  @BeforeEach
  public void setup() {
    Guice.createInjector(new RuntimeStackModule(), new MockModule(), BoundFieldModule.of(this))
        .injectMembers(this);
  }

  @Test
  public void stackProvider() {
    assertNotNull(runtimeStackProvider);
    assertNotNull(runtimeStackProvider.get());
  }

  @Test
  public void scopeStack() {
    assertNotNull(runtimeScopeStack);
  }

  @Test
  public void frameFactory() {
    assertNotNull(stackFrameFactory);
    assertNotNull(stackFrameFactory.createStackFrame(mock(SymbolTable.class)));
  }
}
