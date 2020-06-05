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
package net.rptools.mtscript.injection;

import com.google.inject.AbstractModule;
import net.rptools.mtscript.MTScriptErrorStrategy;
import net.rptools.mtscript.ast.ASTNodeFactory;
import net.rptools.mtscript.ast.impl.ASTNodeFactoryImpl;
import net.rptools.mtscript.types.MTSTypeFactory;
import net.rptools.mtscript.types.impl.MTSTypeFactoryImpl;
import net.rptools.mtscript.util.MTScriptConstants;
import net.rptools.mtscript.util.MTScriptConstantsImpl;
import org.antlr.v4.runtime.ANTLRErrorStrategy;

/** Module class for dependency injection. */
public class ScriptModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(ANTLRErrorStrategy.class).to(MTScriptErrorStrategy.class);
    bind(MTScriptConstants.class).to(MTScriptConstantsImpl.class);
    bind(ASTNodeFactory.class).to(ASTNodeFactoryImpl.class);
    bind(MTSTypeFactory.class).to(MTSTypeFactoryImpl.class);
  }
}
