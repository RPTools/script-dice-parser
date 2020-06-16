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

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.rptools.mtscript.interpreter.runtimestack.RuntimeScopeStack;
import net.rptools.mtscript.interpreter.runtimestack.RuntimeStack;

/** Provider class for {@link RuntimeStack} for Google guice. */
class RuntimeStackProvider implements Provider<RuntimeStack> {

  /** {@link RuntimeScopeStackProvider} for creating new {@link RuntimeScopeStack}s . */
  private final RuntimeScopeStackProvider provider;

  /**
   * Creates a new {@code RuntimeStackProvider}.
   *
   * @param runtimeCallStackProvider the {@link RuntimeScopeStackProvider} used to create new {@link
   *     RuntimeScopeStack}s.
   */
  @Inject
  RuntimeStackProvider(RuntimeScopeStackProvider runtimeCallStackProvider) {
    provider = runtimeCallStackProvider;
  }

  /**
   * Returns a new {@link RuntimeStack}.
   *
   * @return a new {@link RuntimeStack}.
   */
  @Override
  public RuntimeStack get() {
    return new StandardRunTimeStack(provider.get());
  }
}
