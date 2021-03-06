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

import javax.inject.Provider;
import net.rptools.mtscript.interpreter.runtimestack.RuntimeScopeStack;

/** Provider class for {@link RuntimeScopeStack} for Google guice. */
public class RuntimeScopeStackProvider implements Provider<RuntimeScopeStack> {

  /**
   * Creates a new {@code RuntimeCallStack}.
   *
   * @return a new {@code RuntimeCallStack}.
   */
  @Override
  public RuntimeScopeStack get() {
    return new StandardRunTimeScopeStack();
  }
}
