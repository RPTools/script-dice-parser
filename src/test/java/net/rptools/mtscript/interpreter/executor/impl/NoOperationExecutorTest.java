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
package net.rptools.mtscript.interpreter.executor.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.Optional;
import net.rptools.mtscript.ast.ASTNode;
import net.rptools.mtscript.interpreter.runtimestack.RuntimeStack;
import org.junit.jupiter.api.Test;

class NoOperationExecutorTest {

  @Test
  public void execute() {
    NoOperationExecutor executor = new NoOperationExecutor();
    Optional<Object> val = executor.execute(mock(ASTNode.class), mock(RuntimeStack.class));
    assertTrue(val.isEmpty());
  }
}
